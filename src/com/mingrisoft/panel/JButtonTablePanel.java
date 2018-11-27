package com.mingrisoft.panel;

import static javax.swing.BorderFactory.createTitledBorder;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mingrisoft.archives.InserStockFrame;
import com.mingrisoft.archives.UpdateDepotFrame;
import com.mingrisoft.archives.UpdateStockFrame;
import com.mingrisoft.bean.Stock;
import com.mingrisoft.dao.StockDao;
import com.mingrisoft.jbutton.ButtonEditor;
import com.mingrisoft.jbutton.ButtonRenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class JButtonTablePanel extends JPanel {
	private JTable table;
	 DefaultTableModel dm = new DefaultTableModel();
	 StockDao dao = new StockDao();
	 private JTextField conditionTextField;
	/**
	 * Create the panel.
	 */
	public JButtonTablePanel() {
		setLayout(null);
		this.setBorder(createTitledBorder(null, "�ɹ�����",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font(
                        "sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 116, 520, 211);
		add(scrollPane);	
		dm.setDataVector(new Object[][]{{"","","","","","","",""}},
	                     new Object[]{"�Ƿ����","���","�ͻ�","������","��������","��Ʒ��","���","����"});	                     
		dm.removeRow(0);
		table = new JTable(dm);
		scrollPane.setViewportView(table);		 
		    List list = dao.selectStock();
		    for(int i = 0;i<list.size();i++){
		    	Stock stock = (Stock)list.get(i);
		    	String oid = stock.getOrderId();
		    	int id = dao.selectJoinStockByOid(oid);	    	
		    	if(id <=0){
		    		 dm.addRow(new Object[]{"���",stock.getId(),stock.getsName(),stock.getOrderId(),stock.getConsignmentDate(),stock.getBaleName(),
		    				 stock.getMoney(),stock.getCount()});
		    		  File file = new File("files.txt");	    		  
		    		 try {	    				
		    				FileOutputStream out = new FileOutputStream(file);
		    				out.write(stock.getId());
		    				out.close();	    			
		    				repaint();			    				
		    			} catch (Exception ee) {
		    				ee.printStackTrace();
		    			}
		    	}
		    	else{
		    		 dm.addRow(new Object[]{"�Ѿ����",stock.getId(),stock.getsName(),stock.getOrderId(),stock.getConsignmentDate(),stock.getBaleName(),
		    				 stock.getMoney(),stock.getCount()});
		    	}
		    }	   
		    table.getColumn("�Ƿ����").setCellRenderer(new ButtonRenderer());	//����ָ���е���Ⱦ��
		    table.getColumn("�Ƿ����").setCellEditor(new ButtonEditor(new JCheckBox()));
		JButton insertButton = new JButton("���");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserStockFrame frame = new InserStockFrame();
				frame.setVisible(true);
			}
		});
		insertButton.setBounds(143, 353, 68, 23);
		add(insertButton);
		
JButton updateButton = new JButton("�޸�");
updateButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		  int row = table.getSelectedRow();  			   //��ȡ�û�ѡ�б�������
			if (row < 0) {	
				JOptionPane.showMessageDialog(getParent(), "û��ѡ��Ҫ�޸ĵ����ݣ�",
						"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
				return;
			} else {
				File file = new File("filedd.txt");			//�����ļ�����
				try {
					String column = dm.getValueAt(row, 1).toString();		//��ȡ����е�����						
					file.createNewFile();					//�½��ļ�
					FileOutputStream out = new FileOutputStream(file);
					out.write((Integer.parseInt(column)));					//������д���ļ���
					UpdateStockFrame frame = new UpdateStockFrame();		//�����޸���Ϣ����
					frame.setVisible(true);				
					out.close();							//�����ر�		
					repaint();
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
	}
});
		updateButton.setBounds(236, 353, 68, 23);
		add(updateButton);
		
JButton deleteButton = new JButton("ɾ��");
deleteButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		int row = table.getSelectedRow();  			//��ȡ�û�ѡ��ı����к�	   
		if (row < 0) {								//�ж��û�ѡ����к��Ƿ����0
			JOptionPane.showMessageDialog(getParent(), "û��ѡ��Ҫɾ�������ݣ�",
					"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);	
			return;									//�˳�����
		}
		String column = dm.getValueAt(row, 1).toString();		//��ȡ�û�ѡ����еĵ�һ������	
		dao.deleteStock(Integer.parseInt(column));				//����ɾ�����ݷ���
		JOptionPane.showMessageDialog(getParent(), "����ɾ���ɹ���",	
				"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);		//������ʾ��Ϣ
	}
});
		deleteButton.setBounds(337, 353, 68, 23);
		add(deleteButton);
		
		JLabel label = new JLabel("��ѯ������");
		label.setBounds(79, 72, 68, 15);
		add(label);
		String[] name = {"�ͻ�","������","��������"};
		final JComboBox comboBox = new JComboBox(name);
		comboBox.setBounds(143, 66, 81, 21);
		add(comboBox);
		
		conditionTextField = new JTextField();
		conditionTextField.setBounds(252, 69, 145, 25);
		add(conditionTextField);
		conditionTextField.setColumns(10);
		
JButton findButton = new JButton("����");
findButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		dm.setRowCount(0);			//������������
		String condition = comboBox.getSelectedItem().toString();		//��ȡ�û�ѡ��Ĳ�ѯ����
		String conditionText = conditionTextField.getText();			//��ȡ�û���ӵĲ�ѯ����
		if(conditionText.equals("")){									//����û�û����Ӳ�ѯ����
			JOptionPane.showMessageDialog(getParent(), "�������ѯ������",
					"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);		//������ʾ��Ϣ
			return;														//�˳�����
		}
		if(condition.equals("��Ʒ����")){								//����û�ѡ�񰴻�Ʒ���ƽ�������
		   List list = dao.selectStockBySName(conditionText);			//���ð���Ʒ���Ʋ�ѯ���ݷ���
		   for(int i= 0;i<list.size();i++){								//ѭ��������ѯ���
			   Stock stock = (Stock)list.get(i);
				String oid = stock.getOrderId();						//��ȡ��������Ϣ
		    	int id = dao.selectJoinStockByOid(oid);	    			//���ݶ����Ų�ѯ�����Ϣ
		    	if(id <=0){												//����ö����Ļ�Ʒ�������в�����
		    		 dm.addRow(new Object[]{"���",stock.getId(),stock.getsName(),stock.getOrderId(),stock.getConsignmentDate(),stock.getBaleName(),
		    				 stock.getMoney(),stock.getCount()});		//�������������
	 
		    	}
		    	else{													//���ָ�������ŵĻ�Ʒ�����������д���
		    		 dm.addRow(new Object[]{"�Ѿ����",stock.getId(),stock.getsName(),stock.getOrderId(),stock.getConsignmentDate(),stock.getBaleName(),
		    				 stock.getMoney(),stock.getCount()});
		    	}
		   }
		}
				if(condition.equals("������")){
					   List list = dao.selectStockByOid(conditionText);
					    for(int i= 0;i<list.size();i++){
						   Stock stock = (Stock)list.get(i);
							String oid = stock.getOrderId();
					    	int id = dao.selectJoinStockByOid(oid);	    
					       	if(id <=0){
					    		 dm.addRow(new Object[]{"���",stock.getId(),stock.getsName(),stock.getOrderId(),stock.getConsignmentDate(),stock.getBaleName(),
					    				 stock.getMoney(),stock.getCount()});
					    		  File file = new File("files.txt");	  
					    		 
					    	}
					    	else{
					    		 dm.addRow(new Object[]{"�Ѿ����",stock.getId(),stock.getsName(),stock.getOrderId(),stock.getConsignmentDate(),stock.getBaleName(),
					    				 stock.getMoney(),stock.getCount()});
					    	}
					   }
					}
				if(condition.equals("��������")){
					   List list = dao.selectStockByDate(conditionText);
					   for(int i= 0;i<list.size();i++){
						   Stock stock = (Stock)list.get(i);
							String oid = stock.getOrderId();
					    	int id = dao.selectJoinStockByOid(oid);	    	
					    	if(id <=0){
					    		 dm.addRow(new Object[]{"���",stock.getId(),stock.getsName(),stock.getOrderId(),stock.getConsignmentDate(),stock.getBaleName(),
					    				 stock.getMoney(),stock.getCount()});
					    		  File file = new File("files.txt");	  
					    		 
					    	}
					    	else{
					    		 dm.addRow(new Object[]{"�Ѿ����",stock.getId(),stock.getsName(),stock.getOrderId(),stock.getConsignmentDate(),stock.getBaleName(),
					    				 stock.getMoney(),stock.getCount()});
					    	}
					   }
					}
			}
		});
		findButton.setBounds(407, 68, 68, 23);
		add(findButton);
		
	}
}
