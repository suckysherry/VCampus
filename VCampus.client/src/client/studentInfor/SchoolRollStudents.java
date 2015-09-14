/**
 *SchoolRollStudents这个类是学生可以看到的学籍管理界面，该界面包括学生可以看到的关于自己的基础信息，
 *由于学生没有办法修改自己的学籍信息，所以该界面仅仅保留有查询信息的功能，但在本类里面预留了修改信息的按键
 *@author  黄靖芳
 **/
package client.studentInfor;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.Component;
import java.awt.Color;
import conn.common.*;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;
import javax.swing.Box;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.DropMode;

public class SchoolRollStudents extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 317212717495677053L;
	private static Student student = new Student();
	private boolean isTeacher;
	private JTextField course;
	private JTextField honor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SchoolRollStudents frame = new SchoolRollStudents(student,false);
					frame.setVisible(true);
					frame.setPanel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public void setPanel() {
		this.setTitle("学籍管理");
		this.setSize(630, 500);
		this.setVisible(true);
		Container content = this.getContentPane();
		// content.setSize(800,800);
		content.setVisible(true);// ���������

		/// ���ܼ�������м������͸���ؼ�
		// ���붥��������TopJpanel
		JPanel TopJpanel = new JPanel();
		TopJpanel.setLocation(0, 0);
		TopJpanel.setVisible(true);
		// Photo.setBackground();
		TopJpanel.setSize(614, 177);

		JPanel Characters = new JPanel();
		Characters.setBounds(5, 5, 434, 172);
		Characters.setBackground(Color.LIGHT_GRAY);
		JPanel Characters0 = new JPanel();
		Characters0.setLocation(0, 0);
		JPanel Characters1 = new JPanel();
		Characters1.setBounds(0, 79, 437, 48);
		JPanel Characters2 = new JPanel();
		Characters2.setBounds(0, 124, 437, 48);
		JPanel Characters3 = new JPanel();
		Characters3.setBounds(0, 35, 437, 48);

		Characters.setLayout(null);
		Characters.add(Characters0);
		Characters.add(Characters1);
		Characters.add(Characters2);
		Characters.add(Characters3);

		// Characters0����

		JLabel name = new JLabel("姓名：" + student.getUName());
		name.setBounds(10, 0, 203, 37);
		name.setVisible(true);
		name.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		name.setHorizontalAlignment(SwingConstants.LEFT);
		Characters0.setSize(437, 41);
		Characters0.setLayout(null);
		Characters0.add(name);
		JLabel stuNumber = new JLabel("学号：" + student.getUID());
		stuNumber.setLocation(10, 10);
		stuNumber.setHorizontalAlignment(SwingConstants.LEFT);
		stuNumber.setSize(209, 41);
		JLabel birthday = new JLabel("生日：" + student.getUBirthday());
		birthday.setBounds(229, 10, 191, 41);
		birthday.setHorizontalAlignment(SwingConstants.LEFT);
		Characters1.setLayout(null);
		Characters1.add(stuNumber);
		Characters1.add(birthday);
		JLabel cardNumber = new JLabel("一卡通：" +student.getUCard());
		cardNumber.setBounds(10, 10, 209, 41);
		JLabel birthplace = new JLabel("籍贯：" + student.getUHometown());
		birthplace.setBounds(229, 10, 191, 41);

		Characters2.setLayout(null);
		Characters2.add(cardNumber);
		Characters2.add(birthplace);
		TopJpanel.setLayout(null);

		JLabel sex = new JLabel("性别：" + student.getUSex());
		sex.setBounds(10, 10, 210, 36);
		JLabel classID = new JLabel("班级：" + student.getUClass());
		classID.setBounds(230, 10, 191, 36);
		Characters3.setLayout(null);
		Characters3.add(sex);
		Characters3.add(classID);

		TopJpanel.add(Characters);

		// �����м������OtherJpanel;
		JPanel OtherJpanel = new JPanel();
		OtherJpanel.setBounds(0, 177, 614, 245);
		OtherJpanel.setLayout(null);

		// ����ײ�������BottomJPanel;
		JPanel BottomJpanel = new JPanel();
		BottomJpanel.setBounds(0, 421, 604, 33);
		BottomJpanel.setLayout(new FlowLayout());
		JButton modify = new JButton("修改");
		modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*if (user.getURole().equalsIgnoreCase("student")) {
					JFrame result = new JFrame();
					result.setVisible(true);
					result.setSize(200, 100);
					Container resCon = new Container();
					resCon.setVisible(true);
					resCon.setLayout(new FlowLayout());
					JLabel tip = new JLabel("�Բ�����û���޸�Ȩ�ޣ�");
					tip.setVisible(true);
					resCon.add(tip);
					result.getContentPane().add(resCon);*/
				if(isTeacher == false)
				{
					JOptionPane.showMessageDialog(new JFrame(), "对不起，您没有修改权限！");
				}else{
					SRT_Modify modify = new SRT_Modify(student);
				}
				/*} else if (student.getURole().equalsIgnoreCase("teacher")) {
					SRT_Modify modify = new SRT_Modify(student);
				}*/

			}
		});
		JButton exit = new JButton("退出");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		BottomJpanel.add(modify);
		BottomJpanel.add(exit);
		this.getContentPane().setLayout(null);

		content.add(TopJpanel);

		JPanel Photo = new JPanel();
		Photo.setBounds(449, 10, 155, 167);
		ImageIcon head = new ImageIcon("C:/Users/靖芳/Desktop/11.jpg");
		Photo.setLayout(null);
		JLabel pic = new JLabel(head);
		pic.setBounds(0, 5, 145, 162);
		Photo.add(pic);
		TopJpanel.add(Photo);

		content.add(OtherJpanel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(10, 0, 598, 232);
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
		content.add(BottomJpanel);

		////

	}

	public SchoolRollStudents() {
		student = null;
		//this.student = new Student("09013132", "0901313406", "student", "�ƾ���", "213130866", "��", "090131", "1995,5,20","��������");
		setPanel();
	}

	public SchoolRollStudents(Student stu,boolean isTeacher) {
		this.student = stu;
		this.isTeacher = isTeacher;

		setPanel();
	}
}
