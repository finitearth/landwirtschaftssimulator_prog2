package machinery;

import javafx.scene.image.Image;

/**
 * This class models the Dump Truck pendant.
 *
 * @author Lukas Bumüller
 * @version 1.0
 *
 */

public class DumpTruck extends Equipment {

	private int load;
	private int maxload;

	Image dumpTruckW = new Image("File:./Images/DumpTruckW.png", 50, 50, false, false);
	Image dumpTruckA = new Image("File:./Images/DumpTruckA.png", 50, 50, false, false);
	Image dumpTruckS = new Image("File:./Images/DumpTruckS.png", 50, 50, false, false);
	Image dumpTruckD = new Image("File:./Images/DumpTruckD.png", 50, 50, false, false);

	/**
	 * The constructor for the DumpTruck class. Sets the image for the direction of movement left.
	 *
	 * @param int posX 			the x coordinate of the Dump Truck.
	 *
	 * @param int posY 			the y coordinate of the Dump Truck.
	 * 
	 * @param int maxload		the maximum Load of the Dump Truck.
	 *
	 *
	 */
	public DumpTruck(int posX, int posY, int maxload) {
		this.setX(posX);
		this.setY(posY);
		this.maxload = maxload;
		this.setImage(dumpTruckA);
	}

	/**
	 * Method to set the image of the harvester for the direction of movement up.
	 */
	@Override
	public void setImageW() {
		this.setImage(dumpTruckW);
	}

	/**
	 * Method to set the image of the harvester for the direction of movement left.
	 */
	@Override
	public void setImageA() {
		this.setImage(dumpTruckA);
	}

	/**
	 * Method to set the image of the harvester for the direction of movement down.
	 */
	@Override
	public void setImageS() {
		this.setImage(dumpTruckS);
	}

	/**
	 * Method to set the image of the harvester for the direction of movement right.
	 */
	@Override
	public void setImageD() {
		this.setImage(dumpTruckD);
	}

	/**
	 * getter for the load variable.
	 *
	 * @return int load
	 */
	public int getLoad() {
		return load;
	}

	/**
	 * setter for the load variable.
	 *
	 * @param int load
	 */
	public void setLoad(int load) {
		this.load = load;
	}

	/**
	 * getter for the maxload variable.
	 *
	 * @return int maxload
	 */
	public int getMaxload() {
		return maxload;
	}

	/**
	 * setter for the maxload variable.
	 *
	 * @param int maxload
	 */
	public void setMaxload(int maxload) {
		this.maxload = maxload;
	}
}
