package com.mingrisoft.widget;

import java.awt.*;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.Serializable;

import javax.swing.*;


public class GlassButton extends JToggleButton implements Serializable {
    
    private final class MouseRollListener extends MouseAdapter implements
            Serializable {
        @Override
        public void mouseExited(MouseEvent e) {
            paintFlag = false;
            repaint();
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            paintFlag = true;
            repaint();
        }
    }
    
    private boolean paintFlag = false;
    
    public GlassButton() {
        addMouseListener(new MouseRollListener());
        initialize();
    }
    
    /**
     * This method initializes this
     */
    private void initialize() {
        this.setBorderPainted(false);
        this.setSize(new Dimension(168, 136));
        this.setContentAreaFilled(false);
        this.setOpaque(false);
        this.setBorder(null);
        this.setBorderPainted(false);
        this.setVerticalAlignment(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setIconTextGap(0);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        super.paint(g2);
        Rectangle bs = g2.getClipBounds();
        if (paintFlag || isSelected()) {
            Point2D center = new Point2D.Float(bs.width / 2, bs.height / 2);
            float radius = Math.min(bs.height / 2, bs.width / 2);
            Point2D focus = new Point2D.Float(bs.width / 2f, bs.height / 2f);
            float[] dist = { 0f, 0.8f };
            Color[] colors = { Color.WHITE, new Color(255, 255, 255, 0) };
            if (radius > 0) {
                RadialGradientPaint p = new RadialGradientPaint(center, radius,
                        focus, dist, colors, CycleMethod.NO_CYCLE);
                g2.setPaint(p);
                g2.fillRect(0, 0, bs.width, bs.height);
            }
        }
    }
} // @jve:decl-index=0:visual-constraint="10,10"