package machinery;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Cultivator extends ImageView {
	Image cultivator = new Image("File:./Images/PlayerTest.png");
	
	
	public Cultivator(int posX, int posY){
		this.setX(posX);
		this.setY(posY);
		this.setImage(cultivator);
	}
	
	public void cultivate(GridPane grid) {
		
	}
}
