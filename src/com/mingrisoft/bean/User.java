package com.mingrisoft.bean;

public class User {
	private int id;				//����ӳ������������
	private String userName;	//����ӳ���û���������
	private String passWord;	//����ӳ�����������
	public int getId() {		//id���Ե�getXXX()����
		return id;
	}
	public void setId(int id) {	//id���Ե�setXXX()����
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}	
}
