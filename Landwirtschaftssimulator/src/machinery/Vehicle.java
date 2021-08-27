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
		double vehicle_x = this.getX();
		double vehicle_y = this.getY();
		if (((player_x - vehicle_x + player_y - vehicle_y) < 150)) {
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


