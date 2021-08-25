package machinery;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SeedDrill extends ImageView{
	Image seedDrillW = new Image("File:./Images/PlayerTest.png"); // TODO Bilder ersetzen
	Image seedDrillA = new Image("File:./Images/PlayerTest.png");
	Image seedDrillS = new Image("File:./Images/PlayerTest.png");
	Image seedDrillD = new Image("File:./Images/PlayerTest.png");
	
	
	public SeedDrill(int posX, int posY){
		this.setX(posX);
		this.setY(posY);
		this.setImage(seedDrillA);
	}
	
	
	public void seed() {
		
	}

}
