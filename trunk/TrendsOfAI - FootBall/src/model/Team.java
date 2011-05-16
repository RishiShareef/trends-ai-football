package model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import view.FieldPanel;

public class Team {

	private ArrayList<Player> _ar_player;
	private final static int _numberPlayers = 11;
	private Dimension _fieldDimension;
	private Match _match;
	private Color _teamColor;
	private Location _location;
	
	public Team(Dimension fieldDimension) {
		/*
		 * Constructs a random team
		 */
		_ar_player = new ArrayList<Player>();
		_fieldDimension = fieldDimension;
		//_teamColor = generateRandomColor();
		generateRandomPlayers();
	}
	
	public Team(Dimension fieldDimension, ArrayList<Player> ar_player) {
		/*
		 * Constructs a team with ar_player players
		 */
		_fieldDimension = fieldDimension;
		//_teamColor = generateRandomColor();
		_ar_player = ar_player;
	}
	
	public void beginMatch(Match match, Color color, Location location) {
		_match = match;
		_location = location;
		for(Player player : _ar_player) {
			player.beginMatch(color);
		}
	}
	
	public void acts(Ball ball){
		for(Player player : _ar_player)
			player.acts(ball);
	}
	
	private void generateRandomPlayers() {
		Random random = new Random();
		System.out.println(random);
		for(int i = 0; i<_numberPlayers-1; i++) {
			double probaPass = random.nextDouble();
			double [] strategy = {probaPass, 1-probaPass};
			_ar_player.add(new Player(this, random.nextInt(_fieldDimension.width), random.nextInt(_fieldDimension.height), strategy));
		}
		_ar_player.add(new Keeper(this, 10, _fieldDimension.height/2));
	}
	
	private Color generateRandomColor() {
		Random random = new Random();
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}
	
	public ArrayList<Player> getPlayers(){return new ArrayList<Player>(_ar_player);}
	public Match getMatch(){return _match;}
	public Location getLocation() {return _location;}
	public Dimension getGoal() {
		switch(_location) {
		case HOME : return new Dimension(_fieldDimension.width, _fieldDimension.height/2);
		case VISITOR : return new Dimension(0, _fieldDimension.height/2);
		default : return null;
		}
	}
}
