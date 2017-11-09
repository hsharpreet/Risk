
package game.risk.model;

//Table Model to fill data in JTAble (Per user terroritries)

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * This class saves the current statics of the player
 * 
 * @author Team
 * 
 */
public class CurrentGameStaticsTableModel extends AbstractTableModel {

	public List<CurrentGameStatics> list;

	String columnNames[] = { "Territory", "Continent", "Infantries" };

	/**
	 * a constructor that initializes the arrayList
	 * 
	 * @param list
	 *            the arrayList
	 */
	public CurrentGameStaticsTableModel(List<CurrentGameStatics> list) {
		this.list = list;
	}

	/**
	 * Method to get the columnName
	 * 
	 * @param index
	 *            an integer variable
	 */
	public String getColumnName(int index) {
		return columnNames[index];
	}

	/**
	 * Method to get the row count
	 * 
	 * @return the size of the list
	 */
	@Override
	public int getRowCount() {
		return list.size();
	}

	/**
	 * Method to get the column count
	 * 
	 * @return the length of the column name
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	/**
	 * Method to get the value of the territory continent and infantries at the
	 * current index
	 * 
	 * @param rowIndex
	 *            the row index
	 * @param columnIndex
	 *            the column index
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return list.get(rowIndex).territory.getName();
		} else if (columnIndex == 1) {
			return list.get(rowIndex).territory.getContinent();
		} else {
			return list.get(rowIndex).infantries;
		}
	}

}
