package model;

import java.util.Random;

public class Calculate {

	public static double calculatePointDistance(double xPoint1, double yPoint1, double xPoint2, double yPoint2) {
		
		return Math.sqrt(calculatePointDistanceSquare(xPoint1, yPoint1, xPoint2, yPoint2));
	}
	
	public static double calculatePointDistanceSquare(double xPoint1, double yPoint1, double xPoint2, double yPoint2) {
		
		return (xPoint1 - xPoint2)*(xPoint1 - xPoint2) + (yPoint1 - yPoint2)*(yPoint1 - yPoint2);
	}
	
	public static double calculateRightPointDistance(double xPoint, double yPoint, double xRight1, double yRight1, double xRight2, double yRight2) {
		double a = yRight1 - yRight2;
		double b = xRight2 - xRight1;
		double c = -(yRight1 - yRight2)*xRight1 + (xRight1 - xRight2)*yRight1;
		double numerator = Math.abs(a*xPoint + b*yPoint + c);
		double denominator = Math.sqrt(a*a+b*b);
		return numerator/denominator;
	}

	public static double calculateSegmentRightPointDistance(int xPlayer, int yPlayer, int xBallOld, int yBallOld, int xBallNew,	int yBallNew) {
		/*
		 * Returns distance to a right segment
		 * a2 = distance(ballOld, ballNew)^2
		 * b2 = distance(ballOld, playerPosition)^2
		 * c2 = distance(ballNew, playerPosition)^2
		 */
		double a2 = calculatePointDistanceSquare(xBallOld, yBallOld, xBallNew, yBallNew);
		double b2 = calculatePointDistanceSquare(xBallOld, yBallOld, xPlayer, yPlayer);
		double c2 = calculatePointDistanceSquare(xBallNew, yBallNew, xPlayer, yPlayer);
		
		if(b2 > a2 + c2)
			return Integer.MAX_VALUE;
//			return Math.sqrt(c2);
		else if(c2 > a2 + b2)
			return Integer.MAX_VALUE;
//			return Math.sqrt(b2);
		else
			return calculateRightPointDistance(xPlayer, yPlayer, xBallOld, yBallOld, xBallNew, yBallNew);
	}
	
	public static int [] permut(int [] ar_int) {
		Random random = new Random();
		for (int i = 0; i<ar_int.length; i++) {
		    int randomPosition = random.nextInt(ar_int.length);
		    int temp = ar_int[randomPosition];
		    ar_int[randomPosition] = ar_int[i];
		    ar_int[i] = temp;
		}
		
		return ar_int;
	}
	
}
