package client.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.sun.org.apache.xpath.internal.operations.Bool;

import client.util.ClientMsgHelper;

import java.util.*;
import conn.common.*;

/**
 * 用户管理窗口
 * @author Suckysherry
 *
 */

public class ClientUserManageWindow extends JFrame {
	
	public ClientUserManageWindow() {
		
		setResizable(false);
		setTitle("Create new user");
		setBounds(100, 100, 500, 500);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(getOwner());
		
		final JPanel createPanel = new JPanel();
		createPanel.setOpaque(false);
		getContentPane().add(createPanel, BorderLayout.CENTER);
		((JPanel) getContentPane()).setOpaque(false);
		createPanel.setLayout(null);
		
		ImageIcon img = new ImageIcon(getClass().getResource("/res/menuuser.png"));
		JLabel background = new JLabel(img);
		getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		background.setLayout(null);
		
		JLabel lbUserList = new JLabel("User list:");
		lbUserList.setBounds(53, 20, 100, 30);
		lbUserList.setHorizontalAlignment(SwingConstants.LEFT);
		lbUserList.setVisible(true);
		createPanel.add(lbUserList);
		
		UserManageOperateDB umodb = new UserManageOperateDB();
		Vector<String> tableHead = new Vector<String>();
		final Vector<Vector<String>> data = new Vector<Vector<String>>();
		Vector<String> user = new Vector<String>();
		tableHead.add("Userid");
		tableHead.add("Name");
		tableHead.add("Role");
		tableHead.add("Lib admin");
		tableHead.add("Shop admin");
		tableHead.add("JWC admin");
		Vector<User> users = null;
		try {
			String sql = "SELECT * FROM USER";
			ClientMsgHelper cmh = new ClientMsgHelper();
			cmh.selectUsers(sql);
			cmh.sendMsg();
			cmh.recieveMsg();
			users = (Vector<User>) cmh.getDataInMsg();
		}catch(Exception e) {
			e.printStackTrace();
		}
		for(int i=0;i<users.size();i++){
			user.add(users.get(i).getUID());
			user.add(users.get(i).getUName());
			user.add(users.get(i).getURole());
			user.add(Boolean.toString(users.get(i).isLibraryAdmin()));
			user.add(Boolean.toString(users.get(i).isJWCAdmin()));
			user.add(Boolean.toString(users.get(i).isShopAdmin()));
			data.add((Vector<String>) user.clone());
			user.removeAllElements();
		}
	
//		测试数据
//		User user1 = new User("1", "1", "student", true, true, true);
//		User user2 = new User("2", "2", "teacher", true, true, true);
//		User user3 = new User("3", "3", "admin", true, true, true);
//		user.add(user1.getUID());
//		user.add(user1.getURole());
//		user.add(user2.getUID());
//		user.add(user2.getURole());
//		user.add(user3.getUID());
//		user.add(user3.getURole());
//		data.add((Vector<String>) user.clone());
		
		final JTable tbUser = new JTable(data, tableHead);
		final JScrollPane scrollpane = new JScrollPane(tbUser);
		scrollpane.setBounds(53, 62, 386, 150);
		tbUser.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollpane.setViewportView(tbUser);
		scrollpane.setRowHeaderView(tbUser.getTableHeader());
		
		JLabel lbcUserid = new JLabel("UserID");
		lbcUserid.setBounds(30, 243, 80, 20);
		lbcUserid.setHorizontalAlignment(SwingConstants.LEFT);
		lbcUserid.setVisible(true);
		createPanel.add(lbcUserid);
		final JTextField useridField = new JTextField();
		useridField.setBounds(103, 243, 180, 20);
		createPanel.add(useridField);
		
		JLabel lbcPassword = new JLabel("Password");
		lbcPassword.setBounds(30, 320, 80, 20);
		lbcPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lbcPassword.setVisible(true);
		createPanel.add(lbcPassword);
		final JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(103, 320, 180, 20);
		createPanel.add(passwordField);
		
		JLabel lbcConfirm = new JLabel("Confirm");
		lbcConfirm.setBounds(30, 357, 80, 20);
		lbcConfirm.setHorizontalAlignment(SwingConstants.LEFT);
		lbcConfirm.setVisible(true);
		createPanel.add(lbcConfirm);
		final JPasswordField confirmField = new JPasswordField();
		confirmField.setBounds(103, 352, 180, 30);
		createPanel.add(confirmField);
		
		JLabel lbcUsername = new JLabel("Name");
		lbcUsername.setBounds(30, 283, 80, 20);
		lbcUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lbcUsername.setVisible(true);
		createPanel.add(lbcUsername);
		final JTextField userNameField = new JTextField();
		userNameField.setBounds(103, 283, 180, 20);
		createPanel.add(userNameField);
		
		String[] roleList = {"admin", "student", "teacher"};
		final JComboBox cbcRole = new JComboBox(roleList);
		cbcRole.setBounds(325, 239, 114, 30);
		cbcRole.setVisible(true);
		createPanel.add(cbcRole);
		
		final JCheckBox cbLibAdmin = new JCheckBox("Library Admin");
		cbLibAdmin.setBounds(323, 270, 150, 30);
		cbLibAdmin.setVisible(true);
		createPanel.add(cbLibAdmin);
		
		final JCheckBox cbShopAdmin = new JCheckBox("Shop Admin");
		cbShopAdmin.setBounds(323, 310, 150, 30);
		cbShopAdmin.setVisible(true);
		createPanel.add(cbShopAdmin);
		
		final JCheckBox cbJWCAdmin = new JCheckBox("JWC Admin");
		cbJWCAdmin.setBounds(323, 352, 150, 30);
		cbJWCAdmin.setVisible(true);
		createPanel.add(cbJWCAdmin);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreate.setBorderPainted(true);
		btnCreate.setBounds(77, 408, 150, 30);
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String userid = useridField.getText();
				String name = userNameField.getText();
				String password = String.valueOf(passwordField.getPassword());
				String confirm = String.valueOf(confirmField.getPassword());
				String role = String.valueOf(cbcRole.getSelectedItem());
				Boolean isLibraryAdmin = cbLibAdmin.isSelected();
				Boolean isJWCAdmin = cbJWCAdmin.isSelected();
				Boolean isShopAdmin = cbShopAdmin.isSelected();
			
				if(userid.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
					JOptionPane.showMessageDialog(null, "用户名/密码不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
				}
				if(!password.equals(confirm)) {
					JOptionPane.showMessageDialog(null, "两次密码不相同！", "错误", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						boolean rscu = umodb.createUserInDB(userid, password, role);
						if(rscu == true) {
							JOptionPane.showMessageDialog(null, "成功创建新用户！", "信息", JOptionPane.INFORMATION_MESSAGE);
							user.add(userid);
							user.add(name);
							user.add(role);
							user.add(Boolean.toString(isLibraryAdmin));
							user.add(Boolean.toString(isJWCAdmin));
							user.add(Boolean.toString(isShopAdmin));
							data.add(0, (Vector<String>) user.clone());
							user.removeAllElements();
						} else
							JOptionPane.showMessageDialog(null, "新用户创建不成功！", "错误", JOptionPane.ERROR_MESSAGE);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		JButton btnUpdateRole = new JButton("Authorize");
		btnUpdateRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdateRole.setOpaque(true);
		btnUpdateRole.setBounds(280, 408, 150, 30);
		btnUpdateRole.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String userid = useridField.getText();
				String role = (String) cbcRole.getSelectedItem();
				int rowCount = tbUser.getRowCount();
				for(int i=0; i<rowCount; i++) {
					if(tbUser.getValueAt(i, 0).equals(userid)) {
						tbUser.setValueAt(role, i, 1);
						break;
					}
				}
				tbUser.updateUI();
				try {
					boolean rsmur = umodb.modifyUserRoleInDB(userid, role);
					if(rsmur == true) {
						JOptionPane.showMessageDialog(null, "修改用户身份成功！", "信息", JOptionPane.INFORMATION_MESSAGE);
					} else 
						JOptionPane.showMessageDialog(null, "修改用户身份不成功！", "错误", JOptionPane.ERROR_MESSAGE);
				} catch(Exception e) {
					e.printStackTrace();
				}

			}
		});
		
		tbUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int rowIndex = tbUser.getSelectedRow();
				String username = (String) tbUser.getValueAt(rowIndex, 0);
				String role = (String) tbUser.getValueAt(rowIndex, 1);
				if (role.equals("student")){cbcRole.setSelectedIndex(0);}
				if (role.equals("teacher")){cbcRole.setSelectedIndex(1);}
				if (role.equals("admin")){cbcRole.setSelectedIndex(2);}
				useridField.setText(username);
			}
		});
		
		createPanel.add(scrollpane);
		createPanel.add(btnCreate);
		createPanel.add(btnUpdateRole);
	}

}
