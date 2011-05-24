package model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

public class Player implements BallActor {

	protected double _visibility = 70; // max distance to intercept ball
	protected int _xPosition;
	protected int _yPosition;
	protected Team _team;
	protected int _size;
	protected Color _color;
	protected int _pass; // 0 to shoot on goal, 1-11 for pass to player
	protected int _playerPosition; // 1-11

	public Player(Team team, int xPosition, int yPosition, int pass,
			int playerPosition) {
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

	public void acts(Ball ball) {
		BallActor alice;
		if (_pass == -1) {
			alice = _team.getGoal();
		} else {
			alice = _team.getPlayer(_pass);
		}
		ball.setDesiredBallActor(alice);
	}

	public boolean interceptBall(Ball ball) {
		if (ball.isPlaying(this)) {
			return false;
		}
		BallActor alice = ball.getBallActor();
		BallActor bob = ball.getDesiredBallActor();
		double distance = Calculate.calculateSegmentRightPointDistance(
				_xPosition, _yPosition, alice.getXPosition(),
				alice.getYPosition(), bob.getXPosition(), bob.getYPosition());
		if (distance > _visibility)
			return false;
		double passLength = Calculate.calculatePointDistance(alice.getXPosition(), alice.getYPosition(), bob.getXPosition(), bob.getYPosition());
		double proba = 1 / (10*distance / passLength + 1);
//		System.out.println("Player::interceptBall >> player " + _playerPosition
//				+ "; proba = " + proba);
		// proba = 0;
		Random random = new Random();
		if (random.nextDouble() < proba) {
			ball.setBallIntercepted(this);
			return true;
		}
		return false;
	}

	public void setPosition(int posX, int posY) {
		_xPosition = posX;
		_yPosition = posY;
	}

	public int getXPosition() {
		return _xPosition;
	}

	public int getYPosition() {
		return _yPosition;
	}

	public Color getColor() {
		return _color;
	}

	public int getSize() {
		return _size;
	}

	public void print() {
		System.out.println("Player::print >> ");
		System.out.println("\tposition = " + _playerPosition + " ("
				+ _xPosition + ", " + _yPosition + ")");
		System.out.println("\tpass to = " + _pass);
	}

	public int getPosition() {
		return _playerPosition;
	}

	public Location getLocation() {
		return _team.getLocation();
	}

	public Dimension getStrategyPosition() {
		if (_pass == -1) {
			return new Dimension(_team.getGoal().getXPosition(), _team
					.getGoal().getYPosition());
		} else {
			return new Dimension(_team.getPlayer(_pass).getXPosition(), _team
					.getPlayer(_pass).getYPosition());
		}
	}

	public void setVisitorPosition(int xFieldDimension) {
		_xPosition = xFieldDimension - _xPosition;
	}
}
