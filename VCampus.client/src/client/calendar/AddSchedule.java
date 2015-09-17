package client.calendar;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 添加日程
 * @author 靖芳
 *
 */

public class AddSchedule extends JPanel {
	/**
	 * 
	 */
	int year;
	int month;
	int day;
	String path;
//	private User user;
//	File userFile;// = new File(String.format("F:/schedule/%s", user.getUID()));
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//		userFile = new File(String.format("/schedule/%s", user.getUID()));
//	}

	public JFrame frame;
	public JPanel panel;
	
	private static final long serialVersionUID = -54735834069009546L;
	JTextField textField;
	
	/**
	 * 构造函数
	 * @param frame
	 * @param panel
	 * @param year
	 * @param month
	 * @param day
	 */
	public AddSchedule(JFrame frame,JPanel panel,int year,int month,int day) {
		//this.path = "F:/schedule/"+ user.getUID()+".txt";
		//setUser(user);
		this.frame = frame;
		this.panel = panel;
		this.year = year;
		this.month = month;
		this.day = day;
		//setTitle("添加日程");
		setLayout(null);
		setBounds(0, 0, 900, 550);
		setVisible(true);
		
		
		JLabel lblNewLabel = new JLabel("请输入今天的日程：");
		lblNewLabel.setFont(new Font("新宋体", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 7, 206, 32);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(15, 44, 833, 396);
		add(textField);
		textField.setColumns(10);
		
		JButton save = new JButton("保存");
		save.setBounds(97, 466, 121, 37);
		add(save);
		save.addActionListener(new add_schedule());
		
		JButton exit = new JButton("取消");
		exit.setBounds(599, 466, 130, 37);
		add(exit);
		exit.addActionListener(new exitListener());
		
	}
	
	/**
	 * 退出监听器
	 * @author 靖芳
	 *
	 */
	public class exitListener implements ActionListener{


		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//dispose();
			frame.setContentPane(panel);
		}


	}
	
	/**
	 * 保存监听器
	 * @author 靖芳
	 *
	 */

	public class add_schedule implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String content = textField.getText();
			if(content.equals("")){
				JOptionPane.showMessageDialog(null, "日程内容不能为空");
			}else{
				System.out.println(content);
				try {
					File file = new File("/Users/Suckysherry/Code/workspace/VCampus/VCampus.client/src/res/Schedule.txt");
					FileWriter fw = new FileWriter(file,true);
					String date = year + "," + month + "," + day + ":%%";
					fw.write(date);
					fw.write(content);
					fw.write("@@"+"\r\n");
					fw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//dispose();
				frame.setContentPane(panel);
				JOptionPane.showMessageDialog(null, "日程保存成功");

			}
		}
	}
}
