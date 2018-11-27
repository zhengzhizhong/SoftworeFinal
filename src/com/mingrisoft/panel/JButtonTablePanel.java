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
		this.setBorder(createTitledBorder(null, "采购订货",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font(
                        "sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 116, 520, 211);
		add(scrollPane);	
		dm.setDataVector(new Object[][]{{"","","","","","","",""}},
	                     new Object[]{"是否入库","编号","客户","订单号","交货日期","商品名","金额","数量"});	                     
		dm.removeRow(0);
		table = new JTable(dm);
		scrollPane.setViewportView(table);		 
		    List list = dao.selectStock();
		    for(int i = 0;i<list.size();i++){
		    	Stock stock = (Stock)list.get(i);
		    	String oid = stock.getOrderId();
		    	int id = dao.selectJoinStockByOid(oid);	    	
		    	if(id <=0){
		    		 dm.addRow(new Object[]{"入库",stock.getId(),stock.getsName(),stock.getOrderId(),stock.getConsignmentDate(),stock.getBaleName(),
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
		    		 dm.addRow(new Object[]{"已经入库",stock.getId(),stock.getsName(),stock.getOrderId(),stock.getConsignmentDate(),stock.getBaleName(),
		    				 stock.getMoney(),stock.getCount()});
		    	}
		    }	   
		    table.getColumn("是否入库").setCellRenderer(new ButtonRenderer());	//设置指定列的渲染器
		    table.getColumn("是否入库").setCellEditor(new ButtonEditor(new JCheckBox()));
		JButton insertButton = new JButton("添加");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserStockFrame frame = new InserStockFrame();
				frame.setVisible(true);
			}
		});
		insertButton.setBounds(143, 353, 68, 23);
		add(insertButton);
		
JButton updateButton = new JButton("修改");
updateButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		  int row = table.getSelectedRow();  			   //获取用户选中表格的行数
			if (row < 0) {	
				JOptionPane.showMessageDialog(getParent(), "没有选择要修改的数据！",
						"信息提示框", JOptionPane.INFORMATION_MESSAGE);
				return;
			} else {
				File file = new File("filedd.txt");			//创建文件对象
				try {
					String column = dm.getValueAt(row, 1).toString();		//获取表格中的数据						
					file.createNewFile();					//新建文件
					FileOutputStream out = new FileOutputStream(file);
					out.write((Integer.parseInt(column)));					//将数据写入文件中
					UpdateStockFrame frame = new UpdateStockFrame();		//创建修改信息窗体
					frame.setVisible(true);				
					out.close();							//将流关闭		
					repaint();
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
	}
});
		updateButton.setBounds(236, 353, 68, 23);
		add(updateButton);
		
JButton deleteButton = new JButton("删除");
deleteButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		int row = table.getSelectedRow();  			//获取用户选择的表格的行号	   
		if (row < 0) {								//判断用户选择的行号是否大于0
			JOptionPane.showMessageDialog(getParent(), "没有选择要删除的数据！",
					"信息提示框", JOptionPane.INFORMATION_MESSAGE);	
			return;									//退出程序
		}
		String column = dm.getValueAt(row, 1).toString();		//获取用户选择的行的第一列数据	
		dao.deleteStock(Integer.parseInt(column));				//调用删除数据方法
		JOptionPane.showMessageDialog(getParent(), "数据删除成功！",	
				"信息提示框", JOptionPane.INFORMATION_MESSAGE);		//给出提示信息
	}
});
		deleteButton.setBounds(337, 353, 68, 23);
		add(deleteButton);
		
		JLabel label = new JLabel("查询条件：");
		label.setBounds(79, 72, 68, 15);
		add(label);
		String[] name = {"客户","订单号","交货日期"};
		final JComboBox comboBox = new JComboBox(name);
		comboBox.setBounds(143, 66, 81, 21);
		add(comboBox);
		
		conditionTextField = new JTextField();
		conditionTextField.setBounds(252, 69, 145, 25);
		add(conditionTextField);
		conditionTextField.setColumns(10);
		
JButton findButton = new JButton("搜索");
findButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		dm.setRowCount(0);			//将表格内容清空
		String condition = comboBox.getSelectedItem().toString();		//获取用户选择的查询条件
		String conditionText = conditionTextField.getText();			//获取用户添加的查询条件
		if(conditionText.equals("")){									//如果用户没有添加查询条件
			JOptionPane.showMessageDialog(getParent(), "请输入查询条件！",
					"信息提示框", JOptionPane.INFORMATION_MESSAGE);		//给出提示信息
			return;														//退出程序
		}
		if(condition.equals("货品名称")){								//如果用户选择按货品名称进行搜索
		   List list = dao.selectStockBySName(conditionText);			//调用按货品名称查询数据方法
		   for(int i= 0;i<list.size();i++){								//循环遍历查询结果
			   Stock stock = (Stock)list.get(i);
				String oid = stock.getOrderId();						//获取订单号信息
		    	int id = dao.selectJoinStockByOid(oid);	    			//根据订单号查询入库信息
		    	if(id <=0){												//如果该订单的货品在入库表中不存在
		    		 dm.addRow(new Object[]{"入库",stock.getId(),stock.getsName(),stock.getOrderId(),stock.getConsignmentDate(),stock.getBaleName(),
		    				 stock.getMoney(),stock.getCount()});		//向表格中添加数据
	 
		    	}
		    	else{													//如果指定订单号的货品名称在入库表中存在
		    		 dm.addRow(new Object[]{"已经入库",stock.getId(),stock.getsName(),stock.getOrderId(),stock.getConsignmentDate(),stock.getBaleName(),
		    				 stock.getMoney(),stock.getCount()});
		    	}
		   }
		}
				if(condition.equals("订单号")){
					   List list = dao.selectStockByOid(conditionText);
					    for(int i= 0;i<list.size();i++){
						   Stock stock = (Stock)list.get(i);
							String oid = stock.getOrderId();
					    	int id = dao.selectJoinStockByOid(oid);	    
					       	if(id <=0){
					    		 dm.addRow(new Object[]{"入库",stock.getId(),stock.getsName(),stock.getOrderId(),stock.getConsignmentDate(),stock.getBaleName(),
					    				 stock.getMoney(),stock.getCount()});
					    		  File file = new File("files.txt");	  
					    		 
					    	}
					    	else{
					    		 dm.addRow(new Object[]{"已经入库",stock.getId(),stock.getsName(),stock.getOrderId(),stock.getConsignmentDate(),stock.getBaleName(),
					    				 stock.getMoney(),stock.getCount()});
					    	}
					   }
					}
				if(condition.equals("交货日期")){
					   List list = dao.selectStockByDate(conditionText);
					   for(int i= 0;i<list.size();i++){
						   Stock stock = (Stock)list.get(i);
							String oid = stock.getOrderId();
					    	int id = dao.selectJoinStockByOid(oid);	    	
					    	if(id <=0){
					    		 dm.addRow(new Object[]{"入库",stock.getId(),stock.getsName(),stock.getOrderId(),stock.getConsignmentDate(),stock.getBaleName(),
					    				 stock.getMoney(),stock.getCount()});
					    		  File file = new File("files.txt");	  
					    		 
					    	}
					    	else{
					    		 dm.addRow(new Object[]{"已经入库",stock.getId(),stock.getsName(),stock.getOrderId(),stock.getConsignmentDate(),stock.getBaleName(),
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
