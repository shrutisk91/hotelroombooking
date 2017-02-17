package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.BookingHistory;
import models.Context;
import models.Room;

/**
 * Handler for Booking History in MySQL
 * @author shruti
 *
 */
public class BookingHistoryCRUD {
	private Connection connection;
	private String url = "jdbc:mysql://www.papademas.net:3306/dbfp?zeroDateTimeBehavior=convertToNull";
	private String username = "fpuser";
	private String password = "510";
	
	/**
	 * Creates a row in table
	 * @param bh
	 * 
	 */
	public BookingHistory createHistory(BookingHistory bh){
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		String query = "INSERT INTO shru_room_booking_history VALUES (default, ?, ?, ?, ?,?,?) ;";

		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			statement.setInt(1, bh.getHid());
			statement.setInt(2, bh.getRid());
			statement.setInt(3, Context.getInstance().currentUserchoice().getUid());
			statement.setDate(4, bh.getBooked_from_date());
			statement.setDate(5, bh.getBooked_to_date());
			statement.setDouble(6, bh.getRoom_fare());
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
			}
		} catch (SQLException e) {
			bh = null;
			System.out.println("Error Creating because of: " + e);
		}

		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		return bh;
	}
	
	/**
	 * Deletes row in table
	 * @param bh
	 * 
	 * @throws SQLException
	 */
	public BookingHistory delete(BookingHistory bh) throws SQLException{
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		String query = "delete from shru_room_booking_history where HID=? and RID=?; ";

		 PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, bh.getHid());
			statement.setInt(2, bh.getRid());
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
		return bh;
	}
	
	/**
	 * Selects all rows from table
	 * 
	 */
	public ObservableList<BookingHistory> getAllHistoryList() {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		ObservableList<BookingHistory> historylist = FXCollections.observableArrayList();
		String query = "select * from shru_room_booking_history where uid=?;";
		
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){	
			statement.setInt(1, Context.getInstance().currentUserchoice().getUid());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				BookingHistory bh = new BookingHistory();
				bh.setRbid(resultSet.getInt("rbid"));
				bh.setHid(resultSet.getInt("hid"));
				bh.setRid(resultSet.getInt("rid"));
				bh.setUid(resultSet.getInt("uid"));
				bh.setBooked_from_date(resultSet.getDate("booked_from_date"));
				bh.setBooked_to_date(resultSet.getDate("booked_to_date"));
				bh.setRoom_fare(resultSet.getDouble("room_fare"));
				historylist.add(bh);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return historylist;
	}

}
