package buildings;

import Utils.CollisionChecker;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import machinery.Vehicle;

public class Player extends ImageView {

	Image playerImageW = new Image("File:./Images/PlayerTest.png"); // TODO Bilder ersetzen
	Image playerImageA = new Image("File:./Images/PlayerTest.png");
	Image playerImageS = new Image("File:./Images/PlayerS.png");
	Image playerImageD = new Image("File:./Images/PlayerTest.png");
	Image playerImageCollided = new Image("File:./Images/PlayerTest.png");

	private boolean mounted = false;

	private Vehicle enteredvehicle = null;

	public Player(int posX, int posY) {
		this.setX(posX);
		this.setY(posY);
		this.setImage(playerImageS);
	}

	public void setImageW() {
		this.setImage(playerImageW);
	}

	public void setImageA() {
		this.setImage(playerImageA);
	}

	public void setImageS() {
		this.setImage(playerImageS);
	}

	public void setImageD() {
		this.setImage(playerImageD);
	}

	public void setImageCollided() {
		this.setImage(playerImageCollided);
	}

	public void setNoImage() {
		this.setImage(null);
	}

	public Vehicle getEnteredVehicle() {
		return enteredvehicle;
	}

	public void setEnteredVehicle(Vehicle vehicle) {
		// Boolean enterable = vehicle.enter(this);
		if (vehicle == null) {
			enteredvehicle = null;
			return;
		}
		if ((enteredvehicle == null) && (vehicle.enter(this))) {
			setNoImage();
			enteredvehicle = vehicle;
		}

	}

	public void moveup(CollisionChecker bc, double walkingspeed) {
		setImageW();
		setY(getY() + bc.collisioncheckY(getX(), getY(), -walkingspeed));
	}
	public void moveright(CollisionChecker bc, double walkingspeed) {
		setImageD(); setX(getX() + bc.collisioncheckX(getX(), getY(), + walkingspeed));
	}
	
	public void movedown(CollisionChecker bc, double walkingspeed) {
		setImageS(); setY(getY() + bc.collisioncheckY(getX(), getY(), + walkingspeed));
	}
	public void moveleft(CollisionChecker bc, double walkingspeed) {
		setImageA(); setX(getX() + bc.collisioncheckX(getX(), getY(), - walkingspeed));
	}

}
