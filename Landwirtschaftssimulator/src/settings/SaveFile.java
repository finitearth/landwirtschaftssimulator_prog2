package settings;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
public class SaveFile {
	
	//Player
	private String playerName = "";
	private int playerX = 1300;
	private int playerY = 550;
	
	public void savetofile() {
		  try {  
			  	JSONObject countryObj = new JSONObject();  
	            // Writing to a file  
	            File file=new File("JsonFile.json");  
	            file.createNewFile();  
	            FileWriter fileWriter = new FileWriter(file);  
	            System.out.println("Writing JSON object to file");  
	            System.out.println("-----------------------");  
	            System.out.print(countryObj);  

	            fileWriter.write(countryObj.toJSONString());  
	            fileWriter.flush();  
	            fileWriter.close();  

	        } catch (IOException e) {  
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
		return playerX;
	}
	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}
	public int getPlayerY() {
		return playerY;
	}
	public void setPlayerY(int playerY) {
		this.playerY = playerY;
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
	private int tractorFuel;
	
	// getter & setter Tractor Pos.
	public int getTractorX() {
		return tractorX;
	}
	public void setTractorX(int tractorX) {
		this.tractorX = tractorX;
	}
	public int getTractorY() {
		return tractorY;
	}
	public void setTractorY(int tractorY) {
		this.tractorY = tractorY;
	}
	
	// getter & setter Tractor current Fuel
	public int getTractorFuel() {
		return tractorFuel;
	}
	public void setTractorFuel(int tractorFuel) {
		this.tractorFuel = tractorFuel;
	}
	
	
	
	// Harvester
	private int harvesterX = 1250;
	private int harvesterY = 500;
	private int harvesterFuel;
	private int harvesterLoad = 0;
	
	// getter & setter Harvester Pos.
	public int getHarvesterX() {
		return harvesterX;
	}
	public void setHarvesterX(int harvesterX) {
		this.harvesterX = harvesterX;
	}
	public int getHarvesterY() {
		return harvesterY;
	}
	public void setHarvesterY(int harvesterY) {
		this.harvesterY = harvesterY;
	}
	
	// getter & setter Harvester current Fuel
	public int getHarvesterFuel() {
		return harvesterFuel;
	}
	public void setHarvesterFuel(int harvesterFuel) {
		this.harvesterFuel = harvesterFuel;
	}
	
	// getter & setter Harvester current Load
	public int getHarvesterLoad() {
		return harvesterLoad;
	}
	public void setHarvesterLoad(int harvesterLoad) {
		this.harvesterLoad = harvesterLoad;
	}
	
	
	
	// Cultivator
	private int cultivatorX = 1250;
	private int cultiavtorY = 500;

	// getter & setter Cultivator Pos.
	public int getCultivatorX() {
		return cultivatorX;
	}
	public void setCultivatorX(int cultivatorX) {
		this.cultivatorX = cultivatorX;
	}
	public int getCultiavtorY() {
		return cultiavtorY;
	}
	public void setCultiavtorY(int cultiavtorY) {
		this.cultiavtorY = cultiavtorY;
	}
	
	
	
	// Dump Truck
	private int dumpTruckX = 1250;
	private int dumpTruckY = 500;
	private int dumTruckLoad = 0;

	// getter & setter Dump Truck Pos.
	public int getDumpTruckX() {
		return dumpTruckX;
	}
	public void setDumpTruckX(int dumpTruckX) {
		this.dumpTruckX = dumpTruckX;
	}
	public int getDumpTruckY() {
		return dumpTruckY;
	}
	public void setDumpTruckY(int dumpTruckY) {
		this.dumpTruckY = dumpTruckY;
	}
	
	// getter & setter Dump Truck current Load
	public int getDumTruckLoad() {
		return dumTruckLoad;
	}
	public void setDumTruckLoad(int dumTruckLoad) {
		this.dumTruckLoad = dumTruckLoad;
	}
	
	
	
	// Seed Drill
	private int seedDrillX = 1350;
	private int seedDrillY = 500;

	// getter & setter Seed Drill Pos.
	public int getSeedDrillX() {
		return seedDrillX;
	}
	public void setSeedDrillX(int seedDrillX) {
		this.seedDrillX = seedDrillX;
	}
	public int getSeedDrillY() {
		return seedDrillY;
	}
	public void setSeedDrillY(int seedDrillY) {
		this.seedDrillY = seedDrillY;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
