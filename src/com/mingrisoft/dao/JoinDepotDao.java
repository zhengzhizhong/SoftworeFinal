package com.mingrisoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mingrisoft.bean.Depot;
import com.mingrisoft.bean.JoinDepot;
import com.mingrisoft.bean.Provide;

public class JoinDepotDao {
	// 定义添加仓库信息方法
	GetConnection connection = new GetConnection();
	Connection conn = null;
	//向仓库入库表中添加数据
	public void insertJoinDepot(JoinDepot joinDepot) {
		conn = connection.getCon();
		try {
			PreparedStatement statement = conn
					.prepareStatement("insert into tb_joinDepot values(?,?,?,?,?,?)");
			statement.setString(1, joinDepot.getoId());
			statement.setInt(2, joinDepot.getdId());
			statement.setString(3, joinDepot.getWareName());
			statement.setString(4, joinDepot.getJoinTime());
			statement.setFloat(5, joinDepot.getWeight());
			statement.setString(6, joinDepot.getRemark());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 定义查询仓库入库表中全部数据方法
	public List selectJoinDepot() {
		List list = new ArrayList<Provide>();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_joinDepot");
			while (rest.next()) {
				JoinDepot depot = new JoinDepot();
				depot.setId(rest.getInt(1));
				depot.setoId(rest.getString(2));
				depot.setdId(rest.getInt(3));
				depot.setWareName(rest.getString(4));
				depot.setJoinTime(rest.getString(5));
				depot.setWeight(rest.getShort(6));
				depot.setRemark(rest.getString(7));
				list.add(depot);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// 编写按编号查询仓库入库信息方法
	public JoinDepot selectJoinDepotByid(int id) {
		JoinDepot depot = new JoinDepot();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_joinDepot where id = " + id;
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				depot.setId(id);
				depot.setoId(rest.getString(2));
				depot.setdId(rest.getInt(3));
				depot.setWareName(rest.getString(4));
				depot.setJoinTime(rest.getString(5));
				depot.setWeight(rest.getFloat(6));
				depot.setRemark(rest.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return depot;
	}

	// 定义按订单号查询仓库入库信息方法
	public List selectDepotByOid(String oId) {		
		conn = connection.getCon();
		List list = new ArrayList<JoinDepot>();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_joinDepot where oId = '" + oId +"'";
			ResultSet rest = statement.executeQuery(sql);			
			while (rest.next()) {
				JoinDepot depot = new JoinDepot();
				depot.setId(rest.getInt(1));
				depot.setoId(rest.getString(2));
				depot.setdId(rest.getInt(3));
				depot.setWareName(rest.getString(4));
				depot.setJoinTime(rest.getString(5));
				depot.setWeight(rest.getFloat(6));
				depot.setRemark(rest.getString(7));
				list.add(depot);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// 定义按仓库入库时间查询方法
	public List selectJoinDepot(String joinTime) {		
		conn = connection.getCon();
		List list = new ArrayList<Provide>();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_joinDepot where joinTime = '" + joinTime +"'";
			ResultSet rest = statement.executeQuery(sql);			
			while (rest.next()) {
				JoinDepot depot = new JoinDepot();
				depot.setId(rest.getInt(1));
				depot.setoId(rest.getString(2));
				depot.setdId(rest.getInt(3));
				depot.setWareName(rest.getString(4));
				depot.setJoinTime(rest.getString(5));
				depot.setWeight(rest.getFloat(6));
				depot.setRemark(rest.getString(7));
				list.add(depot);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// 定义按仓库入库时间和入库时间查询方法
	public List selectJoinDepotAndDate(String oid,String joinTime) {		
		conn = connection.getCon();
		List list = new ArrayList<Provide>();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_joinDepot where oid = '"+oid+"' and joinTime = '" + joinTime +"'";
			ResultSet rest = statement.executeQuery(sql);			
			while (rest.next()) {
				JoinDepot depot = new JoinDepot();
				depot.setId(rest.getInt(1));
				depot.setoId(rest.getString(2));
				depot.setdId(rest.getInt(3));
				depot.setWareName(rest.getString(4));
				depot.setJoinTime(rest.getString(5));
				depot.setWeight(rest.getFloat(6));
				depot.setRemark(rest.getString(7));
				list.add(depot);		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// 定义修改仓库入库信息方法
	public void updateJoinDepot(JoinDepot depot) {
		conn = connection.getCon();
		try {
			String sql = "update tb_joinDepot set oId= ?,dId=?,wareName=?,joinTime=?,weight=?,remark=? where id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, depot.getoId());
			statement.setInt(2, depot.getdId());
			statement.setString(3, depot.getWareName());
			statement.setString(4, depot.getJoinTime());
			statement.setFloat(5, depot.getWeight());
			statement.setString(6, depot.getRemark());
			statement.setInt(7, depot.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 定义仓库信息方法
	public void deleteJoinDepot(int id){
		conn = connection.getCon();
		String sql = "delete from tb_joinDepot where id ="+id;
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
	}
	//查询所有仓库编号方法
	public List selectDepotId() {		
		conn = connection.getCon();
		List list = new ArrayList<JoinDepot>();
		try {
			Statement statement = conn.createStatement();
			String sql = "select id from tb_depot";
			ResultSet rest = statement.executeQuery(sql);			
			while (rest.next()) {
				int id = rest.getInt(1);
				list.add(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//查询仓库中所有入库商品名
	public List selectNameBydId(int did) {		
		conn = connection.getCon();
		List list = new ArrayList<JoinDepot>();
		try {
			Statement statement = conn.createStatement();
			String sql = "select wareName from tb_joinDepot where dId = "+did;
			ResultSet rest = statement.executeQuery(sql);			
			while (rest.next()) {
				String sName = rest.getString(1);
				list.add(sName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//查询所有订单编号方法
	public List selectOidId() {		
		conn = connection.getCon();
		List list = new ArrayList<JoinDepot>();
		try {
			Statement statement = conn.createStatement();
			String sql = "select orderId from tb_stock";
			ResultSet rest = statement.executeQuery(sql);			
			while (rest.next()) {
				String orderId = rest.getString(1);
				list.add(orderId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//查询所有订单编号查询货品名称方法
	public String selectOidId(String oid) {		
		conn = connection.getCon();
		String orderIds ="";		
		try {
			Statement statement = conn.createStatement();
			String sql = "select sName from tb_stock where orderId = '"+oid+"'";
			System.out.println(sql);
			ResultSet rest = statement.executeQuery(sql);			
			while (rest.next()) {				
				orderIds = rest.getString(1);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderIds;
	}	
}
