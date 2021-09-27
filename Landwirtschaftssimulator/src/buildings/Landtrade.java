package buildings;

import javafx.scene.image.Image;
import machinery.DumpTruck;
import machinery.Equipment;
import machinery.Tractor;
import settings.GameState;

public class Landtrade extends building {
	Image Landtrade = new Image("File:./Images/Silo.png", 50, 50, false, false);
	DumpTruck dumpTruck;
	Tractor tractor;
	GameState save;

	public Landtrade(int x, int y, GameState save) {
		super(x, y);
		this.setImage(Landtrade);
		this.save = save;
	}

	public void selling(Landtrade landtrade, DumpTruck dumpTruck_, Tractor tractor_) {
		dumpTruck = dumpTruck_;
		tractor = tractor_;
		Equipment activeEquipment = tractor.getTrailer();
		if (landtrade != null && activeEquipment == dumpTruck) {
			int payment = dumpTruck.getLoad() * save.getUnitPrice();
			save.setCash(save.getCash() + payment);
			dumpTruck.setLoad(0);
		} else {
			System.out.println("You are not near the Landtrade or your Dumptruck is missing!");
		}
	}
}