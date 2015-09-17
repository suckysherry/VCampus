package server.connDb;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sun.org.apache.regexp.internal.recompile;

import conn.common.Book;
import conn.common.Goods;
import conn.common.Student;
import conn.common.Teacher;
import conn.common.User;

/**
 * 
 * @author 戴思琪
 *
 */
public class OperateDB {
	public static String DRIVER_NAME = "com.mysql.jdbc.Driver";
//	public static String CONN_URL = "jdbc:mysql://107.170.216.207:3306/VirtualCampus";
//	public static String USER_NAME = "vc_admin";
//	public static String PASSWORD = "12345678";
	public static String CONN_URL = "jdbc:mysql://127.0.0.1:3306/vc?useUnicode=true&characterEncoding=utf8";
	public static String USER_NAME = "root";
	public static String PASSWORD = "123212321";
	public static String BOOK_PIC_RESTORE_DIR = "/Users/Suckysherry/Code/workspace/VCampus/VCampus.client/src/res/bookpic/";
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

	public List<Goods> selectGoods(String sql) throws SQLException {
		List<Goods> allgoods= new ArrayList<>();
		ResultSet rs= null;

		try {
			Class.forName(DRIVER_NAME);	
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD); 
			

			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);   
			
			while(rs.next() == true) {
				Goods newgoods =new Goods(rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getInt(4), rs.getString(5));
				allgoods.add(newgoods);
			}			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return allgoods;
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

	public Boolean delete(String sql) throws SQLException {
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
	//lib
	public String randName() {  
        // TODO Auto-generated method stub  
        String result="";  
        for(int i=0;i<28;i++){  
            int intVal=(int)(Math.random()*26+97);  
            result=result+(char)intVal;  
        }  
        return result;  
    }
	
	public Vector<Book> selectBooks(String sql) {
		Vector<Book> bookList = new Vector<Book>();
		ResultSet rs = null;
		try {
			Class.forName(DRIVER_NAME);	
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD); 
			

			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);   
			
			while(rs.next()){
				Book a = new Book();
				a.setBookName(rs.getString("bookName"));
				a.setBookId(rs.getString("bookID"));
				a.setBookAuthor(rs.getString("bookAuthor"));
				a.setBookDetails(rs.getString("bookDetails"));
				a.setBookPublisher(rs.getString("bookPublisher"));
				a.setBookPublishingDate(rs.getString("bookPublishingDate"));
				a.setBookNum(rs.getInt("bookNum"));
				a.setBookBorrowedNum(rs.getInt("bookBorrowedNum"));
				a.setBookPic(rs.getString("bookPic"));
				FileInputStream fi = new FileInputStream(BOOK_PIC_RESTORE_DIR + a.getBookPic()+".jpg");
				byte[] b = new byte[100000];
				fi.read(b);
				a.setPicBytes(b);
				bookList.add(a);
			}		
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bookList;
	}

	public void updateBook(Book book, String oldID) {
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD);
			java.sql.PreparedStatement stmt = conn.prepareStatement("UPDATE Book SET bookId = ?, bookName = ?, bookAuthor = ?, bookPublisher = ?, bookPublishingDate = ?, bookDetails = ?, bookNum = ?, bookBorrowedNum = ?, bookPic = ? WHERE bookID = ?"); 
			stmt.setString(1, book.getBookId());
			stmt.setString(2, book.getBookName());
			stmt.setString(3, book.getBookAuthor());
			stmt.setString(4, book.getBookPublisher());
			stmt.setString(5, book.getBookPublishingDate());
			stmt.setString(6, book.getBookDetails());
			stmt.setInt(7, book.getBookNum());
			stmt.setInt(8, book.getBookBorrowedNum());
			stmt.setString(10, oldID);
			stmt.setString(9,book.getBookPic());
			stmt.execute();
			stmt = conn.prepareStatement("UPDATE BorrowInfo SET bookID = ? WHERE bookID = ?");
			stmt.setString(1, book.getBookId());
			stmt.setString(2, oldID);
			stmt.execute();
			FileOutputStream fo = new FileOutputStream(BOOK_PIC_RESTORE_DIR+book.getBookPic()+".jpg");
			fo.write(book.getPicBytes());
			fo.flush();
			fo.close();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void addBook(Book book) {
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD);
			java.sql.PreparedStatement stmt = conn.prepareStatement("INSERT INTO Book VALUES (?,?,?,?,?,?,?,?,?)"); 
			stmt.setString(1, book.getBookId());
			stmt.setString(2, book.getBookName());
			stmt.setString(3, book.getBookAuthor());
			stmt.setString(4, book.getBookPublisher());
			stmt.setString(5, book.getBookPublishingDate());
			stmt.setString(6, book.getBookDetails());
			stmt.setInt(7, book.getBookNum());
			stmt.setInt(8, book.getBookBorrowedNum());
			book.setBookPic(randName());
			stmt.setString(9,(book.getBookPic()));
			stmt.execute();
			FileOutputStream fo = new FileOutputStream(BOOK_PIC_RESTORE_DIR+book.getBookPic()+".jpg");
			fo.write(book.getPicBytes());
			fo.flush();
			fo.close();
		
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public int[] getCurrentAuthority() {
		int[] numbers = {0,0};
		ResultSet rst;
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD);
			java.sql.PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Preferences"); 
			rst = stmt.executeQuery();
			int i = 0;
			while(rst.next()){
				numbers[i] = rst.getInt("optionNum");
				i += 1;
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		return numbers;
		
	}

