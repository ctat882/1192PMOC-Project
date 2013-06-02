
/**
 * 
 * @author Dave
 * An object to represent a move that has performed including its coordinates and previous value
 * so that the move can be undone
 */
public class UndoMove {
	private int x;
	private int y;
	private int oldValue;
	private int newValue;

	/**
	 * Creates a new undo move
	 * @param x - x coordinate of the changed cell
	 * @param y - y coordinate of the changed cell 
	 * @param oldValue - existing value of the cell
	 * @param newValue - the new value of the cell (about to be set)
	 */
	public UndoMove(int x, int y, int oldValue, int newValue) {
		this.x = x;
		this.y = y;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	/**
	 * Returns the value of the cell that this objects represents' previous value
	 * @return this moves old value
	 */
	public int getOld() {
		return this.oldValue;
	}
	/**
	 * Returns this moves current value
	 * @return this moves current value
	 */
	public int getNew() {
		return this.newValue;
	}
	/**
	 * Returns this moves x coordinate
	 * @return x coordinate
	 */
	public int getX() {
		return this.x;
	}
	/**
	 * Returns this moves y coordinate
	 * @return y coordinate
	 */
	public int getY() {
		return this.y;
	}
}