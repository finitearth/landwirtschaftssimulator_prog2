package machinery;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class Cultivator extends Equipment {
	Image cultivatorW = new Image("File:./Images/CultivatorW.png"); 
	Image cultivatorA = new Image("File:./Images/CultivatorA.png");
	Image cultivatorS = new Image("File:./Images/CultivatorS.png");
	Image cultivatorD = new Image("File:./Images/CultivatorD.png");
	String type = "Cultivator";

	public Cultivator(int posX, int posY){
		setX(posX);
		setY(posY);
		setImage(cultivatorD);
	}
	
	public void cultivate(GridPane grid) {
		
	}
	public void setImageA() {
		setImage(cultivatorA);
	}
	
	public void setImageW() {
		setImage(cultivatorW);
	}
	
	public void setImageS() {
		setImage(cultivatorS);
	}
	
	public void setImageD() {
		setImage(cultivatorD);
	}
	public String getType() {
		return type;
	}
}
