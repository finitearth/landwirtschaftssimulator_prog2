package Utils;

import java.util.ArrayList;

/**
 * A class implementing methods to check rather a player/vehicle is colliding
 * with a boundary, such as a river, building, river or window boundaries.
 *
 * @author Tom Zehle
 * @version 1.0
 *
 */
public class CollisionChecker {
	ArrayList<ArrayList<Integer>> boundaries = new ArrayList<>();

	/**
	 * A function to check rather the player/vehicle is hitting a boundary with a
	 * specific move in x-direction. If so, stop the move.
	 *
	 * @param double x - the x-coordinate of player/vehicle
	 *
	 * @param double y - the y-coordinate of player/vehicle
	 *
	 * @param double d_x - the x-direction the player wants to move in.
	 */
	public double collisioncheckX(double x, double y, double d_x) { // check for collision: if it collides, bounce back
		x = x + d_x; // what would the position of the player after the move be?

		for (ArrayList<Integer> boundarie : boundaries) { // iterate through every boundary
			int x1 = boundarie.get(0); // left edge of the boundary
			int y1 = boundarie.get(1); // lower edge
			int x2 = boundarie.get(2); // right edge
			int y2 = boundarie.get(3); // lower edge

			if ((x1 <= x) && (x2 >= x) && (y1 <= y) && (y2 >= y)) { // would the player touch, exceed the boundary?
				return 0; // than return 0: no movement will be made
			}
		}
		return d_x; // else: return the initial movement: the movement is allowed!
	}

	/**
	 * A function to check rather the player/vehicle is hitting a boundary with a
	 * specific move in y-direction. If so, stop the move.
	 *
	 * @param double x - the x-coordinate of player/vehicle
	 *
	 * @param double y - the y-coordinate of player/vehicle
	 *
	 * @param double d_y - the y-direction the player wants to move in.
	 */
	public double collisioncheckY(double x, double y, double d_y) {
		y = y + d_y; // what would the position of the player after the move be?

		for (ArrayList<Integer> boundarie : boundaries) { // iterate through every boundary
			int x1 = boundarie.get(0); // left edge of the boundary
			int y1 = boundarie.get(1); // lower edge
			int x2 = boundarie.get(2); // right edge
			int y2 = boundarie.get(3); // lower edge

			if ((x1 <= x) && (x2 >= x) && (y1 <= y) && (y2 >= y)) { // would the player touch, exceed the boundary?
				return 0; // than return 0: no movement will be made
			}
		}
		return d_y; // else: return the initial movement: the movement is allowed!
	}

	/**
	 * Add boundaries to the instance variable of type arraylist named boundaries.
	 *
	 * @param x1: the left edge of the boundary
	 *
	 * @param y1: the upper edge of the boundary
	 *
	 * @param x2: the right edge of the boundary
	 *
	 * @param y2: the lower edge of the boundary
	 */
	public void addboundary(int x1, int y1, int x2, int y2) {
		ArrayList<Integer> temparraylist = new ArrayList<>();
		temparraylist.add(x1);
		temparraylist.add(y1);
		temparraylist.add(x2);
		temparraylist.add(y2);

		boundaries.add(temparraylist);
	}
}
