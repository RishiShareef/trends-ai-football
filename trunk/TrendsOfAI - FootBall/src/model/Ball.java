package model;

import java.awt.Dimension;

public class Ball {
	private Player _owner;
	private int _xPosition;
	private int _yPosition;
	private int _xOldPosition;
	private int _yOldPosition;
	private int _radius;
	private int _xDirection;
	private int _yDirection;
	
	public Ball( Player player ) {
		this._owner = player;
		giveBallToPlayer(player);
		this._xOldPosition = player.getXPosition();
		this._yOldPosition = player.getYPosition();
		_radius = 7;
		_xDirection = 0;
		_yDirection = 0;
	}
	
	public void shoot(int xDirection, int yDirection) {
		_xDirection += xDirection;
		_yDirection += yDirection;
	}

	public void updatePosition() {
		_xOldPosition = _xPosition;
		_yOldPosition = _yPosition;
		_xPosition += _xDirection;
		_yPosition += _yDirection;
		_xDirection = 0;
		_yDirection = 0;
	}
	
	public void setOwner(Player player){
		this._owner=player; 
		giveBallToPlayer(player);
	}
	
	public void setPosition(int xPosition, int yPosition){_xPosition = xPosition; _yPosition = yPosition;}
	public void giveBallToPlayer(Player player){_xPosition=player.getXPosition(); _yPosition=player.getYPosition();}
	
	public int getRadius() {return _radius;}
	public int getXPosition() {return _xPosition;}
	public int getYPosition() {return _yPosition;}
	public int getXOldPosition() {return _xOldPosition;}
	public int getYOldPosition() {return _yOldPosition;}
	public Dimension getPosition() {return new Dimension(_xPosition, _yPosition);}
}
