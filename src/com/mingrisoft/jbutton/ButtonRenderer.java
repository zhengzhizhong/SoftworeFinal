
package com.mingrisoft.jbutton;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/*在做界面主题的变换的时候，我们经常要对系统的一些颜色进行默认设置，而获得在主题变换时，
 * 组件的颜色自动转换到该主题相应的颜色的一种效果。UIManager在这方面是一种很关键的应用。
   在UIManager(Java 2 Platform SE v1.4.2)的方法public static Color getColor(Object key)注释中，
   key是一个指定颜色的对象，而具体包括哪些可以使用则不得知。
    我们阅读jdk的源代码可以知道，运行UIManager.getColor的时候，
    其实是通过运行[return getDefaults().getColor(key); ]这段代码获得颜色的。
    而getDefaults() 返回了一个UIDefaults。
    再看看UIDefaults，它是通过[Object value = get(key);]来获得颜色的对象，
    通过它调用的最低层的代码，我们知道，
    这些对象列表是保存在一个Hashtable（UIDefaults继承了Hashtable）中的，
    而Hashtable提供了一个接口[public synchronized Enumeration keys()]来获得这些值。
    因此，我们可以通过一段简单的代码来获得可以使用的颜色的关键对象。


*/
public class ButtonRenderer extends JButton implements TableCellRenderer {

  public ButtonRenderer() {
    setOpaque(true);
  }
  
  public Component getTableCellRendererComponent(JTable table, Object value,
                   boolean isSelected, boolean hasFocus, int row, int column) {
    if (isSelected) {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());//设置色值
    } else{
      setForeground(table.getForeground());
      setBackground(UIManager.getColor("Button.background"));//设置色值
    }
    setText( (value ==null) ? "" : value.toString() );
    return this;
  }
}
