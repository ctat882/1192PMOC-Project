
public class UniqueChecker {
	

	private static final int ASCENDING = 0;
	private static final int DESCENDING = 1;
	
	/**
	 * Checks if the current Sudoku puzzle has a unique solution.
	 * This is achieved by using the "3-pass" technique, which involves
	 * brute-force solving the puzzle from top to bottom, left to right, testing
	 * numbers sequentially from 1 to 9. This solution is then compared to 
	 * the same brute-force solver, only the numbers tested go from 9 to 1.
	 * If these two solutions are equal, then a 3rd brute-force solver is 
	 * used, which runs from bottom to top, right to left.
	 * Finally if this solution matches the last two, a unique solution exists.
	 * @param pC The puzzle creator holding the Sudoku grid that needs
	 * to be manipulated.
	 * @return Returns true if there is only one solution, false otherwise.
	 */
	public static boolean hasUniqueSolution (PuzzleCreator pC) {
		boolean unique = false;
		copyGrid(pC.testGridOne,pC.testGridTwo);		
		int[][] testGridThree = new int[PuzzleCreator.SIZE][PuzzleCreator.SIZE];
		copyGrid(pC.testGridOne,testGridThree);
		if (solver(0,0,ASCENDING,pC.testGridOne) && solver(0,0,DESCENDING,pC.testGridTwo)) {
			if (gridsEqual(pC.testGridOne,pC.testGridTwo)) {
				if (backSolver(8,8,ASCENDING,testGridThree)) {
					if (gridsEqual(pC.testGridOne,testGridThree)) {				
						if (gridsEqual(pC.testGridOne,pC.solutionGrid)) {
							unique = true;
						}
					}
				}
			}	
		}
		return unique;		
	}
	/**
	 * Solves a Sudoku puzzle using a brute-force technique.
	 * Recursively solves a sudoku puzzle by iterating through numbers
	 * 1 - 9, in ascending or descending order from bottom to top, right to left.
	 * @param col The column to start from.
	 * @param row The row to start from.
	 * @param direction Iterate through the numbers in either ASCENDING or DESCENDING order.
	 * @param grid The Sudoku grid.
	 * @return Return true if a solution was found, false otherwise.
	 */
	private static boolean backSolver (int col, int row, int direction, int[][] grid) {		
		if (col == -1) {
			col = 8;
			row = row - 1;
			if (row == -1)
				return true;
		}
		// if the current cell has a number
		if (grid[col][row] != 0)			
			return backSolver(col - 1,row,direction,grid);
		// iterate from 1-9 
		if (direction == ASCENDING) {
			for (int i = 1; i <= PuzzleCreator.SIZE; i++) {
				if (legal(col,row,i,grid)) {
					grid[col][row] = i;
					if (backSolver(col - 1,row,direction,grid))				
						return true;				
				}
			}			
		}
		// iterate from 9-1
		if (direction == DESCENDING){
			for (int i = PuzzleCreator.SIZE; i > 0; i--) {
				if (legal(col,row,i,grid)) {
					grid[col][row] = i;
					if (backSolver(col - 1,row,direction,grid))				
						return true;				
				}
			}
		}
		grid[col][row] = 0;
		return false;
	}
	
	/**
	 * Solves a Sudoku puzzle.
	 * Recursively solves a sudoku puzzle by iterating through numbers
	 * 1 - 9, in ascending or descending order from top to bottom, left to right.	
	 * @param col The column to start from.
	 * @param row The row to start from.
	 * @param direction Iterate through the numbers in this direction.
	 * @param grid The Sudoku grid.
	 * @return Returns true if there is a complete solution, false otherwise.
	 */
	private static boolean solver (int col, int row, int direction, int[][] grid) {		
		if (col == PuzzleCreator.SIZE) {
			col = 0;
			row++;
			if (row == PuzzleCreator.SIZE)
				return true;
		}
		// if the current cell has a number
		if (grid[col][row] != 0)			
			return solver(col + 1,row,direction,grid);
		// iterate from 1-9 
		if (direction == ASCENDING) {
			for (int i = 1; i <= PuzzleCreator.SIZE; i++) {
				if (legal(col,row,i,grid)) {
					grid[col][row] = i;
					if (solver(col + 1,row,direction,grid))				
						return true;				
				}
			}			
		}
		// iterate from 9-1
		if (direction == DESCENDING){
			for (int i = PuzzleCreator.SIZE; i > 0; i--) {
				if (legal(col,row,i,grid)) {
					grid[col][row] = i;
					if (solver(col + 1,row,direction,grid))				
						return true;				
				}
			}
		}
		grid[col][row] = 0;
		return false;
	}
	
	/**
	 * Copy one 2D integer array to another of the same size.
	 * @param source The array to make a copy of.
	 * @param destination The copy.
	 */
	public static void copyGrid (int[][]source, int[][]destination) {
		for (int row = 0; row < PuzzleCreator.SIZE; row++) {
			for (int col = 0; col < PuzzleCreator.SIZE; col++) {
				destination[row][col] = source[row][col];
			}
		}
	}
	
	/**
	 * Check if two grids are equal.
	 * @param g1 Grid 1
	 * @param g2 Grid 2
	 * @return Returns true if the two grids are equal, false otherwise.
	 */
	private static boolean gridsEqual (int[][] g1, int[][] g2) {
		boolean equal = true;
		for (int row = 0; row < PuzzleCreator.SIZE; row++) {
			for (int col = 0; col < PuzzleCreator.SIZE; col++) {
				if (g1[row][col] != g2[row][col])
					equal = false;
			}
		}
		return equal;
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
	private static boolean legal(int col, int row, int val,int[][] grid) {
		boolean valid = true;
		if (!rowValid(val,col,grid)) valid = false;
        if (!columnValid(val,row,grid)) valid = false;
        if(!regionValid(val,row,col,grid)) valid = false;
        return valid;     
	}
	/**
	 * Check if the 3x3 region is valid.
	 * @param value
	 * @param row
	 * @param col
	 * @param grid
	 * @return
	 */
	private static boolean regionValid (int value, int row, int col, int[][] grid) {
		boolean valid = true;
		int regionRowOffset = (col / 3) * 3;
        int regionColOffset = (row / 3) * 3;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (value == grid[regionRowOffset + i][regionColOffset + j]) valid = false;
            }
        }
		return valid;
	}
	
	/**
	 * Check if the row is valid.
	 * @param value
	 * @param row
	 * @param grid
	 * @return
	 */
	private static boolean rowValid (int value, int row, int[][] grid) {
		boolean valid = true;
		for (int i = 0; i < PuzzleCreator.SIZE; ++i) {  
            if (value == grid[row][i])
                valid = false;
		}
        return valid;
	}
	/**
	 * Check if the column is valid.
	 * @param value
	 * @param col
	 * @param grid
	 * @return
	 */
	private static boolean columnValid (int value, int col, int[][] grid) {
		//Check the column
		boolean valid = true;
        for (int i = 0; i < PuzzleCreator.SIZE; ++i){ 
            if (value == grid[i][col])
                valid = false;
        }
        return valid;
	}
}
