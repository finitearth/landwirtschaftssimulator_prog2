package Fields;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import machinery.Harvester;

public class ArableField extends ImageView{
	int growthstate=-2;
	Image sowReady 		= new Image("File:./Images/Wheatfield0.png");
	Image growthStage0	= new Image("File:./Images/Wheatfield1.png");
	Image growthStage1 	= new Image("File:./Images/Wheatfield2.png");
	Image growthStage2 	= new Image("File:./Images/Wheatfield3.png");
	Image harvestReady 	= new Image("File:./Images/Wheatfield4.png");
	Image harvested 	= new Image("File:./Images/Wheatfield5.png");
	Image notOwned 		= new Image("File:./Images/WheatfieldNotOwned.png");

	private boolean owned = false;

	public ArableField(int x, int y) {
		updateFieldImage();
		this.setX(x);
		this.setY(y);
	}


	private void updateFieldImage() {
		switch(growthstate) {
			case -1	:	setImage(sowReady);			break;
			case  0	:	setImage(growthStage0);		break;
			case  1 :	setImage(growthStage1);		break;
			case  2	:	setImage(growthStage2);		break;
			case  3	:	setImage(harvestReady);		break;
			case -2	:	setImage(harvested);		break;
			default	:  System.out.println("ALARM");	break;
		}

	}

	public void update() {
		if (this.getState() >= 0 && this.isOwned()){
			growthstate = Math.min(growthstate+1, 3);
			updateFieldImage();
		}


	}

	public void harvest(Harvester harvester) {
		if(this.isOwned()) {
			harvester.fill(growthstate-1);
			growthstate = -2;
			updateFieldImage();
		}
	}

	public void sow() {
		if (this.getState() == -1 && this.isOwned()) {
			growthstate = 0;
			updateFieldImage();
		}
	}
	public void cultivate() {
		if (this.getState() == -2 && this.isOwned()) {
			growthstate = -1;
			updateFieldImage();
		}
	}

	public int getState() {
		return growthstate;
	}

	public void setState(int i) {
		growthstate = i;
		updateFieldImage();
	}


	public boolean isOwned() {
		return owned;
	}


	public void setOwned(boolean owned) {
		this.owned = owned;
		if(!this.owned)
			this.setImage(notOwned);
		else
			this.setImage(harvested);
	}
}
