package resources;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Field extends ImageView {
	Image fieldImage = new Image("File:./Images/FieldTest.png");
	
	public Field() {
		this.setImage(fieldImage);
	}
	
	boolean passable;
	int CordX;
	int CordY;
	boolean bought;
}

