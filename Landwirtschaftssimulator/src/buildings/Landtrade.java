package buildings;

import javafx.scene.image.Image;
import machinery.DumpTruck;
import machinery.Equipment;
import machinery.Tractor;
import settings.GameState;

/**
 * This class models the Landtrade. It allows the Player to sell his load.
 * 
 * @author Julius Großmann
 * @version 1.0
 * 
 */
public class Landtrade extends building {
	Image Landtrade = new Image("File:./Images/Silo.png", 50, 50, false, false);
	DumpTruck dumpTruck;
	Tractor tractor;
	GameState save;

	/*
	* The Construction for the Landtrade class. Calls the super constructor of bulding. Set the image.
	*
	* @param int x the x coordinate of the Landtrade
	*
	* @param int y the y coordinate of the Landtrade
	*
	* @param GameState save the instance of the game state.
	*/
	public Landtrade(int x, int y, GameState save) {
		super(x, y);
		this.setImage(Landtrade);
		this.save = save;
	}

	/*
	* Sells dumptruck load to the landtrade
	*
	* @param int payment the amount of cash the player get from the landtrade
	*/
	public void selling(Landtrade landtrade, DumpTruck dumpTruck_, Tractor tractor_) {
		dumpTruck = dumpTruck_;
		tractor = tractor_;
		Equipment activeEquipment = tractor.getTrailer();
		if (landtrade != null && activeEquipment == dumpTruck) { //is the Tractor is near the landtrade and the dumptruck the trailer of the tractor?
			int payment = dumpTruck.getLoad() * save.getUnitPrice(); //calculate the cash the player earned
			save.setCash(save.getCash() + payment);		//saves the cash
			dumpTruck.setLoad(0);		//setting dumptruck load to zero
		} else {
			System.out.println("You are not near the Landtrade or your Dumptruck is missing!"); //error-message 
		}
	}
}
