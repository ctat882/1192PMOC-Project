import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameStatsPanel extends JPanel  {

	private JLabel tilesLeftValue, tilesPlacedValue;
	private final int FONT_SIZE = 18;
	
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
		
		tilesLeft.setAlignmentX(0);
		
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
}
