package client.studentInfor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import conn.common.*;


public class SRT_Modify extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7669576611425062184L;
	private JPanel contentPane;
	private static Teacher teacher = new Teacher();
	private Student student = new Student();
	private Student studentreturn= new Student();
	private JTextField NameInput;
	private JTextField SexInput;
	private JTextField ClassInput;
	private JTextField IDInput;
	private JTextField BirthdayInput;
	private JTextField CardInput;
	private JTextField HometownInput;
	
	private JTextField course;
	private JTextField honor;
	private Connection con;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {

		try {
			SRT_Modify frame = new SRT_Modify(teacher);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/
	private Connection connection(){
		String url ="jdbc:mysql://107.170.216.207:3306/VirtualCampus";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con =DriverManager.getConnection(url,"vc_admin","12345678");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * Create the frame.
	 */
	public SRT_Modify(Teacher teacher) {
		this.teacher = null;
		//this.teacher = new Student("09013132","0901313406","student","�ƾ���","213130866","��","090131","1995,5,20","��������");
		setPanel();
	}
	/**
	 * @wbp.parser.constructor
	 */
	public SRT_Modify(Student student) {
		this.student = student;
		//this.student= new Student("09013132","0901313406","student","�ƾ���","213130866","��","090131","1995,5,20","��������");
		setPanel();
	}
	public void setPanel(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		this.setTitle("修改信息");
		this.setSize(630, 500);
		this.setVisible(true);
		Container content = this.getContentPane();
		//content.setSize(800,800);
		content.setVisible(true);//���������
		
		//���ܼ�������м������͸���ؼ�
		//���붥��������TopJpanel
		JPanel TopJpanel = new JPanel();
		TopJpanel.setLocation(0, 0);
		TopJpanel.setVisible(true);
		//Photo.setBackground();
		TopJpanel.setSize(784, 177);
		
		JPanel Characters = new JPanel();
		Characters.setBounds(5, 5, 462, 172);
		Characters.setBackground(Color.LIGHT_GRAY);
		JPanel Characters0 = new JPanel();
		Characters0.setLocation(0, 0);
		JPanel Characters1 = new JPanel();
		Characters1.setBounds(0, 79, 464, 48);
		JPanel Characters2 = new JPanel();
		Characters2.setBounds(0, 124, 464, 48);
		JPanel Characters3 = new JPanel();
		Characters3.setBounds(0, 35, 464, 48);
		
		Characters.setLayout(null);
		Characters.add(Characters0);
		Characters.add(Characters1);
		Characters.add(Characters2);
		Characters.add(Characters3);
		
		//Characters0����
		
		JLabel name = new JLabel("姓名：");
		
		name.setBounds(10, 0, 44, 37);
		name.setVisible(true);
		name.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		name.setHorizontalAlignment(SwingConstants.LEFT);
		Characters0.setSize(464, 41);
		Characters0.setLayout(null);
		Characters0.add(name);
		
		NameInput = new JTextField(student.getUName());
		NameInput.setBounds(73, 3, 155, 31);
		Characters0.add(NameInput);
	//	NameInput.setText(student.getUName());
		NameInput.setColumns(10);

		
		JLabel stuNumber = new JLabel("学号：");
		stuNumber.setLocation(10, 10);
		stuNumber.setHorizontalAlignment(SwingConstants.LEFT);
		stuNumber.setSize(52, 41);
		JLabel birthday = new JLabel("生日：");
		birthday.setBounds(241, 10, 45, 41);
		birthday.setHorizontalAlignment(SwingConstants.LEFT);
		Characters1.setLayout(null);
		Characters1.add(stuNumber);
		Characters1.add(birthday);
		
		IDInput = new JTextField(student.getUID());
		IDInput.setBounds(72, 17, 156, 28);
		//IDInput.setText(student.getUID());
		Characters1.add(IDInput);
		IDInput.setColumns(10);
		
		BirthdayInput = new JTextField(student.getUBirthday());
		BirthdayInput.setBounds(280, 18, 172, 26);
		//BirthdayInput.setText(student.getUBirthday());
		Characters1.add(BirthdayInput);
		BirthdayInput.setColumns(10);
		JLabel cardNumber = new JLabel("一卡通号：");
		cardNumber.setBounds(10, 10, 67, 41);
		JLabel birthplace = new JLabel("籍贯：");
		birthplace.setBounds(240, 10, 43, 41);

		Characters2.setLayout(null);
		Characters2.add(cardNumber);
		Characters2.add(birthplace);
		
		CardInput = new JTextField(student.getUCard());
		CardInput.setBounds(72, 17, 156, 28);
		//CardInput.setText(student.getUCard());
		Characters2.add(CardInput);
		CardInput.setColumns(10);
		
		HometownInput = new JTextField(student.getUHometown());
		HometownInput.setBounds(279, 17, 172, 28);
		//HometownInput.setText(student.getUHometown());
		Characters2.add(HometownInput);
		HometownInput.setColumns(10);
		TopJpanel.setLayout(null);

		JLabel sex = new JLabel("性别");
		sex.setBounds(10, 10, 49, 36);
		JLabel classID = new JLabel("班级：");
		classID.setBounds(240, 10, 49, 36);
		Characters3.setLayout(null);
		Characters3.add(sex);
		Characters3.add(classID);
		
		SexInput = new JTextField(student.getUSex());
		SexInput.setBounds(72, 15, 155, 28);
		//SexInput.setText(student.getUSex());
		Characters3.add(SexInput);
		SexInput.setColumns(10);
		
		ClassInput = new JTextField(student.getUClass());
		ClassInput.setBounds(281, 15, 173, 28);
		//ClassInput.setText(student.getUClass());
		Characters3.add(ClassInput);
		ClassInput.setColumns(10);
		
		TopJpanel.add(Characters);
		
		
		JPanel OtherJpanel = new JPanel();
		OtherJpanel.setBounds(0, 177, 615, 227);
		OtherJpanel.setLayout(null);
		
		
		JTabbedPane tabbedPane1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane1.setBorder(null);
		tabbedPane1.setBounds(10, 0, 595, 217);
		OtherJpanel.add(tabbedPane1);
		
		course = new JTextField();
		course.setHorizontalAlignment(SwingConstants.LEFT);
		course.setBorder(null);
		course.setText("马克思列宁主义");
		tabbedPane1.addTab("课程信息", new ImageIcon(SchoolRollStudents.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")), course, null);
		tabbedPane1.setEnabledAt(0, true);
		course.setColumns(10);
		
		honor = new JTextField();
		honor.setHorizontalAlignment(SwingConstants.LEFT);
		honor.setBorder(null);
		honor.setText("校三好学生");
		honor.setHorizontalAlignment(SwingConstants.LEFT);
		tabbedPane1.addTab("学生荣誉", new ImageIcon(SchoolRollStudents.class.getResource("/com/sun/java/swing/plaf/windows/icons/HardDrive.gif")), honor, null);
		honor.setColumns(10);
		//content.add(BottomJpanel);
		
		//����ײ�������BottomJPanel;
		JPanel BottomJpanel = new JPanel();
		BottomJpanel.setBounds(10, 414, 594, 33);
		BottomJpanel.setLayout(new FlowLayout());
		JButton ensure = new JButton("确认");
		ensure.addActionListener(new ensureListener());
		JButton exit = new JButton("退出");
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
			
		});
		BottomJpanel.add(ensure);
		BottomJpanel.add(exit);
		this.getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		
		content.add(TopJpanel);
		
		JPanel Photo = new JPanel();
		Photo.setBounds(476, 10, 130, 167);
		ImageIcon head = new ImageIcon("C:/Users/靖芳/Desktop/11.jpg");
		TopJpanel.add(Photo);
		Photo.setLayout(null);
		JLabel pic = new JLabel(head);
		pic.setBounds(0, 0, 128, 167);
		pic.setText("pic");
		Photo.add(pic);
		
		
		content.add(OtherJpanel);
		content.add(BottomJpanel);
	}
	
			
			////		
	public class ensureListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			String sql = "INSERT　INTO Student VALUE('" + NameInput.getText()+"','" +BirthdayInput.getText()
				+ "','" + BirthdayInput.getText() + "','" + ClassInput.getText() + "','"
				+ HometownInput.getText() + "','" +IDInput.getText() + "','" + SexInput.getText();
			//System.out.println(sql);
			Statement stmt;
			
			try {
				stmt = connection().createStatement();
				stmt.execute(sql);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//System.out.println(result);
			

			JOptionPane.showMessageDialog(null, "修改成功！");
		}
		
	}
	

	

	
	
}
