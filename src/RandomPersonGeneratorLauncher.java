import java.awt.GridLayout;
import javax.swing.JFrame;
/**
 * This class sets up the whole program and launches it.
 * @author		Richard Barney
 * @version		1.0.0 June 2015
 */
public class RandomPersonGeneratorLauncher {
	/**
	 * Entry point "main()" as required by the JVM. Launches the program.
	 * @param	args	standard command line parameters (arguments) as a Wtring array.
	 */
	public static void main(String[] args) {
		RandomPersonGeneratorGUI view = new RandomPersonGeneratorGUI();
		JFrame frame = new JFrame("Random Person Generator");
		frame.setSize(450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1,1));
		frame.getContentPane().add(view);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	    frame.setVisible(true);
	} // end method main
} // end class RandomPersonGeneratorLauncher
