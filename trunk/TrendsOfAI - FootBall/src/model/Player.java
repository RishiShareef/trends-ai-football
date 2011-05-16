package model;

public class Player {
	
	protected int _xPosition;
	protected int _yPosition;
	protected Team team;

	public Player(Team team, int xPosition, int yPosition) {
		_xPosition = xPosition;
		_yPosition = yPosition;
	}
}
