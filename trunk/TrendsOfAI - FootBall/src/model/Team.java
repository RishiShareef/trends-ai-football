package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import view.FieldPanel;

public class Team {

	private ArrayList<Player> _ar_player;
	private final static int _numberPlayers = 10;
	private FieldPanel _fieldPanel;
	private Color _teamColor;
	
	public Team(FieldPanel fieldPanel) {
		/*
		 * Constructs a random team
		 */
		_fieldPanel = fieldPanel;
		_teamColor = generateRandomColor();
		generateRandomPlayers();
	}
	
	public Team(FieldPanel fieldPanel, ArrayList<Player> ar_player) {
		/*
		 * Constructs a team with ar_player players
		 */
		_fieldPanel = fieldPanel;
		_teamColor = generateRandomColor();
		_ar_player = ar_player;
	}
	
	private void generateRandomPlayers() {
		for(int i = 0; i<_numberPlayers; i++) {
			Random random = new Random();
			_ar_player.add(new Player(this, random.nextInt(_fieldPanel.getXSize()), random.nextInt(_fieldPanel.getYSize())));
			_ar_player.add(new Keeper(this, 0, _fieldPanel.getY()/2));
		}
	}
	
	private Color generateRandomColor() {
		Random random = new Random();
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}
}
