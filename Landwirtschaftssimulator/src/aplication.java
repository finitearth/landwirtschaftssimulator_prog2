package resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class aplication extends Application {
	
	static Movement movement = Movement.left;
	
	public enum Movement{
		left,right, up, down
	}


	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {

		Scene scene = new Scene(generateGamefield());  // Spielfeld erstellen
		
		scene.addEventFilter(KeyEvent.KEY_PRESSED, key ->{
			if(key.getCode() == KeyCode.W) {
				movement = Movement.up;
			}
			if(key.getCode() == KeyCode.A) {
				movement = Movement.left;
			}
			if(key.getCode() == KeyCode.S) {
				movement = Movement.down;
			}
			if(key.getCode() == KeyCode.D) {
				movement = Movement.right;
			}
		
		});
		
		stage.setScene(scene);
		stage.show();
		
	}
	
	public GridPane generateGamefield() {
		
		Player player = new Player(10, 8);
		
		GridPane grid = new GridPane();
		
		grid.addEventFilter(KeyEvent.KEY_PRESSED, key ->{
			if(key.getCode() == KeyCode.W) {
				movement = Movement.up;
			}
			if(key.getCode() == KeyCode.A) {
				movement = Movement.left;
			}
			if(key.getCode() == KeyCode.S) {
				movement = Movement.down;
			}
			if(key.getCode() == KeyCode.D) {
				movement = Movement.right;
			}
		
		});
		
		Image background = new Image("File:./Images/TestImage.png");	// Festes Spielfeld
		ImageView backg = new ImageView(background);
		
		for (int i = 0; i < 30; i++) {
            ColumnConstraints column = new ColumnConstraints(50);		// Spielfeldgr��e
            grid.getColumnConstraints().add(column);
		}
		for (int i = 0; i < 21; i++) {
            RowConstraints row = new RowConstraints(50);
            grid.getRowConstraints().add(row);
		}
		
		grid.add(backg, 0, 10);											// Landschaft
		
//		for(int j = 8; j <= 10; j++) {
//		for (int i = 8; i <=23; i++) grid.add(new Field(), i, j);		// Feld1
//		}
//		for(int j = 12; j <= 17; j++) {
//			for (int i = 8; i <=12; i++) grid.add(new Field(), i, j);	// Feld2
//		}
//		for(int j = 12; j <= 17; j++) {
//			for (int i = 14; i <=23; i++) grid.add(new Field(), i, j);	// Feld 3
//		}
		
//		Image bitmap = new Image("File:./Images/Bitmap.png");
		File file = new File("Images/Bitmap.bmp");
		BufferedImage bitmap;
		try {
			bitmap = ImageIO.read(file);
			for (int y = 0; y < bitmap.getHeight(); y++) {
			    for (int x = 0; x < bitmap.getWidth(); x++) {
			          int  clr   = bitmap.getRGB(x, y); 
//			          System.out.println(clr);
			          if(clr == -10728) {
			        	  grid.add(new Field(), x, y);
			          }
//			          int  red   = (clr & 0x00ff0000) >> 16;
//			          int  green = (clr & 0x0000ff00) >> 8;
//			          int  blue  =  clr & 0x000000ff;
//			          bitmap.setRGB(x, y, clr);
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		grid.add(player, player.getPosX(), player.getPosY());			// Spielfigur
		
		
		switch(movement) {
		case up:
			player.relocate(player.getTranslateX(), player.getTranslateY()-1);
			break;
		case down:
			player.relocate(player.getPosX(), player.getPosY()+1);

			break;
		case left:
			player.relocate(player.getPosX()-1, player.getPosY());

			break;
		case right:
			player.setLayoutX(player.getPosX()+1);

			break;
		}
		
		grid.setGridLinesVisible(true);
		return grid;
		
	}

}
