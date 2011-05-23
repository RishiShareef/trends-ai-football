import view.MainFrame;
import model.Team;
import genetic.GeneticAlgo;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GeneticAlgo algo = new GeneticAlgo(20,10,0.2);
		
		Integer[][] ar_home = new Integer [11][3];
		Integer[][] ar_visitor = new Integer [11][3];
		
//		ar_home = algo.getBestStrategy();
//		ar_visitor = algo.getBestStrategy();
		
		ar_home = algo.createRandomStrategy();
		ar_visitor = algo.createRandomStrategy();
		
		System.out.println("Home strategy is");
		algo.printStrategy(ar_home);
		System.out.println("Visitor strategy is");
		algo.printStrategy(ar_visitor);
		
//		best = new Integer [] {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		
		
		MainFrame mainFrame = new MainFrame();
		mainFrame.run(ar_home, ar_visitor);
	}
}
