import java.util.Random;

/**
 * Used to create unique puzzles to varying
 * difficulties.
 */
public class PuzzleCreator {
	/* ###################
	   ##	CONSTANTS   ##
	   ################### */
	private final int SIZE = 9;			//The magic number of Sudoku
	private final int BLOCK = 3;		//The number of blocks	
	private final int NUM_GAMES = 2;	//The number of defined terminal solutions
	private final int MAX_SWAPS = 5;	
	private final int RAND_COLS = 2;	
	
	/** Easy difficulty base number of givens.*/
	private final int EASY_BASE = 36;
	/** Medium difficulty base number of givens.*/
	private final int MEDIUM_BASE = 32;
	/** Hard difficulty base number of givens.*/
	private final int HARD_BASE = 28;
	/** Expert difficulty base number of givens.*/
	private final int EXPERT_BASE = 24;
	
	/* ###################
	   ##	SOLUTIONS   ##
	   ################### */
	private final int[][] SOLUTION_1 = {{4,9,2,5,3,8,1,6,7},
										{1,5,7,9,6,2,3,4,8},
										{3,6,8,4,1,7,2,5,9},
										{5,8,6,3,2,4,9,7,1},
										{9,3,1,7,8,6,4,2,5},
										{7,2,4,1,5,9,6,8,3},
										{8,4,3,2,9,5,7,1,6},
										{2,1,5,6,7,3,8,9,4},
										{6,7,9,8,4,1,5,3,2}};
	
	private final int[][] SOLUTION_2 = {{6,8,2,9,4,7,5,1,3},
										{3,1,4,6,2,5,7,9,8},
										{9,7,5,8,3,1,4,6,2},
										{2,5,7,3,8,6,9,4,1},
										{1,4,6,7,9,2,3,8,5},
										{8,9,3,1,5,4,6,2,7},
										{7,6,9,2,1,3,8,5,4},
										{4,2,8,5,7,9,1,3,6},
										{5,3,1,4,6,8,2,7,9}};
	
	/* ###################
	   ##	  FIELDS    ##
	   ################### */
	
	private Puzzle puzzle;
	private Puzzle solution;
	public int[][] pGrid;
	public int[][] solvedGrid;
	public int[][] solutionGrid;
	public int[][] other;
	/**
	 * Default constructor
	 */
	public PuzzleCreator()
	{
		puzzle = new Puzzle();
		solution = new Puzzle();
		pGrid = new int[SIZE][SIZE];
		solutionGrid = new int[SIZE][SIZE];
		solvedGrid = new int[SIZE][SIZE];
		other = new int[SIZE][SIZE];
	}
	
	/* ###################
	   ##	  PUBLIC    ##
	   ################### */
	
	
	/**
	 * Create a solution.
	 * A solution is created by first, selecting at random a number between
	 * [0,NUM_GAMES) which will provide a pre-defined terminal solution.
	 * Then the grid is manipulated using several  "propagation techniques"
	 * with random parameters. The grid is then checked against the three
	 * main valid-game constraints to see if it is a valid solution.
	 */
	public void createSolution () {
		int[][] newGame;
		// Get a solution from a random number MOD 
		// the number of predefined solutions
		Random rand = new Random();
		//random
		int gameSelect = rand.nextInt(NUM_GAMES);
		if (gameSelect == 0) {
			newGame = SOLUTION_1;
		}
		else {
			newGame = SOLUTION_2;
		}
		newGame = transformGrid(newGame);
		//Check if a valid solution
		if (!validGame(newGame)) {
			System.out.println("Error");
		}
		//Valid game, so set the solution
		else {
			for (int i = 0; i < 9; i++) 
				for (int j = 0; j < 9; j++) 
					solution.set(i, j, newGame[i][j]);			
		}
		//Initialise grids for puzzle creation by filling them
		//with the solution
		for (int k = 0; k < 9; k++) {
			for (int j = 0; j < 9; j++) {
				pGrid[k][j] = newGame[k][j];
				solutionGrid[k][j] = newGame[k][j];
			}
		}
	}
	
