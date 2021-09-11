package machinery;

import javafx.scene.image.Image;

public class DumpTruck extends Equipment {

	private int load;
	private int maxload;

	Image dumpTruckW = new Image("File:./Images/DumpTruckW.png");
	Image dumpTruckA = new Image("File:./Images/DumpTruckA.png");
	Image dumpTruckS = new Image("File:./Images/DumpTruckS.png");
	Image dumpTruckD = new Image("File:./Images/DumpTruckD.png");

	public DumpTruck(int posX, int posY, int maxload) {
		this.setX(posX);
		this.setY(posY);
		this.maxload = maxload;
		this.setImage(dumpTruckA);
	}

	public void setImageW() {
		this.setImage(dumpTruckW);
	}

	public void setImageA() {
		this.setImage(dumpTruckA);
	}

	public void setImageS() {
		this.setImage(dumpTruckS);
	}

	public void setImageD() {
		this.setImage(dumpTruckD);
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
