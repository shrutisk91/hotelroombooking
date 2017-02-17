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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.Hotel;

/**
 * Table to view the hotel list
 * @author shruti
 *
 */
public class ViewHotelTable implements Initializable{
	
	@FXML
	private TableView<Hotel> table;
	
	/**
	 * Initialises the hotel table
	 */
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
		//table = new TableView<>();
		
		ObservableList<Hotel> hotelist = hcrud.getAllHotelList();
		table.setItems(hotelist);
		table.getColumns().addAll(idcolumn,namecolumn,starscolumn,locationcolumn, roomscolumn);
		
	}
	
	/**
	 * Redirects to admin home page
	 */
	@FXML
	public void goBack(){
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
