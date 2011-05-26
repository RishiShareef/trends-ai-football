package genetic;

import model.*;
import genetic.teamGenerator.TeamGenerator;

import java.awt.Dimension;
import java.util.Random;

public class TestingStrategy {

	private int _numberTest;
	private Dimension _fieldDimension;
	private TeamGenerator _teamGenerator;
	
	public TestingStrategy(int numberTest, TeamGenerator teamGenerator){
		_numberTest = numberTest;
		_teamGenerator = teamGenerator;
		_fieldDimension = new Dimension(1000, 600);
	}
	
	public double testStrategy(Integer[][] strategy){
		double score=0;
		Match match;
		Team home = new Team(_fieldDimension, strategy);
		for(int i=0;i<_numberTest;i++){
			match = new Match(home,new Team(_fieldDimension,createRandomStrategy()),null);
			score += match.run();
		}
		
		return (score/_numberTest);
	}
	
	public void printTotalTest(Integer[][] strategy){
		System.out.println(testStrategy(strategy) + " " + testStrategyPassers(strategy) + " " + testStrategyShooters(strategy));
	}
	
	public double testStrategyShooters(Integer[][] strategy){
		double score=0;
		Match match;
		Team home = new Team(_fieldDimension, strategy);
		Integer[][] visitorStrategy = new Integer [][] {{1, 0, 300}, {-1, 648, 407}, {-1, 681, 320}, {-1, 752, 1}, {-1, 186, 71}, {-1, 652, 333}, {-1, 854, 389}, {-1, 821, 281}, {-1, 685, 544}, {-1, 624, 559}, {-1, 867, 317}};
		Team visitor = new Team(_fieldDimension, visitorStrategy);
		for(int i=0;i<_numberTest;i++){
			match = new Match(home,visitor,null);
			score += match.run();
		}
		
		return (score/_numberTest);
	}
	
	public double testStrategyPassers(Integer[][] strategy){
		double score=0;
		Match match;
		Team home = new Team(_fieldDimension, strategy);
		Integer[][] visitorStrategy = new Integer [][] {{1, 0, 300}, {5, 648, 407}, {6, 681, 320}, {7, 752, 1}, {8, 186, 71}, {9, 652, 333}, {9, 854, 389}, {10, 821, 281}, {10, 685, 544}, {-1, 624, 559}, {-1, 867, 317}};
		Team visitor = new Team(_fieldDimension, visitorStrategy);
		for(int i=0;i<_numberTest;i++){
			match = new Match(home,visitor,null);
			score += match.run();
		}
		
		return (score/_numberTest);
	}
	
	private Integer[][] createRandomStrategy() {
		
		return _teamGenerator.createTeam();
	}
	
	public boolean containCycle(Integer[] strategy) {
		/*
		 * Return true if strategy contains minimum 1 cycle
		 */
		int currentPlayer;
		for (int i = 0; i < 11; i++) {
			currentPlayer = i;
			nocycle: for (int j = 0; j < 12; j++) {
				if (strategy[currentPlayer] == -1)
					break nocycle;
				else
					currentPlayer = strategy[currentPlayer];
				if (j == 12)
					return true;
			}
		}
		return false;
	}
}
