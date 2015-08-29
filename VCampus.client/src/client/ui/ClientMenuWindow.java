//created on 2015-8-28
package client.ui;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import conn.common.*;


/**
 * 主菜单窗口
 * @author Suckysherry
 *
 */

public class ClientMenuWindow extends JFrame {
	//private static final long serialVersionUID = 3617579318470127671L;
	private JPanel contentPanel;
	private ClientLoginWindow clw;
	private User user;
	
	/**
	 * @param clwIn
	 * @param userIn
	 */
	
	public ClientMenuWindow(ClientLoginWindow clwIn, User userIn) {
		this.clw = clwIn;
		this.user = userIn;
		
		setResizable(false);
		setTitle("Menu");
		setBounds(100, 100, 900, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(getOwner());
		setType(Type.POPUP);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		getContentPane().add(panel);
		((JPanel) getContentPane()).setOpaque(false);
		//ImageIcon img = new ImageIcon(getClass().getResource("URL"));
		//JLabel background = new JLabel(img);
		//getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		//background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		panel.setLayout(null);
		
		JLabel lbUserRole = new JLabel(user.getURole());
		lbUserRole.setBounds(765, 235, 50, 20);
		lbUserRole.setHorizontalAlignment(SwingConstants.CENTER);
		lbUserRole.setVisible(true);
		panel.add(lbUserRole);
		
		JButton btnStudentInfor = new JButton("学籍管理");
		btnStudentInfor.setBorderPainted(true);//改为false
		//btnStudentInfor.setIcon(new ImageIcon(getClass().getResource("URL")));
		//btnStudentInfor.setRolloverIcon(new ImageIcon(getClass().getResource("URL")));
		btnStudentInfor.setBounds(30, 30, 177, 220);
		btnStudentInfor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//弹出学籍管理页面
			}
		});
		panel.add(btnStudentInfor);
		
		JButton btnjwc = new JButton("教务处");
		btnjwc.setBorderPainted(true);//改为false
		//btnjwc.setIcon(new ImageIcon(getClass().getResource("URL")));
		//btnjwc.setRolloverIcon(new ImageIcon(getClass().getResource("URL")));
		btnjwc.setBounds(240, 30, 177, 220);
		btnjwc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//弹出教务处页面
			}
		});
		panel.add(btnjwc);
		
		JButton btnShop = new JButton("商店");
		btnShop.setBorderPainted(true);//改为false
		//btnShop.setIcon(new ImageIcon(getClass().getResource("URL")));
		//btnShop.setRolloverIcon(new ImageIcon(getClass().getResource("URL")));
		btnShop.setBounds(240, 270, 177, 220);
		btnShop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//弹出教务处页面
			}
		});
		panel.add(btnShop);
		
		JButton btnLibrary = new JButton("图书馆");
		btnLibrary.setBorderPainted(true);//改为false
		//btnLibrary.setIcon(new ImageIcon(getClass().getResource("URL")));
		//btnLibrary.setRolloverIcon(new ImageIcon(getClass().getResource("URL")));
		btnLibrary.setBounds(30, 270, 177, 220);
		btnLibrary.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//弹出教务处页面
			}
		});
		panel.add(btnLibrary);
		
		if(user.getURole().equals("admin")) {
			JButton btnUserMng = new JButton("用户管理");
			btnUserMng.setBorderPainted(true);//false
			//btnUserMng.setIcon(new ImageIcon(getClass().getResource("URL")));
			//btnUserMng.setRolloverIcon(new ImageIcon(getClass().getResource("URL")));
			btnUserMng.setBounds(690, 310, 177, 46);
			btnUserMng.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					//用户管理界面，增删改学生、老师、管理员等
				}
			});
			panel.add(btnUserMng);
		}
		
		JButton btnProfile = new JButton("个人资料");
		btnProfile.setBorderPainted(true);//改为false
		//btnProfile.setIcon(new ImageIcon(getClass().getResource("URL")));
		//btnProfile.setRolloverIcon(new ImageIcon(getClass().getResource("URL")));
		btnProfile.setBounds(690, 370, 177, 46);
		btnProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//弹出个人资料界面
			}
		});
		panel.add(btnProfile);
		
		JButton btnLogout = new JButton("注销");
		btnLogout.setBorderPainted(true);//改为false
		//btnLogout.setIcon(new ImageIcon(getClass().getResource("URL")));
		//btnLogout.setRolloverIcon(new ImageIcon(getClass().getResource("URL")));
		btnLogout.setBounds(690, 430, 177, 48);
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//弹出注销界面
			}
		});
		panel.add(btnLogout);
		
		clw.frame.setVisible(false);
		
	}
	
	public JPanel getContentPanel() {
		return contentPanel;
	}
	
	public void setContenPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

}
