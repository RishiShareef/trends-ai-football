package view;

import java.awt.Color;

import javax.swing.JFrame;

import model.Ball;
import model.Match;

public class MainFrame extends JFrame {

	private Match _match;
	private FieldPanel _fieldPanel;
	private final static int _xSize = 1000, _ySize = 600;
	boolean _finished;
	
	public MainFrame() {
		_finished = false;
		setSize(_xSize, _ySize);
		setVisible(true);
		_fieldPanel = new FieldPanel(_xSize, _ySize);
		_match = new Match(_fieldPanel);
		_fieldPanel.initialiseParameters(_match.getBall(), _match.getAllPlayers());
		setContentPane(_fieldPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Football modeliser");
		validate();
		run();
	}
	
	private void run(){
		while(!_finished){
			_match.acts();
			_fieldPanel.repaint();
		}
	}
}
