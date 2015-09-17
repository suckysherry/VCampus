/*
 * classname:ShopUser
 * 
 * Date：2015，9，15
 * 
 * create shopuser for the shop role 
 */
package client.shop;

import javax.swing.JOptionPane;

import conn.common.User;

public class ShopUser {
	
	private boolean isShopAdmin=true;
	private String Username;
	
	public ShopUser(){
	}
	
	public ShopUser(User a){
		isShopAdmin = a.isShopAdmin();
	}
	
	public boolean isShopAdmin(){
		return isShopAdmin;
	}

}
