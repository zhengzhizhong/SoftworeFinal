package com.mingrisoft.model;
public class WareModel extends javax.swing.table.DefaultTableModel {
	Class[] types = new Class[] { java.lang.Object.class,
			java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
			, java.lang.String.class};
	boolean[] canEdit = new boolean[] { false, false, false,false,false,false, false, false,false, false, false};
	public WareModel() {
		super(new Object[][] {}, new String[] { "���", "��Ʒ����","��Ʒ����", "��λ","������","���ۼ�" ,"��Ա��"});
	}
	public Class getColumnClass(int columnIndex) {
		return types[columnIndex];
	}
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit[columnIndex];
	}
}