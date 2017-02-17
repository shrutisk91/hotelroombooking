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
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import models.Hotel;
import models.Room;

/**
 * Controller for room update
 * @author shruti
 *
 */
public class UpdateRoom implements Initializable{
	
	@FXML
	private ComboBox<String> hotelcombo;
	
	/**
	 * Initialises the combobox
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> hotelist = FXCollections.observableArrayList();
		HotelCRUD crud = new HotelCRUD();
		hotelist = crud.getuniquehotels();
		System.out.println(hotelist);
		hotelcombo.setItems(hotelist);
	}
	
	/**
	 * update the room availability in the db
	 */
	@FXML
	public void updateroomavail(){
		Room room = new Room();
		Hotel hotel = new Hotel();
		hotel.setHotelname(hotelcombo.getValue());
		HotelCRUD hcrud = new HotelCRUD();
		int hid = hcrud.getHotelid(hotel);
		
		room.setHid(hid);
		
		RoomCRUD crud = new RoomCRUD();
		Room updatedroom = crud.updateRoomAvail(room);
		System.out.println("Room is made available");
		
		AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/AdminPage.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setTitle("Login");
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Goes back to admin home page
	 */
	@FXML
	public void goback(){
		AnchorPane root;
		try {
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/AdminPage.fxml"));
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
