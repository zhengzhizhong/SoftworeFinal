package com.mingrisoft.widget;

import java.awt.*;
import java.io.Serializable;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class CTable extends JTree {
    public CTable() {
    	  
    	  DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("������������");
          DefaultMutableTreeNode childNode1 = new DefaultMutableTreeNode("�����̹���");
          DefaultMutableTreeNode childNode2 = new DefaultMutableTreeNode("�����̹���");
          DefaultMutableTreeNode childNode3 = new DefaultMutableTreeNode("���õ�������");
          DefaultMutableTreeNode childNode4 = new DefaultMutableTreeNode("�ֿ����");
          DefaultMutableTreeNode childNode5 = new DefaultMutableTreeNode("��Ա����");
          rootNode.add(childNode1);
          rootNode.add(childNode2);
          rootNode.add(childNode3);
          rootNode.add(childNode4);
          rootNode.add(childNode5);
          
          this.setExpandedState(new TreePath(rootNode), true);
          this.expandRow(0);


    }    
 
    
    
}
