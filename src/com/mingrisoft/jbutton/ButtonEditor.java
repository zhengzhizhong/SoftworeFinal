package com.mingrisoft.jbutton;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.mingrisodft.util.GetDate;
import com.mingrisoft.bean.Depot;
import com.mingrisoft.bean.JoinDepot;
import com.mingrisoft.bean.Stock;
import com.mingrisoft.dao.DepotDao;
import com.mingrisoft.dao.JoinDepotDao;
import com.mingrisoft.dao.StockDao;
import com.mingrisoft.mainFrame.RemoveButtomFrame;

import java.io.*;
public class ButtonEditor extends DefaultCellEditor {
  protected JButton button;
  private String    label;
  private boolean   isPushed;
  private StockDao dao = new StockDao();
  public ButtonEditor(JCheckBox checkBox) {
    super(checkBox);
    button = new JButton();
    button.setOpaque(true);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	  int did = 0 ;
    	 
    		 try {
    			 if(button.getText().equals("入库")){
    			 DepotDao daoA = new DepotDao();    			
    			 String[] options = new String[] { "1号", "2号", "3号" };
 			    String message = "请选择仓库？";// 对话框中的信息
 			    int num = JOptionPane.showOptionDialog(new RemoveButtomFrame(), message, "选择仓库",
 			            JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
 			            null, options, "1号");// 显示自定义对话框
 			    if (options[num].equals("1号")) {
 			       did = 1;
 			    } else if (options[num].equals("2号"))  {
 			    	 did = 2;
 			    }
 			    else if (options[num].equals("3号"))  {
			    	 did = 3;
			    }
   			  File file = new File("files.txt");
   	            FileInputStream fin = new FileInputStream(file);
   	            int id =  fin.read();    
   	            Stock stock = dao.selectStockByid(id);
   	            JoinDepot joinDepot = new JoinDepot();
   	            joinDepot.setoId(stock.getOrderId());
   	            System.out.println("did:"+did);
   	            joinDepot.setdId(did);
   	            joinDepot.setWareName(stock.getsName());
   	            GetDate getDate = new GetDate();
   	            String date = getDate.getDateTime();
   	            joinDepot.setJoinTime(date);
   	            System.out.println();
	            joinDepot.setWeight(Float.parseFloat(stock.getCount()));
   	            joinDepot.setRemark("无");
   	            JoinDepotDao jDao = new JoinDepotDao();
   	            jDao.insertJoinDepot(joinDepot);
   	            file.delete();
    			 }
    			 else{
   	            JOptionPane.showMessageDialog(button ,"商品已入库");   	            
    			 }
   	        } catch (Exception ee) {	          
   	           ee.printStackTrace();
   	    }
      
      }
    });
  }

  public Component getTableCellEditorComponent(JTable table, Object value,
                   boolean isSelected, int row, int column) {
    if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
    } else{
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }
    label = (value ==null) ? "" : value.toString();
    button.setText( label );
    isPushed = true;
    return button;
  }

  public Object getCellEditorValue() {
    if (isPushed)  {
      // 
      // 
      JOptionPane.showMessageDialog(button ,label + ": Ouch!");
      // System.out.println(label + ": Ouch!");
    }
    isPushed = false;
    return new String( label ) ;
  }
  
  public boolean stopCellEditing() {
    isPushed = false;
    return super.stopCellEditing();
  }

  protected void fireEditingStopped() {
    super.fireEditingStopped();
  }
}

