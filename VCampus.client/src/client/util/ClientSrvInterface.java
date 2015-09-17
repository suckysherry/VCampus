package client.util;

/**
 * @author 戴思琪
 */

import conn.common.Message;

public interface ClientSrvInterface extends Runnable{
	public void send(Message msg);
	public Message receive();

}
