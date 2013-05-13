
public class CreatePuzzleTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PuzzleCreator pC = new PuzzleCreator ();
		pC.generateNewPuzzle(Difficulty.EASY);
		
		Puzzle puzz = pC.retreivePuzzleSolution();
	}

}
