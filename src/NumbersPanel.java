import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;


public class NumbersPanel extends JPanel implements KeyListener{

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
			btn.setActionCommand("" + (i + 1));
			numbers.add(btn);
			group.add(btn);
		}
		
		add(numbers);
		
		JCheckBox checkBox = new JCheckBox("Thinking", false);
		checkBox.setFocusable(false);
		add(checkBox);
		
		this.addKeyListener(this);
		setFocusable(true);
	}
	
	public String getCurrentlySelected()
	{
		ButtonModel model;
		model = group.getSelection();
		
		if(model != null) {
			return model.getActionCommand();
		}
		
		return "0";
	}

	@Override
	public void keyPressed(KeyEvent arg0) 
	{	
		for (Enumeration<AbstractButton> e = group.getElements(); e.hasMoreElements();)
		{
			AbstractButton b = e.nextElement();
			if (b.getText().equalsIgnoreCase(KeyEvent.getKeyText(arg0.getKeyChar())))
			{
				group.setSelected(b.getModel(), true);
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
