import view.MainFrame;
import model.Team;
import genetic.GeneticAlgo;
import genetic.teamGenerator.*;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Searching positions");
		Integer[][] ar_home = new Integer [11][3];
		Integer[][] ar_visitor = new Integer [11][3];
		
		GeneticAlgo algo = new GeneticAlgo(new TG_442(), 20, 100, 0.2);
		ar_home = algo.getBestStrategy();
		System.out.println("Best strategy is");
		algo.printStrategyArray(ar_home);
		ar_visitor = (new TG_Selfish()).createTeam();
//		ar_visitor = new Integer [][] {{1, 0, 300}, {5, 648, 407}, {6, 681, 320}, {7, 752, 1}, {8, 186, 71}, {9, 652, 333}, {9, 854, 389}, {10, 821, 281}, {10, 685, 544}, {-1, 624, 559}, {-1, 867, 317}};
		
//		ar_home = algo.createRandomStrategy();
//		ar_visitor = algo.createRandomStrategy();
		
		System.out.println("Home strategy is");
		algo.printStrategy(ar_home);
		System.out.println("Visitor strategy is");
		algo.printStrategy(ar_visitor);
		
		MainFrame mainFrame = new MainFrame();
		mainFrame.run(ar_home, ar_visitor);
	}
}
