package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Context;
import models.Hotel;
import models.Room;

/**
 * Handler for Room in MySQL
 * @author shrut
 *
 */
public class RoomCRUD {
	private Connection connection;
	private String url = "jdbc:mysql://www.papademas.net:3306/dbfp?zeroDateTimeBehavior=convertToNull";
	private String username = "fpuser";
	private String password = "510";

	/**
	 * Creates room row
	 * @param room
	 * 
	 */
	public Room createRoom(Room room) {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		String query = "INSERT INTO shru_rooms VALUES (default,?, ?, 1, ?, '0000-00-00', '0000-00-00', null) ;";

		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			statement.setInt(1, room.getHid());
			statement.setString(2, room.getRoom_type());
			statement.setDouble(3, room.getRate_per_day());
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
			}
		} catch (SQLException e) {
			room = null;
			System.out.println("Error Creating because of: " + e);
		}

		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		return room;
	}

	/**
	 * Updates row in room with rate
	 * @param room
	 * 
	 */
	public Room updateRoom(Room room) {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		String query = "update shru_rooms set availability=1 where RID=?; ";

		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			statement.setInt(1, room.getRid());
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
			}
		} catch (SQLException e) {
			room = null;
			System.out.println("Error Creating because of: " + e);
		}

		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		return room;
	}
	
	/**
	 * Updates all rooms which are booked
	 * @param room
	 * 
	 */
	public Room updateRoomAvail(Room room) {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		String query = "update shru_rooms set availability=1, userid=null where booked_to_date > NOW() and HID=?; ";

		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			statement.setInt(1, room.getHid());
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
			}
		} catch (SQLException e) {
			room = null;
			System.out.println("Error Creating because of: " + e);
		}

		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		return room;
	}

	/**
	 * Updates booking for a room
	 * @param room
	 * 
	 */
	public Room updateBooking(Room room) {
		//List<Room> roomlist = new ArrayList<>();
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		String query = "update shru_rooms set availability=0, booked_from_date=?, "
				+ "booked_to_date=?, userid=? where room_type=? and hid=? limit ?; ";

		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			statement.setDate(1, room.getBooked_from_date());
			statement.setDate(2, room.getBooked_to_date());
			statement.setInt(3, Context.getInstance().currentUserchoice().getUid());
			statement.setString(4, room.getRoom_type());
			statement.setInt(5, room.getHid());
			statement.setInt(6, room.getNoofrooms());
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				
			}
		} catch (SQLException e) {
			room = null;
			System.out.println("Error Creating because of: " + e);
		}

		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		return room;
	}

	/**
	 * Deletes room row
	 * @param room
	 * 
	 * @throws SQLException
	 */
	public Room deleteRoom(Room room) throws SQLException {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		String query = "delete from shru_rooms where HID=? and room_type=?; ";

		PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		statement.setInt(1, room.getHid());
		statement.setString(2, room.getRoom_type());
		statement.executeUpdate();
		ResultSet resultSet = statement.getGeneratedKeys();
		if (resultSet.next()) {
		}

		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		return room;
	}

	/**
	 * Gets all rooms
	 * @param hid
	 * 
	 */
	public ObservableList<Room> getRoomList(int hid) {

		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		ObservableList<Room> roomlist = FXCollections.observableArrayList();
		String query = "select hid,room_type, rate_per_day, count(*) as noofrooms from shru_rooms where availability=1 and hid=? and room_type='deluxe' "
				+ "union select hid,room_type, rate_per_day, count(*) as noofrooms from shru_rooms where availability=1 and hid=? and room_type='standard';";

		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			statement.setInt(1, hid);
			statement.setInt(2, hid);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Room room = new Room();
				room.setHid(resultSet.getInt("hid"));
				room.setRoom_type(resultSet.getString("room_type"));
				room.setRate_per_day(resultSet.getDouble("rate_per_day"));
				room.setNoofrooms(resultSet.getInt("noofrooms"));
				roomlist.add(room);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		return roomlist;
	}

	/**
	 * Gets all rooms
	 * 
	 */
	public ObservableList<Room> getAllRoomList() {
		HotelCRUD hcrud = new HotelCRUD();
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		ObservableList<Room> roomlist = FXCollections.observableArrayList();
		String query = "select * from shru_rooms;";

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Room room = new Room();
				room.setRid(resultSet.getInt("rid"));
				room.setHid(resultSet.getInt("hid"));
				room.setAvailability(resultSet.getInt("availability"));
				room.setRoom_type(resultSet.getString("room_type"));
				room.setRate_per_day(resultSet.getDouble("rate_per_day"));
				room.setBooked_from_date(resultSet.getDate("booked_from_date"));
				room.setBooked_to_date(resultSet.getDate("booked_to_date"));
				room.setHotel_name(hcrud.getHotelName(room.getHid()));
				roomlist.add(room);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		return roomlist;
	}
	
	/**
	 * Gets room type for a room
	 * @param rid
	 * 
	 */
	public String getRoomType(int rid){
		String roomtype = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		String query = "select * from shru_rooms where rid=?;";
		
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			statement.setInt(1, rid);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				roomtype = resultSet.getString("room_type");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		return roomtype;
	}
	
	/**
	 * Gets all rooms for a particular user
	 * 
	 */
	public ArrayList<Room> getRoomsforUser(){
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		ArrayList<Room> roomlist = new ArrayList<>();
		String query = "select * from shru_rooms where userid=?;";

		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			statement.setInt(1, Context.getInstance().currentUserchoice().getUid());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Room room = new Room();
				room.setRid(resultSet.getInt("rid"));
				room.setHid(resultSet.getInt("hid"));
				room.setRate_per_day(resultSet.getDouble("rate_per_day"));
				room.setBooked_from_date(resultSet.getDate("booked_from_date"));
				room.setBooked_to_date(resultSet.getDate("booked_to_date"));
				roomlist.add(room);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		return roomlist;
	}

}
