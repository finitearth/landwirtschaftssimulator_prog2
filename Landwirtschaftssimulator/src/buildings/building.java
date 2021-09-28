package buildings;

import javafx.scene.image.ImageView;

/**
 ** This class models a building. It acts as a parent class for the buildings
 * Farmyard, Gasstation and Landtrade.
 * 
 * @author Julius Großmann
 * @version 1.0
 *
 *
 *
 * 
 */
public class building extends ImageView {

	public int x;
	public int y;

	/**
	 * Constructor of the building class. Sets the x and y
	 * coordinates.
	 * 
	 * @param x The x-Coordinate of the vehicle.
	 * 
	 * @param y The y-Coordinate of the vehicle.
	 */
	public building(int x_, int y_) {
		setX(x_);
		setY(y_);
	}
}
