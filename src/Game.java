import java.awt.Point;

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
	private UndoSystem undoSystem;
	private HintSystem hintSystem;
	
	//This function, called exclusively by
	//the front end, will generate a new
	//sudoku puzzle of a given difficulty and
	//construct the board as required. This process
	//should use the functionality of the PuzzleCreator class.
	public void generatePuzzle(Board board, Difficulty difficulty)
	{
		PuzzleCreator creator = new PuzzleCreator();
		undoSystem = new UndoSystem(difficulty);
		hintSystem = new HintSystem(difficulty);
		
		//Create a new puzzle and update the stored puzzles
		creator.generateNewPuzzle(difficulty);
		gameSolution = creator.retreivePuzzleSolution();
		currentGameState = creator.retreiveInitialPuzzleState();
		initialGameState = creator.retreiveInitialPuzzleState();
		
		board.unlockBoard();
		board.resetBoard();
		board.setIsActive(true);
		
		//Update the UI element
		for (int i = 0; i < currentGameState.getColCount(); i++)
		{
			for (int j = 0; j < currentGameState.getRowCount(); j++)
			{
				board.set(j, i, currentGameState.get(j, i));
				board.setCellProtected(j, i, currentGameState.get(j, i) == 0 ? false : true);
			}
		}
	}
	
	//Called each time the user presses the mouse
	//button over a cell in the GUI. It passes in
	//the cell ID (x, y) and the value the user 
	//currently has selected, updating only the front 
	//and back end puzzle representations and checking
	//if by making this move the puzzle is complete
	public void setCell(Board board, int x, int y, int value, boolean isUndo)
	{
		if (board.getIsActive())
		{
			if (!isUndo)
				undoSystem.addUndo(new UndoMove(y, x, currentGameState.get(y, x), value));
			
			currentGameState.set(y, x, value);
			
			if (checkSolution())
			{
				//Update the UI element
				for (int i = 0; i < currentGameState.getColCount(); i++)
				{
					for (int j = 0; j < currentGameState.getRowCount(); j++)
					{
						board.setCellProtected(j, i, true);
					}
				}
				board.puzzleCompleted();
			}
		}
	}
	
	public int getHintsRemaining()
	{
		return hintSystem.getHintsRemaining();
	}
	
	//Given the current state of the board, this function
	//returns a hint to the user by somehow graphically
	//altering the board
	public void showHint(Board board)
	{
		if (board.getIsActive())
		{
			Point poi = hintSystem.getHintCell(currentGameState);
			
			if (poi != null)
				board.setHintCell(poi.x, poi.y);
		}
	}
	
	//Returns the current puzzle to the original
	//state it was provided to the user in, erasing
	//all entries that the user has commited to the
	//board and updating the front end appropriatly.
	public void resetCurrentPuzzle(Board board)
	{
		if(board.getIsActive()) {
			//Revert to the original state
			currentGameState = initialGameState;
		
			//Update the UI element
			for (int i = 0; i < currentGameState.getColCount(); i++)
			{
				for (int j = 0; j < currentGameState.getRowCount(); j++)
				{
					board.set(j, i, currentGameState.get(j, i));
					board.resetBoard();
				}
			}	
		}
		else
		{
			board.resetBoard();
		}
	}
	
	//checks to see if the current state of the board is
	//the same as the solution
	private boolean checkSolution()
	{
		return currentGameState.equals(gameSolution);
	}

	public void undoMove(Board board) 
	{
		if (board.getIsActive())
		{
			UndoMove u = undoSystem.getLastUndoMove();
			
			//Perform undo
			if(u != null)
			{
				setCell(board, u.getX(), u.getY(), u.getOld(), true);
				board.set(u.getX(), u.getY(), u.getOld());
			}    
		}
	}
}
