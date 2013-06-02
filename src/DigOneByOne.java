/** 
 * Removes a set amount of squares, traversing left to right, top to bottom.
 * In the event that the search for the next cell to be dug hangs, a fail-safe
 * is used to create an entirely different puzzle.
 * To be used for expert difficulty.
 *
 */
public class DigOneByOne implements Digger {
	/** When the fail-safe should commence. */
	private final int RESET_LIMIT = 2;
	/** The puzzle creator. */
	private PuzzleCreator pC;
	/** The number of given cells. */
	private int givens;
	/**
	 * Constructor.
	 * @param pC The puzzle creator.
	 * @param givens The number of givens.
	 */
	public DigOneByOne (PuzzleCreator pC, int givens) {
		this.pC = pC;
		this.givens = givens;
	}
	
	@Override
	public void digSolution() {
		int removeCount = 0;			// keep track of the number of cells removed
		int startCol = 0;				// the column to start traversal from	
		int startRow = 0;				// the row to start traversal from
		int loop = 0;					// the number of times the search has looped
		int resetCount = 0;				// the number of times the search has reset
		boolean completed = false;		// is the puzzle complete
		while (!completed) {
			for (int row = startRow; row < SIZE; row++) {
				for (int col = startCol; col < SIZE; col++) {
					//if cell not 0, and still more cells to remove
					if (pC.pGrid[col][row] != 0 && removeCount < (81 - givens)) {
						//copy the puzzle grid to a temporary grid
						UniqueChecker.copyGrid(pC.pGrid,pC.solvedGrid);
						//make the current cell in the temporary grid equal 0
						pC.solvedGrid[col][row] = 0;
						//check that if by removing this cell, the puzzle maintains
						//a unique solution
						if (UniqueChecker.hasUniqueSolution(pC)) {
							//if it does, set the current cell on the puzzle grid to 0
							pC.pGrid[col][row] = 0;
							//increase the remove count
							removeCount++;
							//TODO: debugging
							System.out.println("removed " + removeCount + "out of " + (81 - givens));
						}
						//move the column location back to the far left
						startCol = 0;
					}
				}
			}
			//the end of the grid has been reached
			loop++;
			//if the required number of cells have been removed,
			//then the puzzle is complete
			if (removeCount == (81 - givens)) {
				completed = true;
			}
			//if not, restart the search by shifting the start column 
			//by one
			else {
				resetCount++;
				System.out.println("Reset");
				//if the search keeps "hanging", implement the 
				//fail-safe and get a new solution
				if (resetCount == RESET_LIMIT) {
					System.out.println("Reset Limit Reached");
					pC.createSolution();
					pC.copySolution();
					resetCount = 0;
					loop = 0;
				}
				//re-initialise the grids and starting location
				removeCount = 0;
				UniqueChecker.copyGrid(pC.solutionGrid,pC.pGrid);
				if (startCol < SIZE){					
					startCol = loop % SIZE;
					System.out.println("col " + startCol);
				}				
				else  {
					startCol = 0;
					if(startRow < SIZE) startRow++;
					System.out.println("row " + startRow);
				}
			}
		}		
	}

}
