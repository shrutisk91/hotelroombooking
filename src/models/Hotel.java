package models;

/**
 * Model for Hotel with getters and setters
 * @author shruti
 *
 */
public class Hotel {
	private int hid;
	private String hotelname;
	private int stars;
	private String location;
	private int noofroomsavailable;
	
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public String getHotelname() {
		return hotelname;
	}
	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public int getNoofroomsavailable() {
		return noofroomsavailable;
	}
	public void setNoofroomsavailable(int noofroomsavailable) {
		this.noofroomsavailable = noofroomsavailable;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
