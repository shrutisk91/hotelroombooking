package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Hotel;

/**
 * Handler to Hotel in MySQL
 * @author shruti
 *
 */
public class HotelCRUD {

	private Connection connection;
	private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	private String username = "fpuser";
	private String password = "510";

	/**
	 * Creates a row for hotel
	 * @param hotel
	 * 
	 */
	public Hotel createHotel(Hotel hotel){
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		String query = "INSERT INTO shru_hotel (hotel_name, location,stars) VALUES (?, ?, ?) ;";

		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, hotel.getHotelname());
			statement.setString(2, hotel.getLocation());
			statement.setInt(3, hotel.getStars());
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
			}
		} catch (SQLException e) {
			hotel = null;
			System.out.println("Error Creating because of: " + e);
		}

		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		return hotel;
	}
	
	/**
	 * Updates row in hotel 
	 * @param hotel
	 * 
	 */
	public Hotel updateHotel(Hotel hotel){
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		String query = "update shru_hotel set stars=? where hotel_name=?; ";

		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			statement.setInt(1, hotel.getStars());
			statement.setString(2, hotel.getHotelname());
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
			}
		} catch (SQLException e) {
			hotel = null;
			System.out.println("Error Creating because of: " + e);
		}

		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		return hotel;
	}
	
	/**
	 * Deletes row in hotel
	 * @param hotel
	 * 
	 * @throws SQLException
	 */
	public Hotel deleteHotel(Hotel hotel) throws SQLException{
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		String query = "delete from shru_hotel where hotel_name=?; ";

		 PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, hotel.getHotelname());
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
		return hotel;
	}
	 
	
	public ObservableList<String> getuniquehotels(){
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		ObservableList<String> hotelist = FXCollections.observableArrayList();
		String query = "Select distinct hotel_name from shru_hotel;";
		try { 
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				hotelist.add(resultSet.getString("hotel_name"));
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
		return hotelist;
	}
	
	public int getHotelid(Hotel hotel){
		int hotelid=0;
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		ObservableList<Hotel> list = FXCollections.observableArrayList();
		String query = "select * from shru_hotel where hotel_name=?;";
		
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			statement.setString(1, hotel.getHotelname());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				hotelid = resultSet.getInt("hid");
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
		return hotelid;
	}
	
	public String getHotelName(int hid){
		String hotelname = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		ObservableList<Hotel> list = FXCollections.observableArrayList();
		String query = "select * from shru_hotel where hid=?;";
		
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			statement.setInt(1, hid);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				hotelname = resultSet.getString("hotel_name");
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
		return hotelname;
	}
	
	public ObservableList<Hotel> getHotelList(String location) {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		ObservableList<Hotel> list = FXCollections.observableArrayList();
		String query = "select a.hid,a.location, a.stars, a.hotel_name, count(*) as noofrooms from "
				+ "(select * from shru_hotel where location= ? ) as a natural join "
				+ "(select * from shru_rooms where availability=1) as b group by a.hid;";
		
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			statement.setString(1, location);
			ResultSet resultSet = statement.executeQuery();
			System.out.println(statement + "!!!!!!!!!!!!!!!!!!");
			while (resultSet.next()) {
				Hotel hotel = new Hotel();
				hotel.setHid(resultSet.getInt("hid"));
				hotel.setHotelname(resultSet.getString("hotel_name"));
				hotel.setLocation(resultSet.getString("location"));
				hotel.setNoofroomsavailable(resultSet.getInt("noofrooms"));
				hotel.setStars(resultSet.getInt("stars"));
				list.add(hotel);
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
		return list;
	}
	
	public ObservableList<Hotel> getAllHotelList() {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		ObservableList<Hotel> list = FXCollections.observableArrayList();
		String query = "select a.hid,a.location, a.stars, a.hotel_name, count(*) as noofrooms from "
				+ "shru_hotel as a natural join "
				+ "shru_rooms as b group by a.hid;";
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Hotel hotel = new Hotel();
				hotel.setHid(resultSet.getInt("hid"));
				hotel.setHotelname(resultSet.getString("hotel_name"));
				hotel.setLocation(resultSet.getString("location"));
				hotel.setNoofroomsavailable(resultSet.getInt("noofrooms"));
				hotel.setStars(resultSet.getInt("stars"));
				list.add(hotel);
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
		return list;
	}

}
