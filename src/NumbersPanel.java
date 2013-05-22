import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;


public class NumbersPanel extends JPanel{

	private ButtonGroup group;
	
	public NumbersPanel()
	{
		super(new GridLayout(2,1));
		
		setBorder(BorderFactory.createTitledBorder(" Numbers "));
		
		JPanel numbers = new JPanel(new FlowLayout(FlowLayout.LEADING));
		
		group = new ButtonGroup();
		for (int i = 0; i < 9; i++)
		{
			JToggleButton btn = new JToggleButton("" + (i + 1));
			btn.setFocusable(false);
			numbers.add(btn);
			group.add(btn);
		}
		
		add(numbers);
		
		JCheckBox checkBox = new JCheckBox("Thinking", false);
		checkBox.setFocusable(false);
		add(checkBox);
	}
	
	public String getCurrentlySelected()
	{
		return "1";
	}
}
