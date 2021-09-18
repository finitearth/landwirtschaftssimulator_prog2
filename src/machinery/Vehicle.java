package machinery;

import java.util.ArrayList;

import Utils.NotificationPopUp;
import javafx.scene.image.ImageView;
import settings.GameState;

/**
 ** This class models a vehicle. It acts as a parent class for the vehicles
 * tractor and harvester.
 * 
 * @author Tom Zehle
 * @version 1.0
 *
 *
 *
 * 
 */

public class Vehicle extends ImageView {
	public boolean entered = false;
	public int maxfuel;
	public int fuel;
	Equipment trailer = null;
	GameState gs;

	/*
	 * Constructor of the vehicle class. Sets the maxfuel capacity, the x and y
	 * coordinates as well as the game state. The fuel is intialized at the maximum
	 * fuel capacity.
	 * 
	 * @param x The x-Coordinate of the vehicle.
	 * 
	 * @param y The y-Coordinate of the vehicle.
	 * 
	 * @param maxfuel The maximum fuel capacity of the vehicle.
	 * 
	 * @param gs the gamestate instance.
	 */
	public Vehicle(int x, int y, int maxfuel, GameState gs) {
		setX(x);
		setY(y);
		this.maxfuel = maxfuel;
		fuel = maxfuel;
		this.gs = gs;
	}

	/*
	 * Reduces the amount of fuel left in the tank by the distance traveled. if
	 * there's no fuel left an event is triggered, spawning the player near together
	 * with the vehicle near the gasstation and forcing him to pay a fee.
	 * 
	 * @param double d_s - the distance travelled.
	 */
	public void updatefuel(double d_s) {
		fuel -= d_s;
		boolean enoughfuel = fuel > 0; // if there's enough fuel (more than 0) left in the tank after traveling
										// distance d_s.
		if (!enoughfuel) { // if theres not enough fuel, spawn the player near the gas station and open a
							// pop up telling him he is supposed to pay a fee.
			ArrayList<String> actions = new ArrayList<>();
			actions.add("OKAY!");
			NotificationPopUp wind = new NotificationPopUp(
					"Ihr Tank ist leer und wachen ohnm�chtig an einer Tanke auf!\nZahle 3.000$ f�r deine Unachtsamkeit!",
					actions);
			wind.display();
			gs.setCash(gs.getCash() - 3000);
			fuel = maxfuel;
			setX(250);
			setY(350);

		}
	}
	public void refuel(int d_fuel) {
		fuel = Math.min(d_fuel + fuel, maxfuel);

	}

}