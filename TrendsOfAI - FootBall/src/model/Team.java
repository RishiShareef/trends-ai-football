package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import view.FieldPanel;

public class Team {

	private ArrayList<Player> _ar_player;
	private final static int _numberPlayers = 11;
	private FieldPanel _fieldPanel;
	private Match _match;
	private Color _teamColor;
	
	public Team(FieldPanel fieldPanel, Match match, Color color) {
		/*
		 * Constructs a random team
		 */
		_ar_player = new ArrayList<Player>();
		_fieldPanel = fieldPanel;
		_match = match;
		_teamColor = color;
		//_teamColor = generateRandomColor();
		generateRandomPlayers();
	}
	
	public Team(FieldPanel fieldPanel, Match match, ArrayList<Player> ar_player, Color color) {
		/*
		 * Constructs a team with ar_player players
		 */
		_fieldPanel = fieldPanel;
		_match = match;
		_teamColor = color;
		//_teamColor = generateRandomColor();
		_ar_player = ar_player;
	}
	
	public void acts(){
		for(Player player : _ar_player)
			player.acts();
	}
	
	private void generateRandomPlayers() {
		Random random = new Random();
		System.out.println(random);
		for(int i = 0; i<_numberPlayers-1; i++)
			_ar_player.add(new Player(this, random.nextInt(_fieldPanel.getXSize()), random.nextInt(_fieldPanel.getYSize()),_teamColor));
		_ar_player.add(new Keeper(this, 10, _fieldPanel.getY()/2,_teamColor));
	}
	
	private Color generateRandomColor() {
		Random random = new Random();
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}
	
	public ArrayList<Player> getPlayers(){return _ar_player;}
	public Match getMatch(){return _match;}
}
