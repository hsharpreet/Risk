package game.risk.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TempTableModel extends AbstractTableModel {

	public List<TempGameStatics> list;

	String columnNames[] = { "Territory", "Continent", "Infantries", "Player", "Own" };

	public TempTableModel(List<TempGameStatics> list) {
		this.list = list;
	}

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
