
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author damartinable
 * An individual Sudoku square representation.
 */
public class UIGridSquare extends JPanel{
	
	private JLabel field;
	private Color previousColour = Color.white;
	
	private boolean isProtected = false;
	private boolean isHint = false;
	
	private final String HINT_MARKER = "!";
	private final int CELL_SIZE = 48;
	private final Board board;
	private final Point position;
	
	private NumbersPanel numbers;
	private GameStatsPanel stats;
	
	/**
	 * Creates a UIGridSquare for the sudoku game. Each square is a part of a Board, has a NumberPanel for input and has a position
	 * @param board - board this square is to be added to
	 * @param numbers - input NumberPanel
	 * @param position - the squares position
	 */
	public UIGridSquare(Board board, NumbersPanel numbers, Point position) 
	{		
		this.numbers = numbers;
		this.board = board;
		this.position = position;
		
		field = new JLabel(" ");
		Dimension boxSize = new Dimension(CELL_SIZE, CELL_SIZE);
		
		//Ensure a set size of the cells
		field.setMinimumSize(boxSize);
		field.setPreferredSize(boxSize);	
		
		field.setHorizontalAlignment(JLabel.CENTER);
		field.setHorizontalTextPosition(JLabel.CENTER);
		field.setVerticalTextPosition(JLabel.CENTER);
		field.setVerticalAlignment(JLabel.CENTER);
		
		Font f = field.getFont();
		Font g = f.deriveFont(f.PLAIN, 30);
		
		field.setFont(g);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 1;
		c.gridy = 1;
				
		this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		this.setBackground(Color.WHITE);
		
		this.setMinimumSize(boxSize);
		this.setLayout(new GridBagLayout());
		this.add(field,c);
		
		createHoverListener();
		createClickListener();
		setFocusable(false);
	}
	
	//When you click a square it changes to blue and increments its value.
	/**
	 * A listener for the UIGridSquare, sets the value of this square to the selected number in the NumberPanel when left clicked
	 * or clears the UIGridSquare(sets it to 0, which is not displayed by the UI) when right clicked.
	 */
	private void createClickListener() {
		addMouseListener(
			new MouseAdapter() {
				public void mouseReleased(MouseEvent event) 
				{
					if(event.getButton() == MouseEvent.BUTTON3) {
						setValue(String.valueOf(0));
					}else {
						if (!numbers.getCurrentlySelected().equalsIgnoreCase("0"))
						{
							setValue(numbers.getCurrentlySelected());
							board.getFrame().getGame().setCell(board, position.x, position.y,
									Integer.parseInt(numbers.getCurrentlySelected()), false);
							previousColour = Color.BLUE;
							setTextColour(previousColour);
							//stats.update(board);
						}
					}
					
				}
			}
		);
	}
	/**
	 * Hover listener to highlight the square that the mouse is currently hovering over.
	 */
	private void createHoverListener()
	{
		addMouseListener(
				new MouseAdapter()
				{
					public void mouseEntered(MouseEvent event)
					{
						if (isHint)
							setHint(false);
						
						setColor(Color.GRAY);			
					}
				}
			);
		
		addMouseListener(
				new MouseAdapter()
				{
					public void mouseExited(MouseEvent event)
					{
						setColor(Color.WHITE);
					}
				}
			);
	}
	
	/**
	 * Return the text of this JLabel
	 * @return - the text of this JLabel if 1 - 9 or 0 if the field currently contains " " or "!". 
	 */
	public int getValue()
	{
		if (field.getText().equalsIgnoreCase(" ") || field.getText().equalsIgnoreCase("!"))
		{
			return 0;
		}
		else
		{
			return Integer.parseInt(field.getText());
		}
	}
	/**
	 * Sets this square as protected so that it cannot be manipulated by other methods without clearing this Boolean
	 * @param isProtected - A Boolean value, true if this UIGridSquare is to be protected, false otherwise
	 */
	public void setIsProtected(Boolean isProtected) {
		this.isProtected = isProtected; 
	}
	/**
	 * Check this UIGridSquare to see if it is protected
	 * @return - true if protected, false otherwise
	 */
	public boolean getIsProtected()
	{
		return isProtected;
	}
	
	public void setHint(Boolean hint)
	{
		if (hint)
		{
			field.setText(HINT_MARKER);
			setColor(new Color(0, 128, 255));
		}
		else
		{
			if (isHint)
			{
				setValue("0");
				setColor(Color.WHITE);
			}
		}
		
		this.isHint = hint;
	}
	
	/**
	 * Set this UIGridSquares text value and update the board, will only occur if the square is not protected
	 * @param value - value to be set
	 */
	public void setValue(String value)
	{	
		if(!isProtected) 
		{			
			if (value.equalsIgnoreCase("0"))
			{
				field.setText(" ");
			}
			else
			{
				field.setText(value);
			}
			
			stats = board.getFrame().getOptions().getStatsPanel();
			if (stats != null)
				stats.update(board);
		}
	}
	/**
	 * Sets this squares background colour.
	 * @param colour - colour to set this UIGridSquare to
	 */
	public void setColor(Color colour)
	{
		if(!isProtected) {
			setBackground(colour);		
		}
	}
	/**
	 * Sets this UIGridSquares text colour
	 * @param colour - colour to set the text to
	 */
	public void setTextColour(Color colour) {
		if (!isProtected) {
			this.getComponent(0).setForeground(colour);
		}
	}
}
