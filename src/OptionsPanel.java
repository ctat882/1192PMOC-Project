import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * The options panel is the container sitting in the
 * north-eastern corner of the screen housing both the option
 * buttons and the contextual stats panel, along with the functionality
 * of both. Due to the event based nature of the UI all this setup is done
 * in the class constructor
 * @author Will
 *
 */
public class OptionsPanel extends JPanel{
	
	private final UIFrame frame;
	private final int buttonSize = 100;
	
	private GameStatsPanel statsPanel;
	
	//The how to play message
	final String howToPlay = new String("The aim of Sudoku is to ultimatly fill every square in the shown 9x9 grid \nwith a number from 1-9.\n" +
			"The rules as to where you can put these numbers are as follows:\n1. Any row must only have the numbers 1-9 exclusivly in it." +
			"\n2. Any column must only have the numbers 1-9 exclusivly in it.\n3. Any marked (by bold lines) 3x3 blocks must only have the numbers 1-9 exclusively in them." +
			"\n\nIf you fill every square in the grid and your solution in NOT correct the game will \ncontinue to run until you have corrected your solution." +
			"\nIf you solution IS correct the game will end, stopping the timer and locking the board \nuntil a new game has begun." +
			"\n\nTo place numbers either use your mouse to select your desired number from the panel \nat the bottom of the screen or select the number using the corresponding number on your keyboard." +
			"\n\nIf at any point you get stuck trying to solve the next step of a solution the HINT button \ncan be used to draw your attention to a single square that once solved, should prompt further progress." +
			"\n\nGood Luck!");
	
	public OptionsPanel(UIFrame theFrame)
	{	
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.frame = theFrame;
		
		setBorder(BorderFactory.createTitledBorder(" Options "));
		
		JButton newGame = new JButton("New Game");
		newGame.setMaximumSize(new Dimension(buttonSize, 30));
		newGame.setPreferredSize(new Dimension(buttonSize, 30));
		newGame.setFocusable(false);
		
		newGame.addActionListener(new
				ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						frame.startNewGame();
						if (frame.getBoard().getIsActive())
							showStatsPanel(frame.getBoard());
					}
				});
		
		JButton reset= new JButton("Reset");
		reset.setMaximumSize(new Dimension(buttonSize, 30));
		reset.setPreferredSize(new Dimension(buttonSize, 30));
		reset.setFocusable(false);
		reset.addActionListener(new
				ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						frame.getGame().resetCurrentPuzzle(frame.getBoard());
					}
				});
		
		JButton undo = new JButton("Undo");
		undo.setMaximumSize(new Dimension(buttonSize, 30));
		undo.setPreferredSize(new Dimension(buttonSize, 30));
		undo.setFocusable(false);
		undo.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event) {
			            frame.getGame().undoMove(frame.getBoard());      
					}
				});
		
		JButton hint = new JButton("Hint");
		hint.setMaximumSize(new Dimension(buttonSize, 30));
		hint.setPreferredSize(new Dimension(buttonSize, 30));
		hint.setFocusable(false);
		hint.addActionListener(new
				ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						frame.getGame().showHint(frame.getBoard());
					}
				});
		
		JButton help = new JButton("How To Play");
		help.setMaximumSize(new Dimension((int)(buttonSize * 1.5f), 30));
		help.setPreferredSize(new Dimension((int)(buttonSize * 1.5f), 30));
		help.setFocusable(false);
		help.addActionListener(new
				ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						JOptionPane.showMessageDialog(frame, howToPlay,
								"How To Play", JOptionPane.INFORMATION_MESSAGE);
					}
				});
		
		JPanel align = new JPanel();
		align.setMaximumSize(new Dimension(300, 110));
		align.setAlignmentX(0);
		align.add(newGame);
		align.add(reset);
		align.add(undo);
		align.add(hint);
		align.add(help);
		
		add(align);	
	}
	
	public GameStatsPanel getStatsPanel()
	{
		return statsPanel;
	}
	
	public void showStatsPanel(Board board)
	{
		if (statsPanel != null)
			remove(statsPanel);
		
		statsPanel = new GameStatsPanel(board);
		add(statsPanel);
	}
}
