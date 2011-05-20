package model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import org.omg.CORBA._PolicyStub;

public class Player {
	
	protected double _visibility = 500;
	protected int _xPosition;
	protected int _yPosition;
	protected Team _team;
	protected int _size;
	protected Color _color;
	protected int _pass; // 0 to shoot on goal, 1-11 for pass to player
	protected int _playerPosition; // 1-11

	public Player(Team team, int xPosition, int yPosition, int pass, int playerPosition) {
		_team = team;
		_xPosition = xPosition;
		_yPosition = yPosition;
		_size = 15;
		_pass = pass;
		_playerPosition = playerPosition;
	}
	
	public void beginMatch(Color color) {
		_color = color;
	}
	
	public void acts(Ball ball){
		if(_pass==-1){
			ball.setPosition(_team.getGoal().width, _team.getGoal().height);
		}else {
			ball.setOwner(_team.getPlayer(_pass));
		}
		
	}
	
	public boolean interceptBall(Ball ball) {
		if(ball.isOwner(this)) {
			return false;
		}
		double distance = Calculate.calculateRightPointDistance(_xPosition, _yPosition, ball.getXPosition(), ball.getYPosition(), ball.getXOldPosition(), ball.getYOldPosition());
		System.out.println("Player::interceptBall >> position = " + _playerPosition + "; distance = " + distance);
		if(distance > _visibility)
			return false;
		double proba = 1/(distance/10+1);
		Random random = new Random();
		if(random.nextDouble() < proba) {
			ball.setOwner(this);
			return true;
		}
		return false;
	}
	
	public void setPosition(int posX, int posY){
		this._xPosition = posX;
		this._yPosition = posY;
	}
	
	public int getXPosition(){return _xPosition;}
	public int getYPosition(){return _yPosition;}
	public Color getColor(){return _color;}
	public int getSize() {return _size; }

	public void print() {
		System.out.println("Player::print >> ");
		System.out.println("\tposition = " + _playerPosition + " (" + _xPosition + ", " + _yPosition + ")");
		System.out.println("\tpass to = " + _pass);
	}

	public int getPosition() {
		return _playerPosition;
	}
}
