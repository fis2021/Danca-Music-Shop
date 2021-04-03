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

public class ShopPage extends Application {
	
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
        
        // Add Artist Button
         
    	Button seeAlbumsButton = new Button("Shop");
        seeAlbumsButton.setPrefHeight(40);
        seeAlbumsButton.setDefaultButton(true);
        seeAlbumsButton.setPrefWidth(100);
        gridPane.add(seeAlbumsButton, 0, 5, 2, 1);
        GridPane.setHalignment(seeAlbumsButton, HPos.LEFT);
        GridPane.setMargin(seeAlbumsButton, new Insets(40, 0,40,0));
        
        // Add Customer Button
        Button addAlbumButton = new Button("Add Album");
        addAlbumButton.setPrefHeight(40);
        addAlbumButton.setDefaultButton(true);
        addAlbumButton.setPrefWidth(100);
        gridPane.add(addAlbumButton, 0, 5, 2, 1);
        GridPane.setHalignment(addAlbumButton, HPos.RIGHT);
        GridPane.setMargin(addAlbumButton, new Insets(45, 0,45,0));

        // Add Header
        Label headerLabel = new Label("Welcome " + UserSession.userName + "!");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));
        
        if(UserSession.userType.equals("customer")) 
        	addAlbumButton.setVisible(false);
        
        
        seeAlbumsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	PrintAlbums printAlbums = new PrintAlbums();
            	try {
            		printAlbums.start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        addAlbumButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	AddAlbum addAlbum = new AddAlbum();
            	try {
            		addAlbum.start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
    }
    
    public static void launchShopPage()
    {
    	launch();
    }
    
    public static void main(String[] args) {
        launchShopPage();

    }
}
