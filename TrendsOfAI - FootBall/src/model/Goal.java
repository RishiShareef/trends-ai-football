package model;

public class Goal implements BallActor {

	private int _xPosition;
	private int _yPosition;
	private static final int _position = -1;
	private Location _location;
	
	public Goal(int xPosition, int yPosition, Location location) {
		_xPosition = xPosition;
		_yPosition = yPosition;
		_location = location;
	}
	
	public void acts(Ball ball) {
		ball.setGoal(_location);
	}

	public int getXPosition() {return _xPosition;}
	public int getYPosition() {return _yPosition;}
	public int getPosition() {return _position;}

}
