package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import dao.RoomCRUD;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.Room;

/**
 * Controller to view room
 * @author shruti
 *
 */
public class ViewRoom implements Initializable {
	
	@FXML
	private TableView<Room> table;
	
	/**
	 * Initialises the room table
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RoomCRUD rcrud = new RoomCRUD();
		ObservableList<Room> roomlist = rcrud.getAllRoomList();
		
		TableColumn<Room, Integer> idcolumn = new TableColumn<>("Room id");
		idcolumn.setMinWidth(50);
		idcolumn.setCellValueFactory(new PropertyValueFactory<>("rid"));
		
		TableColumn<Room, String> hotelnamecolumn = new TableColumn<>("Hotel Name");
		hotelnamecolumn.setMinWidth(50);
		hotelnamecolumn.setCellValueFactory(new PropertyValueFactory<>("hotel_name"));
		
		TableColumn<Room, String> typecolumn = new TableColumn<>("Room Type");
		typecolumn.setMinWidth(100);
		typecolumn.setCellValueFactory(new PropertyValueFactory<>("room_type"));
		
		TableColumn<Room, Double> ratecolumn = new TableColumn<>("Rate per Day");
		ratecolumn.setMinWidth(100);
		ratecolumn.setCellValueFactory(new PropertyValueFactory<>("rate_per_day"));
		
		TableColumn<Room, Integer> roomscolumn = new TableColumn<>("Is Available?");
		roomscolumn.setMinWidth(100);
		roomscolumn.setCellValueFactory(new PropertyValueFactory<>("availability"));
		
		TableColumn<Room, Integer> fromdatecolumn = new TableColumn<>("Check-In");
		fromdatecolumn.setMinWidth(100);
		fromdatecolumn.setCellValueFactory(new PropertyValueFactory<>("booked_from_date"));
		
		TableColumn<Room, Integer> todatecolumn = new TableColumn<>("Check-Out");
		todatecolumn.setMinWidth(100);
		todatecolumn.setCellValueFactory(new PropertyValueFactory<>("booked_to_date"));
		
		table.setItems(roomlist);
		table.getColumns().addAll(idcolumn,hotelnamecolumn, typecolumn, ratecolumn, roomscolumn, fromdatecolumn, todatecolumn);
		
	}
	
	/**
	 * Redirects to admin home page
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
