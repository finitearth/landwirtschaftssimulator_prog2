package machinery;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Cultivator extends Equipment {
	Image cultivatorW = new Image("File:./Images/CultivatorW.png"); 
	Image cultivatorA = new Image("File:./Images/CultivatorA.png");
	Image cultivatorS = new Image("File:./Images/CultivatorS.png");
	Image cultivatorD = new Image("File:./Images/CultivatorD.png");
	
	
	public Cultivator(int posX, int posY){
		this.setX(posX);
		this.setY(posY);
		this.setImage(cultivatorD);
	}
	
	public void cultivate(GridPane grid) {
		
	}
}
