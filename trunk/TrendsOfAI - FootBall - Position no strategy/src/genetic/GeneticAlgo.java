package genetic;

import genetic.teamGenerator.TG_Next;
import genetic.teamGenerator.TeamGenerator;

import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgo {

	private int _populationSize;
	private int _numberGenerations;
	private double _mutationRate;
	private ArrayList<Integer[][]> _ar_strategies;
	private ArrayList<Integer[][]> _ar_champions;
	private static final double _reproductionRate = 1;
	private static final double _probaShoot = 0;
	private TeamGenerator _teamGenerator;

	public GeneticAlgo(TeamGenerator teamGenerator, int populationSize, int numberGeneration,
			double mutationRate) {
		_populationSize = populationSize;
		_numberGenerations = numberGeneration;
		_mutationRate = mutationRate;
		_ar_champions = new ArrayList<Integer[][]>();
		_teamGenerator = teamGenerator;
	}

	public Integer[][] getBestStrategy() {
		_ar_champions.clear();
		_ar_strategies = new ArrayList<Integer[][]>();
		for (int i = 1; i < _populationSize; i++)
			_ar_strategies.add(_teamGenerator.createTeam());
//		Integer[] ar_home = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
//		_ar_strategies.add(ar_home);

		Integer[] scores = new Championship(_ar_strategies).getScores();
		_ar_champions.add(_ar_strategies.get(getBestScoreId(scores)));
		for (int i = 1; i < _numberGenerations; i++) {
			System.out.println("Génération " + i);
			_ar_strategies = reproduction(scores);
			scores = new Championship(_ar_strategies).getScores();
			_ar_champions.add(_ar_strategies.get(getBestScoreId(scores)));
			
			printStrategy(_ar_strategies.get(getBestScoreId(scores)));
			System.out.println("Score : " + scores[getBestScoreId(scores)]);
			for (int k = 0; k < _ar_strategies.size(); k++)
				printStrategy(_ar_strategies.get(k));
		}

		scores = new Championship(_ar_champions).getScores();
		return _ar_champions.get(getBestScoreId(scores));
	}

	public boolean containCycle(Integer[][] strategy) {
		/*
		 * Return true if strategy contains minimum 1 cycle
		 */
		int currentPlayer;
		for (int i = 0; i < 11; i++) {
			currentPlayer = i;
			nocycle: for (int j = 0; j < 12; j++) {
				if (strategy[currentPlayer][0] == -1)
					break nocycle;
				else
					currentPlayer = strategy[currentPlayer][0];
				if (j == 12)
					return true;
			}
		}
		return false;
	}

	private ArrayList<Integer[][]> reproduction(Integer[] scores) {
		int strategyId1, strategyId2;
		ArrayList<Integer[][]> ar_newPopulation = new ArrayList<Integer[][]>();
		ar_newPopulation.add(_ar_strategies.get(getBestScoreId(scores)));
		int totalScore = 0;
		for (int i = 0; i < scores.length; i++)
			totalScore += scores[i];
		int numberReproducted = ((Double)((double)_populationSize * _reproductionRate)).intValue();
		for (int i = 1; i < numberReproducted; i++) {
			strategyId1 = chooseteam(scores, totalScore, -1);
			strategyId2 = chooseteam(scores, totalScore, strategyId1);
			ar_newPopulation.add(mateOnePointCrossOver(_ar_strategies.get(strategyId1),
					_ar_strategies.get(strategyId2)));
		}
		for (int i = numberReproducted; i < _populationSize; i++) {
			ar_newPopulation.add(_teamGenerator.createTeam());
		}
		
		return ar_newPopulation;

	}

	private int chooseteam(Integer[] scores, int totalScore, int firstTeam) {
		Random random = new Random();
		int ran, temp;
		int currentTeam = firstTeam;
		while (firstTeam == currentTeam) {
			ran = random.nextInt(totalScore);
			temp = scores[0];
			searchTeam: for (int j = 0; j < _ar_strategies.size(); j++) {
				if (temp > ran) {
					currentTeam = j;
					break searchTeam;
				}
				if (j < _ar_strategies.size() - 1)
					temp += scores[j + 1];
			}
		}

		return currentTeam;
	}

	private Integer[][] mateCycle(Integer[][] strategy1, Integer[][] strategy2) {
		Random random = new Random();
		int ran;
		Integer[][] newStrategy = new Integer[11][3];
		for (int i = 0; i < newStrategy.length; i++)
			newStrategy[i][0] = null;
		while (!isFull(newStrategy)) {
			ran = random.nextInt(strategy1.length + strategy2.length);
			if (ran > 10) {
				ran = ran % 11;
				while1: while (newStrategy[ran][0] == null) {
					if (random.nextDouble() > _mutationRate) {
						for(int i = 0; i<3; i++) {
							newStrategy[ran][i] = strategy2[ran][i];
						}
					} else {
						int randomPass;
						do {
							randomPass = random.nextInt(12) - 1;
						} while (randomPass == ran);
						newStrategy[ran][0] = randomPass;
						if(ran == 0) {
							newStrategy[ran][1] = 0;
							newStrategy[ran][2] = 300;
						}
						else {
							newStrategy[ran][1] = random.nextInt(1000);
							newStrategy[ran][2] = random.nextInt(600);
						}
					}
					if (newStrategy[ran][0] == -1)
						break while1;
					ran = newStrategy[ran][0];
				}
			} else {
				while2: while (newStrategy[ran][0] == null) {
					if (random.nextDouble() > _mutationRate) {
						for(int i = 0; i<3; i++) {
							newStrategy[ran][i] = strategy1[ran][i];
						}
					} else {
						int randomPass;
						do {
							randomPass = random.nextInt(12) - 1;
						} while (randomPass == ran);
						newStrategy[ran][0] = randomPass;
						if(ran == 0) {
							newStrategy[ran][1] = 0;
							newStrategy[ran][2] = 300;
						}
						else {
							newStrategy[ran][1] = random.nextInt(1000);
							newStrategy[ran][2] = random.nextInt(600);
						}
					}
					if (newStrategy[ran][0] == -1)
						break while2;
					ran = newStrategy[ran][0];
				}
			}
		}
		return newStrategy;
	}

	private Integer[][] mateOnePointCrossOver(Integer[][] ar_strategy1,
			Integer[][] ar_strategy2) {
		Random random = new Random();
		int crossOverPoint = random.nextInt(12);
		Integer[][] ar_newStrategy = new Integer[11][3];

		for (int i = 0; i < crossOverPoint; i++) {
			for(int j = 0; j < 3; j++) {
				ar_newStrategy[i][j] = ar_strategy1[i][j];
			}
		}
		for (int i = crossOverPoint; i < 11; i++)
			for(int j = 0; j < 3; j++) {
				ar_newStrategy[i][j] = ar_strategy2[i][j];
			}

		for (int i = 0; i < 11; i++) {
			if (random.nextDouble() < _mutationRate) {
				ar_newStrategy[i][1] = random.nextInt(1000);
				ar_newStrategy[i][2] = random.nextInt(600);
			}
		}

		return ar_newStrategy;
	}

	private boolean isFull(Integer[][] ar_int) {
		for (int i = 0; i < ar_int.length; i++) {
			if (ar_int[i][0] == null)
				return false;
		}
		return true;
	}

	private int getBestScoreId(Integer[] scores) {
		int bestScoreId = 0;
		for (int i = 1; i < scores.length; i++) {
			if (scores[i] > scores[bestScoreId])
				bestScoreId = i;
		}
		return bestScoreId;
	}

	public void printStrategy(Integer[][] strategy) {
		System.out.print("player\t");
		for (int i = 0; i < strategy.length; i++) {
			System.out.print(i + "\t");
		}
		System.out.println();
		System.out.print("posX\t");
		for (int i = 0; i < strategy.length; i++) {
			System.out.print(strategy[i][1] + "\t");
		}
		System.out.println();
		System.out.print("posY\t");
		for (int i = 0; i < strategy.length; i++) {
			System.out.print(strategy[i][2] + "\t");
		}
		System.out.println();
		System.out.print("strat\t");
		for (int i = 0; i < strategy.length; i++) {
			System.out.print(strategy[i][0] + "\t");
		}
		System.out.println();
		System.out.println();
	}
	
	public void printStrategyArray(Integer[][] strategy) {
		System.out.print("{");
		for (int i = 0; i < strategy.length-1; i++) {
			System.out.print("{" + strategy[i][0] + ", " + strategy[i][1] + ", "+ strategy[i][2] + "}, ");
		}
		System.out.println("{" + strategy[strategy.length-1][0] + ", " + strategy[strategy.length-1][1] + ", "+ strategy[strategy.length-1][2] + "}}");
	}
}
