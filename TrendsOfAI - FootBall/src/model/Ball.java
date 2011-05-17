package model;

import java.awt.Dimension;

public class Ball {
	private int _xPosition;
	private int _yPosition;
	private int _xOldPosition;
	private int _yOldPosition;
	private int _radius;
	private int _xDirection;
	private int _yDirection;
	
	public Ball( int xPosition, int yPosition) {
		_xPosition = xPosition;
		_yPosition = yPosition;
		_xOldPosition = xPosition;
		_yOldPosition = yPosition;
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
	
	public void setPosition(int xPosition, int yPosition) {
		_xPosition = xPosition;
		_yPosition = yPosition;
	}
	public int getRadius() {return _radius;}
	public int getXPosition() {return _xPosition;}
	public int getYPosition() {return _yPosition;}
	public int getXOldPosition() {return _xOldPosition;}
	public int getYOldPosition() {return _yOldPosition;}
	public Dimension getPosition() {return new Dimension(_xPosition, _yPosition);}
}
