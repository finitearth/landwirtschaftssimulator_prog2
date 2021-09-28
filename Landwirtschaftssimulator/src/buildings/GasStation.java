package buildings;

import javafx.scene.image.Image;
import machinery.Harvester;
import machinery.Tractor;
import settings.GameState;

/**
 * This class models the Gasstation. Particularly it refuels the Harvester and Tractor an checks if player has enough cash.
 * 
 * @author Julius Großmann
 * @version 1.0
 * 
 */
public class GasStation extends building {
	Image GasStation = new Image("File:./Images/Tanke.png", 50, 50, false, false);
	Harvester harvester;
	Tractor tractor;
	GameState save;
	
	/*
	 * @param costPerLiter the Costs for one Liter fuel.
	 */	
	int costPerLiter = 1;
	
	/*
	 * The constructor for the GasStation class. Calls the super constructor of
	 * building. Sets the image.
	 * 
	 * @param int x the x coordinate of the tractor.
	 * 
	 * @param int y the y coordinate of the tractor.
	 * 
	 * @param GameState save the instance of the game state.
	 */
	public GasStation(int x, int y, GameState save) {
		super(x, y);
		this.setImage(GasStation);
		this.save = save;
	}

	/*
	* Refuels the tractor.
	*
	* @param GasStation gasStation the instance of the Gasstation
	*
	* @param Tractor tractor the instance of the Tractor
	*
	* @param int newFuel the amount of fuel that fits in the tractor
	*/
	public void refuelTractor(GasStation gasStation, Tractor tractor_) {
		tractor = tractor_;
		if (gasStation != null) {
			int newFuel = tractor.maxfuel - tractor.fuel;	//calculate the fuel for refulling
			if (save.getCash() >= newFuel * costPerLiter) {		//is Cash enough to buy the fuel?
				System.out.println(tractor.fuel);
				save.setCash(save.getCash() - (newFuel * costPerLiter));	//setting Cash
				System.out.println("You have " + newFuel + " Liter refueled");
				tractor.setFuel(10000);		//setting fuel
				System.out.print(save.getCash());
			} else if (save.getCash() == 0) {	//is cash zero?
				System.out.println("You have no money");
			} else {	//is cash not enough to buy the whole fuel needed and not zero?
				newFuel = save.getCash() * costPerLiter;	//calculate the fuel for refulling
				save.setCash(0);	//setting cash to zero
				System.out.println("You have " + newFuel + " Liter refueled");
				tractor.setFuel(tractor.getFuel() + newFuel);	//setting fuel
				System.out.print(save.getCash());
			}
		} else {
			System.out.println("There is no Gasstation nearby");
		}
	}

	public void refuelHarvester(GasStation gasStation, Harvester harvester_) { //same as refuelTractor just for the harvester
		harvester = harvester_;
		if (gasStation != null) {
			int newFuel = harvester.maxfuel - harvester.fuel;
			if (save.getCash() >= newFuel * costPerLiter) {
				System.out.println(harvester.fuel);
				save.setCash(save.getCash() - (newFuel * costPerLiter));
				System.out.println("You have " + newFuel + " Liter refueled");
				harvester.setFuel(10000);
				System.out.print(save.getCash());
			} else if (save.getCash() == 0) {
				System.out.println("You have no money");
			} else {
				newFuel = save.getCash() * costPerLiter;
				save.setCash(0);
				System.out.println("You have " + newFuel + " Liter refueled");
				harvester.setFuel(harvester.getFuel() + newFuel);
				System.out.print(save.getCash());
			}
		} else {
			System.out.println("There is no Gasstation nearby");
		}
	}
}
