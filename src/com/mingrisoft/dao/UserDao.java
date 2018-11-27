package com.mingrisoft.dao;
import java.sql.*;

import com.mingrisoft.bean.User;
public class UserDao {
GetConnection connection = new GetConnection();
Connection conn = null;
//��д���û��������ѯ�û�����
public User getUser(String userName,String passWord){
	User user = new User();				//����JavaBean����
	conn = connection.getCon();			//��ȡ���ݿ�����
	try {
		String sql = "select * from tb_users where userName = ? and passWord = ?";	//�����ѯԤ�������
		PreparedStatement statement = conn.prepareStatement(sql);		//ʵ����PreparedStatement����
		statement.setString(1, userName);			//����Ԥ����������
		statement.setString(2, passWord);
		ResultSet rest = statement.executeQuery();	//ִ��Ԥ�������
		while(rest.next()){
			user.setId(rest.getInt(1));				//Ӧ�ò�ѯ������ö�������
			user.setUserName(rest.getString(2));
			user.setPassWord(rest.getString(3));
		}
	} catch (SQLException e) {			
		e.printStackTrace();
	}		
	return user;						//���ز�ѯ���
}	
}