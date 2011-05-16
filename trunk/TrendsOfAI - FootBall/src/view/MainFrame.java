package view;

import java.awt.Color;

import javax.swing.JFrame;

import model.Ball;

public class MainFrame extends JFrame {

	private FieldPanel _fieldPanel;
	private final static int _xSize = 1000, _ySize = 600;
	
	public MainFrame() {
		setSize(_xSize, _ySize);
		setVisible(true);
		_fieldPanel = new FieldPanel(_xSize, _ySize);
		setContentPane(_fieldPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Football modeliser");
		validate();
	}
}
