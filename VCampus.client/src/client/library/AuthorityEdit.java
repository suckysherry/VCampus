package client.library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.org.apache.xpath.internal.operations.Number;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AuthorityEdit extends JFrame {

	private JPanel contentPane;
	private JTextField maxDaysText;
	private JTextField maxBookNumText;
	private int currentMaxDays;
	private int currentMaxNum;

	/**
	 * Launch the application.
	 */
	
	//主函数用于测试
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthorityEdit frame = new AuthorityEdit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AuthorityEdit() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 376, 236);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("权限设置");//标题“虚拟校园图书馆”
		label.setBounds(137, 0, 92, 53);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 23));
		contentPane.add(label);
		
		maxDaysText = new JTextField();//输入框，用于查询书名
		maxDaysText.setBounds(128, 66, 134, 23);
		maxDaysText.setColumns(10);
		contentPane.add(maxDaysText);
		
		JLabel lblNewLabel = new JLabel("借书天数上限");
		lblNewLabel.setBounds(26, 70, 92, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("借书数量上限");
		lblNewLabel_1.setBounds(26, 118, 92, 15);
		contentPane.add(lblNewLabel_1);
		
		maxBookNumText = new JTextField();
		maxBookNumText.setBounds(128, 115, 134, 21);
		contentPane.add(maxBookNumText);
		maxBookNumText.setColumns(10);
		
		JButton submit = new JButton("保存");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(editAuthorityAction()){
					JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "权限修改成功!", "权限设置", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "权限修改失败,未知错误!", "权限设置", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		submit.setBounds(137, 165, 93, 23);
		contentPane.add(submit);
		updateCurrentAuthority();
	}

	private void updateCurrentAuthority() {
		OperateDB opdb = new OperateDB();
		int[] numbers = {0,0};
		numbers = opdb.updateCurrentAuthority();	
		currentMaxDays = numbers[0];
		currentMaxNum = numbers[1];
		maxBookNumText.setText(currentMaxNum+"");
		maxDaysText.setText(currentMaxDays+"");
	}

	public static boolean isNumeric(String str){ //是否为纯数字
	 	if(str.matches("\\d*")){
	 		return true; 
	 	}
	 	else{
	 		return false;
	 	}
	 }
	
	
	protected boolean editAuthorityAction() {
		OperateDB opdb = new OperateDB();
		if(maxDaysText.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "请输入借书天数上限!", "输入错误", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(maxBookNumText.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "请输入借书数量上限!", "输入错误", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(isNumeric(maxDaysText.getText()) != true){
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "借书天数上限应为整数!", "输入错误", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(isNumeric(maxBookNumText.getText()) != true){
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "借书数量上限应为整数!", "输入错误", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		int maxDay = Integer.parseInt(maxDaysText.getText());
		int maxBookNum = Integer.parseInt(maxBookNumText.getText());
		return opdb.updateAuthority(maxDay,maxBookNum);
		
	}
}
