package com.mingrisoft.bean;

public class Stock {
	private int id;	
	private String sName;		
	private String orderId;
	private String consignmentDate;
	private String baleName;
	private String count;
	private float money;	
	private String lairage;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getConsignmentDate() {
		return consignmentDate;
	}
	public void setConsignmentDate(String consignmentDate) {
		this.consignmentDate = consignmentDate;
	}
	public String getBaleName() {
		return baleName;
	}
	public void setBaleName(String baleName) {
		this.baleName = baleName;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}	
	public String getLairage() {
		return lairage;
	}
	public void setLairage(String lairage) {
		this.lairage = lairage;
	}
	
}
