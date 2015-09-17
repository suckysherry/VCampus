package server.connDb;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import server.util.ServerSrvThreadMgr;
import conn.common.Book;
import conn.common.Goods;
import conn.common.Message;
import conn.common.MessageType;
import conn.common.Student;
import conn.common.Teacher;
import conn.common.User;

/**
 * 接受多个客户端发送信息线程
 * @author 戴思琪
 *
 */
public class ServerThread extends Thread {
	protected Socket client;
	protected boolean isClosed;
	protected OperateDB opdb;
	protected String clientID;// 客户端线程编号(现在使用线程编号)

	public ServerThread(Socket s, String cID) {
		this.client = s;
		this.isClosed = false;
		this.clientID = cID;
		opdb = new OperateDB();
	}

	public void closeThread() {
		this.isClosed = true;
		// 关闭线程
	}

	
	@Override
	public void run() {
		while (!this.isClosed) {
			try {
				ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
				Message msg = (Message) ois.readObject();
				String type = msg.getType();

				ServerThread sth = ServerSrvThreadMgr.get(clientID);
				Message msgRsp = new Message();
				
				if(type.equals(MessageType.C_REQ_VERIFYUSER)){
					String username = (String) msg.getUserName();
					String password = (String) msg.getPassword();
					User user = opdb.verifyUser(username, password);
					msgRsp.setData(user);	

				}
				else if(type.equals(MessageType.C_REQ_UPDATE)) {
					String sql = (String) msg.getSql();
					Boolean state = opdb.update(sql);
					msgRsp.setState(state);
				}
				else if(type.equals(MessageType.C_REQ_SELECT_USERS)) {
					String sql = (String) msg.getSql();
					Vector<User> users = opdb.selectUser(sql);
					msgRsp.setData(users);
				}
				else if(type.equals(MessageType.C_REQ_SELECT_STUDENTS)) {
					String sql = (String) msg.getSql();
					Vector<Student> students = opdb.selectStudent(sql);
					msgRsp.setData(students);
				}
				else if(type.equals(MessageType.C_REQ_SELECT_TEACHERS)) {
					String sql = (String) msg.getSql();
					Vector<Teacher> teachers = opdb.selectTeacher(sql);
					msgRsp.setData(teachers);
				}
				else if(type.equals(MessageType.C_REQ_INSERT)){
					String sql = (String) msg.getSql();
					Boolean state = opdb.insert(sql);
					msgRsp.setState(state);
				}
				else if(type.equals(MessageType.C_REQ_DELETE)) {
					String sql = (String) msg.getSql();
					Boolean state = opdb.delete(sql);
					msgRsp.setState(state);
				}
				else if(type.equals(MessageType.C_REQ_SELECT_GOODS)) {
					String sql = (String) msg.getSql();
					List<Goods> allgoods= opdb.selectGoods(sql);
					msgRsp.setData(allgoods);
				}
				//lib
				else if(type.equals(MessageType.C_REQ_SELECT_BOOKS)) {
					String sql = (String) msg.getSql();
					Vector<Book> bookList = opdb.selectBooks(sql);
					msgRsp.setData(bookList);
				}
				else if(type.equals(MessageType.C_REQ_UPDATE_BOOK)) {
					opdb.updateBook(msg.getNewBook(),msg.getBookID());
				}
				else if(type.equals(MessageType.C_REQ_ADD_BOOK)) {
					opdb.addBook(msg.getNewBook());
				}
				else if(type.equals(MessageType.C_REQ_SELECT_AUTHORITY)) {
					int[] numbers = opdb.getCurrentAuthority();
					msgRsp.setData(numbers);
				}
				else if(type.equals(MessageType.C_REQ_UPDATE_AUTHORITY)) {
					boolean state = opdb.updateAuthority(msg.getMaxBorrowDays(),msg.getMaxBorrowNum());
					msgRsp.setState(state);
				}
				else if(type.equals(MessageType.C_REQ_SELECT_NONRETURNED_BOOK)) {
					Vector<String>  borrowList = opdb.searchNonreturnedBorrowInfoInDB(msg.getBookID());
					msgRsp.setData(borrowList);
				}
				else if(type.equals(MessageType.C_REQ_RETURN_BOOK)) {
					int  state = opdb.returnBook(msg.getReaderID(), msg.getBookID());
					msgRsp.setData(state);
				}
				else if(type.equals(MessageType.C_REQ_BORROW_BOOK)) {
					int  state = opdb.borrowBook(msg.getReaderID(), msg.getBookID());
					msgRsp.setData(state);
				}
				else if(type.equals(MessageType.C_REQ_SELECT_NONRETURNED_USER)) {
					Vector<String>  list = opdb.searchUserNonreturnedBorrowInfoInDB(msg.getReaderID());
					msgRsp.setData(list);
				}
				else if(type.equals(MessageType.C_REQ_SELECT_ALL_BORROWINFO_USER)) {
					Vector<String>  list = opdb.searchUserBorrowInfoInDB(msg.getReaderID());
					msgRsp.setData(list);
				}
				else if(type.equals(MessageType.C_REQ_SELECT_ALL_BORROWINFO_BOOK)) {
					Vector<String>  list = opdb.searchBorrowInfoInDB(msg.getBookID());
					msgRsp.setData(list);
				}//end lib
				ObjectOutputStream oos = new ObjectOutputStream(sth.getClient().getOutputStream());
				oos.writeObject(msgRsp);
				oos.flush();
				ServerSrvThreadMgr.remove(clientID);
				
				
//				else if (type.equals(MessageType.C_REQ_QUERYSTU)){
//					ServerThread sth = ServerSrvThreadMgr.get(clientID);
//					int username = Integer.parseInt(msg.getUsername());
//					Student stu  = opdb.queryStudent(username);
//					Message msgRsp = new Message();
//					msgRsp.setType(MessageType.S_RET_DATA);
//					msgRsp.setData(stu);
//					ObjectOutputStream oos = new ObjectOutputStream(sth.getClient().getOutputStream());
//					oos.writeObject(msgRsp);
//					oos.flush();
//					ServerSrvThreadMgr.remove(clientID);
//				
//				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	protected Socket getClient() {
		return client;
	}

}
