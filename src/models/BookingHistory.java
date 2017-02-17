package models;

import java.sql.Date;

/**
 * Model for Booking History with getters and setters
 * @author shruti
 *
 */
public class BookingHistory {
	
	private int rbid;
	private int hid;
	private int rid;
	private int uid;
	private Date booked_from_date;
	private Date booked_to_date;
	private Double room_fare;
	public int getRbid() {
		return rbid;
	}
	public void setRbid(int rbid) {
		this.rbid = rbid;
	}
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public Date getBooked_from_date() {
		return booked_from_date;
	}
	public void setBooked_from_date(Date booked_from_date) {
		this.booked_from_date = booked_from_date;
	}
	public Date getBooked_to_date() {
		return booked_to_date;
	}
	public void setBooked_to_date(Date booked_to_date) {
		this.booked_to_date = booked_to_date;
	}
	public Double getRoom_fare() {
		return room_fare;
	}
	public void setRoom_fare(Double room_fare) {
		this.room_fare = room_fare;
	}

}
