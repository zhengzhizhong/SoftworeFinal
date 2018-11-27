package com.mingrisoft.model;
public class DepotModel extends javax.swing.table.DefaultTableModel {
	Class[] types = new Class[] { java.lang.Object.class,
			java.lang.String.class, java.lang.String.class};
	boolean[] canEdit = new boolean[] { false, false, false};
	public DepotModel() {
		super(new Object[][] {}, new String[] { "±‡∫≈", "ø‚π‹","√Ë ˆ"});
	}
	public Class getColumnClass(int columnIndex) {
		return types[columnIndex];
	}
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit[columnIndex];
	}
}