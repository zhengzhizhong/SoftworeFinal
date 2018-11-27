// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ClockFrame.java

package com.mingrisoft.jbutton;

import com.sun.awt.AWTUtilities;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;

// Referenced classes of package com.lzw:
//			ClockPanel

public class ClockFrame extends JDialog
{

	private float opqua;	
	public ClockFrame()
	{
		opqua = 0.7F;
		setUndecorated(true);
		setAlwaysOnTop(true);
		setTitle("明日科技石英钟");
		getContentPane().setLayout(new BorderLayout());
		setBounds(100, 100, 217, 257);
		setDefaultCloseOperation(1);
		ClockPanel clockPanel = new ClockPanel();
		addKeyListener(new KeyAdapter() {

			final ClockFrame this$0;

			public void keyPressed(KeyEvent e)
			{
				do_clockPanel_keyPressed(e);
			}

			
			{
				this$0 = ClockFrame.this;
//				super();
			}
		});
		getContentPane().add(clockPanel);
		pack();
		AWTUtilities.setWindowOpacity(this, opqua);
	}

	protected void do_clockPanel_keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();
		switch (code)
		{
		case 107: // 'k'
			opqua += 0.050000000000000003D;
			opqua = opqua <= 0.95F ? opqua : 1.0F;
			break;

		case 109: // 'm'
			opqua -= 0.050000000000000003D;
			opqua = opqua >= 0.1F ? opqua : 0.1F;
			break;
		}
		if (code == 88 && e.getModifiersEx() == 192)
			System.exit(0);
		AWTUtilities.setWindowOpacity(this, opqua);
	}
}
