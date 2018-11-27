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
import com.mingrisoft.bean.OutDepot;
import com.mingrisoft.bean.Provide;

public class OutDepotDao {
	// ������Ӳֿ���Ϣ����
	GetConnection connection = new GetConnection();
	Connection conn = null;
	//��ֿ��������������
	public void insertOutDepot(OutDepot depot) {
		conn = connection.getCon();
		try {
			PreparedStatement statement = conn
					.prepareStatement("insert into tb_outDepot values(?,?,?,?,?)");		
			statement.setInt(1, depot.getDid());
			statement.setString(2,depot.getwName());
			statement.setString(3, depot.getOutDate());
			statement.setFloat(4, depot.getWight());
			statement.setString(5,depot.getRemark());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// �����ѯ�ֿ�������ȫ�����ݷ���
	public List selectOutDepot() {
		List list = new ArrayList<OutDepot>();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_outDepot");
			while (rest.next()) {
				OutDepot depot = new OutDepot();
				depot.setId(rest.getInt(1));		
				depot.setDid(rest.getInt(2));
				depot.setwName(rest.getString(3));
				depot.setOutDate(rest.getString(4));
				depot.setWight(rest.getFloat(5));
				depot.setRemark(rest.getString(6));
				list.add(depot);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// ��д����Ų�ѯ�ֿ������Ϣ����
	public OutDepot selectOutDepotByid(int id) {
		OutDepot depot = new OutDepot();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_outDepot where id = " + id;
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				depot.setId(id);		
				depot.setDid(rest.getInt(2));
				depot.setwName(rest.getString(3));
				depot.setOutDate(rest.getString(4));
				depot.setWight(rest.getFloat(5));
				depot.setRemark(rest.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return depot;
	}

	// ���尴�ֿ��Ų�ѯ�ֿ������Ϣ����
	public List selectOutDepotByDid(int did) {		
		conn = connection.getCon();
		List list = new ArrayList<JoinDepot>();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_outDepot where dId = " + did +"";
			ResultSet rest = statement.executeQuery(sql);			
			while (rest.next()) {
				OutDepot depot = new OutDepot();
				depot.setId(rest.getInt(1));				
				depot.setDid(rest.getInt(2));
				depot.setwName(rest.getString(3));
				depot.setOutDate(rest.getString(4));
				depot.setWight(rest.getFloat(5));
				depot.setRemark(rest.getString(6));
				list.add(depot);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// ���尴�ֿ����ʱ��Ͳֿ��Ų�ѯ������Ϣ����
	public List selectOutDepotByTime(String outTime,int did) {		
		conn = connection.getCon();
		List list = new ArrayList<Provide>();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_outDepot where joinTime = '" + outTime +"' and did = "+did;
			ResultSet rest = statement.executeQuery(sql);			
			while (rest.next()) {
				OutDepot depot = new OutDepot();
				depot.setId(rest.getInt(1));				
				depot.setDid(rest.getInt(2));
				depot.setwName(rest.getString(3));
				depot.setOutDate(rest.getString(4));
				depot.setWight(rest.getFloat(5));
				depot.setRemark(rest.getString(6));
				list.add(depot);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// ���尴��Ʒ���Ʋ�ѯ��Ʒ��������
	public float selectJoinDepotAndDate(String wName,int did) {		
		conn = connection.getCon();
		float wight = 0;
		try {
			Statement statement = conn.createStatement();
			String sql = "select weight from tb_joinDepot where wareName = '"+wName+"' and did ="+did;
			ResultSet rest = statement.executeQuery(sql);			
			while (rest.next()) {
				wight = rest.getFloat(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wight;
	}	
	// �����޸Ĳֿ������Ϣ����
	public void updateOutDepot(OutDepot depot) {
		conn = connection.getCon();
		try {
			String sql = "update tb_outDepot set dId=?,wName=?,outDate=?,wight=?,remark=? where id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);			
			statement.setInt(1, depot.getDid());
			statement.setString(2, depot.getwName());
			statement.setString(3, depot.getOutDate());
			statement.setFloat(4, depot.getWight());
			statement.setString(5, depot.getRemark());
			statement.setInt(6, depot.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//���尴�ֿ��ѯ��Ʒ���Ʒ���
	public List selectOutDepotNames(int did) {		
		conn = connection.getCon();
		List list = new ArrayList<String>();
		try {
			Statement statement = conn.createStatement();
			String sql = "select wareName from tb_joinDepot where did = '" + did +"'";
			ResultSet rest = statement.executeQuery(sql);			
			while (rest.next()) {
				String wName = rest.getString(1);
				list.add(wName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//���尴�ֿ������Ʒ�����޸Ļ�Ʒ��������
	public void updateJoin(int did,String wName,float wight) {		
		conn = connection.getCon();
		List list = new ArrayList<String>();
		try {
			Statement statement = conn.createStatement();
			String sql = "update tb_joinDepot set weight = weight-"+wight+" where did = '" + did +"' and wareName ='"+wName+"'";
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//���尴�ֿ������Ʒ�����޸Ļ�Ʒ��������
	public void updateJoinAdd(int did,String wName,float wight) {		
		conn = connection.getCon();
		List list = new ArrayList<String>();
		try {
			Statement statement = conn.createStatement();
			String sql = "update tb_joinDepot set weight = weight+"+wight+" where did = '" + did +"' and wareName ='"+wName+"'";
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	// ����ɾ���ֿ������Ϣ
	public void deleteOutDepot(int id){
		conn = connection.getCon();
		String sql = "delete from tb_outDepot where id ="+id;
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
	}
	
	
}
