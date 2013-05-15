package UI;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author damartinable
 * An individual Sudoku square representation
 *
 */
public class UserInterfaceGridSquare extends JPanel{
	public UserInterfaceGridSquare() {
		super();
		
		JTextField field = new JTextField();
		Dimension boxSize = new Dimension(30, 30);
		
		field.setMinimumSize(boxSize);
		field.setPreferredSize(boxSize);
		this.setMinimumSize(boxSize);
		this.add(field);
	}
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
