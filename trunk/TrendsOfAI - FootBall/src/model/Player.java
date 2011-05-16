package model;

import java.awt.Color;
import java.util.Random;

public class Player {
	
	protected int _xPosition;
	protected int _yPosition;
	protected int _size;
	protected Team _team;
	protected Color _color;

	public Player(Team team, int xPosition, int yPosition, Color color) {
		_team = team;
		_xPosition = xPosition;
		_yPosition = yPosition;
		_size = 15;
		_color = color;
	}
	
	public void acts(){
		Random random = new Random();
		if(Math.abs(_team.getMatch().getBall().getXPosition()-_xPosition)<150 && Math.abs(_team.getMatch().getBall().getYPosition()-_yPosition)<150 ){
			if(_team.getMatch().getBall().getXPosition()>_xPosition)
				_xPosition++;
			else
				_xPosition--;
			if(_team.getMatch().getBall().getYPosition()>_yPosition)
				_yPosition++;
			else
				_yPosition--;
		}
	}
	
	public int getXPosition(){return _xPosition;}
	public int getYPosition(){return _yPosition;}
	public int getSize(){return _size;}
	public Color getColor(){return _color;}
}
