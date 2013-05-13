/*
 * Used to create unique puzzles to varying
 * difficulties.
 */
public class PuzzleCreator {
	private final int SIZE = 9;
	private final int[][] SOLUTION_1 = {{4,9,2,5,3,8,1,6,7},
										{1,5,7,9,6,2,3,4,8},
										{3,6,8,4,1,7,2,5,9},
										{5,8,6,3,2,4,9,7,1},
										{9,3,1,7,8,6,4,2,5},
										{7,2,4,1,5,9,6,8,3},
										{8,4,3,2,9,5,7,1,6},
										{2,1,5,6,7,3,8,9,4},
										{6,7,9,8,4,1,5,3,2}};
	private Puzzle puzzle;
	//Default constructor
	public PuzzleCreator()
	{
		puzzle = new Puzzle();
	}
	
	//A standalone function that causes the class to
	//create a new puzzle based off the given difficulty.
	//This function does NOT return a puzzle, only replaces
	//the current puzzles stored in this class
	public void generateNewPuzzle(Difficulty difficulty)
	{
		int[][] newGame = SOLUTION_1;
		newGame = transformGrid(newGame);
		//TODO: this if statement is just for debugging
		if (!checkColumns(newGame)) {
			System.out.println("Column error");
		}
		else {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					puzzle.set(i, j, newGame[i][j]);
				}
			}
			printPuzzle();
		}
	}
	
	private int[][] transformGrid (int[][] solution) {
		
		swapDigits(solution,1,9);
		return solution;
	}
	/**
	 * Check that all columns have unique values
	 * @return
	 */
	private boolean checkColumns (int[][] grid) {
		boolean allValid = true;
		for (int col = 0; col < SIZE; col++) {
			boolean[] valid = new boolean[SIZE];
			for (int row = 0; row < SIZE; row++) {
				if (!valid[grid[col][row] - 1]) {
					valid[grid[col][row] - 1] = true;
				}
				else {
					allValid = false;
				}
			}
		}		
		return allValid;
	}
	/**
	 * Mutual exchange of two digits.
	 * @param numA
	 * @param numB
	 */
	private void swapDigits (int[][] grid, int numA, int numB) {
		int[][] boxIndex = {{0,0},{3,0},{6,0},
							{0,3},{3,3},{6,3},
							{0,6},{3,6},{6,6}};
		for (int row = 0; row < 7; row += 3) {
			for (int col = 0; col < 7; col += 3) {
				for (int x = 0; x < 3; x++) {
					for (int y = 0; y < 3; y++) {
						if (grid[col + x][row + y] == numA) {
							grid[col + x][row + y] = numB;
						}
						else if (grid[col + x][row + y] == numB) {
							grid[col + x][row + y] = numA;
						}
					}
				}
			}
			
		}
	}
	//TODO THIS IS FOR DEBUGGING PURPOSES ONLY
	private void printPuzzle () {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print("" + puzzle.get(j, i) + " ");
			}
			System.out.print("\n");
		}
	}
	//Gets the LAST puzzle to be created, returning the inital 
	//of the puzzle with blank cells fill with zero's
	public Puzzle retreiveInitialPuzzleState()
	{
		return null;
	}
	
	//Gest the LAST puzzle to be created, returning the entire
	//solution.
	public Puzzle retreivePuzzleSolution()
	{
		
		return puzzle;
	}
}
