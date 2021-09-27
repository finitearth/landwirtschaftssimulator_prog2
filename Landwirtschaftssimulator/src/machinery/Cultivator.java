package machinery;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class Cultivator extends Equipment {
	Image cultivatorW = new Image("File:./Images/CultivatorW.png");
	Image cultivatorA = new Image("File:./Images/CultivatorA.png");
	Image cultivatorS = new Image("File:./Images/CultivatorS.png");
	Image cultivatorD = new Image("File:./Images/CultivatorD.png");
	String type = "Cultivator";

	public Cultivator(int posX, int posY) {
		setX(posX);
		setY(posY);
		setImage(cultivatorD);
	}

	@Override
	public void setImageA() {
		setImage(cultivatorA);
	}

	@Override
	public void setImageW() {
		setImage(cultivatorW);
	}

	@Override
	public void setImageS() {
		setImage(cultivatorS);
	}

	@Override
	public void setImageD() {
		setImage(cultivatorD);
	}

	@Override
	public String getType() {
		return type;
	}
}
