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
import com.mingrisoft.bean.Stock;
import com.mingrisoft.dao.FeelDao;
import com.mingrisoft.dao.SellDao;
import com.mingrisoft.dao.StockDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;
public class InserStockFrame extends JFrame {
	private JPanel contentPane;
	private JTextField orderIdTextField;
	private JTextField nameTextField;
	private JLabel dateLabel;
	private JTextField dateTextField;
	private JLabel wNameLabel;
	private JTextField wNameTextField;
	private JLabel countLabel;
	private JTextField countTextField;
	private JLabel moneyabel;
	private JTextField moneyTextField;
	private JButton closeButton;
	private JLabel starLabel;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;	
	/**
	 * Create the frame.
	 */
	public InserStockFrame() {
		setTitle("��Ӳɹ���������");	
		setBounds(100, 100, 635, 269);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel orderIdLabel = new JLabel("�����ţ�");
		orderIdLabel.setBounds(59, 55, 60, 15);
		contentPane.add(orderIdLabel);

		orderIdTextField = new JTextField();
		orderIdTextField.setBounds(114, 50, 164, 25);
		contentPane.add(orderIdTextField);
		orderIdTextField.setColumns(10);

		JLabel nameLabel = new JLabel(" ��  ����");
		nameLabel.setBounds(315, 55, 60, 15);
		contentPane.add(nameLabel);

		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(385, 50, 164, 25);
		contentPane.add(nameTextField);

		dateLabel = new JLabel(" �������ڣ�");
		dateLabel.setBounds(50, 99, 70, 15);
		contentPane.add(dateLabel);

		dateTextField = new JTextField();
		dateTextField.setBounds(114, 94, 164, 25);
		contentPane.add(dateTextField);
		dateTextField.setColumns(10);

		wNameLabel = new JLabel("�������ƣ�");
		wNameLabel.setBounds(315, 97, 72, 15);
		contentPane.add(wNameLabel);

		wNameTextField = new JTextField();
		wNameTextField.setBounds(385, 94, 164, 25);
		contentPane.add(wNameTextField);
		wNameTextField.setColumns(10);

		countLabel = new JLabel("  �� ����");
		countLabel.setBounds(50, 140, 54, 15);
		contentPane.add(countLabel);

		countTextField = new JTextField();
		countTextField.setColumns(10);
		countTextField.setBounds(114, 137, 164, 25);
		contentPane.add(countTextField);

		moneyabel = new JLabel("  ��  �");
		moneyabel.setBounds(315, 140, 60, 15);
		contentPane.add(moneyabel);

		moneyTextField = new JTextField();
		moneyTextField.setColumns(10);
		moneyTextField.setBounds(385, 137, 164, 25);
		contentPane.add(moneyTextField);
		
		
JButton insertButton = new JButton("���");
insertButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		StockDao dao = new StockDao();					//����������ݱ���
		String oId = orderIdTextField.getText();		//��ȡ�û���ӵĶ�����
		String wname = nameTextField.getText();			//��ȡ�û���ӵĿͻ�����
		String wDate = dateTextField.getText();			//��ȡ�û���ӵĽ�������
		String count = countTextField.getText();		//��ȡ�û���ӵ���Ʒ����
		String bName = wNameTextField.getText();		//��ȡ�û���ӵĻ�Ʒ����
		String money = moneyTextField.getText();		//��ȡ�û���ӵĻ�Ʒ���		
		int countIn = 0;
		float fmoney = 0;
		if((oId.equals(""))||(wname.equals("")) ||(wDate.equals("")) ||
			(count.equals("")) || (money.equals(""))){	//�ж��û���ӵ���Ϣ�Ƿ�����
			JOptionPane.showMessageDialog(getContentPane(), "�뽫���Ǻŵ�������д������",
					"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);		//������ʾ��Ϣ
			return;										//�˳�����
		}
		try{
			countIn	= Integer.parseInt(count);			//���û���ӵ�����ת��Ϊ����
			fmoney = Float.parseFloat(money);
		}catch (Exception ee) {
			JOptionPane.showMessageDialog(getContentPane(), "Ҫ�������֣�",
					"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
			return;	
		}
		Stock stock = new Stock();						//���������ݱ��Ӧ��JavaBean����
		stock.setsName(wname);							//���ö�������
		stock.setBaleName(bName);
		stock.setConsignmentDate(wDate);
		stock.setCount(count);
		stock.setMoney(fmoney);
		stock.setOrderId(oId);
		dao.insertStock(stock);							//�������ݿ���ӷ���
		JOptionPane.showMessageDialog(getContentPane(), "������ӳɹ���",
				"��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);			//��ʾ��Ϣ
	}
});
		insertButton.setBounds(176, 193, 93, 23);
		contentPane.add(insertButton);

		closeButton = new JButton("�˳�");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_closeButton_actionPerformed(e);
			}
		});
		closeButton.setBounds(315, 193, 93, 23);
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
		label_5.setBounds(559, 140, 18, 15);
		contentPane.add(label_5);
		
	}

	protected void do_closeButton_actionPerformed(ActionEvent arg0) {
		this.setVisible(false);
	}
}
