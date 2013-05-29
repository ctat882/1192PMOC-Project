
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author damartinable
 * An individual Sudoku square representation
 *
 */
public class UIGridSquare extends JPanel{
	
	private JLabel field;
	private JButton button;
	private Color previousColour = Color.white;
	
	private boolean isProtected = false;
	private boolean isHint = false;
	
	private final String HINT_MARKER = "!";
	private final int CELL_SIZE = 48;
	private final Board board;
	private final Point position;
	
	private NumbersPanel numbers;
	private GameStatsPanel stats;
	
	public UIGridSquare(Board board, NumbersPanel numbers, Point position) 
	{		
		this.numbers = numbers;
		this.board = board;
		this.position = position;
		
		field = new JLabel(" ");
		button = new JButton();
		Dimension boxSize = new Dimension(CELL_SIZE, CELL_SIZE);
		
		//Ensure a set size of the cells
		field.setMinimumSize(boxSize);
		field.setPreferredSize(boxSize);	
		
		button.setPreferredSize(boxSize);
		button.setHorizontalAlignment(JButton.CENTER);
		button.setVerticalAlignment(JButton.CENTER);
		button.setFocusable(true);
		
		field.setHorizontalAlignment(JLabel.CENTER);
		field.setHorizontalTextPosition(JLabel.CENTER);
		field.setVerticalTextPosition(JLabel.CENTER);
		field.setVerticalAlignment(JLabel.CENTER);
//		field.setLayout(new GridBagLayout());
//		field.setVerticalTextPosition(JLabel.CENTER);
		
		Font f = field.getFont();
		Font g = f.deriveFont(f.PLAIN, 30);
		
		field.setFont(g);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		
		
		this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		this.setBackground(Color.WHITE);
		
		this.setMinimumSize(boxSize);
		this.setLayout(new GridBagLayout());
		this.add(field,c);
		
		createHoverListener();
		createClickListener();
		setFocusable(false);
	}
	
	//When you click a square it changes to blue and increments its value. Should be able to inout its value using
	//the keyboard however I cannot get the keyListener to work.
	private void createClickListener() {
		addMouseListener(
			new MouseAdapter() {
				public void mouseReleased(MouseEvent event) {
					
					button.requestFocusInWindow();
					if(event.getButton() == MouseEvent.BUTTON3) {
						setValue(String.valueOf(0));
//						previousColour = Color.WHITE;
//						setColor(previousColour);
					}else {
						if (!numbers.getCurrentlySelected().equalsIgnoreCase("0"))
						{
							setValue(numbers.getCurrentlySelected());
							board.getFrame().getGame().setCell(board, position.x, position.y,
									Integer.parseInt(numbers.getCurrentlySelected()), false);
							previousColour = Color.BLUE;
							setTextColour(previousColour);
//							setColor(previousColour);
						}
					}
					
				}
			}
		);
	}

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
	
	
	
	public int getValue()
	{
		if (field.getText().equalsIgnoreCase(" "))
		{
			return 0;
		}
		else
		{
			return Integer.parseInt(field.getText());
		}
	}
	
	public void setIsProtected(Boolean isProtected) {
		this.isProtected = isProtected; 
	}
	
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
	
	public void setColor(Color colour)
	{
		if(!isProtected) {
			setBackground(colour);
//			this.getComponent(0).setForeground(colour);			
		}
	}
	
	public void setTextColour(Color colour) {
		if (!isProtected) {
			this.getComponent(0).setForeground(colour);
		}
	}
}
