import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;


public class SudokuDesignDaveOne {

	public static void main(String args[]) {
		//Create main game grid
		JPanel gameGrid = new JPanel();
		gameGrid.setLayout(new GridLayout(3,3));
		gameGrid.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.BLACK));
		
		//
		for(int i = 0; i != 9; i++) {
			JPanel subPanel = new JPanel();
			subPanel.setLayout(new GridLayout(3,3));
			for(int j = 0; j != 9; j++) {
				JButton button = new JButton();
			
				subPanel.add(button);
			}
			subPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.BLACK));
			gameGrid.add(subPanel);
		}
		
		JPanel optionsGrid = new JPanel();
		optionsGrid.setLayout(new GridLayout(2,4));
		
		JButton hintButton = new JButton("Hint");
		JButton undoButton = new JButton("Undo");
		JButton resetButton = new JButton("Reset");
		JButton pauseButton  = new JButton("Pause");
		JButton easyButton = new JButton("Easy");
		JButton mediumButton = new JButton("Medium");
		JButton hardButton = new JButton("Hard");
		JButton insaneButton = new JButton("Insane");
		
		optionsGrid.add(hintButton);
		optionsGrid.add(undoButton);
		optionsGrid.add(pauseButton);
		optionsGrid.add(resetButton);
		optionsGrid.add(easyButton);
		optionsGrid.add(mediumButton);
		optionsGrid.add(hardButton);
		optionsGrid.add(insaneButton);
		
		//Create Jframe
		JFrame mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new BorderLayout());
		
		mainFrame.add(optionsGrid, BorderLayout.SOUTH);
		mainFrame.add(gameGrid, BorderLayout.CENTER);
		
		mainFrame.setPreferredSize(new Dimension(300, 300));
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
}
