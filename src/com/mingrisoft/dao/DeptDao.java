package com.mingrisoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mingrisoft.bean.Depot;
import com.mingrisoft.bean.Dept;
import com.mingrisoft.bean.Provide;

public class DeptDao {
	// 定义添加部门信息方法
	GetConnection connection = new GetConnection();		//
	Connection conn = null;
	public void insertDept(Dept dept) {
		conn = connection.getCon();
		try {
			PreparedStatement statement = conn
					.prepareStatement("insert into tb_dept values(?,?,?)");
			statement.setString(1,dept.getdName());
			statement.setString(2, dept.getPrincipal());
			statement.setString(3, dept.getBewrite());		
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 定义查询部门表中全部数据方法
public List selectDept() {
	List list = new ArrayList<Dept>();				//定义List集合对象
	conn = connection.getCon();						//获取数据库连接
	try {
		Statement statement = conn.createStatement();		//获取Statement方法
		ResultSet rest = statement.executeQuery("select * from tb_dept");	//执行查询语句获取查询结果集
		while (rest.next()) {						//循环遍历查询结果集
			Dept dept = new Dept();
			dept.setId(rest.getInt(1));				//应用查询结果设置对象属性
			dept.setdName(rest.getString(2));
			dept.setPrincipal(rest.getString(3));
			dept.setBewrite(rest.getString(4));
			list.add(dept);							//将对象添加到集合中
		}		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return list;
}

	// 编写按编号查询部门信息方法
	public Dept selectDeptByid(int id) {
		Dept dept = new Dept();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_dept where id = " + id;
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
	//编写按照部门名称查询部门编号方法
	public int selectDeptIdByName(String name) {
		int id = 0;
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			String sql = "select id from tb_dept where dName = '" + name+"'";
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				id = rest.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	// 定义按部门名称查询部门信息方法
public Dept selectDeptByName(String name) {		
	conn = connection.getCon();			//获取数据库连接
	Dept dept = null;
	try {
		Statement statement = conn.createStatement();		//实例化Statement对象
		String sql = "select * from tb_dept where dName = '" + name +"'";	//定义按部门名称查询部门信息SQL语句
		ResultSet rest = statement.executeQuery(sql);		//执行查询语句获取查询结果集	
		while (rest.next()) {		//循环遍历查询结果集
			dept = new Dept();					//定义与部门表对应的JavaBean对象
			dept.setId(rest.getInt(1));			//应用查询结果设置对象属性
			dept.setdName(rest.getString(2));
			dept.setPrincipal(rest.getString(3));
			dept.setBewrite(rest.getString(4));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return dept;					//返回JavaBean对象
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

	// 定义修改部门信息方法
	public void updateDept(Dept dept) {
		conn = connection.getCon();
		try {
			String sql = "update tb_dept set dName = ?,principal = ?,bewrite=? where id =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, dept.getdName());
			statement.setString(2, dept.getPrincipal());
			statement.setString(3, dept.getBewrite());
			statement.setInt(4, dept.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 定义删除部门信息方法
	public void deleteDept(int id){
		conn = connection.getCon();
		String sql = "delete from tb_dept where id ="+id;
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
	}
}
