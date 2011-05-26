package model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

public class Team {

	private ArrayList<Player> _ar_player;
	private Goal _goal; // The goal is the opponent's goal, that is the goal you want to shoot into ;)
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
	
	public Team(Dimension fieldDimension, Integer[] ar_playerStrategy) {
		this(fieldDimension);
		_ar_player = new ArrayList<Player>();
		for(int i = 0; i<_numberPlayers; i++) {
			_ar_player.add(new Player(this, _fieldDimension.width/2, fieldDimension.height/2, ar_playerStrategy[i], i));
		}
	}
	
	public Team(Dimension fieldDimension, Integer[][] ar_playerStrategy) {
		this(fieldDimension);
		_ar_player = new ArrayList<Player>();
		_ar_player.add(new Keeper(this, ar_playerStrategy[0][0]));
		for(int i = 1; i<_numberPlayers; i++) {
			_ar_player.add(new Player(this, ar_playerStrategy[i][1], ar_playerStrategy[i][2], ar_playerStrategy[i][0], i));
		}
	}
	
	public void beginMatch(Match match, Color color, Location location) {
		beginMatch(match, color, location, true);
	}
	
	public void beginMatch(Match match, Color color, Location location, boolean replacePlayers) {
		_match = match;
		_location = location;
		_score = 0;
		if(location==Location.HOME) {
			if(replacePlayers)
				replacePlayer();
			_goal = new Goal(_fieldDimension.width, _fieldDimension.height/2, location);
		}
		else if(location==Location.VISITOR) {
			if(replacePlayers)
				replacePlayer();
			else
				replacePlayerVisitor();
			_goal = new Goal(0, _fieldDimension.height/2, location);
		}
		for(Player player : _ar_player) {
			player.beginMatch(color);
		}
	}
	
	public void acts(Ball ball){
		int [] ar_playerID = new int [_ar_player.size()];
		for(int i = 0; i<_ar_player.size(); i++) {
			ar_playerID[i] = i;
		}
		ar_playerID = Calculate.permut(ar_playerID);
		for(int i = 0; i<_ar_player.size(); i++) {
			System.out.println("Team::acts >> + " + ar_playerID[i]);
			_ar_player.get(ar_playerID[i]).acts(ball);
		}
	}
	
	private void generateRandomPlayers() {
		Random random = new Random();
		int randomPass;
		do {
			randomPass = random.nextInt(_numberPlayers);
		}
		while(randomPass == 1);
		_ar_player.add(new Keeper(this, randomPass));
		for(int i = 1; i<_numberPlayers; i++) {
			do {
				randomPass = random.nextInt(_numberPlayers);
				if(random.nextDouble() < _probaShoot) {
					randomPass = -1;
				}
			}
			while(randomPass == i);
			_ar_player.add(new Player(this, random.nextInt(_fieldDimension.width), random.nextInt(_fieldDimension.height), randomPass, i));
		}
	}
	
	public void score() {
		_score++;
	}
	
	public boolean interceptBall(Ball ball) {
		int [] ar_playerID = new int [_ar_player.size()];
		for(int i = 0; i<_ar_player.size(); i++) {
			ar_playerID[i] = i;
		}
		ar_playerID = Calculate.permut(ar_playerID);
		for(int i = 0; i<_ar_player.size(); i++) {
			if(_ar_player.get(ar_playerID[i]).interceptBall(ball))
				return true;
		}
		
		return false;
	}
	
//	public void passToRandomPlayer(Ball ball){
//		Random ran = new Random();
//		Player newOwner = _ar_player.get(ran.nextInt(_ar_player.size()));
//		ball.setOwner(newOwner);
//	}
	
	public void replaceAr_Player() {
		
	}
	
	private void replacePlayer(){
		
//		_ar_player.get(0).setPosition(1*_fieldDimension.width/20, _fieldDimension.height/2);
//		_ar_player.get(1).setPosition(5*_fieldDimension.width/20, _fieldDimension.height/8);
//		_ar_player.get(2).setPosition(3*_fieldDimension.width/20, 3*_fieldDimension.height/8);
//		_ar_player.get(3).setPosition(3*_fieldDimension.width/20, 5*_fieldDimension.height/8);
//		_ar_player.get(4).setPosition(5*_fieldDimension.width/20, 7*_fieldDimension.height/8);
//		_ar_player.get(5).setPosition(12*_fieldDimension.width/20, _fieldDimension.height/8);
//		_ar_player.get(6).setPosition(9*_fieldDimension.width/20, 3*_fieldDimension.height/8);
//		_ar_player.get(7).setPosition(9*_fieldDimension.width/20, 5*_fieldDimension.height/8);
//		_ar_player.get(8).setPosition(12*_fieldDimension.width/20, 7*_fieldDimension.height/8);
//		_ar_player.get(9).setPosition(15*_fieldDimension.width/20, _fieldDimension.height/4);
//		_ar_player.get(10).setPosition(15*_fieldDimension.width/20, 3*_fieldDimension.height/4);
		
		_ar_player.get(0).setPosition(_fieldDimension.width/20, _fieldDimension.height/2);
		_ar_player.get(1).setPosition(4*_fieldDimension.width/20, 2*_fieldDimension.height/8);
		_ar_player.get(2).setPosition(4*_fieldDimension.width/20, 6*_fieldDimension.height/8);
		_ar_player.get(3).setPosition(9*_fieldDimension.width/20, 1*_fieldDimension.height/6);
		_ar_player.get(4).setPosition(9*_fieldDimension.width/20, 2*_fieldDimension.height/6);
		_ar_player.get(5).setPosition(9*_fieldDimension.width/20, 3*_fieldDimension.height/6);
		_ar_player.get(6).setPosition(9*_fieldDimension.width/20, 4*_fieldDimension.height/6);
		_ar_player.get(7).setPosition(9*_fieldDimension.width/20, 5*_fieldDimension.height/6);
		_ar_player.get(8).setPosition(14*_fieldDimension.width/20, _fieldDimension.height/4);
		_ar_player.get(9).setPosition(14*_fieldDimension.width/20, 2*_fieldDimension.height/4);
		_ar_player.get(10).setPosition(14*_fieldDimension.width/20, 3*_fieldDimension.height/4);
	}
	
	private void replacePlayerVisitor(){
		for(Player player : _ar_player) {
			player.setVisitorPosition(_fieldDimension.width);
		}
	}
	
	private Color generateRandomColor() {
		Random random = new Random();
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}
	
	public ArrayList<Player> getPlayers(){return new ArrayList<Player>(_ar_player);}
	public Match getMatch(){return _match;}
	public Location getLocation() {return _location;}
	public Goal getGoal() {return _goal;}
	public Color getColor(){return _teamColor;}
	public int getScore(){return _score;}
	public int getXFieldDimension() {return _fieldDimension.width;}
	public int getYFieldDimension() {return _fieldDimension.height;}
	public void setOpponent(Team opponent){_opponent=opponent;}
	public Team getOpponent(){return _opponent;}
	public void printTeam() {
		System.out.println("Team::printTeam >> ");
		for(Player player : _ar_player) {
			//player.print();
		}
	}

	public Player getRandomPlayer() {
		Random random = new Random();
		return _ar_player.get(random.nextInt(_ar_player.size()));
	}
	public Player getPlayer(int i) {return _ar_player.get(i);}
	public Goal getOwnGoal() {return _match.getOtherTeam(this).getGoal();}
}
