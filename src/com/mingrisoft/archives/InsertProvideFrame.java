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
import com.mingrisoft.dao.FeelDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class InsertProvideFrame extends JFrame {

	private JPanel contentPane;
	private JTextField cNameTextField;
	private JTextField addressTextField;
	private JLabel linkNameLabel;
	private JTextField linkNameTextField;
	private JLabel linkPhoneLabel;
	private JTextField linkPhoneTextField;
	private JLabel fexesLabel;
	private JTextField faxesTextField;
	private JLabel postNumLabel;
	private JTextField postNumTextField;
	private JLabel bankNumLabel;
	private JTextField bankNumTextField;
	private JLabel netAddressLabel;
	private JTextField netAddressTextField;
	private JLabel emailLabel;
	private JTextField emaillTextField;
	private JLabel label;
	private JButton closeButton;
	JTextArea remarkTextArea = new JTextArea();
	private JLabel starLabel;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	/**
	 * Create the frame.
	 */
	public InsertProvideFrame() {
		setTitle("添加供应商窗体");	
		setBounds(100, 100, 635, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel cNameLabel = new JLabel("客户名称：");
		cNameLabel.setBounds(49, 53, 72, 15);
		contentPane.add(cNameLabel);

		cNameTextField = new JTextField();
		cNameTextField.setBounds(114, 50, 164, 25);
		contentPane.add(cNameTextField);
		cNameTextField.setColumns(10);

		JLabel addressLabel = new JLabel("客户地址：");
		addressLabel.setBounds(315, 53, 72, 15);
		contentPane.add(addressLabel);

		addressTextField = new JTextField();
		addressTextField.setColumns(10);
		addressTextField.setBounds(385, 50, 164, 25);
		contentPane.add(addressTextField);

		linkNameLabel = new JLabel(" 联系人：");
		linkNameLabel.setBounds(49, 97, 70, 15);
		contentPane.add(linkNameLabel);

		linkNameTextField = new JTextField();
		linkNameTextField.setBounds(114, 94, 164, 25);
		contentPane.add(linkNameTextField);
		linkNameTextField.setColumns(10);

		linkPhoneLabel = new JLabel("联系电话：");
		linkPhoneLabel.setBounds(315, 97, 72, 15);
		contentPane.add(linkPhoneLabel);

		linkPhoneTextField = new JTextField();
		linkPhoneTextField.setBounds(385, 94, 164, 25);
		contentPane.add(linkPhoneTextField);
		linkPhoneTextField.setColumns(10);

		fexesLabel = new JLabel("  传 真：");
		fexesLabel.setBounds(50, 140, 54, 15);
		contentPane.add(fexesLabel);

		faxesTextField = new JTextField();
		faxesTextField.setColumns(10);
		faxesTextField.setBounds(114, 137, 164, 25);
		contentPane.add(faxesTextField);

		postNumLabel = new JLabel("  邮 编：");
		postNumLabel.setBounds(321, 140, 54, 15);
		contentPane.add(postNumLabel);

		postNumTextField = new JTextField();
		postNumTextField.setColumns(10);
		postNumTextField.setBounds(385, 137, 164, 25);
		contentPane.add(postNumTextField);

		bankNumLabel = new JLabel("银行账号：");
		bankNumLabel.setBounds(49, 180, 72, 15);
		contentPane.add(bankNumLabel);

		bankNumTextField = new JTextField();
		bankNumTextField.setColumns(10);
		bankNumTextField.setBounds(114, 177, 164, 25);
		contentPane.add(bankNumTextField);

		netAddressLabel = new JLabel("   网 址：");
		netAddressLabel.setBounds(315, 180, 60, 15);
		contentPane.add(netAddressLabel);

		netAddressTextField = new JTextField();
		netAddressTextField.setColumns(10);
		netAddressTextField.setBounds(385, 177, 164, 25);
		contentPane.add(netAddressTextField);

		emailLabel = new JLabel("   邮 箱：");
		emailLabel.setBounds(49, 223, 72, 15);
		contentPane.add(emailLabel);

		emaillTextField = new JTextField();
		emaillTextField.setColumns(10);
		emaillTextField.setBounds(114, 220, 164, 25);
		contentPane.add(emaillTextField);

		label = new JLabel("   备 注：");
		label.setBounds(49, 294, 72, 15);
		contentPane.add(label);

		remarkTextArea.setBounds(114, 267, 435, 89);
		contentPane.add(remarkTextArea);

		JButton insertButton = new JButton("添加");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FeelDao dao = new FeelDao();
				Provide provide = new Provide();
				String cName = cNameTextField.getText();
				String address = addressTextField.getText();
				String bankNum = bankNumTextField.getText();
				String linkName = linkNameTextField.getText();
				String linkPhone = linkPhoneTextField.getText();
				String faxes = faxesTextField.getText();
				String netAddress = netAddressTextField.getText();
				String emaillAddress = emaillTextField.getText();
				if((cName.equals(""))||(address.equals(""))||(bankNum.equals("")) ||(linkName.equals(""))
						||(linkPhone.equals("")) || (faxes.equals(""))){
					JOptionPane.showMessageDialog(getContentPane(), "将带星号的信息填写完整！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				provide.setcName(cName);
				provide.setAddress(address);
				provide.setBankNum(bankNum);
				provide.setLinkman(linkName);
				provide.setLinkPhone(linkPhone);
				provide.setFaxes(faxes);				
				provide.setBankNum(bankNum);
				provide.setPostNum(postNumTextField.getText());
				provide.setNetAddress(netAddress);
				provide.setEmaillAddress(emaillAddress);
				provide.setRemark(remarkTextArea.getText());
				dao.insertProvide(provide);
				JOptionPane.showMessageDialog(getContentPane(), "数据添加成功！",
						"信息提示框", JOptionPane.INFORMATION_MESSAGE);
				repaint();
				
			}
		});
		insertButton.setBounds(185, 388, 93, 23);
		contentPane.add(insertButton);

		closeButton = new JButton("退出");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_closeButton_actionPerformed(e);
			}
		});
		closeButton.setBounds(342, 388, 93, 23);
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
		
		label_5 = new JLabel("*");
		label_5.setForeground(Color.RED);
		label_5.setBounds(283, 180, 6, 15);
		contentPane.add(label_5);
		
		label_6 = new JLabel("*");
		label_6.setForeground(Color.RED);
		label_6.setBounds(283, 223, 6, 15);
		contentPane.add(label_6);
	}

	protected void do_closeButton_actionPerformed(ActionEvent arg0) {
		this.setVisible(false);
	}
}
