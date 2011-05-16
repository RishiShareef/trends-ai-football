package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import view.FieldPanel;

public class Match {

	public Match(FieldPanel fieldPanel){
		this.fieldPanel = fieldPanel;
		_team1 = new Team(fieldPanel, this, Color.BLUE);
		_team2 = new Team(fieldPanel, this, Color.RED);
		_ball = new Ball(fieldPanel.getXSize()/2,fieldPanel.getYSize()/2);
	}
	
	public void acts(){
		Random random = new Random();
		if(random.nextBoolean()){
			_team1.acts();
			_team2.acts();
		}else{
			_team2.acts();
			_team1.acts();
		}
	}
	
	public ArrayList<Player> getAllPlayers(){
		ArrayList<Player> ar_allPlayers = _team1.getPlayers();
		for(Player player : _team2.getPlayers())
			ar_allPlayers.add(player);
		return ar_allPlayers;
	}
	
	public Ball getBall(){return _ball;}
	
	private FieldPanel fieldPanel;
	private Team _team1, _team2;
	private Ball _ball;
	
}
