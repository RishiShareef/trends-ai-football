package model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import view.FieldPanel;

public class Match extends Thread {
	
	private Team _teamHome, _teamVisitor;
	private Ball _ball;
	private FieldPanel _fieldPanel;

	public Match(Team teamHome, Team teamVisitor, FieldPanel fieldPanel){
		_fieldPanel = fieldPanel;
		_teamHome = teamHome;
		_teamHome.beginMatch(this, Color.BLUE, Location.HOME);
		_teamVisitor = teamVisitor;
		_teamVisitor.beginMatch(this, Color.RED, Location.VISITOR);
		_ball = new Ball(fieldPanel.getWidth()/2,fieldPanel.getHeight()/2);
	}
	
	public void run() {
		int time = 0;
		boolean intercetion = false;
		while(time < 1000) {
			time++;
			System.out.println("Match::run >> minute " + time);
//			System.out.println("ball position " + _ball.getPosition());
			
			if(!intercetion) {
//				double minDistance =
			}
			
			for(Player player : getAllPlayers()) {
				player.acts(_ball);
			}
			for(Player player : getAllPlayers()) {
				player.interceptBall(_ball);
			}
			
			_ball.updatePosition();
			checkGoal();
			_fieldPanel.repaint();
			try {
				sleep(100);
			} catch (Exception e) {e.printStackTrace();}
		}
	}
	
	public ArrayList<Player> getAllPlayers(){
		ArrayList<Player> ar_allPlayers = _teamHome.getPlayers();
		ar_allPlayers.addAll(_teamVisitor.getPlayers());
		return ar_allPlayers;
	}
	
	public void checkGoal() {
		if(_ball.getXPosition() <= 0 && _ball.getYPosition() <= _fieldPanel.getHeight()/2 + 37 && _ball.getYPosition() >= _fieldPanel.getHeight()/2 - 37) {
			_ball.setPosition(_fieldPanel.getWidth()/2, _fieldPanel.getHeight()/2);
			System.out.println("Visitor scored !");
		}
		else if(_ball.getXPosition() >= _fieldPanel.getWidth() && _ball.getYPosition() <= _fieldPanel.getHeight()/2 + 37 && _ball.getYPosition() >= _fieldPanel.getHeight()/2 - 37) {
			_ball.setPosition(_fieldPanel.getWidth()/2, _fieldPanel.getHeight()/2);
			System.out.println("Home scored !");
		}
	}
	
	public Ball getBall(){return _ball;}
	
}
