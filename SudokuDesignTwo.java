import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * @author Corey Tattam
 *
 */
public class SudokuDesignTwo {

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
				// TODO: implement button functionality
				JButton numButton = new JButton();
				numButton.setHorizontalTextPosition(SwingConstants.CENTER);
				numButton.setBorder(BorderFactory.createLineBorder(Color.black, 1));
				threeByThree.add(numButton); 
			}
			threeByThree.setBorder(BorderFactory.createLineBorder(Color.black, 3));
			mainGrid.add(threeByThree);
		}
		/* Option panel */
		JPanel optionPanel = new JPanel();
		optionPanel.setLayout(new GridLayout(4,1));
		JButton hintB = new JButton("Hint");
		JButton pencilMode = new JButton("Pencil Mode");	//show picture/icon of pencil
		JButton penMode = new JButton("Pen Mode");			//show picture/icon of pen
		JButton eraserMode = new JButton("Eraser");			//show picture/icon of eraser
		
		optionPanel.add(hintB);
		optionPanel.add(penMode);
		optionPanel.add(pencilMode);
		optionPanel.add(eraserMode);
		
		
		
		/* Create a menu bar and menu*/
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menu = new JMenu("New Game");
		/* Create menu items for menu New Game*/
		JMenuItem easyDiff = new JMenuItem ("Easy");
		menu.add(easyDiff);
		JMenuItem medDiff = new JMenuItem ("Medium");
		menu.add(medDiff);
		JMenuItem hardDiff = new JMenuItem ("Hard");
		menu.add(hardDiff);		
		menuBar.add(menu);
		
		/* Menu Help */
		JMenu helpMenu = new JMenu("Help");
		JMenuItem about = new JMenuItem ("About");
		
		helpMenu.add(about);
		
		menuBar.add(helpMenu);
		
		
		/* Create Frame */
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(1,2));
		frame.add(mainGrid);
		frame.add(optionPanel);	
		
		//add menu bar
		frame.setJMenuBar(menuBar);
		
		frame.pack();
		frame.setVisible(true);
		
	}

}
