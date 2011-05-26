import view.MainFrame;
import model.Team;
import genetic.GeneticAlgo;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GeneticAlgo algo = new GeneticAlgo(20,30,0.2);
		
		Integer[][] ar_home = new Integer [11][3];
		Integer[][] ar_visitor = new Integer [11][3];
		
		ar_home = algo.getBestStrategy();
//		ar_visitor = algo.getBestStrategy();
		ar_visitor = new Integer [][] {{1, 0, 300}, {5, 648, 407}, {6, 681, 320}, {7, 752, 1}, {8, 186, 71}, {9, 652, 333}, {9, 854, 389}, {10, 821, 281}, {10, 685, 544}, {-1, 624, 559}, {-1, 867, 317}};
		
//		ar_home = algo.createRandomStrategy();
//		ar_visitor = algo.createRandomStrategy();
		
		System.out.println("Home strategy is");
		algo.printStrategy(ar_home);
		System.out.println("Visitor strategy is");
		algo.printStrategy(ar_visitor);
		
		
		MainFrame mainFrame = new MainFrame();
		mainFrame.run(ar_home, ar_visitor, true, false);
	}
}
