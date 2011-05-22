import view.MainFrame;
import model.Team;
import genetic.GeneticAlgo;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GeneticAlgo algo = new GeneticAlgo(50,30,0.25);
		Integer[] best;
//		best = algo.getBestStrategy();
		MainFrame mainFrame = new MainFrame();
		
		Integer [] ar_home = {1,5,5,8,9,8,9,10,-1,8,-1};
		best = new Integer [] {1,-1,-1,-1,8,-1,-1,-1,-1,8,-1};
		
		mainFrame.run(ar_home, best);
	}
}
