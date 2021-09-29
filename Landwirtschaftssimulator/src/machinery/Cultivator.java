package machinery;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

/**
 * This class models the Cultivator pendant.
 *
 * @author Lukas Bumüller
 * @version 1.0
 *
 */
public class Cultivator extends Equipment {
	Image cultivatorW = new Image("File:./Images/CultivatorW.png");
	Image cultivatorA = new Image("File:./Images/CultivatorA.png");
	Image cultivatorS = new Image("File:./Images/CultivatorS.png");
	Image cultivatorD = new Image("File:./Images/CultivatorD.png");
	String type = "Cultivator";

	/**
	 * The constructor for the Cultivator class. Sets the image for the direction of movement left.
	 *
	 * @param int posX 			the x coordinate of the Dump Truck.
	 *
	 * @param int posY 			the y coordinate of the Dump Truck.
	 * 
	 */
	public Cultivator(int posX, int posY) {
		setX(posX);
		setY(posY);
		setImage(cultivatorA);
	}

	/**
	 * Method to set the image of the cultivator for the direction of movement left.
	 */
	@Override
	public void setImageA() {
		setImage(cultivatorA);
	}

	/**
	 * Method to set the image of the cultivator for the direction of movement up.
	 */
	@Override
	public void setImageW() {
		setImage(cultivatorW);
	}

	/**
	 * Method to set the image of the cultivator for the direction of movement down.
	 */
	@Override
	public void setImageS() {
		setImage(cultivatorS);
	}

	/**
	 * Method to set the image of the cultivator for the direction of movement right.
	 */
	@Override
	public void setImageD() {
		setImage(cultivatorD);
	}

	/**
	 * getter for the type variable.
	 *
	 * @return String type
	 */
	@Override
	public String getType() {
		return type;
	}
}
