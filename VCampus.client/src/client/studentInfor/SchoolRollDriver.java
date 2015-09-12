/**SchoolRollDriver是一个驱动类，该类的作用是判断用户类型然后打开对应于该用户的窗口
 * @author 靖芳
 */
package client.studentInfor;



import java.io.IOException;

import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conn.common.*;


public class SchoolRollDriver {
	User user;
	Student student = new Student();
	Teacher teacher = new Teacher();
	private Connection con;
	
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

	SchoolRollDriver(){}
	public SchoolRollDriver(User MyUser) throws ClassNotFoundException, IOException, SQLException{
		user = MyUser;	
		//this.user = new User("09013132","0901313406","teacher","�ƾ���","213130866","��","090131","1995,5,20","��������");
	
		
		
	}
	public void judge() throws ClassNotFoundException, IOException, SQLException{
		
		if(user.getURole().equalsIgnoreCase("student")){
			String sql = "SELECT * From STUDENT WHERE ID = '" + user.getUID() + "'";
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
					System.out.println(result.getString(4)+result.getString(2));
					student.setUName(result.getString(4));
					student.setUBirthday(result.getString(8));
					student.setUCard(result.getString(1));
					student.setUClass(result.getString(6));
					student.setUHometown(result.getString(7));
					student.setUID(result.getString(2));
					student.setURole(result.getString(3));
					student.setUSex(result.getString(6));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			SchoolRollStudents SRS = new SchoolRollStudents(student,false);
			System.out.println(student.getUName());
			SRS.setPanel();
			SRS.setVisible(true);
			}else if(user.getURole().equalsIgnoreCase("teacher")||user.getURole().equalsIgnoreCase("admin")){
			String sql = "SELECT * From TEACHER where ID = '" + user.getUID() + "'";
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
			System.out.println(result);
			try {
				while(result.next()){
					teacher.setUName(result.getString("Name"));
					//teacher.setUBirthday(result.getString(1));
					teacher.setUCard(result.getString("ID"));
					teacher.setUClass(result.getString("class"));
					//teacher.setUHometown(result.getString(4));
					teacher.setUID(result.getString("Num"));
					teacher.setURole(result.getString("role"));
					teacher.setUSex(result.getString("sex"));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			SchoolRollTeachers SCT = new SchoolRollTeachers(teacher,true);
			SCT.setVisible(true);
			SCT.setPanel();

		}
	}
	/*public Message getMessage() throws IOException, ClassNotFoundException{
		Message request = new Message(user);
		Socket socket = new Socket("localhost",1596);
		ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

		os.writeObject(request);
		Message mess = new Message();
		ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		mess = (Message) is.readObject();
		return mess;
	}
	*/
//public static void main(String args[]) throws IOException, SQLException{
//	
//	try {
//		User user = new User("t1","t1","teacher");
//		SchoolRollDriver driver = new SchoolRollDriver(user);
//		driver.judge();
//	} catch (ClassNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	//driver.judge();
//}

}
