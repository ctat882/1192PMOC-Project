import java.util.Random;

/**
 * Removes a set amount of squares, skipping one square as it iterates.
 * To be used on Medium - Hard difficulty.
 *
 */
public class DigJumpOne implements Digger {
	
	/** The puzzle creator. */
	private PuzzleCreator pC;
	/** The number of given cells. */
	private int givens;
	/**
	 * Constructor.
	 * @param pC The puzzle creator.
	 * @param givens The number of givens.
	 */
	public DigJumpOne (PuzzleCreator pC, int givens) {
		this.pC = pC;
		this.givens = givens;
	}
	
	@Override
	public void digSolution() {
		Random rand = new Random();
		int startCol = 0;
		int startRow = 0;
		int removeCount = 0;
		while (removeCount < (81 - givens)) {
			for (int row = startRow; row < SIZE; row++) {
				for (int col = startCol; col < SIZE; col+= 2) {
					if(col < SIZE) {
						if (pC.puzzleGrid[col][row] != 0 && removeCount < (81-givens)) {
							UniqueChecker.copyGrid(pC.puzzleGrid,pC.testGridOne);
							pC.testGridOne[col][row] = 0;
							if (UniqueChecker.hasUniqueSolution(pC)) {
								pC.puzzleGrid[col][row] = 0;
								removeCount++;
								//TODO debugging
								System.out.println("removed " + removeCount + "out of " + (81 - givens));
							}
						}
					}
				}
				startCol = rand.nextInt(2);
			}			
			startCol = rand.nextInt(SIZE);
			startRow = rand.nextInt(SIZE);
		}
	}

}
