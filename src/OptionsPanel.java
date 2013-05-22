import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class OptionsPanel extends JPanel{

	private final UIFrame frame;
	
	public OptionsPanel(UIFrame theFrame)
	{	
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.frame = theFrame;
		
		JButton newGame = new JButton("New Game");
		newGame.setMaximumSize(new Dimension(100, 30));
		newGame.setPreferredSize(new Dimension(100, 30));
		newGame.setFocusable(false);
		
		newGame.addActionListener(new
				ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						frame.startNewGame();
					}
				});
		
		this.add(newGame);
		
		JButton reset= new JButton("Reset");
		reset.setFocusable(false);
		JButton undo = new JButton("Undo");
		undo.setFocusable(false);
		
		add(reset);
		add(undo);
	}
}
