package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class FileTableModel extends AbstractTableModel {

	String columnNames[] = { "Name", "Ext", "Size", "Date" };

	List<CmdFileRow> data = new ArrayList<CmdFileRow>();

	@Override
	public int getColumnCount() {

		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {

		Object value = new Object();
		switch (col) {
		case 0:
			value = data.get(row).getLocation();
			break;
		case 1:
			value = data.get(row).getExtension();
			break;
		case 2:
			value = data.get(row).getFileSize();
			break;
		case 3:
			value = data.get(row).getLastModified();
			break;
		}
		return value;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	public void setData(String path) {
		this.data.clear();

		File location = new File(path);
		File[] fileList = location.listFiles();

		if (path.length() > 3) {
			CmdFileRow first = new CmdFileRow(location, true);
			this.data.add(first);
		}
		// if (first != null) {
		// this.data.add(first);
		// }

		if (fileList == null) {

		} else {
			for (int i = 0; i < fileList.length; i++) {
				File currFile = fileList[i];
				CmdFileRow fileRow = new CmdFileRow(currFile);

				this.data.add(i, fileRow);
			}

			Collections.sort(this.data, CmdFileRow.getNameComparator());
		}

		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int column) {

		return columnNames[column];
	}

	public CmdFileRow getRowAt(int row) {
		CmdFileRow c = this.data.get(row);
		return c;
	}
	// @Override
	// public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	// data.get(rowIndex)[columnIndex] = aValue;
	// }

}
