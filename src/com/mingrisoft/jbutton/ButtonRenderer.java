
package com.mingrisoft.jbutton;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/*������������ı任��ʱ�����Ǿ���Ҫ��ϵͳ��һЩ��ɫ����Ĭ�����ã������������任ʱ��
 * �������ɫ�Զ�ת������������Ӧ����ɫ��һ��Ч����UIManager���ⷽ����һ�ֺܹؼ���Ӧ�á�
   ��UIManager(Java 2 Platform SE v1.4.2)�ķ���public static Color getColor(Object key)ע���У�
   key��һ��ָ����ɫ�Ķ��󣬶����������Щ����ʹ���򲻵�֪��
    �����Ķ�jdk��Դ�������֪��������UIManager.getColor��ʱ��
    ��ʵ��ͨ������[return getDefaults().getColor(key); ]��δ�������ɫ�ġ�
    ��getDefaults() ������һ��UIDefaults��
    �ٿ���UIDefaults������ͨ��[Object value = get(key);]�������ɫ�Ķ���
    ͨ�������õ���Ͳ�Ĵ��룬����֪����
    ��Щ�����б��Ǳ�����һ��Hashtable��UIDefaults�̳���Hashtable���еģ�
    ��Hashtable�ṩ��һ���ӿ�[public synchronized Enumeration keys()]�������Щֵ��
    ��ˣ����ǿ���ͨ��һ�μ򵥵Ĵ�������ÿ���ʹ�õ���ɫ�Ĺؼ�����


*/
public class ButtonRenderer extends JButton implements TableCellRenderer {

  public ButtonRenderer() {
    setOpaque(true);
  }
  
  public Component getTableCellRendererComponent(JTable table, Object value,
                   boolean isSelected, boolean hasFocus, int row, int column) {
    if (isSelected) {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());//����ɫֵ
    } else{
      setForeground(table.getForeground());
      setBackground(UIManager.getColor("Button.background"));//����ɫֵ
    }
    setText( (value ==null) ? "" : value.toString() );
    return this;
  }
}
