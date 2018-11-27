package com.mingrisoft.mainFrame;
import static javax.swing.BorderFactory.createTitledBorder;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import com.mingrisodft.util.Session;
import com.mingrisoft.bean.User;
import com.mingrisoft.panel.ClockPanel;
import com.mingrisoft.panel.DepotPanel;
import com.mingrisoft.panel.DeptPanel;
import com.mingrisoft.panel.FeelWarePanel;
import com.mingrisoft.panel.JoinDepotPanel;
import com.mingrisoft.panel.MyJPanel;
import com.mingrisoft.panel.OutDepotPanel;
import com.mingrisoft.panel.PersonnelPanel;
import com.mingrisoft.panel.SellPanel;
import com.mingrisoft.panel.StockPanel;
import com.mingrisoft.panel.WarePanel;
import com.mingrisoft.widget.BGPanel;
import com.mingrisoft.widget.CalendarPanel;
import com.mingrisoft.widget.SmallScrollPanel;
import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import com.mingrisoft.widget.GlassButton;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class RemoveButtomFrame extends JFrame {
	private MyJPanel contentPane;
	private BGPanel backPanel;
	private SmallScrollPanel moduleButtonGroup = null;
	private JTree tree;
	private JPanel panel;
	FeelWarePanel panelFeel = new FeelWarePanel();
	JPanel panel_1 = new JPanel();
	JLabel fristLabel = new JLabel("������������");
	private BGPanel jPanel = null;
	private ButtonGroup buttonGroup = null;
	private GlassButton workSpaceButton = null;
	private GlassButton progressButton = null;
	private GlassButton bookProjectButton = null;
	private GlassButton chukuButton = null;
	private GlassButton personnelManagerButton = null;
	private GlassButton deptManagerButton = null;
	JLabel label_1 = new JLabel("����ǰ��λ���ǣ�");
	/**
	 * Create the frame.
	 */
	public RemoveButtomFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 934, 625);
		contentPane = new MyJPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.add(getModuleButtonGroup());
		setTitle("���й���ϵͳ");
		contentPane.setLayout(null);
		setResizable(false);
		JPanel clockpanel = new JPanel();
		clockpanel.setBackground(new Color(71,201,223));
		clockpanel.setBounds(10, 124, 258, 245);
		contentPane.add(clockpanel);
		clockpanel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 210, 276, 1);
		clockpanel.add(panel_1);
		panel_1.setLayout(null);
User user =	Session.getUser();				//��ȡ��¼�û�����		
String info = "<html><body>" + "<font color=#FFFFFF>�� �ã�</font>"
		+ "<font color=yellow><b>" + user.getUserName() + "</b></font>"
		+ "<font color=#FFFFFF>                �� ӭ �� ¼</font>" + "</body></html>";	//���崰����ʾ����
