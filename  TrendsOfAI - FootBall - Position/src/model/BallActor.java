package model;

import java.awt.Dimension;

public interface BallActor {

	public void acts(Ball ball);
	public int getXPosition();
	public int getYPosition();
	public int getPosition();
	public Location getLocation();
	public Dimension getStrategyPosition();
}
