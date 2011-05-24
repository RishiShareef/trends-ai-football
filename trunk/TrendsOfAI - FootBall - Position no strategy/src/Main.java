import view.MainFrame;
import model.Team;
import genetic.GeneticAlgo;
import genetic.teamGenerator.*;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Integer[][] ar_home = new Integer [11][3];
		Integer[][] ar_visitor = new Integer [11][3];
		
		GeneticAlgo algo = new GeneticAlgo(new TG_442(), 20,10,0.2);
		ar_home = algo.getBestStrategy();
		ar_visitor = (new TG_Selfish()).createTeam();
		
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
