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
	private Team _opponent;
	private int _score;
	private static final double _probaShoot = 0.3;
	
	public Team(Dimension fieldDimension) {
		/*
		 * Constructs a random team
		 */
		_score = 0;
		_fieldDimension = fieldDimension;
	}
	
	public Team(Dimension fieldDimension, boolean randomPlayers) {
		/*
		 * Constructs a random team
		 */
		this(fieldDimension);
		_ar_player = new ArrayList<Player>();
		generateRandomPlayers();
	}
	
	public Team(Dimension fieldDimension, ArrayList<Player> ar_player) {
		/*
		 * Constructs a team with ar_player players
		 */
		this(fieldDimension);
		_ar_player = ar_player;
	}
	
	public Team(Dimension fieldDimension, int [] ar_playerStrategy) {
		this(fieldDimension);
		_ar_player = new ArrayList<Player>();
		for(int i = 0; i<_numberPlayers; i++) {
			_ar_player.add(new Player(this,0,0, ar_playerStrategy[i], i));
		}
	}
	
	public void beginMatch(Match match, Color color, Location location) {
		_match = match;
		_location = location;
		if(location==Location.HOME)
			replacePlayerHOME();
		else if(location==Location.VISITOR)
			replacePlayerVISITOR();
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
		int randomPass;
		do {
			randomPass = random.nextInt(_numberPlayers);
		}
		while(randomPass == 1);
		_ar_player.add(new Keeper(this, 10, _fieldDimension.height/2, randomPass));
		for(int i = 0; i<_numberPlayers-1; i++) {
			int position = i+2;
			do {
				randomPass = random.nextInt(_numberPlayers);
				if(random.nextDouble() < _probaShoot) {
					randomPass = -1;
				}
			}
			while(randomPass == position);
			_ar_player.add(new Player(this, random.nextInt(_fieldDimension.width), random.nextInt(_fieldDimension.height), randomPass, position));
		}
	}
	
	public void score(){
		_score++;
		_match.giveBallToNewPlayer(this);
	}
	
	public void passToRandomPlayer(Ball ball){
		Random ran = new Random();
		Player newOwner = _ar_player.get(ran.nextInt(_ar_player.size()));
		ball.setOwner(newOwner);
	}
	
	private void replacePlayerHOME(){
		_ar_player.get(0).setPosition(_fieldDimension.width/20, _fieldDimension.height/2);
		_ar_player.get(1).setPosition(5*_fieldDimension.width/20, _fieldDimension.height/8);
		_ar_player.get(2).setPosition(3*_fieldDimension.width/20, 3*_fieldDimension.height/8);
		_ar_player.get(3).setPosition(3*_fieldDimension.width/20, 5*_fieldDimension.height/8);
		_ar_player.get(4).setPosition(5*_fieldDimension.width/20, 7*_fieldDimension.height/8);
		_ar_player.get(5).setPosition(12*_fieldDimension.width/20, _fieldDimension.height/8);
		_ar_player.get(6).setPosition(9*_fieldDimension.width/20, 3*_fieldDimension.height/8);
		_ar_player.get(7).setPosition(9*_fieldDimension.width/20, 5*_fieldDimension.height/8);
		_ar_player.get(8).setPosition(12*_fieldDimension.width/20, 7*_fieldDimension.height/8);
		_ar_player.get(9).setPosition(15*_fieldDimension.width/20, _fieldDimension.height/4);
		_ar_player.get(10).setPosition(15*_fieldDimension.width/20, 3*_fieldDimension.height/4);
	}
	
	private void replacePlayerVISITOR(){
		_ar_player.get(0).setPosition(19*_fieldDimension.width/20, _fieldDimension.height/2);
		_ar_player.get(1).setPosition(15*_fieldDimension.width/20, _fieldDimension.height/8);
		_ar_player.get(2).setPosition(17*_fieldDimension.width/20, 3*_fieldDimension.height/8);
		_ar_player.get(3).setPosition(17*_fieldDimension.width/20, 5*_fieldDimension.height/8);
		_ar_player.get(4).setPosition(15*_fieldDimension.width/20, 7*_fieldDimension.height/8);
		_ar_player.get(5).setPosition(8*_fieldDimension.width/20, _fieldDimension.height/8);
		_ar_player.get(6).setPosition(11*_fieldDimension.width/20, 3*_fieldDimension.height/8);
		_ar_player.get(7).setPosition(11*_fieldDimension.width/20, 5*_fieldDimension.height/8);
		_ar_player.get(8).setPosition(8*_fieldDimension.width/20, 7*_fieldDimension.height/8);
		_ar_player.get(9).setPosition(5*_fieldDimension.width/20, _fieldDimension.height/4);
		_ar_player.get(10).setPosition(5*_fieldDimension.width/20, 3*_fieldDimension.height/4);
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
	public Color getColor(){return _teamColor;}
	public int getScore(){return _score;}
	public int getXFieldDimension() {return _fieldDimension.width;}
	public int getYFieldDimension() {return _fieldDimension.height;}
	public void setOpponent(Team opponent){_opponent=opponent;}
	public Team getOpponent(){return _opponent;}
	public void printTeam() {
		System.out.println("Team::printTeam >> ");
		for(Player player : _ar_player) {
			player.print();
		}
	}

	public Player getRandomPlayer() {
		Random random = new Random();
		return _ar_player.get(random.nextInt(_ar_player.size()));
	}
	public Player getPlayer(int i) {return _ar_player.get(i);}
}
