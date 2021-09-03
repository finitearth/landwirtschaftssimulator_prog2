package Utils;

import java.util.ArrayList;
import javafx.scene.image.ImageView;

public class AvailableObjectsNearby {
	ArrayList<ImageView> objects = new ArrayList<>();

	public void add(ImageView object) {
		objects.add(object);
	}

	public ImageView search(double x_player, double y_player) {
		double x;
		double y;
		double dist;
		double min_d = 100.0;
		ImageView nearest = null;
		for (ImageView object : objects) {
			x = object.getX();
			y = object.getY();
			dist = Math.abs(x - x_player) + Math.abs(y - y_player);
			if ((dist < min_d)) {
				min_d = dist;
				nearest = object;
			}
		}

		return nearest;
	}

	public ImageView search(double x_player, double y_player, String type) {
		double x;
		double y;
		double dist;
		double min_d = 100.0;
		ImageView nearest = null;
		for (ImageView object : objects) {
			x = object.getX();
			y = object.getY();
			dist = Math.abs(x - x_player) + Math.abs(y - y_player);
			try {
				System.out.println(x);
				System.out.println(y);
				System.out.println(object);
				if ((dist < min_d) && (object.getClass().getSuperclass() == Class.forName(type))) {
					min_d = dist;
					nearest = object;
				}
			} catch (ClassNotFoundException e) {
			}	
		}
		
		return nearest;
	}
}
