package resources;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends ImageView {				// ImageView hat schon posX und posY + getter/setter
													// also wieso diese überschreiben?
	Image playerImage = new Image("File:./Images/PlayerTest.png");
	
//	private int posX;
//	private int posY;
	private boolean mounted = false;
	
	public Player(int posX, int posY){
		this.setX(posX);
		this.setY(posY);
		this.setImage(playerImage);
	}
//	public int getPosX() {
//		return posX;
//	}

//	public void setPosX(int posX) {
//		this.posX = posX;
//	}

//	public int getPosY() {
//		return posY;
//	}
//
//	public void setPosY(int posY) {
//		this.posY = posY;
//	}
	public boolean isMounted() {
		return mounted;
	}
	public void setMounted(boolean mounted) {
		this.mounted = mounted;
	}
	
}
