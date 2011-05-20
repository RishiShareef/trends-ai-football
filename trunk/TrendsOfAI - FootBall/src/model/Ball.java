package model;

import java.awt.Dimension;

public class Ball {
	private Player _owner;
	private int _xPosition;
	private int _yPosition;
	private int _xOldPosition;
	private int _yOldPosition;
	private int _radius;
	
	public Ball(Player player) {
		_owner = player;
		_xOldPosition = player.getXPosition();
		_yOldPosition = player.getYPosition();
		_radius = 7;
	}
	
	public void acts() {
		/*
		 * Ask the owner to shoot
		 */
		_owner.acts(this);
	}

	public void updatePosition() {
		setPosition(_owner.getXPosition(), _owner.getYPosition());
	}
	
	public void setOwner(Player player){
		_owner = player;
	}
	
	public void setPosition(int xPosition, int yPosition){
		_xOldPosition = _xPosition;
		_yOldPosition = _yPosition;
		_xPosition = xPosition;
		_yPosition = yPosition;
	}
	
	public int getRadius() {return _radius;}
	public int getXPosition() {return _xPosition;}
	public int getYPosition() {return _yPosition;}
	public int getXOldPosition() {return _xOldPosition;}
	public int getYOldPosition() {return _yOldPosition;}
	public Dimension getPosition() {return new Dimension(_xPosition, _yPosition);}

	public void print() {
		System.out.println("Ball::print >> ");
		System.out.println("\towner = " + _owner.getPosition());
	}

	public boolean isOwner(Player player) {
		return (player == _owner);
	}
}
