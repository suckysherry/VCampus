package client.shop;

import javax.swing.*;
import java.net.*;

public class Goods {
	private String goodsName;//商品名称
	private String goodsType;//商品类型
	private int goodsPrice;//商品价格
	private Icon goodsImage;//商品图片
	private int goodsNumber;//商品数量
	
	public Goods(String goodsname,String goodstype,int goodsprice,int goodsnumber){
		goodsName = goodsname;
		goodsType = goodstype;
		goodsPrice =goodsprice;
		goodsNumber =goodsnumber;
		
		
		//URL url = Goods.class.getResource(picturename);
		//goodsImage = new ImageIcon(url);
	}
	
	public void setGoodsName(String goodsname){
		goodsName =goodsname;
	}
	public String getGoodsName(){
		return goodsName;
	}
	
	public void setGoodsType(String goodstype){
		goodsType =goodstype;
	}
	public String getGoodsType(){
		return goodsType;
	}
	
	public void setGoodsPrice(int goodsprice){
		goodsPrice =goodsprice;
	}
	public int getGoodsPrice(){
		return goodsPrice;
	}
	
	public void setGoodsImage(String picturename){
		URL url = Goods.class.getResource(picturename);
		goodsImage = new ImageIcon(url);
	}
	public Icon getGoodsImage(){
		return goodsImage;
	}
	
	public void setGoodNumber(int goodsnumber){
		goodsNumber=goodsnumber;
	}
	public int getGoodNumber(){
		return goodsNumber;
	}
}

