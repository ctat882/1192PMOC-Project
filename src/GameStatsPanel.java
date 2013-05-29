import java.awt.Dimension;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameStatsPanel extends JPanel  {

	private JLabel tilesLeftValue, tilesPlacedValue, hintsValue, undosAvailable;
	private final int FONT_SIZE = 18;
	
	private Timer timer;
	private int timeValue = 0;
	
	public GameStatsPanel(Board board)
	{
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createTitledBorder("Stats"));
		setAlignmentX(0);
		
		int emptyCells = 0;
		
		for (int i = 0; i < board.getBoardSize().y; i++)
		{
			for (int j = 0; j < board.getBoardSize().x; j++)
			{
				if (board.get(i,  j) == 0)
				{
					emptyCells++;
				}
			}
		}
		
		Font font = new Font("Ariel", Font.PLAIN, FONT_SIZE);
		
		JPanel tilesLeftPanel = new JPanel();
		tilesLeftPanel.setLayout(new BoxLayout(tilesLeftPanel, BoxLayout.LINE_AXIS));
		tilesLeftPanel.setMaximumSize(new Dimension(400, 35));
		JLabel tilesLeft = new JLabel("Tiles Left: ");
		tilesLeftValue = new JLabel(new Integer(emptyCells).toString());
		tilesLeft.setFont(font);
		tilesLeftValue.setFont(font);
		tilesLeftPanel.add(tilesLeft);
		tilesLeftPanel.add(tilesLeftValue);
		add(tilesLeftPanel);
		
		JPanel tilesPlacedPanel = new JPanel();
		tilesPlacedPanel.setLayout(new BoxLayout(tilesPlacedPanel, BoxLayout.LINE_AXIS));
		tilesPlacedPanel.setMaximumSize(new Dimension(400, 35));
		JLabel tilesPlaced = new JLabel("Tiles Placed: ");
		tilesPlacedValue = new JLabel("0");
		tilesPlaced.setFont(font);
		tilesPlacedValue.setFont(font);
		tilesPlacedPanel.add(tilesPlaced);
		tilesPlacedPanel.add(tilesPlacedValue);
		add(tilesPlacedPanel);
		
		JPanel hintsPanel = new JPanel();
		hintsPanel.setLayout(new BoxLayout(hintsPanel, BoxLayout.LINE_AXIS));
		hintsPanel.setMaximumSize(new Dimension(400, 35));
		JLabel hints = new JLabel("Hints Left: ");
		hintsValue = new JLabel(new Integer(board.getFrame().getGame().getHintsRemaining()).toString());
		hints.setFont(font);
		hintsValue.setFont(font);
		hintsPanel.add(hints);
		hintsPanel.add(hintsValue);
		add(hintsPanel);
		
		JPanel undoPanel = new JPanel();
		undoPanel.setLayout(new BoxLayout(undoPanel, BoxLayout.LINE_AXIS));
		undoPanel.setMaximumSize(new Dimension(400, 35));
		JLabel undos = new JLabel("Undos available: ");
		String maxAvailable = new Integer(board.getFrame().getGame().getUndoSystem().getMaxUndos()).toString();
		undosAvailable = new JLabel(new Integer(board.getFrame().getGame().getUndoSystem().getSize()).toString() + "/" + maxAvailable);
		undos.setFont(font);
		undosAvailable.setFont(font);
		undoPanel.add(undos);
		undoPanel.add(undosAvailable);
		add(undoPanel);
		
		JPanel timerPanel = new JPanel();
		timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.LINE_AXIS));
		timerPanel.setMaximumSize(new Dimension(400, 35));		
		JLabel timeLabel = new JLabel("Time Played: ");
		final JLabel time = new JLabel();
		timeLabel.setFont(font);
		time.setFont(font);
		timerPanel.add(timeLabel);
		timerPanel.add(time);
		add(timerPanel);
		
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){
			public void run() {
				time.setText(intToTime(timeValue++));
			}
		}
		, 1000, 1000);
	}
	
	private String intToTime(int value)
	{
		long longVal = value;
	    int hours = (int) longVal / 3600;
	    int remainder = (int) longVal - hours * 3600;
	    int mins = remainder / 60;
	    remainder = remainder - mins * 60;
	    int secs = remainder;

	    String hoursString = hours < 10 ? "0" + new Integer(hours).toString() : new Integer(hours).toString();
	    String minsString = mins < 10 ? "0" + new Integer(mins).toString() : new Integer(mins).toString();
	    String secsString = secs < 10 ? "0" + new Integer(secs).toString() : new Integer(secs).toString();
	    
	    return new String(hoursString + ":" + minsString + ":" + secsString);
	}
	
	public void stopTimer()
	{
		timer.cancel();
	}
	
	public void update(Board board)
	{
		int emptyCells = 0;
		int filledCells = 0;
		
		for (int i = 0; i < board.getBoardSize().y; i++)
		{
			for (int j = 0; j < board.getBoardSize().x; j++)
			{
				if (board.get(i, j) == 0)
				{
					emptyCells++;
				}
				else
				{
					if (!board.getCellProtected(i, j))
					{
						filledCells++;
					}
				}
			}
		}
		
		setTilesLeftValue(emptyCells);
		setTilesPlacedValue(filledCells);
		hintsValue.setText(new Integer(board.getFrame().getGame().getHintsRemaining()).toString());
		String maxAvailableUndos = new Integer(board.getFrame().getGame().getUndoSystem().getMaxUndos()).toString();
		String availableUndos = new Integer(board.getFrame().getGame().getUndoSystem().getSize()).toString(); 
		undosAvailable.setText(availableUndos + "/" + maxAvailableUndos);
	}
	
	public int getTilesLeftValue()
	{
		return Integer.parseInt(tilesLeftValue.getText());
	}
	
	public void setTilesLeftValue(int value)
	{
		tilesLeftValue.setText(new Integer(value).toString());
	}
	
	public int getTilesPlacedValue()
	{
		return Integer.parseInt(tilesPlacedValue.getText());
	}
	
	public void setTilesPlacedValue(int value)
	{
		tilesPlacedValue.setText(new Integer(value).toString());
	}
	
	public int getUndosLeftValue() {
		return Integer.parseInt(undosAvailable.getText());
	}
	
	public void setUndosLeftValue(int value) {
		undosAvailable.setText(new Integer(value).toString());
	}
}