	/**
	 * A stand-alone function that causes the class to
	 * create a new puzzle based off the given difficulty.
	 * This function does NOT return a puzzle, only replaces
	 * the current puzzles stored in this class	
	 * @param difficulty
	 */
	public void generateNewPuzzle(Difficulty difficulty)
	{
		//create a solution
		createSolution();
		copySolution();
		//get the number of 'givens'to leave based on the 
		//difficulty selected
		int givens = getNumGivens(difficulty);				
		//remove the squares from the puzzle board
		createPuzzle (givens);				
	}
		
	/**
	 * Check that the given grid is a valid Sudoku Game.
	 * This function checks the three basic rules of
	 * Sudoku; that there are no duplicates in the rows,
	 * columns and 3x3 regions.
	 * @param grid
	 * @return
	 */
	public boolean validGame (int[][] grid) {
		boolean allValid = true;
		//Check regions
		if (!validRegions(grid)) {
			allValid = false;
		}
		//Check Columns
		if (!validColumns(grid)) {
			allValid = false;
		}
		//Check Rows
		if (!validRows(grid)) {
			allValid = false;
		}
		return allValid;
	}
	
	//Gets the LAST puzzle to be created, returning the inital 
	//of the puzzle with blank cells fill with zero's
	public Puzzle retreiveInitialPuzzleState()
	{
		return puzzle;
	}
	
	//Gest the LAST puzzle to be created, returning the entire
	//solution.
	public Puzzle retreivePuzzleSolution()
	{		
		return solution;
	}
	
