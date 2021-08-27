package machinery;

import javafx.scene.image.Image;
import buildings.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tractor extends Vehicle {
	Image TractorViewA = new Image("File:./Images/TractorTestL.png", 100, 100, false, false);
	Image TractorViewD = new Image("File:./Images/TractorTestR.png", 100, 100, false, false);
	Image TractorViewW = new Image("File:./Images/TractorTestW.png", 100, 100, false, false);
	Image TractorViewS = new Image("File:./Images/TractorTestS.png", 100, 100, false, false);
	//Equiptment trailer;

	public Tractor(int x, int y, int maxfuel) {
		super(x, y, maxfuel);
		this.setImage(TractorViewA);
		
		//trailer = null;

	}

		@Override
		public void setImageW() {
			
			this.setImage(TractorViewW);
		}
		@Override
		public void setImageA() {
			this.setImage(TractorViewA);
		}
		@Override
		public void setImageS() {
			this.setImage(TractorViewS);
		}
		@Override
		public void setImageD() {
			this.setImage(TractorViewD);
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