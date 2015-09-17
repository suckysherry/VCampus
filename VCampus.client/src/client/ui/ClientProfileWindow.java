package client.ui;

import java.awt.event.*;
import java.util.Vector;
import java.awt.*;
import javax.swing.*;

import com.sun.javafx.binding.StringFormatter;

import client.util.ClientMsgHelper;
import conn.common.*;

/**
 * 个人信息窗口
 * @author 戴思琪
 *
 */

public class ClientProfileWindow extends JFrame {
	private User user;
	private Vector<Student> students;
	private Vector<Teacher> teachers;
	private JPanel profilePanel;
	
	/**
	 * @param userIn
	 */
	
	public ClientProfileWindow(User userIn) {
		this.user = userIn;
		
		try {
			if(user.getURole().equals("student")) {
//				this.student = podb.queryStudentInDB(user.getUID());
				String sql = String.format("SELECT * FROM STUDENT WHERE ID = '%s' ", user.getUID());
				ClientMsgHelper cmh = new ClientMsgHelper();
				cmh.selectStudents(sql);
				cmh.sendMsg();
				cmh.recieveMsg();
				this.students = (Vector<Student>) cmh.getDataInMsg();
			}
			else if(user.getURole().equals("teacher")) {
//				this.teacher = podb.queryTeacherInDB(user.getUID());
				String sql = String.format("SELECT * FROM TEACHER WHERE ID = '%s'", user.getUID());
				ClientMsgHelper cmh = new ClientMsgHelper();
				cmh.selectTeachers(sql);
				cmh.sendMsg();
				cmh.recieveMsg();
				this.teachers = (Vector<Teacher>) cmh.getDataInMsg();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

//		测试数据
//		student.setUBirthday("1995-04-01");
//		student.setUCard("213131297");
//		student.setUClass("090131");
//		student.setUHometown("贵阳");
//		student.setUID("09013104");
//		student.setUName("戴思琪");
//		student.setURole("student");
//		student.setUSex("女");
		
		
		setResizable(false);
		setTitle("Profile");
		setBounds(100, 100, 900, 550);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(getOwner());
		setUndecorated(true);

		
		profilePanel = new JPanel();
		profilePanel.setOpaque(false);
		getContentPane().add(profilePanel);
		((JPanel) getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon(getClass().getResource("/res/profilebg.png"));
		JLabel background = new JLabel(img);
		getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		profilePanel.setLayout(null);
		
		/**
		 * 关闭页面
		 */
//		JButton btnClose = new JButton();
//		btnClose.setBorderPainted(false);
//		btnClose.setIcon(new ImageIcon(getClass().getResource("/res/btnclose.png")));
//		btnClose.setRolloverIcon(new ImageIcon(getClass().getResource("/res/btnclose3.png")));
//		btnClose.setBounds(0, 0, 40, 48);
//		btnClose.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				dispose();
//				System.out.println("Close");;
//			}
//		});
//		profilePanel.add(btnClose);
		
		JLabel lbCard = new JLabel("CARD");
		lbCard.setBounds(60, 146, 100, 30);
		lbCard.setHorizontalAlignment(SwingConstants.LEFT);
		lbCard.setVisible(true);
		lbCard.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbCard.setForeground(Color.LIGHT_GRAY);
		profilePanel.add(lbCard);
		JLabel lbtCard = new JLabel("一卡通");
		lbtCard.setBounds(211, 146, 180, 30);
		lbtCard.setHorizontalAlignment(SwingConstants.LEFT);
		lbtCard.setVisible(true);
		lbtCard.setForeground(Color.GRAY);
		lbtCard.setFont(new Font("Dialog", Font.BOLD, 20));
		profilePanel.add(lbtCard);
		
		JLabel lbName = new JLabel("name");
		lbName.setBounds(250, 35, 225, 55);
		lbName.setFont(new Font("Dialog", Font.BOLD, 46));
		lbName.setForeground(Color.LIGHT_GRAY);
		lbName.setHorizontalAlignment(SwingConstants.LEFT);
		lbName.setVisible(true);
		profilePanel.add(lbName);
		
		JLabel lbRole = new JLabel("ROLE");
		lbRole.setBounds(60, 188, 100, 30);
		lbRole.setHorizontalAlignment(SwingConstants.LEFT);
		lbRole.setVisible(true);
		lbRole.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbRole.setForeground(Color.LIGHT_GRAY);
		profilePanel.add(lbRole);
		JLabel lbtRole = new JLabel("ROLE");
		lbtRole.setBounds(211, 188, 180, 30);
		lbtRole.setHorizontalAlignment(SwingConstants.LEFT);
		lbtRole.setForeground(Color.GRAY);
		lbtRole.setFont(new Font("Dialog", Font.BOLD, 20));
		lbtRole.setVisible(true);
		profilePanel.add(lbtRole);
		
		JLabel lbID = new JLabel("ID");
		lbID.setBounds(60, 230, 100, 30);
		lbID.setHorizontalAlignment(SwingConstants.LEFT);
		lbID.setVisible(true);
		lbID.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbID.setForeground(Color.LIGHT_GRAY);
		profilePanel.add(lbID);
		JLabel lbtID = new JLabel("ID");
		lbtID.setBounds(211, 230, 180, 30);
		lbtID.setHorizontalAlignment(SwingConstants.LEFT);
		lbtID.setForeground(Color.GRAY);
		lbtID.setFont(new Font("Dialog", Font.BOLD, 20));
		lbtID.setVisible(true);
		profilePanel.add(lbtID);
		
		JLabel lbSex = new JLabel("SEX");
		lbSex.setBounds(60, 272, 100, 30);
		lbSex.setHorizontalAlignment(SwingConstants.LEFT);
		lbSex.setVisible(true);
		lbSex.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbSex.setForeground(Color.LIGHT_GRAY);
		profilePanel.add(lbSex);
		JLabel lbtSex = new JLabel("SEX");
		lbtSex.setBounds(211, 272, 180, 30);
		lbtSex.setHorizontalAlignment(SwingConstants.LEFT);
		lbtSex.setForeground(Color.GRAY);
		lbtSex.setFont(new Font("Dialog", Font.BOLD, 20));
		lbtSex.setVisible(true);
		profilePanel.add(lbtSex);
		
		JLabel lbClass = new JLabel("CLASS");
		lbClass.setBounds(60, 314, 100, 30);
		lbClass.setHorizontalAlignment(SwingConstants.LEFT);
		lbClass.setVisible(true);
		lbClass.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbClass.setForeground(Color.LIGHT_GRAY);
		profilePanel.add(lbClass);
		JLabel lbtClass = new JLabel("CLASS");
		lbtClass.setBounds(211, 314, 180, 30);
		lbtClass.setHorizontalAlignment(SwingConstants.LEFT);
		lbtClass.setForeground(Color.GRAY);
		lbtClass.setFont(new Font("Dialog", Font.BOLD, 20));
		lbtClass.setVisible(true);
		profilePanel.add(lbtClass);
		
		JLabel lbBirthday = new JLabel("BIRTHDAY");
		lbBirthday.setBounds(60, 359, 100, 30);
		lbBirthday.setHorizontalAlignment(SwingConstants.LEFT);
		lbBirthday.setVisible(true);
		lbBirthday.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbBirthday.setForeground(Color.LIGHT_GRAY);
		profilePanel.add(lbBirthday);
		JLabel lbtBirthday = new JLabel("BIRTHDAY");
		lbtBirthday.setBounds(211, 359, 180, 30);
		lbtBirthday.setHorizontalAlignment(SwingConstants.LEFT);
		lbtBirthday.setForeground(Color.GRAY);
		lbtBirthday.setFont(new Font("Dialog", Font.BOLD, 20));
		lbtBirthday.setVisible(true);
		profilePanel.add(lbtBirthday);
		
		JLabel lbHometown = new JLabel("HOME");
		lbHometown.setBounds(60, 405, 100, 30);
		lbHometown.setHorizontalAlignment(SwingConstants.LEFT);
		lbHometown.setVisible(true);
		lbHometown.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbHometown.setForeground(Color.LIGHT_GRAY);
		profilePanel.add(lbHometown);
		JLabel lbtHometown = new JLabel("HOME");
		lbtHometown.setBounds(211, 405, 180, 30);
		lbtHometown.setHorizontalAlignment(SwingConstants.LEFT);
		lbtHometown.setForeground(Color.GRAY);
		lbtHometown.setFont(new Font("Dialog", Font.BOLD, 20));
		lbtHometown.setVisible(true);
		profilePanel.add(lbtHometown);
		
		JLabel lbPic = new JLabel("");
		lbPic.setIcon(new ImageIcon(getClass().getResource("/res/fun.gif")));
		lbPic.setBounds(70, 80, 300, 300);
		lbPic.setHorizontalAlignment(SwingConstants.CENTER);
		lbPic.setVisible(false);
		profilePanel.add(lbPic);
		
		if(user.getURole().equals("student")) {
			
//			lbtCard.setForeground(Color.decode("64676a"));
//			JTextField tfCard = new JTextField(student.getUCard());
//			tfCard.setBounds(80, 20, 180, 30);
//			tfCard.setEditable(false);
//			tfCard.setHorizontalAlignment(SwingConstants.LEFT);
//			tfCard.setVisible(true);
//			profilePanel.add(tfCard);
			
			
			lbtCard.setText(students.get(0).getUCard());
			lbName.setText(students.get(0).getUName());
			lbtRole.setText(students.get(0).getURole());
			lbtID.setText(students.get(0).getUID());
			lbtSex.setText(students.get(0).getUSex());
			lbtClass.setText(students.get(0).getUClass());
			lbtBirthday.setText(students.get(0).getUBirthday());
			lbtHometown.setText(students.get(0).getUHometown());
			

			
			
		} else if(user.getURole().equals("teacher")) {
			
			lbtCard.setText(teachers.get(0).getUCard());
			lbName.setText(teachers.get(0).getUName());
			lbtRole.setText(teachers.get(0).getURole());
			lbtID.setText(teachers.get(0).getUID());
			lbtSex.setText(teachers.get(0).getUSex());
			lbtClass.setText(teachers.get(0).getUClass());
			lbBirthday.setVisible(false);
			lbHometown.setVisible(false);
			lbtBirthday.setVisible(false);
			lbtHometown.setVisible(false);
	
			
		} else if(user.getURole().equals("admin")) {
			
			lbCard.setVisible(false);
			lbRole.setVisible(false);
			lbID.setVisible(false);
			lbSex.setVisible(false);
			lbClass.setVisible(false);
			lbBirthday.setVisible(false);
			lbHometown.setVisible(false);
			
			lbtCard.setVisible(false);
			lbName.setVisible(false);
			lbtRole.setVisible(false);
			lbtID.setVisible(false);
			lbtSex.setVisible(false);
			lbtClass.setVisible(false);
			lbtBirthday.setVisible(false);
			lbtHometown.setVisible(false);
			
			lbPic.setVisible(true);
			
		}
		
		JLabel lbPassword = new JLabel("New Pwd:");
		lbPassword.setBounds(491, 198, 100, 30);
		lbPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lbPassword.setVisible(true);
		lbPassword.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbPassword.setForeground(Color.LIGHT_GRAY);
		profilePanel.add(lbPassword);
		final JPasswordField tfPassword = new JPasswordField("");
		tfPassword.setBounds(591, 201, 234, 30);
		tfPassword.setHorizontalAlignment(SwingConstants.CENTER);
		tfPassword.setVisible(true);
		profilePanel.add(tfPassword);
		
		JLabel lbConPwd = new JLabel("Confirm:");
		lbConPwd.setBounds(491, 269, 100, 30);
		lbConPwd.setHorizontalAlignment(SwingConstants.LEFT);
		lbConPwd.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbConPwd.setForeground(Color.LIGHT_GRAY);
		lbConPwd.setVisible(true);
		profilePanel.add(lbConPwd);
		final JPasswordField tfConPwd = new JPasswordField("");
		tfConPwd.setBounds(591, 272, 234, 30);
		tfConPwd.setHorizontalAlignment(SwingConstants.CENTER);
		tfConPwd.setVisible(true);
		profilePanel.add(tfConPwd);
		
		JButton btnChangePwd = new JButton("");
		btnChangePwd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChangePwd.setBorderPainted(false);
		btnChangePwd.setIcon(new ImageIcon(getClass().getResource("/res/profilebtn.png")));
		btnChangePwd.setRolloverIcon(new ImageIcon(getClass().getResource("/res/profilebtn2.png")));
		btnChangePwd.setBounds(557, 388, 250, 47);
		btnChangePwd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String password = String.valueOf(tfPassword.getPassword());
				String confirm = String.valueOf(tfConPwd.getPassword());
				
				if(!password.equals(confirm)) {
					JOptionPane.showMessageDialog(null, "两次密码不相同！", "错误", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						String sql = String.format("UPDATE USER SET password = '%s' WHERE id ='%s' ", password, user.getUID());
						ClientMsgHelper cmh = new ClientMsgHelper();
						cmh.update(sql);
						cmh.sendMsg();
						cmh.recieveMsg();
						boolean changePsw = cmh.getState();
						if(changePsw == true)
							JOptionPane.showMessageDialog(null, "成功！", "信息", JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "不成功！", "错误", JOptionPane.ERROR_MESSAGE);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		profilePanel.add(btnChangePwd);
		
		
	}
	
	public JPanel getProfilePanel() {
		return profilePanel;
	}


	public void setProfilePanel(JPanel profilePanel) {
		this.profilePanel = profilePanel;
	}

}
