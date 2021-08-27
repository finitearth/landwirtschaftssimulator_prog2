package buildings;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import machinery.Vehicle;

public class Player extends ImageView {				
													
	Image playerImageW = new Image("File:./Images/PlayerTest.png"); // TODO Bilder ersetzen
	Image playerImageA = new Image("File:./Images/PlayerTest.png");
	Image playerImageS = new Image("File:./Images/PlayerTest.png");
	Image playerImageD = new Image("File:./Images/PlayerTest.png");
	Image playerImageCollided = new Image("File:./Images/PlayerTest.png");
	
	private boolean mounted = false;
	
	private Vehicle enteredvehicle = null;
	
	public Player(int posX, int posY){
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
	public boolean isMounted() {
		return mounted;
	}
	public void setMounted(boolean mounted) {
		this.mounted = mounted;
	}
	
	public Vehicle getEnteredVehicle() {
		return enteredvehicle;
	}
	
	public void setEnteredVehicle(Vehicle vehicle) {
		enteredvehicle = vehicle;
		if (vehicle==null) {
			setNoImage();
			return;
		}
		if (vehicle.enter(this)) {
			setNoImage();
		}
	}
	
}
