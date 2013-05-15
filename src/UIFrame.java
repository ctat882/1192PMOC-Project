
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author damartinable
 * The UI main display frame
 *
 */
public class UIFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private final int X_PIXELS = 800;
	private final int Y_PIXELS = 600;
	//private final int HORIZONTAL_LENGTH = 9;
	//private final int VERTICAL_LENGTH = 9;
	private SudokuController controller;
	//private UIGridSquare[][] squares; 
	
	public UIFrame(SudokuController controller) {
		super();
		this.controller = controller;
		//this.squares = new UIGridSquare[HORIZONTAL_LENGTH][VERTICAL_LENGTH];
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(X_PIXELS, Y_PIXELS));
		this.setMinimumSize(new Dimension(X_PIXELS, Y_PIXELS));
		this.setLayout(new BorderLayout());
		
		//this.add(createSudokuGrid(), BorderLayout.NORTH);
		this.add(new Board(), BorderLayout.NORTH);
		this.add(createButtonPanel(), BorderLayout.SOUTH);
		
		this.pack();
		this.setVisible(true);
		
		
		
	}

//	private JPanel createSudokuGrid() {
//		JPanel panel = new JPanel();
//		LayoutManager l = new GridBagLayout();
//		GridBagConstraints c = new GridBagConstraints();
//		panel.setLayout(l);
//		
//		for(int i = 0; i < this.HORIZONTAL_LENGTH; i++) {
//			for(int j = 0; j < this.VERTICAL_LENGTH; j++) {
//				UIGridSquare square = new UIGridSquare();
//				squares[i][j] = square;
//				c.gridx = i;
//				c.gridy = j;
//				panel.add(square, c);
//			}
//		}
//		
//		return panel;
//	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JButton reset= new JButton("Reset");
		JButton undo = new JButton("Undo");
		JButton newGame = new JButton("New");
		
		panel.add(reset, BorderLayout.EAST);
		panel.add(undo, BorderLayout.CENTER);
		panel.add(newGame, BorderLayout.WEST);
		
		return panel;
	}
	
}


