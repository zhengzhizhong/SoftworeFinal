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
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class InsertDepotFrame extends JFrame {

	private JPanel contentPane;
	private JTextField idTextField;
	private JLabel unitLabel;
	private JButton closeButton;
	private JLabel starLabel;
	JTextArea textArea = new JTextArea();
	DepotDao dao = new DepotDao();
	String message = "";
	/**
	 * Create the frame.
	 */
	public InsertDepotFrame() {
		setTitle("��Ӳֿ���Ϣ����");	
		setBounds(100, 100, 635, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel cNameLabel = new JLabel("  ��  �ܣ�");
		cNameLabel.setBounds(39, 119, 65, 15);
		contentPane.add(cNameLabel);

		idTextField = new JTextField();
		idTextField.setBounds(114, 114, 164, 25);
		contentPane.add(idTextField);
		idTextField.setColumns(10);

		unitLabel = new JLabel("  ��  ����");
		unitLabel.setBounds(40, 165, 65, 15);
		contentPane.add(unitLabel);
		String[] ids = new String[]{"1","2","3"};			
		final JComboBox comboBox = new JComboBox(ids);
		JButton insertButton = new JButton("���");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!message.equals("")){
					JOptionPane.showMessageDialog(getContentPane(), "�òֿ��Ѿ��й���Ա�ˣ�",
							"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				String manager = idTextField.getText();
				String content = textArea.getText();
				if(manager.equals("")){
					JOptionPane.showMessageDialog(getContentPane(), "�����Ǻŵ���Ϣ��д������",
							"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				Depot depot = new Depot();
				depot.setId(Integer.parseInt(comboBox.getSelectedItem().toString()));
				depot.setManage(manager);				
				depot.setFunctional(content);
				dao.insertDepot(depot);
				JOptionPane.showMessageDialog(getContentPane(), "������ӳɹ���",
						"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
				

			}
		});
		insertButton.setBounds(226, 253, 93, 23);
		contentPane.add(insertButton);

		closeButton = new JButton("�˳�");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_closeButton_actionPerformed(e);
			}
		});
		closeButton.setBounds(351, 253, 93, 23);
		contentPane.add(closeButton);
		
		starLabel = new JLabel("*");
		starLabel.setForeground(Color.RED);
		starLabel.setBounds(286, 119, 6, 15);
		contentPane.add(starLabel);
		
	
		textArea.setBounds(114, 161, 435, 82);
		contentPane.add(textArea);
		
		JLabel label = new JLabel("��  �⣺");
		label.setBounds(50, 29, 54, 15);
		contentPane.add(label);
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				System.out.println(e.SELECTED);				
				java.util.List list = dao.selectDepot();
				for(int i = 0;i < list.size();i++){
					Depot depot = (Depot)list.get(i);
					int id = depot.getId();
					String strid = comboBox.getSelectedItem().toString();
					int idd = Integer.parseInt(strid);
					if(idd == id){
						message = "����������ݣ�";							
						break;						
					}
					else{
						message = "";
					}
				}	
			
			}
		});
		comboBox.setBounds(112, 26, 166, 21);
		contentPane.add(comboBox);
	}

	protected void do_closeButton_actionPerformed(ActionEvent arg0) {
		this.setVisible(false);
	}
}
