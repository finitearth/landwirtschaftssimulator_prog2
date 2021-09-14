package machinery;

import javafx.animation.TranslateTransition;

import java.util.ArrayList;

import Utils.CollisionChecker;
import buildings.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import machinery.Equipment;
import Utils.InterActableObjects;
import Utils.NotificationPopUp;

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
	Equipment trailer = null;
	
	
	public Vehicle(int x_, int y_, int maxfuel_) {
		setX(x_);
		setY(y_);
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

	public void updatefuel(double d_s) {
		fuel -= d_s;
		boolean enoughfuel = (fuel > 0);
		if (!enoughfuel) {
			ArrayList<String> actions = new ArrayList<>();
			actions.add("OKAY!");
			NotificationPopUp wind = new NotificationPopUp("Ihr Tank ist leer und wachen ohnmächtig an einer Tanke auf!\nZahle 3.000$ für deine Unachtsamkeit!", actions);
			wind.display();
			fuel = maxfuel;
			setX(250);
			setY(350);

		}
	}

	public void refuel(int d_fuel) {
		fuel = Math.min(d_fuel + fuel, maxfuel);

	}

	public void moveup(CollisionChecker bc, double speed) {
		setImageW();
		updatefuel(speed);
		setY(getY() + bc.collisioncheckY(getX(), getY(), -speed));

		if (trailer != null) {
			trailer.setX(getX());
			trailer.setY(getY() + 30);
		}
	}

	public void moveright(CollisionChecker bc, double speed) {
		setImageD();
		updatefuel(speed);
		setX(getX() + bc.collisioncheckX(getX(), getY(), +speed));

		if (trailer != null) {
			trailer.setX(getX() - 30);
			trailer.setY(getY());
		}
	}

	public void movedown(CollisionChecker bc, double speed) {
		setImageS();
		updatefuel(speed);
		setY(getY() + bc.collisioncheckY(getX(), getY(), +speed));

		if (trailer != null) {
			trailer.setX(getX());
			trailer.setY(getY() - 30);
		}
	}

	public void moveleft(CollisionChecker bc, double speed) {
		setImageA();
		updatefuel(speed);
		setX(getX() + bc.collisioncheckX(getX(), getY(), -speed));

		if (trailer != null) {
			trailer.setX(getX() + 30);
			trailer.setY(getY());
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

	public void equip(ImageView object) {
	}

	public void equip(Equipment equipment) {

	}
}
