/*
 * classname:GoodsUpdate
 * 
 * Date:2015,9,15
 * 
 */
package client.shop;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import client.util.ClientMsgHelper;
import conn.common.Goods;

/*
 * create a new panel for updating the information about good
 */
public class GoodsUpdate extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JComboBox<String> comboBox;

	
	public JPanel getPane(){
		return panel;
	}
	
	public GoodsUpdate(final Goods good) {
	
		setResizable(false);
		setTitle("修改商品信息");
		setBounds(100, 100, 655, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setSize(655,450);
		getContentPane().add(contentPane);
		contentPane.setLayout(null);
		
		panel= new JPanel();
		panel.setLocation(0, 0);
		panel.setLayout(null);
		panel.setSize(637,426);
		contentPane.add(panel);
		
		JLabel label = new JLabel("商品信息修改");//显示“虚拟商店”名字
		label.setFont(new Font("宋体", Font.PLAIN, 21));
		label.setBounds(236, 22, 168, 20);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u5546\u54C1\u540D\u79F0");//商品名称，后面的文本框用于修改
		label_1.setFont(new Font("宋体", Font.PLAIN, 17));
		label_1.setBounds(57, 133, 77, 31);
		panel.add(label_1);
		textField = new JTextField(good.getGoodsName());
		textField.setBounds(209, 134, 98, 31);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("\u5546\u54C1\u4EF7\u683C");//商品价格，后面的文本框用于修改
		label_2.setFont(new Font("宋体", Font.PLAIN, 17));
		label_2.setBounds(57, 185, 77, 31);
		panel.add(label_2);
		textField_1 = new JTextField(good.getGoodsPrice()+"");
		textField_1.setColumns(10);
		textField_1.setBounds(209, 185, 98, 31);
		panel.add(textField_1);
		
		JLabel label_3 = new JLabel("\u5546\u54C1\u7C7B\u578B");//商品类型，后面的文本框用于修改
		label_3.setFont(new Font("宋体", Font.PLAIN, 17));
		label_3.setBounds(57, 243, 77, 31);
		panel.add(label_3);
		final JComboBox<String> comboBox=new JComboBox();
		String type="";
		if(good.getGoodsType().equals("clothing")){
			type = "服装饰品";
		}else if(good.getGoodsType().equals("shoes")){
			type = "鞋子箱包";
		}else if(good.getGoodsType().equals("foods")){
			type = "零食";
		}else if(good.getGoodsType().equals("drugs")){
			type = "医药保健";
		}else if(good.getGoodsType().equals("electronic")){
			type = "电子产品";
		}
		comboBox.setSelectedItem(type);
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(209, 244, 98, 31);
	    comboBox.addItem("服装饰品");
	    comboBox.addItem("电子产品");
	    comboBox.addItem("鞋子箱包");
	    comboBox.addItem("零食");
	    comboBox.addItem("医药保健");
	    String type1 = null;
		if(good.getGoodsType().equals("clothing")){
			type1 = "服装饰品";	
		}else if(good.getGoodsType().equals("electronic")){
			type1 = "电子产品";	
		}else if(good.getGoodsType().equals("shoes")){
			type1 = "鞋子箱包";		
		}else if(good.getGoodsType().equals("foods")){
			type1 = "零食";	
		}else if(good.getGoodsType().equals("drugs")){
			type1 = "医药保健";	
		}
		comboBox.setSelectedItem(type);
		panel.add(comboBox);
		
		JLabel label_4 = new JLabel("\u5546\u54C1\u6570\u91CF");//商品数量，后面的文本框用于修改
		label_4.setFont(new Font("宋体", Font.PLAIN, 17));
		label_4.setBounds(57, 311, 68, 20);
		panel.add(label_4);
		textField_3 = new JTextField(good.getGoodNumber()+"");
		textField_3.setColumns(10);
		textField_3.setBounds(209, 307, 98, 31);
		panel.add(textField_3);
		
		JButton button = new JButton("\u4FDD\u5B58");//保存键，如果存在某一个信息没有填，则会提示填写该信息
		button.setBackground(Color.WHITE);
		button.setFont(new Font("宋体", Font.PLAIN, 17));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textField.getText().equals("")){
					JOptionPane.showMessageDialog(null,"请填写商品名称");
					return;
				}
				String name=textField.getText();
				
				if(textField_1.getText().equals("")){
					JOptionPane.showMessageDialog(null,"请填写商品价格");
					return;
				}
				String sprice=textField_1.getText();
				int price = 0;
				try{
					price =Integer.parseInt(sprice);
				}catch(NumberFormatException e1){
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"商品价格输入错误，请重新输入");}
				
				if(textField_3.getText().equals("")){
					JOptionPane.showMessageDialog(null,"请填写商品数量");
					return;
				}
				String snumber=textField_3.getText();
				int number = 0;
				try{
					number =Integer.parseInt(snumber);
				}catch(NumberFormatException e1){
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"商品数量输入错误，请重新输入");}
				
				String type = null;
				if(((String) comboBox.getSelectedItem()).equals("服装饰品")){
					type = "clothing";	
				}else if
				(((String) comboBox.getSelectedItem()).equals("电子产品")){
					type = "electronic";	
				}else if
				(((String) comboBox.getSelectedItem()).equals("鞋子箱包")){
					type = "shoes";	
				}else if
				(((String) comboBox.getSelectedItem()).equals("零食")){
					type = "foods";	
				}else if
				(((String) comboBox.getSelectedItem()).equals("医药保健")){
					type = "drugs";	
				}
				
				
				String sql="update Goods set goodsName='"+name+"',"
						+ " goodsNumber='"+number+"', goodsType='"+type+"',goodsPrice='"+price+"' "
						+ "where goodsname ='"+good.getGoodsName()+"';";
				ClientMsgHelper cmh = new ClientMsgHelper();
				cmh.update(sql);
				cmh.sendMsg();
				cmh.recieveMsg();	
				
				good.setGoodsName(name);
				good.setGoodsType(type);
				good.setGoodsPrice(price);
				good.setGoodNumber(number);
				JOptionPane.showMessageDialog(null,"保存成功");
				
				dispose();
			}
		});
		button.setBounds(178, 363, 93, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("重置");//重置键，将所有的输入框均变为空
		button_1.setBackground(Color.WHITE);
		button_1.setFont(new Font("宋体", Font.PLAIN, 17));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
				textField_1.setText(null);
				textField_3.setText(null);
			}
		});
		button_1.setBounds(366, 363, 93, 23);
		panel.add(button_1);
		
		JLabel label_5 = new JLabel("\u56FE\u7247");//图片显示
		label_5.setFont(new Font("宋体", Font.PLAIN, 17));
		label_5.setBounds(57, 75, 50, 25);
		panel.add(label_5);
		
		JButton button_2 = new JButton("\u9009\u62E9\u56FE\u7247");//选择上传图片
		button_2.setBackground(Color.WHITE);
		button_2.setFont(new Font("宋体", Font.PLAIN, 17));
		button_2.setBounds(209, 72, 98, 31);
		panel.add(button_2);
		
		final JLabel lblNewLabel = new JLabel("New label");//图片显示
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		lblNewLabel.setBounds(364, 100, 243, 223);
		ImageIcon pic = new ImageIcon(getClass().getResource(good.getGoodsImage()));
		pic.setImage(pic.getImage().getScaledInstance(228,219,Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(pic);
		panel.add(lblNewLabel);
		
		button_2.addActionListener(new ActionListener() {   //从文件中选择图片
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			FileFilter filter = new FileNameExtensionFilter("图片格式（JPG/GIF）","JPG","JPEG","GIF");
			fileChooser.setFileFilter(filter);
			
			int i = fileChooser.showOpenDialog(getContentPane());
		
			if (i == JFileChooser.APPROVE_OPTION) {
				
				File selectedFile = fileChooser.getSelectedFile();
				lblNewLabel.setIcon(new ImageIcon(selectedFile
						.getAbsolutePath()));
				lblNewLabel.setText(null);
			}
		}
	});
	}
}
	





