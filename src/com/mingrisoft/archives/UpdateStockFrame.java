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
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JComboBox;
public class UpdateStockFrame extends JFrame {
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
	private Stock stock;
	private StockDao dao = new StockDao();	
	/**
	 * Create the frame.
	 */
	public UpdateStockFrame() {
		setTitle("修改采购订单窗体");	
		setBounds(100, 100, 635, 269);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try {
			  File file = new File("filedd.txt");				//创建文件对象
	          FileInputStream fin = new FileInputStream(file);	//创建文件输入流对象
	          int count =  fin.read();      					//读取文件中数据
	          stock = dao.selectStockByid(count);				//调用按编号查询数据方法
	          file.delete();	         						//删除文件
	        } catch (Exception e) {	          
	            e.printStackTrace();
	    }	     
		JLabel orderIdLabel = new JLabel("订单号：");
		orderIdLabel.setBounds(59, 55, 60, 15);
		contentPane.add(orderIdLabel);
		orderIdTextField = new JTextField();					//创建文本框对象
		orderIdTextField.setText(stock.getOrderId());			//设置文本框对象内容
		orderIdTextField.setBounds(114, 50, 164, 25);
		contentPane.add(orderIdTextField);						//将文本框对象添加到面板中
		orderIdTextField.setColumns(10);	

		JLabel nameLabel = new JLabel(" 客  户：");
		nameLabel.setBounds(315, 55, 60, 15);
		contentPane.add(nameLabel);

		nameTextField = new JTextField();
		nameTextField.setText(stock.getsName());
		nameTextField.setColumns(10);
		nameTextField.setBounds(385, 50, 164, 25);
		contentPane.add(nameTextField);

		dateLabel = new JLabel(" 交货日期：");
		dateLabel.setBounds(50, 99, 70, 15);
		contentPane.add(dateLabel);

		dateTextField = new JTextField();
		dateTextField.setText(stock.getConsignmentDate());
		dateTextField.setBounds(114, 94, 164, 25);
		contentPane.add(dateTextField);
		dateTextField.setColumns(10);

		wNameLabel = new JLabel("货物名称：");
		wNameLabel.setBounds(315, 97, 72, 15);
		contentPane.add(wNameLabel);

		wNameTextField = new JTextField();
		wNameTextField.setText(stock.getBaleName());
		wNameTextField.setBounds(385, 94, 164, 25);
		contentPane.add(wNameTextField);
		wNameTextField.setColumns(10);

		countLabel = new JLabel("  数 量：");
		countLabel.setBounds(50, 140, 54, 15);
		contentPane.add(countLabel);

		countTextField = new JTextField();
		countTextField.setText(stock.getCount());
		countTextField.setColumns(10);
		countTextField.setBounds(114, 137, 164, 25);
		contentPane.add(countTextField);

		moneyabel = new JLabel("  金  额：");
		moneyabel.setBounds(315, 140, 60, 15);
		contentPane.add(moneyabel);

		moneyTextField = new JTextField();
		moneyTextField.setText(""+stock.getMoney());
		moneyTextField.setColumns(10);
		moneyTextField.setBounds(385, 137, 164, 25);
		contentPane.add(moneyTextField);	
	
		JButton insertButton = new JButton("修改");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StockDao dao = new StockDao();					//创建保存有修改方法的类对象
				String oId = orderIdTextField.getText();		//获取用户填写订单数据
				String wname = nameTextField.getText();			//获取用户填写的客户名信息
				String wDate = dateTextField.getText();			//获取用户填写的交货日期信息
				String count = countTextField.getText();		
				String bName = wNameTextField.getText();
				String money = moneyTextField.getText();			
				int countIn = 0;
				float fmoney = 0;
				if((oId.equals(""))||(wname.equals("")) ||(wDate.equals("")) ||	
					(count.equals("")) || (money.equals(""))){	//判断用户是否将信息添加完整
					JOptionPane.showMessageDialog(getContentPane(), "请将带星号的内容填写完整！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);	//给出提示信息
					return;					
				}
				try{
					countIn	= Integer.parseInt(count);			//将用户填写的数量转换为整数
					fmoney = Float.parseFloat(money);
				}catch (Exception ee) {
					JOptionPane.showMessageDialog(getContentPane(), "要输入数字！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);	//如果有异常抛出给出提示信息
					return;	
				}
				stock.setsName(wname);							//将设置采购订货信息属性
				stock.setBaleName(bName);
				stock.setConsignmentDate(wDate);
				stock.setCount(count);
				stock.setMoney(fmoney);
				stock.setOrderId(oId);
				dao.updateStock(stock);							//调用修改信息方法
				JOptionPane.showMessageDialog(getContentPane(), "数据添加成功！",
						"信息提示框", JOptionPane.INFORMATION_MESSAGE);
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
