package com.mingrisoft.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.BorderFactory.createTitledBorder;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import com.mingrisoft.archives.InsertProvideFrame;
import com.mingrisoft.archives.InsertWareFrame;
import com.mingrisoft.archives.UpdateProvideFrame;
import com.mingrisoft.archives.UpdateWareFrame;
import com.mingrisoft.bean.Provide;
import com.mingrisoft.bean.Ware;
import com.mingrisoft.dao.FeelDao;
import com.mingrisoft.dao.WareDao;
import com.mingrisoft.model.LocalTableModel;
import com.mingrisoft.model.WareModel;

import java.io.*;

public class WarePanel extends JPanel {
	private JPanel message;
	private JTextField nameTextField;
	private JTable table;	
	WareDao wareDao = new WareDao();
	WareModel wareModel = new WareModel();
	/**
	 * Create the panel.
	 */
	public WarePanel() {
	}
	public JPanel getMessage() {
		this.setBorder(createTitledBorder(null, "��Ʒ��Ϣ",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font(
						"sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
		message = new JPanel();
		message.setBackground(new Color(71,201,223));
		message.setBounds(0, 0, 520, 394);
		message.setLayout(null);
		JLabel nameLabel = new JLabel("��Ʒ����");
		nameLabel.setBounds(70, 34, 54, 15);
		message.add(nameLabel);

		nameTextField = new JTextField();
		nameTextField.setBounds(132, 31, 97, 25);
		message.add(nameTextField);
		nameTextField.setColumns(10);

		JButton findButton = new JButton("����");
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wareModel.setRowCount(0);
				String name = nameTextField.getText();						
				if(name.equals("")){					
					JOptionPane.showMessageDialog(getParent(), "����д��ѯ������",
							"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					return;					
				}
				if(!name.equals("")){
					 List list = wareDao.selectWareByName(name);
					 for(int i = 0;i<list.size();i++){
						 Ware ware = (Ware)list.get(i);						
						 wareModel.addRow(new Object[] { ware.getId(), ware.getWareName(),
									ware.getWarBewrite(),ware.getSpec(),
									ware.getStockPrice(),ware.getRetailPrice(),ware.getAssociatorPrice()
						 });
					 }
				}			
				
			}
		});
		findButton.setBounds(280, 30, 77, 23);
		message.add(findButton);
		JButton insertButton = new JButton("���");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 InsertWareFrame ware = new InsertWareFrame();
			 ware.setVisible(true);
			 }
		});
		insertButton.setBounds(51, 313, 77, 23);
		message.add(insertButton);

		JButton updateButton = new JButton("�޸�");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();			   
				if (row < 0) {
					JOptionPane.showMessageDialog(getParent(), "û��ѡ��Ҫ�޸ĵ����ݣ�",
							"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					File file = new File("file.txt");
					try {
						String column =	wareModel.getValueAt(row, 0).toString();		
						file.createNewFile();
						FileOutputStream out = new FileOutputStream(file);
						out.write((Integer.parseInt(column)));
						out.close();					
						UpdateWareFrame ware = new UpdateWareFrame();
						ware.setVisible(true);
						repaint();
					} catch (Exception ee) {
						ee.printStackTrace();
					}
				}
			}
		});
		updateButton.setBounds(169, 313, 77, 23);
		message.add(updateButton);
		JButton deleteButton = new JButton("ɾ��");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String column =	wareModel.getValueAt(row, 0).toString();
				if (Integer.parseInt(column) < 0) {
					JOptionPane.showMessageDialog(getParent(), "û��ѡ��Ҫ�h�������ݣ�",
							"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					wareDao.deleteWare(Integer.parseInt(column));				
					JOptionPane.showMessageDialog(getParent(), "����ɾ���ɹ���",
							"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					repaint();

				}
			}
		});
		deleteButton.setBounds(285, 313, 77, 23);
		message.add(deleteButton);
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 70, 416, 213);
		message.add(scrollPane_2);
		table = new JTable(wareModel);
		repaint();
		List list = wareDao.selectWare();
		wareModel.setRowCount(0);
		for (int i = 0; i < list.size(); i++) {
			Ware ware = (Ware)list.get(i);						
			 wareModel.addRow(new Object[] { ware.getId(), ware.getWareName(),
						ware.getWarBewrite(), ware.getSpec(),
						ware.getStockPrice(),ware.getRetailPrice(),ware.getAssociatorPrice()
			});
		}
		scrollPane_2.setViewportView(table);
		return message;
	}
}
