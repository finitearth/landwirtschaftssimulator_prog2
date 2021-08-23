package resources;


public class Farmyard  extends Field{
	private int siloLevel;
	private int maxSiloLevel;
	String[] machineParking = {};
	
	public int fillSilo(int amountOfFilling) {
		siloLevel = siloLevel + amountOfFilling;
		if(siloLevel > maxSiloLevel) {
			siloLevel = maxSiloLevel;
			return siloLevel - maxSiloLevel;
		}
		else return 0;
	}
	public int clearSilo(int amountOfClear) {
		if(siloLevel >= amountOfClear) {
			siloLevel = siloLevel - amountOfClear;
			return amountOfClear;
		}
		else {
			int filling = siloLevel;
			siloLevel = 0;
			System.out.println("You just get " + filling); //nur provisorisch
			return filling;
		}
	}
	public void machinePickUp() {
		
	}
	public void machineStore() {
		
	}
}
