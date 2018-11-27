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
		setLocationRelativeTo(null);// �������
		setTitle("��¼����");
		setBounds(100, 100, 559, 285);
		contentPane =  getLoginPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	/**
	 * ��ʼ����¼���
	 * 
	 * @return 
	 */
	private BackgroundPanel getLoginPanel() {
		if (contentPane == null) {
			contentPane = new BackgroundPanel();// ������¼������		
			contentPane.setOpaque(false);// ���͸��
			contentPane.setImage(getToolkit().getImage(
		            getClass().getResource("login.png")));// ������屳��ͼƬ
			contentPane.setLayout(null);
			JPanel panel = new ClockPanel();
			panel.setBounds(377, 54, 151, 142);
			contentPane.add(panel);
			JLabel userNameLabel = new JLabel("�û�����");
			userNameLabel.setBounds(40, 116, 54, 15);
			contentPane.add(userNameLabel);
			userNameTextField = new JTextField();
			userNameTextField.setBounds(92, 113, 139, 25);
			contentPane.add(userNameTextField);
			userNameTextField.setColumns(10);
			JLabel passWordLabel = new JLabel("��  �룺");
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
			enterButton.setContentAreaFilled(false);	// ȡ���������
			enterButton.setBorder(null);				// ȡ���߿�	
enterButton.addActionListener(new ActionListener() {		//��ť�ĵ����¼�
	public void actionPerformed(ActionEvent e) {
		UserDao userDao = new UserDao();					//���������в������ݿ������
		User user = userDao.getUser(userNameTextField.getText(),passwordField.getText());	//���û���ӵ��û���������Ϊ�������ò�ѯ�û�����
		if(user.getId()>0){					//�ж��û�����Ƿ����0
			Session.setUser(user);			//����Session�����User����ֵ
			RemoveButtomFrame frame = new RemoveButtomFrame();		//�������������
			frame.setVisible(true);			//��ʾ������
			 Enter.this.dispose();			//���ٵ�¼����
		}
		else{								//����û�������û������������
			JOptionPane.showMessageDialog(getContentPane(), "�û������������");	//������ʾ��Ϣ
			userNameTextField.setText("");		//�û����ı�������Ϊ��
			passwordField.setText("");			//�����ı�������Ϊ��
		}
	}
});
			enterButton.setBounds(253, 116, 93, 64);
			contentPane.add(enterButton);
			URL urlclose = getClass().getResource("close.png");
			ImageIcon imageIconclose = new ImageIcon(urlclose);
			// �������¼�������
			contentPane.addMouseListener(new TitleMouseAdapter());
			// �����궯��������
			
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
