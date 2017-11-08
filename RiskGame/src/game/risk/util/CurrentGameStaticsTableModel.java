
package game.risk.util;

//Table Model to fill data in JTAble (Per user terroritries)

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * @author Team
 * 
 * This class saves the current statics of the player
 * 
 */
public class CurrentGameStaticsTableModel extends AbstractTableModel {

	public List<CurrentGameStatics> list;

	String columnNames[] = { "Territory", "Continent", "Infantries" };

	/**
	 * 
	 * @param list
	 */
	public CurrentGameStaticsTableModel(List<CurrentGameStatics> list) {
		this.list = list;
	}

	/**
	 * @param index
	 */
	public String getColumnName(int index) {
		return columnNames[index];
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	/**
	 * @param rowIndex
	 * @param columnIndex
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