clockpanel.add(getPanel());
JLabel label = new JLabel(info);			//������ʾָ�����ݵı�ǩ����
		label.setBackground(Color.yellow);
		label.setBounds(45, 210, 128, 35);
		clockpanel.add(label);

		CalendarPanel panel_2 = new CalendarPanel();
		panel_2.setBounds(10, 370, 258, 207);
		contentPane.add(panel_2);
		contentPane.add(getContentPanel()); // �������������
		
	}

	private BGPanel getContentPanel() {
		if (backPanel == null) {
			backPanel = new BGPanel();
			backPanel.setBackground(new Color(71,201,223));
			backPanel.setSize(629, 416); // ������ʾ�������
			backPanel.setLocation(279, 149);
			backPanel.setLayout(null);

			label_1.setHorizontalAlignment(SwingConstants.RIGHT);
			label_1.setVerticalAlignment(SwingConstants.BOTTOM);
			label_1.setBounds(38, 38, 96, 15);
			backPanel
					.setBorder(createTitledBorder(null, "������������",
							TitledBorder.DEFAULT_JUSTIFICATION,
							TitledBorder.TOP, new Font("sansserif", Font.BOLD,
									12), new Color(59, 59, 59)));
			backPanel.add(label_1);
			fristLabel.setBounds(133, 38, 123, 15);
			backPanel.add(fristLabel);
			panel_1.setBounds(10, 63, 611, 343);
			panel_1.setLayout(null);
			backPanel.add(panel_1);
			JScrollPane scrollPane = new JScrollPane();
			panel_1.add(scrollPane);
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBackground(new Color(71,201,223));
			scrollPane_1.setBounds(0, 0, 138, 334);
			panel_1.add(scrollPane_1);
			DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(
					"������������");
			DefaultMutableTreeNode childNode1 = new DefaultMutableTreeNode(
					"�����̹���");
			DefaultMutableTreeNode childNode2 = new DefaultMutableTreeNode(
					"�����̹���");
			DefaultMutableTreeNode childNode3 = new DefaultMutableTreeNode(
					"��Ʒ��������");
			DefaultMutableTreeNode childNode4 = new DefaultMutableTreeNode(
					"�ֿ����");
			rootNode.add(childNode1);
			rootNode.add(childNode2);
			rootNode.add(childNode3);
			rootNode.add(childNode4);
			tree = new JTree(rootNode);
			scrollPane_1.setColumnHeaderView(tree);
			final JPanel sellPanel = new JPanel();
			sellPanel.setBackground(new Color(71,201,223));
			sellPanel.setBounds(138, 0, 473, 343);
			sellPanel.setLayout(null);
			panel_1.add(sellPanel);
		
			tree.getSelectionModel().setSelectionMode(
					TreeSelectionModel.SINGLE_TREE_SELECTION);
			TreeSelectionModel treeSelectionModel = tree.getSelectionModel();
			treeSelectionModel
					.setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
			tree.addTreeSelectionListener(new TreeSelectionListener() {
				@Override
				public void valueChanged(TreeSelectionEvent e) {
					if (!tree.isSelectionEmpty()) {
						TreePath selectionPaths = tree.getSelectionPath();
						Object path = selectionPaths.getLastPathComponent();
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) path;
						String userObject = (String) node.getUserObject();
						repaint();
						if (userObject.equals("�����̹���")) {
							fristLabel.setText("�����̹���");
							sellPanel.removeAll();
							sellPanel.add(panelFeel.getMessage());

						} else if (userObject.equals("�����̹���")) {
							fristLabel.setText("�����̹���");
							sellPanel.removeAll();
							SellPanel sell = new SellPanel();
							sellPanel.add(sell.getMessage());
							repaint();
						} else if (userObject.equals("��Ʒ��������")) {
							fristLabel.setText("��Ʒ��������");
							sellPanel.removeAll();
							WarePanel warePanel = new WarePanel();
							sellPanel.add(warePanel.getMessage());
							repaint();
						} else if (userObject.equals("�ֿ����")) {
							fristLabel.setText("�ֿ����");
							sellPanel.removeAll();
							DepotPanel depotPanel = new DepotPanel();
							sellPanel.add(depotPanel.getMessage());
							repaint();
						}
					}
				}
			});

		}
		return backPanel;
	}

	private SmallScrollPanel getModuleButtonGroup() {
		if (moduleButtonGroup == null) {
			moduleButtonGroup = new SmallScrollPanel();// �����ƶ����
			moduleButtonGroup.setBounds(250, 20, 434, 68);
			moduleButtonGroup.setOpaque(false);
			// ����ť�������Ϊ�ƶ�������ͼ
			moduleButtonGroup.setViewportView(getJPanel());
			moduleButtonGroup.getAlphaScrollPanel()
					.setViewportView(getJPanel());
			// �������¼�������
		}
		return moduleButtonGroup;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new ClockPanel();
			panel.setBounds(56, 35, 153, 148);
		}
		return panel;
	}

