package settings;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Fields.ArableField;
import buildings.Farmyard;
import buildings.Player;
import machinery.Cultivator;
import machinery.DumpTruck;
import machinery.Harvester;
import machinery.SeedDrill;
import machinery.Tractor;

/**
 * A class that saves the state of the game to a file and is able to load the
 * game state from a file.
 *
 * @author Lukas Bum�ller and Tom Zehle
 * @version 1.0
 *
 */
public class GameState {
	String playerName;
	Player player;
	Tractor tractor;
	Cultivator cultivator;
	SeedDrill seeddrill;
	Harvester harvester;
	DumpTruck dumptruck;
	Farmyard farmyard;

	/**
	 * setup of the gamestate instance. Defines all the necessary objects that need
	 * to be kept track of.
	 */
	public void setup(Player player_, Tractor tractor_, Cultivator cultivator_, SeedDrill seeddrill_,
			Harvester harvester_, DumpTruck dumptruck, Farmyard farmyard_) {
		player = player_;
		tractor = tractor_;
		cultivator = cultivator_;
		seeddrill = seeddrill_;
		harvester = harvester_;
		farmyard = farmyard_;
		this.dumptruck = dumptruck;
	}

	/**
	 * finds all relevant variables of the relevant objects, as well as game
	 * variables and saves them to a json file.
	 */

	@SuppressWarnings("unchecked")
	public void savetofile(HashMap<String, ArableField> fieldtracker) {
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

		obj.put("DumpTruckX", getDumpTruckX());
		obj.put("DumpTruckY", getDumpTruckY());
		obj.put("DumpTruckLoad", getDumpTruckLoad());

		obj.put("SeedDrillX", getSeedDrillX());
		obj.put("SeedDrillY", getSeedDrillY());
		obj.put("UnitPrice", getUnitPrice());
		obj.put("Amount", getAmount());
		obj.put("PriceField3", getPriceField3());
		obj.put("PriceField2", getPriceField2());

		fieldtracker.forEach((position, field) -> {

			if (field.isOwned()) {
				obj.put(position, field.getState());
			}

			else {
				obj.put(position, -10);
			}
		});

		try {
			FileWriter file = new FileWriter("src/settings/savegame.json");
			file.write(obj.toString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets all the relevant variables of all relevant objects, as well as the game
	 * variables to the values defined in the json file.
	 */

	public void loadfile(HashMap<String, ArableField> fieldtracker) {
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

			setDumpTruckX((int) (long) obj.get("DumpTruckX"));
			setDumpTruckY((int) (long) obj.get("DumpTruckY"));
			setDumTruckLoad((int) (long) obj.get("DumpTruckLoad"));

			setSeedDrillX((int) (long) obj.get("SeedDrillX"));
			setSeedDrillY((int) (long) obj.get("SeedDrillY"));
			setUnitPrice((int) (long) obj.get("UnitPrice"));
			setAmount((int) (long) obj.get("Amount"));
			setPriceField3((int) (long) obj.get("PriceField3"));
			setPriceField2((int) (long) obj.get("PriceField2"));

			fieldtracker.forEach((position, field) -> {
				int state = (int) (long) obj.get(position);
				if (state == -10) {
					field.setOwned(false);
				} else {
					field.setOwned(true);
					field.setState(state);
				}

			});

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
		player.setX(playerX);
	}

	public int getPlayerY() {
		return (int) player.getY();
	}

	public void setPlayerY(int playerY) {
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

	// getter & setter Tractor Pos.
	public int getTractorX() {
		return (int) tractor.getX();
	}

	public void setTractorX(int tractorX) {
		tractor.setX(tractorX);
	}

	public int getTractorY() {
		return (int) tractor.getY();
	}

	private int siloLevel = 0;

	public void setSiloLevel(int fuel) {
		siloLevel = siloLevel + fuel;
	}

	public int getSiloLevel() {
		return siloLevel;
	}

	public void setTractorY(int tractorY) {
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
		tractor.setFuel(tractorFuel);
	}

	// Harvester

	// getter & setter Harvester Pos.
	public int getHarvesterX() {
		return (int) harvester.getX();
	}

	public void setHarvesterX(int harvesterX) {
		harvester.setX(harvesterX);
	}

	public int getHarvesterY() {
		return (int) harvester.getY();
	}

	public void setHarvesterY(int harvesterY) {
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
		harvester.setLoad(harvesterLoad);
	}

	// Cultivator

	// getter & setter Cultivator Pos.
	public int getCultivatorX() {
		return (int) cultivator.getX();
	}

	public void setCultivatorX(int cultivatorX) {
		cultivator.setX(cultivatorX);
	}

	public int getCultiavtorY() {
		return (int) cultivator.getY();
	}

	public void setCultiavtorY(int cultiavtorY) {
		cultivator.setY(cultiavtorY);
	}

	// Dump Truck

	// getter & setter Dump Truck Pos.

	public int getDumpTruckX() {
		return (int) dumptruck.getX();
	}

	public void setDumpTruckX(int dumpTruckX) {
		dumptruck.setX(dumpTruckX);
	}

	public int getDumpTruckY() {
		return (int) dumptruck.getX();
	}

	public void setDumpTruckY(int dumpTruckY) {
		dumptruck.setY(dumpTruckY);
	}

	// getter & setter Dump Truck current Load
	public int getDumpTruckLoad() {
		return dumptruck.getLoad();

	}

	public void setDumTruckLoad(int dumTruckLoad) {
		dumptruck.setLoad(dumTruckLoad);

	}

	// Seed Drill

	// getter & setter Seed Drill Pos.
	public int getSeedDrillX() {
		return (int) seeddrill.getX();
	}

	public void setSeedDrillX(int seedDrillX) {
		seeddrill.setX(seedDrillX);
	}

	public int getSeedDrillY() {
		return (int) seeddrill.getY();
	}

	public void setSeedDrillY(int seedDrillY) {
		seeddrill.setY(seedDrillY);
	}

	// ArableField

	private int amount;

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
