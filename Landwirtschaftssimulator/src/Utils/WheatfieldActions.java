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
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import settings.GameState;



public class WheatfieldActions {

	public HashMap<String, ArableField> wheatfieldOneTracker   = new HashMap<>();
	public HashMap<String, ArableField> wheatfieldTwoTracker   = new HashMap<>();
	public HashMap<String, ArableField> wheatfieldThreeTracker = new HashMap<>();

	public BufferedImage bitmap = null;
	private File file = new File("Images/Bitmap.bmp"); // Weizenfelder BufferedImage
	int amount;
	
	public WheatfieldActions(GameState sf) {
		try {
			this.bitmap = ImageIO.read(this.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		amount = sf.getAmount();
	}

	public void BuyWheatfieldTwo() {
		for (int y = 0; y < bitmap.getHeight(); y++) {
			for (int x = 0; x < bitmap.getWidth(); x++) {
				String position = "fieldX" + x + "Y" + (y + 1);
				ArableField field = wheatfieldTwoTracker.get(position);
				if(field != null)
					field.setOwned(true);
			}
		}
	}
	public void BuyWheatfieldThree() {
		for (int y = 0; y < bitmap.getHeight(); y++) {
			for (int x = 0; x < bitmap.getWidth(); x++) {
				String position = "fieldX" + x + "Y" + (y + 1);
				ArableField field = wheatfieldThreeTracker.get(position);
				if(field != null)
					field.setOwned(true);
			}
		}
	}
	public ArableField GenerateWheatfieldOne(int x, int y) {
		ArableField arableField = new ArableField(x * 50, (y + 1) * 50, amount);
		String position = "fieldX" + x + "Y" + (y + 1);
		this.wheatfieldOneTracker.put(position, arableField);
		arableField.setOwned(true);
		return arableField;
	}
	public ArableField GenerateWheatfieldTwo(int x, int y) {
		ArableField arableField = new ArableField(x * 50, (y + 1) * 50, amount);
		String position = "fieldX" + x + "Y" + (y + 1);
		this.wheatfieldTwoTracker.put(position, arableField);
		arableField.setOwned(false);
		return arableField;
	}
	public ArableField GenerateWheatfieldThree(int x, int y) {
		ArableField arableField = new ArableField(x * 50, (y + 1) * 50, amount);
		String position = "fieldX" + x + "Y" + (y + 1);
		this.wheatfieldThreeTracker.put(position, arableField);
		arableField.setOwned(false);
		return arableField;
	}
	public void updateWheatfields(GridPane gridPane) {
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), ev -> {
			for (int y = 0; y < bitmap.getHeight(); y++) {
				for (int x = 0; x < bitmap.getWidth(); x++) {
					String position = "fieldX" + x + "Y" + (y + 1);
					if (wheatfieldOneTracker.get(position) != null) {
						ArableField field = wheatfieldOneTracker.get(position);
						field.update();
					}
					else if(wheatfieldTwoTracker.get(position) != null) {
						ArableField field = wheatfieldTwoTracker.get(position);
						field.update();
					}
					else if(wheatfieldThreeTracker.get(position) != null) {
						ArableField field = wheatfieldThreeTracker.get(position);
						field.update();
					}
				}
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
}
