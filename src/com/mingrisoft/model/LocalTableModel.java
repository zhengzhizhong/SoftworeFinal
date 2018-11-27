package com.mingrisoft.model;
public class LocalTableModel extends javax.swing.table.DefaultTableModel {
	Class[] types = new Class[] { java.lang.Object.class,
			java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
			, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,  java.lang.String.class};
	boolean[] canEdit = new boolean[] { false, false, false,false,false,false, false, false,false, false, false};
	public LocalTableModel() {
		super(new Object[][] {}, new String[] { "编号", "客户名称","地址", "联系人","联系电话","传真" ,"邮编","银行账号","网址","Emall地址","备注"});
	}
	public Class getColumnClass(int columnIndex) {
		return types[columnIndex];
	}
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit[columnIndex];
	}
}