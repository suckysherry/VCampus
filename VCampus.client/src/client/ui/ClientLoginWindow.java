//Created on 2015-08-27
package client.ui;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import javax.swing.*;

import client.ui.ClientMenuWindow;
import client.util.ClientMsgHelper;
import conn.common.*;

/**
 * 登录界面
 * @author Suckysherry
 *
 */

public class ClientLoginWindow {
	
	public JFrame frame;
	private JTextField textUserID;
	private JPasswordField passwordField;
	private static ClientLoginWindow window;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ClientLoginWindow();
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}
	
	public ClientLoginWindow() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(frame.getOwner());
		frame.setResizable(false);
		frame.setTitle("Welcome to SEU VCampus");
		frame.setBounds(100, 100, 913, 511);
		frame.setUndecorated(true);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		frame.getContentPane().add(panel);
		((JPanel) frame.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon(getClass().getResource("/res/background3.png"));
		JLabel background = new JLabel(img);
		frame.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		
		panel.setLayout(null);
		
		/**
		 * 退出程序按钮
		 */
		JButton btnClose = new JButton();
		btnClose.setBorderPainted(false);
		btnClose.setIcon(new ImageIcon(getClass().getResource("/res/btnclose.png")));
		btnClose.setRolloverIcon(new ImageIcon(getClass().getResource("/res/btnclose3.png")));
		btnClose.setBounds(0, 0, 40, 48);
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
				System.out.println("Close");;
			}
		});
		panel.add(btnClose);
		
		textUserID = new JTextField();
//		textUserID.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyTyped(KeyEvent arg0) {
//				if(!Character.isDigit(arg0.getKeyChar()))
//					arg0.consume();
//			}
//		});
		textUserID.setBounds(350, 160, 280, 50);
		panel.add(textUserID);
		textUserID.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(350, 222, 280, 50);
		panel.add(passwordField);

		JButton btnLogin = new JButton("");
		btnLogin.setBorderPainted(false); 
		btnLogin.setIcon(new ImageIcon(getClass().getResource("/res/login.png")));
		btnLogin.setRolloverIcon(new ImageIcon(getClass().getResource("/res/login2.png")));
		btnLogin.setBounds(350, 284, 319, 49);
		panel.add(btnLogin);
		
		btnLogin.addMouseListener(new MouseAdapter() {
			//监听登录按钮
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String userName = textUserID.getText();
				String password = String.valueOf(passwordField.getPassword());
				
				if(userName.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "用户名/密码不能为空", "错误", JOptionPane.ERROR_MESSAGE);
				} else {
//					LoginOperateDB lodb = new LoginOperateDB();
//					User verifyDB = null;
//					try {
//						verifyDB = lodb.verifyUserInDB(userName, password);
//						if (verifyDB == null){
//							JOptionPane.showMessageDialog(null, "用户名密码错误", "错误", JOptionPane.ERROR_MESSAGE);
//						}
//						else {
//							ClientMenuWindow cmw = new ClientMenuWindow(window, verifyDB);//verifyDB
//							cmw.setVisible(true);
//						}
//					} catch(Exception e) {
//						e.printStackTrace();
//					}
					ClientMsgHelper cmh = new ClientMsgHelper();
					cmh.verifyUser(userName, password);
					cmh.sendMsg();
					cmh.recieveMsg();
					User user = (User)cmh.getDataInMsg();
					if (user == null){
						JOptionPane.showMessageDialog(null, "用户名密码错误", "错误", JOptionPane.ERROR_MESSAGE);
					}
					else {
						ClientMenuWindow cmw = new ClientMenuWindow(window, user);
						cmw.setVisible(true);
					}
					
				}
			}
		});
		
		frame.setVisible(true);
		
	}
	
	public static ClientLoginWindow getWindow() {
		return window;
	}
	
	public static void setWindow(ClientLoginWindow window) {
		ClientLoginWindow.window = window;
	}

}
