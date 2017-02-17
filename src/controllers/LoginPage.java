package controllers;

import java.io.IOException;

import application.Main;
import dao.UserCRUD;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Context;
import models.User;

/**
 * Login page Controller
 * @author shruti
 *
 */
public class LoginPage {
	
	@FXML
	private TextField username;
	
	@FXML 
	private PasswordField pwd;
	
	@FXML
	private TextField user_location;
	
	/**
	 * Logs in the user
	 */
	@FXML
	public void login(){
		User lu = new User();
		lu.setUsername(username.getText());
		lu.setPassword(pwd.getText());
		
		UserCRUD crud = new UserCRUD();
		User validatedUser = crud.CustomerValidate(lu);
		if(validatedUser.getUsername()!=null){
			System.out.println("Validated. Success!!");
			navigateUser(validatedUser);
		}else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error Dialog");
			alert.setContentText("Ooops, your credentials are not correct!");

			alert.showAndWait();
			AnchorPane root;
			try {
				root = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
				Scene scene = new Scene(root);
				Main.stage.setTitle("Login");
				Main.stage.setScene(scene);
				Main.stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	
	/**
	 * Registers the user
	 */
	@FXML
	public void register(){
		try {
			AnchorPane root1 = (AnchorPane)FXMLLoader.load(Main.class.getResource("/views/Register.fxml"));
			Scene scene = new Scene(root1);
			Main.stage.setTitle("Create new User");
            Main.stage.setScene(scene);  
            Main.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("The error is "+ e.getMessage());
		}
		
	}
	
	/**
	 * Navigates the user to either admin or user home page
	 * @param user
	 */
	public void navigateUser(User user){
		UserCRUD crud = new UserCRUD();
		Context.getInstance().currentUserchoice().setUid(user.getUserid());
		crud.setLoggedInUser(user);
		if(user.getUsername().equals("admin")){
			AnchorPane root;
			try {
				root = (AnchorPane)FXMLLoader.load(Main.class.getResource("/views/AdminPage.fxml"));
				Scene scene = new Scene(root);
	            Main.stage.setScene(scene);  
	            Main.stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			AnchorPane root;
			try {
				root = (AnchorPane)FXMLLoader.load(Main.class.getResource("/views/HotelSearch.fxml"));
				Scene scene = new Scene(root);
	            Main.stage.setScene(scene);  
	            Main.stage.setTitle("Search Page");
	            Main.stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Add the user to the db
	 */
	@FXML
	public void registeruser(){
		User ru = new User();
		
		if(!username.getText().equals("") | !pwd.getText().equals("")){
			ru.setUsername(username.getText());
			ru.setPassword(pwd.getText());
			ru.setLocation(user_location.getText());
			
			UserCRUD crud = new UserCRUD();
			User registeredUser = crud.create(ru);
			System.out.println("New user Created");
			System.out.println(registeredUser.getUserid());
			Context.getInstance().currentUserchoice().setUid(registeredUser.getUserid());
			crud.setLoggedInUser(registeredUser);
			
			AnchorPane root;
			try {
				root = (AnchorPane)FXMLLoader.load(Main.class.getResource("/views/HotelSearch.fxml"));
				Scene scene = new Scene(root);
	            Main.stage.setScene(scene);  
	            Main.stage.setTitle("Search Page");
	            Main.stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error Dialog");
			alert.setContentText("Ooops, no empty spaces are allowed!");

			alert.showAndWait();
		}
		
	}
	
}
