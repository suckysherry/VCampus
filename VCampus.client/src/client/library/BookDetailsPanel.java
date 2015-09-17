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
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.event.ListSelectionListener;
import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;

import conn.common.Book;
import conn.common.User;

import javax.swing.event.ListSelectionEvent;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.BoxLayout;

/**
 * 提供图书详情的panel,用于显示书的详情
 * @author CDK
 *
 */
public class BookDetailsPanel extends JFrame {

	boolean isAdmin;
	private JPanel contentPane;
	private JTextField BookBorrowStatus;
	private JTextField BookName;
	private JTextField BookAuthor;
	private JTextField BookPublisher;
	private JTextField BookPublishingTime;
	private JTextField BookID;
	private JEditorPane editorPane;
	private Book currentBook;
	private String[] bookNameList;
	private Vector<Book> bookList;
	private JLabel picLabel;
	private ImageIcon bookImageIcon;
	private File picFile;
	private DefaultListModel dlm;
	private int currentIndex;
	private JPanel panel;

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
			}
		});
	}

	/**
	 * Create the frame.
	 * @param parentPane 
	 */
	public BookDetailsPanel(Book book, JPanel parentPanel){
		currentBook = book;
		setPreferredSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 993, 667);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		//setVisible(true);
		setVisible(false);
				panel = new JPanel();
				panel.setBounds(0, 0, 900, 550);
				contentPane.add(panel);
				panel.setLayout(null);
				
				JLabel label = new JLabel("图书详情");
				label.setBounds(404, 5, 92, 31);
				panel.add(label);
				label.setForeground(Color.BLACK);
				label.setFont(new Font("微软雅黑", Font.PLAIN, 23));
				JPanel BookInfoPanel = new JPanel();
				BookInfoPanel.setBounds(10, 46, 617, 494);
				panel.add(BookInfoPanel);
				BookInfoPanel.setLayout(null);
				
				JPanel BookDetails = new JPanel();
				BookDetails.setBounds(5, 340, 602, 108);
				BookInfoPanel.add(BookDetails);
				
				
				
				JPanel BookPic = new JPanel();
				BookPic.setBounds(5, 10, 196, 299);
				BookInfoPanel.add(BookPic);
				BookPic.setLayout(null);
				
				JPanel Info = new JPanel();
				Info.setBounds(211, 10, 396, 299);
				BookInfoPanel.add(Info);
				Info.setLayout(null);
				
				JLabel label_7 = new JLabel("详细信息：");
				label_7.setAlignmentX(Component.CENTER_ALIGNMENT);
				label_7.setBounds(5, 319, 60, 17);
				label_7.setFont(new Font("微软雅黑", Font.PLAIN, 12));
				BookInfoPanel.add(label_7);
				BookDetails.setLayout(new BoxLayout(BookDetails, BoxLayout.X_AXIS));
				
				
				editorPane = new JEditorPane();
				editorPane.setEditable(false);
				editorPane.setFont(new Font("微软雅黑", Font.PLAIN, 12));
				BookDetails.add(editorPane);
				
						JPanel panel_1 = new JPanel();
						panel_1.setBounds(10, 10, 176, 245);
						BookPic.add(panel_1);
						
						picLabel = new JLabel();
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
						BookName.setBounds(126, 5, 231, 21);
						BookName.setColumns(25);
						Info.add(BookName);
						
						JLabel lblNewLabel = new JLabel("可借副本：");
						lblNewLabel.setBounds(33, 61, 60, 17);
						lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
						Info.add(lblNewLabel);
						
						BookBorrowStatus = new JTextField();
						BookBorrowStatus.setEditable(false);
						BookBorrowStatus.setBounds(126, 59, 231, 21);
						BookBorrowStatus.setColumns(25);
						Info.add(BookBorrowStatus);
						
						JLabel label_1 = new JLabel("责任者：");
						label_1.setBounds(39, 115, 48, 17);
						label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
						Info.add(label_1);
						
						BookAuthor = new JTextField();
						BookAuthor.setEditable(false);
						BookAuthor.setBounds(126, 113, 231, 21);
						BookAuthor.setColumns(25);
						Info.add(BookAuthor);
						
						JLabel label_2 = new JLabel("出版、发行者：");
						label_2.setBounds(21, 169, 84, 17);
						label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
						Info.add(label_2);
						
						BookPublisher = new JTextField();
						BookPublisher.setEditable(false);
						BookPublisher.setBounds(126, 167, 231, 21);
						BookPublisher.setColumns(25);
						Info.add(BookPublisher);
						
						JLabel label_3 = new JLabel("出版发行时间：");
						label_3.setBounds(21, 223, 84, 17);
						label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
						Info.add(label_3);
						
						BookPublishingTime = new JTextField();
						BookPublishingTime.setEditable(false);
						BookPublishingTime.setBounds(126, 221, 231, 21);
						BookPublishingTime.setColumns(25);
						Info.add(BookPublishingTime);
						
						JLabel label_4 = new JLabel("书号：");
						label_4.setHorizontalAlignment(SwingConstants.CENTER);
						label_4.setBounds(27, 277, 72, 17);
						label_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
						Info.add(label_4);
						
						BookID = new JTextField();
						BookID.setEditable(false);
						BookID.setBounds(126, 275, 231, 21);
						BookID.setColumns(25);
						Info.add(BookID);
						
						JPanel panel_2 = new JPanel();
						panel_2.setBounds(637, 46, 253, 494);
						panel.add(panel_2);
						panel_2.setLayout(null);
						
						JButton btnNewButton = new JButton("返回");
						btnNewButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								parentPanel.getComponent(0).setVisible(true);
								parentPanel.remove(getPanel());
							}
						});
						btnNewButton.setBounds(91, 235, 71, 23);
						panel_2.add(btnNewButton);
				try {
					updateCurrentInfo();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
		
	}


	public JPanel getPanel() {
		return panel;
	}


	public void setPanel(JPanel panel) {
		this.panel = panel;
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
}
	
	

