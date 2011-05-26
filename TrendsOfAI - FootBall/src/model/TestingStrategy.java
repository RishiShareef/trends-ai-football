package model;

import java.awt.Dimension;
import java.util.Random;

public class TestingStrategy {

	private int _numberTest;
	private Dimension _fieldDimension;
	
	public TestingStrategy(int numberTest){
		_numberTest = numberTest;
		_fieldDimension = new Dimension(1000, 600);
	}
	
	public double testStrategy(Integer[] strategy){
		double score=0;
		Match match;
		Team home = new Team(_fieldDimension, strategy, 1);
		for(int i=0;i<_numberTest;i++){
			match = new Match(home,new Team(_fieldDimension,createRandomStrategy(),2),null);
			score += match.run();
		}
		
		return (score/_numberTest);
	}
	
	public void printTotalTest(Integer[] strategy){
		System.out.println(testStrategy(strategy) + " " + testStrategyPassers(strategy) + " " + testStrategyShooters(strategy));
	}
	
	public double testStrategyShooters(Integer[] strategy){
		double score=0;
		Match match;
		Team home = new Team(_fieldDimension, strategy, 1);
		Integer[] visitorStrategy = {1,-1,-1,-1,8,-1,-1,-1,-1,8,-1};
		Team visitor = new Team(_fieldDimension, visitorStrategy, 2);
		for(int i=0;i<_numberTest;i++){
			match = new Match(home,visitor,null);
			score += match.run();
		}
		
		return (score/_numberTest);
	}
	
	public double testStrategyPassers(Integer[] strategy){
		double score=0;
		Match match;
		Team home = new Team(_fieldDimension, strategy, 1);
		Integer[] visitorStrategy = {1,5,5,4,9,4,9,6,-1,8,-1};
		Team visitor = new Team(_fieldDimension, visitorStrategy, 2);
		for(int i=0;i<_numberTest;i++){
			match = new Match(home,visitor,null);
			score += match.run();
		}
		
		return (score/_numberTest);
	}
	
	private Integer[] createRandomStrategy() {
		Integer[] strategy = new Integer[11];
		Random random = new Random();
		for (int i = 0; i < 11; i++) 
			strategy[i] = random.nextInt(12) - 1;
		if (containCycle(strategy))
			return createRandomStrategy();
		return strategy;
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