public BGPanel getJPanel() {
	if (jPanel == null) {
		GridLayout gridLayout = new GridLayout();	//�������񲼾ֹ�����
		gridLayout.setRows(1);						//�������񲼾ֹ�����������
		gridLayout.setHgap(0);						//���������ˮƽ���
		gridLayout.setVgap(0);						//��������䴹ֱ���
		jPanel = new BGPanel();						//
		// ���ò��ֹ�����
		jPanel.setLayout(gridLayout);
		// ���ó�ʼ��С
		jPanel.setPreferredSize(new Dimension(400, 50));
		jPanel.setOpaque(false);
		// ��Ӱ�ť
		jPanel.add(getWorkSpaceButton(), null);
		jPanel.add(getProgressButton(), null);
		jPanel.add(getrukuButton(), null);
		jPanel.add(getchukuButton(), null);
		jPanel.add(getPersonnelManagerButton(), null);
		jPanel.add(getDeptManagerButton(), null);
		if (buttonGroup == null) {
			buttonGroup = new ButtonGroup();
		}
		// �����а�ť��ӵ�һ����ؼ���
		buttonGroup.add(getProgressButton());
		buttonGroup.add(getWorkSpaceButton());
		buttonGroup.add(getrukuButton());
		buttonGroup.add(getchukuButton());
		buttonGroup.add(getPersonnelManagerButton());
		buttonGroup.add(getDeptManagerButton());
	}
	return jPanel;
}

	// ������������ť
