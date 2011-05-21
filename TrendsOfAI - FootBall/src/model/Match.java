package model;

import genetic.Winner;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import view.FieldPanel;

public class Match {
	
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
		_ball = new Ball(_teamHome.getRandomPlayer(), this);
	}
	
	public Winner run() {
		int time = 0;
		while(time < 1000) {
			time++;
//			System.out.println("Match::run >> minute " + time);			
			
			_ball.acts();
			
			if(_ball.getLocationTeamPlaying() == Location.HOME) {
				_teamVisitor.interceptBall(_ball);
			}
			else {
				_teamHome.interceptBall(_ball);
			}
			
			_ball.updatePosition();
			
			if(_fieldPanel!=null)
				_fieldPanel.repaint();
			try {
				if(_fieldPanel!=null)
					Thread.sleep(1000);
			} catch (Exception e) {e.printStackTrace();}
		}
		
		if(_teamHome.getScore() > _teamVisitor.getScore())
			return Winner.HOME;
		else if(_teamHome.getScore() < _teamVisitor.getScore())
			return Winner.VISITOR;
		return Winner.DRAW;
	}
	
	public ArrayList<Player> getAllPlayers(){
		ArrayList<Player> ar_allPlayers = _teamHome.getPlayers();
		ar_allPlayers.addAll(_teamVisitor.getPlayers());
		return ar_allPlayers;
	}
	
	public void setGoal(Location location) {
		if(location == Location.HOME) {
			System.out.println("HOME SCORED !!!");
			_teamHome.score();
			_ball.setBallActor(_teamVisitor.getRandomPlayer());
		}
		else if(location == Location.VISITOR) {
			System.out.println("VISITOR SCORED !!!");
			_teamVisitor.score();
			_ball.setBallActor(_teamHome.getRandomPlayer());
		}
	}
	
	public Ball getBall(){return _ball;}
	
}
