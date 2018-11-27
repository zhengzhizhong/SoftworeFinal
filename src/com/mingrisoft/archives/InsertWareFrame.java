package com.mingrisoft.archives;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import com.mingrisoft.bean.Provide;
import com.mingrisoft.bean.Ware;
import com.mingrisoft.dao.FeelDao;
import com.mingrisoft.dao.WareDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class InsertWareFrame extends JFrame {

	private JPanel contentPane;
	private JTextField cNameTextField;
	private JTextField bewriteTextField;
	private JLabel unitLabel;
	private JTextField unitTextField;
	private JLabel stockLabel;
	private JTextField stockTextField;
	private JLabel retailLabel;
	private JTextField retailTextField;
	private JLabel memberLabel;
	private JTextField memberTextField;
	private JButton closeButton;
	private JLabel starLabel;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	/**
	 * Create the frame.
	 */
	public InsertWareFrame() {
		setTitle("添加货品窗体");	
		setBounds(100, 100, 635, 267);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel cNameLabel = new JLabel("货品名称：");
		cNameLabel.setBounds(49, 53, 65, 15);
		contentPane.add(cNameLabel);

		cNameTextField = new JTextField();
		cNameTextField.setBounds(114, 50, 164, 25);
		contentPane.add(cNameTextField);
		cNameTextField.setColumns(10);

		JLabel bewriteLabel = new JLabel("货品描述：");
		bewriteLabel.setBounds(315, 53, 72, 15);
		contentPane.add(bewriteLabel);

		bewriteTextField = new JTextField();
		bewriteTextField.setColumns(10);
		bewriteTextField.setBounds(385, 50, 164, 25);
		contentPane.add(bewriteTextField);

		unitLabel = new JLabel(" 单  位：");
		unitLabel.setBounds(49, 97, 54, 15);
		contentPane.add(unitLabel);

		unitTextField = new JTextField();
		unitTextField.setBounds(114, 94, 164, 25);
		contentPane.add(unitTextField);
		unitTextField.setColumns(10);

		stockLabel = new JLabel("进 货 价：");
		stockLabel.setBounds(315, 97, 72, 15);
		contentPane.add(stockLabel);

		stockTextField = new JTextField();
		stockTextField.setBounds(385, 94, 164, 25);
		contentPane.add(stockTextField);
		stockTextField.setColumns(10);

		retailLabel = new JLabel("零 售 价：");
		retailLabel.setBounds(49, 142, 65, 15);
		contentPane.add(retailLabel);

		retailTextField = new JTextField();
		retailTextField.setColumns(10);
		retailTextField.setBounds(114, 137, 164, 25);
		contentPane.add(retailTextField);

		memberLabel = new JLabel("会 员 价：");
		memberLabel.setBounds(315, 140, 60, 15);
		contentPane.add(memberLabel);

		memberTextField = new JTextField();
		memberTextField.setColumns(10);
		memberTextField.setBounds(385, 137, 164, 25);
		contentPane.add(memberTextField);

		JButton insertButton = new JButton("添加");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WareDao dao = new WareDao();
				String cName = cNameTextField.getText();
				String bewrite = bewriteTextField.getText();
				String unit = stockTextField.getText();
				String retail = retailTextField.getText();
				String member = memberTextField.getText();	
				String spec = unitTextField.getText();
				String stock = stockTextField.getText();
				float memberPrice =0;
				float unitPrice = 0;
				float retailPrice = 0;
				if((cName.equals(""))||(bewrite.equals(""))||(unit.equals(""))||
						(retail.equals(""))||(member.equals(""))){
					JOptionPane.showMessageDialog(getContentPane(), "将带星号的信息填写完整！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				try{
					unitPrice = Float.parseFloat(unit);
					retailPrice = Float.parseFloat(retail);
					memberPrice = Float.parseFloat(member);
					
				}catch (Exception ee) {
					JOptionPane.showMessageDialog(getContentPane(), "进货价、零售价、会员价必须是数字！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}				
			
				Ware ware = new Ware();
				ware.setWareName(cName);
				ware.setWarBewrite(bewrite);
				ware.setAssociatorPrice(memberPrice);
				ware.setSpec(spec);
				ware.setStockPrice(memberPrice);
				ware.setRetailPrice(retailPrice);
				dao.insertWare(ware);
				JOptionPane.showMessageDialog(getContentPane(), "数据添加成功！",
						"信息提示框", JOptionPane.INFORMATION_MESSAGE);
				

			}
		});
		insertButton.setBounds(185, 185, 93, 23);
		contentPane.add(insertButton);

		closeButton = new JButton("退出");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_closeButton_actionPerformed(e);
			}
		});
		closeButton.setBounds(315, 185, 93, 23);
		contentPane.add(closeButton);
		
		starLabel = new JLabel("*");
		starLabel.setForeground(Color.RED);
		starLabel.setBounds(283, 53, 6, 15);
		contentPane.add(starLabel);
		
		label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(559, 53, 6, 15);
		contentPane.add(label_1);
		
		label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setBounds(283, 97, 6, 15);
		contentPane.add(label_2);
		
		label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setBounds(559, 97, 6, 15);
		contentPane.add(label_3);
		
		label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setBounds(283, 140, 6, 15);
		contentPane.add(label_4);
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(559, 140, 6, 15);
		contentPane.add(label);
	}

	protected void do_closeButton_actionPerformed(ActionEvent arg0) {
		this.setVisible(false);
	}
}
