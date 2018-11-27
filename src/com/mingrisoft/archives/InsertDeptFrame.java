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

import com.mingrisoft.bean.Depot;
import com.mingrisoft.bean.Dept;
import com.mingrisoft.bean.Provide;
import com.mingrisoft.bean.Ware;
import com.mingrisoft.dao.DepotDao;
import com.mingrisoft.dao.DeptDao;
import com.mingrisoft.dao.FeelDao;
import com.mingrisoft.dao.WareDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class InsertDeptFrame extends JFrame {

	private JPanel contentPane;
	private JTextField dNameTextField;
	private JLabel unitLabel;
	private JButton closeButton;
	private JLabel starLabel;
	JTextArea textArea = new JTextArea();
	private JTextField managerTextField;	

	/**
	 * Create the frame.
	 */
	public InsertDeptFrame() {
		setTitle("添加部门信息窗体");	
		setBounds(100, 100, 635, 267);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel bNameLabel = new JLabel("部门名称：");
		bNameLabel.setBounds(39, 55, 65, 15);
		contentPane.add(bNameLabel);

		dNameTextField = new JTextField();
		dNameTextField.setBounds(114, 50, 164, 25);
		contentPane.add(dNameTextField);
		dNameTextField.setColumns(10);

		unitLabel = new JLabel("  描 述：");
		unitLabel.setBounds(39, 121, 54, 15);
		contentPane.add(unitLabel);

		JButton insertButton = new JButton("添加");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeptDao dao = new DeptDao();
				String dName = dNameTextField.getText();
				String manager = managerTextField.getText();
				if(dName.equals("") ||(manager.equals(""))){
					JOptionPane.showMessageDialog(getContentPane(), "将带星号的信息填写完整！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				Dept dept = new Dept();
				dept.setdName(dName);
				dept.setPrincipal(manager);
				dept.setBewrite(textArea.getText());
				dao.insertDept(dept);
				JOptionPane.showMessageDialog(getContentPane(), "数据添加成功！",
						"信息提示框", JOptionPane.INFORMATION_MESSAGE);
				

			}
		});
		insertButton.setBounds(210, 196, 93, 23);
		contentPane.add(insertButton);

		closeButton = new JButton("退出");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_closeButton_actionPerformed(e);
			}
		});
		closeButton.setBounds(334, 196, 93, 23);
		contentPane.add(closeButton);
		
		starLabel = new JLabel("*");
		starLabel.setForeground(Color.RED);
		starLabel.setBounds(283, 53, 6, 15);
		contentPane.add(starLabel);
		
	
		textArea.setBounds(114, 93, 415, 82);
		contentPane.add(textArea);
		
		JLabel managerLabel = new JLabel("负责人：");
		managerLabel.setBounds(308, 55, 54, 15);
		contentPane.add(managerLabel);
		
		managerTextField = new JTextField();
		managerTextField.setColumns(10);
		managerTextField.setBounds(361, 52, 164, 25);
		contentPane.add(managerTextField);
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(535, 55, 6, 15);
		contentPane.add(label);
	}

	protected void do_closeButton_actionPerformed(ActionEvent arg0) {
		this.setVisible(false);
	}
}