private GlassButton getWorkSpaceButton() {
	if (workSpaceButton == null) {
		workSpaceButton = new GlassButton();
		workSpaceButton.setActionCommand("������������");		//���ð�ť�Ķ�������
		workSpaceButton.setIcon(new ImageIcon(getClass().getResource(
				"/com/mingrisoft/frame/buttonIcons/myWorkSpace.png")));	//���尴ť�ĳ�ʼ������
		ImageIcon icon = new ImageIcon(getClass().getResource(
				"/com/mingrisoft/frame/buttonIcons/myWorkSpace2.png"));	//����ͼƬ����
		workSpaceButton.setRolloverIcon(icon);		//���ð�ť�ķ�תͼƬ
		workSpaceButton.setSelectedIcon(icon);		//���ð�ť��ѡ��ʱ��ʾͼƬ
		workSpaceButton.setSelected(true);			
		workSpaceButton.addActionListener(new toolsButtonActionAdapter());	//��ť�ļ�����
	}
	return workSpaceButton;
}

	// �ɹ���������ť
	private GlassButton getProgressButton() {
		if (progressButton == null) {
			progressButton = new GlassButton();
			progressButton.setActionCommand("�ɹ�����");
			progressButton.setText("");
			progressButton.setIcon(new ImageIcon(getClass().getResource(
					"/com/mingrisoft/frame/buttonIcons/caigou1.png")));
			ImageIcon icon = new ImageIcon(getClass().getResource(
					"/com/mingrisoft/frame/buttonIcons/caigou2.png"));
			progressButton.setRolloverIcon(icon);
			progressButton.setSelectedIcon(icon);
			progressButton.addActionListener(new toolsButtonActionAdapter());
		}
		return progressButton;
	}

	// �ֿ����
	private GlassButton getrukuButton() {
		if (bookProjectButton == null) {
			bookProjectButton = new GlassButton();
			bookProjectButton.setActionCommand("�ֿ����");
			// bookProjectButton.setText("ͼ��ƻ�");
			ImageIcon icon = new ImageIcon(getClass().getResource(
					"/com/mingrisoft/frame/buttonIcons/ruku2.png"));
			bookProjectButton.setSelectedIcon(icon);
			bookProjectButton.setRolloverIcon(icon);
			bookProjectButton.setIcon(new ImageIcon(getClass().getResource(
					"/com/mingrisoft/frame/buttonIcons/ruku1.png")));
			bookProjectButton.addActionListener(new toolsButtonActionAdapter());
		}
		return bookProjectButton;
	}

	// �ֿ�������
	private GlassButton getchukuButton() {
		if (chukuButton == null) {
			chukuButton = new GlassButton();
			chukuButton.setActionCommand("�ֿ����");
			ImageIcon icon = new ImageIcon(getClass().getResource(
					"/com/mingrisoft/frame/buttonIcons/chuku1.png"));
			chukuButton.setSelectedIcon(icon);
			chukuButton.setRolloverIcon(icon);
			chukuButton.setIcon(new ImageIcon(getClass().getResource(
					"/com/mingrisoft/frame/buttonIcons/chuku2.png")));
			chukuButton.addActionListener(new toolsButtonActionAdapter());
		}
		return chukuButton;
	}

	// ��Ա����ϵͳ
	private GlassButton getPersonnelManagerButton() {
		if (personnelManagerButton == null) {
			personnelManagerButton = new GlassButton();
			// personnelManagerButton.setText("��Ա����");
			personnelManagerButton.setActionCommand("��ѯ��ͳ��ϵͳ");
			// personnelManagerButton.setCursor(new
			// Cursor(Cursor.DEFAULT_CURSOR));
			ImageIcon imageIcon = new ImageIcon(getClass().getResource(
					"/com/mingrisoft/frame/buttonIcons/person2.png"));
			personnelManagerButton.setIcon(imageIcon);
			ImageIcon icon = new ImageIcon(getClass().getResource(
					"/com/mingrisoft/frame/buttonIcons/person1.png"));
			personnelManagerButton.setRolloverIcon(icon);
			personnelManagerButton.setSelectedIcon(icon);
			personnelManagerButton.setFocusPainted(false);
			personnelManagerButton
					.addActionListener(new toolsButtonActionAdapter());
		}
		return personnelManagerButton;
	}

	// ���Ź���ϵͳ
	private GlassButton getDeptManagerButton() {
		if (deptManagerButton == null) {
			deptManagerButton = new GlassButton();

			deptManagerButton.setActionCommand("��ѯ��ͳ��ϵͳ");
			// personnelManagerButton.setCursor(new
			// Cursor(Cursor.DEFAULT_CURSOR));
			ImageIcon imageIcon = new ImageIcon(getClass().getResource(
					"/com/mingrisoft/frame/buttonIcons/deptButton.png"));
			deptManagerButton.setIcon(imageIcon);
			ImageIcon icon = new ImageIcon(getClass().getResource(
					"/com/mingrisoft/frame/buttonIcons/deptButton2.png"));
			deptManagerButton.setRolloverIcon(icon);
			deptManagerButton.setSelectedIcon(icon);
			deptManagerButton.setFocusPainted(false);
			deptManagerButton.addActionListener(new toolsButtonActionAdapter());
		}
		return deptManagerButton;
	}

	class toolsButtonActionAdapter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == workSpaceButton) {
				backPanel.removeAll();
				backPanel.add(label_1);
				fristLabel.setBounds(133, 38, 123, 15);
				backPanel.add(fristLabel);
				panel_1.setBounds(10, 63, 611, 376);
				backPanel.add(panel_1);
				fristLabel.setText("������������");
				repaint();
			}
			if (e.getSource() == progressButton) {
				backPanel.removeAll();
				backPanel.add(label_1);
				fristLabel.setBounds(133, 38, 123, 15);
				backPanel.add(fristLabel);
				panel_1.setBounds(10, 63, 611, 386);
				StockPanel stockPanl = new StockPanel();
				fristLabel.setText("�ɹ�����");
				backPanel.add(stockPanl);
				repaint();
			}
			if (e.getSource() == bookProjectButton) {
				backPanel.removeAll();
				backPanel.add(label_1);
				fristLabel.setBounds(133, 38, 123, 15);
				backPanel.add(fristLabel);
				panel_1.setBounds(10, 63, 611, 386);
				JoinDepotPanel joinPanel = new JoinDepotPanel();
				backPanel.add(joinPanel);
				fristLabel.setText("�ֿ����");
				repaint();
			}
			if (e.getSource() == chukuButton) {
				backPanel.removeAll();
				backPanel.add(label_1);
				fristLabel.setBounds(133, 38, 123, 15);
				backPanel.add(fristLabel);
				panel_1.setBounds(10, 63, 611, 386);
				OutDepotPanel outPanel = new OutDepotPanel();
				backPanel.add(outPanel);
				fristLabel.setText("�ֿ����");
				repaint();
			}
			if (e.getSource() == deptManagerButton) {
				backPanel.removeAll();
				backPanel.add(label_1);
				fristLabel.setBounds(133, 38, 123, 15);
				backPanel.add(fristLabel);
				panel_1.setBounds(10, 63, 611, 386);
				DeptPanel outPanel = new DeptPanel();
				backPanel.add(outPanel);
				fristLabel.setText("���Ź���");
				repaint();
			}
			if (e.getSource() == personnelManagerButton) {
				backPanel.removeAll();			
				panel_1.setBounds(10, 63, 611, 386);
				PersonnelPanel panel = new PersonnelPanel();
				backPanel.add(panel);				
				repaint();
			}
		}

	}

}
