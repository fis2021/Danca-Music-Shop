package src.application;

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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class RegistrationForm extends Application {


	/// insert users in the data base
public static void insertUser(String username, String password, String type) {
		
		JSONArray jsonArray = new JSONArray();
		JSONParser parser = new JSONParser();
		
		try(Reader reader = new FileReader("kfc.json")) 
		{
			jsonArray = (JSONArray) parser.parse(reader);
					
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		
		JSONObject obj = new JSONObject();
		obj.put("username", username);
		obj.put("password", password);
		obj.put("type", type);
		
		JSONObject user = new JSONObject();
		user.put("user", obj);
		
		jsonArray.add(user);
		
		try(FileWriter file = new FileWriter("kfc.json"))
		{
			file.write(jsonArray.toJSONString());
			file.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		System.out.println(obj);
		
	}
	
	/// checks for username availability

public static boolean found = false;

public static void foundUser(String usr) {
	
	JSONParser parser = new JSONParser();
	
try(FileReader reader = new FileReader("kfc.json"))
{
	// read
	Object obj = parser.parse(reader);
	JSONArray userList = (JSONArray) obj;
	// iterate
	userList.forEach(user -> parseUserObject((JSONObject)user, usr));
	
}	
		
	catch(FileNotFoundException e) {e.printStackTrace();}
	catch(IOException e) {e.printStackTrace();}
	catch(Exception e) {e.printStackTrace();}
}

private static void parseUserObject(JSONObject user, String usr)
{
	JSONObject userObj = (JSONObject) user.get("user");
	/// get username
	String readUser = (String) userObj.get("username");
	
	if(usr.equals(readUser)) {
		found = true;
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
        Label headerLabel = new Label("Registration Form");
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
       
        /* Card details
        
        // Add Card Number Label
        Label CardNumberLabel = new Label("Card Number : ");
        gridPane.add(CardNumberLabel, 0,2);

        // Add Card Number Field
        TextField CardNumberField = new TextField();
        CardNumberField.setPrefHeight(40);
        gridPane.add(CardNumberField, 1,2);
        
        // Add Expiration Date Label
        Label ExpirationDateLabel = new Label("Expiration Date : ");
        gridPane.add(ExpirationDateLabel, 0,3);

        // Add Expiration Date Field
        TextField ExpirationDateField = new TextField();
        ExpirationDateField.setPrefHeight(40);
        gridPane.add(ExpirationDateField, 1,3);
        
        // Add Card Name Label
        Label CardNameLabel = new Label("Card Name : ");
        gridPane.add(CardNameLabel, 0,4);

        // Add Card Number Field
        TextField CardNameField = new TextField();
        CardNameField.setPrefHeight(40);
        gridPane.add(CardNameField, 1,4);
        
        // Add CVV/CVC Label
        Label CvvCvcLabel = new Label("CVV/CVC : ");
        gridPane.add(CvvCvcLabel, 0,5);

        // Add CVV/CVC Field
        TextField CvvCvcField = new TextField();
        CvvCvcField.setPrefHeight(40);
        gridPane.add(CvvCvcField, 1,5);
*/
        // Add Password Label
        Label passwordLabel = new Label("Password : ");
        gridPane.add(passwordLabel, 0, 2);

        // Add Password Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 2);
        
     // Add Password2 Label
        Label passwordLabel2 = new Label("Password again : ");
        gridPane.add(passwordLabel2, 0, 3);

        // Add Password2 Field
        PasswordField passwordField2 = new PasswordField();
        passwordField2.setPrefHeight(40);
        gridPane.add(passwordField2, 1, 3);
        
        // Add RadioBox Label
        
        Label customerOrArtistLabel = new Label("User type:");
        gridPane.add(customerOrArtistLabel, 0, 4);
        
        
        // Add RadioBox Field
        final ToggleGroup group = new ToggleGroup();
                
        RadioButton customerRadiobox = new RadioButton("customer");
        HBox hbox = new HBox(customerRadiobox);
        gridPane.add(customerRadiobox, 1, 8);
        customerRadiobox.setToggleGroup(group);
        customerRadiobox.setSelected(true);
        
        RadioButton artistRadiobox = new RadioButton("artist");
        HBox hbox2 = new HBox(artistRadiobox);
        gridPane.add(artistRadiobox, 1, 9);
        artistRadiobox.setToggleGroup(group);
        
        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 10, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(usernameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your username");
                    return;
                }
                /* Card details
                
                if(CardNumberField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your card number");
                    return;
                }
                if(ExpirationDateField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter the expiration date of the card");
                    return;
                }
                if(CardNameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter the card name");
                    return;
                }
                if(CvvCvcField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter the CVV/CVC");
                    return;
                }
                */
                if(passwordField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a password");
                    return;
                }
                if(passwordField2.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter the password again");
                    return;
                }
                if(passwordField2.getText().equals(passwordField.getText())==false) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "The passwords don't match");
                    return;
                }    
                /// check for username in the database  
                foundUser(usernameField.getText());
                if(found == true) {
                	showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Username not available");
                	return;
                }
                
                /// insert users to database
                if(customerRadiobox.isSelected() == true)
                	insertUser(usernameField.getText(), passwordField2.getText(), "customer");
                else
                	insertUser(usernameField.getText(), passwordField2.getText(), "artist");
                
                showAlert(Alert.AlertType.INFORMATION, gridPane.getScene().getWindow(), "Registration Successful!", "Welcome " + usernameField.getText());
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
    
    	public static void launchRegister() {
    		launch();
    	}
    
    public static void main(String[] args) {
        launch(args);
    }
}