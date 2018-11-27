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
	// 定义添加员工基本信息方法
	GetConnection connection = new GetConnection();
	Connection conn = null;
	//定义向员工表中添加数据方法
public void insertBasicMessage(BasicMessage message) {
	conn = connection.getCon();		//获取数据库连接
	try {
		PreparedStatement statement = conn
				.prepareStatement("insert into tb_basicMessage values(?,?,?,?,?)");	//定义添加数据SQL语句
		statement.setString(1,message.getName());		//设置预处理语句参数值
		statement.setInt(2, message.getAge());
		statement.setString(3, message.getSex());
		statement.setInt(4, message.getDept());
		statement.setInt(5, message.getHeadship());
		statement.executeUpdate();						//执行插入语句
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
	//定义向员工联系人表中添加数据方法
public void insertContact(Contact contact) {
	conn = connection.getCon();					//获取数据库连接
	try {
		PreparedStatement statement = conn
				.prepareStatement("insert into tb_contact values(?,?,?,?,?,?)");	//定义插入数据SQL语句		
		statement.setInt(1, contact.getHid());				//设置插入语句参数
		statement.setString(2, contact.getContact());
		statement.setString(3, contact.getOfficePhone());
		statement.setString(4, contact.getFax());
		statement.setString(5, contact.getEmail());
		statement.setString(6, contact.getFaddress());
		statement.executeUpdate();			//执行插入语句
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
	
	// 定义查询职务表中全部数据方法
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

	// 编写按职务名称查询编号方法
public int selectIdByHeadship(String hName) {
	int id = 0;						//定义保存查询结果的int对象
	conn = connection.getCon();		//获取数据库连接
	try {
		Statement statement = conn.createStatement();	//定义Statement对象
		String sql = "select id from tb_headship where headshipName = '" + hName+"'";	//定义执行查询的SQL语句
		ResultSet rest = statement.executeQuery(sql);	//执行查询语句获取查询结果集
		while (rest.next()) {		//循环遍历查询结果集
			id = rest.getInt(1);	
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return id;
}
	// 定义按姓名查询员工编号方法
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
	// 定义按部门查询员工信息方法
public List selectBasicMessageByDept(int dept) {		
	conn = connection.getCon();				//获取数据库连接
	List list = new ArrayList<String>();	//定义保存查询结果的集合对象
	try {
		Statement statement = conn.createStatement();		//实例化Statement对象
		String sql = "select name from tb_basicMessage where dept = " + dept +"";		//定义按照部门名称查询员工信息方法
		ResultSet rest = statement.executeQuery(sql);		//执行查询语句获取查询结果集
		while (rest.next()) {				//循环遍历查询结果集
			list.add(rest.getString(1));	//将查询信息保存到集合中
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return list;							//返回查询集合
}	
	// 定义按负责人查询部门信息方法
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

	// 定义按部门编号查询部门信息方法
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
	// 定义按编号查询职务信息方法
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
	// 定义按部门名称和员工姓名查询员工信息信息方法
public BasicMessage selectBNameById(String dept,String name) {		
	conn = connection.getCon();				//获取数据库连接
	BasicMessage message = new BasicMessage();		//创建与数据表对应的JavaBean对象
	try {
		Statement statement = conn.createStatement();
		String sql = "select * from tb_basicMessage where name = '"+name+"' and dept = (select id from tb_dept" +
				" where dName = '"+dept+"')";		//定义查询数据SQL语句			
		ResultSet rest = statement.executeQuery(sql);		//执行查询语句
		while (rest.next()) {						//循环遍历查询结果集
			message.setId(rest.getInt(1));			//应用查询结果设置对象属性
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
	// 定义按员工编号查询详细信息方法
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
	// 定义按员工编号查询基本信息方法
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

	// 定义修改员工基本信息方法
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
	// 定义修改员工详细信息方法
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
	// 定义删除部门信息方法
public void deleteBasicMessage(int id){
	conn = connection.getCon();			//调用获取数据库连接方法
	String sql = "delete from tb_basicMessage where id ="+id;		//定义删除数据的SQL语句
	try {
		Statement statement = conn.createStatement();	//定义Statement方法
		statement.executeUpdate(sql);	//执行删除数据SQL语句
	} catch (SQLException e) {			
		e.printStackTrace();
	}		
}
}
