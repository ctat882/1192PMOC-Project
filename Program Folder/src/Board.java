import javax.swing.JPanel;

/*
 * Represents the interface that allows the user to
 * view the state of the current game and interact with it.
 * This class should contain something along the lines of
 * a 2D array of a more complex class of "Sudoku Squares"
 */
public class Board extends JPanel{

	//Default constructor. 
	public Board()
	{
		
		generateCells();
	}
	
	//Create all the sudoku squares and store them so they
	//can be easily accessed in the future
	private void generateCells()
	{
		
	}
	
	//returns the value at cell (x, y)
	public int get(int x, int y)
	{
		return 0;
	}
	
	//sets the value of the cell at (x, y) to
	//the given value
	public void set(int x, int y, int value)
	{
		
	}
}
