package machinery;

import javafx.scene.image.Image;

public class SeedDrill extends Equipment {
	Image seedDrillW = new Image("File:./Images/SeedDrillW.png", 50, 50, false, false); // TODO Bilder ersetzen
	Image seedDrillA = new Image("File:./Images/SeedDrillA.png", 50, 50, false, false);
	Image seedDrillS = new Image("File:./Images/SeedDrillS.png", 50, 50, false, false);
	Image seedDrillD = new Image("File:./Images/SeedDrillD.png", 50, 50, false, false);
	String type = "SeedDrill";

	public SeedDrill(int posX, int posY) {
		this.setX(posX);
		this.setY(posY);
		this.setImage(seedDrillA);
	}

	@Override
	public void setImageA() {
		setImage(seedDrillA);
	}

	@Override
	public void setImageW() {
		setImage(seedDrillW);
	}

	@Override
	public void setImageS() {
		setImage(seedDrillS);
	}

	@Override
	public void setImageD() {
		setImage(seedDrillD);
	}

	public void seed() {

	}
	@Override
	public String getType() {
		return type;
	}
}
