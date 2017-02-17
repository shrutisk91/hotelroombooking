package models;

import java.sql.Date;

/**
 * Model for User Choice with getters and setters
 * @author shruti
 *
 */
public class UserChoice {
	private int uid;
	private String location;
	private String room_type;
	private Date from_date;
	private Date to_date;
	private int hid;
	private int roomsnumber;
	
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getFrom_date() {
		return from_date;
	}
	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}
	public Date getTo_date() {
		return to_date;
	}
	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public int getroomsnumber() {
		return roomsnumber;
	}
	public void setroomsnumber(int roomsnumber) {
		this.roomsnumber = roomsnumber;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}

}
