package model;

public class Calculate {

	public static double calculatePointDistance(double xPoint1, double yPoint1, double xPoint2, double yPoint2) {
		
		return Math.sqrt((xPoint1 - xPoint2)*(xPoint1 - xPoint2) + (yPoint1 - yPoint2)*(yPoint1 - yPoint2));
	}
	
	public static double calculateRightPointDistance(double xPoint, double yPoint, double xRight1, double yRight1, double xRight2, double yRight2) {
		double a = yRight1 - yRight2;
		double b = xRight2 - xRight1;
		double c = -(yRight1 - yRight2)*xRight1 + (xRight1 - xRight2)*yRight1;
		double numerator = Math.abs(a*xPoint + b*yPoint + c);
		double denominator = Math.sqrt(a*a+b*b);
		return numerator/denominator;
	}
	
}
