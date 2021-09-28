package machinery;

import Fields.ArableField;
import Utils.AvailableObjectsNearby;
import Utils.CollisionChecker;
import javafx.scene.image.Image;
import settings.GameState;

/**
 * This class models the tractor vehicle. Particularly it updates the images of
 * the tractor according to the direction it is driving to, as well as checking
 * if there are arablefields nearby it can interact with using its equipment.
 *
 * @author Tom Zehle
 * @version 1.0
 *
 */
public class Tractor extends Vehicle {

	Image TractorViewA = new Image("File:./Images/TractorA.png", 50, 50, false, false);
	Image TractorViewD = new Image("File:./Images/TractorD.png", 50, 50, false, false);
	Image TractorViewW = new Image("File:./Images/TractorW.png", 50, 50, false, false);
	Image TractorViewS = new Image("File:./Images/TractorS.png", 50, 50, false, false);
	AvailableObjectsNearby aonb;

	/**
	 * The trailer (seeddrill, dump truck, harvester or null) attached to the
	 * tractor. Intialized to be null (no equipment attached).
	 */
	public Equipment trailer = null;
	double speed;
	CollisionChecker bc;

	/**
	 * The constructor for the tractor class. Calls the super constructor of
	 * vehicle. Sets the image to facing to the left and sets the variable save.
	 *
	 * @param int x the x coordinate of the tractor.
	 *
	 * @param int y the y coordinate of the tractor.
	 *
	 * @param AvailableObjectsNearby aonb the instance of AONB, allowing to search
	 * for nearby objects.
	 *
	 * @param GameState gs the instance of the game state, only used to pass it to
	 * the super constructor.
	 */
	public Tractor(int x, int y, int maxfuel, double speed, CollisionChecker bc, AvailableObjectsNearby aonb,
			GameState gs) {
		super(x, y, maxfuel, gs);
		setImage(TractorViewA);
		this.aonb = aonb;
		this.speed = speed;
		this.bc = bc;
	}

	/**
	 * Checks if there are actions to be made if a movement is detected If a field
	 * is nearby and a cultivator is attached: cultivate that field! If a field is
	 * nearby and a seeddrill is attached: sow that field!
	 */
	public void checkmovementactions() {
		if (trailer != null) { // is there a trailer attached?
			if (trailer.getType() == "Cultivator") { // is a cultivator attached?
				ArableField field = (ArableField) aonb.search(getX() + 25, getY() + 25, "ArableField"); // find the
																										// field nearby
				if (field != null) { // did the aonb.search method return a field?
					field.cultivate(); // cultivate it!
				}
			} else if (trailer.getType() == "SeedDrill") { // is there a seeddrill attached?
				ArableField field = (ArableField) aonb.search(getX() + 25, getY() + 25, "ArableField"); // find the
																										// field nearby
				if (field != null) { // if the aonb.search method returned a field
					field.sow(); // sow it!

				}
			}
		}
	}
	
	/**
	* getter for the trailer
	*
	* @return Trailer trailer
	*/
	public Equipment getTrailer() {
		return trailer;
	}

	/**
	 * Equips a equipment if there's no trailer attached yet by setting the trailer
	 * variable to the passed equipment parameter. Deequip if there is already a
	 * trailer attached.
	 *
	 * @param Equipment equipment the equipment supposed to be equipped.
	 *
	 */
	public void equip(Equipment equipment) {
		if (trailer == null) { // is there no trailer yet?
			trailer = equipment;
		} else {
			deequip();
		}
	}

	/**
	 * Deequips the trailer by setting the trailer variable to null.
	 */
	public void deequip() {
		trailer = null;
	}

	/**
	 * sets the image to facing up and updates the coordinates of the tractor. Calls
	 * checkmovementactions. If a trailer is attached, its image as well as its
	 * coordinates are updated accordingly. Checks for collisions.
	 */
	public void moveup() {
		setImage(TractorViewW);
		updatefuel();
		setY(getY() + bc.collisioncheckY(getX() + 25, getY() + 25, -speed)); 	// +25 so the center of the vehicle will be
																				// checked, not the top left corner as
																				// suggested by the package
		checkmovementactions();
		if (trailer != null) {
			trailer.setX(getX());
			trailer.setY(getY() + 50); // +50 so it doesnt overlap with the tractor
			trailer.setImageW();
		}

	}

	/**
	 * sets the image to facing to the right and updates the coordinates of the
	 * tractor. Calls checkmovementactions. If a trailer is attached, its image as
	 * well as its coordinates are updated accordingly. Checks for collisions.
	 */
	public void moveright() {
		setImage(TractorViewD);
		updatefuel();
		setX(getX() + bc.collisioncheckX(getX() + 25, getY() + 25, +speed));
		checkmovementactions();
		if (trailer != null) {
			trailer.setX(getX() - 40); // -40 so it does not overlap with the tractor
			trailer.setY(getY());
			trailer.setImageD();
		}
	}

	/**
	 * sets the image to facing down and updates the coordinates of the tractor.
	 * Calls checkmovementactions. If a trailer is attached, its image as well as
	 * its coordinates are updated accordingly. Checks for collisions.
	 */
	public void movedown() {
		setImage(TractorViewS);
		updatefuel();
		setY(getY() + bc.collisioncheckY(getX() + 25, getY() + 25, +speed));
		checkmovementactions();
		if (trailer != null) {
			trailer.setX(getX());
			trailer.setY(getY() - 30);
			trailer.setImageS();
		}
	}

	/**
	 * sets the image to facing to the left and updates the coordinates of the
	 * tractor. Calls checkmovementactions. If a trailer is attached, its image as
	 * well as its coordinates are updated accordingly. Checks for collisions.
	 */
	public void moveleft() {
		setImage(TractorViewA);
		updatefuel();
		setX(getX() + bc.collisioncheckX(getX() + 25, getY() + 25, -speed));
		checkmovementactions();
		if (trailer != null) {
			trailer.setX(getX() + 50);
			trailer.setY(getY());
			trailer.setImageA();
		}

	}

	/**
	 * getter for the fuel variable
	 *
	 * @return int fuel
	 */
	public int getFuel() {
		return fuel;
	}

	/**
	 * Setter for the fuel variable.
	 *
	 * @param int fuel - the amount of fuel the tractor is supposed to have.
	 */
	public void setFuel(int fuel) {
		this.fuel = fuel;

	}
	
	/**
	* Setter for the ImageD of the tractor
	*/
	public void setImageD() {
		setImage(TractorViewW);

	}
}
