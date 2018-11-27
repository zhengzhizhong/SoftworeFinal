package com.mingrisoft.archives;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import com.mingrisoft.bean.JoinDepot;
import com.mingrisoft.bean.Provide;
import com.mingrisoft.bean.Sell;
import com.mingrisoft.bean.Stock;
import com.mingrisoft.dao.FeelDao;
import com.mingrisoft.dao.JoinDepotDao;
import com.mingrisoft.dao.SellDao;
import com.mingrisoft.dao.StockDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.List;

import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
public class InserJoinDepotFrame extends JFrame {
	private JPanel contentPane;
	private JLabel dateLabel;
	private JTextField wNameTextField;
	private JLabel joinTimeLabel;
	private JTextField joinTimeTextField;
	private JLabel remarkLabel;
	private JButton closeButton;
	private JLabel starLabel;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	JoinDepotDao dao = new JoinDepotDao();
	private JComboBox dIdcomboBox;
	JComboBox oIdcomboBox;
	JTextArea remarkTextArea = new JTextArea();
	private JTextField wighttextField;
	private JLabel label_4;	

	/**
	 * Create the frame.
	 */
	public InserJoinDepotFrame() {
		setTitle("添加仓库入库窗体");	
		setBounds(100, 100, 635, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel orderIdLabel = new JLabel("订单号：");
		orderIdLabel.setBounds(59, 55, 60, 15);
		
		contentPane.add(orderIdLabel);

		JLabel dIdLabel = new JLabel("仓库编号：");
		dIdLabel.setBounds(315, 55, 74, 15);
		contentPane.add(dIdLabel);

		dateLabel = new JLabel(" 货品名称：");
		dateLabel.setBounds(41, 99, 70, 15);
		contentPane.add(dateLabel);

		wNameTextField = new JTextField();
		wNameTextField.setBounds(114, 94, 164, 25);
		contentPane.add(wNameTextField);
		wNameTextField.setColumns(10);

		joinTimeLabel = new JLabel("入库时间：");
		joinTimeLabel.setBounds(315, 97, 72, 15);
		contentPane.add(joinTimeLabel);

		joinTimeTextField = new JTextField();
		joinTimeTextField.setBounds(385, 94, 164, 25);
		contentPane.add(joinTimeTextField);
		joinTimeTextField.setColumns(10);

		remarkLabel = new JLabel("  备 注：");
		remarkLabel.setBounds(59, 233, 54, 15);
		contentPane.add(remarkLabel);
		
		
		JButton insertButton = new JButton("添加");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				String did = dIdcomboBox.getSelectedItem().toString();
				String oId = oIdcomboBox.getSelectedItem().toString();
				String wName = wNameTextField.getText();
				String joinTime = joinTimeTextField.getText();
				String wight = wighttextField.getText();
				float depotWight = 0;
				if((did.equals(""))||(wName.equals("")) ||(joinTime.equals("")) ||(wight.equals(""))){
					JOptionPane.showMessageDialog(getContentPane(), "请将带星号的内容填写完整！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;					
				}		
				try{
					depotWight = Float.parseFloat(wight);
				}catch (Exception ee) {
					JOptionPane.showMessageDialog(getContentPane(), "请输出数值类型！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;					
				}
				JoinDepot joinDepot = new JoinDepot();
				joinDepot.setdId(Integer.parseInt(did));
				joinDepot.setJoinTime(joinTime);
				joinDepot.setWareName(wName);
				joinDepot.setWeight(depotWight);
				joinDepot.setRemark(remarkTextArea.getText());
				dao.insertJoinDepot(joinDepot);
				JOptionPane.showMessageDialog(getContentPane(), "数据添加成功！",
						"信息提示框", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		insertButton.setBounds(185, 329, 93, 23);
		contentPane.add(insertButton);

		closeButton = new JButton("退出");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_closeButton_actionPerformed(e);
			}
		});
		closeButton.setBounds(326, 329, 93, 23);
		contentPane.add(closeButton);
		
		starLabel = new JLabel("*");
		starLabel.setForeground(Color.RED);
		starLabel.setBounds(284, 53, 7, 15);
		contentPane.add(starLabel);
		
		label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(558, 53, 7, 15);
		contentPane.add(label_1);
		
		label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setBounds(284, 97, 7, 15);
		contentPane.add(label_2);
		
		label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setBounds(559, 97, 7, 15);
		contentPane.add(label_3);
		List list = dao.selectOidId();
		String [] orderId = new String[list.size()+1];
		orderId[0] = "";
		for(int i = 0;i<list.size();i++){
			orderId[i+1 ]=  (String)list.get(i);
		}
		oIdcomboBox = new JComboBox(orderId);
		oIdcomboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String oid = oIdcomboBox.getSelectedItem().toString();				
				String wName = dao.selectOidId(oid);				
				wNameTextField.setText(wName);
			}
		});
		
		oIdcomboBox.setBounds(114, 52, 164, 21);
		contentPane.add(oIdcomboBox);		
		remarkTextArea.setBounds(114, 193, 435, 112);
		contentPane.add(remarkTextArea);
		List listDid = dao.selectDepotId();
		Integer[] did = new Integer [listDid.size()]; 
		for(int j = 0;j<listDid.size();j++){
			did[j] = (Integer)listDid.get(j);
		}
		dIdcomboBox = new JComboBox(did);
		dIdcomboBox.setBounds(385, 52, 164, 21);
		contentPane.add(dIdcomboBox);
		
		JLabel wightlabel = new JLabel("重量： ");
		wightlabel.setBounds(70, 146, 44, 15);
		contentPane.add(wightlabel);
		
		wighttextField = new JTextField();
		wighttextField.setBounds(114, 143, 164, 25);
		contentPane.add(wighttextField);
		wighttextField.setColumns(10);
		
		JLabel label = new JLabel("千克");
		label.setBounds(284, 146, 54, 15);
		contentPane.add(label);
		
		label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setBounds(315, 146, 7, 15);
		contentPane.add(label_4);
	}
	protected void do_closeButton_actionPerformed(ActionEvent arg0) {
		this.setVisible(false);
	}
}
