package conn.common;

import java.io.Serializable;

public class Goods implements Serializable{
	private String goodsName;//商品名称
	private String goodsType;//商品类型
	private int goodsPrice;//商品价格
	private String picPath;//商品图片
	private int goodsNumber;//商品数量
	
	public Goods(String goodsname,String goodstype,int goodsprice,int goodsnumber,String picpath){
		goodsName = goodsname;
		goodsType = goodstype;
		goodsPrice =goodsprice;
		goodsNumber =goodsnumber;
		picPath = picpath;
		
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
	
	public void setGoodsImage(String path){
		picPath =path;
	}
	public String getGoodsImage(){
		return picPath;
	}
	
	public void setGoodNumber(int goodsnumber){
		goodsNumber=goodsnumber;
	}
	public int getGoodNumber(){
		return goodsNumber;
	}

}
