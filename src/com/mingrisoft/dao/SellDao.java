package com.mingrisoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.mingrisoft.bean.Sell;

public class SellDao {
	// ������ӹ����̷���
	GetConnection connection = new GetConnection();
	Connection conn = null;
	public void insertSell(Sell sell) {
		conn = connection.getCon();
		try {
			PreparedStatement statement = conn
					.prepareStatement("insert into tb_sell values(?,?,?,?,?,?,?,?,?,?)");
			statement.setString(1, sell.getSellName());
			statement.setString(2, sell.getAddress());
			statement.setString(3, sell.getLinkman());
			statement.setString(4, sell.getLinkPhone());
			statement.setString(5, sell.getFaxNum());
			statement.setString(6, sell.getPostNum());
			statement.setString(7, sell.getBankNum());
			statement.setString(8, sell.getNetAddress());
			statement.setString(9, sell.getEmallAddress());
			statement.setString(10, sell.getRemark());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// �����ѯ�����̱���ȫ�����ݷ���
	public List selectSell() {
		List list = new ArrayList<Sell>();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_sell");
			while (rest.next()) {
				Sell sell = new Sell();
				sell.setId(rest.getInt(1));
				sell.setSellName(rest.getString(2));
				sell.setAddress(rest.getString(3));
				sell.setLinkman(rest.getString(4));
				sell.setLinkPhone(rest.getString(5));
				sell.setFaxNum(rest.getString(6));
				sell.setPostNum(rest.getString(7));
				sell.setBankNum(rest.getString(8));
				sell.setNetAddress(rest.getString(9));
				sell.setEmallAddress(rest.getString(10));
				sell.setRenark(rest.getString(11));
				list.add(sell);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// ��д����Ų�ѯ��Ӧ����Ϣ����
	public Sell selectProvideByid(int id) {
		Sell sell = new Sell();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_sell where id = " + id;
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				sell.setId(rest.getInt(1));
				sell.setSellName(rest.getString(2));
				sell.setAddress(rest.getString(3));
				sell.setLinkman(rest.getString(4));
				sell.setLinkPhone(rest.getString(5));
				sell.setFaxNum(rest.getString(6));
				sell.setPostNum(rest.getString(7));
				sell.setBankNum(rest.getString(8));
				sell.setNetAddress(rest.getString(9));
				sell.setEmallAddress(rest.getString(10));
				sell.setRenark(rest.getString(11));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sell;
	}

	// ���尴�ͻ���ַ��ѯ��Ӧ����Ϣ����
	public List selectsellByAddress(String address) {
		Sell sell = new Sell();
		conn = connection.getCon();
		List list = new ArrayList<Sell>();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_sell where address = '" + address +"'";
			ResultSet rest = statement.executeQuery(sql);			
			while (rest.next()) {
				sell.setId(rest.getInt(1));
				sell.setSellName(rest.getString(2));
				sell.setAddress(rest.getString(3));
				sell.setLinkman(rest.getString(4));
				sell.setLinkPhone(rest.getString(5));
				sell.setFaxNum(rest.getString(6));
				sell.setPostNum(rest.getString(7));
				sell.setBankNum(rest.getString(8));
				sell.setNetAddress(rest.getString(9));
				sell.setEmallAddress(rest.getString(10));
				sell.setRenark(rest.getString(11));
				list.add(sell);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// ���尴�ͻ����Ʋ�ѯ��Ӧ����Ϣ����
	public List selectsellByName(String name) {
		
		conn = connection.getCon();
		List list = new ArrayList<Sell>();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_sell where sellName = '" + name+"'";
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				Sell sell = new Sell();
				sell.setId(rest.getInt(1));
				sell.setSellName(rest.getString(2));
				sell.setAddress(rest.getString(3));
				sell.setLinkman(rest.getString(4));
				sell.setLinkPhone(rest.getString(5));
				sell.setFaxNum(rest.getString(6));
				sell.setPostNum(rest.getString(7));
				sell.setBankNum(rest.getString(8));
				sell.setNetAddress(rest.getString(9));
				sell.setEmallAddress(rest.getString(10));
				sell.setRenark(rest.getString(11));
				list.add(sell);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// ���尴�ͻ����ƺͿͻ���ַ��ѯ��Ӧ����Ϣ����
	public List selectSellByNameAddress(String address, String name) {
		Sell sell = new Sell();
		conn = connection.getCon();
		List list = new ArrayList<Sell>();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_sell where sellName = '" + name
					+ "' and address = '" + address +"'";
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				sell.setId(rest.getInt(1));
				sell.setSellName(rest.getString(2));
				sell.setAddress(rest.getString(3));
				sell.setLinkman(rest.getString(4));
				sell.setLinkPhone(rest.getString(5));
				sell.setFaxNum(rest.getString(6));
				sell.setPostNum(rest.getString(7));
				sell.setBankNum(rest.getString(8));
				sell.setNetAddress(rest.getString(9));
				sell.setEmallAddress(rest.getString(10));
				sell.setRenark(rest.getString(11));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// �����޸Ĺ�Ӧ����Ϣ����
	public void updateSell(Sell sell) {
		conn = connection.getCon();
		try {
			String sql = "update tb_sell set sellName = ?,address = ?,linkman = ?,linkPhone=?,faxNum=?,postNum=?,"
					+ " bankNum=?,netAddress=?,emaillAddress=?,remark=? where id =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, sell.getSellName());
			statement.setString(2, sell.getAddress());
			statement.setString(3, sell.getLinkman());
			statement.setString(4, sell.getLinkPhone());
			statement.setString(5, sell.getFaxNum());
			statement.setString(6, sell.getPostNum());
			statement.setString(7, sell.getBankNum());
			statement.setString(8, sell.getNetAddress());
			statement.setString(9, sell.getEmallAddress());
			statement.setString(10, sell.getRemark());
			statement.setInt(11, sell.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ����ɾ����Ӧ����Ϣ����
	public void deleteSell(int id){
		conn = connection.getCon();
		String sql = "delete from tb_sell where id ="+id;
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}
	
}
