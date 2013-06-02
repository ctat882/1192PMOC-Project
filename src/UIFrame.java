
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * The main container class for the sudoku interface
 * representing the frame in which all the UI features are
 * created and embedded.
 * @author damartinable
 *
 */
public class UIFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private final int X_PIXELS = 800;
	private final int Y_PIXELS = 600;
	
	private Board board;
	private Game game;
	private NumbersPanel numbersPanel;
	private OptionsPanel optionsPanel;
	
	/**
	 * Creates the main UI frame for the Sudoku game and adds the necessary UI elements
	 */
	public UIFrame() {
		super("Sudoku");
		
		numbersPanel = new NumbersPanel();
		optionsPanel = new OptionsPanel(this);
		board = new Board(this, numbersPanel);
		game = new Game();	
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(X_PIXELS, Y_PIXELS));
		this.setMinimumSize(new Dimension(X_PIXELS, Y_PIXELS));
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		
		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new BorderLayout());
		upperPanel.add(board, BorderLayout.WEST);
		upperPanel.add(optionsPanel, BorderLayout.CENTER);
		
		this.add(upperPanel);
		this.add(numbersPanel);
		
		this.pack();
		this.setVisible(true);		
	}
	
	/**
	 * Returns this UIFrames Game.
	 * @return - a Game object
	 */
	public Game getGame()
	{
		return game;
	}
	/**
	 * Returns this UIFrames Board
	 * @return - A Board object
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * Return this UIFrames options panel object
	 * @return - the OptionsPanel of this frame
	 */
	public OptionsPanel getOptions()
	{
		return optionsPanel;
	}
	
	/**
	 * If the puzzle is complete, shows a panel that says that the user has completed the puzzle, stops the timer
	 */
	public void showGameOver()
	{
		getOptions().getStatsPanel().stopTimer();
		String timeTaken = getOptions().getStatsPanel().getTime();
		JOptionPane.showMessageDialog(this, "Congratulations! You completed the puzzle.\nThanks for playing.\n\nYour time take was: " + timeTaken + " (hh:mm:ss)",
				"Well done!", JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * Generates a new Sudoku puzzle to be played
	 */
	public void startNewGame()
	{
		Object[] difficulties = {Difficulty.EASY.toString(), Difficulty.MEDIUM.toString(),
						Difficulty.HARD.toString(), Difficulty.EXPERT.toString()};
		
		String result = (String)JOptionPane.showInputDialog(this, "Please select a difficulty for the puzzle:",
						"Start New Game", JOptionPane.PLAIN_MESSAGE, null, difficulties, Difficulty.EASY.toString());
		
		if (result != null)
		{
			Difficulty difficulty = Enum.valueOf(Difficulty.class, result);
			game.generatePuzzle(board, difficulty);
		}
	}
	
}


