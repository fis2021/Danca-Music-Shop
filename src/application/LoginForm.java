package src.application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException; 

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginForm extends Application {

	/// remember the user type
	//public static String globalType;
	/// checks for username and password match
	public static boolean match = false;

	public static void userMatch(String usr, String pass) {
		
		JSONParser parser = new JSONParser();
		
	try(FileReader reader = new FileReader("kfc.json"))
	{
		// read
		Object obj = parser.parse(reader);
		JSONArray userList = (JSONArray) obj;
		// iterate
		userList.forEach(user -> parseUserObject((JSONObject)user, usr, pass));
		
	}	
			
		catch(FileNotFoundException e) {e.printStackTrace();}
		catch(IOException e) {e.printStackTrace();}
		catch(Exception e) {e.printStackTrace();}
	}

	private static void parseUserObject(JSONObject user, String usr, String pass)
	{
		JSONObject userObj = (JSONObject) user.get("user");
		/// get username, password, user type
		String readUser = (String) userObj.get("username");
		String readPass = (String) userObj.get("password");
		String readType = (String) userObj.get("type");
		
		if(usr.equals(readUser)) {
			if(pass.equals(readPass)) {
				match = true;
				UserSession.userName = readUser;
                UserSession.userType = readType;
			}
			return;
		}
	}
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Danca Music Shop");

        // Create the registration form grid pane
        GridPane gridPane = createRegistrationFormPane();
        // Add UI controls to the registration form grid pane
        addUIControls(gridPane);
        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage	
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }


    private GridPane createRegistrationFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Log in Form");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        Label usernameLabel = new Label("Username : ");
        gridPane.add(usernameLabel, 0,1);

        // Add Name Text Field
        TextField usernameField = new TextField();
        usernameField.setPrefHeight(40);
        gridPane.add(usernameField, 1,1);

        // Add Password Label
        Label passwordLabel = new Label("Password : ");
        gridPane.add(passwordLabel, 0, 2);

        // Add Password Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 2);
        
        // Add RadioBox Label
      
        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 5, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(usernameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your username");
                    return;
                }
                if(passwordField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a password");
                    return;
                }
                userMatch(usernameField.getText(), passwordField.getText());
                if(match == false) {
                	showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Database Error!", "Username and Password don't match");
                	return;
                }
                match = false;
                
                
                
                
                // opens the next page
                ShopPage shopPage = new ShopPage();
            	try {
            		shopPage.start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                showAlert(Alert.AlertType.INFORMATION, gridPane.getScene().getWindow(), "Login Successful!", "Welcome, " + usernameField.getText() + "!");
            }
        });
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    
    public static void launchLogin()
    {
    	launch();
    }

    public static void main(String[] args) {
        launchLogin();
        
    }
}