package client.shop;

import javax.swing.JOptionPane;
import conn.common.*;

public class ShopUser {
	private int userCash=50;
	private boolean isShopAdmin=false;
	private String Username;
	
	public ShopUser(){
	}
	
	public ShopUser(User a){
		isShopAdmin = a.isShopAdmin();
	}
	
	public int getUserCash(){
		return userCash;
	}
	
	public boolean isShopAdmin(){
		return isShopAdmin;
	}
	public boolean buy( Goods a){
		if(userCash>a.getGoodsPrice()&&a.getGoodNumber()>0){
			userCash=userCash-a.getGoodsPrice();
			System.out.println("购买成功");
			JOptionPane.showMessageDialog(null,"购买成功");
			return true;
		}
		else if(userCash<a.getGoodsPrice()){
			System.out.println("余额不足，无法完成购买");
			JOptionPane.showMessageDialog(null,"余额不足，无法完成购买");
			return false;
			}
		else{
			JOptionPane.showMessageDialog(null,"余额不足，无法完成购买");
			System.out.println("余额不足，无法完成购买");
			return false;
		}
	}
	
	//管理员增加商品
	public void add(Goods a){
		a.setGoodNumber(a.getGoodNumber()+1);
	}
	
}
