/*
 * classname:GoodsInformation
 * 
 * Date:2015,9,14
 */

package client.shop;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conn.common.Goods;

/*
 * creat a frame for showing the Information about the good
 */

public class GoodsInformation extends JFrame {

	private JPanel contentPane;
	private JPanel panel;

	
	public JPanel getPane(){
		return panel;
	}
	
	public GoodsInformation(final ShopUser m,final Goods a) {
		
		setTitle("商品信息");
		setBounds(100, 100, 653, 455);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane);
		contentPane.setLayout(null);
		
		panel =new JPanel();
		panel.setLocation(0, 0);
		panel.setLayout(null);
		panel.setSize(637,426);
		contentPane.add(panel);
		
		JLabel label = new JLabel("商品信息");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(226, 10, 98, 57);
		panel.add(label);
		
		JLabel label_1 = new JLabel("商品名称");
		label_1.setFont(new Font("宋体", Font.PLAIN, 17));
		label_1.setBounds(24, 77, 86, 32);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("商品价格");
		label_2.setFont(new Font("宋体", Font.PLAIN, 17));
		label_2.setBounds(24, 125, 86, 45);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("商品类型");
		label_3.setFont(new Font("宋体", Font.PLAIN, 17));
		label_3.setBounds(22, 180, 72, 32);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("商品数量");
		label_4.setFont(new Font("宋体", Font.PLAIN, 17));
		label_4.setBounds(24, 235, 86, 38);
		panel.add(label_4);
		
		//show the good picture
		ImageIcon pic = new ImageIcon(getClass().getResource(a.getGoodsImage()));
		pic.setImage(pic.getImage().getScaledInstance(228,219,Image.SCALE_DEFAULT));
		JLabel label_6 = new JLabel();
		label_6.setIcon(pic);
		label_6.setFont(new Font("宋体", Font.PLAIN, 12));
		label_6.setBounds(336, 81, 228, 219);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel(a.getGoodsName());
		label_7.setFont(new Font("宋体", Font.PLAIN, 17));
		label_7.setBounds(144, 77, 118, 28);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel(""+a.getGoodsPrice());
		label_8.setFont(new Font("宋体", Font.PLAIN, 17));
		label_8.setBounds(144, 125, 118, 45);
		panel.add(label_8);
		
		 String type1 = null;
			if(a.getGoodsType().equals("clothing")){
				type1 = "服装饰品";	
			}else if(a.getGoodsType().equals("electronic")){
				type1 = "电子产品";	
			}else if(a.getGoodsType().equals("shoes")){
				type1 = "鞋子箱包";		
			}else if(a.getGoodsType().equals("foods")){
				type1 = "零食";	
			}else if(a.getGoodsType().equals("drugs")){
				type1 = "医药保健";	
			}
			
		JLabel label_9 = new JLabel(type1);
		label_9.setFont(new Font("宋体", Font.PLAIN, 17));
		label_9.setBounds(142, 180, 107, 32);
		panel.add(label_9);
		
		JLabel label_10 = new JLabel(""+a.getGoodNumber());
		label_10.setFont(new Font("宋体", Font.PLAIN, 17));
		label_10.setBounds(144, 241, 118, 28);
		panel.add(label_10);
		
		JButton button_1 = new JButton("返回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 12));
		button_1.setBounds(243, 341, 93, 23);
		panel.add(button_1);
		
		final JButton btnClose = new JButton();
		btnClose.setBorderPainted(false);
		btnClose.setIcon(new ImageIcon(getClass().getResource("/res/btnclose.png")));
		btnClose.setRolloverIcon(new ImageIcon(getClass().getResource("/res/btnclose3.png")));
		btnClose.setBounds(0, 0, 40, 48);
		
		//add a ActionListener for store the good and update it in DB 
		JButton button_2 = new JButton("修改信息");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final GoodsUpdate update = new GoodsUpdate(a);
				panel.setVisible(false);
				update.getPane().add(btnClose);
				contentPane.add(update.getPane());
				update.getPane().setVisible(true);
				update.getPane().setSize(655, 455);
				update.setVisible(false);
				
				btnClose.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						update.getPane().setVisible(false);
						getContentPane().remove(update.getPane());
						panel.setVisible(true);
						System.out.println("Close");
					}
				});

			}
		});
		button_2.setFont(new Font("宋体", Font.PLAIN, 12));
		button_2.setBounds(169, 340, 93, 23);
		if(m.isShopAdmin()){
			panel.add(button_2);
			button_1.setBounds(320, 340, 93, 23);
		}
	}
	
}
