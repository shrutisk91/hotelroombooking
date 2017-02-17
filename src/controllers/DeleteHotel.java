package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import dao.HotelCRUD;
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

/**
 * Has methods for delete and updates the hotel
 * @author shruti
 *
 */
public class DeleteHotel implements Initializable{
	
	@FXML
	private ComboBox<String> hotelcombo;
	
	@FXML
	private TextField stars;
	
	/**
	 * Initialises the combo box
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> hotelist = FXCollections.observableArrayList();
		HotelCRUD crud = new HotelCRUD();
		hotelist = crud.getuniquehotels();
		hotelcombo.setItems(hotelist);
	}
	
	/**
	 * Deletes the hotel
	 */
	@FXML
	public void deletehotel(){
		String hotelname = hotelcombo.getValue();
		HotelCRUD crud = new HotelCRUD();
		Hotel hotel = new Hotel();
		hotel.setHotelname(hotelname);
		try{
			crud.deleteHotel(hotel);
		}
		catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error Dialog");
			alert.setContentText("Please delete all the rooms in the hotel before deleting");
			alert.showAndWait();
			
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
		}finally{
			AnchorPane root;
			try {
				root = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/AdminPage.fxml"));
				Scene scene = new Scene(root,600,600);
				Main.stage.setTitle("Login");
				Main.stage.setScene(scene);
				Main.stage.show();
			} catch (IOException ioe) {
				// TODO Auto-generated catch block
				ioe.printStackTrace();
			}
		}
	}
	
	/**
	 * Updates the hotel
	 */
	@FXML
	public void updatehotel() {
		String hotelname = hotelcombo.getValue();
		HotelCRUD crud = new HotelCRUD();
		Hotel hotel = new Hotel();
		hotel.setHotelname(hotelname);
		hotel.setStars(Integer.parseInt(stars.getText()));
		crud.updateHotel(hotel);

		AnchorPane root;
		try {
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/AdminPage.fxml"));
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
	 * Goes to admin menu page
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
