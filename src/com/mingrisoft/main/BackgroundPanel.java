package com.mingrisoft.main;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * 背景面板
 * 
 * @author 
 */
public class BackgroundPanel extends JPanel {
    private Image image;// 背景图片    
    public BackgroundPanel() {
        setOpaque(false);
        setLayout(null);// 使用绝对定位布局控件
    }    
    /**
     * 设置背景图片对象的方法
     * 
     * @param image
     */
    public void setImage(Image image) {
        this.image = image;
    }    
    /**
     * 画出背景
     */
    protected void paintComponent(Graphics g) {
        if (image != null) {// 如果图片已经初始化
            // 画出图片
            g.drawImage(image, 0, 0, this);
        }
        super.paintComponent(g);
    }
}
