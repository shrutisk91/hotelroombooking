package models;

/**
 * Model for User with getters and setters
 * @author shruti
 *
 */
public class User {
	private int userid;
	private String username;
	private String password;
	private String location;
	private int isloggedin;
	
	public User(int userid, String username, String password, String location, int isloggedin){
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.location = location;
		this.isloggedin = isloggedin;
	}
	
	public User(){
		this.userid=0;
		this.username=null;
		this.password=null;
		this.location=null;
		this.isloggedin = 0;
	}
	
	public int getUserid(){
		return userid;
	}
	
	public void setUserid(int userid){
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getIsloggedin() {
		return isloggedin;
	}

	public void setIsloggedin(int isloggedin) {
		this.isloggedin = isloggedin;
	}

}
