import java.util.Random;

/**
 * Remove a set amount of squares from the solution at random locations.
 * Ensuring that a unique solution is maintained. This technique should
 * be used only for the easy difficulty.
 * @param pC The puzzle creator.
 * @param givens The number of squares to remove.
 */
public class DigRandom implements Digger{
	/** The number of unsuccessful attempts to dig a cell */
	private final int NUM_LOOPS = 10;
	
	/** The puzzle creator. */
	private PuzzleCreator pC;
	/** The number of given cells. */
	private int givens;
	/**
	 * Constructor.
	 * @param pC The puzzle creator.
	 * @param givens The number of givens.
	 */
	public DigRandom (PuzzleCreator pC, int givens) {
		this.pC = pC;
		this.givens = givens;
	}
	
	@Override
	public void digSolution() {				
		Random rand = new Random();				//Random number generator
		int row,col;							
		int loopCount = 0;						//the number of times the search has looped
		int removeCount = 0;					//the number of cells removed
		while (removeCount < (81 - givens)) {
			boolean removed = false;
			//keep searching for a valid cell to dig
			while (!removed && loopCount < NUM_LOOPS) {
				//randomize the cell location
				col = rand.nextInt(SIZE);
				row = rand.nextInt(SIZE);
				//if the current cell is not 0
				if (pC.pGrid[row][col] != 0) {
					//copy the puzzle grid to a temporary grid
					UniqueChecker.copyGrid(pC.pGrid,pC.solvedGrid);
					//make the current cell in the temporary grid equal 0
					pC.solvedGrid[row][col] = 0;
					//check that if by removing this cell, the puzzle maintains
					//a unique solution
					if (UniqueChecker.hasUniqueSolution(pC)) {
						//if it does, set the current cell on the puzzle grid to 0
						pC.pGrid[col][row] = 0;
						//TODO: debugging
						System.out.println("removed " + (removeCount + 1) + " out of " + (81 - givens));
						removeCount++;
						removed = true;
					}
					else loopCount++;
				}
			}
			// If the digger gets stuck, reset puzzle
			if (loopCount == NUM_LOOPS) {
				System.out.println("Reset");
				// reset the pC.pGrid
				UniqueChecker.copyGrid(pC.solutionGrid,pC.pGrid);
				// reset the loop count
				loopCount = 0;
				// reset the remove count
				removeCount = 0;
			}
		}
	}
}
