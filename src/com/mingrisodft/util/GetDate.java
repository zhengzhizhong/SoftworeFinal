package com.mingrisodft.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetDate {

	/**
	 * @param args
	 */
public static String getDateTime(){		//该方法返回值为String类型
	SimpleDateFormat format;
						//simpleDateFormat类可以选择任何用户定义的日期-时间格式的模式
	Date date = null; 
	Calendar myDate = Calendar.getInstance();
						//Calendar的方法getInstance()，以获得此类型的一个通用的对象
	myDate.setTime(new java.util.Date());
						//使用给定的Date设置此Calendar的时间
	date = myDate.getTime();
						//返回一个表示此Calendar时间值（从历元至现在的毫秒偏移量）的Date对象
	format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						//编写格式化时间为“年-月-日 时：分：秒”
	String strRtn = format.format(date);
						//将给定的Date格式化为日期/时间字符串，并将结果赋值给给定的String
	return strRtn;			//返回保存返回值变量
}

}
