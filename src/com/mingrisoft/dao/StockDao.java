package com.mingrisoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mingrisoft.bean.Sell;
import com.mingrisoft.bean.Stock;

public class StockDao {

	GetConnection connection = new GetConnection();
	Connection conn = null;
	// 定义添加采购订货方法
public void insertStock(Stock stock) {
	conn = connection.getCon();			//获取数据库连接
	try {
		PreparedStatement statement = conn
				.prepareStatement("insert into tb_stock values(?,?,?,?,?,?)");	//定义查询数据的SQL语句
		statement.setString(1,stock.getsName());			//设置预处理语句参数
		statement.setString(2,stock.getOrderId());			
		statement.setString(3,stock.getConsignmentDate());
		statement.setString(4,stock.getBaleName());
		statement.setString(5,stock.getCount());
		statement.setFloat(6,stock.getMoney());			
		statement.executeUpdate();							//执行插入操作
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

	// 定义查询采购订货表中全部数据方法
	public List selectStock() {
		List list = new ArrayList<Sell>();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_stock");
			while (rest.next()) {
				Stock stock = new Stock();
				stock.setId(rest.getInt(1));
				stock.setsName(rest.getString(2));
				stock.setOrderId(rest.getString(3));
				stock.setConsignmentDate(rest.getString(4));
				stock.setBaleName(rest.getString(5));
				stock.setCount(rest.getString(6));
				stock.setMoney(rest.getFloat(7));			
				list.add(stock);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	// 定义订单号查询仓库入库表数据方法
	public int selectJoinStockByOid(String oid) {
		List list = new ArrayList<Sell>();
		conn = connection.getCon();
		int id = 0;
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select id from tb_joinDepot where oid ='"+oid+"'");
			while (rest.next()) {
				id = rest.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}
	// 定义按货品名查询订单表数据方法
public List selectStockBySName(String sName) {
	List list = new ArrayList<Stock>();			//定义保存查询结果的List对象
	conn = connection.getCon();					//获取数据库连接
	int id = 0;
	try {
		Statement statement = conn.createStatement();	//实例化Statement对象
		ResultSet rest = statement.executeQuery("select * from tb_stock where sName ='"+sName+"'");	//定义查询语句，获取查询结果集
		while (rest.next()) {					//循环遍历查询结果集
			Stock stock = new Stock();			//定义与数据表对象的JavaBean对象
			stock.setId(rest.getInt(1));		//应用查询结果设置JavaBean属性
			stock.setsName(rest.getString(2));
			stock.setOrderId(rest.getString(3));
			stock.setConsignmentDate(rest.getString(4));
			stock.setBaleName(rest.getString(5));
			stock.setCount(rest.getString(6));
			stock.setMoney(rest.getFloat(7));
			list.add(stock);					//将JavaBean对象添加到集合
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return list;								//返回查询集合
}
	// 定义按订单号查询订单表数据方法
	public List selectStockByOid(String oId) {
		List list = new ArrayList<Stock>();
		conn = connection.getCon();
		int id = 0;
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_stock where orderId = '"+oId+"'");
			while (rest.next()) {
				Stock stock = new Stock();
				stock.setId(rest.getInt(1));
				stock.setsName(rest.getString(2));
				stock.setOrderId(rest.getString(3));
				stock.setConsignmentDate(rest.getString(4));
				stock.setBaleName(rest.getString(5));
				stock.setCount(rest.getString(6));
				stock.setMoney(rest.getFloat(7));
				list.add(stock);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// 定义按订货日期查询订单表数据方法
	public List selectStockByDate(String cDate) {
		List list = new ArrayList<Stock>();
		conn = connection.getCon();
		int id = 0;
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_stock where consignmentDate = '"+cDate+"'");
			while (rest.next()) {
				Stock stock = new Stock();
				stock.setId(rest.getInt(1));
				stock.setsName(rest.getString(2));
				stock.setOrderId(rest.getString(3));
				stock.setConsignmentDate(rest.getString(4));
				stock.setBaleName(rest.getString(5));
				stock.setCount(rest.getString(6));
				stock.setMoney(rest.getFloat(7));
				list.add(stock);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// 编写按编号查询订货信息方法
public Stock selectStockByid(int id) {
	Stock stock = new Stock();		//定义对与数据库对应的JavaBean对象
	conn = connection.getCon();		//获取数据库连接
	try {
		Statement statement = conn.createStatement();
		String sql = "select * from tb_stock where id = " + id;		//定义查询SQL语句
		ResultSet rest = statement.executeQuery(sql);				//执行查询语句获取查询结果集
		while (rest.next()) {		//循环遍历查询结果集
			stock.setId(id);									//应用查询结果设置对象属性
			stock.setsName(rest.getString(2));
			stock.setOrderId(rest.getString(3));
			stock.setConsignmentDate(rest.getString(4));
			stock.setBaleName(rest.getString(5));				
			stock.setCount(rest.getString(6));
			stock.setMoney(rest.getFloat(7));			
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return stock;						//返回Stock对象
}

	
	// 定义修改供应商信息方法
public void updateStock(Stock stock) {
	conn = connection.getCon();					//获取数据库连接
	try {
		String sql = "update tb_stock set sName=?,orderId=?,consignmentDate=?," +
				"baleName=?,count=?,money=? where id =?";			//定义修改数据表方法
		PreparedStatement statement = conn.prepareStatement(sql);	//获取PreparedStatement对象
		statement.setString(1, stock.getsName());					//设置预处理语句参数值
		statement.setString(2, stock.getOrderId());
		statement.setString(3, stock.getConsignmentDate());
		statement.setString(4, stock.getBaleName());
		statement.setString(5, stock.getCount());
		statement.setFloat(6, stock.getMoney());
		statement.setInt(7, stock.getId());
		statement.executeUpdate();									//执行更新语句
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

	// 定义删除供应商信息方法
public void deleteStock(int id){
	conn = connection.getCon();							//获取数据库连接
	String sql = "delete from tb_stock where id ="+id;	//定义删除数据SQL语句
	try {
		Statement statement = conn.createStatement();	//实例化Statement对象
		statement.executeUpdate(sql);					//执行SQL语句
	} catch (SQLException e) {			
		e.printStackTrace();
	}		
}
}
