package model;

import java.util.ArrayList;

public class Team {

	private ArrayList<Player> _ar_player;
	private final static int _numberPlayers = 10;
	
	public Team() {
		/*
		 * Constructs a random team
		 */
		for(int i = 0; i<_numberPlayers; i++) {
			_ar_player.add(new Player());
			_ar_player.add(new Keeper());
		}
	}
	
	public Team(ArrayList<Player> ar_player) {
		/*
		 * Constructs a team with ar_player players
		 */
		_ar_player = ar_player;
	}
}
