package conn.common;

public class Course {
	
	public String cName;
	public String cNumber;//限制选该课的人数
	public String cID;
	public String cMajor;
	
	public String getCName() {
		return cName;
	}
	
	public void setCName(String cName) {
		this.cName = cName;
	}
	
	public String getCNumber() {
		return cNumber;
	}
	
	public void setCNumber(String cNumber) {
		this.cNumber = cNumber;
	}
	
	public String getCID() {
		return cID;
	}
	
	public void setCID(String cID) {
		this.cID = cID;
	}
	
	public String getCMajor() {
		return cMajor;
	}
	
	public void setCMajor(String cMajor) {
		this.cMajor = cMajor;
	}

}
