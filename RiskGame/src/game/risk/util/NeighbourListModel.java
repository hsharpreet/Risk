package game.risk.util;

import java.util.List;
import javax.swing.AbstractListModel;

/**
 * This class is a list model which is used to make the displaying list dynamic
 * 
 */
public class NeighbourListModel extends AbstractListModel {
	public List<String> neighbours;

	/**
	 * a constructor to initialize the neighbor attribute
	 * 
	 * @param neighbours
	 *            an arrayList of string type containing name of the neighbors
	 */
	public NeighbourListModel(List<String> neighbours) {
		this.neighbours = neighbours;
	}

	/**
	 * Method to get the number of neighbors
	 */
	@Override
	public int getSize() {
		return neighbours.size();
	}

	/**
	 * Method to get the index of the neighbors
	 */
	@Override
	public Object getElementAt(int index) {
		return neighbours.get(index);
	}

}
