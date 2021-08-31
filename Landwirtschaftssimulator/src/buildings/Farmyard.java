package buildings;

import Fields.Field;
import javafx.scene.image.Image;

public class Farmyard  extends Field{
	private int siloLevel;
	private int maxSiloLevel;
	Image Farmyard  = new Image("File:./Images/Scheune.png", 50, 50, false, false);
	String[] machineParking = {};
	
	public Farmyard(int posX, int posY) {
		this.setX(posX);
		this.setY(posY);
		this.setImage(Farmyard);
	}
	
	public int fillSilo(int amountOfFilling) {
		siloLevel = siloLevel + amountOfFilling;
		if(siloLevel > maxSiloLevel) {
			siloLevel = maxSiloLevel;
			return siloLevel - maxSiloLevel;
		}
		else return 0;
	}
	public int clearSilo(int amountOfClear) {
		if(siloLevel >= amountOfClear) {
			siloLevel = siloLevel - amountOfClear;
			return amountOfClear;
		}
		else {
			int filling = siloLevel;
			siloLevel = 0;
			System.out.println("You just get " + filling); //nur provisorisch
			return filling;
		}
	}
	public void machinePickUp() {
		
	}
	public void machineStore() {
		
	}
}
