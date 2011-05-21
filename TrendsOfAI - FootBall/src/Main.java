import view.MainFrame;
import model.Team;
import genetic.GeneticAlgo;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		GeneticAlgo algo = new GeneticAlgo(20,1000,0.1);
//		Integer[] best = algo.getBestStrategy();
		MainFrame mainFrame = new MainFrame();
		
		Integer [] ar_home = {2,5,6,7,8,9,9,10,10,-1,-1};
		Integer [] ar_visitor = {3,5,3,-1,9,1,-1,0,5,3,4,9};
		
		mainFrame.run(ar_home, ar_visitor);
	}
}
