package client.library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;

import client.util.ClientMsgHelper;
import conn.common.Book;

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
import javax.swing.BoxLayout;
/**
 * 提供图书借阅信息的panel,用于查询某本书的借阅信息
 * @author 陈石开
 *
 */
public class BookBorrowInfoPanel extends JFrame {

	private JPanel contentPane;
	private Book book;
	private Vector<String> borrowList;
	private JList list;
	private DefaultListModel dlm;
	private JLabel bookNameLabel;
	private JLabel bookIDLabel;
	private JPanel panel;
	public BookBorrowInfoPanel(Book bk, JPanel parentPanel) {
		book = bk;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 918, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 900, 550);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("借阅信息");
		label.setBounds(427, 0, 93, 56);
		panel.add(label);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 23));
		
		JPanel panel_ = new JPanel();
		panel_.setBounds(10, 66, 583, 474);
		panel.add(panel_);
		panel_.setLayout(new BoxLayout(panel_, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_.add(scrollPane);
		list = new JList();
		scrollPane.setViewportView(list);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(603, 68, 287, 472);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("所有记录");
		btnNewButton_1.setBounds(91, 249, 104, 23);
		panel_2.add(btnNewButton_1);
		
		JButton searchNonreturned = new JButton("未还记录");
		searchNonreturned.setBounds(91, 345, 104, 23);
		panel_2.add(searchNonreturned);
		
		bookNameLabel = new JLabel(book.getBookName());
		bookNameLabel.setBounds(82, 73, 195, 15);
		panel_2.add(bookNameLabel);
		
		JLabel label_1 = new JLabel("书名:");
		label_1.setBounds(33, 73, 39, 15);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("书号:");
		label_2.setBounds(32, 161, 39, 15);
		panel_2.add(label_2);
		
		bookIDLabel = new JLabel(book.getBookId());
		bookIDLabel.setBounds(81, 161, 196, 15);
		panel_2.add(bookIDLabel);
		
		JButton btnNewButton = new JButton("返回");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentPanel.getComponent(0).setVisible(true);
				parentPanel.remove(getPanel());
			}
		});
		btnNewButton.setBounds(91, 439, 104, 23);
		panel_2.add(btnNewButton);
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

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	protected void updateNonreturnedBorrowInfo() {
		ClientMsgHelper cmh = new ClientMsgHelper();
		cmh.searchNonreturnedBorrowInfoInDB(book.getBookId());
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
		cmh.searchBorrowInfoInDB(book.getBookId());
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
