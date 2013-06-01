import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * Searches through a puzzle to provide a 
 * 'Point of interest' style hint in order
 * to help the player proceed.
 */
public class HintSystem {
	
	private Puzzle puzzle;
	
	private final int RANGE = 9;
	
	private int hintsRemaining;

	/**
	 * Set up the hint restrictions based off
	 * the given difficulty of the puzzle
	 * @param difficulty The chosen difficulty of the game
	 */
	public HintSystem(Difficulty difficulty)
	{
		if (difficulty == Difficulty.EASY)
		{
			hintsRemaining = 40;
		}
		else if (difficulty == Difficulty.MEDIUM)
		{
			hintsRemaining = 15;
		}
		else if (difficulty == Difficulty.HARD)
		{
			hintsRemaining = 8;
		}
		else if (difficulty == Difficulty.EXPERT)
		{
			hintsRemaining = 50;
		}
	}
	
	public int getHintsRemaining()
	{
		return hintsRemaining;
	}
	
	/**
	 * Process goes along the line of
	 * -find every empty cell
	 * -randomly choose cells until you find one with a unique possible solution, discarding those which dont
	 * -return the first appropriate cell
	 * @param currentState The data representation of the up to date puzzle board
	 */
	public Point getHintCell(Puzzle currentState)
	{
		if (hintsRemaining > 0)
		{
			hintsRemaining--;
			puzzle = currentState;
			
			ArrayList<Point> emptyCells = new ArrayList<Point>();
			
			for (int i = 0; i < puzzle.getColCount(); i++)
			{
				for (int j = 0; j < puzzle.getRowCount(); j++)
				{
					if (puzzle.get(j,  i) == 0)
					{
						emptyCells.add(new Point(j, i));
					}
				}
			}
			Random random = new Random();
			
			while (emptyCells.size() > 0)
			{
				int solutions = 0;
				Point current = emptyCells.get(random.nextInt(emptyCells.size()));
				
				for (int i = 0; i < RANGE; i++)
				{
					if (legal(current.x, current.y, i + 1, puzzle))
					{
						solutions++;
						
						//There is no need to continue if no unique solution is found
						if (solutions > 1)
							break;
					}
				}
				
				if (solutions == 1)
				{
					return current;
				}
				else
				{
					emptyCells.remove(current);
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Checks if a number entered is valid.
	 * Checks the the column, row and region constraints.
	 * @param col The column number.
	 * @param row The row number.
	 * @param val The value to check.
	 * @param grid The Sudoku grid.
	 * @return Returns true if the move is valid, false otherwise.
	 */
	private boolean legal(int col, int row, int val, Puzzle grid) {
        //Check the row
		boolean valid = true;
		for (int i = 0; i < RANGE; ++i) {  
            if (val == grid.get(col, i))
                valid = false;
		}
        //Check the column
        for (int i = 0; i < RANGE; ++i){ 
            if (val == grid.get(i, row))
                valid = false;
        }
        //Check the 3x3 region
        int boxRowOffset = (col / 3)*3;
        int boxColOffset = (row / 3)*3;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (val == grid.get(boxRowOffset+i, boxColOffset+j))
                    valid = false;
            }
        }    
        return valid;     
	}
}
