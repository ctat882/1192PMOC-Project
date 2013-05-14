/*
 * An abstract representation of a single 
 * sudoku puzzle. This is the data representation
 * used by the back end and has no connection to 
 * any UI elements.
 */
public class Puzzle {
	private int[][] grid;
	private final int NUM_ROWS = 9;
	private final int NUM_COLS = 9;
	
	//Default constructor
	public Puzzle()
	{		
		grid = new int[NUM_ROWS][NUM_COLS];
	}
	
	//returns the value at cell (x, y)
	public int get(int x, int y)
	{
		return grid[x][y];
	}
	
	//sets the value of the cell at (x, y) to
	//the given value
	public void set(int x, int y, int value)
	{
		grid[x][y] = value;
	}

	//Tests if two puzzles are equal in nature
	@Override
	public boolean equals(Object o)
	{
		//TODO: Add your logic here and alter the return statement
		return true;
	}
}