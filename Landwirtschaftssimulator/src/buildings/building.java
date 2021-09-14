package buildings;

import javafx.animation.TranslateTransition;

import java.util.ArrayList;

import Utils.CollisionChecker;
import buildings.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import machinery.Equipment;
import Utils.NotificationPopUp;

public class building extends ImageView {

	public int x;
	public int y;
	

	public building(int x_, int y_) {
		setX(x_);
		setY(y_);
	}
}