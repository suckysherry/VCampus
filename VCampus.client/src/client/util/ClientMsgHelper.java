package client.util;

import conn.common.Message;
import conn.common.MessageType;

/**
 * 消息类方法
 * @author daisiqi
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




}
