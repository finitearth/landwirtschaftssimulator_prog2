package resources;

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

public class aplication extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		Player player = new Player(10, 10);								// Spieler erstellen
		GridPane gridPane = generateGamefield();						// Spielfeld erstellen	
		final Group group = new Group(gridPane, player);
		Scene scene = new Scene(group);
		
		movePlayerOnKeyPress(scene, player);
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
		
		Image background = new Image("File:./Images/TestImage.png");	// Festes Spielfeld
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
	
	private void movePlayerOnKeyPress(Scene scene, Player player) {		// TODO implement proper collision detection
	    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	      @Override public void handle(KeyEvent event) {
	        switch (event.getCode()) {
	          case UP	, 	W	:  	player.setY(player.getY() - 50); break;
	          case RIGHT,	D	: 	player.setX(player.getX() + 50); break;
	          case DOWN	, 	S	: 	player.setY(player.getY() + 50); break;
	          case LEFT	, 	A	: 	player.setX(player.getX() - 50); break;
			default:
				break;
	        }
	        if(player.getY() < 0) { player.setY(player.getY() + 50); 	}
	        if(player.getX() < 0) { player.setX(player.getX() + 50); 	}
	        if(player.getY() > 1050) { player.setY(player.getY() - 50); } // Window ist 1050px hoch
	        if(player.getX() > 1500) { player.setX(player.getX() - 50); } // Window ist 1500px breit
	      }
	    });
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