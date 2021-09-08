package buildings;

import buildings.building;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import machinery.Tractor;

public class GasStation extends building{
	int costPerLiter = 1;
	int cash = 5;
	Image GasStation  = new Image("File:./Images/Tanke.png", 50, 50, false, false);
	
	public GasStation(int x, int y) {
		super(x, y);
		this.setImage(GasStation);
	}

	public void refuel(GasStation gasStation, Tractor tractor) {
		if(gasStation != null) {
			int newFuel = tractor.maxfuel - tractor.fuel;
			if(cash>=newFuel * costPerLiter) {
				System.out.println(tractor.fuel);
				cash = cash - (newFuel * costPerLiter);
				System.out.println("You have " + newFuel + " Liter refueled");
				tractor.refuel(newFuel);
			}
			else if(cash == 0){
				System.out.println("You have no money");
			}	
			else {
				newFuel = cash * costPerLiter;
				cash = 0;
				System.out.println("You have " + newFuel + " Liter refueled");
				tractor.refuel(newFuel);
			}
		}
		else {
			System.out.println("There is no Gasstation nearby");
		}
	}
}
