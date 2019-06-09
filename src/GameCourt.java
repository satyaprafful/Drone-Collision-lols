

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * GameCourt
 * This class holds the primary game logic for how different objects interact
 * with one another. 
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

//	private Paddle paddle; // the Black paddle, keyboard control
	private Drone[] drones; 
	
	private Drone crasher1;
	private Drone crasher2;

	public boolean playing = false; // whether the game is running

	// Game constants
	public static final int COURT_WIDTH = 600;
	public static final int COURT_HEIGHT = 350;
	public static final int PADDLE_VELOCITY = 5;

	// Update interval for timer, in milliseconds
	public static final int INTERVAL = 16;

	public GameCourt(JLabel status) {
		// creates border around the court area, JComponent method
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// The timer is an object which triggers an action periodically with the given
		// INTERVAL. We
		// register an ActionListener with this timer, whose actionPerformed() method is
		// called each
		// time the timer triggers. We define a helper method called tick() that
		// actually does
		// everything that should be done in a single timestep.
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start();
		setFocusable(true);
	}

	/**
	 * (Re-)set the game to its initial state.
	 */
	public void reset() {
		
		int numOfDrones = 7;
		drones = new Drone[numOfDrones];
		for (int i = 0; i < drones.length; i++) {
			drones[i] = new Drone(COURT_WIDTH, COURT_HEIGHT, 40, Color.BLACK);
		}
		playing = true;

		// Making sure that this component has the keyboard focus
		requestFocusInWindow();
	}

	/**
	 * This method is called every time the timer defined in the constructor
	 * triggers.
	 */
	void tick() {
		if (playing) {
			// Advance the drone in its current direction.
			for (int i = 0; i < drones.length; i++) {
				drones[i].move();
			}
			
			for (int i = 0; i < drones.length; i++) {
				for (int j = 0; j < drones.length; j++) {
					boolean goingToCrashIntoEachOther = drones[i].intersects(drones[j]) > 0; 
					crasher1 = drones[i];
					crasher2 = drones[j];
			
					if (goingToCrashIntoEachOther) {
						dont(); 
					}
					
				}
			}
			
			
			for (int i = 0; i < drones.length; i++) {
				drones[i].hitWall();
			}
			// update the display
			repaint();
		}
	}
	
	void dont() {
		crasher1.hitObj(crasher2, crasher1.intersects(crasher2));
		crasher2.hitObj(crasher1, crasher2.intersects(crasher1)); 

	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < drones.length; i++) {
			drones[i].draw(g);
		}	
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}

	public void pauseGame() {
		playing = false;
	}

	public void resumeGame() {
		playing = true;
	}
}