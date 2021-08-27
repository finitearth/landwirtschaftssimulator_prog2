package machinery;
import buildings.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * 
 * @author Tom Zehle
 * @version 0.1
 *
 * 
 * @param entered Rather the vehicle has been entered or not.
 * @param x       The x-Coordinate of the vehicle.
 * @param y       The y-Coordinate of the vehicle.
 * @param maxfuel The maximum fuel capacity of the vehicle.
 * @param fuel    The current fuel level of the vehicle.
 * 
 */

public class Vehicle extends ImageView {
	
	public boolean entered = false;
	public int x;
	public int y;
	public int maxfuel;
	public int fuel;

	public Vehicle(int x_, int y_, int maxfuel_) {
		x = x_;
		y = y_;
		maxfuel = maxfuel_;
		fuel = maxfuel;
	}

	/**
	 * Enters the vehicle
	 */
	public boolean enter(Player player) {
		int player_x = (int) player.getX();
		int player_y = (int) player.getY();
		Vehicle alreadyinvehicle = player.getEnteredVehicle();
		if (((player_x - x + player_y - y) < 10000.0)) {
			entered = true;
			System.out.println(entered);
		} else {
			entered = false;
		}
		return entered;
	}

	public void exit() {
		if (entered) {
			entered = false;
		}
	}

	public void move(int d_x, int d_y) {
		x += d_x;
		y += d_y;
		fuel -= d_x - d_y;
	}

	public void refuel(int d_fuel) {
		fuel = Math.min(d_fuel + fuel, maxfuel);

	}
	
	public void setImageW() {
	}
	public void setImageA() {
	}
	public void setImageS() {
	}
	public void setImageD() {
	}
}



class Harvester extends Vehicle {
	public int graintank;
	public int maxgraintank;

	public Harvester(int x, int y, int maxfuel, int maxgraintank_) {
		super(x, y, maxfuel);
		graintank = 0;
		maxgraintank = maxgraintank_;

	}

	//public void mow(Grainfield field) {
	/*
	 * int grainamount = field.getGrainamount(); if (graintank + grainamount <=
	 * maxgraintank) { graintank += grainamount; field.mow(); } else {
	 * System.out.println("Tank full"); }
	 * 
	 * }
	 */

	/*public void empty(Silo silo) {
		int silox = silo.getX();
		int siloy = silo.getY();
		int silolevel = silo.getLevel();

		if (Math.pow((silox - x), 2) + Math.pow((siloy - y), 2) < 1) {
			try {
				silo.setLevel(silolevel + graintank);
				graintank = 0;
			} catch (TankFull e) {
				System.out.println("Tank full :(");
			}
		}*/
	//}

}

