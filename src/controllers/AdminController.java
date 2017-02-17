package controllers;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * Shows the admin menu page
 * @author shruti
 *
 */
public class AdminController {
	
	/**
	 * Calls the page for hotels 
	 */
	@FXML
	public void hotelactivity(){
		AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(Main.class.getResource("/views/HotelActivity.fxml"));
			Scene scene = new Scene(root);
            Main.stage.setScene(scene); 
            Main.stage.setTitle("Hotel Activities");
            Main.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Calls the page for rooms
	 */
	@FXML
	public void roomactivity(){
		AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(Main.class.getResource("/views/RoomActivity.fxml"));
			Scene scene = new Scene(root);
            Main.stage.setScene(scene);  
            Main.stage.setTitle("Room Activities");
            Main.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Logs out a user
	 */
	@FXML
	public void logout(){
		AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(Main.class.getResource("/views/Main.fxml"));
			Scene scene = new Scene(root);
            Main.stage.setScene(scene);  
            Main.stage.setTitle("Login");
            Main.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
