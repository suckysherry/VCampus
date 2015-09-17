package client.library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;

import client.util.ClientMsgHelper;
import conn.common.User;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.jws.soap.SOAPBinding.Use;
import javax.swing.BoxLayout;
import java.awt.Rectangle;
/**
 * 用于查询用户的借阅信息
 * @author 陈石开
 *
 */
public class UserBorrowInfoPanel extends JFrame {

	private JPanel contentPane;
	private Vector<String> borrowList;
	private JList list;
	private DefaultListModel dlm;
	private JLabel userNameLabel;
	private JLabel userIDLabel;
	private User user;
	/**
	 * Launch the application.
	 */

	public UserBorrowInfoPanel(User us, JPanel parentPanel) {
		user = us;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 954, 609);
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(0, 0, 900, 550));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 900, 550);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 70, 562, 451);
		panel_2.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		list = new JList();
		scrollPane.setViewportView(list);
		
		JLabel label = new JLabel("借阅信息");
		label.setBounds(344, 29, 93, 31);
		panel_2.add(label);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 23));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(582, 70, 308, 451);
		panel_2.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("所有记录");
		btnNewButton_1.setBounds(106, 214, 96, 23);
		panel_1.add(btnNewButton_1);
		
		userNameLabel = new JLabel(user.getUName());
		userNameLabel.setBounds(134, 62, 116, 15);
		panel_1.add(userNameLabel);
		
		JButton searchNonreturned = new JButton("未还记录");
		searchNonreturned.setBounds(106, 298, 96, 23);
		panel_1.add(searchNonreturned);
		
		userIDLabel = new JLabel(user.getUID());
		userIDLabel.setBounds(134, 138, 116, 15);
		panel_1.add(userIDLabel);
		
		JLabel label_1 = new JLabel("名字:");
		label_1.setBounds(38, 62, 39, 15);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("ID:");
		label_2.setBounds(38, 138, 39, 15);
		panel_1.add(label_2);
		
		JButton btnNewButton = new JButton("返回");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentPanel.getComponent(0).setVisible(true);
				parentPanel.remove(contentPane);
			}
		});
		btnNewButton.setBounds(106, 382, 96, 23);
		panel_1.add(btnNewButton);
		searchNonreturned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateNonreturnedBorrowInfo();
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAllBorrowInfo();
			}
		});
		
	}

	protected void updateNonreturnedBorrowInfo() {
		ClientMsgHelper cmh = new ClientMsgHelper();
		cmh.searchUserNonreturnedBorrowInfoInDB(user.getUID());
		cmh.sendMsg();
		cmh.recieveMsg();
		borrowList = (Vector<String>) cmh.getDataInMsg();
		dlm = new DefaultListModel();
		for (String info : borrowList) {
			dlm.addElement(info);
		}
		list.setModel(dlm);
		
	}

	private void updateAllBorrowInfo() {
		ClientMsgHelper cmh = new ClientMsgHelper();
		cmh.searchUserBorrowInfoInDB(user.getUID());
		cmh.sendMsg();
		cmh.recieveMsg();
		borrowList = (Vector<String>) cmh.getDataInMsg();
		dlm = new DefaultListModel();
		for (String info : borrowList) {
			dlm.addElement(info);
		}
		list.setModel(dlm);
	}
}
