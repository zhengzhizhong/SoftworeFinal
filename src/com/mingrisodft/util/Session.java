package com.mingrisodft.util;


import com.mingrisoft.bean.User;

public class Session {
	private static User user;		//User��������
	public static User getUser() {			//���Ե�getXXX()����
		return user;
	}
	public static void setUser( User user) {	//���Ե�setXXX()����
		 Session.user = user;	
	}	
}
