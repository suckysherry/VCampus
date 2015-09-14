package client.library;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.event.ListSelectionListener;

import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;

import conn.common.User;

import javax.swing.event.ListSelectionEvent;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JRadioButton;

import conn.common.*;

@SuppressWarnings("serial")
public class LibraryMain extends JFrame {

	boolean isAdmin;
	private JPanel contentPane;
	private JTextField fastSearchTextField;
	private Book currentBook;
	private String[] bookNameList;
	private Vector<Book> bookList;
	private JScrollPane scrollPane;
	private JPanel panel_2;
	private JButton searchButton;
	private ImageIcon bookImageIcon;
	private File picFile;
	private JList list;
	private DefaultListModel dlm;
	private int currentIndex;
	private JButton borrowOrReturnBookButton;
	private ButtonGroup group;
	private JRadioButton bookNameButton;
	private JRadioButton bookIDButton;
	private JRadioButton keyWordsButton;
	private JButton adminButton;
	/**
	 * Launch the application.
	 */
	public static boolean isNumeric(String str){ //是否为纯数字
		 	if(str.matches("\\d*")){
		 		return true; 
		 	}
		 	else{
		 		return false;
		 	}
		 }

	
	//主函数用于测试
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					User us = new User("1");
//					us.setUName("reader1");
//					us.setLibraryAdmin(false);
//					LibraryMain frame = new LibraryMain(us);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public LibraryMain(User us){
		isAdmin = us.isLibraryAdmin();
		setPreferredSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 457);
		setVisible(true);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		((JPanel) getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon(getClass().getResource("/res/libbg.png"));
		JLabel background = new JLabel(img);
		getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		
//		JLabel label = new JLabel("图书馆");//标题“图书管理”
//		label.setBounds(357, 10, 69, 31);
//		label.setForeground(Color.BLACK);
//		label.setFont(new Font("微软雅黑", Font.PLAIN, 23));
//		contentPane.add(label);;
		
		searchButton = new JButton("");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchBookAction();	
			}
		});
		searchButton.setBorderPainted(false);
		searchButton.setIcon(new ImageIcon(getClass().getResource("/res/libbtnfind.png")));
		searchButton.setBounds(665, 51, 109, 23);
		contentPane.add(searchButton);
		
		/**
		 * 借书还书
		 */
		borrowOrReturnBookButton = new JButton("");
		borrowOrReturnBookButton.setBorderPainted(false);
		borrowOrReturnBookButton.setIcon(new ImageIcon(getClass().getResource("/res/libbtn1.png")));
		borrowOrReturnBookButton.setRolloverIcon(new ImageIcon(getClass().getResource("/res/libbtn1.png")));
		borrowOrReturnBookButton.setBounds(134, 384, 103, 23);
		contentPane.add(borrowOrReturnBookButton);
		
		fastSearchTextField = new JTextField();
		fastSearchTextField.setBounds(257, 51, 398, 21);
		contentPane.add(fastSearchTextField);
		fastSearchTextField.setColumns(10);
		
		panel_2 = new JPanel();
		panel_2.setBounds(35, 94, 739, 280);
		contentPane.add(panel_2);
		panel_2.setPreferredSize(new Dimension(40, 10));
		panel_2.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(40, 2));
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
	
		list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
			  listValueChangedAction();
			}
		});
		scrollPane.setViewportView(list);
		
		bookNameButton = new JRadioButton("书名");
		bookNameButton.setBounds(35, 50, 60, 23);
		contentPane.add(bookNameButton);
		
		bookIDButton = new JRadioButton("书号");
		bookIDButton.setBounds(108, 50, 60, 23);
		contentPane.add(bookIDButton);
		
		keyWordsButton = new JRadioButton("关键词");
		keyWordsButton.setSelected(true);
		keyWordsButton.setBounds(170, 50, 81, 23);
		contentPane.add(keyWordsButton);
		borrowOrReturnBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BorrowOrReturnBook(us);
			}
		});
		group =new ButtonGroup();
		group.add(bookNameButton);
		group.add(bookIDButton);
		group.add(keyWordsButton);
		
		/**
		 * 我的借阅
		 */
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new UserBorrowInfo(us);
			}
		});
		button.setBorderPainted(false);
		button.setIcon(new ImageIcon(getClass().getResource("/res/libbtn3.png")));
		button.setRolloverIcon(new ImageIcon(getClass().getResource("/res/libbtn4.png")));
		button.setBounds(35, 384, 93, 23);
		contentPane.add(button);
		
		/**
		 * 图书详情
		 */
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new BookDetails(currentBook);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBorderPainted(false);
		btnNewButton.setIcon(new ImageIcon(getClass().getResource("/res/libbtn5.png")));
		btnNewButton.setRolloverIcon(new ImageIcon(getClass().getResource("/res/libbtn6.png")));
		btnNewButton.setBounds(681, 384, 93, 23);
		contentPane.add(btnNewButton);
		
		/**
		 * 进入后台
		 */
		adminButton = new JButton("");
		adminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new LibraryAdmin(us);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		adminButton.setBorderPainted(false);
		adminButton.setIcon(new ImageIcon(getClass().getResource("/res/libbtn7.png")));
		adminButton.setRolloverIcon(new ImageIcon(getClass().getResource("/res/libbtn8.png")));
		adminButton.setBounds(240, 384, 93, 23);
		contentPane.add(adminButton);
		if(isAdmin == false){
			adminButton.setVisible(false);
		}
		
	}
	protected void listValueChangedAction() {
		
		if(list.getSelectedIndex() != -1){
			  currentIndex = list.getSelectedIndex();
			  currentBook = bookList.get(currentIndex);
		}
		else{
			  currentBook = null;
		}
	}
	protected void searchBookAction() {
		// TODO Auto-generated method stub
		int method = 0;
		if(bookIDButton.isSelected()) method = 1;
		if(bookNameButton.isSelected()) method = 2;
		if(keyWordsButton.isSelected()) method = 3;
		OperateDB opdb = new OperateDB();
		String searchText;
		searchText = fastSearchTextField.getText();
		if(!searchText.isEmpty()){
			bookList = opdb.searchBookInDB(searchText,method);
			dlm = new DefaultListModel();
			for (Book book : bookList) {
				String newBookNameAndID = "书名:  \t"+ book.getBookName()+ "\t  书号:  \t" + book.getBookId() + "\t  作者: \t" + book.getBookAuthor() + "\t 出版商: \t " + book.getBookPublisher() +  "\t 可借副本: \t" + book.getBookBorrowStatus();
				dlm.addElement(newBookNameAndID);
			}
			list.setModel(dlm);
		}
	}
}
