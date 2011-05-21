package genetic;

import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgo {

	private int _populationSize;
	private int _numberGenerations;
	private ArrayList<Integer[]> _ar_strategies;
	
	public GeneticAlgo(int populationSize,int numberGeneration){
		_populationSize = populationSize;
		_numberGenerations = numberGeneration;
	}
	
	public Integer[] getBestStrategy(){
		_ar_strategies = new ArrayList<Integer[]>();
		for(int i=0;i<_populationSize;i++)
			_ar_strategies.add(createRandomStrategy());
		
		for(int i=0;i<_numberGenerations;i++){
			
			
		}
		
		return _ar_strategies.get(0);
	}
	
	private Integer[] createRandomStrategy(){
		Integer[] strategy = new Integer[11];
		Random random = new Random();
		for(int i=0;i<11;i++){
			strategy[i] = random.nextInt(12)-1;
		}
		if(containCycle(strategy))
			return createRandomStrategy();
		return strategy;
	}
	
	// Return true if strategy contains minimum 1 cycle
	public boolean containCycle(Integer[] strategy){
		int currentPlayer;
		for(int i=0;i<11;i++){
			currentPlayer=i;
			nocycle : for(int j=0;j<12;j++){
				if(strategy[currentPlayer]==-1)
					break nocycle;
				else
					currentPlayer=strategy[currentPlayer];
				if(j==12)
					return true;
			}
		}
		return false;
	}
	
}
