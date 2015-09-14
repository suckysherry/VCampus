package client.studentInfor;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import conn.common.*;


import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

public class SRT_Manager extends JFrame {

	/**
	 * 
	 */
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
	private static final long serialVersionUID = -7172832820206867909L;
	private JPanel contentPane;
	private static Teacher teacher = new Teacher();
	private Student student = new Student();
	private String [] classinfo;

	private String getclass;
	private String getstudent;
	private Connection con;
	public Teacher getTeacher(){
		return teacher;
	}
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SRT_Manager frame = new SRT_Manager(teacher);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	/**
	 * Create the frame.
	 */


	public SRT_Manager(Teacher teacher) {
		this.teacher = teacher;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(400, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 384, 145);
		contentPane.add(panel);
		panel.setLayout(null);

		final JComboBox<String> CBClass = new JComboBox<String>();
		//CBClass.addActionListener(new getClassListener());
		String class_info = teacher.getUClass();
		classinfo = class_info.split(",");
		CBClass.addItem("请选择班级");
		for(int i = 0; i < classinfo.length; i++)
		{
			CBClass.addItem(classinfo[i]);
		}
		//CBClass.addItem(teacher.getUClass());
		/*int count = 0;
		if(count < classinfo.size())
		{
			CBClass.addItem(classinfo.get(count));
			count++;
		}*/
		

		//CBClass.setSelectedItem("1");
		

		CBClass.setBounds(163, 22, 140, 26);
		panel.add(CBClass);

		JLabel lblNewLabel = new JLabel("\u73ED\u7EA7\uFF1A");
		lblNewLabel.setBounds(68, 22, 47, 26);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u59D3\u540D\uFF1A");
		lblNewLabel_1.setBounds(68, 82, 47, 26);
		panel.add(lblNewLabel_1);

		final JComboBox<String> CBName = new JComboBox<String>();

		System.out.println("SELECT * From ClassInfo WHERE Class = '" + getclass + "'");
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
	/*	String sql = "SELECT * From ClassInfo WHERE Class = '" + getclass + "'";
		System.out.println(sql);
		Statement stmt;
		ResultSet result = null;
		try {
			stmt = connection().createStatement();
			result = stmt.executeQuery(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//System.out.println(result);

		try {
			while(result.next())
			{
				CBName.addItem(result.getString("student"));
				System.out.println(result.getString("student"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	*/
		ItemListener itemlis = new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == e.SELECTED)
				{
					getclass = (String) CBClass.getSelectedItem();
					// TODO Auto-generated method stub
					String sql = "SELECT * From ClassInfo WHERE Class = '" + getclass + "'";
					System.out.println(sql);
					Statement stmt;
					ResultSet result = null;
					try {
						stmt = connection().createStatement();
						result = stmt.executeQuery(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//System.out.println(result);
					
					CBName.removeAllItems();
					CBName.addItem("请选择学生");
					try {
					
						while(result.next())
						{
							CBName.addItem(result.getString("student"));
							System.out.println(result.getString("student"));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		};
		CBClass.addItemListener(itemlis);
		/*int count1 = 0;
		if(count1 < studentinfo.size())
		{
			CBClass.addItem(studentinfo.get(count1));
			count1++;
		}*/
		CBName.setBounds(163, 82, 140, 26);
		ItemListener getstu = new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == e.SELECTED)
				{
					getstudent = (String) CBName.getSelectedItem();
				}
			}
			
		};
		CBName.addItemListener(getstu);
		

		panel.add(CBName);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 145, 384, 66);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton yes = new JButton("\u786E\u8BA4");
		yes.setBounds(34, 10, 114, 31);
		yes.addActionListener(new yesListener());
		panel_1.add(yes);

		JButton quit = new JButton("\u9000\u51FA");
		quit.setBounds(229, 10, 132, 31);
		quit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.exit(0);
				dispose();
			}

		});
		panel_1.add(quit);
	}

	public class yesListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				String sql = "SELECT * From STUDENT WHERE ID = '" + getstudent + "'";
				System.out.println(sql);
				Statement stmtment;
				ResultSet resultset = null;
				try {
					stmtment = connection().createStatement();
					resultset = stmtment.executeQuery(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//System.out.println(result);
				while (resultset.next()){
					System.out.println(resultset.getString(4)+resultset.getString(8));			
					student.setUName(resultset.getString(4));
					student.setUBirthday(resultset.getString(8));
					student.setUCard(resultset.getString(1));
					student.setUClass(resultset.getString(6));
					student.setUHometown(resultset.getString(7));
					student.setUID(resultset.getString(2));
					student.setURole(resultset.getString(3));
					student.setUSex(resultset.getString(6));
				}
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SchoolRollStudents stu =new SchoolRollStudents(student,true);
			stu.setVisible(true);
			stu.setPanel();
			dispose();
		}

	
	
	/*public class getClassListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String sql = "SELECT * From Teacher WHERE name =" + teacher.getUName();
			Statement stmt;
			ResultSet result = null;
			try {
				stmt = connection().createStatement();
				result = stmt.executeQuery(sql);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

			
			int count = 0;
			try {
				while(result.next()){
					String classname = result.getString("class");
					classinfo.add(classname);
					count++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			classinfo.
		}*/
			

	}
	/*public class getStudentListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Socket socket;
			try {
				socket = new Socket("localhost",12345);
				ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				
				
				ResultSet result = (ResultSet) is.readObject();
				os.writeBytes("SELECT * From ClassInfo WHERE class = "+ getclass);

				int count = 0;
				try {
					while(result.next()){
						String studentname = result.getString(count);
						studentinfo.add(studentname);
						count++;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
				//studentinfo = class_mess.getClassInfo().getStudent();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}*/
	
	/*@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}*/
}
