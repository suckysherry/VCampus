package client.ui;

import java.sql.*;
import java.util.Vector;
import conn.common.*;

public class UserManageOperateDB {
	public static String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static String CONN_URL = "jdbc:mysql://127.0.0.1:3306/vc";
	public static String USER_NAME = "root";
	public static String PASSWORD = "123212321";
	public Statement connStat;
	protected Connection conn;
	
	/**
	 * 获取用户表格信息
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public Vector<User> selectUser() throws SQLException {
		Vector<User> users = new Vector<User>();
		ResultSet rsGetUser = null;

		try {
			Class.forName(DRIVER_NAME);	
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD); 
			

			Statement stmt = conn.createStatement();
			rsGetUser = stmt.executeQuery("SELECT * FROM USER"); 
			
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
	
	
	
	/**
	 * 创建新用户
	 * @param userid
	 * @param userPsw
	 * @param userRole
	 * @throws SQLException
	 */
	public boolean createUserInDB(String id, String Psw, String name, String role, Boolean lib, Boolean jwc, Boolean shop) throws SQLException{
		
		try {
			Class.forName(DRIVER_NAME);	
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD); 
			

			PreparedStatement stmt = conn.prepareStatement("INSERT INTO USER(id, password, name, role, isLibraryAdmin, isJWCAdmin, isShopAdmin) VALUES (?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, id);
			stmt.setString(2, Psw);
			stmt.setString(3, name);
			stmt.setString(4, role);
			stmt.setBoolean(5, lib);
			stmt.setBoolean(6, jwc);
			stmt.setBoolean(7, shop);
			stmt.executeUpdate(); 
			
			if(role.equals("student")) {
				PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO STUDENT(ID, Name, role) VALUES (?, ?, ?)");
				stmt2.setString(1, id);
				stmt2.setString(2, name);
				stmt2.setString(3, role);
				stmt2.executeUpdate(); 
			} else if(role.equals("teacher")) {
				PreparedStatement stmt3 = conn.prepareStatement("INSERT INTO TEACHER(ID, Name, Role) VALUES (?, ?, ?)");
				stmt3.setString(1, id);
				stmt3.setString(2, name);
				stmt3.setString(3, role);
				stmt3.executeUpdate(); 
			}
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
	
	public boolean modifyUserRoleInDB(String userID, String userRole, String userName, Boolean libAdmin, Boolean jwcAdmin, Boolean shopAdmin) throws SQLException{

		ResultSet rsModifySt = null;
		
		try {
			Class.forName(DRIVER_NAME);	
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD); 
			

			PreparedStatement stmt = conn.prepareStatement("UPDATE USER SET role = ?, isLibraryAdmin = ?, isJWCAdmin = ?, isShopAdmin = ? WHERE id = ?");
			stmt.setString(1, userRole);
			stmt.setBoolean(2, libAdmin);
			stmt.setBoolean(3, jwcAdmin);
			stmt.setBoolean(4, shopAdmin);
			stmt.setString(5, userID);
			stmt.executeUpdate();
			
			if(userRole.equals("student")) {
				PreparedStatement stmt2 = conn.prepareStatement("SELECT * FROM STUDENT WHERE ID = ?");
				stmt2.setString(1, userID);
				rsModifySt = stmt2.executeQuery();
				if(rsModifySt.next() == false) {
					PreparedStatement stmt3 = conn.prepareStatement("INSERT INTO STUDENT(ID, Name, role) VALUES (?, ?, ?)");
					stmt3.setString(1, userID);
					stmt3.setString(2, userName);
					stmt3.setString(3, userRole);
					stmt3.executeUpdate(); 
				} 
				
				PreparedStatement stmt4 = conn.prepareStatement("SELECT * FROM TEACHER WHERE ID = ?");
				stmt4.setString(1, userID);
				rsModifySt = stmt4.executeQuery();
				if(rsModifySt.next() == true) {
					PreparedStatement stmt5 = conn.prepareStatement("DELETE FROM TEACHER WHERE ID = ？");
					stmt5.setString(1, userID);
					stmt5.executeUpdate(); 
				}
				
			} else if(userRole.equals("teacher")) {
				PreparedStatement stmt3 = conn.prepareStatement("SELECT * FROM TEACHER WHERE ID = ?");
				stmt3.setString(1, userID);
				rsModifySt = stmt3.executeQuery();
				if(rsModifySt.next() == false) {
					PreparedStatement stmt4 = conn.prepareStatement("INSERT INTO TEACHER(ID, Name, Role) VALUES (?, ?, ?)");
					stmt4.setString(1, userID);
					stmt4.setString(2, userName);
					stmt4.setString(3, userRole);
					stmt4.executeUpdate(); 
				}
				
				PreparedStatement stmt6 = conn.prepareStatement("SELECT * FROM STUDENT WHERE ID = ?");
				stmt6.setString(1, userID);
				rsModifySt = stmt6.executeQuery();
				if(rsModifySt.next() == true) {
					PreparedStatement stmt7 = conn.prepareStatement("DELETE FROM STUDENT WHERE ID = ？");
					stmt7.setString(1, userID);
					stmt7.executeUpdate(); 
				}
			}
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
