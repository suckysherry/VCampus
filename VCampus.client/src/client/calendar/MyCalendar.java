/**
 * @author PatrickHuang(huangjingfang)
 * This is a Java sourse code of a caldendar,the calendar can add schedules and look up for schedules
 * the schedules are located in file(schedule/txt),for the limitation of time, we didn't upload this file to servicer 
 * 
 */

package client.calendar;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;



public class MyCalendar extends JPanel{ //defination of MyCalendar
	/**
	 * 
	 */


	private static final long serialVersionUID = 2896991358988547290L;
	int yearinfo;
	int monthinfo;
	int dayinfo;
//	private File userfile;
//	private User user = new User("t1","t1","teacher");
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//		//userfile = new File(String.format("/schedule/%s", user.getUID()));
//	}

	String path;
	JButton[] btn = new JButton[42];
	defaultMListener[] def = new defaultMListener[42];
	final JComboBox<Integer> year = new JComboBox<Integer>();
	final JComboBox<Integer> month = new JComboBox<Integer>();
	String onclickbtn;
	JButton onClickedbtn = new JButton();

	static JFrame jframe =new JFrame();
	static MyCalendar frame;

	//private JPanel contentPane;
	//	public void changePanel(){
	//		jframe.setContentPane(new panel(jframe,frame));
	//	}


	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					//int a = (2015-2016)/4;
//					//System.out.println(a);
//					
//					frame = new MyCalendar();
//					jframe.setContentPane(frame);
//					//frame.setIconImage(null);
//					frame.setVisible(true);
//					jframe.setVisible(true);
//					jframe.setBounds(100, 100, 900, 550);
//					//System.out.println(frame.getweekday(2018,6,25));
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	/**
	 * inintialize the button
	 */
	public void initbtn(){
		for(int i = 0; i<42;i++){
			btn[i].setBackground(Color.WHITE);
			btn[i].setText("");
		}

	}
	
	/**
	 * 获得一个月的天数
	 * @param month
	 * @return
	 */
	public int getmonthday(int month){
		int monthaddition;
		if(yearinfo % 4 == 0){
			monthaddition = 1;
		}else monthaddition = 0;
		switch(month){

		case 1: return 31; 
		case 2: return 28 + monthaddition; 
		case 3: return 31;
		case 4: return 30;
		case 5: return 31;
		case 6: return 30;
		case 7: return 31;
		case 8: return 31;
		case 9: return 30;
		case 10: return 31;
		case 11: return 30;
		case 12: return 31;
		default: return 0;
		}
	}

	/**
	 * 获得某年某月某日是星期几
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public int getweekday(int year,int month,int day){
		int weekday = 0;
		int length;
		int yearlen;
		int monthlen = 0;
		int yearaddition = (year-2013)/4;
		int monthaddition;
		if(year % 4 == 0){
			monthaddition = 1;
		}else monthaddition = 0;
		yearlen = (year - 2015) * 365 + yearaddition;
		switch(month){
		case 1: monthlen = 0; break;
		case 2: monthlen = 31; break;
		case 3: monthlen = 59 + monthaddition;break;
		case 4: monthlen = 90 + monthaddition;break;
		case 5: monthlen = 120 + monthaddition;break;
		case 6: monthlen = 151 + monthaddition;break;
		case 7: monthlen = 181 + monthaddition;break;
		case 8: monthlen = 212 + monthaddition;break;
		case 9: monthlen = 243 + monthaddition;break;
		case 10: monthlen = 273 + monthaddition;break;
		case 11: monthlen = 304 + monthaddition;break;
		case 12: monthlen = 334 + monthaddition;break;
		default: JOptionPane.showMessageDialog(null, "月份输入错误");
		}
		length = yearlen + monthlen + day;
		//System.out.println(yearlen);
		//System.out.println(monthlen);
		//System.out.println(length);
		weekday = (length % 7 + 3)% 7;
		return weekday;

	}

	/**
	 * 获得被点击按钮上的日子
	 */
	public int getClickedButton(){
		return dayinfo;

	}
	/**
	 * Create the frame.
	 * 构造函数
	 */
	public MyCalendar() {
//		this.path = String.format("F:/%s.txt ", user.getUID());
//		File userid = new File(path);
		//setUser(user);
		//File file = getClass().getResource("/schedule/schedule.txt");
		Calendar now = Calendar.getInstance();
		yearinfo = now.get(Calendar.YEAR);
		monthinfo = now.get(Calendar.MONTH);
		//final ShowMessage showmess = new ShowMessage();
		onclickbtn = "";
		//setUndecorated(true);
		//getRootPane().setWindowDecorationStyle(JTextPane.SOMEBITS);
		//getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
		//setPreferredSize(new Dimension(400, 200));
		//pack();

		setBackground(Color.WHITE);
		//setForeground(Color.WHITE);
		//setTitle("日历");
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		//contentPane = new JPanel();
		//contentPane.setBackground(Color.WHITE);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 860, 105);
		add(panel);
		panel.setLayout(null);


		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(0, 59, 853, 40);
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel label = new JLabel("星期日");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label);

