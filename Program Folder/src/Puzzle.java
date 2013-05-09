/*
 * An abstract representation of a single 
 * sudoku puzzle. This is the data representation
 * used by the back end and has no connection to 
 * any UI elements.
 */
public class Puzzle {

	//Default constructor
	public Puzzle()
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

	//Tests if two puzzles are equal in nature
	@Override
	public boolean equals(Object o)
	{
		return true;
	}
}
