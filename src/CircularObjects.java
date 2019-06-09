import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

public abstract class CircularObjects extends GameObj {
	private int radius;
	public static final int INIT_POS_X = 300;
	public static final int INIT_POS_Y = 170;
	public static final int INIT_VEL_X = -2;
	public static final int INIT_VEL_Y = -3;

	private Color color;
	private int courtWidth;
    private BufferedImage droneImage;


	public CircularObjects(int vx, int vy, int px, int py, int radius, int courtWidth, int courtHeight, Color color) {
		super(vx, vy, px, py, 2 * radius, 2 * radius, courtWidth, courtHeight);

		this.radius = radius;
		this.color = color;
		this.courtWidth = courtWidth;
		
		try {
			File f = new File("src/test.png"); 
			droneImage	= ImageIO.read(f);
			droneImage = Scalr.resize(droneImage, 50);

		}
		catch (Exception e) {
		}
		
	}

	@Override
	int intersects(GameObj that) {
		int CircleCentreX = this.getPx() + radius;
		int CircleCentreY = this.getPy() + radius;
		int RectCentreX = that.getPx() + that.getWidth() / 2;
		int RectCentreY = that.getPy() + that.getHeight() / 2;

		// dx and dy between Centre of both Circles and Rectangles
		int HoriDist = Math.abs(CircleCentreX - RectCentreX);
		int VertDist = Math.abs(CircleCentreY - RectCentreY);

		if (HoriDist > that.getWidth() / 2 + radius) {
			return 0;
		}
		if (VertDist > that.getHeight() / 2 + radius) {
			return 0;
		}

		if (HoriDist <= that.getWidth() / 2 || VertDist <= that.getHeight() / 2) {
			if (CircleCentreX > that.getPx() + (that.getWidth())) {
				return 2;
			}
			if (CircleCentreY < that.getPy()) {
				return 1;
			}
			if (CircleCentreX <= that.getPx()) {
				return 4;
			}
			if (CircleCentreY >= that.getPy() + that.getHeight()) {
				return 3;
			}
		}
		return 0;
	}

	// Wall Checks
	@Override
	void hitWall() {
		if (this.getPy() <= 0) {
			this.setVy((Math.abs(this.getVy())));
		}

		if (this.getPx() <= 0) {
			this.setVx(Math.abs(this.getVx()));
		}

		if (this.getPx() >= courtWidth - 80) {
			this.setVx(-Math.abs(this.getVx()));
		}

		
		if (this.getPy() >= 270) {
			this.setVy(-Math.abs(this.getVy())); 
		}
		 

	}

	// Changing the Direction of the Drone based on which side of "that" object the
	// Drone collides
	// Co-ordinate System: Top = 1, Right = 2, Bottom = 3, Left = 4
	@Override
	void hitObj(GameObj that, int collisionSide) {

		if (collisionSide == 1) {
			this.setVy(-(Math.abs(this.getVy())));
		}

		if (collisionSide == 3) {
			this.setVy((Math.abs(this.getVy())));
		}

		if (collisionSide == 2) {
			this.setVx((Math.abs(this.getVx())));
		}

		if (collisionSide == 4) {
			this.setVx(-(Math.abs(this.getVx())));
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(droneImage, this.getPx(), this.getPy(), null);
	}

}
