package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.User;

/**
 * Handler for User table in MySQL
 * @author shruti
 *
 */
public class UserCRUD {

	private Connection connection;
	private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	private String username = "fpuser";
	private String password = "510";

	/**
	 * Adds a user row
	 * @param user
	 * 
	 */
	public User create(User user) {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		String query = "INSERT INTO shru_user (username, password, location,isloggedin) VALUES (?, ?, ?,0) ;";

		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getLocation());
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
			}
		} catch (SQLException e) {
			user = null;
			System.out.println("Error Creating because of: " + e);
		}

		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		return user;
	}
	
	/**
	 * Sets the user to logged in
	 * @param user
	 * 
	 */
	public User setLoggedInUser(User user){
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		String query = "update shru_user set isloggedin=1 where username=?; ";

		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, user.getUsername());
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
			}
		} catch (SQLException e) {
			user = null;
			System.out.println("Error Creating because of: " + e);
		}

		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		return user;
	}
	
	/**
	 * Sets a user to logged off
	 */
	public void setLoggedOffUser(){
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		String query = "update shru_user set isloggedin=0; ";

		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
			}
		} catch (SQLException e) {
			System.out.println("Error Creating because of: " + e);
		}

		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
	}
	
	/**
	 * Gets a logged in user
	 * 
	 */
	public User getLoggedinUser() {

		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}

		User lu = new User();
		String query = "Select * from shru_user where isloggedin=1; ";

		try {

			connection.setAutoCommit(true);
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lu.setUserid(resultSet.getInt("userid"));
				lu.setUsername(resultSet.getString("username"));
				lu.setPassword(resultSet.getString("password"));
				lu.setLocation(resultSet.getString("location"));
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
		return lu;
	}


	/**
	 * Validates the user with username and password
	 * @param loginUser
	 * 
	 */
	public User CustomerValidate(User loginUser) {

		String uname = loginUser.getUsername();
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}

		User lu = new User();
		String query = "Select * from shru_user where username=? and " + "password=? ";

		try {

			connection.setAutoCommit(true);
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, loginUser.getUsername());
			statement.setString(2, loginUser.getPassword());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lu.setUserid(resultSet.getInt("userid"));
				lu.setUsername(resultSet.getString("username"));
				lu.setPassword(resultSet.getString("password"));
				lu.setLocation(resultSet.getString("location"));
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
		return lu;
	}
}
