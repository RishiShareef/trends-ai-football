package genetic;

import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgo {

	private int _populationSize;
	private int _numberGenerations;
	private double _mutationRate;
	private ArrayList<Integer[]> _ar_strategies;
	private ArrayList<Integer[]> _ar_champions;
	private static final double _reproductionRate = 0.75;
	private static final double _probaShoot = 0.3;

	public GeneticAlgo(int populationSize, int numberGeneration,
			double mutationRate) {
		_populationSize = populationSize;
		_numberGenerations = numberGeneration;
		_mutationRate = mutationRate;
		_ar_champions = new ArrayList<Integer[]>();
	}

	public Integer[] getBestStrategy() {
		_ar_strategies = new ArrayList<Integer[]>();
		for (int i = 0; i < _populationSize; i++)
			_ar_strategies.add(createRandomStrategy());
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

	private Integer[] createRandomStrategy() {
		Integer[] strategy = new Integer[11];
		Random random = new Random();
		for (int i = 0; i < 11; i++) {
			if(random.nextDouble() < _probaShoot) {
				strategy[i] = -1;
			}
			else {
				strategy[i] = random.nextInt(12) - 1;
			}
		}
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

	private ArrayList<Integer[]> reproduction(Integer[] scores) {
		int strategyId1, strategyId2;
		ArrayList<Integer[]> ar_newPopulation = new ArrayList<Integer[]>();
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
			ar_newPopulation.add(createRandomStrategy());
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

	private Integer[] mateCycle(Integer[] strategy1, Integer[] strategy2) {
		Random random = new Random();
		int ran;
		Integer[] newStrategy = new Integer[11];
		for (int i = 0; i < newStrategy.length; i++)
			newStrategy[i] = null;
		while (!isFull(newStrategy)) {
			ran = random.nextInt(strategy1.length + strategy2.length);
			if (ran > 10) {
				ran = ran % 11;
				while1: while (newStrategy[ran] == null) {
					if (random.nextDouble() > _mutationRate) {
						newStrategy[ran] = strategy2[ran];
					} else {
						int randomPass;
						do {
							randomPass = random.nextInt(12) - 1;
						} while (randomPass == ran);
						newStrategy[ran] = randomPass;
					}
					if (newStrategy[ran] == -1)
						break while1;
					ran = newStrategy[ran];
				}
			} else {
				while2: while (newStrategy[ran] == null) {
					if (random.nextDouble() > _mutationRate) {
						newStrategy[ran] = strategy1[ran];
					} else {
						int randomPass;
						do {
							randomPass = random.nextInt(12) - 1;
						} while (randomPass == ran);
						newStrategy[ran] = randomPass;
					}
					if (newStrategy[ran] == -1)
						break while2;
					ran = newStrategy[ran];
				}
			}
		}
		return newStrategy;
	}

	private Integer[] mateOnePointCrossOver(Integer[] ar_strategy1,
			Integer[] ar_strategy2) {
		Random random = new Random();
		int crossOverPoint = random.nextInt(12);
		Integer[] ar_newStrategy = new Integer[11];

		for (int i = 0; i < crossOverPoint; i++)
			ar_newStrategy[i] = ar_strategy1[i];
		for (int i = crossOverPoint; i < 11; i++)
			ar_newStrategy[i] = ar_strategy2[i];

		for (int i = 0; i < 11; i++) {
			if (random.nextDouble() < _mutationRate) {
				int newStrategy;
				do {
					newStrategy = random.nextInt(12) - 1;
				} while (newStrategy == i);
				ar_newStrategy[i] = newStrategy;
			}
		}

		return ar_newStrategy;
	}

	private boolean isFull(Integer[] ints) {
		for (int i = 0; i < ints.length; i++) {
			if (ints[i] == null)
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

	public void printStrategy(Integer[] strategy) {
		for (int i = 0; i < strategy.length; i++) {
			System.out.print(strategy[i] + " ");
		}
		System.out.println();

	}
}
