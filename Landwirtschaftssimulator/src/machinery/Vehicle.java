package machinery;
import javafx.animation.TranslateTransition;
import Utils.CollisionChecker;
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
		this.setX(x_);
		this.setY(y_);
		maxfuel = maxfuel_;
		fuel = maxfuel;
	}

	/**
	 * Enters the vehicle
	 */
	public boolean enter(Player player) {
		double player_x = player.getX();
		double player_y = player.getY();
		double vehicle_x = getX();
		double vehicle_y = getY();
		if (((Math.abs(player_x - vehicle_x) + Math.abs(player_y - vehicle_y)) < 150)) {
			entered = true;
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

	public boolean updatefuel(double d_s) {
		fuel -= d_s;
		boolean enoughfuel = (fuel > 0);

		return enoughfuel;
	}

	public void refuel(int d_fuel) {
		fuel = Math.min(d_fuel + fuel, maxfuel);

	}

	public void moveup(CollisionChecker bc, double speed) {
		setImageW();
		if (updatefuel(speed)) {
			setY(getY() + bc.collisioncheckY(getX(), getY(), -speed));
		} else {
			System.out.println("NO FUEL LEFT - REFUEL!!!");
			fuel = 0;
		}
	}

	public void moveright(CollisionChecker bc, double speed) {
		setImageD();
		if (updatefuel(speed)) {
			setX(getX() + bc.collisioncheckX(getX(), getY(), +speed));
		} else {
			System.out.println("NO FUEL LEFT - REFUEL!!!");
			fuel = 0;
		}
	}

	public void movedown(CollisionChecker bc, double speed) {
		setImageS();
		if (updatefuel(speed)) {
			setY(getY() + bc.collisioncheckY(getX(), getY(), +speed));
		} else {
			System.out.println("NO FUEL LEFT - REFUEL!!!");
			fuel = 0;
		}
	}

	public void moveleft(CollisionChecker bc, double speed) {
		setImageA();
		if (updatefuel(speed)) {
			setX(getX() + bc.collisioncheckX(getX(), getY(), -speed));
		} else {
			System.out.println("NO FUEL LEFT - REFUEL!!!");
			fuel = 0;
		}

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
