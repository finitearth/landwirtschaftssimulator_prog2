

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
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

public class aplication2 extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Pane welcome = new Pane();
		welcome.setPrefSize(1500, 1050);
		Button newGame = new Button("New Game");
		newGame.setFont(new Font("Arial", 30));
		newGame.setPrefSize(200, 20);
		newGame.relocate(650, 470);
		newGame.setOnMouseClicked(e -> {stage.setScene(chooseSettings(stage));});
		Button load = new Button("Load");
		load.setFont(new Font("Arial", 30));
		load.setPrefSize(200, 20);
		load.relocate(650, 580);
		load.setOnMouseClicked(e -> { }); // TODO M�glichkeit zum Spielstand laden
		
		Image backg = new Image("File:./Images/back.png", 1500, 1050, false, false); // TODO Hintergrundbild erstellen
		BackgroundImage backgroundImage = new BackgroundImage(backg,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
															  BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		welcome.setBackground(new Background(backgroundImage));
		
		welcome.getChildren().addAll(load, newGame);
		
		Scene scene = new Scene(welcome);
		
		stage.setScene(scene);
		stage.setTitle("Farmland");
		stage.show();
		
	}
	
	public Scene chooseSettings(Stage stage) {
		Pane settings = new Pane();
		settings.setPrefSize(1500, 1050);
		
		Label headline = new Label("Einstellungen");
		headline.setFont(new Font("Arial", 30));
		headline.setPrefSize(200, 20);
		headline.relocate(650, 30);
		
		Label nameDescription = new Label("Charakter Name");
		nameDescription.setFont(new Font("Arial", 16));
		nameDescription.setPrefSize(200, 10);
		nameDescription.relocate(250, 300);
		
		TextField name = new TextField();
		name.setPrefSize(200, 20);
		name.relocate(250, 330);
		
		Label difficultlyLevel = new Label("Schwierigkeitsgrad");
		difficultlyLevel.setFont(new Font("Arial", 16));
		difficultlyLevel.setPrefSize(400, 20);
		difficultlyLevel.relocate(250, 500);
		
		ToggleGroup difficultly = new ToggleGroup();
		RadioButton easy = new RadioButton("Einfach");
		easy.setToggleGroup(difficultly);
		easy.setSelected(true);
		easy.relocate(250, 530);
		RadioButton middle = new RadioButton("Mittel");
		middle.setToggleGroup(difficultly);
		middle.relocate(330, 530);
		RadioButton hard = new RadioButton("Schwer");
		hard.setToggleGroup(difficultly);
		hard.relocate(400, 530);
		
		ToggleGroup playerChoose = new ToggleGroup();
		
		RadioButton player1 = new RadioButton();
		player1.setToggleGroup(playerChoose);
		player1.setSelected(true);
		player1.relocate(1050, 300);
		Image player1Image = new Image("File:./Images/PlayerS.png", 50, 50, false, false);
		ImageView player1View = new ImageView(player1Image);
		player1View.relocate(1100, 280);
		
		RadioButton player2 = new RadioButton();
		player2.setToggleGroup(playerChoose);
		player2.relocate(1050, 400);
		Image player2Image = new Image("File:./Images/PlayerS.png", 50, 50, false, false);
		ImageView player2View = new ImageView(player2Image);
		player2View.relocate(1100, 380);
		
		RadioButton player3 = new RadioButton();
		player3.setToggleGroup(playerChoose);
		player3.relocate(1050, 500);
		Image player3Image = new Image("File:./Images/PlayerS.png", 50, 50, false, false);
		ImageView player3View = new ImageView(player3Image);
		player3View.relocate(1100, 480);
		
		RadioButton player4 = new RadioButton();
		player4.setToggleGroup(playerChoose);
		player4.relocate(1050, 600);
		Image player4Image = new Image("File:./Images/PlayerS.png", 50, 50, false, false);
		ImageView player4View = new ImageView(player4Image);
		player4View.relocate(1100, 580);
		
		
		Button start = new Button("Start");
		start.setFont(new Font("Arial", 25));
		start.setPrefSize(100, 20);
		start.relocate(700, 850);
		start.setOnMouseClicked(e -> {stage.setScene(generateGame());});
		
		Image backg = new Image("File:./Images/", 1500, 1050, false, false); // TODO Hintergrundbild erstellen
		BackgroundImage backgroundImage = new BackgroundImage(backg,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
															  BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		settings.setBackground(new Background(backgroundImage));
		
		settings.getChildren().addAll(headline, nameDescription, name, start, difficultlyLevel,
									  easy, middle, hard, player1, player1View, player2, player2View,
									  player3, player3View, player4, player4View);
		
		Scene scene = new Scene(settings);
		
		return scene;
		
	}
	
	public Scene generateGame() {
		GridPane grid = new GridPane();

		for (int i = 0; i < 30; i++) {
            ColumnConstraints column = new ColumnConstraints(50);		// Spielfeldgrösse
            grid.getColumnConstraints().add(column);
		}
		for (int i = 0; i < 21; i++) {
            RowConstraints row = new RowConstraints(50);
            grid.getRowConstraints().add(row);
		}
		
		Image background = new Image("File:./Images/Map.png");	
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
		
		Player player = new Player(500,500);
		Tractor tractor = new Tractor(450, 450, 10000);
		GasStation gasStation = new GasStation(250,350);
		Cultivator cultivator = new Cultivator(160, 160);
		SeedDrill seeddrill = new SeedDrill(160, 300);
		Landtrade landtrade = new Landtrade(1200, 550);
		Farmyard farmyard = new Farmyard(1300, 450);
		
		AvailableObjectsNearby aonb = new AvailableObjectsNearby();
		aonb.add(tractor);
		aonb.add(cultivator);
		aonb.add(gasStation);
		aonb.add(landtrade);
		aonb.add(farmyard);
		aonb.add(seeddrill);
		
		final Group group = new Group(grid, player, tractor, cultivator,gasStation,farmyard,landtrade, seeddrill);
		Scene scene = new Scene(group);
		
		movePlayerOnKeyPress(scene, player, aonb);
		movePlayerOnMousePress(scene, player, createTransition(player));
		
		return scene;
		
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