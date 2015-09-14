package client.ui;

import java.sql.*;
import java.util.Vector;
import conn.common.*;

public class UserManageOperateDB {
	public static String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static String CONN_URL = "jdbc:mysql://107.170.216.207:3306/VirtualCampus";
	public static String USER_NAME = "vc_admin";
	public static String PASSWORD = "12345678";
	public Statement connStat;
	protected Connection conn;
	
	
	
	
	/**
	 * 创建新用户
	 * @param userid
	 * @param userPsw
	 * @param userRole
	 * @throws SQLException
	 */
	
	public boolean createUserInDB(String userid, String userPsw, String userRole) throws SQLException{
		
		try {
			Class.forName(DRIVER_NAME);	
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD); 
			

			PreparedStatement stmt = conn.prepareStatement("INSERT INTO USER(id, password, role) VALUES (?, ?, ?)");
			stmt.setString(1, userid);
			stmt.setString(2, userPsw);
			stmt.setString(3, userRole);
			stmt.executeUpdate(); 
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
	
	/**
	 * 修改用户身份
	 * @param userID
	 * @param userRole
	 * @return
	 * @throws SQLException
	 */
	
	public boolean modifyUserRoleInDB(String userID, String userRole) throws SQLException{

		try {
			Class.forName(DRIVER_NAME);	
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD); 
			

			PreparedStatement stmt = conn.prepareStatement("UPDATE USER SET role = ? WHERE id = ?");
			stmt.setString(1, userRole);
			stmt.setString(2, userID);
			stmt.executeUpdate();
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

}
