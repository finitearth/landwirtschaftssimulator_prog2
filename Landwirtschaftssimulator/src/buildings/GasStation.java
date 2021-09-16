package buildings;

import javafx.scene.image.Image;
import machinery.Harvester;
import machinery.Tractor;
import settings.SaveFile;

public class GasStation extends building{
	int costPerLiter = 1;
	Image GasStation  = new Image("File:./Images/Tanke.png", 50, 50, false, false);

	public GasStation(int x, int y) {
		super(x, y);
		this.setImage(GasStation);
	}

	public void refuelTractor(GasStation gasStation, Tractor tractor, SaveFile save) {
		if(gasStation != null) {
			int newFuel = tractor.maxfuel - tractor.fuel;
			if(save.getCash()>=newFuel * costPerLiter) {
				System.out.println(tractor.fuel);
				save.setCash(save.getCash() - (newFuel * costPerLiter));
				System.out.println("You have " + newFuel + " Liter refueled");
				tractor.refuel(newFuel);
				System.out.print(save.getCash());
			}
			else if(save.getCash() == 0){
				System.out.println("You have no money");
			}
			else {
				newFuel = save.getCash() * costPerLiter;
				save.setCash(0);
				System.out.println("You have " + newFuel + " Liter refueled");
				tractor.refuel(newFuel);
				System.out.print(save.getCash());
			}
		}
		else {
			System.out.println("There is no Gasstation nearby");
		}
	}
	public void refuelHarvester(GasStation gasStation, Harvester harvetser, SaveFile save) {
		if(gasStation != null) {
			int newFuel = harvetser.maxfuel - harvetser.fuel;
			if(save.getCash()>=newFuel * costPerLiter) {
				System.out.println(harvetser.fuel);
				save.setCash(save.getCash() - (newFuel * costPerLiter));
				System.out.println("You have " + newFuel + " Liter refueled");
				harvetser.refuel(newFuel);
				System.out.print(save.getCash());
			}
			else if(save.getCash() == 0){
				System.out.println("You have no money");
			}
			else {
				newFuel = save.getCash() * costPerLiter;
				save.setCash(0);
				System.out.println("You have " + newFuel + " Liter refueled");
				harvetser.refuel(newFuel);
				System.out.print(save.getCash());
			}
		}
		else {
			System.out.println("There is no Gasstation nearby");
		}
	}
}
