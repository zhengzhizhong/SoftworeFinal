package com.mingrisoft.model;
public class StockModel extends javax.swing.table.DefaultTableModel {
	Class[] types = new Class[] { java.lang.Object.class,
			java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
			java.lang.String.class, java.lang.String.class, java.lang.String.class};
	boolean[] canEdit = new boolean[] { false, false, false};
	public StockModel() {
		super(new Object[][] {}, new String[] { "编号","订单号", "货品名称","交货日期","进货商","数量","金额",""});
	}
	public Class getColumnClass(int columnIndex) {
		return types[columnIndex];
	}
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit[columnIndex];
	}
}