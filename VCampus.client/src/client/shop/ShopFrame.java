/*
 * classname:ShopFrame
 * 
 * Date:2015,9,15
 * 
 */

package client.shop;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import client.util.ClientMsgHelper;
import conn.common.Goods;
import conn.common.User;

/*
 * create a new frame for the shop
 */
public class ShopFrame extends JFrame{

	private JPanel contentPane;
	private JPanel panel;

	public JPanel getcontentPane(){
		return contentPane;
	}
	public JPanel getPane(){
		return panel;
	}
	
	public ShopFrame(User m) throws UnknownHostException, IOException {

		ShopUser shopUser = new ShopUser(m);
		List<Goods> allgoods = new ArrayList<>();
		
		//get the all the goods
		String sql = "select * FROM Goods"; 
		ClientMsgHelper cmh = new ClientMsgHelper();
		cmh.selectGoods(sql);
		cmh.sendMsg();
		cmh.recieveMsg();
		allgoods = (List<Goods>) cmh.getDataInMsg();
		
		//create the label for every good
		List <JLabel> jl = new ArrayList<>();
		jl=VStoreManage.getVSM().getAllJLabel(shopUser,allgoods);
		
		setBackground(Color.WHITE);
		setBounds(100, 100, 900, 550);
		setSize(900,560);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		getContentPane().add(contentPane);
		setVisible(true);
		setSize(900, 560);
		
		panel =new JPanel();
		panel.setLayout(null);
		panel.setSize(900,560);
		contentPane.add(panel);
		
		JLabel label = new JLabel("\u865A\u62DF\u5546\u5E97");//显示名字”虚拟商店“
		label.setBounds(398, 10, 118, 46);
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		panel.add(label);
		
		//create the tabbed panel for showing goods
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 66, 672, 445);
		panel.add(tabbedPane);
		tabbedPane.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				int selectedIndex =tabbedPane.getSelectedIndex();
				String title = tabbedPane.getTitleAt(selectedIndex);
			}
		});
		
		//add all the goods in the tabbedpane where the type is
		JPanel contentPaneA1 =new JPanel(new FlowLayout(0,20,20));
		contentPaneA1.setBackground(Color.WHITE);
		JScrollPane contentPaneA =new JScrollPane(contentPaneA1);
		contentPaneA.setName("服装饰品");
		tabbedPane.add(contentPaneA);
		for(int i = 0;i<allgoods.size();i++)
		{
				if(allgoods.get(i).getGoodsType().equals("clothing")){
					jl.get(i).setBounds(59, 204, 103,93);
					contentPaneA1.add(jl.get(i));
				}
		}
	
		JPanel contentPaneB1 =new JPanel(new FlowLayout(0,20,20));
		contentPaneB1.setBackground(Color.WHITE);
		JScrollPane contentPaneB =new JScrollPane(contentPaneB1);
		contentPaneB.setName("电子产品");
		tabbedPane.add(contentPaneB);
		for(int i = 0;i<allgoods.size();i++)
		{
			if(allgoods.get(i).getGoodsType().equals("electronic")){
				contentPaneB1.add(jl.get(i));
			}
		}
	
		JPanel contentPaneC1 =new JPanel(new FlowLayout(0,20,20));
		contentPaneC1.setBackground(Color.WHITE);
		JScrollPane contentPaneC =new JScrollPane(contentPaneC1);
		contentPaneC.setName("鞋子箱包");
		tabbedPane.add(contentPaneC);
		for(int i = 0;i<allgoods.size();i++)
			{
				if(allgoods.get(i).getGoodsType().equals("shoes")){
					contentPaneC1.add(jl.get(i));
				}
			}
		
		JPanel contentPaneD1 =new JPanel(new FlowLayout(0,20,20));
		contentPaneD1.setBackground(Color.WHITE);
		JScrollPane contentPaneD =new JScrollPane(contentPaneD1);
		contentPaneD.setName("医药保健品");
		tabbedPane.add(contentPaneD);
		for(int i = 0;i<allgoods.size();i++)
		{
			if(allgoods.get(i).getGoodsType().equals("foods")){
				contentPaneD1.add(jl.get(i));
			}
		}
		
		JPanel contentPaneE1 =new JPanel(new FlowLayout(0,20,20));
		JScrollPane contentPaneE =new JScrollPane(contentPaneE1);
		contentPaneE1.setBackground(Color.WHITE);
		contentPaneE.setName("零食");
		tabbedPane.add(contentPaneE);
		for(int i = 0;i<allgoods.size();i++)
		{
			if(allgoods.get(i).getGoodsType().equals("drugs")){
				contentPaneE1.add(jl.get(i));
			}
		}
		
		final JButton btnClose = new JButton();
		btnClose.setBorderPainted(false);
		btnClose.setIcon(new ImageIcon(getClass().getResource("/res/btnclose.png")));
		btnClose.setRolloverIcon(new ImageIcon(getClass().getResource("/res/btnclose3.png")));
		btnClose.setBounds(0, 0, 40, 48);
		
		JButton button = new JButton("添加新商品");//如果是管理员，则有添加新商品按钮
		button.setFont(new Font("宋体", Font.PLAIN, 17));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final AddGoods addgood =new AddGoods();
				addgood.getPane().add(btnClose);
				panel.setVisible(false);
				contentPane.add(addgood.getPane());
				addgood.getPane().setVisible(true);
				addgood.getPane().setSize(900, 560);
				addgood.setVisible(false);
				
				btnClose.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						addgood.getPane().setVisible(false);	
						getContentPane().remove(addgood.getPane());
						panel.setVisible(true);
						System.out.println("Close");
					}
				});
				
			}
		}); 
		button.setBounds(720, 20, 139, 46);
		if(shopUser.isShopAdmin()){
			panel.add(button);
		}
	}
}



