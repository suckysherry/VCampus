package conn.common;

import java.io.Serializable;

/**
 * 消息类
 * @author daisiqi
 *
 */
public class Message implements Serializable {
	protected static final long serialVersionUID = -2034483694793482528L;
	protected String type; // 消息类型
	protected Client client; // 客户端身份（避免发错数据）
	protected Object data;
	protected Boolean state;
	
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	protected String userName;
	protected String password;
	protected String sql;

	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	


	
}
