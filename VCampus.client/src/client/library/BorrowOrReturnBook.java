package client.library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conn.common.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BorrowOrReturnBook extends JFrame {

	private JPanel contentPane;
	private JTextField userIDText;
	private JTextField bookIDText;

	private boolean isAdmin;
	/**
	 * Launch the application.
	 */
	
	//主函数用于测试
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User us;
					us = new User("61013110");
					us.setLibraryAdmin(false);
					BorrowOrReturnBook frame = new BorrowOrReturnBook(us);
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
	public BorrowOrReturnBook(User us) {
		isAdmin = us.isLibraryAdmin();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 374, 273);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("借还书");//标题“虚拟校园图书馆”
		label.setBounds(98, 0, 161, 53);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 23));
		contentPane.add(label);
		
		JButton button = new JButton("借书");//“我的图书”按钮，用于查询当前用户借阅信息
		button.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				borrowBookAction();
			}
		});//事件监听器，新建一个MyBook对象
		button.setBounds(89, 166, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("还书");//“查询”按钮
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnBook();
			}
		});//事件监听器，查询显示信息，目前为空
		button_1.setBounds(192, 166, 93, 23);
		contentPane.add(button_1);
		
		userIDText = new JTextField();//输入框，用于查询书名
		userIDText.setBounds(124, 66, 134, 23);
		userIDText.setColumns(10);
		contentPane.add(userIDText);
		
		bookIDText = new JTextField();
		bookIDText.setBounds(124, 114, 134, 21);
		contentPane.add(bookIDText);
		bookIDText.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("用户ID");
		lblNewLabel.setBounds(57, 70, 54, 15);
		contentPane.add(lblNewLabel);
		if(isAdmin == false){
			userIDText.setText(us.getUID());
			userIDText.setEditable(false);
		}
		
		JLabel lblid = new JLabel("图书ID");
		lblid.setBounds(57, 117, 54, 15);
		contentPane.add(lblid);
	}

	protected void returnBook() {
		OperateDB opdp = new OperateDB();
		int rst;
		rst = opdp.returnBook(userIDText.getText(),bookIDText.getText());
		switch (rst) {
		case 0:
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "还书失败,未知错误!", "还书", JOptionPane.ERROR_MESSAGE);
			break;
		case 1:
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "还书成功!", "还书", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 3:
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "还书失败,图书ID错误!", "还书", JOptionPane.ERROR_MESSAGE);
			break;
		case 4:
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "还书失败,用户ID错误!", "还书", JOptionPane.ERROR_MESSAGE);
			break;
		case 5:
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "还书失败,无未还记录!", "还书", JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
		
	}

	protected void borrowBookAction() {
		OperateDB opdp = new OperateDB();
		int rst;
		rst = opdp.borrowBook(userIDText.getText(),bookIDText.getText());
		switch (rst) {
		case 0:
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "借书失败,未知错误!", "借书", JOptionPane.ERROR_MESSAGE);
			break;
		case 1:
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "借书成功!", "借书", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 2:
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "借书失败,超过借书上限!", "借书", JOptionPane.ERROR_MESSAGE);
			break;
		case 3:
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "借书失败,图书ID错误!", "借书", JOptionPane.ERROR_MESSAGE);
			break;
		case 4:
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "借书失败,用户ID错误!", "借书", JOptionPane.ERROR_MESSAGE);
			break;
		case 5:
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "借书失败,重复借阅!", "借书", JOptionPane.ERROR_MESSAGE);
			break;
		case 6:
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "借书失败,超过可借上限!", "借书", JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
		
	}

}
