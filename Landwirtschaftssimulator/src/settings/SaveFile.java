package settings;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Fields.ArableField;
import buildings.Player;
import machinery.Cultivator;
import machinery.Harvester;
import machinery.SeedDrill;
import machinery.Tractor;

public class SaveFile {
	private String playerName = "";
	private int playerX = 1300;
	private int playerY = 550;
	Player player;
	Tractor tractor;
	Cultivator cultivator;
	SeedDrill seeddrill;
	Harvester harvester;

	public void setup(Player player_, Tractor tractor_, Cultivator cultivator_, SeedDrill seeddrill_,
			Harvester harvester_) {
		player = player_;
		tractor = tractor_;
		cultivator = cultivator_;
		seeddrill = seeddrill_;
		harvester = harvester_;
	}
	// Player

	@SuppressWarnings("unchecked")
	public void savetofile(HashMap fieldtracker) {
		JSONObject obj = new JSONObject();
		obj.put("Playername", getPlayerName());
		obj.put("PlayerX", getPlayerX());
		obj.put("PlayerY", getPlayerY());
		obj.put("Cash", getCash());
		obj.put("TractorX", getTractorX());
		obj.put("TractorY", getTractorY());
		obj.put("TractorFuel", getTractorFuel());
		obj.put("HarvesterX", getHarvesterX());
		obj.put("HarvesterY", getHarvesterY());
		obj.put("HarvesterFuel", getHarvesterFuel());
		obj.put("HarvesterLoad", getHarvesterLoad());
		/*
		 * obj.put("DumpTruckX", getDumpTruckX()); obj.put("DumpTruckY",
		 * getDumpTruckY()); obj.put("DumpTruckLoad", getDumTruckLoad());
		 */
		obj.put("SeedDrillX", getSeedDrillX());
		obj.put("SeedDrillY", getSeedDrillY());
		obj.put("UnitPrice", getUnitPrice());
		obj.put("Amount", getAmount());
		obj.put("PriceField3", getPriceField3());
		obj.put("PriceField2", getPriceField2());

		fieldtracker.forEach((position, field) -> obj.put(position, ((ArableField) field).getState()));
		try {
			FileWriter file = new FileWriter("src/settings/savegame.json");
			file.write(obj.toString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadfile(HashMap fieldtracker) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) parser.parse(new FileReader("src/settings/savegame.json"));
			setPlayerName((String) obj.get("PlayerName"));
			setPlayerX((int) (long) obj.get("PlayerX"));
			setPlayerY((int) (long) obj.get("PlayerY"));
			setCash((int) (long) obj.get("Cash"));
			setTractorX((int) (long) obj.get("TractorX"));
			setTractorY((int) (long) obj.get("TractorY"));
			setTractorFuel((int) (long) obj.get("TractorFuel"));
			setHarvesterX((int) (long) obj.get("HarvesterX"));
			setHarvesterY((int) (long) obj.get("HarvesterY"));
			setHarvesterFuel((int) (long) obj.get("HarvesterFuel"));
			setHarvesterLoad((int) (long) obj.get("HarvesterLoad"));
			/*
			 * setDumpTruckX((int) (long) obj.get("DumpTruckX")); setDumpTruckY((int) (long)
			 * obj.get("DumpTruckY")); setDumTruckLoad((int) (long)
			 * obj.get("DumpTruckLoad"));
			 */
			setSeedDrillX((int) (long) obj.get("SeedDrillX"));
			setSeedDrillY((int) (long) obj.get("SeedDrillY"));
			setUnitPrice((int) (long) obj.get("UnitPrice"));
			setAmount((int) (long) obj.get("Amount"));
			setPriceField3((int) (long) obj.get("PriceField3"));
			setPriceField2((int) (long) obj.get("PriceField2"));

			Integer[] fas = (Integer[]) obj.get("Fieldstates");
			//ArableField[] afs = (ArableField[]) fieldtracker.values().toArray();
			fieldtracker.forEach((position, field) -> ((ArableField) field).setState((int) (long) obj.get(position)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// getter & setter Player Name
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	// getter & setter Player Pos.
	public int getPlayerX() {
		try {
			return (int) player.getX();
		} catch (Exception e) {
			return 0;
		}
	}

	public void setPlayerX(int playerX) {
		this.playerX = playerX;
		player.setX(playerX);
	}

	public int getPlayerY() {
		return (int) player.getY();
	}

	public void setPlayerY(int playerY) {
		this.playerY = playerY;
		player.setY(playerY);
	}

	// Cash
	private int cash = 2000;

	// getter & setter Cash
	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	// Tractor
	private int tractorX = 1300;
	private int tractorY = 500;
	private int tractorFuel = 100;

	// getter & setter Tractor Pos.
	public int getTractorX() {
		return (int) tractor.getX();
	}

	public void setTractorX(int tractorX) {
		this.tractorX = tractorX;
		tractor.setX(tractorX);
	}

	public int getTractorY() {
		return (int) tractor.getY();
	}

	public void setTractorY(int tractorY) {
		this.tractorY = tractorY;
		tractor.setY(tractorY);
	}

	// getter & setter Tractor current Fuel
	public int getTractorFuel() {
		try {
			return tractor.getFuel();
		} catch (Exception e) {
			return 0;
		}
	}

	public void setTractorFuel(int tractorFuel) {
		this.tractorFuel = tractorFuel;
		tractor.setFuel(tractorFuel);
	}

	// Harvester
	private int harvesterX = 1250;
	private int harvesterY = 500;
	private int harvesterFuel = 100;
	private int harvesterLoad = 0;

	// getter & setter Harvester Pos.
	public int getHarvesterX() {
		return (int) harvester.getX();
	}

	public void setHarvesterX(int harvesterX) {
		this.harvesterX = harvesterX;
		harvester.setX(harvesterX);
	}

	public int getHarvesterY() {
		return (int) harvester.getY();
	}

	public void setHarvesterY(int harvesterY) {
		this.harvesterY = harvesterY;
		harvester.setY(harvesterY);
	}

	// getter & setter Harvester current Fuel
	public int getHarvesterFuel() {
		try {
			return harvester.getFuel();
		} catch (Exception e) {
			return 0;
		}
	}

	public void setHarvesterFuel(int harvesterFuel) {
		this.harvesterFuel = harvesterFuel;
		harvester.setFuel(harvesterFuel);
	}

	// getter & setter Harvester current Load
	public int getHarvesterLoad() {
		try {
			return harvester.getLoad();
		} catch (Exception e) {
			return 0;
		}

	}

	public void setHarvesterLoad(int harvesterLoad) {
		this.harvesterLoad = harvesterLoad;
		harvester.setLoad(harvesterLoad);
	}

	// Cultivator
	private int cultivatorX = 1250;
	private int cultiavtorY = 500;

	// getter & setter Cultivator Pos.
	public int getCultivatorX() {
		return (int) cultivator.getX();
	}

	public void setCultivatorX(int cultivatorX) {
		this.cultivatorX = cultivatorX;
		cultivator.setX(cultivatorX);
	}

	public int getCultiavtorY() {
		return (int) cultivator.getY();
	}

	public void setCultiavtorY(int cultiavtorY) {
		this.cultiavtorY = cultiavtorY;
		cultivator.setY(cultiavtorY);
	}

	// Dump Truck
	/*
	 * private int dumpTruckX = 1250; private int dumpTruckY = 500; private int
	 * dumTruckLoad = 0;
	 */

	// getter & setter Dump Truck Pos.
	/*
	 * public int getDumpTruckX() { return dumptruck.getX(); }
	 *
	 * public void setDumpTruckX(int dumpTruckX) { this.dumpTruckX = dumpTruckX; }
	 *
	 * public int getDumpTruckY() { return dumpTruckY; }
	 *
	 * public void setDumpTruckY(int dumpTruckY) { this.dumpTruckY = dumpTruckY; }
	 *
	 * // getter & setter Dump Truck current Load public int getDumTruckLoad() {
	 * return dumTruckLoad; }
	 *
	 * public void setDumTruckLoad(int dumTruckLoad) { this.dumTruckLoad =
	 * dumTruckLoad;
	 */
	// }

	// Seed Drill
	private int seedDrillX = 1350;
	private int seedDrillY = 500;

	// getter & setter Seed Drill Pos.
	public int getSeedDrillX() {
		return (int) seeddrill.getX();
	}

	public void setSeedDrillX(int seedDrillX) {
		this.seedDrillX = seedDrillX;
		seeddrill.setX(seedDrillX);
	}

	public int getSeedDrillY() {
		return (int) seeddrill.getY();
	}

	public void setSeedDrillY(int seedDrillY) {
		this.seedDrillY = seedDrillY;
		seeddrill.setY(seedDrillY);
	}

	// ArableField

	private int amount = 0;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	private int unitPrice = 0;

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	private int priceField2 = 0;
	private int priceField3 = 0;

	public int getPriceField2() {
		return priceField2;
	}

	public void setPriceField2(int priceField2) {
		this.priceField2 = priceField2;
	}

	public int getPriceField3() {
		return priceField3;
	}

	public void setPriceField3(int priceField3) {
		this.priceField3 = priceField3;
	}

}
