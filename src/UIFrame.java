
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
	private SudokuController controller;
	
	private Board board;
	private Game game;
	private NumbersPanel numbersPanel;
	
	public UIFrame(SudokuController controller) {
		super("Sudoku");
		this.controller = controller;
		
		numbersPanel = new NumbersPanel();
		board = new Board(numbersPanel);
		game = new Game();		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(X_PIXELS, Y_PIXELS));
		this.setMinimumSize(new Dimension(X_PIXELS, Y_PIXELS));
		//this.setLayout(new BorderLayout());
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		
		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new BorderLayout());
		upperPanel.add(board, BorderLayout.WEST);
		upperPanel.add(new OptionsPanel(this), BorderLayout.EAST);
		
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

//	private JPanel createSudokuGrid() {
//		JPanel panel = new JPanel();
//		LayoutManager l = new GridBagLayout();
//		GridBagConstraints c = new GridBagConstraints();
//		panel.setLayout(l);
//		
//		for(int i = 0; i < this.HORIZONTAL_LENGTH; i++) {
//			for(int j = 0; j < this.VERTICAL_LENGTH; j++) {
//				UIGridSquare square = new UIGridSquare();
//				squares[i][j] = square;
//				c.gridx = i;
//				c.gridy = j;
//				panel.add(square, c);
//			}
//		}
//		
//		return panel;
//	}
	
//	private JPanel createButtonPanel() {
//		JPanel panel = new JPanel();
//		panel.setLayout(new BorderLayout());
//		JButton reset= new JButton("Reset");
//		JButton undo = new JButton("Undo");
//		JButton newGame = new JButton("New");
//		
//		panel.add(reset, BorderLayout.EAST);
//		panel.add(undo, BorderLayout.CENTER);
//		panel.add(newGame, BorderLayout.WEST);
//		
//		return panel;
//	}
	
}