	public boolean updateAuthority(int maxDay, int maxBookNum) {
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD);
			java.sql.PreparedStatement stmt = conn.prepareStatement("UPDATE Preferences SET optionNum = ? WHERE optionName = 'maxBorrowDays'"); 
			stmt.setInt(1,maxDay);
			stmt.execute();
			stmt = conn.prepareStatement("UPDATE Preferences SET optionNum = ? WHERE optionName = 'maxBorrowNum'"); 
			stmt.setInt(1,maxBookNum);
			stmt.execute();
			return true;
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		return false;
	}

	public Vector<String> searchNonreturnedBorrowInfoInDB(String bookID) {
		ResultSet rst = null;
		int maxBorrowDays;
		Vector<String> borrowList = new Vector<String>();
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD);
			java.sql.PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Preferences WHERE optionName = ?"); 
			stmt.setString(1, "maxBorrowDays");
			rst = stmt.executeQuery();
			rst.next();
			maxBorrowDays = rst.getInt("optionNum");
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");   
			
			stmt = conn.prepareStatement("SELECT * FROM BorrowInfo WHERE bookID = ? AND returned = ?"); 
			stmt.setString(1, bookID);
			stmt.setInt(2, 0);
			rst = stmt.executeQuery();
			while(rst.next()){
				String newInfo = new String();
				java.util.Date date= format.parse(rst.getString("date")); 
				long afterTime=(date.getTime()/1000)+60*60*24*maxBorrowDays; 
				date.setTime(afterTime*1000);   
				String returnDate=format.format(date);   
				String yesOrNo = (rst.getString("returned").equals("1"))?"是":"否";
				newInfo = rst.getString("name") + " - " + rst.getString("ID") + " \t" + "借于  " + rst.getString("date") + "  应还日期: " +returnDate+ "    \t是否归还: " + yesOrNo;
				borrowList.addElement(newInfo);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return borrowList;
		}
	}

	public Vector<String> searchBorrowInfoInDB(String bookID) {
		ResultSet rst = null;
		int maxBorrowDays;
		Vector<String> borrowList = new Vector<String>();
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD);
			java.sql.PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Preferences WHERE optionName = ?"); 
			stmt.setString(1, "maxBorrowDays");
			rst = stmt.executeQuery();
			rst.next();
			maxBorrowDays = rst.getInt("optionNum");
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");   
			
			stmt = conn.prepareStatement("SELECT * FROM BorrowInfo WHERE bookID = ?"); 
			stmt.setString(1, bookID);
			rst = stmt.executeQuery();
			while(rst.next()){
				String newInfo = new String();
				java.util.Date date= format.parse(rst.getString("date")); 
				long afterTime=(date.getTime()/1000)+60*60*24*maxBorrowDays; 
				date.setTime(afterTime*1000);   
				String returnDate=format.format(date);   
				String yesOrNo = (rst.getString("returned").equals("1"))?"是":"否";
				newInfo = rst.getString("name") + " - " + rst.getString("ID") + "    \t" + "借于  " + rst.getString("date") + "  应还日期: " +returnDate+ "    \t是否归还: " + yesOrNo;
				borrowList.addElement(newInfo);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return borrowList;
		}
	}

	public int returnBook(String userID, String bookID) {
		ResultSet rst;
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD);
			java.sql.PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Book WHERE bookID = ?"); 
			stmt.setString(1, bookID);
			rst = stmt.executeQuery();
			if(rst.next() != false){
				stmt = conn.prepareStatement("SELECT * FROM Reader WHERE ID = ?");
				stmt.setString(1, userID);
				rst = stmt.executeQuery();
				if(rst.next() != false){
					stmt = conn.prepareStatement("SELECT * FROM BorrowInfo WHERE ID = ? AND bookID = ? AND returned = ?"); 
					stmt.setString(1, userID);
					stmt.setString(2, bookID);
					stmt.setBoolean(3, false);
					rst = stmt.executeQuery();
					if(rst.next()){
							stmt = conn.prepareStatement("UPDATE BorrowInfo SET returned = ? WHERE ID = ? AND bookID = ?"); 
							stmt.setBoolean(1, true);
							stmt.setString(2, userID);
							stmt.setString(3, bookID);
							stmt.execute();
							stmt = conn.prepareStatement("UPDATE Book SET BookBorrowedNum = BookBorrowedNum - 1 WHERE bookID = ?"); 
							stmt.setString(1, bookID);
							stmt.execute();
							return 1;
					}
					else{
						return 5;
					}
				}
				else{
					return 4;
				}
			}
			else{
				return 3;	
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	public int borrowBook(String userID, String bookID) {
		ResultSet rst;
		int availableNum;
		String userName;
		String bookName;
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD);
			java.sql.PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Preferences WHERE optionName = ?"); 
			stmt.setString(1, "maxBorrowNum");
			rst = stmt.executeQuery();
			rst.next();
			int maxBorrowNum = rst.getInt("optionNum");
			stmt = conn.prepareStatement("SELECT * FROM Book WHERE bookID = ?"); 
			stmt.setString(1, bookID);
			rst = stmt.executeQuery();
			if(rst.next() != false){
				bookName = rst.getString("bookName");
				availableNum = rst.getInt("bookNum") - rst.getInt("bookBorrowedNum");
				if(availableNum <= 0) return 6;
				stmt = conn.prepareStatement("SELECT * FROM Reader WHERE ID = ?");
				stmt.setString(1, userID);
				rst = stmt.executeQuery();
				if(rst.next() != false){
					userName = rst.getString("name");
					stmt = conn.prepareStatement("SELECT * FROM BorrowInfo WHERE ID = ? AND bookID = ? AND returned = ?"); 
					stmt.setString(1, userID);
					stmt.setString(2, bookID);
					stmt.setBoolean(3, false);
					rst = stmt.executeQuery();
					if(rst.next() != true){
						stmt = conn.prepareStatement("SELECT * FROM BorrowInfo WHERE ID = ? AND returned = ? AND returned = ?");
						stmt.setBoolean(2, false);
						stmt.setString(1, userID);
						stmt.setBoolean(3, false);
						rst = stmt.executeQuery();
						int i = 0;
						while(rst.next()){
							i += 1;
						}
						if(i < maxBorrowNum){
							
							stmt = conn.prepareStatement("INSERT INTO BorrowInfo VALUES(?,?,?,?,?,?)"); 
							stmt.setString(1, userName);
							stmt.setString(2, userID);
							stmt.setString(3, bookID);
							stmt.setString(4, bookName);
							java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
							Calendar cal = Calendar.getInstance();// 取当前日期。
							stmt.setString(5, format.format(cal.getTime()));
							stmt.setBoolean(6, false);
							stmt.execute();
							stmt = conn.prepareStatement("UPDATE Book SET BookBorrowedNum = BookBorrowedNum + 1 WHERE bookID = ?"); 
							stmt.setString(1, bookID);
							stmt.execute();
							return 1;
						}
						else{
							return 2;
						}
					}
					else{
						return 5;
					}
				}
				else{
					return 4;
				}
			}
			else{
				return 3;
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	public Vector<String> searchUserNonreturnedBorrowInfoInDB(String uid) {
		ResultSet rst = null;//undone
		int maxBorrowDays;
		Vector<String> borrowList = new Vector<String>();
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD);
			java.sql.PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Preferences WHERE optionName = ?"); 
			stmt.setString(1, "maxBorrowDays");
			rst = stmt.executeQuery();
			rst.next();
			maxBorrowDays = rst.getInt("optionNum");
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");   
			
			stmt = conn.prepareStatement("SELECT * FROM BorrowInfo WHERE ID = ? AND returned = ?"); 
			stmt.setString(1, uid);
			stmt.setBoolean(2, false);
			rst = stmt.executeQuery();
			while(rst.next()){
				String newInfo = new String();
				java.util.Date date= format.parse(rst.getString("date")); 
				long afterTime=(date.getTime()/1000)+60*60*24*maxBorrowDays; 
				date.setTime(afterTime*1000);   
				String returnDate=format.format(date);   
				String yesOrNo = (rst.getString("returned").equals("1"))?"是":"否";
				newInfo = rst.getString("bookName") + " - " + rst.getString("bookID") + " \t" + "借于  " + rst.getString("date") + "  应还日期: " +returnDate+ "    \t是否归还: " + yesOrNo;
				borrowList.addElement(newInfo);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return borrowList;
		}
	}

	public Vector<String> searchUserBorrowInfoInDB(String uid) {
		ResultSet rst = null;
		int maxBorrowDays;
		Vector<String> borrowList = new Vector<String>();
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD);
			java.sql.PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Preferences WHERE optionName = ?"); 
			stmt.setString(1, "maxBorrowDays");
			rst = stmt.executeQuery();
			rst.next();
			maxBorrowDays = rst.getInt("optionNum");
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");   
			
			
			stmt = conn.prepareStatement("SELECT * FROM BorrowInfo WHERE ID = ?"); 
			stmt.setString(1, uid);
			rst = stmt.executeQuery();
			while(rst.next()){
				String newInfo = new String();
				java.util.Date date= format.parse(rst.getString("date")); 
				long afterTime=(date.getTime()/1000)+60*60*24*maxBorrowDays; 
				date.setTime(afterTime*1000);   
				String returnDate=format.format(date);   
				String yesOrNo = (rst.getString("returned").equals("1"))?"是":"否";
				newInfo = rst.getString("bookName") + " - " + rst.getString("bookID") + " \t" + "借于  " + rst.getString("date") + "  应还日期: " +returnDate+ "    \t是否归还: " + yesOrNo;
				borrowList.addElement(newInfo);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return borrowList;
		}
	}



	//endlib

}
