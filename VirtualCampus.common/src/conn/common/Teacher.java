package conn.common;

import java.io.Serializable;

public class Teacher implements Serializable {
	
	protected String uCard;
	protected String uRole;
	protected String uID;
	protected String uName;
	protected String uSex;
	protected String uClass;
	
	public Teacher(String name){
		this.uName = name;
	}
	
	public Teacher() {}
	
	public String getUCard() {
		return uCard;
	}
	
	public  void setUCard(String uCard) {
		this.uCard = uCard;
	}
	
	public String getURole() {
		return uRole;
	}
	
	public  void setURole(String uRole) {
		this.uRole = uRole;
	}
	
	public String getUID() {
		return uID;
	}
	
	public  void setUID(String uID) {
		this.uID = uID;
	}
	
	
	public String getUName() {
		return uName;
	}
	
	public  void setUName(String uName) {
		this.uName = uName;
	}
	
	public String getUSex() {
		return uSex;
	}
	
	public  void setUSex(String uSex) {
		this.uSex = uSex;
	}
	
	public String getUClass() {
		return uClass;
	}
	
	public  void setUClass(String uClass) {
		this.uClass = uClass;
	}
	
}
