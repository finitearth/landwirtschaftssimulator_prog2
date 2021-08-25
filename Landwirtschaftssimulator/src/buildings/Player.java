package buildings;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends ImageView {				
													
	Image playerImageW = new Image("File:./Images/PlayerTest.png");
	Image playerImageA = new Image("File:./Images/PlayerTest.png");
	Image playerImageS = new Image("File:./Images/PlayerTest.png");
	Image playerImageD = new Image("File:./Images/PlayerTest.png");
	
	private boolean mounted = false;
	
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
	
	public boolean isMounted() {
		return mounted;
	}
	public void setMounted(boolean mounted) {
		this.mounted = mounted;
	}
	
}
