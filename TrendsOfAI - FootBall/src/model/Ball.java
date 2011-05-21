package model;

import java.awt.Dimension;

public class Ball {
	private BallActor _bob;
	private BallActor _alice;
	private BallActor _oscar;
	private int _xPosition;
	private int _yPosition;
	private int _xOldPosition;
	private int _yOldPosition;
	private int _radius;
	private Match _match;
	
	public Ball(BallActor ballActor, Match match) {
		_alice = ballActor;
		_xOldPosition = _alice.getXPosition();
		_yOldPosition = _alice.getYPosition();
		_xPosition = _alice.getXPosition();
		_yPosition = _alice.getYPosition();
		_radius = 7;
		_match = match;
	}
	
	public void acts() {
		/*
		 * Ask the owner to shoot
		 */
		_alice.acts(this);
	}
	
	public void setDesiredBallActor(BallActor ballActor){
		_bob = ballActor;
	}
	
	public void setBallIntercepted(BallActor ballActor) {
		/*
		 * When ball is intercepted, old ball actor does not change !
		 */
		_oscar = ballActor;
	}
	
	public void updatePosition() {
		if(_oscar != null)
			_alice = _oscar;
		else
			_alice = _bob;
		_oscar = null;
		_bob = null;
		setPosition(_alice.getXPosition(), _alice.getYPosition());
	}
	
	public void setPosition(int xPosition, int yPosition){
		_xOldPosition = _xPosition;
		_yOldPosition = _yPosition;
		_xPosition = xPosition;
		_yPosition = yPosition;
	}
	
	public Location getLocationTeamPlaying() {
		return _alice.getLocation();
	}
	
	public int getRadius() {return _radius;}
	public int getXPosition() {return _xPosition;}
	public int getYPosition() {return _yPosition;}
	public int getXOldPosition() {return _xOldPosition;}
	public int getYOldPosition() {return _yOldPosition;}
	public Dimension getPosition() {return new Dimension(_xPosition, _yPosition);}

	public void print() {
		System.out.println("Ball::print >> ");
		System.out.println("\towner = " + _alice.getPosition());
	}

	public boolean isPlaying(BallActor ballActor) {
//		System.out.println("Ball::isPlaying >> " + _oldBallActor.getPosition() + " passes to " + _ballActor.getPosition());
		if(ballActor == _alice)
			return true;
		if(ballActor == _bob)
			return true;
		return false;
	}

	public void setGoal(Location location) {
		_match.setGoal(location);
	}
	public BallActor getBallActor() {return _alice;}
	public BallActor getDesiredBallActor() {return _bob;}
}
