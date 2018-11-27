package com.mingrisoft.main;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mingrisodft.util.Session;
import com.mingrisoft.bean.User;
import com.mingrisoft.dao.UserDao;
import com.mingrisoft.mainFrame.RemoveButtomFrame;
import com.mingrisoft.panel.ClockPanel;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.io.Serializable;
import java.net.URL;
public class Enter extends JFrame {
	private BackgroundPanel contentPane;
	private JTextField userNameTextField;
	private JPasswordField passwordField;
	private Point spoint;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							Enter mostly = new Enter();
							mostly.setVisible(true);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public Enter() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);// 窗体居中
		setTitle("登录窗体");
		setBounds(100, 100, 559, 285);
		contentPane =  getLoginPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	/**
	 * 初始化登录面板
	 * 
	 * @return 
	 */
	private BackgroundPanel getLoginPanel() {
		if (contentPane == null) {
			contentPane = new BackgroundPanel();// 创建登录面板对象		
			contentPane.setOpaque(false);// 面板透明
			contentPane.setImage(getToolkit().getImage(
		            getClass().getResource("login.png")));// 设置面板背景图片
			contentPane.setLayout(null);
			JPanel panel = new ClockPanel();
			panel.setBounds(377, 54, 151, 142);
			contentPane.add(panel);
			JLabel userNameLabel = new JLabel("用户名：");
			userNameLabel.setBounds(40, 116, 54, 15);
			contentPane.add(userNameLabel);
			userNameTextField = new JTextField();
			userNameTextField.setBounds(92, 113, 139, 25);
			contentPane.add(userNameTextField);
			userNameTextField.setColumns(10);
			JLabel passWordLabel = new JLabel("密  码：");
			passWordLabel.setBounds(40, 158, 54, 15);
			contentPane.add(passWordLabel);
			passwordField = new JPasswordField();
			passwordField.setBounds(92, 155, 139, 25);
			contentPane.add(passwordField);
			JButton enterButton = new JButton("");
			URL url = getClass().getResource("enter.png");
			ImageIcon imageIcon = new ImageIcon(url);	
			enterButton.setBounds(0, 40,imageIcon.getIconWidth(), imageIcon.getIconHeight());		
			enterButton.setIcon(imageIcon);
			enterButton.setContentAreaFilled(false);	// 取消填充区域
			enterButton.setBorder(null);				// 取消边框	
enterButton.addActionListener(new ActionListener() {		//按钮的单击事件
	public void actionPerformed(ActionEvent e) {
		UserDao userDao = new UserDao();					//创建保存有操作数据库类对象
		User user = userDao.getUser(userNameTextField.getText(),passwordField.getText());	//以用户添加的用户名与密码为参数调用查询用户方法
		if(user.getId()>0){					//判断用户编号是否大于0
			Session.setUser(user);			//设置Session对象的User属性值
			RemoveButtomFrame frame = new RemoveButtomFrame();		//创建主窗体对象
			frame.setVisible(true);			//显示主窗体
			 Enter.this.dispose();			//销毁登录窗体
		}
		else{								//如果用户输入的用户名与密码错误
			JOptionPane.showMessageDialog(getContentPane(), "用户名或密码错误");	//给出提示信息
			userNameTextField.setText("");		//用户名文本框设置为空
			passwordField.setText("");			//密码文本框设置为空
		}
	}
});
			enterButton.setBounds(253, 116, 93, 64);
			contentPane.add(enterButton);
			URL urlclose = getClass().getResource("close.png");
			ImageIcon imageIconclose = new ImageIcon(urlclose);
			// 添加鼠标事件监听器
			contentPane.addMouseListener(new TitleMouseAdapter());
			// 添加鼠标动作监听器
			
		}
		return contentPane;
	}

	private final class TitleMouseAdapter extends MouseAdapter implements
			Serializable {
		public void mousePressed(java.awt.event.MouseEvent e) {
			spoint = e.getPoint();
		}

	}

	
}
