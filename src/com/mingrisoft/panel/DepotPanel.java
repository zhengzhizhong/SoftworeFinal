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

import com.mingrisoft.archives.InsertDepotFrame;
import com.mingrisoft.archives.InsertProvideFrame;
import com.mingrisoft.archives.UpdateDepotFrame;
import com.mingrisoft.archives.UpdateProvideFrame;
import com.mingrisoft.bean.Depot;
import com.mingrisoft.dao.DepotDao;
import com.mingrisoft.dao.FeelDao;
import com.mingrisoft.model.DepotModel;
import com.mingrisoft.model.LocalTableModel;
import java.io.*;

public class DepotPanel extends JPanel {
	private JPanel message;
	private JTextField idTextField;
	private JTable table;
	private JTextField managerTextField;
	DepotDao dao = new DepotDao();
	DepotModel model = new DepotModel();
	/**
	 * Create the panel.
	 */
	public DepotPanel() {

	}

	public JPanel getMessage() {
		this.setBorder(createTitledBorder(null, "�ֿ���Ϣ",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font(
						"sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
		message = new JPanel();
		message.setBackground(new Color(71,201,223));
		message.setBounds(0, 0, 520, 394);
		message.setLayout(null);
		JLabel nameLabel = new JLabel("�ֿ���");
		nameLabel.setBounds(10, 34, 54, 15);
		message.add(nameLabel);

		idTextField = new JTextField();
		idTextField.setBounds(62, 31, 97, 25);
		message.add(idTextField);
		idTextField.setColumns(10);

		JLabel addresLlabel = new JLabel("���");
		addresLlabel.setBounds(169, 34, 38, 15);
		message.add(addresLlabel);

		managerTextField = new JTextField();
		managerTextField.setBounds(204, 31, 119, 25);
		message.add(managerTextField);
		managerTextField.setColumns(10);

		JButton findButton = new JButton("����");
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				String id = idTextField.getText();
				String manager = managerTextField.getText();				
				if((id.equals("")) && (manager.equals(""))){					
					JOptionPane.showMessageDialog(getParent(), "����д��ѯ������",
							"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					return;					
				}
				if((!id.equals(""))&&(manager.equals(""))){
					int idint = 0;
					try{
						idint = Integer.parseInt(id);
					}catch (Exception ee) {
						JOptionPane.showMessageDialog(getParent(), "����ı��Ҫ���������ͣ�",
								"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					 Depot depot = dao.selectDepotByid(idint);
					 model.addRow(new Object[] { depot.getId(), depot.getManage(),
							 depot.getFunctional()});
					 
				}
				if((id.equals(""))&&(!manager.equals(""))){
					List list = dao.selectDepotByManage(manager);
					for(int i =0;i<list.size();i++){
						 Depot depot = (Depot)list.get(i);
						 model.addRow(new Object[] { depot.getId(), depot.getManage(),
									depot.getFunctional()});
					}
				}
				if((!id.equals("")) && (!manager.equals(""))){
					int idint = 0;
					try{
						idint = Integer.parseInt(id);
					}catch (Exception ee) {
						JOptionPane.showMessageDialog(getParent(), "����ı��Ҫ���������ͣ�",
								"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					List list = dao.selectDepotByManageAndId(idint,manager);
					for(int i =0;i<list.size();i++){
					Depot depot = (Depot)list.get(i);
					 model.addRow(new Object[] { depot.getId(), depot.getManage(),
								depot.getFunctional()
						});
					}
				}
			}
		});
		findButton.setBounds(333, 30, 77, 23);
		message.add(findButton);
		JButton insertButton = new JButton("���");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertDepotFrame depot = new InsertDepotFrame();
				depot.setVisible(true);
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
						 String column =	model.getValueAt(row, 0).toString();			
						file.createNewFile();
						FileOutputStream out = new FileOutputStream(file);
						out.write((Integer.parseInt(column)));
						out.close();					
						UpdateDepotFrame update = new UpdateDepotFrame();
						update.setVisible(true);
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
				if (row < 0) {
					JOptionPane.showMessageDialog(getParent(), "û��ѡ��Ҫ�h�������ݣ�",
							"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					String column =	model.getValueAt(row, 0).toString();
					dao.deleteDepot(Integer.parseInt(column));
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

		table = new JTable(model);
		repaint();
		List list = dao.selectDepot();
		model.setRowCount(0);
		for (int i = 0; i < list.size(); i++) {
			Depot depot = (Depot)list.get(i);
			model.addRow(new Object[] { depot.getId(), depot.getManage(),
					depot.getFunctional()});
		}
		scrollPane_2.setViewportView(table);
		return message;

	}

}