		JLabel label_1 = new JLabel("星期一");
		label_1.setBackground(Color.WHITE);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_1);

		JLabel label_2 = new JLabel("星期二");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_2);

		JLabel label_3 = new JLabel("星期三");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_3);

		JLabel label_4 = new JLabel("星期四");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_4);

		JLabel label_5 = new JLabel("星期五");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_5);

		JLabel label_6 = new JLabel("星期六");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_6);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(0, 25, 853, 27);
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel label_7 = new JLabel("年份：");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_3.add(label_7);


		for(int i = 2015; i < 2025; i++){
			year.addItem(i);
		}
		year.setSelectedItem(yearinfo);
		ItemListener getyear = new ItemListener(){										//getyear

			@SuppressWarnings("static-access")
			@Override
			public void itemStateChanged(ItemEvent e) {

				if(e.getStateChange() == e.SELECTED){
					yearinfo = (int) year.getSelectedItem();
					monthinfo = (int) month.getSelectedItem();
					int beginwd;
					//System.out.println(yearinfo + "\t" + monthinfo);
					beginwd = getweekday(yearinfo,monthinfo,1);
					//System.out.println(beginwd);
					initbtn();
					for(int i=0;i<getmonthday(monthinfo);i++){
						btn[beginwd+i].setText(i+1+"");
					}
					//System.out.println(yearinfo);
					
				}
				for(int i=0;i<42;i++){
					btn[i].removeMouseListener(def[i]);;
					btn[i].setBackground(Color.WHITE);
					if(btn[i].getText() == null){
					}
					else{
						//JOptionPane.showMessageDialog(null, btn[i].getText());
						btn[i].addMouseListener(def[i]);
					}
				}
				settips();
			}


		};
		year.addItemListener(getyear);
		panel_3.add(year);

		JLabel label_8 = new JLabel("月：");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_3.add(label_8);


		for(int i = 1; i<=12;i++){
			month.addItem(i);
		}
		month.setSelectedItem(monthinfo+1);
		ItemListener getmonth = new ItemListener(){						//getmonth

			@SuppressWarnings("static-access")
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == e.SELECTED){
					monthinfo = (int) month.getSelectedItem();
					//System.out.println(monthinfo);

					int beginwd;
					//System.out.println(yearinfo + "\t" + monthinfo);
					beginwd = getweekday(yearinfo,monthinfo,1);
					//System.out.println(beginwd);
					initbtn();
					//System.out.println(monthinfo);
					//System.out.println(getmonthday(monthinfo));
					for(int i=0;i<getmonthday(monthinfo);i++){
						btn[beginwd+i].setText(i+1+"");
						//System.out.println(i);
					}
				}//这个函数当月份改变时重置所有按钮，并加上新的值
				for(int i=0;i<42;i++){
					btn[i].removeMouseListener(def[i]);;
					btn[i].setBackground(Color.WHITE);
					if(btn[i].getText() == null){
					}
					else{
						//JOptionPane.showMessageDialog(null, btn[i].getText());

						btn[i].addMouseListener(def[i]);;
					}
				}
				settips();
			}


		};
		month.addItemListener(getmonth);
		panel_3.add(month);


		JLabel lblNewLabel_1 = new JLabel("");
		panel_3.add(lblNewLabel_1);

		JButton button = new JButton("今天");
		button.setForeground(Color.BLACK);
		button.setBorderPainted(false);
		button.setBackground(new Color(255, 153, 0));
		panel_3.add(button);
		button.addActionListener(new ActionListener() {

			//			@Override
			//			public void actionPerformed(ActionEvent e) {
			//				// TODO Auto-generated method stub
			//				changePanel();
			//			}							//btn今天

			@Override
			public void actionPerformed(ActionEvent e) {
				initbtn();

				Calendar now1 = Calendar.getInstance();

				yearinfo = now1.get(Calendar.YEAR);
				monthinfo = now1.get(Calendar.MONTH);
				int day = now1.get(Calendar.DATE);


				int beginwd = getweekday(yearinfo,monthinfo+1,1);
				int thismonth = monthinfo + 1;
				//System.out.println(monthinfo);
				month.setSelectedItem(thismonth);

				year.setSelectedItem(yearinfo);

				//System.out.println(yearinfo + "\t\t" + monthinfo);
				for(int i=0;i<getmonthday(thismonth);i++){
					btn[beginwd+i].setText(i+1+"");
					//btn[beginwd+i].setText("\n #");
				}
				settips();
				for(int i = 0; i < 42; i ++){
					//boolean flag= (btn[i].getText() == Integer.toString(day));
					String str1 = btn[i].getText();
					String str2 = Integer.toString(day);
					if(str2.equalsIgnoreCase(str1)){
						btn[i].setBackground(Color.LIGHT_GRAY);
					}//else System.out.println(btn[i].getText() + Integer.toString(day));
				}
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(9, 108, 851, 390);
		add(panel_1);
		panel_1.setLayout(new GridLayout(6, 0, 0, 0));

		btn[0] = new JButton();
		btn[0].setBackground(Color.WHITE);
		panel_1.add(btn[0]);

		btn[1] = new JButton();
		btn[1].setBackground(Color.WHITE);
		panel_1.add(btn[1]);
		//btnNewButton_1.setBorder(null);
		//btnNewButton_1.setBorderPainted(false);

		btn[2] = new JButton();
		btn[2].setBackground(Color.WHITE);
		panel_1.add(btn[2]);

		btn[3] = new JButton();
		btn[3].setBackground(Color.WHITE);
		panel_1.add(btn[3]);

		btn[4] = new JButton();
		btn[4].setBackground(Color.WHITE);
		panel_1.add(btn[4]);

		btn[5] = new JButton();
		btn[5].setBackground(Color.WHITE);
		panel_1.add(btn[5]);

		btn[6] = new JButton();
		btn[6].setBackground(Color.WHITE);
		panel_1.add(btn[6]);

		btn[7] = new JButton();
		btn[7].setBackground(Color.WHITE);
		panel_1.add(btn[7]);

		btn[8] = new JButton();
		btn[8].setBackground(Color.WHITE);
		panel_1.add(btn[8]);

		btn[9] = new JButton();
		btn[9].setBackground(Color.WHITE);
		panel_1.add(btn[9]);

		btn[10] = new JButton();
		btn[10].setBackground(Color.WHITE);
		panel_1.add(btn[10]);

		btn[11] = new JButton();
		btn[11].setBackground(Color.WHITE);
		panel_1.add(btn[11]);

		btn[12] = new JButton();
		btn[12].setBackground(Color.WHITE);
		panel_1.add(btn[12]);

		btn[13] = new JButton();
		btn[13].setBackground(Color.WHITE);
		panel_1.add(btn[13]);

		btn[14] = new JButton();
		btn[14].setBackground(Color.WHITE);
		panel_1.add(btn[14]);

		btn[15] = new JButton();
		btn[15].setBackground(Color.WHITE);
		panel_1.add(btn[15]);

		btn[16] = new JButton();
		btn[16].setBackground(Color.WHITE);
		panel_1.add(btn[16]);

		btn[17] = new JButton();
		btn[17].setBackground(Color.WHITE);
		panel_1.add(btn[17]);

		btn[18] = new JButton();
		btn[18].setBackground(Color.WHITE);
		panel_1.add(btn[18]);

		btn[19]= new JButton();
		btn[19].setBackground(Color.WHITE);
		panel_1.add(btn[19]);

		btn[20] = new JButton();
		btn[20].setBackground(Color.WHITE);
		panel_1.add(btn[20]);

		btn[21] = new JButton();
		btn[21].setBackground(Color.WHITE);
		panel_1.add(btn[21]);

		btn[22] = new JButton();
		btn[22].setBackground(Color.WHITE);
		panel_1.add(btn[22]);

		btn[23]= new JButton();
		btn[23].setBackground(Color.WHITE);
		panel_1.add(btn[23]);

		btn[24] = new JButton();
		btn[24].setBackground(Color.WHITE);
		panel_1.add(btn[24]);

		btn[25] = new JButton();
		btn[25].setBackground(Color.WHITE);
		panel_1.add(btn[25]);

		btn[26] = new JButton();
		btn[26].setBackground(Color.WHITE);
		panel_1.add(btn[26]);

		btn[27] = new JButton();
		btn[27].setBackground(Color.WHITE);
		panel_1.add(btn[27]);

		btn[28] = new JButton();
		btn[28].setBackground(Color.WHITE);
		panel_1.add(btn[28]);

		btn[29] = new JButton();
		btn[29].setBackground(Color.WHITE);
		panel_1.add(btn[29]);

		btn[30]= new JButton();
		btn[30].setBackground(Color.WHITE);
		panel_1.add(btn[30]);

		btn[31] = new JButton();
		btn[31].setBackground(Color.WHITE);
		panel_1.add(btn[31]);

		btn[32] = new JButton();
		btn[32].setBackground(Color.WHITE);
		panel_1.add(btn[32]);

		btn[33] = new JButton();
		btn[33].setBackground(Color.WHITE);
		panel_1.add(btn[33]);

		btn[34] = new JButton();
		btn[34].setBackground(Color.WHITE);
		panel_1.add(btn[34]);

		btn[35] = new JButton();
		btn[35].setBackground(Color.WHITE);
		panel_1.add(btn[35]);

		btn[36] = new JButton();
		btn[36].setBackground(Color.WHITE);
		panel_1.add(btn[36]);

		btn[37] = new JButton();
		btn[37].setBackground(Color.WHITE);
		panel_1.add(btn[37]);

		btn[38] = new JButton();
		btn[38].setBackground(Color.WHITE);
		panel_1.add(btn[38]);

		btn[39]= new JButton();
		btn[39].setBackground(Color.WHITE);
		panel_1.add(btn[39]);

		btn[40] = new JButton();
		btn[40].setBackground(Color.WHITE);
		panel_1.add(btn[40]);

		btn[41] = new JButton();
		btn[41].setBackground(Color.WHITE);
		panel_1.add(btn[41]);

		adddefLis();


		int beginwd = getweekday(yearinfo,monthinfo+1,1);
		//System.out.println(yearinfo + "\t\t" + monthinfo);
		for(int i=0;i<getmonthday(monthinfo + 1);i++){
			btn[beginwd+i].setText(i+1+"");
			//btn[beginwd+i].setText("\n #");
		}
		//		ShowMessage showmess = new ShowMessage();
		for(int i=0;i<42;i++){
			if(btn[i].getText() == ""){
				btn[i].removeMouseListener(def[i]);
			}
		}
		settips();

	}
	
	/**
	 * 给每个按钮添加一个默认的监听器
	 */
	public void adddefLis(){

		for(int i=0;i<42;i++){
			def[i] = new defaultMListener();
			btn[i].addMouseListener(def[i]);
			//System.out.println(onClickedbtn.getText());
		}
	}
	
	/**
	 * 给每个有日程日子着色提示
	 */

	@SuppressWarnings("resource")
	public void settips(){
		System.out.println("settips has been invoked");
		int selectedy = (int) year.getSelectedItem();
		int selectedm = (int) month.getSelectedItem();
		//int selectedd = Integer.parseInt(onclickbtn);
		String date = selectedy + "," + selectedm + ","/*  +selectedd +":"*/;
		String line = "";

		try {
			BufferedReader bw;
			//setUser(user);
			bw = new BufferedReader(new FileReader("/Users/Suckysherry/Code/workspace/VCampus/VCampus.client/src/res/Schedule.txt"));
			StringBuffer sch_buf = new StringBuffer();
			while((line = bw.readLine()) != null)
			{
				sch_buf.append(line);
			}
			String[] sch = new String[3];
			String input = sch_buf.toString();
			String temp[] = input.split("@@");

			for(int i =0;i<temp.length;i++){
				System.out.println("for loop");	
				if(temp[i].indexOf(date) != -1){
					sch = temp[i].split(":%%");
					//System.out.println(sch[0]);
					String[] date1 = new String[3];
					date1 = sch[0].split(",");
					//System.out.println(date1[2]);
					//System.out.println(year.getSelectedItem() + date1[0]);
//					System.out.println(month.getSelectedItem() + date1[1]);
					for(int j=0;j<42;j++){
						boolean flag1 = year.getSelectedItem().toString().equals(date1[0])&& month.getSelectedItem().toString().equals(date1[1]);
						//System.out.println(flag1);
						if(flag1){
							if(btn[j].getText().equals(date1[2])){
								btn[j].setBackground(new Color(220,255, 255));
								System.out.println("setbackground blue");
							}
							//System.out.println("Correct year and Month");
							
						}
					}
				}
				//				else sch[0] = "null";	
			}
			//System.out.println(sch[1]);
			//			if(year.getSelectedItem() == sch[0]&& month.getSelectedItem() == sch[1]){
			//				for(int i=0;i<42;i++){
			//					if(btn[i].getText() == sch[2])
			//						btn[i].setBackground(Color.RED);
			//				}
			//			}
			//			JOptionPane.showMessageDialog(null, "这是今天的日程：" + sch[1]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * 获得被点击的日子
	 * @return被点击的按钮上的text
	 */
	public String getOnClickedBtn(){

		System.out.println(onclickbtn);
		return onclickbtn;	
	}
	/**
	 * 默认监听器
	 * @author 靖芳
	 *
	 */

	public class defaultMListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("listener has been invoked");
			// TODO Auto-generated method stub		
			if(e.getSource().equals(btn[0])){
				onclickbtn = btn[0].getText();
			}else if(e.getSource().equals(btn[1])){
				onclickbtn = btn[1].getText();
			}else if(e.getSource().equals(btn[2])){
				onclickbtn = btn[2].getText();
			}else if(e.getSource().equals(btn[3])){
				onclickbtn = btn[3].getText();
			}else if(e.getSource().equals(btn[4])){
				onclickbtn = btn[4].getText();
			}else if(e.getSource().equals(btn[5])){
				onclickbtn = btn[5].getText();
			}else if(e.getSource().equals(btn[6])){
				onclickbtn = btn[6].getText();
			}else if(e.getSource().equals(btn[7])){
				onclickbtn = btn[7].getText();
			}else if(e.getSource().equals(btn[8])){
				onclickbtn = btn[8].getText();
			}else if(e.getSource().equals(btn[9])){
				onclickbtn = btn[9].getText();
			}else if(e.getSource().equals(btn[10])){
				onclickbtn = btn[10].getText();
			}else if(e.getSource().equals(btn[11])){
				onclickbtn = btn[11].getText();
			}else if(e.getSource().equals(btn[12])){
				onclickbtn = btn[12].getText();
			}else if(e.getSource().equals(btn[13])){
				onclickbtn = btn[13].getText();
			}else if(e.getSource().equals(btn[14])){
				onclickbtn = btn[14].getText();
			}else if(e.getSource().equals(btn[15])){
				onclickbtn = btn[15].getText();
			}else if(e.getSource().equals(btn[16])){
				onclickbtn = btn[16].getText();
			}else if(e.getSource().equals(btn[17])){
				onclickbtn = btn[17].getText();
			}else if(e.getSource().equals(btn[18])){
				onclickbtn = btn[18].getText();
			}else if(e.getSource().equals(btn[19])){
				onclickbtn = btn[19].getText();
			}else if(e.getSource().equals(btn[20])){
				onclickbtn = btn[20].getText();
			}else if(e.getSource().equals(btn[21])){
				onclickbtn = btn[21].getText();
			}else if(e.getSource().equals(btn[22])){
				onclickbtn = btn[22].getText();
			}else if(e.getSource().equals(btn[23])){
				onclickbtn = btn[23].getText();
			}else if(e.getSource().equals(btn[24])){
				onclickbtn = btn[24].getText();
			}else if(e.getSource().equals(btn[25])){
				onclickbtn = btn[25].getText();
			}else if(e.getSource().equals(btn[26])){
				onclickbtn = btn[26].getText();
			}else if(e.getSource().equals(btn[27])){
				onclickbtn = btn[27].getText();
			}else if(e.getSource().equals(btn[28])){
				onclickbtn = btn[28].getText();
			}else if(e.getSource().equals(btn[29])){
				onclickbtn = btn[29].getText();
			}else if(e.getSource().equals(btn[30])){
				onclickbtn = btn[30].getText();
			}else if(e.getSource().equals(btn[31])){
				onclickbtn = btn[31].getText();
			}else if(e.getSource().equals(btn[32])){
				onclickbtn = btn[32].getText();
			}else if(e.getSource().equals(btn[33])){
				onclickbtn = btn[33].getText();
			}else if(e.getSource().equals(btn[34])){
				onclickbtn = btn[34].getText();
			}else if(e.getSource().equals(btn[35])){
				onclickbtn = btn[35].getText();
			}else if(e.getSource().equals(btn[36])){
				onclickbtn = btn[36].getText();
			}else if(e.getSource().equals(btn[37])){
				onclickbtn = btn[37].getText();
			}else if(e.getSource().equals(btn[38])){
				onclickbtn = btn[38].getText();
			}else if(e.getSource().equals(btn[39])){
				onclickbtn = btn[39].getText();
			}else if(e.getSource().equals(btn[40])){
				onclickbtn = btn[40].getText();
			}else if(e.getSource().equals(btn[41])){
				onclickbtn = btn[41].getText();
			}else onclickbtn = "0";

			if(e.getButton() == MouseEvent.BUTTON1){
				try {
					String line = "";
					int selectedy = (int) year.getSelectedItem();
					int selectedm = (int) month.getSelectedItem();
					String selectedd = onclickbtn;
					//if(!onclickbtn.equals(null)){
					//int selectedd = Integer.parseInt(onclickbtn);
					//}else 
					//onclickbtn = "0";
					System.out.println(onclickbtn);
					String date = selectedy + "," + selectedm + "," + selectedd +":";
					//System.out.println(date);
					@SuppressWarnings("resource")
					BufferedReader bw = new BufferedReader(new FileReader("/Users/Suckysherry/Code/workspace/VCampus/VCampus.client/src/res/Schedule.txt"));
					StringBuffer sch_buf = new StringBuffer();
					while((line = bw.readLine()) != null)
					{
						sch_buf.append(line);
					}
					String[] sch = new String[2];
					String input = sch_buf.toString();
					String temp[] = input.split("@@");
					//StringBuffer datesch = null;
					String schedule = "";
					for(int i =0;i<temp.length;i++){
						//System.out.println(temp[i].indexOf(date));	
						if(temp[i].indexOf(date) != -1){
							sch = temp[i].split("%%");
							//sch[1] = sch[1];
							System.out.println(sch[1]);
							schedule = schedule + "\r\n" + "*" + sch[1];
							//datesch.append(sch[1]);
							//datesch.append("\r\n");
						}
						//						else{
						//							sch[1] = "今日无日程";
						//						}
					}
					if(schedule.equals("")){
						schedule = "今日无日程！";
					}
					//System.out.println(sch[1]);
					//String schedule = datesch.toString();
					JOptionPane.showMessageDialog(null, "这是今天的日程：" + schedule);
					//schedule =null;
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//settips();
			}

			if(e.getButton() == MouseEvent.BUTTON3){

				System.out.println(onclickbtn);
				//JOptionPane.showMessageDialog(null, "鼠标右击");
				yearinfo = (int) year.getSelectedItem();
				monthinfo =(int) month.getSelectedItem();

				int thisday = Integer.parseInt(onclickbtn);
				System.out.println("开始执行这行代码");
				AddSchedule newsch = new AddSchedule(jframe,frame,yearinfo,monthinfo,thisday);
				//newsch.setUser(user);
				jframe.setContentPane(newsch);//test
				System.out.println("这行代码执行结束");
				//btn[30].setBackground(new Color(255, 0, 0));
				//settips();
			}
			if(e.getClickCount() == 2){
				JOptionPane.showMessageDialog(null, "鼠标双击");
			}
			//settips();

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	

	/*public class ShowMessage implements ActionListener{														//显示日程

		@Override
		public void actionPerformed(ActionEvent e) {
			try {

				String line = "";
				int selectedd = 0;
				int selectedy = (int) year.getSelectedItem();
				int selectedm = (int) month.getSelectedItem();
				//if(!onclickbtn.equals(null)){
					selectedd = Integer.parseInt(onclickbtn);
			//}else 
			//onclickbtn = "0";
				System.out.println(onclickbtn);
				String date = selectedy + "," + selectedm + "," + selectedd +":";
				//System.out.println(date);
				BufferedReader bw = new BufferedReader(new FileReader("D:/schedule.txt"));
				StringBuffer sch_buf = new StringBuffer();
				while((line = bw.readLine()) != null)
				{
					sch_buf.append(line);
				}
				String[] sch = new String[2];
				String input = sch_buf.toString();
				String temp[] = input.split("@@");

				for(int i =0;i<temp.length;i++){
					//System.out.println(temp[i].indexOf(date));	
					if(temp[i].indexOf(date) != -1){
						sch = temp[i].split("%%");
						System.out.println(sch[1]);
					}else sch[1] = "今日无日程";				}
				//System.out.println(sch[1]);
				JOptionPane.showMessageDialog(null, "这是今天的日程：" + sch[1]);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}*/

	/*public class MyMouseListener implements MouseListener{						//右键监听器

		@Override
		public void mouseClicked(MouseEvent e) {
			/*for(int i = 0;i<42;i++){
				btn[i].setBackground(Color.WHITE);
			}
			// TODO Auto-generated method stub
			if(e.getButton() == MouseEvent.BUTTON3){
				System.out.println(onclickbtn);
				//JOptionPane.showMessageDialog(null, "鼠标右击");
				yearinfo = (int) year.getSelectedItem();
				monthinfo =(int) month.getSelectedItem();

				new AddSchedule(yearinfo,monthinfo,Integer.parseInt(onclickbtn));								//test
				//btn[30].setBackground(new Color(255, 0, 0));
			}
			if(e.getClickCount() == 2){
				JOptionPane.showMessageDialog(null, "鼠标双击");
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}*/

}
