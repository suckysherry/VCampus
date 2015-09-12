package client.util;

import conn.common.Message;

public interface ClientSrvInterface extends Runnable{
	public void send(Message msg);
	public Message receive();

}
