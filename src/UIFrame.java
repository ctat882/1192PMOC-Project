
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * @author damartinable
 * The UI main display frame
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
	
	public Game getGame()
	{
		return game;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public OptionsPanel getOptions()
	{
		return optionsPanel;
	}
	
	public void showGameOver()
	{
		getOptions().getStatsPanel().stopTimer();
		JOptionPane.showMessageDialog(this, "Congratulations! You completed the puzzle.\nThanks for playing.",
				"Well done!", JOptionPane.INFORMATION_MESSAGE);
	}
	
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


