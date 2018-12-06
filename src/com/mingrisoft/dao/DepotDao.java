package com.mingrisoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import com.mingrisoft.bean.*;

public class DepotDao {
	// 定义添加仓库信息方法
	GetConnection connection = new GetConnection();
	Connection conn = null;
	public void insertDepot(Depot depot) {
		conn = connection.getCon();
		try {
			PreparedStatement statement = conn
					.prepareStatement("insert into tb_depot values(?,?,?)");
			statement.setInt(1, depot.getId());
			statement.setString(2,depot.getManage());
			statement.setString(3, depot.getFunctional());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 编写按编号查询仓库信息方法
	public Depot selectDepotByid(int id) {
		Depot depot = new Depot();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_depot where id = " + id;
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				depot.setId(id);
				depot.setManage(rest.getString(2));
				depot.setFunctional(rest.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return depot;
	}

	// 定义按仓库编号查询仓库信息方法
	public Depot selectDepotById(int id) {		
		conn = connection.getCon();
		Depot depot = new Depot();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_depot where id = '" + id +"'";
			ResultSet rest = statement.executeQuery(sql);			
			while (rest.next()) {			
				depot.setId(rest.getInt(1));
				depot.setManage(rest.getString(2));
				depot.setFunctional(rest.getString(3));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return depot;
	}

	// 定义修改仓库信息方法
	public void updateDepot(Depot depot) {
		conn = connection.getCon();
		try {
			String sql = "update tb_depot set manage = ?,functional = ? where id =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, depot.getManage());
			statement.setString(2, depot.getFunctional());
			statement.setInt(3, depot.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 定义仓库信息方法
	public void deleteDepot(int id){
		conn = connection.getCon();
		String sql = "delete from tb_depot where id ="+id;
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
	}
	
}
