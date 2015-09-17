package client.library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.xml.internal.fastinfoset.util.CharArrayIntMap;

import client.util.ClientMsgHelper;
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
import java.awt.Rectangle;
/**
 * 提供借书还书的panel,用于借书还书.
 * @author 陈石开
 *
 */
public class BorrowOrReturnBookPanel extends JFrame {

	private JPanel contentPane;
	private JTextField userIDText;
	private JTextField bookIDText;

	private boolean isAdmin;
	/**
	 * Launch the application.
	 */
	
	//主函数用于测试
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					User us;
//					us = new User("61013110");
//					us.setLibraryAdmin(false);
//					BorrowOrReturnBookPanel frame = new BorrowOrReturnBookPanel(us);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @param parentPanel 
	 */
	public BorrowOrReturnBookPanel(User us, JPanel parentPanel) {
		isAdmin = us.isLibraryAdmin();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 932, 629);
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(0, 0, 900, 550));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		if(isAdmin == false){
			userIDText.setText(us.getUID());
			userIDText.setEditable(false);
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 900, 550);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("借还书");
		label.setBounds(364, 10, 110, 53);
		panel.add(label);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 23));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 73, 685, 467);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("用户ID");
		lblNewLabel.setBounds(133, 137, 54, 15);
		panel_1.add(lblNewLabel);
		
		userIDText = new JTextField();
		userIDText.setBounds(197, 133, 311, 23);
		panel_1.add(userIDText);
		userIDText.setColumns(10);
		
		bookIDText = new JTextField();
		bookIDText.setBounds(197, 277, 311, 21);
		panel_1.add(bookIDText);
		bookIDText.setColumns(10);
		
		JLabel lblid = new JLabel("图书ID");
		lblid.setBounds(133, 280, 54, 15);
		panel_1.add(lblid);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(705, 73, 185, 467);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JButton button = new JButton("借书");
		button.setBounds(44, 95, 97, 23);
		panel_2.add(button);
		button.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		JButton button_1 = new JButton("还书");
		button_1.setBounds(44, 213, 97, 23);
		panel_2.add(button_1);
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		JButton button_2 = new JButton("返回");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentPanel.getComponent(0).setVisible(true);
				parentPanel.remove(contentPane);
			}
		});
		button_2.setBounds(48, 331, 93, 23);
		panel_2.add(button_2);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnBook();
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				borrowBookAction();
			}
		});
	}

	protected void returnBook() {
		ClientMsgHelper cmh = new ClientMsgHelper();
		int rst;
		cmh.returnBook(userIDText.getText(),bookIDText.getText());
		cmh.sendMsg();
		cmh.recieveMsg();
		rst = (int) cmh.getDataInMsg();
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
		ClientMsgHelper cmh = new ClientMsgHelper();
		int rst;
		cmh.borrowBook(userIDText.getText(),bookIDText.getText());
		cmh.sendMsg();
		cmh.recieveMsg();
		rst = (int) cmh.getDataInMsg();
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
