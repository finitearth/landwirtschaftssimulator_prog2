package buildings;

import javafx.scene.image.Image;
import machinery.Harvester;
import machinery.Tractor;
import settings.GameState;

public class GasStation extends building{
	int costPerLiter = 1;
	Image GasStation  = new Image("File:./Images/Tanke.png", 50, 50, false, false);

	public GasStation(int x, int y) {
		super(x, y);
		this.setImage(GasStation);
	}

	public void refuelTractor(GasStation gasStation, Tractor tractor, GameState save) {
		if(gasStation != null) {
			int newFuel = tractor.maxfuel - tractor.fuel;
			if(save.getCash()>=newFuel * costPerLiter) {
				System.out.println(tractor.fuel);
				save.setCash(save.getCash() - (newFuel * costPerLiter));
				System.out.println("You have " + newFuel + " Liter refueled");
				tractor.setFuel(10000);
				System.out.print(save.getCash());
			}
			else if(save.getCash() == 0){
				System.out.println("You have no money");
			}
			else {
				newFuel = save.getCash() * costPerLiter;
				save.setCash(0);
				System.out.println("You have " + newFuel + " Liter refueled");
				tractor.setFuel(tractor.getFuel()+newFuel);
				System.out.print(save.getCash());
			}
		}
		else {
			System.out.println("There is no Gasstation nearby");
		}
	}
	public void refuelHarvester(GasStation gasStation, Harvester harvester, GameState save) {
		if(gasStation != null) {
			int newFuel = harvester.maxfuel - harvester.fuel;
			if(save.getCash()>=newFuel * costPerLiter) {
				System.out.println(harvester.fuel);
				save.setCash(save.getCash() - (newFuel * costPerLiter));
				System.out.println("You have " + newFuel + " Liter refueled");
				harvester.setFuel(10000);
				System.out.print(save.getCash());
			}
			else if(save.getCash() == 0){
				System.out.println("You have no money");
			}
			else {
				newFuel = save.getCash() * costPerLiter;
				save.setCash(0);
				System.out.println("You have " + newFuel + " Liter refueled");
				harvester.setFuel(harvester.getFuel()+newFuel);
				System.out.print(save.getCash());
			}
		}
		else {
			System.out.println("There is no Gasstation nearby");
		}
	}
}
