

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;

import Fields.Field;
import buildings.Player;
import Utils.CollisionChecker;
import machinery.Tractor;
import machinery.Vehicle;

public class aplication extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		Player player = new Player(10,60);								// Spieler erstellen
		GridPane gridPane = generateGamefield();						// Spielfeld erstellen	
		Tractor tractor = new Tractor(50, 80, 100);
		final Group group = new Group(gridPane, player, tractor);
		Scene scene = new Scene(group);
		
		movePlayerOnKeyPress(scene, player, tractor);
		movePlayerOnMousePress(scene, player, createTransition(player));
		
		
		stage.setScene(scene);
		stage.show();
		
	}
	
	public GridPane generateGamefield() {
		GridPane grid = new GridPane();

		for (int i = 0; i < 30; i++) {
            ColumnConstraints column = new ColumnConstraints(50);		// Spielfeldgrösse
            grid.getColumnConstraints().add(column);
		}
		for (int i = 0; i < 21; i++) {
            RowConstraints row = new RowConstraints(50);
            grid.getRowConstraints().add(row);
		}
		
		Image background = new Image("File:./Images/TestImage.png");	// TODO Bild ersetzen   Festes Spielfeld
		ImageView backg = new ImageView(background);
		grid.add(backg, 0, 10);											// Landschaft

		File file = new File("Images/Bitmap.bmp");						// Weizenfelder
		BufferedImage bitmap;
		try {
			bitmap = ImageIO.read(file);
			for (int y = 0; y < bitmap.getHeight(); y++) {
			    for (int x = 0; x < bitmap.getWidth(); x++) {
//			          System.out.println(bitmap.getRGB(x, y));
			          if(bitmap.getRGB(x, y) == -10728) { grid.add(new Field(), x, (y+1)); }
			    }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
			
		grid.setGridLinesVisible(true);
		
		
		
		return grid;	
		
		
	}
	
	private void movePlayerOnKeyPress(Scene scene, Player player, Vehicle tractor) {
		int upper_boundary = 0;
		int left_boundary = 0;
		int right_boundary = 1500;
		int lower_boundary = 1050;
		CollisionChecker bc = new CollisionChecker();
		bc.addboundary(left_boundary-100,  upper_boundary, left_boundary, lower_boundary); // Left Window boundary
		bc.addboundary(left_boundary, upper_boundary-100, right_boundary, upper_boundary); // Upper Window boundary
		bc.addboundary(right_boundary, upper_boundary, right_boundary+100, lower_boundary); // Right Window boundary
		bc.addboundary(left_boundary, lower_boundary, right_boundary, lower_boundary+100); // Lower Window boundary
		
	    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	      @Override public void handle(KeyEvent event) {
    	  Vehicle enteredvehicle = player.getEnteredVehicle();
    	  if (enteredvehicle == null) {
	        switch (event.getCode()) {
	          case UP	, 	W	:  	player.setImageW(); player.setY(player.getY() + bc.collisioncheckY(player, - 50)); break;
	          case RIGHT,	D	: 	player.setImageD(); player.setX(player.getX() + bc.collisioncheckX(player, + 50)); break;
	          case DOWN	, 	S	: 	player.setImageS(); player.setY(player.getY() + bc.collisioncheckY(player, + 50)); break;
	          case LEFT	, 	A	: 	player.setImageA(); player.setX(player.getX() + bc.collisioncheckX(player, - 50)); break;
	          case E			:   player.setEnteredVehicle(tractor); break;
			default:
				break;
	        }
    	  }
	        else { // TODO collision check for vehicle
	        	switch (event.getCode()) {
		          case UP, 		W	:  	enteredvehicle.setImageW(); enteredvehicle.setY(enteredvehicle.getY() - 100); break;
		          case RIGHT,	D	: 	enteredvehicle.setImageD();enteredvehicle.setX(enteredvehicle.getX() + 100); break;
		          case DOWN	, 	S	: 	enteredvehicle.setImageS();enteredvehicle.setY(enteredvehicle.getY() + 100); break;
		          case LEFT	, 	A	: 	enteredvehicle.setImageA();enteredvehicle.setX(enteredvehicle.getX() - 100); break;
		          case E			:   player.setX(enteredvehicle.getX());	player.setY(enteredvehicle.getY()); player.setImageW(); enteredvehicle.exit(); player.setEnteredVehicle(null);  break;
				default:
					break;
	        }
	       // if(player.getY() < 50) { player.setY(player.getY() + 100); 	}
	        //if(player.getX() < 0) { player.setX(player.getX() + 100); 	}
	        //if(player.getY() > 1050) { player.setY(player.getY() - 100); } // Window ist 1050px hoch
	       // if(player.getX() > 1500) { player.setX(player.getX() - 100); } // Window ist 1500px breit
	      
	    }
    	  }});
	  
	}
	

	
	private void movePlayerOnMousePress(Scene scene, final Player player, final TranslateTransition transition) {
	    scene.setOnMousePressed(new EventHandler<MouseEvent>() {
	      @Override public void handle(MouseEvent event) {
	    	  int newX = ((int) event.getSceneX() / 50) * 50 + 10; // Rundet Position der Maus ab und zentriert Spieler
	    	  int newY = ((int) event.getSceneY() / 50) * 50 + 10; // 10 = (Feld.Breite - Spieler.Breite) / 2
	        if (!event.isControlDown()) {
	          player.setX(newX);
	          player.setY(newY);
	        } else {
	          transition.setToX(newX - player.getX());
	          transition.setToY(newY - player.getY());
	          transition.playFromStart();
	        }  
	      }
	    });
	  }
	
	private TranslateTransition createTransition(final Player player) {
	    final TranslateTransition transition = new TranslateTransition(Duration.seconds(0.25), player);
	    transition.setOnFinished(new EventHandler<ActionEvent>() {
	      @Override public void handle(ActionEvent event) {
	        player.setX(player.getTranslateX() + player.getX());
	        player.setY(player.getTranslateY() + player.getY());
	        player.setTranslateX(0);
	        player.setTranslateY(0);
	      }
	    });
	    return transition;
	  }
	
}