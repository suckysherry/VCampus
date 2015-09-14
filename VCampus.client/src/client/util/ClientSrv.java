package client.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import conn.common.Message;

/**
 * 连接Server
 * @author xindervella
 *
 */
public class ClientSrv implements ClientSrvInterface {
	protected static String SERVER_ADRESS = "127.0.0.1";
	protected static int SERVER_PORT = 8899;
	protected Socket socket;
	protected boolean isOffline;
	
	
	public ClientSrv(){
		try{
			this.socket = new Socket(SERVER_ADRESS, SERVER_PORT);
			this.isOffline = false;
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
	}

	@Override
	public void send(Message msg) {
		try{
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(msg);
			oos.flush();
			System.out.println("CSrv: Sent a msg.");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

	@Override
	public Message receive() {
		try{
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			System.out.println("CSrv: Receive a msg.");
			Message msgRcv = (Message)ois.readObject();

			return msgRcv;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
