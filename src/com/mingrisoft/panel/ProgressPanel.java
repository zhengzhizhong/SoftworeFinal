package com.mingrisoft.panel;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ProgressPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JProgressBar jProgressBar = null;// 进度条
    private BufferedImage bgimage;// 背景图片
    private JLabel jLabel = null;// 标签控件
    
    /**
     * 构造方法
     */
    public ProgressPanel() {
        super();
        initialize();// 调用界面初始化方法
    }
    
    /**
     * 初始化登录进度面板界面
     */
    private void initialize() {
        GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        jLabel = new JLabel();// 初始化标签控件
        jLabel.setText("正在登录系统……");
        // 设置字体
        jLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        // 设置前景色
        jLabel.setForeground(new Color(0x28629e));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 50, 0, 50);
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.gridy = 1;
        // 设置布局管理器
        this.setLayout(new GridBagLayout());
        this.setSize(300, 200);// 设置界面大小
        // 设置前景色
        this.setForeground(Color.white);
        this.setOpaque(false);// 面板透明
        // 添加登录进度面板到面板
        this.add(getJProgressBar(), gridBagConstraints);
        // 添加登录信息标签到面板
        this.add(jLabel, gridBagConstraints2);
    }
    
    /**
     * 初始化进度条控件
     * 
     * @return javax.swing.JProgressBar
     */
    private JProgressBar getJProgressBar() {
        if (jProgressBar == null) {
            jProgressBar = new JProgressBar();
            // 设置进度条为不确定状态
            jProgressBar.setIndeterminate(true);
        }
        return jProgressBar;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        if (bgimage != null)// 如果图片对象已经初始化
            // 绘制图片到面板
            g2.drawImage(bgimage, 0, 0, this);
        g2.dispose();
        super.paintComponent(g);
    }
    
    /**
     * 设置背景图片对象
     * 
     * @param bimage
     *            图片对象参数
     */
    public void setBackImage(BufferedImage bimage) {
        this.bgimage = bimage;
    }
}
