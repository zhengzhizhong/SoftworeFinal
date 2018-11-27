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
import com.mingrisoft.bean.Provide;
import com.mingrisoft.bean.Sell;
import com.mingrisoft.dao.FeelDao;
import com.mingrisoft.dao.SellDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
public class InserSellFrame extends JFrame {
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
	public InserSellFrame() {
		setTitle("添加销售商窗体");	
		setBounds(100, 100, 635, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel cNameLabel = new JLabel("销售商名称：");
		cNameLabel.setBounds(35, 53, 86, 15);
		contentPane.add(cNameLabel);

		cNameTextField = new JTextField();
		cNameTextField.setBounds(114, 50, 164, 25);
		contentPane.add(cNameTextField);
		cNameTextField.setColumns(10);

		JLabel addressLabel = new JLabel("销售商地址：");
		addressLabel.setBounds(301, 53, 86, 15);
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
				SellDao sellDao = new SellDao();
				Sell sell = new Sell();				
				String sellName = cNameTextField.getText();
				String address = addressTextField.getText();
				String linkName = linkNameTextField.getText();
				String linkPhone = linkPhoneTextField.getText();
				String faxes = faxesTextField.getText();
				String bankNum = bankNumTextField.getText();
				String eAddress = netAddressTextField.getText();
				System.out.println("ssssss "+eAddress);
				if((sellName.equals("")) || (address.equals(""))  ||
					(linkName.equals("")) || (linkPhone.equals("")) ||(faxes.equals(""))
					 ||(bankNum.equals(""))){
					JOptionPane.showMessageDialog(getContentPane(), "请将带星号的内容填写完整！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
					
				}
				sell.setSellName(sellName);
				sell.setAddress(address);
				sell.setBankNum(bankNumTextField.getText());
				sell.setFaxNum(faxes);
				sell.setPostNum(postNumTextField.getText());
				sell.setBankNum(bankNum);
				sell.setLinkman(linkName);
				sell.setLinkPhone(linkPhone);
				sell.setNetAddress(eAddress);
				sell.setEmallAddress(emaillTextField.getText());
				sell.setRenark(remarkTextArea.getText());
				sellDao.insertSell(sell);
				JOptionPane.showMessageDialog(getContentPane(), "数据添加成功！",
						"信息提示框", JOptionPane.INFORMATION_MESSAGE);
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
		
		label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setBounds(284, 140, 7, 15);
		contentPane.add(label_4);
		
		label_5 = new JLabel("*");
		label_5.setForeground(Color.RED);
		label_5.setBounds(284, 180, 7, 15);
		contentPane.add(label_5);
		
		label_6 = new JLabel("*");
		label_6.setForeground(Color.RED);
		label_6.setBounds(284, 223, 7, 15);
		contentPane.add(label_6);
	}

	protected void do_closeButton_actionPerformed(ActionEvent arg0) {
		this.setVisible(false);
	}
}
