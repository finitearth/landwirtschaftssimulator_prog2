package machinery;

import Fields.ArableField;
import Utils.AvailableObjectsNearby;
import Utils.CollisionChecker;
import javafx.scene.image.Image;
import settings.GameState;

/**
 * This class models the harvester vehicle. Particularly it updates the images
 * of the tractor according to the direction it is driving to, as well as
 * checking if there are arablefields nearby it can interact with by harvesting
 * it.
 *
 * @author Tom Zehle
 * @version 1.0
 *
 */
public class Harvester extends Vehicle {
	public int graintank;
	public int maxgraintank;
	AvailableObjectsNearby aonb;
	CollisionChecker cb;
	double speed;

	Image ViewA = new Image("File:./Images/HarvesterA.png", 50, 50, false, false);
	Image ViewD = new Image("File:./Images/HarvesterD.png", 50, 50, false, false);
	Image ViewW = new Image("File:./Images/HarvesterW.png", 50, 50, false, false);
	Image ViewS = new Image("File:./Images/HarvesterS.png", 50, 50, false, false);

	/*
	 * The constructor for the harvester class. Calls the super constructor of
	 * vehicle. Sets the image to facing to the left and sets the variable save.
	 * 
	 * @param int x the x coordinate of the harvester.
	 * 
	 * @param int y the y coordinate of the harvester.
	 * 
	 * @param AvailableObjectsNearby aonb the instance of AONB, allowing to search
	 * for nearby objects.
	 * 
	 * @param GameState gs the instance of the game state, only used to pass it to
	 * the super constructor.
	 */
	public Harvester(int x, int y, int maxfuel, int maxgraintank, double speed, CollisionChecker cb,
			AvailableObjectsNearby aonb, GameState gs) {
		super(x, y, maxfuel, gs);
		graintank = 0;
		this.cb = cb;
		this.maxgraintank = maxgraintank;
		setImage(ViewA);
		this.aonb = aonb;
		this.speed = speed;
	}

	/*
	 * Method to fill the graintank by a certain amount of grain.
	 * 
	 * @param int grainamount the amount of grain the tank is supposed to be filled
	 * by.
	 */
	public void fill(int grainamount) {
		graintank += grainamount;
	}

	/*
	 * Method to harvest a field. Use aonb.search in order to find the nearest field
	 * nearby. If it returns null, there is no field nearby -> do nothing. If there
	 * is a field nearby: harvest it!
	 */
	public void harvest() {
		ArableField field = (ArableField) aonb.search(getX(), getY(), "ArableField");
		if (field != null) {
			field.harvest(this);
		}
	}

	/*
	 * sets the image to facing up and updates the coordinates of the tractor. Calls
	 * harvest. Checks for Collision.
	 */
	public void moveup() {
		setImage(ViewW);
		updatefuel();
		setY(getY() + cb.collisioncheckY(getX(), getY(), -speed));
		harvest();
	}

	/*
	 * sets the image to facing up and updates the coordinates of the tractor. Calls
	 * harvest. Checks for Collision.
	 */
	public void moveright() {
		setImage(ViewD);
		updatefuel();
		setX(getX() + cb.collisioncheckX(getX(), getY(), +speed));
		harvest();
	}

	/*
	 * sets the image to facing down and updates the coordinates of the tractor.
	 * Calls harvest. Checks for Collision.
	 */
	public void movedown() {
		setImage(ViewS);
		updatefuel();
		setY(getY() + cb.collisioncheckY(getX(), getY(), +speed));
		harvest();
	}

	/*
	 * sets the image to facing left and updates the coordinates of the tractor.
	 * Calls harvest. Checks for Collision.
	 */
	public void moveleft() {
		setImage(ViewA);
		updatefuel();
		setX(getX() + cb.collisioncheckX(getX(), getY(), -speed));
		harvest();
	}

	/*
	 * getter for the fuel variable.
	 * 
	 * @return int fuel
	 */
	public int getFuel() {
		return fuel;
	}

	/*
	 * setter for the fuel variable.
	 * 
	 * @param int fuel
	 */
	public void setFuel(int fuel) {
		this.fuel = fuel;

	}
	
	/* 
	 * getter for the graintank variable.
	 * @return int graintank
	 */

	public int getLoad() {
		return graintank;
	}

	/*
	 * setter for the graintankvariable.
	 * @param int load the amount of load it is supposed to have.
	 */
	public void setLoad(int load) {
		graintank = load;

	}

}

// public void mow(Grainfield field) {
/*
 * int grainamount = field.getGrainamount(); if (graintank + grainamount <=
 * maxgraintank) { graintank += grainamount; field.mow(); } else {
 * System.out.println("Tank full"); }
 *
 * }
 */

/*
 * public void empty(Silo silo) { int silox = silo.getX(); int siloy =
 * silo.getY(); int silolevel = silo.getLevel();
 * 
 * if (Math.pow((silox - x), 2) + Math.pow((siloy - y), 2) < 1) { try {
 * silo.setLevel(silolevel + graintank); graintank = 0; } catch (TankFull e) {
 * System.out.println("Tank full :("); } }
 */
// }
