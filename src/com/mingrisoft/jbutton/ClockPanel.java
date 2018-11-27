// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ClockPanel.java
package com.mingrisoft.jbutton;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.*;
public class ClockPanel extends JPanel implements Runnable
{
	private static final BasicStroke HOURS_POINT_WIDTH = new BasicStroke(4F);
	private static final BasicStroke MINUETES_POINT_WIDTH = new BasicStroke(3F);
	private static final BasicStroke SEC_POINT_WIDTH = new BasicStroke(2.0F);
	ImageIcon background;
	private int centerX;
	private int centerY;
	private static final int secLen = 60;
	private static final int minuesLen = 55;
	private static final int hoursLen = 36;
	private Point fp;
	public ClockPanel()
	{
		setToolTipText("小键盘+、-调整透明度，Crtl+Shift+X退出");
		setOpaque(false);
		addMouseListener(new MouseAdapter() {

			final ClockPanel this$0;

			public void mousePressed(MouseEvent e)
			{
				do_this_mousePressed(e);
			}	
			{
				this$0 = ClockPanel.this;
//				super();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			final ClockPanel this$0;
			public void mouseDragged(MouseEvent e)
			{
				do_this_mouseDragged(e);
			}			
			{
				this$0 = ClockPanel.this;
//				super();
			}
		});
		background = new ImageIcon(getClass().getResource("02.png"));
		int iconWidth = background.getIconWidth();
		centerX = iconWidth / 2;
		int iconHeight = background.getIconHeight();
		centerY = iconHeight / 2;
		setPreferredSize(new Dimension(iconWidth, iconHeight));
		(new Thread(this)).start();
	}
	public void paint(Graphics g)
	{
		Date date = new Date();
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(background.getImage(), 0, 0, this);
		int sec = date.getSeconds();
		int minutes = date.getMinutes();
		int hours = date.getHours();
		int secAngle = (60 - sec) * 6;
		int minutesAngle = (60 - minutes) * 6;
		int hoursAngle = ((12 - hours) * 360) / 12 - minutes / 2;
		int secX = (int)(60D * Math.sin(Math.toRadians(secAngle)));
		int secY = (int)(60D * Math.cos(Math.toRadians(secAngle)));
		int minutesX = (int)(55D * Math.sin(Math.toRadians(minutesAngle)));
		int minutesY = (int)(55D * Math.cos(Math.toRadians(minutesAngle)));
		int hoursX = (int)(36D * Math.sin(Math.toRadians(hoursAngle)));
		int hoursY = (int)(36D * Math.cos(Math.toRadians(hoursAngle)));
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(HOURS_POINT_WIDTH);
		g2.setColor(Color.BLACK);
		g2.drawLine(centerX, centerY, centerX - hoursX, centerY - hoursY);
		g2.setStroke(MINUETES_POINT_WIDTH);
		if (minutesAngle != hoursAngle)
			g2.setColor(new Color(0x2f2f2f));
		else
			g2.setColor(Color.GREEN);
		g2.drawLine(centerX, centerY, centerX - minutesX, centerY - minutesY);
		g2.setStroke(SEC_POINT_WIDTH);
		if (secAngle != hoursAngle && secAngle != minutesAngle)
			g2.setColor(Color.ORANGE);
		else
			g2.setColor(Color.GREEN);
		g2.fillOval(centerX - 5, centerY - 5, 10, 10);
		g2.drawLine(centerX, centerY, centerX - secX, centerY - secY);
	}
	public void run()
	{
		do
			try
			{
				Thread.sleep(500);
				repaint();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		while (true);
	}
	protected void do_this_mouseDragged(MouseEvent e)
	{
		JDialog frame = (JDialog)getRootPane().getParent();
		Point point = e.getLocationOnScreen();
		frame.setLocation(point.x - fp.x, point.y - fp.y);
	}
	protected void do_this_mousePressed(MouseEvent e)
	{
		fp = e.getPoint();
	}
}
