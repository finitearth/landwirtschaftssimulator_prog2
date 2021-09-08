package buildings;

import buildings.building;
import javafx.scene.image.Image;

public class Landtrade  extends building{
	int pricePerUnit;
	int loading; //provisorisch
	int cash; //provisorisch
	Image Landtrade  = new Image("File:./Images/Silo.png", 50, 50, false, false);
	
	public Landtrade(int x, int y) {
		super(x, y);
		this.setImage(Landtrade);
	}
	
	public void selling(Landtrade landtrade) {
		int amountOfUnitsForSale = 5; //provisorisch
		if(landtrade !=null) {
			if(loading >= amountOfUnitsForSale) {    //loading muss noch definiert/angepasst werden
				int payment = amountOfUnitsForSale * pricePerUnit;
				cash = cash + payment;
			}
			else {
				System.out.println("Not enough loading!"); //nur provisorisch
			}
		}
		else {
			System.out.println("You are not near the Landtrade!");
		}
	}
}
