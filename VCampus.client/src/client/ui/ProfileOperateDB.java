package client.ui;

import java.sql.*;
import conn.common.Teacher;
import conn.common.Student;

public class ProfileOperateDB {
	public static String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static String CONN_URL = "jdbc:mysql://127.0.0.1:3306/vc";
	public static String USER_NAME = "root";
	public static String PASSWORD = "123212321";
	public Statement connStat;
	protected Connection conn;
	
	
	
	
	/**
	 * 查询用户ID对应的学生资料
	 * @throws SQLException
	 */
	
	public Student queryStudentInDB(String quID) throws SQLException{
		
		Student student = null;
		ResultSet rsQueryStudent = null;

		try {
			Class.forName(DRIVER_NAME);	
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD); 
			

			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM STUDENT WHERE ID = ?");
			stmt.setString(1, quID);
			rsQueryStudent = stmt.executeQuery();  
			
			if(rsQueryStudent.next() == true) {
				student = new Student();
				student.setUBirthday(rsQueryStudent.getString("birthday"));
				student.setUCard(rsQueryStudent.getString("Num"));
				student.setUClass(rsQueryStudent.getString("class"));
				student.setUHometown(rsQueryStudent.getString("hometown"));
				student.setUID(rsQueryStudent.getString("ID"));
				student.setUName(rsQueryStudent.getString("Name"));
				student.setURole(rsQueryStudent.getString("role"));
				student.setUSex(rsQueryStudent.getString("sex"));
			}			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return student;
	}
	
	/**
	 * 查询用户 ID 对应教师资料
	 * @throws SQLException
	 */
	
	public Teacher queryTeacherInDB(String quID) throws SQLException{
		
		Teacher teacher = null;
		ResultSet rsQueryTeacher = null;

		try {
			Class.forName(DRIVER_NAME);	
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD); 
			

			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM TEACHER WHERE ID = ?");
			stmt.setString(1, quID);
			rsQueryTeacher = stmt.executeQuery();  
			
			if(rsQueryTeacher.next() == true) {
				teacher = new Teacher();
				teacher.setUCard(rsQueryTeacher.getString("Num"));
				teacher.setUClass(rsQueryTeacher.getString("class"));
				teacher.setUID(rsQueryTeacher.getString("ID"));
				teacher.setUName(rsQueryTeacher.getString("Name"));
				teacher.setURole(rsQueryTeacher.getString("Role"));
				teacher.setUSex(rsQueryTeacher.getString("sex"));
			}			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return teacher;
	}


}