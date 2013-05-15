import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


/*
 * Represents the interface that allows the user to
 * view the state of the current game and interact with it.
 * This class should contain something along the lines of
 * a 2D array of a more complex class of "Sudoku Squares"
 */
public class Board extends JPanel{

	private final int HORIZONTAL_LENGTH = 9;
	private final int VERTICAL_LENGTH = 9;
	
	private UIGridSquare[][] squares; 
	
	//Default constructor. 
	public Board()
	{
		squares = new UIGridSquare[HORIZONTAL_LENGTH][VERTICAL_LENGTH];
		
		generateCells();
	}
	
	//Create all the sudoku squares and store them so they
	//can be easily accessed in the future
	private void generateCells()
	{
		LayoutManager l = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(l);
		
		for(int i = 0; i < HORIZONTAL_LENGTH; i++) {
			for(int j = 0; j < VERTICAL_LENGTH; j++) {
				UIGridSquare square = new UIGridSquare();
				squares[i][j] = square;
				c.gridx = i;
				c.gridy = j;
				add(square, c);
			}
		}
	}
	
	//returns the value at cell (x, y)
	public int get(int x, int y)
	{
		return squares[x][y].getValue();
	}
	
	//sets the value of the cell at (x, y) to
	//the given value
	public void set(int x, int y, int value)
	{
		squares[x][y].setValue(String.valueOf(value));
	}
	
	//This function is to be called from the
	//back-end system when the puzzle has reached
	//its goal state so that the UI can appropriatly
	//display that the game is over
	public void puzzleCompleted()
	{
		
	}
}
