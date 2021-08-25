package buildings;

import Fields.Field;

public class GasStation extends Field{
	int costPerLiter;
	public int fuel(int amountOfFuel) {
		if(cash >= (costPerLiter * amountOfFuel)) {
			cash = cash - (costPerLiter * amountOfFuel);
			return amountOfFuel;
		}
		else {
			System.out.println("Not enough cash!"); //nur provisorisch
			return 0;
		}
	}
}
