
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
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
	
	public UIGridSquare() 
	{		
		//JTextField field = new JTextField();
		field = new JLabel(" ");
		Dimension boxSize = new Dimension(30, 30);
		
		//Ensure a set size of the cells
		field.setMinimumSize(boxSize);
		field.setPreferredSize(boxSize);
		
		field.setHorizontalAlignment(JLabel.CENTER);
		field.setHorizontalTextPosition(JLabel.CENTER);
		
		this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		this.setBackground(Color.WHITE);
		
		this.setMinimumSize(boxSize);
		this.add(field);
		
		createHoverListener();
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
