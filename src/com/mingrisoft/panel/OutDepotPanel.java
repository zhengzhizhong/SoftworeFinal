package com.mingrisoft.panel;

import static javax.swing.BorderFactory.createTitledBorder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mingrisoft.archives.InserJoinDepotFrame;
import com.mingrisoft.archives.InserOutDepotFrame;
import com.mingrisoft.archives.UpdateJoinDepotFrame;
import com.mingrisoft.archives.UpdateOutDepotFrame;
import com.mingrisoft.archives.UpdateProvideFrame;
import com.mingrisoft.bean.Depot;
import com.mingrisoft.bean.JoinDepot;
import com.mingrisoft.bean.OutDepot;
import com.mingrisoft.bean.Stock;
import com.mingrisoft.dao.JoinDepotDao;
import com.mingrisoft.dao.OutDepotDao;
import com.mingrisoft.dao.StockDao;
import com.mingrisoft.model.JoinDepotModel;
import com.mingrisoft.model.OutDepotModel;
import com.mingrisoft.model.StockModel;

public class OutDepotPanel extends JPanel {
	private JTextField dateTextField;
	final OutDepotModel model = new OutDepotModel();
	private JTable table_1;
	private OutDepotDao dao = new OutDepotDao();
	JComboBox comboBox ;
	/**
	 * Create the panel.
	 */
	public OutDepotPanel() {
		this.setBorder(createTitledBorder(null, "仓库出库",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font(
                        "sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
		setSize(631, 445);
		setLayout(null);
		this.setBackground(new Color(71,201,223));
		JLabel conditionLabel = new JLabel("仓库：");
		conditionLabel.setBounds(57, 86, 45, 15);
		add(conditionLabel);		
		
		dateTextField = new JTextField();
		dateTextField.setBounds(347, 83, 156, 25);
		add(dateTextField);
		dateTextField.setColumns(10);
		
		JButton findButton = new JButton("搜索");
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				String oid = comboBox.getSelectedItem().toString();
				String joinDate = dateTextField.getText();
				if(oid.equals("")&&(joinDate.equals(""))){
					JOptionPane.showMessageDialog(getParent(), "没有填写查询条件！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(joinDate.equals("")){
					List  list = dao.selectOutDepotByDid(Integer.parseInt(oid));
					for(int i = 0;i<list.size();i++){
						OutDepot outDepot = (OutDepot)list.get(i);
						String dRemark = outDepot.getRemark();
						if(dRemark.length()>4){
							dRemark = dRemark.substring(0, 4)+"...";
						}
						model.addRow(new Object[] { outDepot.getId(),outDepot.getDid(),outDepot.getwName(),outDepot.getOutDate(),outDepot.getWight(),dRemark});
					}
				}
				else{
					List list = dao.selectOutDepotByTime(joinDate,Integer.parseInt(oid));
					for(int i = 0;i<list.size();i++){
						OutDepot outDepot = (OutDepot)list.get(i);
						String dRemark = outDepot.getRemark();
						if(dRemark.length()>4){
							dRemark = dRemark.substring(0, 4)+"...";
						}
						model.addRow(new Object[] { outDepot.getId(),outDepot.getDid(),outDepot.getwName(),outDepot.getOutDate(),outDepot.getWight(),dRemark});
					}
				}
				
			}
		});
		findButton.setBounds(513, 82, 93, 23);
		add(findButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 129, 611, 208);
		add(scrollPane);		
		
		
		table_1 = new JTable(model);	
		List list = dao.selectOutDepot();
		for (int i = 0; i < list.size(); i++) {
			OutDepot depot = (OutDepot)list.get(i);
			String dRemark = depot.getRemark();
			if(dRemark.length()>4){
				dRemark = dRemark.substring(0, 4)+"...";
			}
			model.addRow(new Object[] { depot.getId(),depot.getDid(),depot.getwName(),depot.getOutDate(),depot.getWight(),dRemark});
		}
		scrollPane.setViewportView(table_1);
		JButton insertButton = new JButton("添加");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserOutDepotFrame frame = new InserOutDepotFrame();
				frame.setVisible(true);
			}
		});
		insertButton.setBounds(180, 347, 66, 23);
		add(insertButton);		
		JButton updateButton = new JButton("修改");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_1.getSelectedRow();  				
				if (row < 0) {
					JOptionPane.showMessageDialog(getParent(), "没有选择要修改的数据！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					File file = new File("file.txt");
					try {
						String column = model.getValueAt(row, 0).toString();			
						file.createNewFile();
						FileOutputStream out = new FileOutputStream(file);
						out.write((Integer.parseInt(column)));
						out.close();					
						UpdateOutDepotFrame frame = new UpdateOutDepotFrame();
						frame.setVisible(true);
						repaint();
					} catch (Exception ee) {
						ee.printStackTrace();
					}
				}
			}
		});			
		updateButton.setBounds(282, 347, 66, 23);
		add(updateButton);
		
		JButton deleteButton = new JButton("删除");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_1.getSelectedRow();			
				if (row < 0) {
					JOptionPane.showMessageDialog(getParent(), "没有选择要刪除的数据！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					String column =	model.getValueAt(row, 0).toString();
					dao.deleteOutDepot(Integer.parseInt(column));
					JOptionPane.showMessageDialog(getParent(), "数据删除成功！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					repaint();
				}
			}
		});
		deleteButton.setBounds(386, 347, 66, 23);
		add(deleteButton);
		
		JLabel dateLabel = new JLabel("出库时间：");
		dateLabel.setBounds(282, 86, 66, 15);
		add(dateLabel);
		JoinDepotDao dao = new	JoinDepotDao(); 
		List lists = dao.selectDepotId();
		Integer [] orderId = new Integer[lists.size()+1];
	
		for(int i = 0;i<lists.size();i++){
			orderId[i ]=  (Integer)lists.get(i);
		}
		comboBox = new JComboBox(orderId);		
		comboBox.setBounds(110, 83, 162, 21);
		add(comboBox);
	}	
}
