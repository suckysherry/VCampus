package server.connDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import conn.common.Student;
import conn.common.User;

/**
 * 
 * @author daisiqi
 *
 */
public class OperateDB {
	public static String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static String CONN_URL = "jdbc:mysql://107.170.216.207:3306/VirtualCampus?useUnicode=true&characterEncoding=utf8";
	public static String USER_NAME = "vc_admin";
	public static String PASSWORD = "12345678";
	public Statement connStat;
	protected Connection conn;
	
	public User verifyUser(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		User user = null;
		ResultSet rsVerifyUser = null;

		try {
			Class.forName(DRIVER_NAME);	//加载 mysql jdbc
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD); //链接远程数据库
			

			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM USER WHERE id = ? AND password = ?");
			stmt.setString(1, username);
			stmt.setString(2, password);
			rsVerifyUser = stmt.executeQuery();  //通过执行sql语句生成数据库结果集的数据表
			
			if(rsVerifyUser.next() == true) 
				user = new User(rsVerifyUser.getString("id"), rsVerifyUser.getString("password"), rsVerifyUser.getString("role"), rsVerifyUser.getString("name"), rsVerifyUser.getBoolean("isLibraryAdmin"), rsVerifyUser.getBoolean("isJWCAdmin"), rsVerifyUser.getBoolean("isShopAdmin"));
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return user;
	}

	public Boolean update(String sql) throws SQLException {
		try {
			Class.forName(DRIVER_NAME);	
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD); 
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			return true;
					
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		} finally {
			conn.close();
		}
		
	}

	public Vector<User> selectUser(String sql) throws SQLException {
		Vector<User> users = new Vector<User>();
		ResultSet rsGetUser = null;

		try {
			Class.forName(DRIVER_NAME);	
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD); 
			

			Statement stmt = conn.createStatement();
			rsGetUser = stmt.executeQuery(sql); 
			
			while(rsGetUser.next() == true)  {
				User user = new User(rsGetUser.getString("id"), rsGetUser.getString("role"), rsGetUser.getString("name"), rsGetUser.getBoolean("isLibraryAdmin"), rsGetUser.getBoolean("isJWCAdmin"), rsGetUser.getBoolean("isShopAdmin"));
				users.add(user);
			}
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return users;
	}
	
	

}
