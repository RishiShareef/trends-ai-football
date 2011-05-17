package model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

public class Player {
	
	protected double _visibility = 500;
	protected int _xPosition;
	protected int _yPosition;
	protected Team _team;
	protected int _size;
	protected Color _color;
	protected boolean _hasBall;
	protected double [] _strategy; // strategy[0] = P(pass) / strategy[1] = P(shoot)

	public Player(Team team, int xPosition, int yPosition, double [] strategy) {
		_team = team;
		_xPosition = xPosition;
		_yPosition = yPosition;
		_hasBall = false;
		_size = 15;
		_strategy = strategy;
	}
	
	public void beginMatch(Color color) {
		_color = color;
	}
	
	public void acts(Ball ball){
////		System.out.println("Player::acts >> PPLAY");
//		double distance = calculatePointDistance(_xPosition, _yPosition, ball.getXPosition(), ball.getYPosition());
////		System.out.println("Player::acts >> distance = " + distance);
//		if(distance < _visibility) {
//			
//			double influence = 100/distance;
//			Dimension goal = _team.getGoal();
//			if(calculatePointDistance(ball.getXPosition(), ball.getYPosition(),goal.width, goal.height) < _visibility) {
//				ball.shoot(goal.width - ball.getXPosition(), goal.height - ball.getYPosition());
//			}
//			else {
//				Random random = new Random();
//				if(_team.getLocation() == Location.HOME) {
//					ball.shoot(((Double)(influence*random.nextDouble())).intValue(), ((Double)(influence*(2*random.nextDouble()-1))).intValue());
//				}
//				else {
//					ball.shoot(((Double)(influence*(random.nextDouble()-1))).intValue(), ((Double)(influence*(2*random.nextDouble()-1))).intValue());
//				}
//			}
//		}
		if(_hasBall) {
			Random random = new Random();
			ball.shoot(random.nextInt(_team.getXFieldDimension()), random.nextInt(_team.getYFieldDimension()));
			_hasBall = false;
		}
		
	}
	
	public boolean interceptBall(Ball ball) {
		double distance = Calculate.calculateRightPointDistance(_xPosition, _yPosition, ball.getXPosition(), ball.getYPosition(), ball.getXOldPosition(), ball.getYOldPosition());
		if(distance > _visibility)
			return false;
		double proba = 1/(distance/10+1);
		Random random = new Random();
		if(random.nextDouble() < proba) {
			_hasBall = true;
			return true;
		}
		return false;
	}
	
	public void getBall() {
		_hasBall = true;
	}
	
	public int getXPosition(){return _xPosition;}
	public int getYPosition(){return _yPosition;}
	public Color getColor(){return _color;}
	public int getSize() {return _size; }
}
