package machinery;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DumpTruck extends ImageView{
	
	private int load;
	private int maxload;
	
	Image dumpTruckW = new Image("File:./Images/PlayerTest.png"); // TODO Bilder ersetzen
	Image dumpTruckA = new Image("File:./Images/PlayerTest.png");
	Image dumpTruckS = new Image("File:./Images/PlayerTest.png");
	Image dumpTruckD = new Image("File:./Images/PlayerTest.png");
	
	public DumpTruck(int posX, int posY){
		this.setX(posX);
		this.setY(posY);
		this.setImage(dumpTruckA);
	}

	public int getLoad() {
		return load;
	}
	public void setLoad(int load) {
		this.load = load;
	}
	public int getMaxload() {
		return maxload;
	}
	public void setMaxload(int maxload) {
		this.maxload = maxload;
	}
}
