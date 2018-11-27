package com.mingrisoft.bean;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

import java.io.*;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class ExceptionTools {
    
    public static void showExceptionMessage(Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "¥ÌŒÛ–≈œ¢",
                ERROR_MESSAGE, null);
        try {
            FileOutputStream fout = new FileOutputStream(
                    "c:/lzwProjectException.log", true);
            PrintStream ps = new PrintStream(fout);
            DateFormat format = DateFormat.getDateTimeInstance(DateFormat.FULL,
                    DateFormat.FULL);
            String time = format.format(new Date());
            ps.println("\n*****" + time);
            ps
                    .println("---------------------------------------------------------");
            e.printStackTrace();
            e.printStackTrace(ps);
            ps.println();
            ps.close();
            fout.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e.printStackTrace();
        }
    }
}
