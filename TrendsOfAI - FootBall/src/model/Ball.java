package model;

public class Ball {
	int _xPosition;
	int _yPosition;
	int _radius;
	
	public Ball(int xPosition, int yPosition) {
		_xPosition = xPosition;
		_yPosition = yPosition;
		_radius = 7;
	}
	

	public int getRadius() {return _radius;}
	public int getXPosition() {return _xPosition;}
	public int getYPosition() {return _yPosition;}
}
