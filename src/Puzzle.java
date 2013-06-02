import java.util.Arrays;

/**
 * An abstract representation of a single 
 * Sudoku puzzle. This is the data representation
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
	
	/**
	 * Returns the number of rows the puzzle has
	 * @return - number of rows
	 */
	public int getRowCount()
	{
		return NUM_ROWS;
	}
	
	/**
	 * Returns the number of columns the puzzle has
	 * @return - number of columns
	 */
	public int getColCount()
	{
		return NUM_COLS;
	}
	
	/**
	 * Returns the value at cell(x,y
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @return - the value of this cell
	 */
	public int get(int x, int y)
	{
		return grid[x][y];
	}
	/**
	 * sets the value of the cell at (x, y) to
	 * the given value
	 * @param x - x coordinate of the cell
	 * @param y - y coordinate of the cell
	 * @param value - value to be set
	 */
	//
	public void set(int x, int y, int value)
	{
		grid[x][y] = value;
	}
	/**
	 * Tests if two puzzles are equal in nature
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Puzzle other = (Puzzle) obj;
		if (NUM_COLS != other.NUM_COLS)
			return false;
		if (NUM_ROWS != other.NUM_ROWS)
			return false;
		if (!Arrays.deepEquals(grid, other.grid))
			return false;
		return true;
	}
	
}
