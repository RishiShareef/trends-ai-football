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
		_teamHome.setOpponent(_teamVisitor);
		_teamVisitor.setOpponent(_teamHome);
		_ball = new Ball(_teamHome.getRandomPlayer());
	}
	
	public void run() {
		int time = 0;
		while(time < 1000) {
			time++;
//			System.out.println("Match::run >> minute " + time);			
			
			_ball.acts();
			
			interception : for(Player player : getAllPlayers()) {
				if(player.interceptBall(_ball))
					break interception;
			}
			_ball.updatePosition();
			
			checkGoal();
			_fieldPanel.repaint();
			try {
				sleep(300);
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
	
	public void giveBallToNewPlayer(Team scorer){
		Player newOwner;
		if(scorer.equals(_teamHome)){
			newOwner = chooseRandomPlayer(_teamVisitor);
			_ball.setOwner(newOwner);
		}
		if(scorer.equals(_teamVisitor)){
			newOwner = chooseRandomPlayer(_teamHome);
			_ball.setOwner(newOwner);
		}
				
	}
	
	private Player chooseRandomPlayer(Team team){
		ArrayList<Player> players;
		if(team==null)
			players = getAllPlayers();
		else
			players = team.getPlayers();
		Random random = new Random();
		return players.get(random.nextInt(players.size()));
	}
	
	public Ball getBall(){return _ball;}
	
}
