import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 * The numbers panel is the southern container in the UI
 * housing all the buttons that are used for number placing 
 * as well as the various methods for switching between
 * the numbers.
 * @author Will
 *
 */
public class NumbersPanel extends JPanel implements KeyListener {

	private ButtonGroup group;
	/**
	 * Creates a number panel with numbers 1 - 9
	 */
	public NumbersPanel()
	{
		super(new GridLayout(2,1));
		
		setBorder(BorderFactory.createTitledBorder(" Numbers "));
		
		JPanel numbers = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
		
		group = new ButtonGroup();
		for (int i = 0; i < 9; i++)
		{
			JToggleButton btn = new JToggleButton("" + (i + 1));
			btn.setFocusable(false);
			btn.setActionCommand("" + (i + 1));
			
			if (i == 0)
				btn.setSelected(true);
			
			numbers.add(btn);
			group.add(btn);
		}
		
		add(numbers);
		
		JCheckBox checkBox = new JCheckBox("Thinking", false);
		checkBox.setFocusable(false);
		add(checkBox);
		
		this.addKeyListener(this);
		createMouseListener();
		setFocusable(true);
	}
	/**
	 * A listener to detect when the scroll wheel is used and cycle through the number panel elements when this occurs
	 */
	private void createMouseListener()
	{
		addMouseWheelListener(
				new MouseWheelListener()
				{
					public void mouseWheelMoved(MouseWheelEvent e)
					{
						int delta = e.getWheelRotation();
						Integer selected = Integer.parseInt(getCurrentlySelected());
						selected = ((selected + delta) % 10);
						if (selected == 0)
						{
							if (delta > 0)
								selected++;
							else
								selected = 9;
						}
					
						for (Enumeration<AbstractButton> i = group.getElements(); i.hasMoreElements();)
						{
							AbstractButton b = i.nextElement();
							if (b.getText().equalsIgnoreCase(selected.toString()))
							{
								group.setSelected(b.getModel(), true);
								break;
							}
						}
					}
				}
			);
	}
	
	/**
	 * Get the action command of the selected button
	 * @return - String of the action command for the selected button
	 */
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
