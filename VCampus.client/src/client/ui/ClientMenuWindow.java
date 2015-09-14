//created on 2015-8-28
package client.ui;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import conn.common.*;
import client.ui.*;
import client.library.*;
import client.shop.ShopFrame;
import client.studentInfor.SchoolRollDriver;


/**
 * 主菜单窗口
 * @author Daisiqi
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
		getContentPane().setLayout(new CardLayout(0, 0));
		
		/**
		 * 总panel，包括主菜单，注销，动画三个panel
		 */
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		getContentPane().add(panel, "name_9354456756404");
		((JPanel) getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon(getClass().getResource("/res/menubackground.png"));
		JLabel background = new JLabel(img);
		getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		panel.setLayout(null);
		
		/**
		 * 上层主panel，包括教务处、图书馆等等
		 */
		JPanel subMainPanel = new JPanel();
		subMainPanel.setBounds(6, 6, 650, 516);
		subMainPanel.setOpaque(false);
		panel.add(subMainPanel);
		subMainPanel.setLayout(null);
		JButton btnStudentInfor = new JButton("");
		btnStudentInfor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		/**
		 * 学籍管理
		 */
		btnStudentInfor.setBounds(25, 19, 177, 220);
		subMainPanel.add(btnStudentInfor);
		btnStudentInfor.setBorderPainted(false);
		btnStudentInfor.setIcon(new ImageIcon(getClass().getResource("/res/xueji3.png")));
		btnStudentInfor.setRolloverIcon(new ImageIcon(getClass().getResource("/res/xueji.png")));
		btnStudentInfor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					SchoolRollDriver srd = new SchoolRollDriver(user);
					srd.judge();
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
		/**
		 * 教务处
		 */
		JButton btnjwc = new JButton("");
		btnjwc.setBounds(242, 19, 177, 220);
		subMainPanel.add(btnjwc);
		btnjwc.setBorderPainted(false);
		btnjwc.setIcon(new ImageIcon(getClass().getResource("/res/jiaowu.png")));
		btnjwc.setRolloverIcon(new ImageIcon(getClass().getResource("/res/jiaowu2.png")));
		btnjwc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//弹出教务处页面
			}
		});
		
		/**
		 * 商店
		 */
		JButton btnShop = new JButton("");
		btnShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnShop.setBounds(242, 266, 177, 220);
		subMainPanel.add(btnShop);
		btnShop.setBorderPainted(false);
		btnShop.setIcon(new ImageIcon(getClass().getResource("/res/market.png")));
		btnShop.setRolloverIcon(new ImageIcon(getClass().getResource("/res/market2.png")));
		btnShop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new ShopFrame(user);
			}
		});
		
		/**
		 * 图书馆
		 */
		JButton btnLibrary = new JButton("");
		btnLibrary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLibrary.setBounds(25, 266, 177, 220);
		subMainPanel.add(btnLibrary);
		btnLibrary.setBorderPainted(false);
		btnLibrary.setIcon(new ImageIcon(getClass().getResource("/res/library3.png")));
		btnLibrary.setRolloverIcon(new ImageIcon(getClass().getResource("/res/library4.png")));
		btnLibrary.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					new LibraryMain(user);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		/**
		 * 个人信息
		 */
		JButton btnProfile = new JButton("");
		btnProfile.setBounds(455, 19, 177, 220);
		subMainPanel.add(btnProfile);
		btnProfile.setBorderPainted(false);
		btnProfile.setIcon(new ImageIcon(getClass().getResource("/res/userinfo.png")));
		btnProfile.setRolloverIcon(new ImageIcon(getClass().getResource("/res/userinfo2.png")));
		btnProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ClientProfileWindow cpw = new ClientProfileWindow(user);
				cpw.setVisible(true);
			}
		});
		
		/**
		 * 用户管理
		 */
		JButton btnUserMng = new JButton("");
		btnUserMng.setBounds(455, 266, 177, 220);
		subMainPanel.add(btnUserMng);
		btnUserMng.setBorderPainted(false);
		btnUserMng.setIcon(new ImageIcon(getClass().getResource("/res/manage.png")));
		btnUserMng.setRolloverIcon(new ImageIcon(getClass().getResource("/res/manage2.png")));
		btnUserMng.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ClientUserManageWindow cumw = new ClientUserManageWindow();
				cumw.setVisible(true);
			}
		});
		if(!(user.getURole().equals("admin"))) {
			btnUserMng.setVisible(false);
		}
		
		/**
		 * 注销panel，包括注销按钮
		 */
		JPanel subLogoutPanel = new JPanel();
		subLogoutPanel.setBounds(668, 379, 226, 143);
		subLogoutPanel.setOpaque(false);
		panel.add(subLogoutPanel);
		subLogoutPanel.setLayout(null);
		JButton btnLogout = new JButton("");
		btnLogout.setBounds(6, 23, 214, 59);
		subLogoutPanel.add(btnLogout);
		btnLogout.setBorderPainted(false);
		btnLogout.setIcon(new ImageIcon(getClass().getResource("/res/menulo.png")));
		btnLogout.setRolloverIcon(new ImageIcon(getClass().getResource("/res/menulo2.png")));
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clw.frame.setVisible(true);
				dispose();
				System.out.println("Close");;
			}
		});
		
		/**
		 * 菜单栏panel，包括动图及身份
		 */
		JPanel subMenuPanel = new JPanel();
		subMenuPanel.setBounds(668, 6, 226, 367);
		subMenuPanel.setOpaque(false);
		panel.add(subMenuPanel);
		subMenuPanel.setLayout(null);
		
		/**
		 * gif
		 */
		JLabel lbPic = new JLabel("");
		lbPic.setBounds(-95, -11, 400, 300);
		subMenuPanel.add(lbPic);
		lbPic.setIcon(new ImageIcon(getClass().getResource("/res/cute.gif")));
		lbPic.setHorizontalAlignment(SwingConstants.CENTER);
		
		/**
		 * 用户身份
		 */
		JLabel lbUserRole = new JLabel(user.getURole());
		lbUserRole.setBounds(80, 317, 73, 16);
		subMenuPanel.add(lbUserRole);
		lbUserRole.setHorizontalAlignment(SwingConstants.CENTER);
		lbUserRole.setVisible(true);
		
		/**
		 * 用户名字
		 */
		JLabel lbUserName = new JLabel(user.getUName());
		lbUserName.setBounds(80, 345, 73, 16);
		subMenuPanel.add(lbUserName);
		lbUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lbUserName.setVisible(true);
		lbPic.setVisible(true);
		
		clw.frame.setVisible(false);
		
	}
	
	public JPanel getContentPanel() {
		return contentPanel;
	}
	
	public void setContenPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}
}
