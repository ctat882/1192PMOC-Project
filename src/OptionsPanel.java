import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class OptionsPanel extends JPanel{
	
	private final UIFrame frame;
	private final int buttonSize = 100;
	
	public OptionsPanel(UIFrame theFrame)
	{	
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.frame = theFrame;
		
		setBorder(BorderFactory.createTitledBorder(" Options "));
		
		JButton newGame = new JButton("New Game");
		newGame.setMaximumSize(new Dimension(buttonSize, 30));
		newGame.setPreferredSize(new Dimension(buttonSize, 30));
		//newGame.setAlignmentX(0.5f);
		newGame.setFocusable(false);
		
		newGame.addActionListener(new
				ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						frame.startNewGame();
					}
				});
		
		JButton reset= new JButton("Reset");
		reset.setMaximumSize(new Dimension(buttonSize, 30));
		reset.setPreferredSize(new Dimension(buttonSize, 30));
		//reset.setAlignmentX(0.5f);
		reset.setFocusable(false);
		reset.addActionListener(new
				ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						frame.getGame().resetCurrentPuzzle(frame.getBoard());
					}
				});
		
		JButton undo = new JButton("Undo");
		undo.setMaximumSize(new Dimension(buttonSize, 30));
		undo.setPreferredSize(new Dimension(buttonSize, 30));
		//undo.setAlignmentX(0.5f);
		undo.setFocusable(false);
		
		JButton hint = new JButton("Hint");
		hint.setMaximumSize(new Dimension(buttonSize, 30));
		hint.setPreferredSize(new Dimension(buttonSize, 30));
		//hint.setAlignmentX(0.5f);
		hint.setFocusable(false);
		hint.addActionListener(new
				ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						frame.getGame().showHint(frame.getBoard()); 
					}
				});
		
		JPanel align = new JPanel();
		//align.setLayout(new BoxLayout(align, BoxLayout.PAGE_AXIS));
		align.setAlignmentX(0);
		align.add(newGame);
		align.add(reset);
		align.add(undo);
		align.add(hint);
		
		add(align);
		
		add(new GameStatsPanel());
	}
}
