package machinery;

import javafx.scene.image.Image;
import buildings.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tractor extends Vehicle {
	Image TractorViewL = new Image("File:./Images/TractorTest.png", 100, 100, false, false);
	//Equiptment trailer;

	public Tractor(int x, int y, int maxfuel) {
		super(x, y, maxfuel);
		this.setX(x);
		this.setY(y);
		this.setImage(TractorViewL);
		//trailer = null;

	}

	//public void equip(Equipment equipment) {
		//if (trailer == null) {
		//	trailer = equipment;
		//	equipment.setEquipped = true;
	//	}

	//}

	public void deequip() {
		//trailer.setEquipped = false;
		//trailer = null;

	}
}