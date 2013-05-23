/*
 * This class represents the essential front-end of
 * the sukodu game system, housing the UI and setting up
 * the action-listeners so that the game will run as intended.
 * All interaction between the program and the user will
 * happen through this class.
 */
public class SudokuGame {

	/**
	 * Main entry point into the program
	 * @param args
	 */
	public static void main(String[] args) {
		
		SudokuGame game = new SudokuGame();
		game.run();
	}
	
	//Construct any data structures that the class needs
	//and initialise the class
	public SudokuGame()
	{
		
	}
	
	//Used to put together the entire GUI of the
	//program. This method will have calls to other 
	//outsourced UI elements but will house the main
	//JFrame that each UI elements sits in.
	//Some possible class containers may include:
	//-the number select panel
	//-the options side panel
	//-possibly a current statistics panel
	//-a Start Game popup frame
	//-a How to Play/ rules popup frame
	//etc...
	public void run()
	{
		UIFrame frame =	new UIFrame();
	}
}
