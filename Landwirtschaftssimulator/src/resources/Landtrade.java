
public class Landtrade  extends Field{
	int pricePerUnit;
	public int selling(int amountOfUnitsForSale) {
		if(loading >= amountOfUnitsForSale) {    //loading muss noch definiert/angepasst werden
			int payment = amountOfUnitsForSale * pricePerUnit;
		return payment;
		}
		else {
		System.out.println("Not enough loading!"); //nur provisorisch
			return 0;
		}
	}
}
