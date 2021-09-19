package buildings;

import Utils.CollisionChecker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import machinery.Vehicle;

public class Player extends ImageView {

	Image playerImageW = new Image("File:./Images/PlayerW.png");
	Image playerImageA = new Image("File:./Images/PlayerA.png");
	Image playerImageS = new Image("File:./Images/PlayerS.png");
	Image playerImageD = new Image("File:./Images/PlayerD.png");
	Image playerImageCollided = new Image("File:./Images/PlayerTest.png");


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

	public void setEnteredVehicle(Vehicle vehicle, Vehicle vehicleTest) {
		// Boolean enterable = vehicle.enter(this);
		if (vehicle == null && enteredvehicle == vehicleTest) {
			enteredvehicle = null;
			return;
		}
		
		else if (enteredvehicle == null) {
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
