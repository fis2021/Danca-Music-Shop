package src.application;

import javafx.application.Application;
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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class AddAlbum extends Application {

	static String artistName = UserSession.userName;
	
	/// insert albums in the data base
public static void insertAlbum(String name, String artist, String price) {

    JSONArray jsonArray = new JSONArray();
    JSONParser parser = new JSONParser();

    try (Reader reader = new FileReader("albums.json")) {
        jsonArray = (JSONArray) parser.parse(reader);

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (org.json.simple.parser.ParseException e) {
        e.printStackTrace();
    }

    JSONObject obj = new JSONObject();
    obj.put("name", name);
    obj.put("artist", artist);
    obj.put("price", price);

    JSONObject album = new JSONObject();
    album.put("album", obj);

    jsonArray.add(album);

    try (FileWriter file = new FileWriter("albums.json")) {
        file.write(jsonArray.toJSONString());
        file.flush();
    } catch (IOException e) {
        e.printStackTrace();
    }

    System.out.println(obj);

}
	
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Danca Music Shop");

        // Create the form grid pane
        GridPane gridPane = createFormPane();
        // Add UI controls to the form grid pane
        addUIControls(gridPane);
        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage	
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }


    private GridPane createFormPane() {
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
        Label headerLabel = new Label("Add an Album");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        Label nameLabel = new Label("Album name : ");
        gridPane.add(nameLabel, 0,1);

        // Add Name Text Field
        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        gridPane.add(nameField, 1,1);
       
        // Add Price Label
        Label priceLabel = new Label("Price : ");
        gridPane.add(priceLabel, 0, 2);

        // Add Price Field
        TextField priceField = new TextField();
        priceField.setPrefHeight(40);
        gridPane.add(priceField, 1, 2);
        

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(nameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter the album name");
                    return;
                }
                if(priceField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a price");
                    return;
                }
                
                /// insert albums to database
                	insertAlbum(nameField.getText(), artistName, priceField.getText());  
                	
                showAlert(Alert.AlertType.INFORMATION, gridPane.getScene().getWindow(), "Succes!", "Album " + nameField.getText() + " added!");
                
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

    public static void launchAddAlbum() {
    	launch();
    }
    
    public static void main(String[] args) {
        launchAddAlbum();
    }
}