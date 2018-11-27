package com.mingrisoft.panel;

import java.awt.*;

import java.net.*;


import javax.swing.*;
public class MyJPanel extends JPanel {

public void	paintComponent(Graphics g){
	try{ 
		URL url = getClass().getResource("/com/mingrisoft/frame/buttonIcons/back.jpg");
		ImageIcon image = new ImageIcon(url);
		g.drawImage(image.getImage(), 0, 0, this); 
		}catch(Exception e){} 
	}
}
