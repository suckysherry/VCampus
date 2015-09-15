package server.connDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import conn.common.Student;
import conn.common.Teacher;
import conn.common.User;

/**
 * 
 * @author daisiqi
 *
 */
public class OperateDB {
	public static String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static String CONN_URL = "jdbc:mysql://127.0.0.1:3306/vc?useUnicode=true&characterEncoding=utf8";
	public static String USER_NAME = "root";
	public static String PASSWORD = "123212321";
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

	public Vector<Student> selectStudent(String sql) throws SQLException {
		Vector<Student> students = new Vector<Student>();
		ResultSet rsQueryStudent = null;

		try {
			Class.forName(DRIVER_NAME);	
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD); 

			Statement stmt = conn.createStatement();
			rsQueryStudent = stmt.executeQuery(sql);  
			
			while(rsQueryStudent.next() == true) {
				Student student = new Student();
				student.setUBirthday(rsQueryStudent.getString("birthday"));
				student.setUCard(rsQueryStudent.getString("Num"));
				student.setUClass(rsQueryStudent.getString("class"));
				student.setUHometown(rsQueryStudent.getString("hometown"));
				student.setUID(rsQueryStudent.getString("ID"));
				student.setUName(rsQueryStudent.getString("Name"));
				student.setURole(rsQueryStudent.getString("role"));
				student.setUSex(rsQueryStudent.getString("sex"));
				students.add(student);
			}			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return students;
	}

	public Vector<Teacher> selectTeacher(String sql) throws SQLException {
		Vector<Teacher> teachers = new Vector<Teacher>();
		ResultSet rsQueryTeacher = null;

		try {
			Class.forName(DRIVER_NAME);	
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD); 
			

			Statement stmt = conn.createStatement();
			rsQueryTeacher = stmt.executeQuery(sql);   
			
			while(rsQueryTeacher.next() == true) {
				Teacher teacher = new Teacher();
				teacher.setUCard(rsQueryTeacher.getString("Num"));
				teacher.setUClass(rsQueryTeacher.getString("class"));
				teacher.setUID(rsQueryTeacher.getString("ID"));
				teacher.setUName(rsQueryTeacher.getString("Name"));
				teacher.setURole(rsQueryTeacher.getString("Role"));
				teacher.setUSex(rsQueryTeacher.getString("sex"));
				teachers.add(teacher);
			}			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return teachers;
	}

	public Boolean insert(String sql) throws SQLException {
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
	

}
