package client.library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;

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

public class BookBorrowInfo extends JFrame {

	private JPanel contentPane;
	private Book book;
	private Vector<String> borrowList;
	private JList list;
	private DefaultListModel dlm;
	private JLabel bookNameLabel;
	private JLabel bookIDLabel;
	/**
	 * Launch the application.
	 */
	
	//主函数用于测试
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Book bk = new Book();
					bk.setBookId("10");
					bk.setBookName("C++");
					BookBorrowInfo frame = new BookBorrowInfo(bk);
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
	public BookBorrowInfo(Book bk) {
		book = bk;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("借阅信息");//标题“虚拟校园图书馆”
		label.setBounds(323, 0, 93, 56);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 23));
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("书名:");
		label_1.setBounds(34, 55, 39, 15);
		contentPane.add(label_1);
		
		bookNameLabel = new JLabel(book.getBookName());
		bookNameLabel.setBounds(83, 55, 112, 15);
		contentPane.add(bookNameLabel);
		
		JLabel label_2 = new JLabel("书号:");
		label_2.setBounds(261, 55, 39, 15);
		contentPane.add(label_2);
		
		bookIDLabel = new JLabel(book.getBookId());
		bookIDLabel.setBounds(310, 55, 134, 15);
		contentPane.add(bookIDLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(21, 77, 740, 335);
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		list = new JList();
		scrollPane.setViewportView(list);
		
		JButton searchNonreturned = new JButton("未还记录");
		searchNonreturned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateNonreturnedBorrowInfo();
			}
		});
		searchNonreturned.setBounds(102, 422, 93, 23);
		contentPane.add(searchNonreturned);
		
		JButton btnNewButton_1 = new JButton("所有记录");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAllBorrowInfo();
			}
		});
		btnNewButton_1.setBounds(564, 422, 93, 23);
		contentPane.add(btnNewButton_1);
		
	}

	protected void updateNonreturnedBorrowInfo() {
		OperateDB opdb = new OperateDB();
		borrowList = opdb.searchNonreturnedBorrowInfoInDB(book.getBookId());
		dlm = new DefaultListModel();
		for (String info : borrowList) {
			dlm.addElement(info);
		}
		list.setModel(dlm);
		
	}

	private void updateAllBorrowInfo() {
		OperateDB opdb = new OperateDB();
		borrowList = opdb.searchBorrowInfoInDB(book.getBookId());
		dlm = new DefaultListModel();
		for (String info : borrowList) {
			dlm.addElement(info);
		}
		list.setModel(dlm);
	}
}
