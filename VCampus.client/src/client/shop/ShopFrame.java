package client.shop;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import conn.common.*;

public class ShopFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	//主函数用于测试
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					User user =new User();
//					ShopFrame b =new ShopFrame(user);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	 /**
	 * Create the frame.
	 */
	public ShopFrame(User m) {
		ShopUser shopUser = new ShopUser(m);
		List<Goods> a = new ArrayList<>();
		a=	VStoreManage.getVSM().getAllGoods();
		List <JLabel> jl = new ArrayList<>();
		jl=VStoreManage.getVSM().getAllJLabel(shopUser,a);
		
		JFrame frame =new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 481, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u865A\u62DF\u5546\u5E97");//显示名字”虚拟商店“
		label.setBounds(170, 21, 72, 20);
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u6211\u7684\u4F59\u989D\uFF1A"+shopUser.getUserCash());//如果不是管理员则显示当前余额
		label_1.setFont(new Font("宋体", Font.PLAIN, 12));
		label_1.setBounds(300, 10, 72, 20);
		if(!shopUser.isShopAdmin()){
			contentPane.add(label_1);
		}
		
		
		JButton button = new JButton("\u6DFB\u52A0\u65B0\u5546\u54C1");//如果是管理员，则有添加新商品按钮
		button.setFont(new Font("宋体", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGoods a=new AddGoods();
			}
		});
		button.setBounds(300, 33, 93, 23);
		if(shopUser.isShopAdmin()){
			contentPane.add(button);
		}
		
		
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 66, 445, 243);
		contentPane.add(tabbedPane);
		tabbedPane.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				int selectedIndex =tabbedPane.getSelectedIndex();
				String title = tabbedPane.getTitleAt(selectedIndex);
			}
		});
		
		JPanel contentPaneA1 =new JPanel(new GridLayout(0,5,6,6));
		JScrollPane contentPaneA =new JScrollPane(contentPaneA1);
		contentPaneA.setName("全部商品");
		tabbedPane.add(contentPaneA);
		for(int i = 0;i<a.size();i++)
		{
			contentPaneA1.add(jl.get(i));
		}	
	
		JPanel contentPaneB1 =new JPanel(new GridLayout(0,5,6,6));
		JScrollPane contentPaneB =new JScrollPane(contentPaneB1);
		contentPaneB.setName("电子产品");
		tabbedPane.add(contentPaneB);
		for(int i = 0;i<a.size();i++)
		{
			if(a.get(i).getGoodsType().equals("e")){
			System.out.println("电子产品");
				contentPaneB1.add(jl.get(i));
			}
		}
	
		JPanel contentPaneC1 =new JPanel(new GridLayout(0,5,6,6));
		JScrollPane contentPaneC =new JScrollPane(contentPaneC1);
		contentPaneC.setName("生活用品");
		tabbedPane.add(contentPaneC);
		for(int i = 0;i<a.size();i++)
		{
			if(a.get(i).getGoodsType().equals("l")){
				contentPaneC1.add(jl.get(i));
				System.out.println("生活用品");
			}
		}
		
		JPanel contentPaneD1 =new JPanel(new GridLayout(0,5,6,6));
		JScrollPane contentPaneD =new JScrollPane(contentPaneD1);
		contentPaneD.setName("衣服鞋帽");
		tabbedPane.add(contentPaneD);
		for(int i = 0;i<a.size();i++)
		{
			if(a.get(i).getGoodsType().equals("w")){
				contentPaneD1.add(jl.get(i));
			}
		}
		
	}
	
}

