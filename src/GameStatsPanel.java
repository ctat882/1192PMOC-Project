import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameStatsPanel extends JPanel  {

	public GameStatsPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createTitledBorder("Stats"));
		
		JLabel tilesLeft = new JLabel("Tiles Left:");
		add(tilesLeft);
		JLabel tilesLeft1 = new JLabel("Tiles Left:");
		add(tilesLeft1);
		JLabel tilesLeft2 = new JLabel("Tiles Left:");
		add(tilesLeft2);
		JLabel tilesLeft3 = new JLabel("Tiles Left:");
		add(tilesLeft3);
		JLabel tilesLeft4 = new JLabel("Tiles Left:");
		add(tilesLeft4);
	}
}
