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
import java.io.IOException;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class PrintAlbums extends Application {
	
public static void printAlbums(GridPane gridPane) {
		
		JSONParser parser = new JSONParser();
	
	try(FileReader reader = new FileReader("albums.json"))
	{
		// read
		Object obj = parser.parse(reader);
		JSONArray albumList = (JSONArray) obj;
		
		// create a table
		Label nameLabel = new Label(" Album");
        gridPane.add(nameLabel, 0, 1);
        Label artistLabel = new Label("Artist");
        gridPane.add(artistLabel, 1, 1);
        Label priceLabel = new Label("Price");
        gridPane.add(priceLabel, 2, 1);
        if(UserSession.userType.equals("artist")) artistLabel.setVisible(false);
        // iterate
		albumList.forEach(album -> parseUserObject((JSONObject)album, (GridPane) gridPane));
	}	
			
		catch(FileNotFoundException e) {e.printStackTrace();}
		catch(IOException e) {e.printStackTrace();}
		catch(Exception e) {e.printStackTrace();}
	}
	
	static String testUser = UserSession.userName;
	
	static boolean foundUser = false;
	
	static int i = 2;
	
	private static void parseUserObject(JSONObject album, GridPane gridPane)
	{
		JSONObject userObj = (JSONObject) album.get("album");
		        
		/// get username, password, user type
		String readName = (String) userObj.get("name");
		String readArtist = (String) userObj.get("artist");
		String readPrice = (String) userObj.get("price");
		
		
		if(UserSession.userType.equals("customer")) {
				foundUser = true;
				int id1=i-1;
				String id;
				id=String.valueOf(id1);
				Label nameLabel = new Label(readName);
            	gridPane.add(nameLabel, 0, i);
            	Label artistLabel = new Label(readArtist);
            	gridPane.add(artistLabel, 1, i);
            	
            	Label priceLabel = new Label("$"+readPrice);
            	gridPane.add(priceLabel, 2, i);
            	
		// create buy button
            	Button buyButton = new Button("Buy");
            	
            	
            	buyButton.setId(id);;
            	buyButton.setPrefHeight(20);
            	buyButton.setDefaultButton(true);
            	buyButton.setPrefWidth(50);
                gridPane.add(buyButton, 3, i);
                GridPane.setHalignment(buyButton, HPos.CENTER);
                GridPane.setMargin(buyButton, new Insets(5, 0,5,0));
            	
                ///System.out.println("id="+buyButton.getId());
                
            	i++;
            	id=String.valueOf(id1);
	
			
			buyButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {                          
	         	
	                showAlert(Alert.AlertType.INFORMATION, gridPane.getScene().getWindow(), "Succes!", "Congrats! You just bought the "+ 
	                nameLabel.getText() + " album by " + artistLabel.getText() + " for " + priceLabel.getText() + "!");
	            }
	        });
		}
			
		
		
		else if(UserSession.userName.equals(readArtist)) {
			

			Label nameLabel = new Label(readName);
        	gridPane.add(nameLabel, 0, i);
        	Label priceLabel = new Label("$"+readPrice);
        	gridPane.add(priceLabel, 2, i);
        	
        	
        	i++;
		}
	
		return;
	}
	public static String artistName = "anca";
	
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
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 50, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,50, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        
        
        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        
    	// Add Header
    	Label headerLabel = new Label("");
    	if(UserSession.userType.equals("customer"))
        	headerLabel.setText("List of albums");
        else 
        	headerLabel.setText("Your albums");
        
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Print Albums
        printAlbums(gridPane);
 
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static void launchPrintAlbums()
    {
    	launch();
    }
    
    public static void main(String[] args) {
    	launchPrintAlbums();
    }
}