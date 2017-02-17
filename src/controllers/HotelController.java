package controllers;

import java.io.IOException;

import application.Main;
import dao.HotelCRUD;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.Hotel;
/**
 * Controller for redirecting the admin to hotel activities
 * @author shruti
 *
 */
public class HotelController{
	
	@FXML
	private TextField hotel_name;
	
	@FXML
	private TextField hotel_location;
	
	@FXML
	private TextField hotel_stars;
	
	/**
	 * Redirects to add hotel
	 */
	@FXML
	public void addhotelactivity(){
		AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(Main.class.getResource("/views/AddHotel.fxml"));
			Scene scene = new Scene(root);
            Main.stage.setScene(scene);  
            Main.stage.setTitle("Add Hotel");
            Main.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Redirects to update hotel 
	 */
	@FXML
	public void updatehotelactivity(){
		AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(Main.class.getResource("/views/UpdateHotel.fxml"));
			Scene scene = new Scene(root);
            Main.stage.setScene(scene);  
            Main.stage.setTitle("Update an Hotel");
            Main.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Redirects to view Hotel
	 */
	@FXML
	public void viewhotelactivity(){
		AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(Main.class.getResource("/views/ViewHotel.fxml"));
			Scene scene = new Scene(root);
            Main.stage.setScene(scene);  
            Main.stage.setTitle("View Hotels");
            Main.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Redirects to Delete hotel
	 */
	@FXML
	public void deletehotelactivity(){
		AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(Main.class.getResource("/views/DeleteHotel.fxml"));
			Scene scene = new Scene(root);
            Main.stage.setScene(scene);  
            Main.stage.setTitle("Delete a Hotel");
            Main.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Redirects to Add Hotel
	 */
	@FXML
	public void addhotel(){
		Hotel hotel = new Hotel();
		hotel.setHotelname(hotel_name.getText().toLowerCase());
		hotel.setLocation(hotel_location.getText().toLowerCase());
		hotel.setStars(Integer.parseInt(hotel_stars.getText().toString()));
		
		HotelCRUD crud = new HotelCRUD();
		Hotel addedhotel = crud.createHotel(hotel);
		System.out.println("New Hotel is added");
		
		AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/AdminPage.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setTitle("Main Menu");
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Goes back to admin menu page
	 */
	@FXML
	public void goback(){
		AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/AdminPage.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setTitle("Main Menu");
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	}
	
}
