package machinery;

import javafx.scene.image.Image;

public class SeedDrill extends Equipment {
	Image seedDrillW = new Image("File:./Images/SeedDrill.jpg", 50, 50, false, false); // TODO Bilder ersetzen
	Image seedDrillA = new Image("File:./Images/SeedDrill.jpg", 50, 50, false, false);
	Image seedDrillS = new Image("File:./Images/SeedDrill.jpg", 50, 50, false, false);
	Image seedDrillD = new Image("File:./Images/SeedDrill.jpg", 50, 50, false, false);

	public SeedDrill(int posX, int posY) {
		this.setX(posX);
		this.setY(posY);
		this.setImage(seedDrillA);
	}

	public void setImageA() {
		setImage(seedDrillA);
	}

	public void setImageW() {
		setImage(seedDrillW);
	}

	public void setImageS() {
		setImage(seedDrillS);
	}

	public void setImageD() {
		setImage(seedDrillD);
	}

	public void seed() {

	}

}
