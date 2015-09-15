package server.connDb;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import server.util.ServerSrvThreadMgr;
import conn.common.Message;
import conn.common.MessageType;
import conn.common.Student;
import conn.common.Teacher;
import conn.common.User;

/**
 * 接受多个客户端发送信息线程
 * @author daisiqi
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
