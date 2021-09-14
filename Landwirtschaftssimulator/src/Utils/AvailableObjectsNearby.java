package Utils;

import java.util.ArrayList;
import javafx.scene.image.ImageView;

import Fields.ArableField;

public class AvailableObjectsNearby {
	ArrayList<ImageView> objects = new ArrayList<>();
	ArrayList<String> types = new ArrayList<>();
	public void add(ImageView object, String type) {
		objects.add(object);
		types.add(type);
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
		for (int i = 0; i < objects.size(); i++) {
			
			ImageView object = objects.get(i);
			String type_object = types.get(i);
			x = object.getX();
			y = object.getY();
			dist = Math.abs(x - x_player) + Math.abs(y - y_player);
			if (type_object == "ArableField") {
				System.out.println(dist);
			}
			if ((type_object == type)&&((dist < min_d))) {
				min_d = dist;
				nearest = object;
			}
		}
		
		return nearest;
	}
}
