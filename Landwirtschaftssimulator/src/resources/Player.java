package resources;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Player extends ImageView {
	
	Image playerImage = new Image("File:./Images/PlayerTest.png");
	
	private int posX;
	private int posY;
	private boolean mounted = false;
	
	public Player(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
		this.setImage(playerImage);
	}
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	public boolean isMounted() {
		return mounted;
	}
	public void setMounted(boolean mounted) {
		this.mounted = mounted;
	}
	
}
