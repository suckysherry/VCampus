package client.ui;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import com.sun.javafx.binding.StringFormatter;

import client.util.ClientMsgHelper;
import conn.common.*;

/**
 * 个人信息窗口
 * @author Suckysherry
 *
 */

public class ClientProfileWindow extends JFrame {
	private User user;
	private Student student;
	private Teacher teacher;
	
	
	public ClientProfileWindow(User userIn) {
		this.user = userIn;
		
		ProfileOperateDB podb = new ProfileOperateDB();
		try {
			if(user.getURole().equals("student"))
				this.student = podb.queryStudentInDB(user.getUID());
			else if(user.getURole().equals("teacher"))
				this.teacher = podb.queryTeacherInDB(user.getUID());
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
		setBounds(100, 100, 820, 391);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(getOwner());
		setUndecorated(true);

		
		JPanel profilePanel = new JPanel();
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
		JButton btnClose = new JButton();
		btnClose.setBorderPainted(false);
		btnClose.setIcon(new ImageIcon(getClass().getResource("/res/btnclose.png")));
		btnClose.setRolloverIcon(new ImageIcon(getClass().getResource("/res/btnclose3.png")));
		btnClose.setBounds(0, 0, 40, 48);
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				System.out.println("Close");;
			}
		});
		profilePanel.add(btnClose);
		
		if(user.getURole().equals("student")) {
			
			try {
				JLabel lbCard = new JLabel("CARD");
				lbCard.setBounds(60, 140, 100, 30);
				lbCard.setHorizontalAlignment(SwingConstants.LEFT);
				lbCard.setVisible(true);
				lbCard.setFont(new java.awt.Font("", 0, 14));
				lbCard.setForeground(Color.LIGHT_GRAY);
				profilePanel.add(lbCard);
				JLabel lbtCard = new JLabel(student.getUCard());
				lbtCard.setBounds(150, 140, 180, 30);
				lbtCard.setHorizontalAlignment(SwingConstants.LEFT);
				lbtCard.setVisible(true);
				lbtCard.setForeground(Color.GRAY);
				lbtCard.setFont(new java.awt.Font("", 1, 14));
				profilePanel.add(lbtCard);
//				lbtCard.setForeground(Color.decode("64676a"));
//				JTextField tfCard = new JTextField(student.getUCard());
//				tfCard.setBounds(80, 20, 180, 30);
//				tfCard.setEditable(false);
//				tfCard.setHorizontalAlignment(SwingConstants.LEFT);
//				tfCard.setVisible(true);
//				profilePanel.add(tfCard);
				
				JLabel lbName = new JLabel(student.getUName());
				lbName.setBounds(250, 35, 225, 35);
				lbName.setFont(new java.awt.Font("", 1, 34));
				lbName.setForeground(Color.LIGHT_GRAY);
				lbName.setHorizontalAlignment(SwingConstants.LEFT);
				lbName.setVisible(true);
				profilePanel.add(lbName);
				
				JLabel lbRole = new JLabel("ROLE");
				lbRole.setBounds(60, 165, 100, 30);
				lbRole.setHorizontalAlignment(SwingConstants.LEFT);
				lbRole.setVisible(true);
				lbRole.setFont(new java.awt.Font("", 0, 14));
				lbRole.setForeground(Color.LIGHT_GRAY);
				profilePanel.add(lbRole);
				JLabel lbtRole = new JLabel(student.getURole());
				lbtRole.setBounds(150, 165, 180, 30);
				lbtRole.setHorizontalAlignment(SwingConstants.LEFT);
				lbtRole.setForeground(Color.GRAY);
				lbtRole.setFont(new java.awt.Font("", 1, 14));
				lbtRole.setVisible(true);
				profilePanel.add(lbtRole);
				
				JLabel lbID = new JLabel("ID");
				lbID.setBounds(60, 190, 100, 30);
				lbID.setHorizontalAlignment(SwingConstants.LEFT);
				lbID.setVisible(true);
				lbID.setFont(new java.awt.Font("", 0, 14));
				lbID.setForeground(Color.LIGHT_GRAY);
				profilePanel.add(lbID);
				JLabel lbtID = new JLabel(student.getUID());
				lbtID.setBounds(150, 190, 180, 30);
				lbtID.setHorizontalAlignment(SwingConstants.LEFT);
				lbtID.setForeground(Color.GRAY);
				lbtID.setFont(new java.awt.Font("", 1, 14));
				lbtID.setVisible(true);
				profilePanel.add(lbtID);
				
				JLabel lbSex = new JLabel("SEX");
				lbSex.setBounds(60, 215, 100, 30);
				lbSex.setHorizontalAlignment(SwingConstants.LEFT);
				lbSex.setVisible(true);
				lbSex.setFont(new java.awt.Font("", 0, 14));
				lbSex.setForeground(Color.LIGHT_GRAY);
				profilePanel.add(lbSex);
				JLabel lbtSex = new JLabel(student.getUSex());
				lbtSex.setBounds(150, 215, 180, 30);
				lbtSex.setHorizontalAlignment(SwingConstants.LEFT);
				lbtSex.setForeground(Color.GRAY);
				lbtSex.setFont(new java.awt.Font("", 1, 14));
				lbtSex.setVisible(true);
				profilePanel.add(lbtSex);
				
				JLabel lbClass = new JLabel("CLASS");
				lbClass.setBounds(60, 240, 100, 30);
				lbClass.setHorizontalAlignment(SwingConstants.LEFT);
				lbClass.setVisible(true);
				lbClass.setFont(new java.awt.Font("", 0, 14));
				lbClass.setForeground(Color.LIGHT_GRAY);
				profilePanel.add(lbClass);
				JLabel lbtClass = new JLabel(student.getUClass());
				lbtClass.setBounds(150, 240, 180, 30);
				lbtClass.setHorizontalAlignment(SwingConstants.LEFT);
				lbtClass.setForeground(Color.GRAY);
				lbtClass.setFont(new java.awt.Font("", 1, 14));
				lbtClass.setVisible(true);
				profilePanel.add(lbtClass);
				
				JLabel lbBirthday = new JLabel("BIRTHDAY");
				lbBirthday.setBounds(60, 265, 100, 30);
				lbBirthday.setHorizontalAlignment(SwingConstants.LEFT);
				lbBirthday.setVisible(true);
				lbBirthday.setFont(new java.awt.Font("", 0, 14));
				lbBirthday.setForeground(Color.LIGHT_GRAY);
				profilePanel.add(lbBirthday);
				JLabel lbtBirthday = new JLabel(student.getUBirthday());
				lbtBirthday.setBounds(150, 265, 180, 30);
				lbtBirthday.setHorizontalAlignment(SwingConstants.LEFT);
				lbtBirthday.setForeground(Color.GRAY);
				lbtBirthday.setFont(new java.awt.Font("", 1, 14));
				lbtBirthday.setVisible(true);
				profilePanel.add(lbtBirthday);
				
				JLabel lbHometown = new JLabel("HOME:");
				lbHometown.setBounds(60, 290, 100, 30);
				lbHometown.setHorizontalAlignment(SwingConstants.LEFT);
				lbHometown.setVisible(true);
				lbHometown.setFont(new java.awt.Font("", 0, 14));
				lbHometown.setForeground(Color.LIGHT_GRAY);
				profilePanel.add(lbHometown);
				JLabel lbtHometown = new JLabel(student.getUHometown());
				lbtHometown.setBounds(150, 290, 180, 30);
				lbtHometown.setHorizontalAlignment(SwingConstants.LEFT);
				lbtHometown.setForeground(Color.GRAY);
				lbtHometown.setFont(new java.awt.Font("", 1, 14));
				lbtHometown.setVisible(true);
				profilePanel.add(lbtHometown);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			
		} else if(user.getURole().equals("teacher")) {
			
			JLabel lbCard = new JLabel("CARD");
			lbCard.setBounds(60, 140, 100, 30);
			lbCard.setHorizontalAlignment(SwingConstants.LEFT);
			lbCard.setVisible(true);
			lbCard.setFont(new java.awt.Font("", 0, 14));
			lbCard.setForeground(Color.LIGHT_GRAY);
			profilePanel.add(lbCard);
			JLabel lbtCard = new JLabel(teacher.getUCard());
			lbtCard.setBounds(150, 140, 180, 30);
			lbtCard.setHorizontalAlignment(SwingConstants.LEFT);
			lbtCard.setVisible(true);
			lbtCard.setForeground(Color.GRAY);
			lbtCard.setFont(new java.awt.Font("", 1, 14));
			profilePanel.add(lbtCard);
			
			JLabel lbName = new JLabel(teacher.getUName());
			lbName.setBounds(250, 35, 225, 35);
			lbName.setFont(new java.awt.Font("", 1, 36));
			lbName.setForeground(Color.LIGHT_GRAY);
			lbName.setHorizontalAlignment(SwingConstants.LEFT);
			lbName.setVisible(true);
			profilePanel.add(lbName);
			
			JLabel lbRole = new JLabel("ROLE");
			lbRole.setBounds(60, 165, 100, 30);
			lbRole.setHorizontalAlignment(SwingConstants.LEFT);
			lbRole.setVisible(true);
			lbRole.setFont(new java.awt.Font("", 0, 14));
			lbRole.setForeground(Color.LIGHT_GRAY);
			profilePanel.add(lbRole);
			JLabel lbtRole = new JLabel(teacher.getURole());
			lbtRole.setBounds(150, 165, 180, 30);
			lbtRole.setHorizontalAlignment(SwingConstants.LEFT);
			lbtRole.setForeground(Color.GRAY);
			lbtRole.setFont(new java.awt.Font("", 1, 14));
			lbtRole.setVisible(true);
			profilePanel.add(lbtRole);
			
			JLabel lbID = new JLabel("ID");
			lbID.setBounds(60, 190, 100, 30);
			lbID.setHorizontalAlignment(SwingConstants.LEFT);
			lbID.setVisible(true);
			lbID.setFont(new java.awt.Font("", 0, 14));
			lbID.setForeground(Color.LIGHT_GRAY);
			profilePanel.add(lbID);
			JLabel lbtID = new JLabel(teacher.getUID());
			lbtID.setBounds(150, 190, 180, 30);
			lbtID.setHorizontalAlignment(SwingConstants.LEFT);
			lbtID.setForeground(Color.GRAY);
			lbtID.setFont(new java.awt.Font("", 1, 14));
			lbtID.setVisible(true);
			profilePanel.add(lbtID);
			
			JLabel lbSex = new JLabel("SEX");
			lbSex.setBounds(60, 215, 100, 30);
			lbSex.setHorizontalAlignment(SwingConstants.LEFT);
			lbSex.setVisible(true);
			lbSex.setFont(new java.awt.Font("", 0, 14));
			lbSex.setForeground(Color.LIGHT_GRAY);
			profilePanel.add(lbSex);
			JLabel lbtSex = new JLabel(teacher.getUSex());
			lbtSex.setBounds(150, 215, 180, 30);
			lbtSex.setHorizontalAlignment(SwingConstants.LEFT);
			lbtSex.setForeground(Color.GRAY);
			lbtSex.setFont(new java.awt.Font("", 1, 14));
			lbtSex.setVisible(true);
			profilePanel.add(lbtSex);
			
			JLabel lbClass = new JLabel("CLASS");
			lbClass.setBounds(60, 240, 100, 30);
			lbClass.setHorizontalAlignment(SwingConstants.LEFT);
			lbClass.setVisible(true);
			lbClass.setFont(new java.awt.Font("", 0, 14));
			lbClass.setForeground(Color.LIGHT_GRAY);
			profilePanel.add(lbClass);
			JLabel lbtClass = new JLabel(teacher.getUClass());
			lbtClass.setBounds(150, 240, 180, 30);
			lbtClass.setHorizontalAlignment(SwingConstants.LEFT);
			lbtClass.setForeground(Color.GRAY);
			lbtClass.setFont(new java.awt.Font("", 1, 14));
			lbtClass.setVisible(true);
			profilePanel.add(lbtClass);
			
		} else if(user.getURole().equals("admin")) {
			
			JLabel lbPic = new JLabel("");
			lbPic.setIcon(new ImageIcon(getClass().getResource("/res/fun.gif")));
			lbPic.setBounds(35, 30, 300, 300);
			lbPic.setHorizontalAlignment(SwingConstants.CENTER);
			lbPic.setVisible(true);
			profilePanel.add(lbPic);
		}
		
		JLabel lbPassword = new JLabel("New Pwd:");
		lbPassword.setBounds(450, 150, 100, 30);
		lbPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lbPassword.setVisible(true);
		lbPassword.setFont(new java.awt.Font("", 0, 14));
		lbPassword.setForeground(Color.LIGHT_GRAY);
		profilePanel.add(lbPassword);
		final JPasswordField tfPassword = new JPasswordField("");
		tfPassword.setBounds(540, 150, 180, 30);
		tfPassword.setHorizontalAlignment(SwingConstants.CENTER);
		tfPassword.setVisible(true);
		profilePanel.add(tfPassword);
		
		JLabel lbConPwd = new JLabel("Confirm:");
		lbConPwd.setBounds(450, 195, 100, 30);
		lbConPwd.setHorizontalAlignment(SwingConstants.LEFT);
		lbConPwd.setFont(new java.awt.Font("", 0, 14));
		lbConPwd.setForeground(Color.LIGHT_GRAY);
		lbConPwd.setVisible(true);
		profilePanel.add(lbConPwd);
		final JPasswordField tfConPwd = new JPasswordField("");
		tfConPwd.setBounds(540, 195, 180, 30);
		tfConPwd.setHorizontalAlignment(SwingConstants.CENTER);
		tfConPwd.setVisible(true);
		profilePanel.add(tfConPwd);
		
		JButton btnChangePwd = new JButton("");
		btnChangePwd.setBorderPainted(false);
		btnChangePwd.setIcon(new ImageIcon(getClass().getResource("/res/profilebtn.png")));
		btnChangePwd.setRolloverIcon(new ImageIcon(getClass().getResource("/res/profilebtn2.png")));
		btnChangePwd.setBounds(470, 250, 250, 47);
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

}