	/**
	 * Copy the solution to the puzzle.
	 */
	public void copySolution () {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				puzzle.set(col, row, solution.get(col, row));
			}
		}
	}
	
	/* ###################
	   ##	 PRIVATE    ##
	   ################### */
	
	
	/**
	 * Dig out the puzzle.
	 * Using the strategy approach, create a puzzle from a
	 * solution based on the level of difficulty chosen
	 * by the user.
	 * @param givens The number of givens.
	 */
	private void createPuzzle (int givens) {
		if (givens < HARD_BASE) 
			digPuzzle (new DigOneByOne(this,givens));
		else if (givens >= HARD_BASE && givens < MEDIUM_BASE)
			digPuzzle (new DigJumpOne(this,givens));
		else if (givens >= MEDIUM_BASE && givens < EASY_BASE)
			digPuzzle (new DigJumpOne(this,givens));
		else 
			digPuzzle (new DigRandom(this,givens));
	}
	
	private void digPuzzle( Digger digger) {
		digger.digSolution();
		setPuzzleGrid(pGrid);
	}
	
	/**
	 * Determine the number of givens the puzzle should have.
	 * This is based on the difficulty level that user has
	 * selected to start a new game.
	 * @param difficulty The difficulty level
	 * @return The number of givens.
	 */
	private int getNumGivens (Difficulty difficulty) {
		int givens = 0;
		Random rand = new Random ();
		if (difficulty.equals(Difficulty.EASY)) givens = rand.nextInt(14) + EASY_BASE;
		else if (difficulty.equals(Difficulty.MEDIUM)) givens = rand.nextInt(4) + MEDIUM_BASE;
		else if (difficulty.equals(Difficulty.HARD)) givens = rand.nextInt(4) + HARD_BASE;
		else if(difficulty.equals(Difficulty.EXPERT)) givens = rand.nextInt(4) + EXPERT_BASE;
		return givens;
	}
	
	
	
	
	private void setPuzzleGrid (int[][]newGame) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				puzzle.set(i, j, newGame[i][j]);

			}
		}
	}
	
	/**
	 * Change the puzzle pattern using the four types of
	 * propagation.
	 * @param grid The Solution to transform.
	 * @return A new random solution.
	 */
	private int[][] transformGrid (int[][] grid) {
		// Create a random number generator
		Random generator = new Random();		
		// Propagation 1: The mutual exchange of two digits.
		int numSwaps = generator.nextInt(MAX_SWAPS);
		for (int i = 0; i <= numSwaps; i++) {
			// randomise the numbers to swap
			swapDigits(grid,generator.nextInt(8) + 1,generator.nextInt(8) + 1);
		}
		// Propagation 2: The mutual exchange of two columns in the same block
		numSwaps = generator.nextInt(3);
		for (int i = 0; i <= numSwaps; i++) {		
			swapColumns(grid,generator.nextInt(RAND_COLS) * 3, 
						generator.nextInt(RAND_COLS),generator.nextInt(RAND_COLS));
		}
		// Propagation 3: The mutual exchange of two blocks of columns
		swapColumnBlocks(grid,generator.nextInt(RAND_COLS)*3,
							generator.nextInt(RAND_COLS) * 3);
		
		return grid;
	}
	
	/**
	 * Check that all 3x3 regions have unique values. 
	 * @param grid
	 * @return
	 */
	private boolean validRegions (int[][] grid) {
		boolean allValid = true;
		for (int row = 0; row < 7; row += 3) {
			for (int col = 0; col < 7; col += 3) {
				boolean[] valid = new boolean[SIZE];
				for (int x = 0; x < 3; x++) {
					for (int y = 0; y < 3; y++) {
						if (!valid[grid[col + x][row + y] - 1]) {
							valid[grid[col + x][row + y] - 1] = true;
						}
						else { 
							allValid = false;
						}
					}
				}
			}			
		}
		return allValid;
	}
	/**
	 * Check that all columns have unique values
	 * @return
	 */
	private boolean validColumns (int[][] grid) {
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
//				}
			}
		}		
		return allValid;
	}
	/**
	 * Check that all rows have unique values.
	 * @param grid
	 * @return
	 */
	private boolean validRows (int[][] grid) {
		boolean allValid = true;
		for (int row = 0; row < SIZE; row++) {
			boolean[] valid = new boolean[SIZE];
			for (int col = 0; col < SIZE; col++) {
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
	 * Mutual exchange of two columns in the same block of columns.
	 * @param grid
	 * @param colBlock Must be in {0, 3, 6}.
	 * @param colA Must be in the range of [0, 2].
	 * @param colB Must be in the range of [0, 2].
	 */
	private void swapColumns (int[][] grid, int colBlock, int colA, int colB) {
		int temp;
		if (colA != colB) {
			for (int row = 0; row < SIZE; row++) {
				temp = grid[colBlock + colA][row];
				grid[colBlock + colA][row] = grid[colBlock + colB][row];
				grid[colBlock + colB][row] = temp;				
			}
		}
	}
	/**
	 * Mutual exchange of two blocks of columns.
	 * @param grid
	 * @param blockA Must be in {0,3,6}.
	 * @param blockB Must be in {0,3,6}.
	 */
	private void swapColumnBlocks (int[][]grid, int blockA, int blockB) {
		if (blockA != blockB) {
			// create a temp array 3x9
			int[][] temp = new int[BLOCK][SIZE];
			// copy blockA into temp
			for (int col = blockA; col < blockA + BLOCK; col++) {
				for (int row = 0; row < SIZE; row++) {
					temp[col % BLOCK][row] = grid[col][row];
				}
			}
			// blockA = blockB
			for (int col = 0; col < BLOCK; col++) {
				for (int row = 0; row < SIZE; row++) {
					grid[col + blockA][row] = grid[col + blockB][row];
				}
			}
			// blockB = temp
			for (int col = 0; col < BLOCK; col++) {
				for (int row = 0; row < SIZE; row++) {
					grid[col + blockB][row] = temp[col][row];
				}
			}
		}		
	}
	
	/**
	 * Mutual exchange of two digits.
	 * @param numA
	 * @param numB
	 */
	private void swapDigits (int[][] grid, int numA, int numB) {
		//boxIndex 
		/*
		boxIndex = {{0,0},{3,0},{6,0},
					{0,3},{3,3},{6,3},
					{0,6},{3,6},{6,6}};
		*/
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
	
	/* ###################
	   ##	  DEBUG     ##
	   ################### */
	
	//TODO: THIS IS FOR DEBUGGING PURPOSES ONLY
	// DELETE BEFORE SUBMISSION
	public void printPuzzle (Puzzle puz) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print("" + puz.get(j, i) + " ");
			}
			System.out.print("\n");
		}
	}
	
	@SuppressWarnings("unused")
	private void printGrid (int[][] grid) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print("" + grid[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
}