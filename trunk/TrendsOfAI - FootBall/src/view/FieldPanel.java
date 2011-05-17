package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Ball;
import model.Player;

public class FieldPanel extends JPanel {

	private int _xSize, _ySize;
	private Ball _ball;
	private ArrayList<Player> _ar_player;
	
	public FieldPanel(int xSize, int ySize) {
		_xSize = xSize;
		_ySize = ySize;
		validate();
		repaint();
	}
	
	public void initialiseParameters(Ball ball, ArrayList<Player> ar_player) {
		_ball = ball;
		_ar_player = ar_player;
	}
	
	public void paint(Graphics g) {
		initialiseField(g);
		drawPlayers(g);
		drawBall(g);
	}

	private void drawBall(Graphics g) {
		int xPosition = _ball.getXPosition() - _ball.getRadius();
		int yPosition = _ball.getYPosition() - _ball.getRadius();
		int diameter = 2*_ball.getRadius();
		g.setColor(Color.WHITE);
		g.fillArc(xPosition, yPosition, diameter, diameter, 0, 360);
	}

	private void drawPlayers(Graphics g) {
		for(Player player : _ar_player){
			int xPosition = player.getXPosition() - (player.getSize()/2);
			int yPosition = player.getYPosition() - (player.getSize()/2);
			g.setColor(player.getColor());
			g.fillRect(xPosition, yPosition, player.getSize(), player.getSize()); 
		}
	}

	private void initialiseField(Graphics g) {
		g.setColor(new Color(70,200,70));
		g.fillRect(0, 0, _xSize, _ySize);
		g.setColor(Color.WHITE);
		
		g.drawLine(_xSize/2, 0, _xSize/2, _ySize);
		g.drawLine(0, 0, _xSize, 0);
		g.drawLine(0, 0, 0, _ySize);
		g.drawLine(_xSize, 0, _xSize, _ySize);
		g.drawLine(0, _ySize, _xSize, _ySize);

		int rectPosY = _ySize/2 - 202;
		g.drawLine(0, rectPosY, 165, rectPosY);
		g.drawLine(0, _ySize - rectPosY, 165, _ySize - rectPosY);
		g.drawLine(165, rectPosY, 165, _ySize - rectPosY);
		g.drawLine(_xSize, rectPosY, _xSize-165, rectPosY);
		g.drawLine(_xSize, _ySize - rectPosY, _xSize-165, _ySize - rectPosY);
		g.drawLine(_xSize-165, rectPosY, _xSize-165, _ySize - rectPosY);
		
		g.drawArc(_xSize/2-94, _ySize/2-94, 188, 188, 0, 360);
		
		g.setColor(new Color(20, 20, 10));
		g.fillRect(0, _ySize/2 - 37, 3, 74);
		g.fillRect(_xSize-5, _ySize/2 - 37, 3, 74);
	}
	
	public int getXSize() {return _xSize;}
	public int getYSize() {return _ySize;}
}