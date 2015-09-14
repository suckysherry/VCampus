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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		getContentPane().add(panel);
		((JPanel) getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon(getClass().getResource("/res/menubackground.png"));
		JLabel background = new JLabel(img);
		getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		panel.setLayout(null);
		
		/**
		 * 显示用户身份
		 */
		JLabel lbUserRole = new JLabel(user.getURole());
		lbUserRole.setBounds(760, 327, 50, 20);
		lbUserRole.setHorizontalAlignment(SwingConstants.CENTER);
		lbUserRole.setVisible(true);
		panel.add(lbUserRole);
		
		/**
		 * 显示用户名字
		 */
		JLabel lbUserName = new JLabel(user.getUName());
		lbUserName.setBounds(760, 347, 50, 20);
		lbUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lbUserName.setVisible(true);
		panel.add(lbUserName);

//		在menu添加动图
		JLabel lbPic = new JLabel("");
		lbPic.setIcon(new ImageIcon(getClass().getResource("/res/cute.gif")));
		lbPic.setBounds(680, 40, 177, 220);
		lbPic.setHorizontalAlignment(SwingConstants.CENTER);
		lbPic.setVisible(true);
		panel.add(lbPic);
		
		/**
		 * 学籍管理
		 */
		JButton btnStudentInfor = new JButton("");
		btnStudentInfor.setBorderPainted(false);
		btnStudentInfor.setIcon(new ImageIcon(getClass().getResource("/res/xueji3.png")));
		btnStudentInfor.setRolloverIcon(new ImageIcon(getClass().getResource("/res/xueji.png")));
		btnStudentInfor.setBounds(30, 30, 177, 220);
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
		panel.add(btnStudentInfor);
		
		/**
		 * 教务处
		 */
		JButton btnjwc = new JButton("");
		btnjwc.setBorderPainted(false);
		btnjwc.setIcon(new ImageIcon(getClass().getResource("/res/jiaowu.png")));
		btnjwc.setRolloverIcon(new ImageIcon(getClass().getResource("/res/jiaowu2.png")));
		btnjwc.setBounds(240, 30, 177, 220);
		btnjwc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//弹出教务处页面
			}
		});
		panel.add(btnjwc);
		
		/**
		 * 商店
		 */
		JButton btnShop = new JButton("");
		btnShop.setBorderPainted(false);
		btnShop.setIcon(new ImageIcon(getClass().getResource("/res/market.png")));
		btnShop.setRolloverIcon(new ImageIcon(getClass().getResource("/res/market2.png")));
		btnShop.setBounds(240, 270, 177, 220);
		btnShop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new ShopFrame(user);
			}
		});
		panel.add(btnShop);
		
		/**
		 * 图书馆
		 */
		JButton btnLibrary = new JButton("");
		btnLibrary.setBorderPainted(false);
		btnLibrary.setIcon(new ImageIcon(getClass().getResource("/res/library3.png")));
		btnLibrary.setRolloverIcon(new ImageIcon(getClass().getResource("/res/library4.png")));
		btnLibrary.setBounds(30, 270, 177, 220);
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
		panel.add(btnLibrary);
		
		/**
		 * 用户管理
		 */
		if(user.getURole().equals("admin")) {
			JButton btnUserMng = new JButton("");
			btnUserMng.setBorderPainted(false);
			btnUserMng.setIcon(new ImageIcon(getClass().getResource("/res/manage.png")));
			btnUserMng.setRolloverIcon(new ImageIcon(getClass().getResource("/res/manage2.png")));
			btnUserMng.setBounds(450, 270, 177, 220);
			btnUserMng.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					ClientUserManageWindow cumw = new ClientUserManageWindow();
//					cumw.setVisible(true);
					JPanel cumwPanel = cumw.getJPanel();
					JLabel cumwBackground = cumw.getBackground();
					
					getContentPane().removeAll();
					getContentPane().invalidate();
					getContentPane().add(cumwPanel);
					getContentPane().revalidate();
					
					getLayeredPane().remove(background);
					getLayeredPane().add(cumwBackground, new Integer(Integer.MIN_VALUE));
					
				}
			});
			panel.add(btnUserMng);
		}
		
		/**
		 * 个人资料
		 */
		JButton btnProfile = new JButton("");
		btnProfile.setBorderPainted(false);
		btnProfile.setIcon(new ImageIcon(getClass().getResource("/res/userinfo.png")));
		btnProfile.setRolloverIcon(new ImageIcon(getClass().getResource("/res/userinfo2.png")));
		btnProfile.setBounds(450, 30, 177, 220);
		btnProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ClientProfileWindow cpw = new ClientProfileWindow(user);
				cpw.setVisible(true);
			}
		});
		panel.add(btnProfile);
		
		/**
		 * 注销
		 */
		JButton btnLogout = new JButton("");
		btnLogout.setBorderPainted(false);
		btnLogout.setIcon(new ImageIcon(getClass().getResource("/res/menulo.png")));
		btnLogout.setRolloverIcon(new ImageIcon(getClass().getResource("/res/menulo2.png")));
		btnLogout.setBounds(690, 420, 180, 47);
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clw.frame.setVisible(true);
				dispose();
				System.out.println("Close");;
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
