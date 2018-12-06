package com.mingrisoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import com.mingrisoft.bean.*;

public class DepotDao {
	// ������Ӳֿ���Ϣ����
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

	// ��д����Ų�ѯ�ֿ���Ϣ����
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

	// ���尴�ֿ��Ų�ѯ�ֿ���Ϣ����
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

	// �����޸Ĳֿ���Ϣ����
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

	// ����ֿ���Ϣ����
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
