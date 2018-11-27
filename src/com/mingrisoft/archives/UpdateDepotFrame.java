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
import com.mingrisoft.bean.Provide;
import com.mingrisoft.bean.Ware;
import com.mingrisoft.dao.DepotDao;
import com.mingrisoft.dao.FeelDao;
import com.mingrisoft.dao.WareDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;

public class UpdateDepotFrame extends JFrame {

	private JPanel contentPane;
	private JTextField idTextField;
	private JLabel unitLabel;
	private JButton closeButton;
	private JLabel starLabel;
	JTextArea textArea = new JTextArea();
	DepotDao dao = new DepotDao();
	Depot depot ;
	/**
	 * Create the frame.
	 */
	public UpdateDepotFrame() {
		setTitle("修改仓库信息窗体");	
		setBounds(100, 100, 635, 267);
		 try {
			  File file = new File("file.txt");
	            FileInputStream fin = new FileInputStream(file);
	            int count =  fin.read();	           
	            file.delete();
	            depot = dao.selectDepotByid(count);
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel cNameLabel = new JLabel("  库  管：");
		cNameLabel.setBounds(39, 55, 65, 15);
		contentPane.add(cNameLabel);

		idTextField = new JTextField();
		idTextField.setBounds(114, 50, 164, 25);
		contentPane.add(idTextField);
		idTextField.setText(depot.getManage());
		idTextField.setColumns(10);

		unitLabel = new JLabel("  描 述：");
		unitLabel.setBounds(39, 97, 54, 15);
		contentPane.add(unitLabel);

		JButton insertButton = new JButton("修改");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				String manager = idTextField.getText();
				String content = textArea.getText();
				if(manager.equals("")){
					JOptionPane.showMessageDialog(getContentPane(), "将带星号的信息填写完整！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				depot.setManage(manager);
				depot.setFunctional(content);
				dao.updateDepot(depot);
				JOptionPane.showMessageDialog(getContentPane(), "数据修改成功！",
						"信息提示框", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		insertButton.setBounds(239, 196, 93, 23);
		contentPane.add(insertButton);

		closeButton = new JButton("退出");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_closeButton_actionPerformed(e);
			}
		});
		closeButton.setBounds(365, 196, 93, 23);
		contentPane.add(closeButton);
		
		starLabel = new JLabel("*");
		starLabel.setForeground(Color.RED);
		starLabel.setBounds(283, 53, 6, 15);
		contentPane.add(starLabel);
		
	
		textArea.setBounds(114, 93, 435, 82);
		contentPane.add(textArea);
		textArea.setText(depot.getFunctional());
	}

	protected void do_closeButton_actionPerformed(ActionEvent arg0) {
		this.setVisible(false);
	}
}
