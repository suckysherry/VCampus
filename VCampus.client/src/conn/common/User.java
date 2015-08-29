package conn.common;

//import java.io.*;
//import java.io.Serializable;

/**
 * 用户类 基础类
 * @author Suckysherry
 *
 */

public class User { //implements Serializable
	
	//protected static
	protected String uID;
	protected String uPassword;
	protected String uRole;
	protected boolean isLogin = false;
	
	public User(String uID, String uPassword, String uRole) {
		super();
		this.uID = uID;
		this.uPassword = uPassword;
		this.uRole = uRole;
	}
	
	public User(String uID, String uPassword) {
		super();
		this.uID = uID;
		this.uPassword = uPassword;
	}
	
	public User(String uID) {
		super();
		this.uID = uID;
	}
	
	public String getUID() {
		return uID;
	}
	
	public void setUID(String uID) {
		this.uID = uID;
	}
	
	public String getUPassword() {
		return uPassword;
	}
	
	public void setUPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	
	public String getURole() {
		return uRole;
	}
	
	public void setURole(String uRole) {
		this.uRole = uRole;
	}
	
	public boolean isLogin() {
		return isLogin;
	}
	
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

}
