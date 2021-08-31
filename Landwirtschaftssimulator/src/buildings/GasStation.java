package buildings;

import Fields.Field;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class GasStation extends Field{
	int costPerLiter;
	int cash;
	Image GasStation  = new Image("File:./Images/Tanke.png", 50, 50, false, false);
	
	public GasStation(int posX, int posY) {
		this.setX(posX);
		this.setY(posY);
		this.setImage(GasStation);
	}

	public int fuel(int amountOfFuel) {
		if(cash >= (costPerLiter * amountOfFuel)) {
			cash = cash - (costPerLiter * amountOfFuel);
			return amountOfFuel;
		}
		else {
			System.out.println("Not enough cash!"); //nur provisorisch
			return 0;
		}
	}
}
