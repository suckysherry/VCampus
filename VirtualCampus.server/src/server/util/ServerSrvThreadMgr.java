package server.util;

import java.util.LinkedHashMap;
import java.util.Map;

import server.connDb.ServerThread;

/**
 * 线程池
 * @author 戴思琪
 *
 */
public class ServerSrvThreadMgr {

	private  static  Map<String, ServerThread>  clientThreadPool = new LinkedHashMap<String, ServerThread>();

	public synchronized static void add(String clientID, ServerThread clientThreadSrv) {
		clientThreadPool.put(clientID, clientThreadSrv);
		System.out.println("SSrvThreadMgr: Whoo~ There\'s a thread coming here! cID:" + clientID);
	}

	public synchronized static ServerThread remove(String cID) {
		System.out.println("SSrvThreadMrg: Thread cID=" + cID + " is no longer here..");
		ServerThread threadToRmv = ServerSrvThreadMgr.get(cID);
		threadToRmv.closeThread();//设置关闭标志
		return clientThreadPool.remove(cID);
	}
	public synchronized static ServerThread get(String cID) {
		ServerThread cts = clientThreadPool.get(cID);
		return cts;
	}
	public synchronized static Map<String, ServerThread> getPool(){
		return clientThreadPool;
	}

	public synchronized static void clear() {
		clientThreadPool.clear();
	}
}
