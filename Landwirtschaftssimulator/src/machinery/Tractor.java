package machinery;

import machinery.Equipment;
import javafx.scene.image.Image;
import Fields.ArableField;
import Utils.AvailableObjectsNearby;
import Utils.CollisionChecker;
import buildings.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tractor extends Vehicle {


	Image TractorViewA = new Image("File:./Images/TractorA.png", 50, 50, false, false);
	Image TractorViewD = new Image("File:./Images/TractorD.png", 50, 50, false, false);
	Image TractorViewW = new Image("File:./Images/TractorW.png", 50, 50, false, false);
	Image TractorViewS = new Image("File:./Images/TractorS.png", 50, 50, false, false);
	AvailableObjectsNearby aonb; 

	public Equipment trailer = null;

	public Tractor(int x, int y, int maxfuel, AvailableObjectsNearby _aonb) {
		super(x, y, maxfuel);
		setImage(TractorViewA);
		aonb = _aonb;
	}

	public void checkmovementactions() {
		if (trailer != null) {
			if (trailer.getType() == "Cultivator") {
				//System.out.println("MOVEMENT DETECTED");
				ArableField field = (ArableField) aonb.search(getX(), getY(), "ArableField");
				//System.out.println(field);
				if (field != null) {
					field.sow();
				}
			}
		}

	}

	@Override
	public void setImageW() {

		this.setImage(TractorViewW);
	}

	@Override
	public void setImageA() {
		this.setImage(TractorViewA);
	}

	@Override
	public void setImageS() {
		this.setImage(TractorViewS);
	}

	@Override
	public void setImageD() {
		this.setImage(TractorViewD);
	}

	@Override
	public void equip(Equipment equipment) {
		if (trailer == null) {
			trailer = equipment;
			/* equipment.setEquipped = true; */
		} else {
			deequip();
		}
	}

	public void deequip() {
		// trailer.setEquipped = false;
		trailer = null;

	}

	public void moveup(CollisionChecker bc, double speed) {
		setImageW();
		updatefuel(speed);
		setY(getY() + bc.collisioncheckY(getX(), getY(), -speed));
		checkmovementactions();
		if (trailer != null) {
			trailer.setX(getX());
			trailer.setY(getY() + 50);
			trailer.setImageW();
		}

	}

	public void moveright(CollisionChecker bc, double speed) {
		setImageD();
		updatefuel(speed);
		setX(getX() + bc.collisioncheckX(getX(), getY(), +speed));
		checkmovementactions();
		if (trailer != null) {
			trailer.setX(getX() - 30);
			trailer.setY(getY());
			trailer.setImageD();
		}
	}

	public void movedown(CollisionChecker bc, double speed) {
		setImageS();
		updatefuel(speed);
		setY(getY() + bc.collisioncheckY(getX(), getY(), +speed));
		checkmovementactions();
		if (trailer != null) {
			trailer.setX(getX());
			trailer.setY(getY() - 30);
			trailer.setImageS();
		}
	}

	public void moveleft(CollisionChecker bc, double speed) {
		setImageA();
		updatefuel(speed);
		setX(getX() + bc.collisioncheckX(getX(), getY(), -speed));
		checkmovementactions();
		if (trailer != null) {
			trailer.setX(getX() + 50);
			trailer.setY(getY());
			trailer.setImageA();
			/* trailer.setImage(cultivatorW) */
		}

	}
}