package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import models.Context;

/**
 * Controller for user home page
 * @author shruti
 *
 */
public class HotelSearchController implements Initializable{
	
	@FXML
	private TextField hotel_location;
	
	@FXML
	private DatePicker from_date;
	
	@FXML
	private DatePicker to_date;
	
	/**
	 * Initialises the date picker
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		from_date.setValue(LocalDate.now());
        final Callback<DatePicker, DateCell> dayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                           
                            if (item.isBefore(
                            		from_date.getValue().plusDays(1))
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #EEEEEE;");
                            }  
                            long p = ChronoUnit.DAYS.between(
                            		from_date.getValue(), item
                            );
                            setTooltip(new Tooltip(
                                "You're about to stay for " + p + " days")
                            );
                    }
                };
            }
        };
        to_date.setDayCellFactory(dayCellFactory);
        to_date.setValue(from_date.getValue().plusDays(1));
		
	}
	
	/**
	 * Processes the date to be searched in the database
	 */
	@FXML
	public void processSearchData(){
		
		System.out.println("inside processSearchData");
		
		Context.getInstance().currentUserchoice().setLocation(hotel_location.getText().toLowerCase());
		Context.getInstance().currentUserchoice().setFrom_date(localDatetoDate(from_date.getValue()));
		Context.getInstance().currentUserchoice().setTo_date(localDatetoDate(to_date.getValue()));
		
		AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(Main.class.getResource("/views/HotelListing.fxml"));
			Scene scene = new Scene(root);
            Main.stage.setScene(scene);  
            //
            from_date.setValue(LocalDate.now());
            Main.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * LocalDate to sql Date
	 * @param localDate
	 * 
	 */
	private Date localDatetoDate(LocalDate localDate){
		return Date.valueOf(localDate);
	}
	
	/**
	 * Displays the booking history
	 */
	@FXML
	public void bookinghistory(){
		AnchorPane root;
		try {
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/BookingHistory.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setTitle("Your Booking History");
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Logs out the user
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
