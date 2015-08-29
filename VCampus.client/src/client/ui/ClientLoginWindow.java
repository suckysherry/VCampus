//Created on 2015-08-27
package client.ui;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import javax.swing.*;
import client.ui.*;
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
		frame.setBounds(100, 100, 400, 550);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		frame.getContentPane().add(panel);
		((JPanel) frame.getContentPane()).setOpaque(false);
		//ImgIcon imgIcon = new ImageIcon(getClass.getResource("路径"))；
		//JLabel background = new JLabel(imgIcon);
		//frame.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		//background.setBounds(0, 0, imgIcon.getIconWidth(), imgIcon.getIconHeight());
		
		panel.setLayout(null);
		
		textUserID = new JTextField();
		textUserID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(!Character.isDigit(arg0.getKeyChar()))
					arg0.consume();
			}
		});
		textUserID.setBounds(80, 338, 250, 30);
		panel.add(textUserID);
		textUserID.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(80, 400, 250, 30);
		panel.add(passwordField);

		JButton btnLogin = new JButton("登录");
		btnLogin.setBorderPainted(true); //有图时改为false
		//btnLogin.setIcon(new ImageIcon(getClass.getResource("路径")));
		//btnLogin.setRolloverIcon(new ImageIcon(getClass().getResource("路径")));
		btnLogin.setBounds(85, 450, 241, 34);
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
					//后台数据交流
//					User user = new User("213131297", "123212321", "student");
					User user = new User("213131297", "123212321", "admin");
					ClientMenuWindow cmw = new ClientMenuWindow(window, user);
					cmw.setVisible(true);
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
