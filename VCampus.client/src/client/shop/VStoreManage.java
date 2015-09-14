package client.shop;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

//该类用于封装一些功能
public class VStoreManage {
	public static String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static String CONN_URL = "jdbc:mysql://107.170.216.207:3306/VirtualCampus";
	public static String USER_NAME = "vc_admin";
	public static String PASSWORD = "12345678";
	protected Connection conn;
	private VStoreManage(){}
	private static VStoreManage instance =new VStoreManage();
	
	public static VStoreManage getVSM(){
		return instance;
	}
	
	//连接数据库，读取所有商品的信息，并且为每个商品生成单独的对象
	public  List<Goods> getAllGoods(){
		List<Goods> allgoods =new ArrayList<>();
		String url ="jdbc:odbc:chatroom";
		try{
			Class.forName(DRIVER_NAME);	//加载 mysql jdbc
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD); 
			Statement stmt =conn.createStatement();
			String sql = "SELECT * FROM Goods"; 
			
			ResultSet rsPrintUser = stmt.executeQuery(sql); 
			while(rsPrintUser.next()){
				allgoods.add (new Goods(rsPrintUser.getString("goodsname"),rsPrintUser.getString("goodsType"),
						rsPrintUser.getInt("goodsPrice"),rsPrintUser.getInt("goodsNumber")));
			}
			
		}catch(SQLException e1){
			e1.printStackTrace(); 
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		return allgoods;
	}
	
	//创建所有商品在主页面显示的标签，并且为每个标签添加鼠标响应
	public List<JLabel> getAllJLabel(ShopUser b,List <Goods> a){
		List<JLabel> jlabel = new ArrayList<>();
		for(int i = 0;i<a.size();i++){
			JLabel jl= new JLabel(a.get(i).getGoodsName());
			jl.setSize(3, 5);
			final Goods m = a.get(i);
			final ShopUser x = b;
			
			jl.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e) {
							new GoodsInformation(x,m);
						}
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
				}
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
				}
				
			});
			jlabel.add(jl);
		}
		return jlabel;
	}
	
	public void AddGoods(String name,int number,String type,int price)
	{
		String sql = "insert into Goods (goodsName, goodsNumber, goodsType,goodsPrice) "
				+ "values ('"+name+"','"+number+"','"+type+"','"+price+"')";
		try{
			Class.forName(DRIVER_NAME);	//加载 mysql jdbc
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD); 

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e1){
			e1.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
