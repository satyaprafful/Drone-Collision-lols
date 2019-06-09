import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game implements Runnable {
	public void run() {

		// Top-level frame in which game components live
		final JFrame frame = new JFrame("Drone Crashing lols");

		// Status panel
		final JPanel status_panel = new JPanel();
		frame.add(status_panel, BorderLayout.SOUTH);
		final JLabel status = new JLabel("Lmaoooooo");
		status_panel.add(status);

		// Main playing area
		final GameCourt court = new GameCourt(status);
		frame.add(court, BorderLayout.CENTER);

		// Top Panel
		// Note here that when we add an action listener to the reset button, we define
		// it as an
		// anonymous inner class that is an instance of ActionListener with its
		// actionPerformed()
		// method overridden. When the button is pressed, actionPerformed() will be
		// called.
		final JPanel control_panel = new JPanel();
		final JButton reset = new JButton("Restart");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.reset();
			}
		});
		final JButton instructions = new JButton("What is this?");
		instructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.pauseGame();
				JOptionPane.showMessageDialog(frame,
						"So there was a meme a while ago about Amazon Drones"
								+ "\nthat said that the underlying code went something like:"
								+ "\n\n        if (goingToCrashIntoEachOther){\n            dont(); \n        } \n"
								+ "       So I figured, why not build it?\n\n\n\n"
								+ "\nCreated by Satya Prafful"
								+ "\nVer 1.0, 2019",
						"Instructions", JOptionPane.PLAIN_MESSAGE);
				court.resumeGame();
				court.requestFocusInWindow();
				// Pause for another 1 second
			}
		});

		control_panel.add(reset);
		control_panel.add(instructions);

		frame.add(control_panel, BorderLayout.NORTH);

		// Put the frame on the screen
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// Start game
		court.reset();
	}

	/**
	 * Main method run to start and run the game. Initializes the GUI elements
	 * specified in Game and runs it. IMPORTANT: Do NOT delete! You MUST include
	 * this in your final submission.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}