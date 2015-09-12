package client.shop;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.*;

import javax.swing.*;
import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.*;

public class AddGoods extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					AddGoods a =new AddGoods();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddGoods() {
		final JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 359, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u865A\u62DF\u5546\u5E97");//显示“虚拟商店”名字
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(139, 10, 72, 20);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u5546\u54C1\u540D\u79F0");//商品名称，后面的文本框用于输入
		label_1.setFont(new Font("宋体", Font.PLAIN, 12));
		label_1.setBounds(70, 137, 54, 15);
		contentPane.add(label_1);
		textField = new JTextField();
		textField.setBounds(145, 135, 98, 18);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("\u5546\u54C1\u4EF7\u683C");//商品价格，后面的文本框用于输入
		label_2.setFont(new Font("宋体", Font.PLAIN, 12));
		label_2.setBounds(70, 162, 54, 15);
		contentPane.add(label_2);
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(145, 163, 98, 18);
		contentPane.add(textField_1);
		
		JLabel label_3 = new JLabel("\u5546\u54C1\u7C7B\u578B");//商品类型，后面的文本框用于输入
		label_3.setFont(new Font("宋体", Font.PLAIN, 12));
		label_3.setBounds(70, 187, 54, 15);
		contentPane.add(label_3);
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(145, 191, 98, 18);
		contentPane.add(textField_2);
		
		JLabel label_4 = new JLabel("\u5546\u54C1\u6570\u91CF");//商品数量，后面的文本框用于输入
		label_4.setFont(new Font("宋体", Font.PLAIN, 12));
		label_4.setBounds(70, 218, 54, 15);
		contentPane.add(label_4);
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(145, 219, 98, 18);
		contentPane.add(textField_3);
		
		JButton button = new JButton("\u4FDD\u5B58");//保存键，如果存在某一个信息没有填，则会提示填写该信息
		button.setFont(new Font("宋体", Font.PLAIN, 12));
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
				
				if(textField_2.getText().equals("")){
					JOptionPane.showMessageDialog(null,"请填写商品类型");
					return;
				}
				String type=textField_2.getText();
				
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
				
				VStoreManage.getVSM().AddGoods(name, number, type, price);		
				
				frame.dispose();
			}
		});
		button.setBounds(58, 265, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("重置");//重置键，将所有的输入框均变为空
		button_1.setFont(new Font("宋体", Font.PLAIN, 12));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
				textField_1.setText(null);
				textField_2.setText(null);
				textField_3.setText(null);
			}
		});
		button_1.setBounds(189, 265, 93, 23);
		contentPane.add(button_1);
		
		JLabel label_5 = new JLabel("\u56FE\u7247");//图片显示
		label_5.setFont(new Font("宋体", Font.PLAIN, 12));
		label_5.setBounds(86, 68, 38, 20);
		contentPane.add(label_5);
		
		JButton button_2 = new JButton("\u9009\u62E9\u56FE\u7247");//选择上传图片
		button_2.setFont(new Font("宋体", Font.PLAIN, 12));
		button_2.setBounds(145, 106, 98, 20);
		contentPane.add(button_2);
		final JLabel lblNewLabel = new JLabel("New label");//图片显示
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel.setBounds(145, 40, 98, 56);
		contentPane.add(lblNewLabel);
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



