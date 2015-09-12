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
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;
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

@SuppressWarnings("serial")
public class LibraryAdmin extends JFrame {

	boolean isAdmin;
	private JPanel contentPane;
	private JTextField BookBorrowStatus;
	private JTextField BookName;
	private JTextField BookAuthor;
	private JTextField BookPublisher;
	private JTextField BookPublishingTime;
	private JTextField BookID;
	private JTextField fastSearchTextField;
	private JEditorPane editorPane;
	private Book currentBook;
	private JButton editBookButton;
	private JButton borrowInfoButton;
	private JButton delBookButton;
	private JButton addBookButton;
	private JButton authorityEditButton;
	private JButton selectPicButton;
	private JButton cancelButton;
	private String[] bookNameList;
	private Vector<Book> bookList;
	private JScrollPane scrollPane;
	private JPanel panel_2;
	private JButton searchButton;
	private JLabel picLabel;
	private ImageIcon bookImageIcon;
	private File picFile;
	private JList list;
	private DefaultListModel dlm;
	private int currentIndex;
	private JButton borrowOrReturnBookButton;

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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User us = new User("1");
					
					LibraryAdmin frame = new LibraryAdmin(us);
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
	public LibraryAdmin(User us)  throws SQLException {
		isAdmin = us.isLibraryAdmin();
		setPreferredSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 806, 607);
		setVisible(true);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.white);
		
		JLabel label = new JLabel("图书馆");//标题“图书管理”
		label.setBounds(369, 10, 69, 31);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 23));
		contentPane.add(label);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(150, 523, 624, 38);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.setBackground(Color.white);
		
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.2);
		splitPane.setBounds(10, 51, 764, 460);
		contentPane.add(splitPane);
		JPanel BookInfoPanel = new JPanel();
		splitPane.setRightComponent(BookInfoPanel);
		BookInfoPanel.setLayout(null);
		splitPane.setBackground(Color.white);

		
		JPanel BookDetails = new JPanel();
		BookDetails.setBounds(5, 340, 573, 108);
		BookInfoPanel.add(BookDetails);
		BookDetails.setLayout(null);
		BookDetails.setBackground(Color.white);
		
		
		
		JPanel BookPic = new JPanel();
		BookPic.setBounds(5, 11, 196, 298);
		BookInfoPanel.add(BookPic);
		BookPic.setLayout(null);
		BookInfoPanel.setBackground(Color.white);
		BookPic.setBackground(new Color(245, 245, 245));
		
		JPanel Info = new JPanel();
		Info.setBounds(211, 11, 367, 298);
		BookInfoPanel.add(Info);
		Info.setLayout(null);
		Info.setBackground(new Color(245, 245, 245));
		
		JLabel label_7 = new JLabel("详细信息：");
		label_7.setAlignmentX(Component.CENTER_ALIGNMENT);
		label_7.setBounds(5, 319, 60, 17);
		label_7.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		BookInfoPanel.add(label_7);
		
		
		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		editorPane.setBounds(0, 0, 573, 108);
		editorPane.setText("详细信息");
		editorPane.setBackground(new Color(245, 245, 245));
		BookDetails.add(editorPane);
		BookDetails.setBackground(Color.white);

		
		selectPicButton = new JButton("选择题图片");
		selectPicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectPicAction();
			}
		});
		selectPicButton.setForeground(Color.decode("#FC8665"));
		selectPicButton.setBorder(BorderFactory.createLineBorder(Color.decode("#FC8665")));
		selectPicButton.setVisible(false);
		selectPicButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		selectPicButton.setBounds(50, 265, 94, 23);
		BookPic.add(selectPicButton);
		
		delBookButton = new JButton("\u5220\u9664\u56fe\u4e66");//“删除图书”按钮
		delBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delBookAction();
				
			}
		});
		delBookButton.setBorderPainted(false);
		delBookButton.setOpaque(true);
		delBookButton.setForeground(Color.white);
		delBookButton.setBackground(new Color(153, 204, 255));
		
		borrowOrReturnBookButton = new JButton("借书还书");
		borrowOrReturnBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BorrowOrReturnBook(us);
			}
		});
		borrowOrReturnBookButton.setBorderPainted(false);
		borrowOrReturnBookButton.setOpaque(true);
		borrowOrReturnBookButton.setForeground(Color.white);
		borrowOrReturnBookButton.setBackground(Color.decode("#FCC926"));
		panel.add(borrowOrReturnBookButton);
		panel.add(delBookButton);
		delBookButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		addBookButton = new JButton("\u6dfb\u52a0\u56fe\u4e66");//“添加图书”按钮
		addBookButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				addBookAction();
			}
		});
		addBookButton.setBorderPainted(false);
		addBookButton.setOpaque(true);
		addBookButton.setForeground(Color.white);
		addBookButton.setBackground(new Color(102, 0, 102));
		panel.add(addBookButton);
		addBookButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		authorityEditButton = new JButton("\u6743\u9650\u8bbe\u5b9a");//“权限设定”按钮
		authorityEditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AuthorityEdit();
			}
		});
		authorityEditButton.setBorderPainted(false);
		authorityEditButton.setOpaque(true);
		authorityEditButton.setForeground(Color.white);
		authorityEditButton.setBackground(new Color(255, 99, 71));
		panel.add(authorityEditButton);
		authorityEditButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		borrowInfoButton = new JButton("借阅详情");
		borrowInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentBook != null) {
					BookBorrowInfo a = new BookBorrowInfo(currentBook);
				} 
				
			}
		});
		borrowInfoButton.setBorderPainted(false);
		borrowInfoButton.setOpaque(true);
		borrowInfoButton.setForeground(Color.white);
		borrowInfoButton.setBackground(new Color(153, 204, 51));
		borrowInfoButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.add(borrowInfoButton);
		
		editBookButton = new JButton("\u7f16\u8f91\u56fe\u4e66");//“编辑图书”按钮
		editBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editBookAction();
				
			}
		});
		editBookButton.setBorderPainted(false);
		editBookButton.setOpaque(true);
		editBookButton.setForeground(Color.white);
		editBookButton.setBackground(new Color(255, 204, 204));
		panel.add(editBookButton);
		editBookButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		
		
		
		
		JPanel ListPanel = new JPanel();
		splitPane.setLeftComponent(ListPanel);
		ListPanel.setBackground(Color.white);
		
		ListPanel.setLayout(new BorderLayout(0, 0));
		
		panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(40, 10));
		ListPanel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_2.setBackground(Color.white);
		
		fastSearchTextField = new JTextField();
		panel_2.add(fastSearchTextField, BorderLayout.NORTH);
		fastSearchTextField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(40, 2));
		scrollPane.setBackground(Color.white);
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
	
		list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
			  listValueChangedAction();
			}
		});
		scrollPane.setViewportView(list);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 176, 245);
		BookPic.add(panel_1);
		panel_1.setBackground(Color.white);
		
		picLabel = new JLabel();
		picLabel.setBackground(new Color(255, 255, 255));
		picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		picLabel.setMaximumSize(new Dimension(166, 235));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(picLabel, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(picLabel, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel label_5 = new JLabel("书名：");
		label_5.setBounds(45, 7, 36, 17);
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Info.add(label_5);
		
		BookName = new JTextField();
		BookName.setEditable(false);
		BookName.setBounds(126, 5, 202, 21);
		BookName.setColumns(25);
		Info.add(BookName);
		
		JLabel lblNewLabel = new JLabel("可借副本：");
		lblNewLabel.setBounds(33, 61, 60, 17);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Info.add(lblNewLabel);
		
		BookBorrowStatus = new JTextField();
		BookBorrowStatus.setEditable(false);
		BookBorrowStatus.setBounds(126, 59, 202, 21);
		BookBorrowStatus.setColumns(25);
		Info.add(BookBorrowStatus);
		
		JLabel label_1 = new JLabel("责任者：");
		label_1.setBounds(39, 115, 48, 17);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Info.add(label_1);
		
		BookAuthor = new JTextField();
		BookAuthor.setEditable(false);
		BookAuthor.setBounds(126, 113, 202, 21);
		BookAuthor.setColumns(25);
		Info.add(BookAuthor);
		
		JLabel label_2 = new JLabel("出版、发行者：");
		label_2.setBounds(21, 169, 84, 17);
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Info.add(label_2);
		
		BookPublisher = new JTextField();
		BookPublisher.setEditable(false);
		BookPublisher.setBounds(126, 167, 202, 21);
		BookPublisher.setColumns(25);
		Info.add(BookPublisher);
		
		JLabel label_3 = new JLabel("出版发行时间：");
		label_3.setBounds(21, 223, 84, 17);
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Info.add(label_3);
		
		BookPublishingTime = new JTextField();
		BookPublishingTime.setEditable(false);
		BookPublishingTime.setBounds(126, 221, 202, 21);
		BookPublishingTime.setColumns(25);
		Info.add(BookPublishingTime);
		
		JLabel label_4 = new JLabel("书号：");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(27, 277, 72, 17);
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Info.add(label_4);
		
		BookID = new JTextField();
		BookID.setEditable(false);
		BookID.setBounds(126, 275, 202, 21);
		BookID.setColumns(25);
		Info.add(BookID);
		//初始化按钮状态
		editBookButton.setEnabled(false);;
		borrowInfoButton.setEnabled(false);
		delBookButton.setEnabled(false);
		
		cancelButton = new JButton("取    消");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelAction();
			}
		});
		cancelButton.setVisible(false);
		panel.add(cancelButton);
		
		searchButton = new JButton("搜索");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchBookAction();	
			}
		});
		searchButton.setBounds(50, 29, 93, 23);
		contentPane.add(searchButton);
		adminUseOnly();
		
		
	}

	private void adminUseOnly() {
		if(isAdmin == false){
			editBookButton.setVisible(false);
			borrowInfoButton.setVisible(false);
			delBookButton.setVisible(false);
			addBookButton.setVisible(false);
			authorityEditButton.setVisible(false);
			selectPicButton.setVisible(false);
		}
		
	}


	private boolean checkInput() {
		if(BookName.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "请输入书名!", "输入错误", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(BookBorrowStatus.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "请输入可借副本!", "输入错误", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(isNumeric(BookBorrowStatus.getText()) != true){
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "可借副本应为整数!", "输入错误", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(BookAuthor.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "请输入作者!", "输入错误", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(BookPublisher.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "请输入发行者!", "输入错误", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(BookPublishingTime.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "请输入发行时间!", "输入错误", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(BookID.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "请输入书号!", "输入错误", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(editorPane.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "请输入详细信息!", "输入错误", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if((picFile == null)){
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "请选择封面图!", "输入错误", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}	
	
  	protected void selectPicAction() {
		JFileChooser picChooser = new JFileChooser();
		picChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if(picChooser.showOpenDialog(contentPane.getParent()) == JFileChooser.APPROVE_OPTION){
			picFile = picChooser.getSelectedFile();
			String filePath = picFile.getAbsolutePath();
			bookImageIcon = new ImageIcon(filePath);
			bookImageIcon.setImage(bookImageIcon.getImage().getScaledInstance(166, 235, Image.SCALE_SMOOTH));
			picLabel.setIcon(bookImageIcon);
		}
	}


	protected void delBookAction() {
		OperateDB opdb;
		int selection = JOptionPane.showConfirmDialog(contentPane.getParent(), "真的要删除"+currentBook.getBookName()+"么?","删除图书", JOptionPane.CANCEL_OPTION);
		if(selection == 0){
			opdb = new OperateDB();
			opdb.delBookInDB(currentBook);
			bookList.removeElement(currentBook);
			currentBook = null;
			updateCurrentInfo();
			updateButtons();
			dlm.remove(currentIndex);
		}
		
	}


	protected void editBookAction() {
		if(editBookButton.getText() == "\u7f16\u8f91\u56fe\u4e66"){	
			
			borrowInfoButton.setVisible(false);
			authorityEditButton.setVisible(false);
			editBookButton.setText("\u4fdd\u5b58\u7f16\u8f91");//按钮设为保存编辑
			delBookButton.setVisible(false);
			addBookButton.setVisible(false);
			cancelButton.setVisible(true);
			BookBorrowStatus.setText(currentBook.getAvailableBookNum()+"");
			setEditableTrue();			
			
		}
		else{//下面将保存到数据库
			if(checkInput() == false) return;
			Book oldBook = currentBook.clone();
			String oldID = oldBook.getBookId();
			updateBook(currentBook);
			currentBook.updateBookInDB(oldID);
			setEditableFalse();
			updateButtons();
			}
		
	}


	protected void listValueChangedAction() {
		
		if(list.getSelectedIndex() != -1){
			  currentIndex = list.getSelectedIndex();
			  currentBook = bookList.get(currentIndex);
			  editBookButton.setEnabled(true);;
			  borrowInfoButton.setEnabled(true);
			  delBookButton.setEnabled(true);
		}
		else{
			  currentBook = bookList.get(currentIndex);
			  updateCurrentInfo();
			  editBookButton.setEnabled(false);;
			  borrowInfoButton.setEnabled(false);
			  delBookButton.setEnabled(false);
		}
		updateCurrentInfo();
	}

	
	protected void cancelAction() {
		updateButtons();
		setEditableFalse();
		updateCurrentInfo();
		
	}


	private void updateButtons() {
		// TODO Auto-generated method stub
		editBookButton.setVisible(true);
		borrowInfoButton.setVisible(true);
		delBookButton.setVisible(true);
		addBookButton.setVisible(true);
		authorityEditButton.setVisible(true);
		selectPicButton.setVisible(false);
		cancelButton.setVisible(false);
		addBookButton.setText("\u6dfb\u52a0\u56fe\u4e66");//添加图书
		editBookButton.setText("\u7f16\u8f91\u56fe\u4e66");//按钮设为编辑图书
		if(currentBook == null){
			editBookButton.setEnabled(false);
			borrowInfoButton.setEnabled(false);
			delBookButton.setEnabled(false);
		}
	}


	protected void searchBookAction() {
		// TODO Auto-generated method stub
		OperateDB opdb = new OperateDB();
		String searchText;
		searchText = fastSearchTextField.getText();
		if(!searchText.isEmpty()){
			bookList = opdb.searchBookInDB(searchText);
			dlm = new DefaultListModel();
			for (Book book : bookList) {
				String newBookNameAndID = book.getBookName()+ " - " + book.getBookId();
				dlm.addElement(newBookNameAndID);
			}
			list.setModel(dlm);
		}
	}


	protected void addBookAction() {
		if(addBookButton.getText() == "\u6dfb\u52a0\u56fe\u4e66"){////添加图书
			addBookButton.setText("保存图书");
			cleanCurrentInfo();
			setEditableTrue();
			editBookButton.setVisible(false);
			delBookButton.setVisible(false);
			borrowInfoButton.setVisible(false);
			authorityEditButton.setVisible(false);
			cancelButton.setVisible(true);
		}
		else{
			if(addBookButton.getText() == "保存图书"){
				if(checkInput() == false) return;
				Book newBook = new Book();
				updateBook(newBook);
				setEditableFalse();
				OperateDB opdb = new OperateDB();
				opdb.addBookToDB(newBook);
				updateButtons();
			}
		}
		
	}

	
	protected void setEditableTrue() {
		BookBorrowStatus.setEditable(true);
		BookName.setEditable(true);
		BookAuthor.setEditable(true);
		BookPublisher.setEditable(true);
		BookPublishingTime.setEditable(true);
		BookID.setEditable(true);
		editorPane.setEditable(true);
		selectPicButton.setVisible(true);
		scrollPane.setEnabled(true);
		panel_2.setVisible(false);
		searchButton.setVisible(false);
		borrowOrReturnBookButton.setVisible(false);
		
		
	}

	
	protected void updateCurrentInfo() {
		if(currentBook != null){
			  BookName.setText(currentBook.getBookName());
			  BookBorrowStatus.setText(currentBook.getBookBorrowStatus());
			  BookAuthor.setText(currentBook.getBookAuthor());
			  BookPublisher.setText(currentBook.getBookPublisher());
			  BookPublishingTime.setText(currentBook.getBookPublishingDate());
			  BookID.setText(currentBook.getBookId());
			  editorPane.setText(currentBook.getBookDetails());
			  if(currentBook.getBookPic() != null){
				  String filePath = currentBook.getBookPic().getAbsolutePath();
				  bookImageIcon = new ImageIcon(filePath);
				  bookImageIcon.setImage(bookImageIcon.getImage().getScaledInstance(166, 235, Image.SCALE_SMOOTH));
				  picLabel.setIcon(bookImageIcon);
			  }
			  picFile = currentBook.getBookPic();
		}
		else{
			  BookName.setText("");
			  BookBorrowStatus.setText("");
			  BookAuthor.setText("");
			  BookPublisher.setText("");
			  BookPublishingTime.setText("");
			  BookID.setText("");
			  editorPane.setText("");
			  picLabel.setIcon(null);
		}
	}
	
	
protected void cleanCurrentInfo() {
		  BookName.setText("");
		  BookBorrowStatus.setText("");
		  BookAuthor.setText("");
		  BookPublisher.setText("");
		  BookPublishingTime.setText("");
		  BookID.setText("");
		  editorPane.setText("");
		  picLabel.setIcon(null);
		  picFile = null;
	}


	protected void setEditableFalse() {
		BookBorrowStatus.setEditable(false);
		BookName.setEditable(false);
		BookAuthor.setEditable(false);
		BookPublisher.setEditable(false);
		BookPublishingTime.setEditable(false);
		BookID.setEditable(false);
		editorPane.setEditable(false);
		selectPicButton.setVisible(false);
		panel_2.setVisible(true);
		searchButton.setVisible(true);
		borrowOrReturnBookButton.setVisible(true);
	}


	protected void updateBook(Book book) {
		if(currentBook != null){
			int oldAvailiableBookNum = book.getAvailableBookNum();
			int newAvailiableBookNum = Integer.parseInt(BookBorrowStatus.getText());
			int diff = newAvailiableBookNum - oldAvailiableBookNum;
			book.setBookNum(diff + currentBook.getBookNum());
		}
		else{
			book.setBookNum(Integer.parseInt(BookBorrowStatus.getText()));
		}
		book.setBookName(BookName.getText());
		book.setBookAuthor(BookAuthor.getText());
		book.setBookPublisher(BookPublisher.getText());
		book.setBookPublishingDate(BookPublishingTime.getText());
		book.setBookId(BookID.getText());
		book.setBookDetails(editorPane.getText());
		book.setBookPic(picFile);
		
	}
}
