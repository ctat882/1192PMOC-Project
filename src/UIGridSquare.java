
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
	private int entryValue = 0; 
	private JButton button;
	
	public UIGridSquare() 
	{		
		//JTextField field = new JTextField();
		field = new JLabel(" ");
		button = new JButton();
		Dimension boxSize = new Dimension(30, 30);
		
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
		this.add(button);
		
		createHoverListener();
		createClickListener();
		createKeyListener();
	}
	
	//Adds a keyListener in theory, however I cannot get this event to occur
	private void createKeyListener() {
		addKeyListener( 
			new KeyListener() {

				@Override
				public void keyPressed(KeyEvent e) {
					int keyCode = e.getKeyCode();
					setBackground(Color.green);
				}

				@Override
				public void keyReleased(KeyEvent e) {
					e.consume();
				}

				@Override
				public void keyTyped(KeyEvent e) {
					e.consume();
				}
			}	
		);
	}
	
	
	//When you click a square it changes to blue and increments its value. Should be able to inout its value using
	//the keyboard however I cannot get the keyListener to work.
	private void createClickListener() {
		addMouseListener(
			new MouseAdapter() {
				public void mouseClicked(MouseEvent event) {
					setBackground(Color.blue);
					button.requestFocusInWindow();
					if(event.getButton() == event.BUTTON3) {
						entryValue = 0;
						setValue(String.valueOf(entryValue));
					}else {
						setValue(String.valueOf(entryValue++));						
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
						setBackground(Color.GRAY);
					}
				}
			);
		
		addMouseListener(
				new MouseAdapter()
				{
					public void mouseExited(MouseEvent event)
					{
						setBackground(Color.WHITE);
					}
				}
			);
	}
	
	
	
	public int getValue()
	{
		return Integer.parseInt(field.getText());
	}
	
	public void setValue(String value)
	{
		if (value.equalsIgnoreCase("0"))
		{
			field.setText(" ");
		}
		else
		{
			field.setText(value);
		}
	}
	
	public void setColor(Color colour)
	{
		
	}
}
