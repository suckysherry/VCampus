package conn.common;

import java.io.Serializable;

//import java.io.*;
//import java.io.Serializable;

/**
 * 用户类 基础类
 * @author Suckysherry
 *
 */

public class User implements Serializable {
	

	protected String uID;
	protected String uPassword;
	protected String uRole; //admin, student,teacher
	protected String uName;
	protected boolean isLibraryAdmin = false;
	protected boolean isJWCAdmin = false;
	protected boolean isShopAdmin = false;
	protected boolean isLogin = false;
	
	public User(String uID, String uPassword, String uRole, String uName, boolean isLibraryAdmin, boolean isJWCAdmin, boolean isShopAdmin) {
		super();
		this.uID = uID;
		this.uPassword = uPassword;
		this.uRole = uRole;
		this.uName = uName;
		this.isLibraryAdmin = isLibraryAdmin;
		this.isJWCAdmin = isJWCAdmin;
		this.isShopAdmin = isShopAdmin;
	}
	
	public User(String uID, String uRole, String uName, boolean isLibraryAdmin, boolean isJWCAdmin, boolean isShopAdmin) {
		super();
		this.uID = uID;
		this.uRole = uRole;
		this.uName = uName;
		this.isLibraryAdmin = isLibraryAdmin;
		this.isJWCAdmin = isJWCAdmin;
		this.isShopAdmin = isShopAdmin;
	}
	
	
	public User(String uID, String uPassword, String uRole, String uName) {
		super();
		this.uID = uID;
		this.uPassword = uPassword;
		this.uRole = uRole;
		this.uName = uName;
	}
	
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
	
	public User() {
		super();
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
	
	public String getUName() {
		return uName;
	}
	
	public void setUName(String uName) {
		this.uName = uName;
	}
	
	public boolean isLibraryAdmin() {
		return isLibraryAdmin;
	}
	
	public void setLibraryAdmin(boolean isLibraryAdmin) {
		this.isLibraryAdmin = isLibraryAdmin;
	}
	
	public boolean isJWCAdmin() {
		return isJWCAdmin;
	}
	
	public void setJWCAdmin(boolean isJWCAdmin) {
		this.isJWCAdmin = isJWCAdmin;
	}
	
	public boolean isShopAdmin() {
		return isJWCAdmin;
	}
	
	public void setShopAdmin(boolean isShopAdmin) {
		this.isShopAdmin = isShopAdmin;
	}
	
	
	public boolean isLogin() {
		return isLogin;
	}
	
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

}
