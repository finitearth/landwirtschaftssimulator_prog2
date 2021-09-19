package Utils;

import java.util.ArrayList;

import javafx.scene.image.ImageView;

/**
 * A class which implements methods for searching objects that are nearby.
 *
 * @author Tom Zehle
 * @version 1.0
 *
 */
public class AvailableObjectsNearby {
	ArrayList<ImageView> objects = new ArrayList<>();
	ArrayList<String> types = new ArrayList<>();

	/*
	 * add an object that you are supposed to be able to search for
	 * 
	 * @param ImageView object the object you want to be able to search for
	 * 
	 * @param String type the type of the object
	 */
	public void add(ImageView object, String type) {
		objects.add(object);
		types.add(type);
	}

	/*
	 * Search for objects nearby of a specific type. return null if there are no
	 * objects of that type nearby.
	 * 
	 * @param double x_player the x-coordinate of the player
	 * 
	 * @param double y_player the y-coordinate of the player
	 * 
	 * @param String type the object type you are searching for
	 */
	public ImageView search(double x_player, double y_player, String type) {
		double x;
		double y;
		double dist;
		double min_d = 100.0; // only return objects if they are closer than 100 units
		ImageView nearest = null; // if there is no object of that type nearer than min_d you are going to return
									// null.
		for (int i = 0; i < objects.size(); i++) { // iterate through every object

			ImageView object = objects.get(i);
			String type_object = types.get(i);
			x = object.getX();
			y = object.getY();
			dist = Math.abs(x - x_player) + Math.abs(y - y_player); // calculate the mahattan distance from player to
																	// the object

			if ((type_object == type) && ((dist < min_d))) { // if the object has the type you are searching for and is
																// nearer than min_d
				min_d = dist; // set min_d to that distance; this variable is only going to be overwritten if
								// there is a object which is nearer later on
				nearest = object; // overwrite nearest to that object
			}
		}

		return nearest;
	}
}
