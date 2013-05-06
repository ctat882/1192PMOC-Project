import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * @author Corey Tattam
 *
 */
public class SudokuDesignOne {

  /**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		/* Creates the main sudoku grid in a JPanel */
		JPanel mainGrid = new JPanel();
		mainGrid.setLayout(new GridLayout(3,3));
		mainGrid.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		/* Creates 9: 3x3 grids */
		for (int j = 0; j < 9; j++) { 
			JPanel threeByThree = new JPanel();
			threeByThree.setLayout(new GridLayout(3, 3)); 
			for (int i = 0; i < 9; i++) { 
				// TODO: get rid of label...this is just for design
				JLabel numLabel = new JLabel("" + (i + 1),SwingConstants.CENTER);
				numLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
				threeByThree.add(numLabel); 
			}
			threeByThree.setBorder(BorderFactory.createLineBorder(Color.black, 3));
			mainGrid.add(threeByThree);
		}
		/* Create right side panel for input */
		JPanel optionGrid = new JPanel();
		optionGrid.setLayout(new GridLayout(3,3));
		for (int i = 0; i < 9; i++) {
			JButton keyButton = new JButton ("" + (i+1));
			keyButton.setHorizontalTextPosition(SwingConstants.CENTER);
			optionGrid.add(keyButton);
		}
		/* Create the frame containing the game and input panels */
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(1,2));
		frame.add(mainGrid);
		frame.add(optionGrid);		
		
		frame.pack();
		frame.setVisible(true);
		
	}

}
