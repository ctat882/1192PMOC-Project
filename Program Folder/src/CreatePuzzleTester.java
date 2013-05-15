
public class CreatePuzzleTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PuzzleCreator pC = new PuzzleCreator ();
		pC.generateNewPuzzle(Difficulty.EXPERT);

		System.out.println("SOLUTION");
		System.out.println("");
		pC.printPuzzle(pC.retreivePuzzleSolution());

		System.out.println("PUZZLE");
		System.out.println("");
		pC.printPuzzle(pC.retreiveInitialPuzzleState());
		Puzzle puzz = pC.retreivePuzzleSolution();
	}

}
