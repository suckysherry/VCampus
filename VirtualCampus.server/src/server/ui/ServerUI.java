package server.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import server.connDb.ServerSrv;

/**
 * server ui
 * @author daisiqi
 *
 */
public class ServerUI {
	private JFrame frmVCServer;
	private JTextField textPort;  //端口号
	private boolean srvStatus;  //server状态
	private ServerSrv serversrv;

	/**
	 * 启动应用
	 * @param args
	 */
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerUI window = new ServerUI();
					window.frmVCServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 创建UI
	 */
	public ServerUI(){
		frmVCServer = new JFrame();
		frmVCServer.setTitle("Virtual Campus");
		frmVCServer.setBounds(100, 100, 400, 300);
		frmVCServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmVCServer.getContentPane().setLayout(gridBagLayout);

		JLabel lbLisentPort = new JLabel("监听端口：");
		lbLisentPort.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		GridBagConstraints gbcLisentPort = new GridBagConstraints();
		gbcLisentPort.gridheight = 2;
		gbcLisentPort.gridwidth = 3;
		gbcLisentPort.insets = new Insets(0, 0, 5, 5);
		gbcLisentPort.gridx = 0;
		gbcLisentPort.gridy = 0;
		frmVCServer.getContentPane().add(lbLisentPort, gbcLisentPort);

		textPort = new JTextField();
		textPort.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		textPort.setText("8899");
		GridBagConstraints gbc_textPort = new GridBagConstraints();
		gbc_textPort.gridheight = 2;
		gbc_textPort.insets = new Insets(0, 0, 5, 0);
		gbc_textPort.fill = GridBagConstraints.BOTH;
		gbc_textPort.gridx = 5;
		gbc_textPort.gridy = 0;
		frmVCServer.getContentPane().add(textPort, gbc_textPort);
		textPort.setColumns(10);

		final JButton btnStartServer = new JButton("Start Server");
		btnStartServer.setFont(new Font("微软雅黑", Font.PLAIN, 48));
		btnStartServer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int port;
				if(srvStatus == false){
					try{
						port = Integer.parseInt(textPort.getText());
					}
					catch(java.lang.NumberFormatException e){
						JOptionPane.showMessageDialog(null,"Wrong PORT!Set to default:8888");
						port = 8888;
						textPort.setText("8888");
					}
					if(serversrv == null){
						//如果服务器没有启动过
						serversrv = new ServerSrv(port);
					}
					serversrv.start();//启动服务器
					srvStatus = true;
					btnStartServer.setText("Stop Server");
				}
				else{
					serversrv.stop();
					serversrv = null;
					srvStatus = false;
					btnStartServer.setText("Start Server");
				}

			}
		});
		GridBagConstraints gbcStartServer = new GridBagConstraints();
		gbcStartServer.gridheight = 4;
		gbcStartServer.gridwidth = 6;
		gbcStartServer.fill = GridBagConstraints.BOTH;
		gbcStartServer.gridx = 0;
		gbcStartServer.gridy = 2;
		frmVCServer.getContentPane().add(btnStartServer, gbcStartServer);
	}

}


