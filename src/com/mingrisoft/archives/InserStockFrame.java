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
		setTitle("添加采购订货窗体");	
		setBounds(100, 100, 635, 269);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel orderIdLabel = new JLabel("订单号：");
		orderIdLabel.setBounds(59, 55, 60, 15);
		contentPane.add(orderIdLabel);

		orderIdTextField = new JTextField();
		orderIdTextField.setBounds(114, 50, 164, 25);
		contentPane.add(orderIdTextField);
		orderIdTextField.setColumns(10);

		JLabel nameLabel = new JLabel(" 客  户：");
		nameLabel.setBounds(315, 55, 60, 15);
		contentPane.add(nameLabel);

		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(385, 50, 164, 25);
		contentPane.add(nameTextField);

		dateLabel = new JLabel(" 交货日期：");
		dateLabel.setBounds(50, 99, 70, 15);
		contentPane.add(dateLabel);

		dateTextField = new JTextField();
		dateTextField.setBounds(114, 94, 164, 25);
		contentPane.add(dateTextField);
		dateTextField.setColumns(10);

		wNameLabel = new JLabel("货物名称：");
		wNameLabel.setBounds(315, 97, 72, 15);
		contentPane.add(wNameLabel);

		wNameTextField = new JTextField();
		wNameTextField.setBounds(385, 94, 164, 25);
		contentPane.add(wNameTextField);
		wNameTextField.setColumns(10);

		countLabel = new JLabel("  数 量：");
		countLabel.setBounds(50, 140, 54, 15);
		contentPane.add(countLabel);

		countTextField = new JTextField();
		countTextField.setColumns(10);
		countTextField.setBounds(114, 137, 164, 25);
		contentPane.add(countTextField);

		moneyabel = new JLabel("  金  额：");
		moneyabel.setBounds(315, 140, 60, 15);
		contentPane.add(moneyabel);

		moneyTextField = new JTextField();
		moneyTextField.setColumns(10);
		moneyTextField.setBounds(385, 137, 164, 25);
		contentPane.add(moneyTextField);
		
		
JButton insertButton = new JButton("添加");
insertButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		StockDao dao = new StockDao();					//定义操作数据表方法
		String oId = orderIdTextField.getText();		//获取用户添加的订单号
		String wname = nameTextField.getText();			//获取用户添加的客户名称
		String wDate = dateTextField.getText();			//获取用户添加的交货日期
		String count = countTextField.getText();		//获取用户添加的商品数量
		String bName = wNameTextField.getText();		//获取用户添加的货品名称
		String money = moneyTextField.getText();		//获取用户添加的货品金额		
		int countIn = 0;
		float fmoney = 0;
		if((oId.equals(""))||(wname.equals("")) ||(wDate.equals("")) ||
			(count.equals("")) || (money.equals(""))){	//判断用户添加的信息是否完整
			JOptionPane.showMessageDialog(getContentPane(), "请将带星号的内容填写完整！",
					"信息提示框", JOptionPane.INFORMATION_MESSAGE);		//给出提示信息
			return;										//退出程序
		}
		try{
			countIn	= Integer.parseInt(count);			//将用户添加的数量转换为整型
			fmoney = Float.parseFloat(money);
		}catch (Exception ee) {
			JOptionPane.showMessageDialog(getContentPane(), "要输入数字！",
					"信息提示框", JOptionPane.INFORMATION_MESSAGE);
			return;	
		}
		Stock stock = new Stock();						//定义与数据表对应的JavaBean对象
		stock.setsName(wname);							//设置对象属性
		stock.setBaleName(bName);
		stock.setConsignmentDate(wDate);
		stock.setCount(count);
		stock.setMoney(fmoney);
		stock.setOrderId(oId);
		dao.insertStock(stock);							//调用数据库添加方法
		JOptionPane.showMessageDialog(getContentPane(), "数据添加成功！",
				"信息提示框", JOptionPane.INFORMATION_MESSAGE);			//提示信息
	}
});
		insertButton.setBounds(176, 193, 93, 23);
		contentPane.add(insertButton);

		closeButton = new JButton("退出");
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
