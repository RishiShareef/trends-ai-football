package model;

import java.awt.Color;
import java.util.Random;

public class Keeper extends Player {
	
	private static final double _keepingDistance = 100;

	public Keeper(Team team, int pass) {
		super(team, 0, team.getYFieldDimension()/2, pass, 0);
	}
	
	public boolean interceptBall(Ball ball) {
		if (ball.isPlaying(this)) {
			return false;
		}
		if(ball.getDesiredBallActor().getXPosition()!=_xPosition || ball.getDesiredBallActor().getYPosition()!=_yPosition) {
			return false;
		}
		
		BallActor alice = ball.getBallActor();
		BallActor bob = ball.getDesiredBallActor();

		double shootLength = Calculate.calculatePointDistance(alice.getXPosition(), alice.getYPosition(), bob.getXPosition(), bob.getYPosition());
		double proba = 1 / (shootLength/_keepingDistance + 1);

		Random random = new Random();
		if (random.nextDouble() < proba) {
			ball.setBallIntercepted(this);
			return true;
		}
		else {
			ball.setDesiredBallActor(_team.getOwnGoal());
		}
		return false;
	}
	
	public void setPosition(int posX, int posY) {
		// Do not replace the keeper
	}
}
