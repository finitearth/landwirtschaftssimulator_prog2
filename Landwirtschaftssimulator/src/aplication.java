

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
import Utils.NotificationPopUp;
import Fields.Field;
import buildings.Player;
import Utils.CollisionChecker;
import machinery.Tractor;
import machinery.Vehicle;
import machinery.Cultivator;
import machinery.Equipment;
import machinery.SeedDrill;
import Utils.AvailableObjectsNearby;
import buildings.GasStation;
import buildings.Landtrade;
import buildings.Farmyard;

public class aplication extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		Player player = new Player(500,500);								// Spieler erstellen
		GridPane gridPane = generateGamefield();						// Spielfeld erstellen	
		Tractor tractor = new Tractor(450, 450, 10000);
		GasStation gasStation = new GasStation(250,350);
		Cultivator cultivator = new Cultivator(400, 400);
		SeedDrill seeddrill = new SeedDrill(400, 500);
		Landtrade landtrade = new Landtrade(1200, 550);
		Farmyard farmyard = new Farmyard(1300, 450);
		
		AvailableObjectsNearby aonb = new AvailableObjectsNearby();
		aonb.add(tractor);
		aonb.add(cultivator);
		aonb.add(gasStation);
		aonb.add(landtrade);
		aonb.add(farmyard);
		aonb.add(seeddrill);
		
		final Group group = new Group(gridPane, player, tractor, cultivator,gasStation,farmyard,landtrade, seeddrill);
		Scene scene = new Scene(group);
		
		movePlayerOnKeyPress(scene, player, aonb);
		movePlayerOnMousePress(scene, player, createTransition(player));
		
		stage.setScene(scene);
		stage.setTitle("Farmland");
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
		
		Image background = new Image("File:./Images/Map.png");	// TODO Bild ersetzen   Festes Spielfeld
		ImageView backg = new ImageView(background);
		grid.add(backg, 0, 10);											// Landschaft

		/*
		 * File file = new File("Images/Bitmap.bmp"); // Weizenfelder BufferedImage
		 * bitmap;
		 */
		/*
		 * try { bitmap = ImageIO.read(file); for (int y = 0; y < bitmap.getHeight();
		 * y++) { for (int x = 0; x < bitmap.getWidth(); x++) { //
		 * System.out.println(bitmap.getRGB(x, y)); if(bitmap.getRGB(x, y) == -10728) {
		 * grid.add(new Field(), x, (y+1)); } } } } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
			
		grid.setGridLinesVisible(true);
		
		
		
		return grid;	
		
		
	}
	
	private void movePlayerOnKeyPress(Scene scene, Player player, AvailableObjectsNearby aonb) { // TODO transitions 

		int upper_boundary = 50-50;
		int left_boundary = 0-50;
		int right_boundary = 1500;
		int lower_boundary = 1050;
		CollisionChecker bc = new CollisionChecker();
		bc.addboundary(left_boundary-100,  upper_boundary, left_boundary, lower_boundary); // Left Window boundary
		bc.addboundary(left_boundary, upper_boundary-100, right_boundary, upper_boundary); // Upper Window boundary
		bc.addboundary(right_boundary, upper_boundary, right_boundary+100, lower_boundary); // Right Window boundary
		bc.addboundary(left_boundary, lower_boundary, right_boundary, lower_boundary+100); // Lower Window boundary
		
		bc.addboundary(550, 700, 650, 1000); // lower river and forest
		bc.addboundary(700, 350, 750, 650); // middle river
		bc.addboundary(600, 400, 850, 450); // river curls left
		bc.addboundary(600, 350, 650, 400); // river before bridge
		bc.addboundary(300, 250, 500, 300); // above gasstation
		bc.addboundary(0, 50, 300, 250); // upper right forest
		bc.addboundary(0, 300, 200, 350); //
	    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	      @Override public void handle(KeyEvent event) {
    	  Vehicle enteredvehicle = player.getEnteredVehicle();
    	  double walkingspeed = 6.25;
    	  double drivingspeed = 50;
    	  if (enteredvehicle == null) {
	        switch (event.getCode()) {
	          case UP	, 	W	:  	player.moveup(bc, walkingspeed); break;
	          case RIGHT,	D	: 	player.moveright(bc, walkingspeed); break;
	          case DOWN	, 	S	: 	player.movedown(bc, walkingspeed); break;
	          case LEFT	, 	A	: 	player.moveleft(bc, walkingspeed); break;
	          case E			:   player.setEnteredVehicle((Vehicle) aonb.search(player.getX(), player.getY(), "machinery.Vehicle")); break;
			default:
				break;
	        }
    	  }
    	  else if (enteredvehicle.getClass() == Tractor.class) { 
    		  Tractor tractor = (Tractor) enteredvehicle;
    		  ArrayList<String> actions = new ArrayList<>();
    		  actions.add("test");
    		  NotificationPopUp wind = new NotificationPopUp("test", actions);
    		  switch (event.getCode()) {
		          case UP, 		W	:  	tractor.moveup(bc, drivingspeed); break;
		          case RIGHT,	D	: 	tractor.moveright(bc, drivingspeed); break;
		          case DOWN	, 	S	: 	tractor.movedown(bc, drivingspeed); break;
		          case LEFT	, 	A	: 	tractor.moveleft(bc, drivingspeed); break;
		          case E			:   player.setX(tractor.getX());	player.setY(tractor.getY()); player.setImageW(); tractor.exit(); player.setEnteredVehicle(null);  break;
		          case X			: 	tractor.equip((Equipment) aonb.search(tractor.getX(), tractor.getY(), "machinery.Equipment")); break;
		          case F			: 	System.out.println(wind.display());
		          case Z 			: 	System.out.println(tractor.getX()); System.out.println(tractor.getY());
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