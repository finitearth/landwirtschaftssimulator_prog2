package machinery;

import machinery.Equipment;
import javafx.scene.image.Image;
import Utils.CollisionChecker;
import buildings.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tractor extends Vehicle {
	Image TractorViewA = new Image("File:./Images/TractorTestL.png", 100, 100, false, false);
	Image TractorViewD = new Image("File:./Images/TractorTestR.png", 100, 100, false, false);
	Image TractorViewW = new Image("File:./Images/TractorTestW.png", 100, 100, false, false);
	Image TractorViewS = new Image("File:./Images/TractorTestS.png", 100, 100, false, false);
	Equipment trailer = null;

	public Tractor(int x, int y, int maxfuel) {
		super(x, y, maxfuel);
		this.setImage(TractorViewA);


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

	public void equip(Equipment equipment) {
		if (trailer == null) {
			trailer = equipment;
			/* equipment.setEquipped = true; */
		}
		else {
			deequip();
		}

	}

	public void deequip() {
		//trailer.setEquipped = false;
		trailer = null;

	}
	public void moveup(CollisionChecker bc, double speed) {
		setImageW();
		if (updatefuel(speed)) {
			setY(getY() + bc.collisioncheckY(getX(), getY(), -speed));
		} else {
			System.out.println("NO FUEL LEFT - REFUEL!!!");
			fuel = 0;
		}
		if (trailer!=null) {
			trailer.setX(getX());
			trailer.setY(getY()+30);
		}
	}

	public void moveright(CollisionChecker bc, double speed) {
		setImageD();
		if (updatefuel(speed)) {
			setX(getX() + bc.collisioncheckX(getX(), getY(), +speed));
		} else {
			System.out.println("NO FUEL LEFT - REFUEL!!!");
			fuel = 0;
		}
		if (trailer!=null) {
			trailer.setX(getX()-30);
			trailer.setY(getY());
		}
	}

	public void movedown(CollisionChecker bc, double speed) {
		setImageS();
		if (updatefuel(speed)) {
			setY(getY() + bc.collisioncheckY(getX(), getY(), +speed));
		} else {
			System.out.println("NO FUEL LEFT - REFUEL!!!");
			fuel = 0;
		}
		if (trailer!=null) {
			trailer.setX(getX());
			trailer.setY(getY()-30);
		}
	}

	public void moveleft(CollisionChecker bc, double speed) {
		setImageA();
		if (updatefuel(speed)) {
			setX(getX() + bc.collisioncheckX(getX(), getY(), -speed));
		} else {
			System.out.println("NO FUEL LEFT - REFUEL!!!");
			fuel = 0;
		}
		if (trailer!=null) {
			trailer.setX(getX()+30);
			trailer.setY(getY());
			/* trailer.setImage(cultivatorW) */
		}

	}
}