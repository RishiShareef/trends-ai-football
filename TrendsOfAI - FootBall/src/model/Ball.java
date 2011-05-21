package model;

import java.awt.Dimension;

public class Ball {
	private BallActor _ballActor;
	private BallActor _oldBallActor;
	private int _xPosition;
	private int _yPosition;
	private int _xOldPosition;
	private int _yOldPosition;
	private int _radius;
	private Match _match;
	
	public Ball(BallActor ballActor, Match match) {
		_ballActor = ballActor;
		_oldBallActor = ballActor;
		_xOldPosition = _ballActor.getXPosition();
		_yOldPosition = _ballActor.getYPosition();
		_radius = 7;
		_match = match;
	}
	
	public void acts() {
		/*
		 * Ask the owner to shoot
		 */
		_ballActor.acts(this);
	}

	public void updatePosition() {
		setPosition(_ballActor.getXPosition(), _ballActor.getYPosition());
	}
	
	public void setBallActor(BallActor ballActor){
		_oldBallActor = _ballActor;
		_ballActor = ballActor;
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
		System.out.println("\towner = " + _ballActor.getPosition());
	}

	public boolean canInteract(BallActor ballActor) {
		return (!(ballActor == _ballActor || ballActor == _oldBallActor));
	}

	public void setGoal(Location location) {
		_match.setGoal(location);
	}
}
