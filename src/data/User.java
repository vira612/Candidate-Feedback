package data;

public class User {
	
	Integer user_id;
	String name;
	String password;
	boolean admin;
	
	public User(Integer user_id,String name,String password,boolean admin ) {
		
		this.user_id=user_id;
		this.name=name;
		this.password=password;
		this.admin=admin;
		
	}
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	

}
