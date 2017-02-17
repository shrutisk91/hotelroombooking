package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import dao.HotelCRUD;
import dao.RoomCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import models.Hotel;
import models.Room;

/**
 * 
 * @author shruti
 * Has the following methods to 
 * 1. Add a room to a hotel
 * 2. Update room rate
 * 3. Delete a Room
 *
 */
public class AddRoom implements Initializable{
	
	@FXML
	private ComboBox<String> hotelcombo;
	
	@FXML
	private ComboBox<String> roomtypecombo;
	
	@FXML
	private TextField rate_per_day;
	/**
	 * Initialising the combo box in the page
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> hotelist = FXCollections.observableArrayList();
		HotelCRUD crud = new HotelCRUD();
		hotelist = crud.getuniquehotels();
		hotelcombo.setItems(hotelist);
		
		ObservableList<String> roomtypelist = FXCollections.observableArrayList("deluxe", "standard");
		roomtypecombo.setItems(roomtypelist);
	}
	
	/**
	 * Adds a room
	 */
	@FXML
	public void addroom(){
		Room room = new Room();
		room.setRoom_type(roomtypecombo.getValue());
		room.setRate_per_day(Double.parseDouble(rate_per_day.getText()));
		
		Hotel hotel = new Hotel();
		hotel.setHotelname(hotelcombo.getValue());
		HotelCRUD hcrud = new HotelCRUD();
		int hid = hcrud.getHotelid(hotel);
		
		room.setHid(hid);
		
		RoomCRUD crud = new RoomCRUD();
		Room addedroom = crud.createRoom(room);
		System.out.println("New Room is added");
		
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
	 * Goes back to Admin Menu
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
	
	/**
	 * Updates the room rate
	 */
	@FXML
	public void updateroomrate(){
		Room room = new Room();
		room.setRoom_type(roomtypecombo.getValue());
		room.setRate_per_day(Double.parseDouble(rate_per_day.getText()));
		
		Hotel hotel = new Hotel();
		hotel.setHotelname(hotelcombo.getValue());
		HotelCRUD hcrud = new HotelCRUD();
		int hid = hcrud.getHotelid(hotel);
		
		room.setHid(hid);
		
		RoomCRUD crud = new RoomCRUD();
		Room updatedroom = crud.updateRoom(room);
		System.out.println("Room is updated");
		
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
	 * Deletes rooms in a hotel
	 */
	@FXML
	public void deleterooms(){
		Room room = new Room();
		room.setRoom_type(roomtypecombo.getValue());
		
		Hotel hotel = new Hotel();
		hotel.setHotelname(hotelcombo.getValue());
		HotelCRUD hcrud = new HotelCRUD();
		int hid = hcrud.getHotelid(hotel);
		
		room.setHid(hid);
		
		RoomCRUD crud = new RoomCRUD();
		try{
			crud.deleteRoom(room);
		}
		catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error Dialog");
			alert.setContentText("Cannot delete the rooms");
			alert.showAndWait();
			
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
		}finally{
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
}
