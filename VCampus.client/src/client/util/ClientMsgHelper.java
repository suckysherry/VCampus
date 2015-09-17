package client.util;

import conn.common.Book;
import conn.common.Message;
import conn.common.MessageType;

/**
 * 消息类方法
 * @author 戴思琪
 *
 */
public class ClientMsgHelper {
	protected Message msg;
	protected ClientSrv clientsrv;
	
	public Message getMsg() {
		return msg;
	}

	public ClientMsgHelper(){
		this.msg = new Message();
	}
	
	public void clearMsg(){
		this.msg = new Message();
	}

	public void sendMsg() {
		// 发送消息
		if( clientsrv == null){
			clientsrv = new ClientSrv();
		}
		clientsrv.send(msg);
			
	}

	public void recieveMsg() {
		// 接收消息
		if( clientsrv == null){
			clientsrv = new ClientSrv();
		}
		this.msg = clientsrv.receive();
		
	}

	public Object getDataInMsg(){
		return msg.getData();
	}
	
	public boolean getState() {
		return msg.getState();
	}

	public void disconnect() {
		// 关闭当前连接
		if(clientsrv != null){
			clientsrv = null;
		}
		
	}

	

	public void verifyUser(String userName, String password) {
		clearMsg();
		this.msg.setType(MessageType.C_REQ_VERIFYUSER);
		this.msg.setUserName(userName);
		this.msg.setPassword(password);
	}

	public void update(String sql) {
		clearMsg();
		this.msg.setType(MessageType.C_REQ_UPDATE);
		this.msg.setSql(sql);
		
	}

	public void selectUsers(String sql) {
		clearMsg();
		this.msg.setType(MessageType.C_REQ_SELECT_USERS);
		this.msg.setSql(sql);
	}

	public void selectStudents(String sql) {
		clearMsg();
		this.msg.setType(MessageType.C_REQ_SELECT_STUDENTS);
		this.msg.setSql(sql);
	}

	public void selectTeachers(String sql) {
		clearMsg();
		this.msg.setType(MessageType.C_REQ_SELECT_TEACHERS);
		this.msg.setSql(sql);
	}

	public void insert(String sql) {
		clearMsg();
		this.msg.setType(MessageType.C_REQ_INSERT);
		this.msg.setSql(sql);
	}

	public void delete(String sql_d_s) {
		clearMsg();
		this.msg.setType(MessageType.C_REQ_DELETE);
		this.msg.setSql(sql_d_s);
	}

	public void selectGoods(String sql) {
		// TODO Auto-generated method stub
		clearMsg();
		this.msg.setType(MessageType.C_REQ_SELECT_GOODS);
		this.msg.setSql(sql);
	}
	//library
		public void searchBookInDB(String keyWords, int method) {
			clearMsg();
			this.msg.setType(MessageType.C_REQ_SELECT_BOOKS);
			String sqlSearchBook = null;
			if(method == 1) sqlSearchBook = "SELECT * FROM `Book` WHERE `bookID` LIKE '%"+ keyWords +"%'";
			if(method == 2) sqlSearchBook = "SELECT * FROM `Book` WHERE `bookName` LIKE '%" + keyWords + "%'";
			if(method == 3 || method == 0) sqlSearchBook = "SELECT * FROM `Book` WHERE `bookID` LIKE '%" + keyWords + "%' OR `bookName` LIKE '%"+ keyWords +"%' or `bookAuthor` LIKE '%"+ keyWords +"%' or `bookPublisher` LIKE '%"+ keyWords +"%' or `bookPublishingDate` LIKE '%"+ keyWords +"%' or `bookDetails` LIKE '%"+ keyWords +"%'";
			this.msg.setSql(sqlSearchBook);
		}

		public void updateBookInDB(Book currentBook, String oldID) {
			clearMsg();
			this.msg.setType(MessageType.C_REQ_UPDATE_BOOK);
			this.msg.setNewBook(currentBook);
			this.msg.setBookID(oldID);
			
		}
		

		public void delBookInDB(String bookId) {
			clearMsg();
			this.msg.setType(MessageType.C_REQ_DELETE);
			this.msg.setSql("DELETE FROM Book WHERE bookID = '"+bookId+"'");
		}

		public void addBookToDB(Book newBook) {
			clearMsg();
			this.msg.setNewBook(newBook);
			this.msg.setType(MessageType.C_REQ_ADD_BOOK);
		}

		public void updateCurrentAuthority() {
			clearMsg();
			this.msg.setType(MessageType.C_REQ_SELECT_AUTHORITY);
			
		}

		public void updateAuthority(int maxDay, int maxBookNum) {
			clearMsg();
			this.msg.setType(MessageType.C_REQ_UPDATE_AUTHORITY);
			this.msg.setMaxBorrowDays(maxDay);
			this.msg.setMaxBorrowNum(maxBookNum);
		}

		public void searchNonreturnedBorrowInfoInDB(String bookId) {
			clearMsg();
			this.msg.setType(MessageType.C_REQ_SELECT_NONRETURNED_BOOK);
			this.msg.setBookID(bookId);
		}

		public void searchBorrowInfoInDB(String bookId) {
			clearMsg();
			this.msg.setType(MessageType.C_REQ_SELECT_ALL_BORROWINFO_BOOK);
			this.msg.setBookID(bookId);
			
		}

		public void returnBook(String uID, String bookID) {
			clearMsg();
			this.msg.setType(MessageType.C_REQ_RETURN_BOOK);
			this.msg.setReaderID(uID);
			this.msg.setBookID(bookID);
		}

		public void borrowBook(String uID, String bookID)  {
			clearMsg();
			this.msg.setType(MessageType.C_REQ_BORROW_BOOK);
			this.msg.setReaderID(uID);
			this.msg.setBookID(bookID);
			
		}

		public void searchUserNonreturnedBorrowInfoInDB(String uid) {
			clearMsg();
			this.msg.setType(MessageType.C_REQ_SELECT_NONRETURNED_USER);
			this.msg.setReaderID(uid);
			
		}

		public void searchUserBorrowInfoInDB(String uid) {
			clearMsg();
			this.msg.setType(MessageType.C_REQ_SELECT_ALL_BORROWINFO_USER);
			this.msg.setReaderID(uid);
		}//end lib




}
