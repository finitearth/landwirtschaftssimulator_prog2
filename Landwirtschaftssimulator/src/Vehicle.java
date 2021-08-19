
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

public class Vehicle {
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
	public void enter(Player player) {
		int player_x = player.getX();
		int player_y = player.getY();
		boolean alreadyinvehicle = player.getEnteredVehicle();
		if (Math.pow((player_x - x), 2) + Math.pow((player_y - y), 2) < 1 && !alreadyinvehicle) {
			entered = true;
			System.out.println(entered);
		} else {
			System.out.println("FAIL");
		}
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
}

class Traktor extends Vehicle {
	Equiptment trailer;

	public Traktor(int x, int y, int maxfuel) {
		super(x, y, maxfuel);
		trailer = null;

	}

	public void equip(Equipment equipment) {
		if (trailer == null) {
			trailer = equipment;
			equipment.setEquipped = true;
		}

	}

	public void deequip() {
		trailer.setEquipped = false;
		trailer = null;

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

	public void mow(Grainfield field) {
		int grainamount = field.getGrainamount();
		if (graintank + grainamount <= maxgraintank) {
			graintank += grainamount;
			field.mow();
		} else {
			System.out.println("Tank full");
		}

	}

	public void empty(Silo silo) {
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
		}
	}
}

