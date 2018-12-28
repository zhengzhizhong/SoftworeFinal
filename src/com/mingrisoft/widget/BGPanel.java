package com.mingrisoft.widget;

import java.awt.*;
import java.io.Serializable;

import javax.swing.*;

//给图片拍好序列
public class BGPanel extends JPanel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private ImageIcon icon; // @jve:decl-index=0:
    public static final int HORIZONGTAL_FILL = 1;
    public static final int VERTICAL_FILL = 2;
    public static final int BOTH_FILL = 3;
    public static final int NO_FILL = 0;
    private int iconFill = NO_FILL;

    public BGPanel() {
        super();
        initialize();
    }

    private void initialize() {
        this.setSize(new Dimension(300, 200));//shezhi高度 宽度
        this.setLayout(new GridBagLayout());
    }
    
    public ImageIcon getIcon() {
        return icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (icon != null) {
            switch (iconFill) {
                case NO_FILL:
                    g.drawImage(icon.getImage(), 0, 0, this);
                    break;
                case HORIZONGTAL_FILL:
                    g.drawImage(icon.getImage(), 0, 0, getWidth(), icon
                            .getIconHeight(), this);
                    break;
                case VERTICAL_FILL:
                    g.drawImage(icon.getImage(), 0, 0, icon.getIconWidth(),
                            getHeight(), this);
                    break;
                case BOTH_FILL:
                    g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(),
                            this);
                    break;
                default:
                    break;
            }
        }
    }
    
    public int getIconFill() {
        return iconFill;
    }

    public void setIconFill(int iconFill) {
        this.iconFill = iconFill;
    }
    
}
