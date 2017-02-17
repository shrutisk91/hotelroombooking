package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author shruti
 * Start of the hotel Booking Application. This is run in order for the app to work.
 *
 */
public class Main extends Application {
	public static Stage stage;
	/**
	 * Calls the first page for login
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("Login");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Cannot load fxml due to "+e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
