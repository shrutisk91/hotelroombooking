package models;

import java.sql.Date;

/**
 * Model for Room with getters and setters
 * @author shruti
 *
 */
public class Room {
	private int rid;
	private int hid;
	private String room_type;
	private int availability;
	private double rate_per_day;
	private Date booked_from_date;
	private Date booked_to_date;
	private int noofrooms;
	private String hotel_name;
	
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	public int getAvailability() {
		return availability;
	}
	public void setAvailability(int availability) {
		this.availability = availability;
	}
	public double getRate_per_day() {
		return rate_per_day;
	}
	public void setRate_per_day(double rate_per_day) {
		this.rate_per_day = rate_per_day;
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
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public int getNoofrooms() {
		return noofrooms;
	}
	public void setNoofrooms(int noofrooms) {
		this.noofrooms = noofrooms;
	}
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
}
