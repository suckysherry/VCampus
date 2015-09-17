package server.connDb;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import server.util.ServerSrvThreadMgr;

/**
 * 服务器服务
 * @author 戴思琪
 *
 */
public class ServerSrv implements ServerSrvInterface {
	protected int SERVER_PORT; // 监听端口
	protected ServerSocket serverSocket;
	protected boolean closed;


	public ServerSrv(int port) {
		System.out.println("SSrv:Listening@port" + port);
		this.SERVER_PORT = port;
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
			closed = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (!closed && !serverSocket.isClosed()) {
				Socket s = serverSocket.accept();
				String simpleDT = String.valueOf(System.currentTimeMillis());
				// 用时间做线程ID
				// 接受客户端发来的信息
				ServerThread st = new ServerThread(s, simpleDT);
				ServerSrvThreadMgr.add(simpleDT, st);

				st.start();

				System.out.println("SSrv:Created a new socket.");
				// 开启线程
			}
			if(serverSocket.isClosed()){
				System.out.println("ServerSrv:serverSocket is down!");
			}
			if(closed){
				System.out.println("ServerSrv:isclosed!");
			}
		} catch (SocketException e) {
			System.out.println("Stopped listening @port" + SERVER_PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start() {
		new Thread(this).start();
	}

	@Override
	public void stop() {
		if (serverSocket != null)
			try {
				// 关闭服务器Socket
				closed = true;
				serverSocket.close();
				serverSocket = null;
				System.out.println("SSrv:Server HAS BEEN SHUT DOWN!");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}
