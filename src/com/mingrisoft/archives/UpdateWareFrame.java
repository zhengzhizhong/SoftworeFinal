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

import com.mingrisoft.bean.Ware;
import com.mingrisoft.bean.Sell;
import com.mingrisoft.dao.FeelDao;
import com.mingrisoft.dao.SellDao;
import com.mingrisoft.dao.WareDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.awt.Color;
public class UpdateWareFrame extends JFrame {

	private JPanel contentPane;
	private JTextField cNameTextField;
	private JTextField bewriteTextField;
	private JLabel linkNameLabel;
	private JTextField specTextField;
	private JLabel linkPhoneLabel;
	private JTextField stockTextField;
	private JLabel fexesLabel;
	private JTextField memberTextField;
	private JLabel postNumLabel;
	private JTextField retailTextField;
	private JButton closeButton;
	Ware ware = null;
	WareDao dao = new WareDao();

	/**
	 * Create the frame.
	 */
	public UpdateWareFrame() {
		setTitle("�޸Ļ�Ʒ����");	
	
		 try {
			  File file = new File("file.txt");
	            FileInputStream fin = new FileInputStream(file);
	            int count =  fin.read();	           
	            file.delete();
	            ware = dao.selectWareByid(count);
	        } catch (Exception e) {
	          
	            e.printStackTrace();
	        }
	
		setBounds(100, 100, 635, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel cNameLabel = new JLabel("��Ʒ���ƣ�");
		cNameLabel.setBounds(49, 53, 72, 15);
		contentPane.add(cNameLabel);

		cNameTextField = new JTextField();
		cNameTextField.setText(ware.getWareName());
		cNameTextField.setBounds(114, 50, 164, 25);
		contentPane.add(cNameTextField);
		cNameTextField.setColumns(10);

		JLabel addressLabel = new JLabel("��Ʒ������");
		addressLabel.setBounds(315, 53, 72, 15);
		contentPane.add(addressLabel);

		bewriteTextField = new JTextField();
		bewriteTextField.setText(ware.getWarBewrite());
		bewriteTextField.setColumns(10);
		bewriteTextField.setBounds(385, 50, 164, 25);
		contentPane.add(bewriteTextField);

		linkNameLabel = new JLabel(" �� λ��");
		
		linkNameLabel.setBounds(49, 97, 70, 15);
		contentPane.add(linkNameLabel);

		specTextField = new JTextField();
		specTextField.setText(ware.getSpec());
		specTextField.setBounds(114, 94, 164, 25);
		contentPane.add(specTextField);
		specTextField.setColumns(10);

		linkPhoneLabel = new JLabel("�����ۣ�");
		linkPhoneLabel.setBounds(315, 97, 72, 15);
		contentPane.add(linkPhoneLabel);

		stockTextField = new JTextField();
		stockTextField.setText(""+ware.getStockPrice());
		stockTextField.setBounds(385, 94, 164, 25);
		contentPane.add(stockTextField);
		stockTextField.setColumns(10);

		fexesLabel = new JLabel(" ��Ա�ۣ�");
		fexesLabel.setBounds(50, 140, 54, 15);
		contentPane.add(fexesLabel);

		memberTextField = new JTextField();
		memberTextField.setText(""+ware.getAssociatorPrice());
		memberTextField.setColumns(10);
		memberTextField.setBounds(114, 137, 164, 25);
		contentPane.add(memberTextField);

		postNumLabel = new JLabel(" ���ۼۣ�");
		postNumLabel.setBounds(321, 140, 54, 15);
		contentPane.add(postNumLabel);
		retailTextField = new JTextField();
		retailTextField.setText(""+ware.getRetailPrice());
		retailTextField.setColumns(10);
		retailTextField.setBounds(385, 137, 164, 25);
		contentPane.add(retailTextField);
		JButton uodatetButton = new JButton("�޸�");
		uodatetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cName = cNameTextField.getText();
				String bewrite = bewriteTextField.getText();
				String unit = stockTextField.getText();
				String retail = retailTextField.getText();
				String member = memberTextField.getText();	
				String spec = specTextField.getText();
				String stock = stockTextField.getText();
				float memberPrice =0;
				float unitPrice = 0;
				float retailPrice = 0;
				if((cName.equals(""))||(bewrite.equals(""))||(unit.equals(""))||
						(retail.equals(""))||(member.equals(""))){
					JOptionPane.showMessageDialog(getContentPane(), "�����Ǻŵ���Ϣ��д������",
							"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				try{
					unitPrice = Float.parseFloat(unit);
					retailPrice = Float.parseFloat(retail);
					memberPrice = Float.parseFloat(member);
					
				}catch (Exception ee) {
					JOptionPane.showMessageDialog(getContentPane(), "�����ۡ����ۼۡ���Ա�۱��������֣�",
							"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					return;
				}				
				
				ware.setWareName(cNameTextField.getText());
				ware.setWarBewrite(bewriteTextField.getText());
				ware.setSpec(specTextField.getText());
				ware.setStockPrice(unitPrice);
				ware.setAssociatorPrice(memberPrice);
				ware.setRetailPrice(retailPrice);
				dao.updateWare(ware);
				repaint();
				JOptionPane.showMessageDialog(getContentPane(), "�����޸ĳɹ���",
						"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		uodatetButton.setBounds(217, 172, 93, 23);
		contentPane.add(uodatetButton);

		closeButton = new JButton("�˳�");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_closeButton_actionPerformed(e);
			}
		});
		closeButton.setBounds(331, 172, 93, 23);
		contentPane.add(closeButton);
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(288, 53, 17, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(559, 53, 17, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setBounds(288, 97, 17, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setBounds(559, 97, 17, 15);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setBounds(288, 140, 17, 15);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("*");
		label_5.setForeground(Color.RED);
		label_5.setBounds(559, 140, 17, 15);
		contentPane.add(label_5);
	}

	protected void do_closeButton_actionPerformed(ActionEvent arg0) {
		this.setVisible(false);
	}
}
