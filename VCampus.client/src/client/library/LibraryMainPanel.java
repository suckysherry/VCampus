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

import client.util.ClientMsgHelper;
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
import javax.swing.BoxLayout;
import java.awt.CardLayout;

/**
 * 图书馆主panel,查询图书,查询自己借阅信息,为自己借书还书,管理员可以进入后台.
 * @author 陈石开
 *
 */
public class LibraryMainPanel extends JFrame {

	boolean isAdmin;
	JPanel panel_3;
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
	private JPanel panel;
	private JPanel menuPanel;
	private JPanel panel_4;
	private JButton bookDetailsButton;
	/**
	 * Launch the application.
	 */
	public static boolean isNumeric(String str){ //鏄惁涓虹函鏁板瓧
		 	if(str.matches("\\d*")){
		 		return true; 
		 	}
		 	else{
		 		return false;
		 	}
		 }

	
	//涓诲嚱鏁扮敤浜庢祴璇�
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User us = new User("1");
					us.setUName("reader1");
					us.setLibraryAdmin(true);
					LibraryMainPanel frame = new LibraryMainPanel(us);
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
	public LibraryMainPanel(User us){
		isAdmin = true;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 920, 588);

		//setVisible(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setOpaque(false);
		contentPane.setBounds(0, 0, 900, 550);
		((JPanel) getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon(getClass().getResource("/res/libbg.png"));
		JLabel background = new JLabel(img);
		getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		
		/**
		 * 鍊熶功杩樹功
		 */
		group =new ButtonGroup();
		contentPane.setLayout(null);
		
		panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 900, 550);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		menuPanel = new JPanel();
		menuPanel.setBounds(723, 6, 152, 534);
		panel_3.add(menuPanel);
		menuPanel.setLayout(new CardLayout(0, 0));
		
		panel_4 = new JPanel();
		menuPanel.add(panel_4, "name_4433584575300");
		panel_4.setLayout(null);
		adminButton = new JButton("");
		adminButton.setBounds(13, 398, 126, 31);
		panel_4.add(adminButton);
		adminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					LibraryAdminPanel adminPanel = new LibraryAdminPanel(us,contentPane);
					contentPane.getComponent(0).setVisible(false);
					//panel_3.setVisible(false);
					contentPane.add(adminPanel.getContentPane());

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		adminButton.setBorderPainted(false);
		adminButton.setIcon(new ImageIcon(getClass().getResource("/res/libbtn7.png")));
		adminButton.setRolloverIcon(new ImageIcon(getClass().getResource("/res/libbtn8.png")));
		JButton button = new JButton("");
		button.setBounds(13, 236, 126, 31);
		panel_4.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserBorrowInfoPanel a = new UserBorrowInfoPanel(us,contentPane);
				contentPane.getComponent(0).setVisible(false);
				contentPane.add(a.getContentPane());
			}
		});
		button.setBorderPainted(false);
		button.setIcon(new ImageIcon(getClass().getResource("/res/libbtn3.png")));
		button.setRolloverIcon(new ImageIcon(getClass().getResource("/res/libbtn4.png")));
		borrowOrReturnBookButton = new JButton("");
		borrowOrReturnBookButton.setBounds(13, 317, 126, 31);
		panel_4.add(borrowOrReturnBookButton);
		borrowOrReturnBookButton.setBorderPainted(false);
		borrowOrReturnBookButton.setIcon(new ImageIcon(getClass().getResource("/res/libbtn1.png")));
		borrowOrReturnBookButton.setRolloverIcon(new ImageIcon(getClass().getResource("/res/libbtn1.png")));
		
		searchButton = new JButton("");
		searchButton.setBounds(13, 75, 126, 30);
		panel_4.add(searchButton);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchBookAction();	
			}
		});
		searchButton.setBorderPainted(false);
		searchButton.setIcon(new ImageIcon(getClass().getResource("/res/libbtnfind.png")));
		bookDetailsButton = new JButton("");
		bookDetailsButton.setBounds(13, 155, 126, 31);
		panel_4.add(bookDetailsButton);
		bookDetailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					BookDetailsPanel bookDetailsPanel = new BookDetailsPanel(currentBook,contentPane);
					contentPane.getComponent(0).setVisible(false);
					contentPane.add(bookDetailsPanel.getPanel());
			}
		});
		bookDetailsButton.setBorderPainted(false);
		bookDetailsButton.setIcon(new ImageIcon(getClass().getResource("/res/libbtn5.png")));
		bookDetailsButton.setRolloverIcon(new ImageIcon(getClass().getResource("/res/libbtn6.png")));
		borrowOrReturnBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorrowOrReturnBookPanel a = new BorrowOrReturnBookPanel(us,contentPane);
				contentPane.getComponent(0).setVisible(false);
				contentPane.add(a.getContentPane());
				
			}
		});
		bookDetailsButton.setEnabled(false);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 707, 534);
		panel_3.add(panel);
		panel.setLayout(null);
		
		fastSearchTextField = new JTextField();
		fastSearchTextField.setBounds(215, 81, 482, 21);
		panel.add(fastSearchTextField);
		fastSearchTextField.setColumns(10);
		
		bookNameButton = new JRadioButton("书名");
		bookNameButton.setBounds(68, 79, 60, 23);
		panel.add(bookNameButton);
		group.add(bookNameButton);
		
		bookIDButton = new JRadioButton("书号");
		bookIDButton.setBounds(6, 79, 60, 23);
		panel.add(bookIDButton);
		group.add(bookIDButton);
		
		keyWordsButton = new JRadioButton("关键词");
		keyWordsButton.setBounds(130, 79, 85, 23);
		panel.add(keyWordsButton);
		keyWordsButton.setSelected(true);
		group.add(keyWordsButton);
		
		panel_2 = new JPanel();
		panel_2.setBounds(6, 112, 691, 394);
		panel.add(panel_2);
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
		
		JLabel label = new JLabel("图书馆");
		label.setBounds(299, 10, 72, 59);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		panel.add(label);
		if(isAdmin == false){
			adminButton.setVisible(false);
		}
		
	}
	
	public JPanel getPanel() {
		return panel;
	}


	public void setPanel(JPanel panel) {
		this.panel = panel;
	}


	protected void listValueChangedAction() {
		
		if(list.getSelectedIndex() != -1){
			  currentIndex = list.getSelectedIndex();
			  currentBook = bookList.get(currentIndex);
			  bookDetailsButton.setEnabled(true);
		}
		else{
			  currentBook = null;
			  bookDetailsButton.setEnabled(false);
		}
	}
	protected void searchBookAction() {
		// TODO Auto-generated method stub
		int method = 0;
		if(bookIDButton.isSelected()) method = 1;
		if(bookNameButton.isSelected()) method = 2;
		if(keyWordsButton.isSelected()) method = 3;
		String searchText;
		searchText = fastSearchTextField.getText();
		if(!searchText.isEmpty()){
			ClientMsgHelper cmh = new ClientMsgHelper();
			cmh.searchBookInDB(searchText,method);
			cmh.sendMsg();
			cmh.recieveMsg();
			bookList = (Vector<Book>) cmh.getDataInMsg();
			dlm = new DefaultListModel();
			for (Book book : bookList) {
				String newBookNameAndID = "书名:  \t"+ book.getBookName()+ "\t  书号:  \t" + book.getBookId() + "\t  作者: \t" + book.getBookAuthor() + "\t 出版商: \t " + book.getBookPublisher() +  "\t 可借副本: \t" + book.getBookBorrowStatus();
				dlm.addElement(newBookNameAndID);
			}
			list.setModel(dlm);
		}
	}
}
