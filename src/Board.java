import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	
	private Boolean isActive = false;
	
	private UIFrame frame;
	
	private UIGridSquare[][] squares; 
	
	//Default constructor. 
	public Board(UIFrame frame, NumbersPanel numbers)
	{
		squares = new UIGridSquare[HORIZONTAL_LENGTH][VERTICAL_LENGTH];
		this.frame = frame;
		
		setBorder(BorderFactory.createTitledBorder(" Board "));
		
		generateCells(numbers);
	}
	
	public UIFrame getFrame()
	{
		return frame;
	}
	
	//Create all the sudoku squares and store them so they
	//can be easily accessed in the future
	private void generateCells(NumbersPanel numbers)
	{
		LayoutManager l = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(l);
		
		JPanel[][] panels = new JPanel[HORIZONTAL_LENGTH / 3][VERTICAL_LENGTH / 3];
		
		c.gridwidth = 3;
		c.gridheight = 3;
		
		for (int i = 0; i < HORIZONTAL_LENGTH / 3; i++)
		{
			for (int j = 0; j < VERTICAL_LENGTH / 3; j++)
			{
				c.gridx = i * 3;
				c.gridy = j * 3;
				
				JPanel panel = new JPanel();
				panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				panel.setLayout(new GridLayout(3, 3));
				panels[i][j] = panel;
				add(panels[i][j], c);
			}
		}
		
		for(int i = 0; i < HORIZONTAL_LENGTH; i++) {
			for(int j = 0; j < VERTICAL_LENGTH; j++) {
				UIGridSquare square = new UIGridSquare(this, numbers, new Point(j, i));
				squares[i][j] = square;
				panels[j / 3][i / 3].add(square);
				c.gridx = i;
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
		if (x < HORIZONTAL_LENGTH && y < VERTICAL_LENGTH && x >= 0 && y >= 0) {
			squares[x][y].setValue(String.valueOf(value));
		}
			
	}
	
	public void setCellProtected(int x, int y, Boolean value) {
		squares[x][y].setIsProtected(value);
	}
	
	public void setHintCell(int x, int y)
	{
		for(int i = 0; i < HORIZONTAL_LENGTH; i++) 
		{
			for(int j = 0; j < VERTICAL_LENGTH; j++) 
			{
				squares[i][j].setHint(false);
			}
		}
		
		squares[x][y].setHint(true);
	}
	
	public void unlockBoard() {
		for(int i = 0; i < HORIZONTAL_LENGTH; i++) {
			for(int j = 0; j < VERTICAL_LENGTH; j++) {
				squares[i][j].setIsProtected(false);
			}
		}
	}
	
	public void resetBoard() {
		for(int i = 0; i < HORIZONTAL_LENGTH; i++) {
			for(int j = 0; j < VERTICAL_LENGTH; j++) {
				squares[i][j].setColor(Color.WHITE);
				squares[i][j].setValue("0");
			}
		}
	}
	
	//This function is to be called from the
	//back-end system when the puzzle has reached
	//its goal state so that the UI can appropriatly
	//display that the game is over
	public void puzzleCompleted()
	{
		frame.showGameOver();
	}
	
	public boolean getIsActive() {
		return isActive;
	}
	
	public void setIsActive(Boolean value) {
		this.isActive = value;
	}
}
