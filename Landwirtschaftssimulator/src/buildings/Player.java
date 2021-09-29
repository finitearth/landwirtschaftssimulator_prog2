package buildings;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import machinery.Vehicle;
import utils.CollisionChecker;

/**
 * This class models the Player.
 *
 * @author Lukas Bumüller
 * @version 1.0
 *
 */
public class Player extends ImageView {

	Image playerImageW = new Image("File:./Images/PlayerW.png");
	Image playerImageA = new Image("File:./Images/PlayerA.png");
	Image playerImageS = new Image("File:./Images/PlayerS.png");
	Image playerImageD = new Image("File:./Images/PlayerD.png");
	Image playerImageCollided = new Image("File:./Images/PlayerTest.png");

	private Vehicle enteredvehicle = null;

	/**
	 * The constructor for the Player class. Sets the image for the direction of
	 * movement down.
	 *
	 * @param int posX the x coordinate of the Dump Truck.
	 *
	 * @param int posY the y coordinate of the Dump Truck.
	 * 
	 */
	public Player(int posX, int posY) {
		this.setX(posX);
		this.setY(posY);
		this.setImage(playerImageS);
	}

	/**
	 * Method to set the image of the player for the direction of movement up.
	 */
	public void setImageW() {
		this.setImage(playerImageW);
	}

	/**
	 * Method to set the image of the player for the direction of movement left.
	 */
	public void setImageA() {
		this.setImage(playerImageA);
	}

	/**
	 * Method to set the image of the player for the direction of movement down.
	 */
	public void setImageS() {
		this.setImage(playerImageS);
	}

	/**
	 * Method to set the image of the player for the direction of movement right.
	 */
	public void setImageD() {
		this.setImage(playerImageD);
	}

	/**
	 * Method to remove the Image of the player.
	 */
	public void setNoImage() {
		this.setImage(null);
	}

	/**
	 * getter for the enterdvehicle variable.
	 *
	 * @return String enteredvehicle
	 */
	public Vehicle getEnteredVehicle() {
		return enteredvehicle;
	}

	/**
	 * Method to remove the Image of the player.
	 */
	public void setEnteredVehicle(Vehicle vehicle, Vehicle vehicleTest) {
		// Boolean enterable = vehicle.enter(this);
		if (vehicle == null && enteredvehicle == vehicleTest) {
			enteredvehicle = null;
			return;
		}

		else if (enteredvehicle == null && vehicle != null) {
			setNoImage();
			enteredvehicle = vehicle;
		}

	}

	public void moveup(CollisionChecker bc, double walkingspeed) {
		setImageW();
		setY(getY() + bc.collisioncheckY(getX(), getY(), -walkingspeed));
	}

	public void moveright(CollisionChecker bc, double walkingspeed) {
		setImageD();
		setX(getX() + bc.collisioncheckX(getX(), getY(), +walkingspeed));
	}

	public void movedown(CollisionChecker bc, double walkingspeed) {
		setImageS();
		setY(getY() + bc.collisioncheckY(getX(), getY(), +walkingspeed));
	}

	public void moveleft(CollisionChecker bc, double walkingspeed) {
		setImageA();
		setX(getX() + bc.collisioncheckX(getX(), getY(), -walkingspeed));
	}

}
