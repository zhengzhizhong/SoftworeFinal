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

import com.mingrisodft.util.GetDate;
import com.mingrisoft.bean.JoinDepot;
import com.mingrisoft.bean.OutDepot;
import com.mingrisoft.bean.Provide;
import com.mingrisoft.bean.Sell;
import com.mingrisoft.bean.Stock;
import com.mingrisoft.dao.FeelDao;
import com.mingrisoft.dao.JoinDepotDao;
import com.mingrisoft.dao.OutDepotDao;
import com.mingrisoft.dao.SellDao;
import com.mingrisoft.dao.StockDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.sql.Date;
import java.util.List;

import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
public class InserOutDepotFrame extends JFrame {
	private JPanel contentPane;
	private JLabel dateLabel;
	private JLabel joinTimeLabel;
	private JTextField joinTimeTextField;
	private JLabel remarkLabel;
	private JButton closeButton;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	OutDepotDao dao = new OutDepotDao();
	private JComboBox dIdcomboBox;
	JTextArea remarkTextArea = new JTextArea();
	private JTextField wighttextField;
	private JLabel label_4;
	JButton button;
	JComboBox comboBox;
	
	/**
	 * Create the frame.
	 */
	public InserOutDepotFrame() {
		setTitle("��Ӳֿ���ⴰ��");	
		setBounds(100, 100, 689, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel dIdLabel = new JLabel("�ֿ��ţ�");
		dIdLabel.setBounds(37, 36, 74, 15);
		contentPane.add(dIdLabel);

		dateLabel = new JLabel(" ��Ʒ���ƣ�");
		dateLabel.setBounds(328, 36, 70, 15);
		contentPane.add(dateLabel);

		joinTimeLabel = new JLabel("����ʱ�䣺");
		joinTimeLabel.setBounds(328, 77, 72, 15);
		contentPane.add(joinTimeLabel);

		joinTimeTextField = new JTextField();
		joinTimeTextField.setBounds(392, 72, 164, 25);
		contentPane.add(joinTimeTextField);
		joinTimeTextField.setColumns(10);

		remarkLabel = new JLabel("  �� ע��");
		remarkLabel.setBounds(37, 158, 54, 15);
		contentPane.add(remarkLabel);
		
		
		JButton insertButton = new JButton("���");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				String did = dIdcomboBox.getSelectedItem().toString();				
				String joinTime = joinTimeTextField.getText();
				String wight = wighttextField.getText();
				String wName = comboBox.getSelectedItem().toString();
				float depotWight = 0;
				if((did.equals(""))|(joinTime.equals("")) ||(wight.equals(""))){
					JOptionPane.showMessageDialog(getContentPane(), "�뽫���Ǻŵ�������д������",
							"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					return;					
				}		
				try{
					depotWight = Float.parseFloat(wight);
				}catch (Exception ee) {
					JOptionPane.showMessageDialog(getContentPane(), "�������ֵ���ͣ�",
							"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					return;					
				}
				OutDepotDao dao = new OutDepotDao();
				float wFloat = dao.selectJoinDepotAndDate(comboBox.getSelectedItem().toString(),Integer.parseInt(did));
				System.out.println("comboBox :"+comboBox.getSelectedItem().toString());
				System.out.println("INTEGER��"+Integer.parseInt(did));
				if(wFloat<depotWight){
					JOptionPane.showMessageDialog(getContentPane(), "�ֿ���ֻ��"+wFloat+"ǧ���ˣ�",
							"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				OutDepot outDepot = new OutDepot();
				int idid = Integer.parseInt(did);
				outDepot.setDid(idid);
				outDepot.setOutDate(joinTimeTextField.getText());
				outDepot.setWight(depotWight);
				outDepot.setRemark(remarkTextArea.getText());
				outDepot.setwName(wName);
				dao.insertOutDepot(outDepot);
				dao.updateJoin(idid,wName,depotWight);
				JOptionPane.showMessageDialog(getContentPane(), "������ӳɹ���",
						"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		insertButton.setBounds(187, 273, 93, 23);
		contentPane.add(insertButton);

		closeButton = new JButton("�˳�");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_closeButton_actionPerformed(e);
			}
		});
		closeButton.setBounds(328, 273, 93, 23);
		contentPane.add(closeButton);
		
		label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(284, 36, 7, 15);
		contentPane.add(label_1);
		
		label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setBounds(579, 36, 7, 15);
		contentPane.add(label_2);
		
		label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setBounds(647, 77, 7, 15);
		contentPane.add(label_3);
		JoinDepotDao jDao = new JoinDepotDao();
		List list = jDao.selectOidId();
		String [] orderId = new String[list.size()+1];
		orderId[0] = "";
		for(int i = 0;i<list.size();i++){
			orderId[i+1 ]=  (String)list.get(i);
		}
		remarkTextArea.setBounds(101, 123, 417, 112);
		contentPane.add(remarkTextArea);
		List listDid = jDao.selectDepotId();
		Integer[] did = new Integer [listDid.size()]; 
		for(int j = 0;j<listDid.size();j++){
			did[j] = (Integer)listDid.get(j);
		}
		dIdcomboBox = new JComboBox(did);
		dIdcomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.removeAllItems();
				String str = dIdcomboBox.getSelectedItem().toString();
				JoinDepotDao dao = new JoinDepotDao();
				List list = dao.selectNameBydId(Integer.parseInt(str));
				for (int i = 0; i < list.size(); i++) {		//ѭ��������ѯ�����
					comboBox.addItem(list.get(i));		//�����������б������Ԫ��				
				}
				repaint();
			}
		});
		dIdcomboBox.setBounds(101, 33, 164, 21);
		contentPane.add(dIdcomboBox);
		
		JLabel wightlabel = new JLabel("������ ");
		wightlabel.setBounds(59, 76, 44, 15);
		contentPane.add(wightlabel);
		
		wighttextField = new JTextField();
		wighttextField.setBounds(101, 73, 164, 25);
		contentPane.add(wighttextField);
		wighttextField.setColumns(10);
		
		JLabel label = new JLabel("ǧ��");
		label.setBounds(275, 77, 31, 15);
		contentPane.add(label);
		
		label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setBounds(311, 77, 7, 15);
		contentPane.add(label_4);
		
		button = new JButton("����");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(button.getText().equals("���")){
					joinTimeTextField.setText("");
					button.setText("����");
				}
				else{GetDate getDate = new GetDate();
				String strDate = getDate.getDateTime();
				joinTimeTextField.setText(strDate);
				button.setText("���");
				}
			}
		});
		button.setBounds(566, 73, 71, 23);
		contentPane.add(button);	
		List listName = dao.selectOutDepotNames(Integer.parseInt(dIdcomboBox.getSelectedItem().toString()));
		String [] orderName = new String[listName.size()+1];
		orderName[0] = "";
		for(int j = 0;j<listName.size();j++){
			orderName[j+1] = listName.get(j).toString(); 
		}		
		comboBox = new JComboBox(orderName);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
		comboBox.setBounds(392, 33, 164, 21);
		contentPane.add(comboBox);
	}
	protected void do_closeButton_actionPerformed(ActionEvent arg0) {
		this.setVisible(false);
	}
}
