import java.util.ArrayList;

/**
 * A undo system object, contains a list of previous moves. The number of previous moves kept depends upon
 * difficulty
 * @author Dave
 *
 */

public class UndoSystem {
	private final int MAX_UNDOS = 5;
	private int numberUndos;
	private ArrayList<UndoMove> list = new ArrayList<UndoMove>();

	//Sets the maximum number of moves based upon difficulty
	public UndoSystem(Difficulty difficulty) {
		numberUndos = MAX_UNDOS;
	}
	/**
	 * Adds an undo move, if the undoMoves list's size has reached its specified limit
	 * the least recent UndoMove is removed from the list
	 * @param u - move to be added
	 */
	public void addUndo(UndoMove u) {
		list.add(u);
		if(list.size() > numberUndos) {
			list.remove(0);
		}
	}
	/**
	 * Removes the most recent return from the undoList. Will return null if the list is empty
	 * @return - the most recent UndoMove in the UndoList, once empty returns null
	 */
	public UndoMove getLastUndoMove() {
		UndoMove u = null;
		if(list.size() != 0) {
			u = list.remove(list.size() - 1);
		}
		return u;
	}
	public int getSize() {
		return list.size();
	}
	
	public int getMaxUndos() {
		return this.numberUndos;
	}
}