package com.mingrisoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.mingrisoft.bean.Provide;

public class FeelDao {
	// 定义添加供货商方法
	GetConnection connection = new GetConnection();
	Connection conn = null;
	public void insertProvide(Provide provide) {
		conn = connection.getCon();
		try {
			PreparedStatement statement = conn
					.prepareStatement("insert into tb_provide values(?,?,?,?,?,?,?,?,?,?)");
			statement.setString(1, provide.getcName());
			statement.setString(2, provide.getAddress());
			statement.setString(3, provide.getLinkman());
			statement.setString(4, provide.getLinkPhone());
			statement.setString(5, provide.getFaxes());
			statement.setString(6, provide.getPostNum());
			statement.setString(7, provide.getBankNum());
			statement.setString(8, provide.getNetAddress());
			statement.setString(9, provide.getEmaillAddress());
			statement.setString(10, provide.getRemark());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 定义查询供货商表中全部数据方法
	public List selectProvide() {
		List list = new ArrayList<Provide>();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_provide");
			while (rest.next()) {
				Provide provide = new Provide();
				provide.setId(rest.getInt(1));
				provide.setcName(rest.getString("cName"));
				provide.setAddress(rest.getString("address"));
				provide.setLinkman(rest.getString("linkman"));
				provide.setLinkPhone(rest.getString("linkPhone"));
				provide.setFaxes(rest.getString("faxes"));
				provide.setPostNum(rest.getString("postNum"));
				provide.setBankNum(rest.getString("bankNum"));
				provide.setNetAddress(rest.getString("netAddress"));
				provide.setEmaillAddress(rest.getString("emaillAddress"));
				provide.setRemark(rest.getString("remark"));
				list.add(provide);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// 编写按编号查询供应商信息方法
	public Provide selectProvideByid(int id) {
		Provide provide = new Provide();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_provide where id = " + id;
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				provide.setId(rest.getInt(1));
				provide.setcName(rest.getString("cName"));
				provide.setAddress(rest.getString("address"));
				provide.setLinkman(rest.getString("linkman"));
				provide.setLinkPhone(rest.getString("linkPhone"));
				provide.setFaxes(rest.getString("faxes"));
				provide.setPostNum(rest.getString("postNum"));
				provide.setBankNum(rest.getString("bankNum"));
				provide.setNetAddress(rest.getString("netAddress"));
				provide.setEmaillAddress(rest.getString("emaillAddress"));
				provide.setRemark(rest.getString("remark"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return provide;
	}

	// 定义按客户地址查询供应商信息方法
	public List selectProvideByAddress(String address) {
		Provide provide = new Provide();
		conn = connection.getCon();
		List list = new ArrayList<Provide>();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_provide where address = '" + address +"'";
			ResultSet rest = statement.executeQuery(sql);			
			while (rest.next()) {
				provide.setId(rest.getInt(1));
				provide.setcName(rest.getString("cName"));
				provide.setAddress(rest.getString("address"));
				provide.setLinkman(rest.getString("linkman"));
				provide.setLinkPhone(rest.getString("linkPhone"));
				provide.setFaxes(rest.getString("faxes"));
				provide.setPostNum(rest.getString("postNum"));
				provide.setBankNum(rest.getString("bankNum"));
				provide.setNetAddress(rest.getString("netAddress"));
				provide.setEmaillAddress(rest.getString("emaillAddress"));
				provide.setRemark(rest.getString("remark"));
				list.add(provide);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 定义按客户名称查询供应商信息方法
	public List selectProvideByName(String name) {
		
		conn = connection.getCon();
		List list = new ArrayList<Provide>();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_provide where cName = '" + name+"'";
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				Provide provide = new Provide();
				provide.setId(rest.getInt(1));
				provide.setcName(rest.getString("cName"));
				provide.setAddress(rest.getString("address"));
				provide.setLinkman(rest.getString("linkman"));
				provide.setLinkPhone(rest.getString("linkPhone"));
				provide.setFaxes(rest.getString("faxes"));
				provide.setPostNum(rest.getString("postNum"));
				provide.setBankNum(rest.getString("bankNum"));
				provide.setNetAddress(rest.getString("netAddress"));
				provide.setEmaillAddress(rest.getString("emaillAddress"));
				provide.setRemark(rest.getString("remark"));
				list.add(provide);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 定义按客户名称和客户地址查询供应商信息方法
	public List selectProvideByNameAddress(String address, String name) {
		Provide provide = new Provide();
		conn = connection.getCon();
		List list = new ArrayList<Provide>();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_provide where cName = '" + name
					+ "' and address = '" + address +"'";
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				provide.setId(rest.getInt(1));
				provide.setcName(rest.getString("cName"));
				provide.setAddress(rest.getString("address"));
				provide.setLinkman(rest.getString("linkman"));
				provide.setLinkPhone(rest.getString("linkPhone"));
				provide.setFaxes(rest.getString("faxes"));
				provide.setPostNum(rest.getString("postNum"));
				provide.setBankNum(rest.getString("bankNum"));
				provide.setNetAddress(rest.getString("netAddress"));
				provide.setEmaillAddress(rest.getString("emaillAddress"));
				provide.setRemark(rest.getString("remark"));
				list.add(provide);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 定义修改供应商信息方法
	public void updateProvide(Provide provide) {
		conn = connection.getCon();
		try {
			String sql = "update tb_provide set cName = ?,address = ?,linkman = ?,linkPhone=?,faxes=?,postNum=?,"
					+ " bankNum=?,netAddress=?,emaillAddress=?,remark=? where id =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, provide.getcName());
			statement.setString(2, provide.getAddress());
			statement.setString(3, provide.getLinkman());
			statement.setString(4, provide.getLinkPhone());
			statement.setString(5, provide.getFaxes());
			statement.setString(6, provide.getPostNum());
			statement.setString(7, provide.getBankNum());
			statement.setString(8, provide.getNetAddress());
			statement.setString(9, provide.getEmaillAddress());
			statement.setString(10, provide.getRemark());
			statement.setInt(11, provide.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 定义删除供应商信息方法
	public void deleteProvide(int id){
		conn = connection.getCon();
		String sql = "delete from tb_provide where id ="+id;
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}
}
