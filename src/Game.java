/*
 * This class represents the essential back-end 
 * of the program and will handle any and all
 * calls to processing that the game may need.
 * It will hold the digital state of the game, its
 * solution and the capacity to perform any of the
 * tasks the user may way to enact on the game.
 * The Methods in this class will in general answer
 * to calls from the GUI.
 */
public class Game {
	
	//Example utilisation of Puzzle class
	private Puzzle gameSolution, currentGameState, initialGameState;

	//Default constructor
	public Game()
	{
		
	}
	
	//This function, called eclusivly by
	//the front end, will generate a new
	//sudoku puzzle of a given difficulty and
	//construct the board as required. This process
	//shjould use the functionality of the PuzzleCreator class.
	public void generatePuzzle(Board board, Difficulty difficulty)
	{
		PuzzleCreator creator = new PuzzleCreator();
		
		//Create a new puzzle and update the stored puzzles
		creator.generateNewPuzzle(difficulty);
		gameSolution = creator.retreivePuzzleSolution();
		currentGameState = creator.retreiveInitialPuzzleState();
		initialGameState = creator.retreiveInitialPuzzleState();
		
		//Update the UI element
		for (int i = 0; i < currentGameState.getColCount(); i++)
		{
			for (int j = 0; j < currentGameState.getRowCount(); j++)
			{
				board.set(j, i, currentGameState.get(j, i));
			}
		}
	}
	
	//Called each time the user presses the mouse
	//button over a cell in the GUI. It passes in
	//the cell ID (x, y) and the value the user 
	//currently has selected, updating only the front 
	//and back end puzzle representations and checking
	//if by making this move the puzzle is complete
	public void setCell(Board board, int x, int y, int value)
	{
		board.set(x, y, value);
		currentGameState.set(x, y, value);
		
		if (checkSolution())
		{
			board.puzzleCompleted();
		}
	}
	
	//Given the current state of the board, this function
	//returns a hint to the user by some how graphically
	//altering the board
	public void showHint(Board board)
	{
		
	}
	
	//Returns the current puzzle to the original
	//state it was provided to the user in, erasing
	//all entries that the user has commited to the
	//board and updating the front end appropriatly.
	public void resetCurrentPuzzle(Board board)
	{
		//Revert to the original state
		currentGameState = initialGameState;
		
		//Update the UI element
		for (int i = 0; i < currentGameState.getColCount(); i++)
		{
			for (int j = 0; j < currentGameState.getRowCount(); j++)
			{
				board.set(j, i, currentGameState.get(j, i));
			}
		}
	}
	
	//checks to see if the current state of the board is
	//the same as the solution
	private boolean checkSolution()
	{
		return currentGameState.equals(gameSolution);
	}
}