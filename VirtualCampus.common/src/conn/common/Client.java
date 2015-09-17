package conn.common;

import java.io.Serializable;

/**
 * 客户端类 表明自己身份 便于线程管理
 * @author 09013104-daisiqi
 *
 */
public class Client implements Serializable {
	private static final long serialVersionUID = 8886696271132516027L;
	public static String clientID;

	public Client(String cID) {
		Client.clientID = cID;
	}

}
