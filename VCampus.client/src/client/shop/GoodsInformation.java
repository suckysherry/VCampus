package client.shop;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GoodsInformation extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public GoodsInformation( final ShopUser m,final Goods a) {
		final JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 453, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("虚拟商店");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(180, 10, 72, 20);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("商品名称");
		label_1.setFont(new Font("宋体", Font.PLAIN, 12));
		label_1.setBounds(99, 108, 54, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("商品价格");
		label_2.setFont(new Font("宋体", Font.PLAIN, 12));
		label_2.setBounds(99, 133, 54, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("商品类型");
		label_3.setFont(new Font("宋体", Font.PLAIN, 12));
		label_3.setBounds(99, 158, 54, 15);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("商品数量");
		label_4.setFont(new Font("宋体", Font.PLAIN, 12));
		label_4.setBounds(99, 189, 54, 15);
		contentPane.add(label_4);
		
		JButton button = new JButton("购买");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final ShopUser user =m;
				final Goods goods =a;
				user.buy(goods);
				dispose();
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 12));
		button.setBounds(77, 214, 93, 23);
		
		JButton button_2 = new JButton("增加");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				m.add(a);
				JOptionPane.showMessageDialog(null,"增加成功");
				frame.dispose();;
			}
		});
		button_2.setFont(new Font("宋体", Font.PLAIN, 12));
		button_2.setBounds(77, 214, 93, 23);
		
		//如果用户role是管理员，显示增加按钮；如果用户role是普通用户，则显示购买按钮
		if(m.isShopAdmin()){
			contentPane.add(button_2);
		}else{
			contentPane.add(button);
		}
		
		JButton button_1 = new JButton("取消");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 12));
		button_1.setBounds(237, 218, 93, 23);
		contentPane.add(button_1);
		
		JLabel label_5 = new JLabel("图片");
		label_5.setFont(new Font("宋体", Font.PLAIN, 12));
		label_5.setBounds(126, 74, 38, 20);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("New label");//显示图片
		label_6.setFont(new Font("宋体", Font.PLAIN, 12));
		label_6.setBounds(180, 40, 93, 56);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel(a.getGoodsName());
		label_7.setBounds(190, 108, 107, 15);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel(""+a.getGoodsPrice());
		label_8.setBounds(190, 133, 107, 15);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel(a.getGoodsType());
		label_9.setBounds(190, 158, 107, 15);
		contentPane.add(label_9);
		
		JLabel label_10 = new JLabel(""+a.getGoodNumber());
		label_10.setBounds(190, 189, 107, 15);
		contentPane.add(label_10);
	}
	

}
