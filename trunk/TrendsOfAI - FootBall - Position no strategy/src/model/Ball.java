package model;

import java.awt.Dimension;

public class Ball {
	private BallActor _bob;
	private BallActor _alice;
	private BallActor _oscar;
	private int _radius;
	private Match _match;
	
	public Ball(BallActor ballActor, Match match) {
		_alice = ballActor;
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
	}
	
	public Location getLocationTeamPlaying() {
		return _alice.getLocation();
	}
	
	public int getRadius() {return _radius;}

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
	public BallActor getNextBallActor() {if(_oscar != null) return _oscar; return _bob;}
}
