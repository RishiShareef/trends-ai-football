package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Ball;
import model.Player;
import model.Team;

public class FieldPanel extends JPanel {

	private int _xSize, _ySize;
	private Ball _ball;
	private ArrayList<Player> _ar_player;
	private Team _teamHome, _teamVisitor;
	private boolean _showStrategies;

	public FieldPanel(int xSize, int ySize) {
		_xSize = xSize;
		_ySize = ySize;
		_ar_player = new ArrayList<Player>();
		_showStrategies = false;
		validate();
		repaint();
	}
	
	public void initialiseParameters(Ball ball, ArrayList<Player> ar_player, Team teamHome, Team teamVisitor) {
		_ball = ball;
		_ar_player = ar_player;
		_teamHome = teamHome;
		_teamVisitor = teamVisitor;
	}
	
	public void paint(Graphics g) {
		initialiseField(g);
		drawPlayers(g);
		if(!_showStrategies) {
			drawBall(g);
		}
		drawScores(g);
	}

	private void drawBall(Graphics g) {
		// Draw player strategy
		g.setColor(new Color(255,0,0));
		g.drawLine(_ball.getBallActor().getXPosition(), _ball.getBallActor().getYPosition(), _ball.getBallActor().getStrategyPosition().width, _ball.getBallActor().getStrategyPosition().height);
		
		// Draw ball trajectory
		int xPosition = _ball.getBallActor().getXPosition() - _ball.getRadius();
		int yPosition = _ball.getBallActor().getYPosition() - _ball.getRadius();
		int diameter = 2*_ball.getRadius();
		g.setColor(Color.WHITE);
		g.drawLine(_ball.getBallActor().getXPosition(), _ball.getBallActor().getYPosition(), _ball.getNextBallActor().getXPosition(), _ball.getNextBallActor().getYPosition());
		
		// Draw ball
		g.fillArc(xPosition, yPosition, diameter, diameter, 0, 360);
	}

	private void drawPlayers(Graphics g) {
		for(Player player : _ar_player){
			if(_showStrategies) {
				g.setColor(Color.BLACK);
				int posX = player.getXPosition() + (player.getStrategyPosition().width - player.getXPosition())/3 - 3;
				int posY = player.getYPosition() + (player.getStrategyPosition().height - player.getYPosition())/3 - 3;
				g.fillRect(posX, posY, 6, 6);
				g.drawLine(player.getXPosition(), player.getYPosition(), player.getStrategyPosition().width, player.getStrategyPosition().height);
			}
			int xPosition = player.getXPosition() - (player.getSize()/2);
			int yPosition = player.getYPosition() - (player.getSize()/2);
			g.setColor(player.getColor());
			g.fillRect(xPosition, yPosition, player.getSize(), player.getSize());
			g.drawString(((Integer)player.getPosition()).toString(), xPosition, yPosition + 2*player.getSize());
		}
	}
	
	private void drawScores(Graphics g){
		g.setColor(_teamHome.getColor());
		Font font = new Font("Arial", Font.BOLD, 14);
		g.setFont(font);
		FontMetrics fontMetrics = g.getFontMetrics();
		String homeScore = new Integer(_teamHome.getScore()).toString();
		g.drawString(homeScore, (_xSize/2)-20-fontMetrics.stringWidth(homeScore), 20);
		g.setColor(_teamVisitor.getColor());
		g.drawString(new Integer(_teamVisitor.getScore()).toString(), (_xSize/2)+20, 20);
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

	public void showStrategies(boolean showStrategies) {
		_showStrategies = showStrategies;
		repaint();
	}
}
