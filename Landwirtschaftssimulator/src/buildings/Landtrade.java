package buildings;

import Fields.Field;
import javafx.scene.image.Image;

public class Landtrade  extends Field{
	int pricePerUnit;
	int loading; //provisorisch
	Image Landtrade  = new Image("File:./Images/Silo.png", 50, 50, false, false);
	
	public Landtrade(int posX, int posY) {
		this.setX(posX);
		this.setY(posY);
		this.setImage(Landtrade);
	}
	
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
