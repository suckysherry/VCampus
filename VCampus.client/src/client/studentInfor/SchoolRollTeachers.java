package client.studentInfor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import conn.common.*;

public class SchoolRollTeachers extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6365800043800492151L;
	public static boolean isTeacher;
	private JTextField course;
	private JTextField honor;


		//��SchoolRollTeacher�������޲εĹ��캯��
		SchoolRollTeachers(){
			teacher = null;
			//this.teacher = new Teacher();
			setPanel();
		}
		//��SchoolRollTeacher���ú�����Ĺ��캯��
		SchoolRollTeachers(Teacher teacher,boolean isTeacher){
			this.isTeacher = isTeacher;
			this.teacher = teacher;
			//this.teacher = new Teacher();
			setPanel();
		}
		
		public void setPanel(){
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setTitle("教师学籍管理");
			Container Tcontents = this.getContentPane();
			this.setSize(630, 500);
			this.setVisible(true);
			///���ܼ�������м������͸���ؼ�
			//���붥��������TopJpanel
			JPanel TopJpanel = new JPanel();
			TopJpanel.setLocation(0, 0);
			TopJpanel.setVisible(true);
			//Photo.setBackground();
			TopJpanel.setSize(611, 177);
			
			JPanel Characters = new JPanel();
			Characters.setBounds(5, 5, 437, 172);
			Characters.setBackground(Color.LIGHT_GRAY);
			JPanel Characters0 = new JPanel();
			Characters0.setLocation(0, 0);
			JPanel Characters1 = new JPanel();
			Characters1.setBounds(0, 79, 442, 48);
			JPanel Characters2 = new JPanel();
			Characters2.setBounds(0, 124, 442, 48);
			JPanel Characters3 = new JPanel();
			Characters3.setBounds(0, 35, 442, 48);
			
			Characters.setLayout(null);
			Characters.add(Characters0);
			Characters.add(Characters1);
			Characters.add(Characters2);
			Characters.add(Characters3);
			
			//Characters0����
	

			JLabel name = new JLabel("姓名："+teacher.getUName());
			name.setBounds(10, 0, 221, 37);
			name.setVisible(true);
			name.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			name.setHorizontalAlignment(SwingConstants.LEFT);
			Characters0.setSize(442, 41);
			Characters0.setLayout(null);
			Characters0.add(name);
			JLabel stuNumber = new JLabel("工号："+teacher.getUID());
			stuNumber.setLocation(10, 10);
			stuNumber.setHorizontalAlignment(SwingConstants.LEFT);
			stuNumber.setSize(230, 41);
			JLabel birthday = new JLabel("生日："/*+teacher.getUBirthday()*/);
			birthday.setBounds(241, 10, 190, 41);
			birthday.setHorizontalAlignment(SwingConstants.LEFT);
			Characters1.setLayout(null);
			Characters1.add(stuNumber);
			Characters1.add(birthday);
			JLabel cardNumber = new JLabel("一卡通号："+teacher.getUCard());
			cardNumber.setBounds(10, 10, 230, 41);
			JLabel birthplace = new JLabel("籍贯："/*+teacher.getUHometown()*/);
			birthplace.setBounds(240, 10, 191, 41);

			Characters2.setLayout(null);
			Characters2.add(cardNumber);
			Characters2.add(birthplace);
			TopJpanel.setLayout(null);

			JLabel sex = new JLabel("性别："+teacher.getUSex());
			sex.setBounds(10, 10, 231, 36);
			JLabel classID = new JLabel("班级："+teacher.getUClass());
			classID.setBounds(242, 10, 191, 36);
			Characters3.setLayout(null);
			Characters3.add(sex);
			Characters3.add(classID);
			
			TopJpanel.add(Characters);
			
			JPanel OtherJpanel = new JPanel();
			OtherJpanel.setBounds(0, 177, 611, 227);
			OtherJpanel.setLayout(null);
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBorder(null);
			tabbedPane.setBounds(10, 10, 599, 217);
			OtherJpanel.add(tabbedPane);
			
			course = new JTextField();
			course.setHorizontalAlignment(SwingConstants.LEFT);
			course.setBorder(null);
			course.setText("马克思列宁主义");
			tabbedPane.addTab("课程信息", new ImageIcon(SchoolRollStudents.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")), course, null);
			tabbedPane.setEnabledAt(0, true);
			course.setColumns(10);
			
			honor = new JTextField();
			honor.setHorizontalAlignment(SwingConstants.LEFT);
			honor.setBorder(null);
			honor.setText("校三好学生");
			honor.setHorizontalAlignment(SwingConstants.LEFT);
			tabbedPane.addTab("学生荣誉", new ImageIcon(SchoolRollStudents.class.getResource("/com/sun/java/swing/plaf/windows/icons/HardDrive.gif")), honor, null);
			honor.setColumns(10);
			
			//����ײ�������BottomJPanel;
			JPanel BottomJpanel = new JPanel();
			BottomJpanel.setBounds(0, 414, 611, 33);
			BottomJpanel.setLayout(new FlowLayout());
			JButton manage = new JButton("管理学生");
			manage.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					SRT_Manager manager = new SRT_Manager(teacher);
					manager.setVisible(true);
				}
				
			});
			JButton modify = new JButton("修改");
			modify.setVisible(false);
			modify.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SRT_Modify modify = new SRT_Modify(teacher);
				}
			});
			JButton exit = new JButton("退出");
			exit.addActionListener(new ExitLisener());
			BottomJpanel.add(manage);
			BottomJpanel.add(modify);
			BottomJpanel.add(exit);
			this.getContentPane().setLayout(null);
			
			
			Tcontents.add(TopJpanel);
			
			JPanel Photo = new JPanel();
			Photo.setBounds(460, 10, 141, 172);
			ImageIcon head = new ImageIcon("C:/Users/靖芳/Desktop/11.jpg");
			JLabel pic = new JLabel(head);
			Photo.add(pic);
			TopJpanel.add(Photo);
			
			
			Tcontents.add(OtherJpanel);
			Tcontents.add(BottomJpanel);
			
			
		}
		public class ExitLisener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//dispose();
				System.exit(0);
			}
			
		}
		
		private Teacher teacher;
		
		
		//**�����ǽ�ʦѧ������ϵͳ�Ľ�������
		
		
		
		 /** @param args
		 */
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SchoolRollTeachers Tframe = new SchoolRollTeachers();
					Tframe.setVisible(true);
					Tframe.setPanel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}

}
