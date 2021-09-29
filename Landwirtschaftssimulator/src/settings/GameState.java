package settings;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import buildings.Farmyard;
import buildings.Player;
import fields.ArableField;
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

	
	// Player
	/**
	 * getter for the playerName variable.
	 *
	 * @return String playerName
	 */
	public String getPlayerName() {
		return playerName;
	}
	
	/**
	 * setter for the playerName variable.
	 *
	 * @param String playerName
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * getter for the Player position X.
	 *
	 * @return int player.getX
	 */
	public int getPlayerX() {
		try {
			return (int) player.getX();
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * setter for the Player position X.
	 *
	 * @param int playerX
	 */
	public void setPlayerX(int playerX) {
		player.setX(playerX);
	}
	
	/**
	 * getter for the Player position Y.
	 *
	 * @return int player.getY
	 */
	public int getPlayerY() {
		return (int) player.getY();
	}
	
	/**
	 * setter for the Player position Y.
	 *
	 * @param int playerY
	 */
	public void setPlayerY(int playerY) {
		player.setY(playerY);
	}

	// Cash
	private int cash = 2000;

	/**
	 * getter for the cash variable.
	 *
	 * @return int cash
	 */
	public int getCash() {
		return cash;
	}
	
	/**
	 * setter for the cash variable.
	 *
	 * @param int cash
	 */
	public void setCash(int cash) {
		this.cash = cash;
	}

	// Tractor

	/**
	 * getter for the Tractor position X.
	 *
	 * @return int tractor.getX
	 */
	public int getTractorX() {
		return (int) tractor.getX();
	}
	
	/**
	 * setter for the Tractor position x.
	 *
	 * @param int tractorX
	 */
	public void setTractorX(int tractorX) {
		tractor.setX(tractorX);
	}
	
	/**
	 * getter for the Tractor position Y.
	 *
	 * @return int tractor.getY
	 */
	public int getTractorY() {
		return (int) tractor.getY();
	}
	
	/**
	 * setter for the Tractor position Y.
	 *
	 * @param int tractorY
	 */
	public void setTractorY(int tractorY) {
		tractor.setY(tractorY);
	}

	/**
	 * getter for the Tractor fuel.
	 *
	 * @return int tractor.getFuel
	 */
	public int getTractorFuel() {
		try {
			return tractor.getFuel();
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * setter for the Tractor fuel.
	 *
	 * @param int tractorFuel
	 */
	public void setTractorFuel(int tractorFuel) {
		tractor.setFuel(tractorFuel);
	}

	// Harvester

	/**
	 * getter for the Harvester position X.
	 *
	 * @return int harvester.getX
	 */
	public int getHarvesterX() {
		return (int) harvester.getX();
	}

	/**
	 * setter for the Harvester position X.
	 *
	 * @param int harvesterX
	 */
	public void setHarvesterX(int harvesterX) {
		harvester.setX(harvesterX);
	}

	/**
	 * getter for the Harvester position Y.
	 *
	 * @return int harvester.getY
	 */
	public int getHarvesterY() {
		return (int) harvester.getY();
	}

	/**
	 * setter for the Harvester position Y.
	 *
	 * @param int harvesterY
	 */
	public void setHarvesterY(int harvesterY) {
		harvester.setY(harvesterY);
	}

	/**
	 * getter for the Harvester fuel.
	 *
	 * @return int harvester.getFuel
	 */
	public int getHarvesterFuel() {
		try {
			return harvester.getFuel();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * setter for the Harvester fuel.
	 *
	 * @param int harvesterFuel
	 */
	public void setHarvesterFuel(int harvesterFuel) {
		harvester.setFuel(harvesterFuel);
	}

	/**
	 * getter for the Harvester load.
	 *
	 * @return int harvester.getLoad
	 */
	public int getHarvesterLoad() {
		try {
			return harvester.getLoad();
		} catch (Exception e) {
			return 0;
		}

	}

	/**
	 * setter for the Harvester load.
	 *
	 * @param int harvesterLoad
	 */
	public void setHarvesterLoad(int harvesterLoad) {
		harvester.setLoad(harvesterLoad);
	}

	// Cultivator

	/**
	 * getter for the Cultivator position X.
	 *
	 * @return int cultivator.getX
	 */
	public int getCultivatorX() {
		return (int) cultivator.getX();
	}

	/**
	 * setter for the Cultivator position X.
	 *
	 * @param int cultivatorX
	 */
	public void setCultivatorX(int cultivatorX) {
		cultivator.setX(cultivatorX);
	}

	/**
	 * getter for the Cultivator position Y.
	 *
	 * @return int cultivator.getY
	 */
	public int getCultiavtorY() {
		return (int) cultivator.getY();
	}

	/**
	 * setter for the Cultivator position Y.
	 *
	 * @param int cultivatorY
	 */
	public void setCultiavtorY(int cultiavtorY) {
		cultivator.setY(cultiavtorY);
	}

	// Dump Truck
	/**
	 * getter for the Dump Truck position X.
	 *
	 * @return int dumptruck.getX
	 */
	public int getDumpTruckX() {
		return (int) dumptruck.getX();
	}

	/**
	 * setter for the Dump Truck position X.
	 *
	 * @param int dumpTruckX
	 */
	public void setDumpTruckX(int dumpTruckX) {
		dumptruck.setX(dumpTruckX);
	}

	/**
	 * getter for the Dump Truck position Y.
	 *
	 * @return int dumptruck.getY
	 */
	public int getDumpTruckY() {
		return (int) dumptruck.getX();
	}

	/**
	 * setter for the Dump Truck position Y.
	 *
	 * @param int dumpTruckY
	 */
	public void setDumpTruckY(int dumpTruckY) {
		dumptruck.setY(dumpTruckY);
	}

	/**
	 * getter for the Dump Truck load.
	 *
	 * @return int dumptruck.getLoad
	 */
	public int getDumpTruckLoad() {
		return dumptruck.getLoad();

	}

	/**
	 * setter for the Dump Truck load.
	 *
	 * @param int dumpTruckLoad
	 */
	public void setDumTruckLoad(int dumpTruckLoad) {
		dumptruck.setLoad(dumpTruckLoad);

	}

	// Seed Drill

	/**
	 * getter for the Seed Drill position X.
	 *
	 * @return int seeddrill.getX
	 */
	public int getSeedDrillX() {
		return (int) seeddrill.getX();
	}

	/**
	 * setter for the Seed Drill position X.
	 *
	 * @param int seedDrillX
	 */
	public void setSeedDrillX(int seedDrillX) {
		seeddrill.setX(seedDrillX);
	}

	/**
	 * getter for the Seed Drill position Y.
	 *
	 * @return int seeddrill.getY
	 */
	public int getSeedDrillY() {
		return (int) seeddrill.getY();
	}

	/**
	 * setter for the Seed Drill position Y.
	 *
	 * @param int seedDrillY
	 */
	public void setSeedDrillY(int seedDrillY) {
		seeddrill.setY(seedDrillY);
	}

	// ArableField

	private int amount;

	/**
	 * getter for the Amount of one Field.
	 *
	 * @return int amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * setter for the Amount of one Field.
	 *
	 * @param int amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	private int unitPrice = 0;

	/**
	 * getter for the Price of one Unit.
	 *
	 * @return int unitPrice
	 */
	public int getUnitPrice() {
		return unitPrice;
	}

	/**
	 * setter for the Price of one Unit.
	 *
	 * @param int unitPrice
	 */
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	private int priceField2 = 0;
	private int priceField3 = 0;

	/**
	 * getter for the Price for the arable field 2.
	 *
	 * @return int priceField2
	 */
	public int getPriceField2() {
		return priceField2;
	}

	/**
	 * setter for the Price for the arable field 2.
	 *
	 * @param int priceField2
	 */
	public void setPriceField2(int priceField2) {
		this.priceField2 = priceField2;
	}

	/**
	 * getter for the Price for the arable field 3.
	 *
	 * @return int priceField3
	 */
	public int getPriceField3() {
		return priceField3;
	}

	/**
	 * setter for the Price for the arable field 3.
	 *
	 * @param int priceField3
	 */
	public void setPriceField3(int priceField3) {
		this.priceField3 = priceField3;
	}
	
	//Farmyard
	private int siloLevel = 0;

	/**
	 * getter for the Farmyard Silo level
	 *
	 * @return int siloLevel
	 */
	public int getSiloLevel() {
		return siloLevel;
	}
	
	/**
	 * setter for the Farmyard Silo level
	 *
	 * @param int siloLevel
	 */
	public void setSiloLevel(int silolevel) {
		this.siloLevel = silolevel;
	}
	

}
