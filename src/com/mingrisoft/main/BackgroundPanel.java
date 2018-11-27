package com.mingrisoft.main;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * �������
 * 
 * @author 
 */
public class BackgroundPanel extends JPanel {
    private Image image;// ����ͼƬ    
    public BackgroundPanel() {
        setOpaque(false);
        setLayout(null);// ʹ�þ��Զ�λ���ֿؼ�
    }    
    /**
     * ���ñ���ͼƬ����ķ���
     * 
     * @param image
     */
    public void setImage(Image image) {
        this.image = image;
    }    
    /**
     * ��������
     */
    protected void paintComponent(Graphics g) {
        if (image != null) {// ���ͼƬ�Ѿ���ʼ��
            // ����ͼƬ
            g.drawImage(image, 0, 0, this);
        }
        super.paintComponent(g);
    }
}
