package com.mingrisodft.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetDate {

	/**
	 * @param args
	 */
public static String getDateTime(){		//�÷�������ֵΪString����
	SimpleDateFormat format;
						//simpleDateFormat�����ѡ���κ��û����������-ʱ���ʽ��ģʽ
	Date date = null; 
	Calendar myDate = Calendar.getInstance();
						//Calendar�ķ���getInstance()���Ի�ô����͵�һ��ͨ�õĶ���
	myDate.setTime(new java.util.Date());
						//ʹ�ø�����Date���ô�Calendar��ʱ��
	date = myDate.getTime();
						//����һ����ʾ��Calendarʱ��ֵ������Ԫ�����ڵĺ���ƫ��������Date����
	format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						//��д��ʽ��ʱ��Ϊ����-��-�� ʱ���֣��롱
	String strRtn = format.format(date);
						//��������Date��ʽ��Ϊ����/ʱ���ַ��������������ֵ��������String
	return strRtn;			//���ر��淵��ֵ����
}

}
