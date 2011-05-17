package view;

import java.awt.Color;

import javax.swing.JFrame;

import model.Ball;
import model.Match;
import model.Team;

public class MainFrame extends JFrame {

	private FieldPanel _fieldPanel;
	private final static int _xSize = 1000, _ySize = 600;
	boolean _finished;
	
	public MainFrame() {
		_finished = false;
		setSize(_xSize, _ySize+40);
		setVisible(true);
		_fieldPanel = new FieldPanel(_xSize, _ySize);
		setContentPane(_fieldPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Football modeliser");
		validate();
		run();
	}
	
	private void run(){
		Team teamHome = new Team(_fieldPanel.getSize());
		Team teamVisitor = new Team(_fieldPanel.getSize());
		Match match = new Match(teamHome, teamVisitor, _fieldPanel);
		_fieldPanel.initialiseParameters(match.getBall(), match.getAllPlayers());
		
		match.start();
	}
}
