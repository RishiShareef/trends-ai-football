package view;

import java.awt.Color;

import javax.swing.JFrame;

import model.Ball;
import model.Match;
import model.Team;

public class MainFrame extends JFrame {

	private FieldPanel _fieldPanel;
	private final static int _xSize = 1000, _ySize = 600;
	
	public MainFrame() {
		setSize(_xSize, _ySize+40);
		setVisible(true);
		_fieldPanel = new FieldPanel(_xSize, _ySize);
		setContentPane(_fieldPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Football IA");
		validate();
	}
	
	public void run(){
		Team teamHome = new Team(_fieldPanel.getSize(), true);
		Team teamVisitor = new Team(_fieldPanel.getSize(), true);
		teamHome.printTeam();
		teamVisitor.printTeam();
		Match match = new Match(teamHome, teamVisitor, _fieldPanel);
		_fieldPanel.initialiseParameters(match.getBall(), match.getAllPlayers(),teamHome,teamVisitor);
		
		match.run();
	}
	
	public void run(Integer [] ar_playerHome, Integer [] ar_playerVisitor){
		Team teamHome = new Team(_fieldPanel.getSize(), ar_playerHome);
		Team teamVisitor = new Team(_fieldPanel.getSize(), ar_playerVisitor);
		teamHome.printTeam();
		teamVisitor.printTeam();
		Match match = new Match(teamHome, teamVisitor, _fieldPanel);
		_fieldPanel.initialiseParameters(match.getBall(), match.getAllPlayers(),teamHome,teamVisitor);
		
		match.run();
	}
	
	public void run(Integer [][] ar_playerHome, Integer [][] ar_playerVisitor){
		Team teamHome = new Team(_fieldPanel.getSize(), ar_playerHome);
		Team teamVisitor = new Team(_fieldPanel.getSize(), ar_playerVisitor);
		teamHome.printTeam();
		teamVisitor.printTeam();
		Match match = new Match(teamHome, teamVisitor, _fieldPanel);
		_fieldPanel.initialiseParameters(match.getBall(), match.getAllPlayers(),teamHome,teamVisitor);
		
		match.run();
	}
}
