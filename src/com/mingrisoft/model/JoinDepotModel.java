package com.mingrisoft.model;
public class JoinDepotModel extends javax.swing.table.DefaultTableModel {
	Class[] types = new Class[] { java.lang.Object.class,
			java.lang.String.class, java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class};
	boolean[] canEdit = new boolean[] { false, false, false,false, false, false,false};
	public JoinDepotModel() {
		super(new Object[][] {}, new String[] { "编号", "订单号","仓库编号","货品名称","入库时间","重量","备注"});
	}
	public Class getColumnClass(int columnIndex) {
		return types[columnIndex];
	}
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit[columnIndex];
	}
}