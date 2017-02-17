package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import application.Main;
import dao.BookingHistoryCRUD;
import dao.HotelCRUD;
import dao.RoomCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.BookingHistory;
import models.HistoryViews;
import models.Hotel;

/**
 * Shows the booking history of the user
 * @author shruti
 *
 */
public class History implements Initializable{
	
	@FXML
	private TableView<HistoryViews> table;
	
	/**
	 * Initialises the Java Table View
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ObservableList<HistoryViews> historylist = getHistory();
		
		TableColumn<HistoryViews, Integer> idcolumn = new TableColumn<>("Room Number");
		idcolumn.setMinWidth(100);
		idcolumn.setCellValueFactory(new PropertyValueFactory<>("rid"));
		
		TableColumn<HistoryViews, String> hnamecolumn = new TableColumn<>("Hotel Name");
		hnamecolumn.setMinWidth(100);
		hnamecolumn.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
		
		TableColumn<HistoryViews, String> rtypecolumn = new TableColumn<>("Room Type");
		rtypecolumn.setMinWidth(100);
		rtypecolumn.setCellValueFactory(new PropertyValueFactory<>("roomType"));
		
		TableColumn<HistoryViews, Date> cincolumn = new TableColumn<>("Check-in Date");
		cincolumn.setMinWidth(100);
		cincolumn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
		
		TableColumn<HistoryViews, Date> coutcolumn = new TableColumn<>("Check-out Date");
		coutcolumn.setMinWidth(100);
		coutcolumn.setCellValueFactory(new PropertyValueFactory<>("checkout"));
		
		TableColumn<HistoryViews, Double> rfarecolumn = new TableColumn<>("Room Fare");
		rfarecolumn.setMinWidth(100);
		rfarecolumn.setCellValueFactory(new PropertyValueFactory<>("fare"));
		
		if(!historylist.isEmpty()){
			table.setItems(historylist);
			table.getColumns().addAll(idcolumn, hnamecolumn,rfarecolumn,cincolumn,coutcolumn, rtypecolumn);
		}else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Empty Table Dialog");
			alert.setHeaderText("No History");
			alert.setContentText("You do not have any booking history in your account");

			alert.showAndWait();
			AnchorPane root;
			try {
				root = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/HotelSearch.fxml"));
				Scene scene = new Scene(root);
				Main.stage.setTitle("Hotel Search");
				Main.stage.setScene(scene);
				Main.stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	/**
	 * Gets the elements for java table view
	 * 
	 */
	private ObservableList<HistoryViews> getHistory(){
		ObservableList<HistoryViews> views = FXCollections.observableArrayList();
		BookingHistoryCRUD bhcrud = new BookingHistoryCRUD();
		HotelCRUD hcrud = new HotelCRUD();
		RoomCRUD rcrud = new RoomCRUD();
		ObservableList<BookingHistory> historylist = bhcrud.getAllHistoryList();
		for(BookingHistory b: historylist){
			HistoryViews h = new HistoryViews();
			h.setRid(b.getRid());
			h.setHotelName(hcrud.getHotelName(b.getHid()));
			h.setRoomType(rcrud.getRoomType(b.getRid()));
			h.setFare(b.getRoom_fare());
			h.setCheckIn(b.getBooked_from_date());
			h.setCheckout(b.getBooked_to_date());
			views.add(h);
		}
		return views;
	}
	
	/**
	 * Cancels the booking
	 */
	@FXML
	public void cancel(){
		HistoryViews select = table.getSelectionModel().getSelectedItem();
		HotelCRUD hcrud = new HotelCRUD();
		BookingHistory bh = new BookingHistory();
		Hotel hotel = new Hotel();
		hotel.setHotelname(select.getHotelName());
		bh.setHid(hcrud.getHotelid(hotel));
		bh.setRid(select.getRid());
		
		BookingHistoryCRUD bhcrud = new BookingHistoryCRUD();
		
		try{
			bhcrud.delete(bh);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Delete Dialog");
			alert.setHeaderText("Delete Dialog");
			alert.setContentText("The row is deleted");

			alert.showAndWait();
			AnchorPane root;
			try {
				root = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/BookingHistory.fxml"));
				Scene scene = new Scene(root);
				Main.stage.setTitle("Booking History");
				Main.stage.setScene(scene);
				Main.stage.show();
			} catch (IOException ge) {
				// TODO Auto-generated catch block
				ge.printStackTrace();
			}
		}catch(Exception ce){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Error Dialog");
				alert.setContentText("Error: "+ce.getMessage());

				alert.showAndWait();
				AnchorPane root;
				try {
					root = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/BookingHistory.fxml"));
					Scene scene = new Scene(root);
					Main.stage.setTitle("Booking History");
					Main.stage.setScene(scene);
					Main.stage.show();
				} catch (IOException ge) {
					// TODO Auto-generated catch block
					ge.printStackTrace();
				}
		}
	}
	
	/**
	 * Goes back to user home page
	 */
	@FXML
	public void goback(){
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
