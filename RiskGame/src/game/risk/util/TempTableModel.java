package game.risk.util;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * a class which stores the information of the neighbors in a table during the
 * attack phase
 * 
 * @author Team
 *
 */
public class TempTableModel extends AbstractTableModel {

	public List<TempGameStatics> list;

	String columnNames[] = { "Territory", "Continent", "Infantries", "Player", "Own" };

	/**
	 * a constructor
	 * 
	 * @param list
	 *            an arrayList of TempTableModel
	 */
	public TempTableModel(List<TempGameStatics> list) {
		this.list = list;
	}

	/**
	 * Method to get the column name
	 * 
	 * @param index
	 *            an integer value
	 */
	public String getColumnName(int index) {
		return columnNames[index];
	}

	@Override
	/**
	 * Method to get the number of rows
	 * 
	 * @return an integer which represents the size of the list
	 */
	public int getRowCount() {
		return list.size();
	}

	@Override
	/**
	 * Method to get the number of the columns
	 * 
	 * @return an integer which represents the length of the name column
	 */
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	/**
	 * A method to get the value at a particular index
	 * 
	 * @param rowIndex
	 *            the number representing the row
	 * @param columnIndex
	 *            the number representing the column
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return list.get(rowIndex).territory.getName();
		} else if (columnIndex == 1) {
			return list.get(rowIndex).territory.getContinent();
		} else if (columnIndex == 2) {
			return list.get(rowIndex).infantries;
		} else if (columnIndex == 3) {
			return list.get(rowIndex).player + 1;
		} else {
			return list.get(rowIndex).isOwn;
		}

	}

}
