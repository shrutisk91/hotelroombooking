package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import dao.HotelCRUD;
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
import models.Context;
import models.Hotel;
import models.UserChoice;

/**
 * Controller to display Table of hotels
 * @author shruti
 *
 */
public class HotelTable implements Initializable{
	
	@FXML
	private TableView<Hotel> table;
	
	/**
	 * Initialises the table
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TableColumn<Hotel, Integer> idcolumn = new TableColumn<>("id");
		idcolumn.setMinWidth(50);
		idcolumn.setCellValueFactory(new PropertyValueFactory<>("hid"));
		
		TableColumn<Hotel, String> namecolumn = new TableColumn<>("Hotel Name");
		namecolumn.setMinWidth(100);
		namecolumn.setCellValueFactory(new PropertyValueFactory<>("hotelname"));
		
		TableColumn<Hotel, Integer> starscolumn = new TableColumn<>("Rating Stars");
		starscolumn.setMinWidth(100);
		starscolumn.setCellValueFactory(new PropertyValueFactory<>("stars"));
		
		TableColumn<Hotel, String> locationcolumn = new TableColumn<>("City");
		locationcolumn.setMinWidth(100);
		locationcolumn.setCellValueFactory(new PropertyValueFactory<>("location"));
		
		TableColumn<Hotel, Integer> roomscolumn = new TableColumn<>("Availability of Rooms");
		roomscolumn.setMinWidth(100);
		roomscolumn.setCellValueFactory(new PropertyValueFactory<>("noofroomsavailable"));
		
		HotelCRUD hcrud = new HotelCRUD();
		UserChoice uc = Context.getInstance().currentUserchoice();
		//table = new TableView<>();
		
		ObservableList<Hotel> hotelist = hcrud.getHotelList(uc.getLocation());
		System.out.println("table contents" + uc.getLocation());
		table.setItems(hotelist);
		table.getColumns().addAll(idcolumn,namecolumn,starscolumn,locationcolumn, roomscolumn);
		
	}
	
	/**
	 * Redirects to room listing
	 */
	@FXML
	public void showrooms(){
		if(!table.getSelectionModel().isEmpty()){
			Hotel hotel = table.getSelectionModel().getSelectedItem();
			Context.getInstance().currentUserchoice().setHid(hotel.getHid());
			try{
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/RoomListing.fxml"));
			Scene scene = new Scene(root,600,600);
			Main.stage.setTitle("Room Listing for "+ hotel.getHotelname());
			Main.stage.setScene(scene);
			Main.stage.show();
			}catch(Exception re){
				re.printStackTrace();
			}
			
		}else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error Dialog");
			alert.setContentText("Ooops, you havent selected an item!");

			alert.showAndWait();
			AnchorPane root;
			try {
				root = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/HotelListing.fxml"));
				Scene scene = new Scene(root);
				Main.stage.setTitle("Hotel Listing");
				Main.stage.setScene(scene);
				Main.stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Back to user home page
	 */
	@FXML
	public void backtohotelsearch(){
		AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(Main.class.getResource("/views/HotelSearch.fxml"));
			Scene scene = new Scene(root);
            Main.stage.setScene(scene);  
            Main.stage.setTitle("Main Menu");
            Main.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
