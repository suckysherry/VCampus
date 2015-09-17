package client.library;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.event.ListSelectionListener;
import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;

import client.util.ClientMsgHelper;
import conn.common.Book;
import conn.common.User;

import javax.swing.event.ListSelectionEvent;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import java.awt.Rectangle;
import javax.swing.BoxLayout;

/**
 * 后台panel,用于编辑添加删除图书,权限管理,图书借阅详情查询,添加借书还书记录.
 * @author 陈石开
 *
 */
public class LibraryAdminPanel extends JFrame {

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
	private JPanel panel_3;
	private JButton button;
	private JPanel panel;


	public static boolean isNumeric(String str){ //是否为纯数字
		 	if(str.matches("\\d*")){
		 		return true; 
		 	}
		 	else{
		 		return false;
		 	}
		 }

	
	public LibraryAdminPanel(User us, JPanel parentPane)  throws SQLException {
		isAdmin = us.isLibraryAdmin();
		setPreferredSize(new Dimension(900, 550));
		setBounds(100, 100, 1107, 704);
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(0, 0, 900, 550));
		contentPane.setLayout(null);
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		setPreferredSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 900, 550);
		contentPane.add(panel);
		panel.setLayout(null);
		panel_3 = new JPanel();
		panel_3.setBounds(10, 43, 714, 497);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(0, 32, 713, 465);
		panel_3.add(splitPane);
		splitPane.setResizeWeight(0.2);
		JPanel BookInfoPanel = new JPanel();
		splitPane.setRightComponent(BookInfoPanel);
		BookInfoPanel.setLayout(null);
		splitPane.setBackground(Color.white);
		
				
				JPanel BookDetails = new JPanel();
				BookDetails.setBounds(5, 333, 518, 125);
				BookInfoPanel.add(BookDetails);
				BookDetails.setBackground(Color.white);
				
				
				
				JPanel BookPic = new JPanel();
				BookPic.setBounds(5, 10, 196, 299);
				BookInfoPanel.add(BookPic);
				BookPic.setLayout(null);
				BookInfoPanel.setBackground(Color.white);
				BookPic.setBackground(new Color(245, 245, 245));
				
				JPanel Info = new JPanel();
				Info.setBounds(211, 10, 312, 299);
				BookInfoPanel.add(Info);
				Info.setLayout(null);
				Info.setBackground(new Color(245, 245, 245));
				
				JLabel label_7 = new JLabel("详细信息：");
				label_7.setAlignmentX(Component.CENTER_ALIGNMENT);
				label_7.setBounds(5, 313, 60, 17);
				label_7.setFont(new Font("微软雅黑", Font.PLAIN, 12));
				BookInfoPanel.add(label_7);
				BookDetails.setLayout(new BoxLayout(BookDetails, BoxLayout.X_AXIS));
				
				
				editorPane = new JEditorPane();
				editorPane.setEditable(false);
				editorPane.setFont(new Font("微软雅黑", Font.PLAIN, 12));
				editorPane.setBackground(new Color(245, 245, 245));
				BookDetails.add(editorPane);
				BookDetails.setBackground(Color.white);
				
						
						selectPicButton = new JButton("选择题图片");
						selectPicButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								try {
									selectPicAction();
								} catch (FileNotFoundException e) {
									
									e.printStackTrace();
								}
							}
						});
						selectPicButton.setForeground(Color.decode("#FC8665"));
						selectPicButton.setBorder(BorderFactory.createLineBorder(Color.decode("#FC8665")));
						selectPicButton.setVisible(false);
						selectPicButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
						selectPicButton.setBounds(50, 265, 94, 23);
						BookPic.add(selectPicButton);
						
						
						
						
						
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
						label_5.setBounds(34, 5, 36, 17);
						label_5.setFont(new Font("微软雅黑", Font.PLAIN, 12));
						Info.add(label_5);
						
						BookName = new JTextField();
						BookName.setEditable(false);
						BookName.setBounds(92, 5, 210, 21);
						BookName.setColumns(25);
						Info.add(BookName);
						
						JLabel lblNewLabel = new JLabel("可借副本：");
						lblNewLabel.setBounds(22, 59, 60, 17);
						lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
						Info.add(lblNewLabel);
						
						BookBorrowStatus = new JTextField();
						BookBorrowStatus.setEditable(false);
						BookBorrowStatus.setBounds(92, 59, 210, 21);
						BookBorrowStatus.setColumns(25);
						Info.add(BookBorrowStatus);
						
						JLabel label_1 = new JLabel("责任者：");
						label_1.setBounds(28, 113, 48, 17);
						label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
						Info.add(label_1);
						
						BookAuthor = new JTextField();
						BookAuthor.setEditable(false);
						BookAuthor.setBounds(92, 113, 210, 21);
						BookAuthor.setColumns(25);
						Info.add(BookAuthor);
						
						JLabel label_2 = new JLabel("出版、发行者：");
						label_2.setBounds(10, 167, 84, 17);
						label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
						Info.add(label_2);
						
						BookPublisher = new JTextField();
						BookPublisher.setEditable(false);
						BookPublisher.setBounds(92, 167, 210, 21);
						BookPublisher.setColumns(25);
						Info.add(BookPublisher);
						
						JLabel label_3 = new JLabel("出版发行时间：");
						label_3.setBounds(10, 221, 84, 17);
						label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
						Info.add(label_3);
						
						BookPublishingTime = new JTextField();
						BookPublishingTime.setEditable(false);
						BookPublishingTime.setBounds(92, 221, 210, 21);
						BookPublishingTime.setColumns(25);
						Info.add(BookPublishingTime);
						
						JLabel label_4 = new JLabel("书号：");
						label_4.setHorizontalAlignment(SwingConstants.CENTER);
						label_4.setBounds(16, 275, 72, 17);
						label_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
						Info.add(label_4);
						
						BookID = new JTextField();
						BookID.setEditable(false);
						BookID.setBounds(92, 275, 210, 21);
						BookID.setColumns(25);
						Info.add(BookID);
						
						searchButton = new JButton("搜索");
						searchButton.setBounds(39, 10, 93, 23);
						panel_3.add(searchButton);
						
						JPanel buttonsPanel = new JPanel();
						buttonsPanel.setBounds(726, 43, 174, 497);
						panel.add(buttonsPanel);
						buttonsPanel.setBorder(null);
						buttonsPanel.setBackground(Color.white);
						
						delBookButton = new JButton("删除图书");
						delBookButton.setBounds(33, 89, 96, 25);
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
						borrowOrReturnBookButton.setBounds(33, 33, 96, 23);
						borrowOrReturnBookButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								BorrowOrReturnBookPanel a = new BorrowOrReturnBookPanel(us,contentPane);
								contentPane.getComponent(0).setVisible(false);
								contentPane.add(a.getContentPane());
							}
						});
						buttonsPanel.setLayout(null);
						borrowOrReturnBookButton.setBorderPainted(false);
						borrowOrReturnBookButton.setOpaque(true);
						borrowOrReturnBookButton.setForeground(Color.white);
						borrowOrReturnBookButton.setBackground(Color.decode("#FCC926"));
						buttonsPanel.add(borrowOrReturnBookButton);
						buttonsPanel.add(delBookButton);
						delBookButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
						
						addBookButton = new JButton("添加图书");
						addBookButton.setBounds(33, 147, 96, 25);
						addBookButton.addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent arg0) {
								addBookAction();
							}
						});
						addBookButton.setBorderPainted(false);
						addBookButton.setOpaque(true);
						addBookButton.setForeground(Color.white);
						addBookButton.setBackground(new Color(102, 0, 102));
						buttonsPanel.add(addBookButton);
						addBookButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
						
						authorityEditButton = new JButton("\u6743\u9650\u8bbe\u5b9a");
						authorityEditButton.setBounds(33, 205, 96, 25);
						authorityEditButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								AuthorityEditPanel a = new AuthorityEditPanel(contentPane);
								contentPane.getComponent(0).setVisible(false);
								contentPane.add(a.getPanel());
								
							}
						});
						authorityEditButton.setBorderPainted(false);
						authorityEditButton.setOpaque(true);
						authorityEditButton.setForeground(Color.white);
						authorityEditButton.setBackground(new Color(255, 99, 71));
						buttonsPanel.add(authorityEditButton);
						authorityEditButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
						
						borrowInfoButton = new JButton("借阅详情");
						borrowInfoButton.setBounds(33, 263, 96, 25);
						borrowInfoButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(currentBook != null) {
									BookBorrowInfoPanel a = new BookBorrowInfoPanel(currentBook,contentPane);
									contentPane.getComponent(0).setVisible(false);
									contentPane.add(a.getPanel());
								} 
								
							}
						});
						borrowInfoButton.setBorderPainted(false);
						borrowInfoButton.setOpaque(true);
						borrowInfoButton.setForeground(Color.white);
						borrowInfoButton.setBackground(new Color(153, 204, 51));
						borrowInfoButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
						buttonsPanel.add(borrowInfoButton);
						
						editBookButton = new JButton("编辑图书");
						editBookButton.setBounds(33, 321, 96, 25);
						editBookButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								editBookAction();
								
							}
						});
						editBookButton.setBorderPainted(false);
						editBookButton.setOpaque(true);
						editBookButton.setForeground(Color.white);
						editBookButton.setBackground(new Color(255, 204, 204));
						buttonsPanel.add(editBookButton);
						editBookButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
						//初始化按钮状态
						editBookButton.setEnabled(false);
						borrowInfoButton.setEnabled(false);
						delBookButton.setEnabled(false);
						
						cancelButton = new JButton("取    消");
						cancelButton.setBounds(33, 379, 96, 25);
						cancelButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								cancelAction();
							}
						});
						cancelButton.setVisible(false);
						buttonsPanel.add(cancelButton);
						
						button = new JButton("返回");
						button.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								parentPane.getComponent(0).setVisible(true);
								parentPane.remove(contentPane);
							}
						});
						button.setBounds(33, 437, 96, 25);
						buttonsPanel.add(button);
						
						JLabel label = new JLabel("图书馆");
						label.setBounds(380, 10, 69, 31);
						panel.add(label);
						label.setForeground(Color.BLACK);
						label.setFont(new Font("微软雅黑", Font.PLAIN, 23));
						searchButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								searchBookAction();	
							}
						});
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
	
  	protected void selectPicAction() throws FileNotFoundException {
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
		int selection = JOptionPane.showConfirmDialog(contentPane.getParent(), "真的要删除"+currentBook.getBookName()+"么?","删除图书", JOptionPane.CANCEL_OPTION);
		if(selection == 0){
			ClientMsgHelper cmh = new ClientMsgHelper();
			cmh.delBookInDB(currentBook.getBookId());
			cmh.sendMsg();
			cmh.recieveMsg();
			bookList.removeElement(currentBook);
			currentBook = null;
			try {
				updateCurrentInfo();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			updateButtons();
			dlm.remove(currentIndex);
		}
		
	}


	protected void editBookAction() {
		if(editBookButton.getText() == "编辑图书"){	
			
			borrowInfoButton.setVisible(false);
			authorityEditButton.setVisible(false);
			editBookButton.setText("保存编辑");//按钮设为保存编辑
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
			ClientMsgHelper cmh = new ClientMsgHelper();
			cmh.updateBookInDB(currentBook,oldID);
			cmh.sendMsg();
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
			  try {
				updateCurrentInfo();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			  editBookButton.setEnabled(false);;
			  borrowInfoButton.setEnabled(false);
			  delBookButton.setEnabled(false);
		}
		try {
			updateCurrentInfo();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	
	protected void cancelAction() {
		updateButtons();
		setEditableFalse();
		try {
			updateCurrentInfo();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}


	private void updateButtons() {
		editBookButton.setVisible(true);
		borrowInfoButton.setVisible(true);
		delBookButton.setVisible(true);
		addBookButton.setVisible(true);
		authorityEditButton.setVisible(true);
		selectPicButton.setVisible(false);
		cancelButton.setVisible(false);
		addBookButton.setText("添加图书");
		editBookButton.setText("编辑图书");
		if(currentBook == null){
			editBookButton.setEnabled(false);
			borrowInfoButton.setEnabled(false);
			delBookButton.setEnabled(false);
		}
	}


	protected void searchBookAction() {
		// TODO Auto-generated method stub
		int method = 3;
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
				String newBookNameAndID = book.getBookName()+ " - " + book.getBookId();
				dlm.addElement(newBookNameAndID);
			}
			list.setModel(dlm);
		}
	}



	protected void addBookAction() {
		if(addBookButton.getText() == "添加图书"){////添加图书
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
				ClientMsgHelper cmh = new ClientMsgHelper();
				cmh.addBookToDB(newBook);
				cmh.sendMsg();
				cmh.recieveMsg();
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

	
	protected void updateCurrentInfo() throws IOException {
		if(currentBook != null){
			  BookName.setText(currentBook.getBookName());
			  BookBorrowStatus.setText(currentBook.getBookBorrowStatus());
			  BookAuthor.setText(currentBook.getBookAuthor());
			  BookPublisher.setText(currentBook.getBookPublisher());
			  BookPublishingTime.setText(currentBook.getBookPublishingDate());
			  BookID.setText(currentBook.getBookId());
			  editorPane.setText(currentBook.getBookDetails());

			  if(currentBook.getBookPic() != null){
					String tmpPath = System.getProperty("java.io.tmpdir");
					FileOutputStream out=new FileOutputStream(tmpPath+currentBook.getBookPic()+".jpg");  
					out.write(currentBook.getPicBytes());  
					out.flush();  
					out.close();
				  bookImageIcon = new ImageIcon(tmpPath+currentBook.getBookPic()+".jpg");
				  bookImageIcon.setImage(bookImageIcon.getImage().getScaledInstance(166, 235, Image.SCALE_SMOOTH));
				  picLabel.setIcon(bookImageIcon);
				  picFile = new File(tmpPath+currentBook.getBookPic()+".jpg");
			  }
			  
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
		FileInputStream fi = null;
		try {
			fi = new FileInputStream(picFile.getAbsolutePath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		byte[] b = new byte[100000];
		try {
			fi.read(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		book.setPicBytes(b);
		
	}
}
