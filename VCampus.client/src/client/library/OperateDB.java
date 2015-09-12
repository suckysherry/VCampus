package client.library;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;

import com.mysql.jdbc.PreparedStatement;
import com.sun.accessibility.internal.resources.accessibility;
import com.sun.imageio.plugins.common.ImageUtil;
import com.sun.prism.Graphics;

import sun.nio.cs.ext.ISCII91;

public class OperateDB {
	public static String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static String CONN_URL = "jdbc:mysql://107.170.216.207:3306/VirtualCampus";
	public static String USER_NAME = "vc_admin";
	public static String PASSWORD = "12345678";
	public Statement connStat;
	protected Connection conn;
	
	
	/**
	 * 输出数据库中所有用户
	 * @throws SQLException
	 */
	@SuppressWarnings("finally")
	public Vector<Book> searchBookInDB(String keyWords,int method){
		String sqlSearchBook = null;
		if(method == 1) sqlSearchBook = "SELECT * FROM `Book` WHERE `bookID` LIKE '%"+ keyWords +"%'";
		if(method == 2) sqlSearchBook = "SELECT * FROM `Book` WHERE `bookName` LIKE '%" + keyWords + "%'";
		if(method == 3 || method == 0) sqlSearchBook = "SELECT * FROM `Book` WHERE `bookID` LIKE '%" + keyWords + "%' OR `bookName` LIKE '%"+ keyWords +"%' or `bookAuthor` LIKE '%"+ keyWords +"%' or `bookPublisher` LIKE '%"+ keyWords +"%' or `bookPublishingDate` LIKE '%"+ keyWords +"%' or `bookDetails` LIKE '%"+ keyWords +"%'";
		ResultSet rsSearchBookUser = null;
		Vector<Book> bookList = new Vector<Book>();
		try {
			Class.forName(DRIVER_NAME);	//加载 mysql jdbc
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD); //链接远程数据库
			Statement stmt = conn.createStatement();  //用于在已经建立链接的基础上向数据库发送要执行的sql语句
			// 常用sql语句 http://www.cnblogs.com/yubinfeng/archive/2010/11/02/1867386.html
			rsSearchBookUser = stmt.executeQuery(sqlSearchBook);  //通过执行sql语句生成数据库结果集的数据表
			while(rsSearchBookUser.next()){
					Book a = new Book();
					a.setBookName(rsSearchBookUser.getString("bookName"));
					a.setBookId(rsSearchBookUser.getString("bookID"));
					a.setBookAuthor(rsSearchBookUser.getString("bookAuthor"));
					a.setBookDetails(rsSearchBookUser.getString("bookDetails"));
					a.setBookPublisher(rsSearchBookUser.getString("bookPublisher"));
					a.setBookPublishingDate(rsSearchBookUser.getString("bookPublishingDate"));
					a.setBookNum(rsSearchBookUser.getInt("bookNum"));
					a.setBookBorrowedNum(rsSearchBookUser.getInt("bookBorrowedNum"));
					if(rsSearchBookUser.getBinaryStream("bookPic") != null){
						InputStream in = rsSearchBookUser.getBinaryStream("bookPic");
						byte[] b=new byte[40000];  
						in.read(b); //从InputStream对象中读取数据放进byte数组中  
						//实例化OutputStream对象，在D盘创建一个图片文件  
						String tmpPath = System.getProperty("java.io.tmpdir");
						FileOutputStream out=new FileOutputStream(tmpPath+a.getBookId()+".jpg");  
						//将文件输出，内容则为byte数组里面的数据  
						out.write(b);  
						out.flush();  
						out.close();
						File picFile = new File(tmpPath+a.getBookId()+".jpg");
						a.setBookPic(picFile);
					}
					
					bookList.add(a);
				}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			// 关闭与数据库链接 ！！ 必须要写！！
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bookList;
		}
		
	}

	public Vector<Book> searchBookInDB(String keyWords){
		String sqlSearchBook = "SELECT * FROM `Book` WHERE `bookID` LIKE '%" + keyWords + "%' OR `bookName` LIKE '%"+ keyWords +"%' or `bookAuthor` LIKE '%"+ keyWords +"%' or `bookPublisher` LIKE '%"+ keyWords +"%' or `bookPublishingDate` LIKE '%"+ keyWords +"%' or `bookDetails` LIKE '%"+ keyWords +"%'";
		ResultSet rsSearchBookUser = null;
		Vector<Book> bookList = new Vector<Book>();
		try {
			Class.forName(DRIVER_NAME);	//加载 mysql jdbc
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD); //链接远程数据库
			Statement stmt = conn.createStatement();  //用于在已经建立链接的基础上向数据库发送要执行的sql语句
			// 常用sql语句 http://www.cnblogs.com/yubinfeng/archive/2010/11/02/1867386.html
			rsSearchBookUser = stmt.executeQuery(sqlSearchBook);  //通过执行sql语句生成数据库结果集的数据表
			while(rsSearchBookUser.next()){
					Book a = new Book();
					a.setBookName(rsSearchBookUser.getString("bookName"));
					a.setBookId(rsSearchBookUser.getString("bookID"));
					a.setBookAuthor(rsSearchBookUser.getString("bookAuthor"));
					a.setBookDetails(rsSearchBookUser.getString("bookDetails"));
					a.setBookPublisher(rsSearchBookUser.getString("bookPublisher"));
					a.setBookPublishingDate(rsSearchBookUser.getString("bookPublishingDate"));
					a.setBookNum(rsSearchBookUser.getInt("bookNum"));
					a.setBookBorrowedNum(rsSearchBookUser.getInt("bookBorrowedNum"));
					if(rsSearchBookUser.getBinaryStream("bookPic") != null){
						InputStream in = rsSearchBookUser.getBinaryStream("bookPic");
						byte[] b=new byte[40000];  
						in.read(b); //从InputStream对象中读取数据放进byte数组中  
						//实例化OutputStream对象，在D盘创建一个图片文件  
						String tmpPath = System.getProperty("java.io.tmpdir");
						FileOutputStream out=new FileOutputStream(tmpPath+a.getBookId()+".jpg");  
						//将文件输出，内容则为byte数组里面的数据  
						out.write(b);  
						out.flush();  
						out.close();
						File picFile = new File(tmpPath+a.getBookId()+".jpg");
						a.setBookPic(picFile);
					}
					
					bookList.add(a);
				}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			// 关闭与数据库链接 ！！ 必须要写！！
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bookList;
		}
		
	}

	/**
	 * 在数据库中插入新的用户信息
	 * @param userID
	 * @param password
	 * @param role
	 * @throws SQLException
	 */
	public void updateBookInDB(Book book, String oldID) {
		InputStream is = null;
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
			is=new FileInputStream(book.getBookPic());  
			stmt.setBinaryStream(9,is,is.available());
			stmt.execute();
			stmt = conn.prepareStatement("UPDATE BorrowInfo SET bookID = ? WHERE bookID = ?");
			stmt.setString(1, book.getBookId());
			stmt.setString(2, oldID);
			stmt.execute();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			
			try {
				is.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void delBookInDB(Book currentBook) {
		// TODO Auto-generated method stub
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD);
			java.sql.PreparedStatement stmt = conn.prepareStatement("DELETE FROM Book WHERE bookID = ?"); 
			stmt.setString(1, currentBook.getBookId());
			stmt.execute();
			
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


	public void addBookToDB(Book book) {
		InputStream is = null;
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
			is=new FileInputStream(book.getBookPic());  
			stmt.setBinaryStream(9,is,is.available());
			stmt.execute();
		
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			
			try {
				is.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	public Vector<String> searchBorrowInfoInDB(String bookId) {
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
			stmt.setString(1, bookId);
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


	public Vector<String> searchNonreturnedBorrowInfoInDB(String bookId) {
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
			stmt.setString(1, bookId);
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


	public int returnBook(String userID, String bookID){
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


	public int[] updateCurrentAuthority() {
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



}
