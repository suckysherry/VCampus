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
 * @author 戴思琪
 *
 */

public class ClientUserManageWindow extends JFrame {

	private final JPanel createPanel;

	public ClientUserManageWindow() {

		setResizable(false);
		setTitle("Create new user");
		setBounds(100, 100, 900, 550);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(getOwner());

		createPanel = new JPanel();
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
		lbUserList.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lbUserList.setBounds(30, 39, 100, 30);
		lbUserList.setHorizontalAlignment(SwingConstants.LEFT);
		lbUserList.setVisible(true);
		createPanel.add(lbUserList);

		Vector<String> tableHead = new Vector<String>();
		final Vector<Vector<String>> data = new Vector<Vector<String>>();
		Vector<String> user = new Vector<String>();
		tableHead.add("Userid");
		tableHead.add("Name");
		tableHead.add("Role");
		tableHead.add("Lib admin");
		tableHead.add("JWC admin");
		tableHead.add("Shop admin");
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
		scrollpane.setBounds(30, 73, 454, 404);
		tbUser.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollpane.setViewportView(tbUser);
		scrollpane.setRowHeaderView(tbUser.getTableHeader());

		JLabel lbcUserid = new JLabel("UserID");
		lbcUserid.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lbcUserid.setBounds(521, 75, 80, 20);
		lbcUserid.setHorizontalAlignment(SwingConstants.LEFT);
		lbcUserid.setVisible(true);
		createPanel.add(lbcUserid);
		final JTextField useridField = new JTextField();
		useridField.setBounds(613, 73, 216, 30);
		createPanel.add(useridField);

//		JLabel lbcPassword = new JLabel("Password");
//		lbcPassword.setBounds(30, 320, 80, 20);
//		lbcPassword.setHorizontalAlignment(SwingConstants.LEFT);
//		lbcPassword.setVisible(true);
//		createPanel.add(lbcPassword);
//		final JPasswordField passwordField = new JPasswordField();
//		passwordField.setBounds(103, 320, 180, 20);
//		createPanel.add(passwordField);

//		JLabel lbcConfirm = new JLabel("Confirm");
//		lbcConfirm.setBounds(30, 357, 80, 20);
//		lbcConfirm.setHorizontalAlignment(SwingConstants.LEFT);
//		lbcConfirm.setVisible(true);
//		createPanel.add(lbcConfirm);
//		final JPasswordField confirmField = new JPasswordField();
//		confirmField.setBounds(103, 352, 180, 30);
//		createPanel.add(confirmField);

		JLabel lbcUsername = new JLabel("Name");
		lbcUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lbcUsername.setBounds(521, 137, 80, 20);
		lbcUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lbcUsername.setVisible(true);
		createPanel.add(lbcUsername);
		final JTextField userNameField = new JTextField();
		userNameField.setBounds(613, 135, 216, 30);
		createPanel.add(userNameField);

		String[] roleList = {"admin", "student", "teacher"};
		final JComboBox cbcRole = new JComboBox(roleList);
		cbcRole.setBounds(513, 185, 104, 70);
		cbcRole.setVisible(true);
		createPanel.add(cbcRole);

		final JCheckBox cbLibAdmin = new JCheckBox("Library Admin");
		cbLibAdmin.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		cbLibAdmin.setBounds(660, 201, 208, 30);
		cbLibAdmin.setVisible(true);
		createPanel.add(cbLibAdmin);

		final JCheckBox cbShopAdmin = new JCheckBox("Shop Admin");
		cbShopAdmin.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		cbShopAdmin.setBounds(660, 270, 169, 30);
		cbShopAdmin.setVisible(true);
		createPanel.add(cbShopAdmin);

		final JCheckBox cbJWCAdmin = new JCheckBox("JWC Admin");
		cbJWCAdmin.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		cbJWCAdmin.setBounds(660, 347, 169, 30);
		cbJWCAdmin.setVisible(true);
		createPanel.add(cbJWCAdmin);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreate.setBorderPainted(true);
		btnCreate.setBounds(512, 420, 150, 44);
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String userid = useridField.getText();
				String name = userNameField.getText();
				String password = userid;
				String role = String.valueOf(cbcRole.getSelectedItem());
				Boolean isLibraryAdmin = cbLibAdmin.isSelected();
				Boolean isJWCAdmin = cbJWCAdmin.isSelected();
				Boolean isShopAdmin = cbShopAdmin.isSelected();


				try {
					String sql = String.format("INSERT INTO USER VALUES ('%s', '%s', '%s', '%d', '%d', '%d', '%s')", userid, password, role, isLibraryAdmin?1:0, isJWCAdmin?1:0, isShopAdmin?1:0, name);
					ClientMsgHelper cmh = new ClientMsgHelper();
					cmh.insert(sql);
					cmh.sendMsg();
					cmh.recieveMsg();
					boolean istu = cmh.getState();
					
					if(role.equals("student")) {
						ClientMsgHelper cmh_s = new ClientMsgHelper();
						String sql_s = String.format("INSERT INTO STUDENT(ID, Name, role) VALUES ('%s', '%s', '%s')", userid, name, role);
						cmh_s.insert(sql_s);
						cmh_s.sendMsg();
						cmh_s.recieveMsg();
					} else if (role.equals("teacher")) {
						ClientMsgHelper cmh_t = new ClientMsgHelper();
						String sql_t = String.format("INSERT INTO TEACHER(ID, Name, Role) VALUES ('%s', '%s', '%s')", userid, name, role);
						cmh_t.insert(sql_t);
						cmh_t.sendMsg();
						cmh_t.recieveMsg();
					}
					
					if(istu == true) {
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
		});

		JButton btnUpdateRole = new JButton("Authorize");
		btnUpdateRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdateRole.setOpaque(false);
		btnUpdateRole.setBounds(718, 420, 150, 44);
		btnUpdateRole.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String userid = useridField.getText();
				String name = userNameField.getText();
				String role = (String) cbcRole.getSelectedItem();
				Boolean isLibraryAdmin = cbLibAdmin.isSelected();
				Boolean isJWCAdmin = cbJWCAdmin.isSelected();
				Boolean isShopAdmin = cbShopAdmin.isSelected();
//				int rowCount = tbUser.getRowCount();
//				for(int i=0; i<rowCount; i++) {
//					if(tbUser.getValueAt(i, 0).equals(userid)) {
//						tbUser.setValueAt(role, i, 2);
//						tbUser.setValueAt(isLibraryAdmin, i, 3);
//						tbUser.setValueAt(isJWCAdmin, i, 4);
//						tbUser.setValueAt(isShopAdmin, i, 5);
//						break;
//					}
//				}
//				tbUser.updateUI();
				try {
					String sql = String.format("UPDATE USER SET role = '%s', isLibraryAdmin = '%d', isJWCAdmin = '%d', isShopAdmin = '%d' WHERE id = '%s' ", role, isLibraryAdmin?1:0, isJWCAdmin?1:0, isShopAdmin?1:0, userid);
					ClientMsgHelper cmh = new ClientMsgHelper();
					cmh.update(sql);
					cmh.sendMsg();
					cmh.recieveMsg();
					boolean mur = cmh.getState();
					
					if(role.equals("student")) {
						String sql_i_s = String.format("INSERT INTO STUDENT(ID, Name, role) VALUES ('%s', '%s', '%s')", userid, name, role);
						ClientMsgHelper cmh_i_s = new ClientMsgHelper();
						cmh_i_s.insert(sql_i_s);
						cmh_i_s.sendMsg();
						cmh_i_s.recieveMsg();
						
						Vector<Teacher> teachers = new Vector<Teacher>();
						String sql_s_t = String.format("SELECT * FROM TEACHER WHERE ID = '%s'", userid);
						ClientMsgHelper cmh_s_t = new ClientMsgHelper();
						cmh_s_t.selectStudents(sql_s_t);
						cmh_s_t.sendMsg();
						cmh_s_t.recieveMsg();
						teachers = (Vector<Teacher>) cmh_s_t.getDataInMsg();
						
						if(teachers.size() != 0) {
							String sql_d_t = String.format("DELETE FROM TEACHER WHERE ID = '%s'", userid);
							ClientMsgHelper cmh_d_t = new ClientMsgHelper();
							cmh_d_t.delete(sql_d_t);
							cmh_d_t.sendMsg();
							cmh_d_t.recieveMsg();
						}	
					}
					
					else if(role.equals("teacher")) {
						String sql_i_t = String.format("INSERT INTO TEACHER(ID, Name, role) VALUES ('%s', '%s', '%s')", userid, name, role);
						ClientMsgHelper cmh_i_t = new ClientMsgHelper();
						cmh_i_t.insert(sql_i_t);
						cmh_i_t.sendMsg();
						cmh_i_t.recieveMsg();
						
						
						Vector<Student> students = new Vector<Student>();
						String sql_s_s = String.format("SELECT * FROM STUDENT WHERE ID = '%s'", userid);
						ClientMsgHelper cmh_s_s = new ClientMsgHelper();
						cmh_s_s.selectStudents(sql_s_s);
						cmh_s_s.sendMsg();
						cmh_s_s.recieveMsg();
						students = (Vector<Student>) cmh_s_s.getDataInMsg();
						
						if(students.size() != 0) {
							String sql_d_s = String.format("DELETE FROM STUDENT WHERE ID = '%s'", userid);
							ClientMsgHelper cmh_d_s = new ClientMsgHelper();
							cmh_d_s.delete(sql_d_s);
							cmh_d_s.sendMsg();
							cmh_d_s.recieveMsg();
						}	
					} 
					else if (role.equals("admin")) {
						Vector<Teacher> teachers_a = new Vector<Teacher>();
						String sql_t_a = String.format("SELECT * FROM TEACHER WHERE ID = '%s'", userid);
						ClientMsgHelper cmh_t_a = new ClientMsgHelper();
						cmh_t_a.selectStudents(sql_t_a);
						cmh_t_a.sendMsg();
						cmh_t_a.recieveMsg();
						teachers_a = (Vector<Teacher>) cmh_t_a.getDataInMsg();
						
						if(teachers_a.size() != 0) {
							String sql_d_t_a = String.format("DELETE FROM TEACHER WHERE ID = '%s'", userid);
							ClientMsgHelper cmh_d_t_a = new ClientMsgHelper();
							cmh_d_t_a.delete(sql_d_t_a);
							cmh_d_t_a.sendMsg();
							cmh_d_t_a.recieveMsg();
						}	
						
						Vector<Student> students_a = new Vector<Student>();
						String sql_s_a = String.format("SELECT * FROM STUDENT WHERE ID = '%s'", userid);
						ClientMsgHelper cmh_s_a = new ClientMsgHelper();
						cmh_s_a.selectStudents(sql_s_a);
						cmh_s_a.sendMsg();
						cmh_s_a.recieveMsg();
						students_a = (Vector<Student>) cmh_s_a.getDataInMsg();
						
						if(students_a.size() != 0) {
							String sql_d_s_a = String.format("DELETE FROM STUDENT WHERE ID = '%s'", userid);
							ClientMsgHelper cmh_d_s_a = new ClientMsgHelper();
							cmh_d_s_a.delete(sql_d_s_a);
							cmh_d_s_a.sendMsg();
							cmh_d_s_a.recieveMsg();
						}
					}
					int rowCount = tbUser.getRowCount();
					for(int i=0; i<rowCount; i++) {
						if(tbUser.getValueAt(i, 0).equals(userid)) {
							tbUser.setValueAt(role, i, 2);
							tbUser.setValueAt(isLibraryAdmin, i, 3);
							tbUser.setValueAt(isJWCAdmin, i, 4);
							tbUser.setValueAt(isShopAdmin, i, 5);
							break;
						}
					}
					tbUser.updateUI();
					
					if(mur == true) {
						JOptionPane.showMessageDialog(null, "修改用户身份成功！", "信息", JOptionPane.INFORMATION_MESSAGE);
					} else
						JOptionPane.showMessageDialog(null, "修改用户身份不成功！", "错误", JOptionPane.ERROR_MESSAGE);
				} catch(Exception e) {
					e.printStackTrace();
				}

			}
		});

		/**
		 * 点表格自动补全
		 */
		tbUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int rowIndex = tbUser.getSelectedRow();
				String userid = (String) tbUser.getValueAt(rowIndex, 0);
				String name = (String) tbUser.getValueAt(rowIndex, 1);
				String role = (String) tbUser.getValueAt(rowIndex, 2);
				Boolean isLibraryAdmin = Boolean.getBoolean((String) tbUser.getValueAt(rowIndex, 3));
				Boolean isJWCAdmin = Boolean.getBoolean((String)  tbUser.getValueAt(rowIndex, 4));
				Boolean isShopAdmin = Boolean.getBoolean((String) tbUser.getValueAt(rowIndex, 5));
				if (role.equals("admin")){cbcRole.setSelectedIndex(0);}
				if (role.equals("student")){cbcRole.setSelectedIndex(1);}
				if (role.equals("teacher")){cbcRole.setSelectedIndex(2);}
				useridField.setText(userid);
				userNameField.setText(name);
				cbLibAdmin.setSelected(isLibraryAdmin);
				cbJWCAdmin.setSelected(isJWCAdmin);
				cbShopAdmin.setSelected(isShopAdmin);
			}
		});

		createPanel.add(scrollpane);
		createPanel.add(btnCreate);
		createPanel.add(btnUpdateRole);
	}
	
	public JPanel getCreatePanel() {
		return createPanel;
	}

}
