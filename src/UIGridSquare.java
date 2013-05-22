
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	
	private NumbersPanel numbers;
	
	public UIGridSquare(NumbersPanel numbers) 
	{		
		this.numbers = numbers;
		
		//JTextField field = new JTextField();
		field = new JLabel(" ");
		button = new JButton();
		Dimension boxSize = new Dimension(50, 50);
		
		//Ensure a set size of the cells
		field.setMinimumSize(boxSize);
		field.setPreferredSize(boxSize);
		
		field.setHorizontalAlignment(JLabel.CENTER);
		field.setHorizontalTextPosition(JLabel.CENTER);
		
		button.setPreferredSize(boxSize);
		button.setHorizontalAlignment(JButton.CENTER);
		button.setFocusable(true);
		
		
		this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		this.setBackground(Color.WHITE);
		
		this.setMinimumSize(boxSize);
		this.add(field);
		//this.add(button);
		
		createHoverListener();
		createClickListener();
	}
	
	//When you click a square it changes to blue and increments its value. Should be able to inout its value using
	//the keyboard however I cannot get the keyListener to work.
	private void createClickListener() {
		addMouseListener(
			new MouseAdapter() {
				public void mouseClicked(MouseEvent event) {
					
					button.requestFocusInWindow();
					if(event.getButton() == event.BUTTON3) {
						setValue(String.valueOf(0));
						previousColour = Color.WHITE;
					}else {
						setValue(numbers.getCurrentlySelected());
						previousColour = Color.cyan;
					}
					
					setColor(previousColour);
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
						setColor(Color.GRAY);
					}
				}
			);
		
		addMouseListener(
				new MouseAdapter()
				{
					public void mouseExited(MouseEvent event)
					{
						setColor(previousColour);
					}
				}
			);
	}
	
	
	
	public int getValue()
	{
		return Integer.parseInt(field.getText());
	}
	
	public void setIsProtected(Boolean isProtected) {
		this.isProtected = isProtected; 
	}
	
	public void setValue(String value)
	{	
		if(!isProtected) {
			if (value.equalsIgnoreCase("0"))
			{
				field.setText(" ");
			}
			else
			{
				field.setText(value);
			}
		}
	}
	
	public void setColor(Color colour)
	{
		if(!isProtected) {
			setBackground(colour);
		}
	}
}
