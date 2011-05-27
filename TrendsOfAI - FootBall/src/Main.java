import view.MainFrame;
import model.Team;
import genetic.GeneticAlgo;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GeneticAlgo algo = new GeneticAlgo(20,20,0.2);
		Integer[] best;
		best = algo.getBestStrategy();		
		System.out.println("Best strategy is");
//		algo.printStrategy(best);
		
//		best = new Integer [] {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		Integer [] ar_home = {1,-1,-1,-1,8,-1,-1,-1,-1,8,-1};
		
		MainFrame mainFrame = new MainFrame();
		mainFrame.run(ar_home, best, false, true);
	}
}
