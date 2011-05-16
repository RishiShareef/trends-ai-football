package model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

public class Player {
	
	protected double _visibility = 500;
	protected int _xPosition;
	protected int _yPosition;
	protected int _size;
	protected Team _team;
	protected Color _color;
	protected boolean _hasBall;
	protected double [] _strategy; // strategy[0] = P(pass) / strategy[1] = P(shoot)

	public Player(Team team, int xPosition, int yPosition, double [] strategy) {
		_team = team;
		_xPosition = xPosition;
		_yPosition = yPosition;
		_size = 15;
		_hasBall = false;
		_strategy = strategy;
	}
	
	public void beginMatch(Color color) {
		_color = color;
	}
	
	public void acts(Ball ball){
//		System.out.println("Player::acts >> PPLAY");
		double distance = calculateDistance(_xPosition, _yPosition, ball.getXPosition(), ball.getYPosition());
//		System.out.println("Player::acts >> distance = " + distance);
		if(distance < _visibility) {
			
			double influence = 50/distance;
			Dimension goal = _team.getGoal();
			if(calculateDistance(ball.getXPosition(), ball.getYPosition(),goal.width, goal.height) < _visibility) {
				ball.shoot(goal.width - ball.getXPosition(), goal.height - ball.getYPosition());
			}
			else {
				Random random = new Random();
				if(_team.getLocation() == Location.HOME) {
					ball.shoot(((Double)(influence*random.nextDouble())).intValue(), ((Double)(influence*(2*random.nextDouble()-1))).intValue());
				}
				else {
					ball.shoot(((Double)(influence*(random.nextDouble()-1))).intValue(), ((Double)(influence*(2*random.nextDouble()-1))).intValue());
				}
			}
		}
	}
	
	private double calculateDistance(double xPoint1, double yPoint1, double xPoint2, double yPoint2) {
		
		return Math.sqrt((xPoint1 - xPoint2)*(xPoint1 - xPoint2) + (yPoint1 - yPoint2)*(yPoint1 - yPoint2));
	}
	
	public int getXPosition(){return _xPosition;}
	public int getYPosition(){return _yPosition;}
	public int getSize(){return _size;}
	public Color getColor(){return _color;}
}
