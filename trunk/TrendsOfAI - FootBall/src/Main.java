import view.MainFrame;
import model.Team;
import genetic.GeneticAlgo;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Searching only passes");
		GeneticAlgo algo = new GeneticAlgo(20,100,0.2);
		Integer[] best;
		best = algo.getBestStrategy();		
		System.out.println("Best strategy is");
		algo.printStrategy(best);
		
//		best = new Integer [] {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		Integer [] ar_home = {1,-1,-1,-1,8,-1,-1,-1,-1,8,-1};
		
		MainFrame mainFrame = new MainFrame();
		mainFrame.run(ar_home, best);
	}
}
