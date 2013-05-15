package UI;

/**
 * 
 * @author damartinable
 * Tester class for the UI
 *
 */
public class UserInterfaceTester {
		public static void main (String args[]) {
			SudokuController controller = new SudokuController();
			UserInterfaceFrame frame =	new UserInterfaceFrame(controller);
			
		}
}
