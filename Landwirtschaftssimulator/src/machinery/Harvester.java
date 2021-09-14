package machinery;

import Utils.CollisionChecker;
import javafx.scene.image.Image;

public class Harvester extends Vehicle {
	public int graintank;
	public int maxgraintank;
	

	Image ViewA = new Image("File:./Images/HarvesterA.png", 50, 50, false, false);
	Image ViewD = new Image("File:./Images/HarvesterD.png", 50, 50, false, false);
	Image ViewW = new Image("File:./Images/HarvesterW.png", 50, 50, false, false);
	Image ViewS = new Image("File:./Images/HarvesterS.png", 50, 50, false, false);

	public Harvester(int x, int y, int maxfuel, int maxgraintank_) {
		super(x, y, maxfuel);
		graintank = 0;
		maxgraintank = maxgraintank_;
		setImage(ViewA);
	}
	
	public void fill(int grainamount) {
		graintank += grainamount;
	}
	
	@Override
	public void setImageW() {

		this.setImage(ViewW);
	}

	@Override
	public void setImageA() {
		this.setImage(ViewA);
	}

	@Override
	public void setImageS() {
		this.setImage(ViewS);
	}

	@Override
	public void setImageD() {
		this.setImage(ViewD);
	}
	
	public void moveup(CollisionChecker bc, double speed) {
		setImageW();
		updatefuel(speed);
		setY(getY() + bc.collisioncheckY(getX(), getY(), -speed));

	}

	public void moveright(CollisionChecker bc, double speed) {
		setImageD();
		updatefuel(speed);
		setX(getX() + bc.collisioncheckX(getX(), getY(), +speed));

		}
	

	public void movedown(CollisionChecker bc, double speed) {
		setImageS();
		updatefuel(speed);
		setY(getY() + bc.collisioncheckY(getX(), getY(), +speed));

		}
	

	public void moveleft(CollisionChecker bc, double speed) {
		setImageA();
		updatefuel(speed);
		setX(getX() + bc.collisioncheckX(getX(), getY(), -speed));

		}
	
	}

	

	//public void mow(Grainfield field) {
	/*
	 * int grainamount = field.getGrainamount(); if (graintank + grainamount <=
	 * maxgraintank) { graintank += grainamount; field.mow(); } else {
	 * System.out.println("Tank full"); }
	 * 
	 * }
	 */

	/*public void empty(Silo silo) {
		int silox = silo.getX();
		int siloy = silo.getY();
		int silolevel = silo.getLevel();

		if (Math.pow((silox - x), 2) + Math.pow((siloy - y), 2) < 1) {
			try {
				silo.setLevel(silolevel + graintank);
				graintank = 0;
			} catch (TankFull e) {
				System.out.println("Tank full :(");
			}
		}*/
	//}


