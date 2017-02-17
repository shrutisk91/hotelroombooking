package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import dao.BookingHistoryCRUD;
import dao.RoomCRUD;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.BookingHistory;
import models.Context;
import models.Room;

/**
 * Controller for room listing table
 * @author shruti
 *
 */
public class RoomTable implements Initializable{
	
	@FXML
	private TableView<Room> table;
	
	@FXML
	private TextField roomslength;
	
	/**
	 * Initialises the table
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		RoomCRUD rcrud = new RoomCRUD();
		ObservableList<Room> roomlist = rcrud.getRoomList(Context.getInstance().currentUserchoice().getHid());
		
		TableColumn<Room, String> typecolumn = new TableColumn<>("Room Type");
		typecolumn.setMinWidth(100);
		typecolumn.setCellValueFactory(new PropertyValueFactory<>("room_type"));
		
		TableColumn<Room, Double> ratecolumn = new TableColumn<>("Rate per Day");
		ratecolumn.setMinWidth(100);
		ratecolumn.setCellValueFactory(new PropertyValueFactory<>("rate_per_day"));
		
		TableColumn<Room, Integer> roomscolumn = new TableColumn<>("Rooms Avlbl");
		roomscolumn.setMinWidth(100);
		roomscolumn.setCellValueFactory(new PropertyValueFactory<>("noofrooms"));
		
		table.setItems(roomlist);
		table.getColumns().addAll(typecolumn, ratecolumn, roomscolumn);
	}	
	
	/**
	 * Books the room and adds to database
	 */
	@FXML
	public void bookroom(){
		if(!table.getSelectionModel().isEmpty()){
			Room room = table.getSelectionModel().getSelectedItem();
			int limit = Integer.parseInt(roomslength.getText());
			if(limit<=room.getNoofrooms()){
				Context.getInstance().currentUserchoice().setroomsnumber(limit);
				Context.getInstance().currentUserchoice().setRoom_type(room.getRoom_type());
				
				RoomCRUD rcrud = new RoomCRUD();
				rcrud.updateBooking(room);
				
				ArrayList<Room> roomlist = rcrud.getRoomsforUser();
				BookingHistoryCRUD bhcrud = new BookingHistoryCRUD();
				for(Room r: roomlist){
					BookingHistory bh = new BookingHistory();
					bh.setHid(room.getHid());
					bh.setRid(r.getRid());
					bh.setRoom_fare(r.getRate_per_day());
					bh.setBooked_from_date(Context.getInstance().currentUserchoice().getFrom_date());
					bh.setBooked_to_date(Context.getInstance().currentUserchoice().getTo_date());
					bhcrud.createHistory(bh);
				}
			}else{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Error Dialog");
				alert.setContentText("Please enter number of rooms greater than or equal to the available rooms!");

				alert.showAndWait();
				AnchorPane root;
				try {
					root = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/RoomListing.fxml"));
					Scene scene = new Scene(root);
					Main.stage.setTitle("Room Listing");
					Main.stage.setScene(scene);
					Main.stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error Dialog");
			alert.setContentText("Ooops, you havent selected an item!");

			alert.showAndWait();
			AnchorPane root;
			try {
				root = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/RoomListing.fxml"));
				Scene scene = new Scene(root);
				Main.stage.setTitle("Room Listing");
				Main.stage.setScene(scene);
				Main.stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success Dialog");
		alert.setHeaderText("Booked Room");
		alert.setContentText("You have successfully booked the room.");

		alert.showAndWait();
		AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/HotelSearch.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setTitle("Main Page");
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Redirects to booking history
	 */
	@FXML
	public void bookinghistory(){
		AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(Main.class.getResource("/views/BookingHistory.fxml"));
			Scene scene = new Scene(root);
            Main.stage.setScene(scene);  
            Main.stage.setTitle("Booking History");
            Main.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Redirects to user home page
	 */
	@FXML
	public void searchpage(){
		AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(Main.class.getResource("/views/HotelSearch.fxml"));
			Scene scene = new Scene(root);
            Main.stage.setScene(scene);  
            Main.stage.setTitle("Hotel Search");
            Main.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
