package Utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Fields.ArableField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import settings.GameState;

/**
 * This class handles the generation, ownership and growth of the three large
 * wheatfields.
 *
 * @author Leonard Fritz
 * @version 1.0
 *
 */

public class WheatfieldActions {

	/**
	 * Keeps track of all the arable field tiles within wheatfield #1
	 */
	public HashMap<String, ArableField> wheatfieldOneTracker = new HashMap<>();
	/**
	 * Keeps track of all the arable field tiles within wheatfield #2
	 */
	public HashMap<String, ArableField> wheatfieldTwoTracker = new HashMap<>();
	/**
	 * Keeps track of all the arable field tiles within wheatfield #3
	 */
	public HashMap<String, ArableField> wheatfieldThreeTracker = new HashMap<>();

	/**
	 * Keeps track of the player's ownership of the wheatfields
	 */
	public HashMap<String, Boolean> WheatfieldOwnership = new HashMap<>(3);

	public BufferedImage bitmap = null;
	private File file = new File("Images/Bitmap.bmp");

	/**
	 * "amount" refers to the amount of wheat that the player per tile is given when
	 * harvesting. Its value is set in the Constructor according to the difficulty.
	 */
	int amount;

	/**
	 * The Constructor attempts to read the Bitmap.bmp file into a BufferedImage
	 * variable, sets the variable amount and populates the WheatfieldOwnership
	 * HashMap with the appropiate key-value pairs.
	 * 
	 * @param sf sf is of type GameState and corresponds to the current state of the
	 *           game
	 */
	public WheatfieldActions(GameState sf) {
		try {
			this.bitmap = ImageIO.read(this.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		amount = sf.getAmount();
		this.WheatfieldOwnership.put("wheatfieldOne", true); // Field #1 is the only one you own at the start of the game
		this.WheatfieldOwnership.put("wheatfieldTwo", false); // that's why Ownership = false for the other two
		this.WheatfieldOwnership.put("wheatfieldThree", false);
	}

	/**
	 * This function completes to following steps to buy wheatfield #2 1. Checks
	 * whether player owns the wheatfield already and has enough money. 2. Loops
	 * through every tile of the map and checks if it is part of wheatfield #2. 3.
	 * Sets the variable owned to true. 4. After looping through the entire map the
	 * player is charged the price of wheatfield #2. 5. The HashMap
	 * WheatfieldOwnership is updated such that the player cannot buy wheatfield #2
	 * again.
	 * 
	 * @param sf The GameState is needed to charge the player the price of
	 *           wheatfield #2.
	 */
	public void BuyWheatfieldTwo(GameState sf) {
		if ((this.WheatfieldOwnership.get("wheatfieldTwo") == false) &&		// You can only buy Field #2 if you don't own it yet
			(sf.getCash() >= sf.getPriceField2()))							// and you have enough money to buy it
		{
			for (int y = 0; y < bitmap.getHeight(); y++) { 
				for (int x = 0; x < bitmap.getWidth(); x++) {
					String position = "fieldX" + x + "Y" + (y + 1);
					ArableField field = wheatfieldTwoTracker.get(position);
					if (field != null && !(field.isOwned()))
						field.setOwned(true);
				}
			}
			sf.setCash(sf.getCash() - sf.getPriceField2());
			this.WheatfieldOwnership.put("wheatfieldTwo", true); // Ownership is set to true because you now own this field
		}
		else if (this.WheatfieldOwnership.get("wheatfieldTwo"))
			System.out.println("You already own this field!");
		else
			System.out.println("You don't have enough money haha!");
	}

	/**
	 * This function completes to following steps to buy wheatfield #3 1. Checks
	 * whether player owns the wheatfield already and has enough money. 2. Loops
	 * through every tile of the map and checks if it is part of wheatfield #3. 3.
	 * Sets the variable owned to true. 4. After looping through the entire map the
	 * player is charged the price of wheatfield #3. 5. The HashMap
	 * WheatfieldOwnership is updated such that the player cannot buy wheatfield #3
	 * again.
	 * 
	 * @param sf The GameState is needed to charge the player the price of
	 *           wheatfield #3.
	 */
	public void BuyWheatfieldThree(GameState sf) {
		if ((this.WheatfieldOwnership.get("wheatfieldThree") == false) &&	// You can only buy Field #2 if you don't own it yet
			(sf.getCash() >= sf.getPriceField3()))	 						// and you have enough money to buy it
		{
			for (int y = 0; y < bitmap.getHeight(); y++) { 
				for (int x = 0; x < bitmap.getWidth(); x++) {
					String position = "fieldX" + x + "Y" + (y + 1);
					ArableField field = wheatfieldThreeTracker.get(position);
					if (field != null && !(field.isOwned()))
						field.setOwned(true);
				}
			}
			sf.setCash(sf.getCash() - sf.getPriceField3());
			this.WheatfieldOwnership.put("wheatfieldThree", true); // Ownership is set to true because you now own this field
		}
		else if (this.WheatfieldOwnership.get("wheatfieldThree"))
			System.out.println("You already own this field!");
		else
			System.out.println("You don't have enough money haha!");
	}

	/**
	 * This function generates a new arable field tile, sets its coordinates and
	 * amount and puts it into the HashMap wheatfieldOneTracker.
	 * 
	 * @param x x refers to position of the tile within the bitmap. It does not
	 *          refer to the position of the tile on the actual, playable map.
	 * @param y y refers to position of the tile within the bitmap. It does not
	 *          refer to the position of the tile on the actual, playable map.
	 */
	public ArableField GenerateWheatfieldOne(int x, int y) {
		ArableField arableField = new ArableField(x * 50, (y + 1) * 50, amount); // x and y are multiplied by 50 because the Map is 50 times bigger than the Bitmap
		String position = "fieldX" + x + "Y" + (y + 1);
		this.wheatfieldOneTracker.put(position, arableField);
		arableField.setOwned(true);
		return arableField;
	}

	/**
	 * This function generates a new arable field tile, sets its coordinates and
	 * amount and puts it into the HashMap wheatfieldTwoTracker.
	 * 
	 * @param x x refers to position of the tile within the bitmap. It does not
	 *          refer to the position of the tile on the actual, playable map.
	 * @param y y refers to position of the tile within the bitmap. It does not
	 *          refer to the position of the tile on the actual, playable map.
	 */
	public ArableField GenerateWheatfieldTwo(int x, int y) {
		ArableField arableField = new ArableField(x * 50, (y + 1) * 50, amount); // x and y are multiplied by 50 because the Map is 50 times bigger than the Bitmap
		String position = "fieldX" + x + "Y" + (y + 1);
		this.wheatfieldTwoTracker.put(position, arableField);
		arableField.setOwned(false);
		return arableField;
	}

	/**
	 * This function generates a new arable field tile, sets its coordinates and
	 * amount and puts it into the HashMap wheatfieldThreeTracker.
	 * 
	 * @param x x refers to position of the tile within the bitmap. It does not
	 *          refer to the position of the tile on the actual, playable map.
	 * @param y y refers to position of the tile within the bitmap. It does not
	 *          refer to the position of the tile on the actual, playable map.
	 */
	public ArableField GenerateWheatfieldThree(int x, int y) {
		ArableField arableField = new ArableField(x * 50, (y + 1) * 50, amount); // x and y are multiplied by 50 because the Map is 50 times bigger than the Bitmap
		String position = "fieldX" + x + "Y" + (y + 1);
		this.wheatfieldThreeTracker.put(position, arableField);
		arableField.setOwned(false);
		return arableField;
	}

	/**
	 * This function loops through every tile of the map and checks whether it is of
	 * type ArableField in which case it calls the update function. This function is
	 * executed regularily and indefinitely.
	 */
	public void updateWheatfields() {
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), ev -> {
			for (int y = 0; y < bitmap.getHeight(); y++) {
				for (int x = 0; x < bitmap.getWidth(); x++) {
					String position = "fieldX" + x + "Y" + (y + 1);
					if (wheatfieldOneTracker.get(position) != null) {
						ArableField field = wheatfieldOneTracker.get(position);
						field.update();
					} else if (wheatfieldTwoTracker.get(position) != null) {
						ArableField field = wheatfieldTwoTracker.get(position);
						field.update();
					} else if (wheatfieldThreeTracker.get(position) != null) {
						ArableField field = wheatfieldThreeTracker.get(position);
						field.update();
					}
				}
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
		public HashMap<String, ArableField> getarableFields(){
		HashMap<String, ArableField> allfields = new HashMap<>();
		allfields.putAll(wheatfieldOneTracker);
		allfields.putAll(wheatfieldTwoTracker);
		allfields.putAll(wheatfieldThreeTracker);
		return allfields;
		}
		
	}
}
