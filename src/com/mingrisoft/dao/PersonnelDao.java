package com.mingrisoft.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.mingrisoft.bean.BasicMessage;
import com.mingrisoft.bean.Contact;
import com.mingrisoft.bean.Dept;
import com.mingrisoft.bean.Headship;

public class PersonnelDao {
	// �������Ա��������Ϣ����
	GetConnection connection = new GetConnection();
	Connection conn = null;
	//������Ա������������ݷ���
public void insertBasicMessage(BasicMessage message) {
	conn = connection.getCon();		//��ȡ���ݿ�����
	try {
		PreparedStatement statement = conn
				.prepareStatement("insert into tb_basicMessage values(?,?,?,?,?)");	//�����������SQL���
		statement.setString(1,message.getName());		//����Ԥ����������ֵ
		statement.setInt(2, message.getAge());
		statement.setString(3, message.getSex());
		statement.setInt(4, message.getDept());
		statement.setInt(5, message.getHeadship());
		statement.executeUpdate();						//ִ�в������
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
	//������Ա����ϵ�˱���������ݷ���
public void insertContact(Contact contact) {
	conn = connection.getCon();					//��ȡ���ݿ�����
	try {
		PreparedStatement statement = conn
				.prepareStatement("insert into tb_contact values(?,?,?,?,?,?)");	//�����������SQL���		
		statement.setInt(1, contact.getHid());				//���ò���������
		statement.setString(2, contact.getContact());
		statement.setString(3, contact.getOfficePhone());
		statement.setString(4, contact.getFax());
		statement.setString(5, contact.getEmail());
		statement.setString(6, contact.getFaddress());
		statement.executeUpdate();			//ִ�в������
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
	
	// �����ѯְ�����ȫ�����ݷ���
	public List selectHeadship() {
		List list = new ArrayList<Dept>();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_headship");
			while (rest.next()) {
				Headship headship = new Headship();
				headship.setId(rest.getInt(1));
				headship.setHeadshipName(rest.getString(2));
				list.add(headship);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// ��д��ְ�����Ʋ�ѯ��ŷ���
public int selectIdByHeadship(String hName) {
	int id = 0;						//���屣���ѯ�����int����
	conn = connection.getCon();		//��ȡ���ݿ�����
	try {
		Statement statement = conn.createStatement();	//����Statement����
		String sql = "select id from tb_headship where headshipName = '" + hName+"'";	//����ִ�в�ѯ��SQL���
		ResultSet rest = statement.executeQuery(sql);	//ִ�в�ѯ����ȡ��ѯ�����
		while (rest.next()) {		//ѭ��������ѯ�����
			id = rest.getInt(1);	
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return id;
}
	// ���尴������ѯԱ����ŷ���
	public int selectBasicMessageByName(String name) {		
		conn = connection.getCon();	
		int id = 0;
		try {
			Statement statement = conn.createStatement();
			String sql = "select id from tb_basicMessage where name = '" + name +"'";		
			ResultSet rest = statement.executeQuery(sql);			
			while (rest.next()) {
				id = rest.getInt(1);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}	
	// ���尴���Ų�ѯԱ����Ϣ����
public List selectBasicMessageByDept(int dept) {		
	conn = connection.getCon();				//��ȡ���ݿ�����
	List list = new ArrayList<String>();	//���屣���ѯ����ļ��϶���
	try {
		Statement statement = conn.createStatement();		//ʵ����Statement����
		String sql = "select name from tb_basicMessage where dept = " + dept +"";		//���尴�ղ������Ʋ�ѯԱ����Ϣ����
		ResultSet rest = statement.executeQuery(sql);		//ִ�в�ѯ����ȡ��ѯ�����
		while (rest.next()) {				//ѭ��������ѯ�����
			list.add(rest.getString(1));	//����ѯ��Ϣ���浽������
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return list;							//���ز�ѯ����
}	
	// ���尴�����˲�ѯ������Ϣ����
	public Dept selectDeptByPrincipal(String manage) {		
		conn = connection.getCon();
		Dept dept =null ;
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_dept where principal = '" + manage +"'";
			ResultSet rest = statement.executeQuery(sql);			
			while (rest.next()) {
			    dept = new Dept();
				dept.setId(rest.getInt(1));
				dept.setdName(rest.getString(2));
				dept.setPrincipal(rest.getString(3));
				dept.setBewrite(rest.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dept;
	}

	// ���尴���ű�Ų�ѯ������Ϣ����
	public Dept selectDepotById(int id) {		
		conn = connection.getCon();
		Dept dept = new Dept();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_dept where id = '" + id +"'";
			ResultSet rest = statement.executeQuery(sql);			
			while (rest.next()) {			
				dept.setId(rest.getInt(1));
				dept.setdName(rest.getString(2));
				dept.setPrincipal(rest.getString(3));
				dept.setBewrite(rest.getString(4));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dept;
	}
	// ���尴��Ų�ѯְ����Ϣ����
	public String selectHeadshipById(int id) {		
		conn = connection.getCon();
		String hName = "";
		try {
			Statement statement = conn.createStatement();
			String sql = "select headshipName from tb_headship where id = '" + id +"'";
			ResultSet rest = statement.executeQuery(sql);			
			while (rest.next()) {			
				hName = rest.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hName;
	}
	// ���尴�������ƺ�Ա��������ѯԱ����Ϣ��Ϣ����
public BasicMessage selectBNameById(String dept,String name) {		
	conn = connection.getCon();				//��ȡ���ݿ�����
	BasicMessage message = new BasicMessage();		//���������ݱ��Ӧ��JavaBean����
	try {
		Statement statement = conn.createStatement();
		String sql = "select * from tb_basicMessage where name = '"+name+"' and dept = (select id from tb_dept" +
				" where dName = '"+dept+"')";		//�����ѯ����SQL���			
		ResultSet rest = statement.executeQuery(sql);		//ִ�в�ѯ���
		while (rest.next()) {						//ѭ��������ѯ�����
			message.setId(rest.getInt(1));			//Ӧ�ò�ѯ������ö�������
			message.setName(rest.getString(2));
			message.setAge(rest.getInt(3));
			message.setSex(rest.getString(4));
			message.setDept(rest.getInt(5));
			message.setHeadship(rest.getInt(6));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return message;				
}
	// ���尴Ա����Ų�ѯ��ϸ��Ϣ����
	public Contact selectContactById(int hid) {		
		conn = connection.getCon();
		Contact contact = new Contact();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_contact where hid = "+hid+"";
			System.out.println(sql);
			ResultSet rest = statement.executeQuery(sql);			
			while (rest.next()) {			
				contact.setId(rest.getInt(1));
				contact.setHid(rest.getInt(2));
				contact.setContact(rest.getString(3));
				contact.setOfficePhone(rest.getString(4));
				contact.setFax(rest.getString(5));
				contact.setEmail(rest.getString(6));
				contact.setFaddress(rest.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contact;
	}
	// ���尴Ա����Ų�ѯ������Ϣ����
	public BasicMessage selectMessageById(int hid) {		
		conn = connection.getCon();
		BasicMessage message = new BasicMessage();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_basicMessage where id = "+hid+"";
			System.out.println(sql);
			ResultSet rest = statement.executeQuery(sql);			
			while (rest.next()) {			
				message.setId(rest.getInt(1));
				message.setName(rest.getString(2));
				message.setAge(rest.getInt(3));
				message.setSex(rest.getString(4));
				message.setDept(rest.getInt(5));
				message.setHeadship(rest.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return message;
	}

	// �����޸�Ա��������Ϣ����
	public void updatertMessage(BasicMessage message) {
		conn = connection.getCon();
		try {
			String sql = "update tb_basicMessage set name = ?,age = ?,sex=?,dept=?,headship=? where id =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, message.getName());
			statement.setInt(2, message.getAge());
			statement.setString(3, message.getSex());
			statement.setInt(4, message.getDept());
			statement.setInt(5, message.getHeadship());
			statement.setInt(6, message.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// �����޸�Ա����ϸ��Ϣ����
	public void updatertContact(Contact contact) {
		conn = connection.getCon();
		try {
			String sql = "update tb_contact set contact = ?,officePhone = ?,fax=?,email=?,faddress=? where hid =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, contact.getContact());
			statement.setString(2, contact.getOfficePhone());
			statement.setString(3, contact.getFax());
			statement.setString(4, contact.getEmail());
			statement.setString(5, contact.getFaddress());
			statement.setInt(6, contact.getHid());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// ����ɾ��������Ϣ����
public void deleteBasicMessage(int id){
	conn = connection.getCon();			//���û�ȡ���ݿ����ӷ���
	String sql = "delete from tb_basicMessage where id ="+id;		//����ɾ�����ݵ�SQL���
	try {
		Statement statement = conn.createStatement();	//����Statement����
		statement.executeUpdate(sql);	//ִ��ɾ������SQL���
	} catch (SQLException e) {			
		e.printStackTrace();
	}		
}
}
