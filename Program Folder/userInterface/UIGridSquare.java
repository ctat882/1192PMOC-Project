package userInterface;

/**
 * 
 * @author damartinable
 * An individual Sudoku square representation
 *
 */
public class UIGridSquare extends JPanel{
	public UIGridSquare() {
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
