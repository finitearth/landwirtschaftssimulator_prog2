package machinery;

import javafx.scene.image.Image;


/**
* This class models the seed drill vehicle. Particularly it updates the images of the seeddrill according to the direction it is driving to.
* @author Lukas Bumüller and Tom Zehle
* @version 1.0
*
*/
public class SeedDrill extends Equipment {
	Image seedDrillW = new Image("File:./Images/SeedDrillW.png", 50, 50, false, false);
	Image seedDrillA = new Image("File:./Images/SeedDrillA.png", 50, 50, false, false);
	Image seedDrillS = new Image("File:./Images/SeedDrillS.png", 50, 50, false, false);
	Image seedDrillD = new Image("File:./Images/SeedDrillD.png", 50, 50, false, false);
	String type = "SeedDrill";

	/*
	 * Constructor of the SeedDrill class. Sets the position in x and y coordinates and sets the image by default facing to the left.
	 * @param x the x-coordinate of the seeddrill.
	 * @param y the y-coordinate of the seeddrill.
	 */
	public SeedDrill(int x, int y) {
		this.setX(x);
		this.setY(y);
		this.setImage(seedDrillA);
	}

	/* 
	 * Updates the image of the vehicle so it is facing to the left.
	 */
	@Override
	public void setImageA() {
		setImage(seedDrillA);
	}
	/* 
	 * Updates the image of the vehicle so it is facing up.
	 */
	@Override
	public void setImageW() {
		setImage(seedDrillW);
	}
	/* 
	 * Updates the image of the vehicle so it is facing down.
	 */
	@Override
	public void setImageS() {
		setImage(seedDrillS);
	}

	/*
	 * Updates the image of the vehicle so it is facing to the right.
	 */
	@Override
	public void setImageD() {
		setImage(seedDrillD);
	}

	/* 
	 * Getter for the type of vehicle it is
	 * @return String type - the type of the vehicle.
	 */
	@Override
	public String getType() {
		return type;
	}
}
