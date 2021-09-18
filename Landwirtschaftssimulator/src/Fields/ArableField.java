package Fields;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import machinery.Harvester;
import settings.GameState;

/**
 * This class models arable fields. Particularly it defines rather the field is
 * owned and if it has been sown yet, determines its growth state and updates
 * its images accordingly. Arable fields is extending the Imageview class.
 *
 * @author Leonard Fritz and Tom Zehle
 * @version 1.0
 *
 */
public class ArableField extends ImageView {
	/**
	 * determines the growthstate of the particular field. Intialiezed to -2:
	 * harvested / not sown yet. -1: Sown 0: Growthstate lvl. 1 1: Growthstate lvl.
	 * 2 2: Growhstate lvl. 3 3: Growhstate lvl. 4 - harvastable
	 */
	int growthstate = -2;

	Image sowReady = new Image("File:./Images/Wheatfield0.png");
	Image growthStage0 = new Image("File:./Images/Wheatfield1.png");
	Image growthStage1 = new Image("File:./Images/Wheatfield2.png");
	Image growthStage2 = new Image("File:./Images/Wheatfield3.png");
	Image harvestReady = new Image("File:./Images/Wheatfield4.png");
	Image harvested = new Image("File:./Images/Wheatfield5.png");
	Image notOwned = new Image("File:./Images/WheatfieldNotOwned.png");

	/**
	 * determines if the field is owned yet.
	 */
	private boolean owned = false;

	/**
	 * determines the amount of grain harvastable from the field if at growthstate
	 * lvl. 4
	 */
	int amount;

	/**
	 * Constructor for the ArableField class. Intializes the image via
	 * updateFieldImage, sets the x/y coordinates of the field as well as the amount
	 * obtained from harvesting this field
	 * 
	 * @param x       the x coordinate of the field
	 * @param y       the y coordinate of the field
	 * @param amount_ the amount obtained from harvesting the field.
	 */
	public ArableField(int x, int y, int amount_) {
		updateFieldImage();
		this.setX(x);
		this.setY(y);
		amount = amount_;
		;
	}

	/**
	 * updates the image of the particular field to its corresponding growth state.
	 */
	private void updateFieldImage() {
		switch (growthstate) {
		case -2:
			setImage(harvested);
			break;
		case -1:
			setImage(sowReady);
			break;
		case 0:
			setImage(growthStage0);
			break;
		case 1:
			setImage(growthStage1);
			break;
		case 2:
			setImage(growthStage2);
			break;
		case 3:
			setImage(harvestReady);
			break;

		default:
			break;
		}

	}

	/**
	 * updates growth state of the field, if the field has been sown (equivalent to
	 * lvl. 0)
	 */
	public void update() {
		if (this.getState() >= 0 && this.isOwned()) {
			growthstate = Math.min(growthstate + 1, 3); // min is used, so that it will never be than 3 (the maximum
														// growth state)
			updateFieldImage();
		}

	}

	/**
	 * harvests the field if field is harvastable (growth state of 3) and owned.
	 * fills the harvester up with the grain amount obtained. updates the growth
	 * state to -2 (harvested). Calls updateFieldImage to update the Image.
	 * 
	 * @param harvester - The harvester that is harvesting the field.
	 */
	public void harvest(Harvester harvester) {
		if (this.getState() == 3 && this.isOwned()) {
			harvester.fill(amount);
			growthstate = -2;
			updateFieldImage();
		}
	}

	/**
	 * Sows the field if the growth state is at -1 (cultivated) and is owned. Sets
	 * the growth state to -1 (sown) and calls updateFieldImage to update the Image.
	 */
	public void sow() {
		if (this.getState() == -1 && this.isOwned()) {
			growthstate = 0;
			updateFieldImage();
		}
	}

	/**
	 * Cultivates the field if the growth state is at -2 (harvested) and is owned.
	 * Sets the growth state to -1 (cultivated) and calls updateFieldImage to update
	 * the Image.
	 */
	public void cultivate() {
		if (this.getState() == -2 && this.isOwned()) {
			growthstate = -1;
			updateFieldImage();
		}
	}

	/**
	 * Getter for the growthstate
	 * 
	 * @return the growthstate of the field
	 */
	public int getState() {
		return growthstate;
	}

	/**
	 * sets the growthstate of the field and updates the image accordingly.
	 * 
	 * @param int growthstate_ the growthstate the field is supposed to be set to.
	 * 
	 */
	public void setState(int growthstate_) {
		growthstate = growthstate_;
		updateFieldImage();
	}

	/**
	 * Getter for the owned-status
	 * 
	 * @return boolean: rather the field is being owned or not
	 */
	public boolean isOwned() {
		return owned;
	}

	/**
	 * sets the owned variable of the field and updates the image accordingly.
	 * 
	 * @param boolean owned: if the field is supposed to be owned or not.
	 * 
	 */
	public void setOwned(boolean owned) {
		this.owned = owned;
		if (!this.owned)
			this.setImage(notOwned);
		else
			this.setImage(harvested);
	}
}
