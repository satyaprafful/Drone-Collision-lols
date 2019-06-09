

import java.awt.*;


public class Drone extends CircularObjects {
	public static final int INIT_POS_X = 250;
	public static final int INIT_POS_Y = 170;
	public static final int INIT_VEL_X = -2;
	public static final int INIT_VEL_Y = -3;

	public Drone(int courtWidth, int courtHeight, int radius , Color color) {
		super(INIT_VEL_X, INIT_VEL_Y, randomInRange(0, 600), randomInRange(0, 300), radius, courtWidth, courtHeight, color);
	}

	private static int randomInRange(int min, int max) {
		return (int)(Math.random() * ((max - min) + 1)) + min;
	}
	
}