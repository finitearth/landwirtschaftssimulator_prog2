package machinery;

import javafx.scene.image.ImageView;

/**
 * This function acts as a super class for the equipment-subclasses. It's
 * methods and variables are overwritten, however it serves the purpose of being
 * able to have unified calls.
 * 
 * @author Tom Zehle
 *
 */
public class Equipment extends ImageView {
	public String type = "Equipment";

	public void setImageW() {

	}

	public void setImageS() {

	}

	public void setImageA() {

	}

	public void setImageD() {

	}

	public String getType() {
		return type;
	}

}
