package buildings;

import java.util.ArrayList;

import Utils.AvailableObjectsNearby;
import Utils.WheatfieldActions;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import machinery.Cultivator;
import machinery.Equipment;
import machinery.Harvester;
import machinery.SeedDrill;
import machinery.Tractor;
import machinery.Vehicle;
import machinery.DumpTruck;
import settings.GameState;

public class Farmyard  extends building{
	
	
	ArrayList<String> actions = new ArrayList<>();
	Tractor tractor;
	Player player;
	Cultivator cultivator;;
	SeedDrill seedDrill;
	DumpTruck dumpTruck;
	Harvester harvester;
	AvailableObjectsNearby aonb;
	GameState save;
	
	
	private int siloLevel = 0;
	private int maxSiloLevel;
	Image Farmyard  = new Image("File:./Images/Scheune.png", 50, 50, false, false);
	ArrayList<String> machineParking = new ArrayList<>();
	
	public Farmyard(int x, int y, AvailableObjectsNearby aonb, GameState save, Player player) {
		super(x, y);
		this.setImage(Farmyard);
		this.aonb = aonb;
		this.save = save;
		this.player = player;
	}
	
	public void fillSilo() {  
		Equipment activeEquipment = tractor.getTrailer();
		if(activeEquipment == dumpTruck) {
		
		save.setSiloLevel(save.getSiloLevel() + dumpTruck.getLoad());
		if(save.getSiloLevel() > maxSiloLevel) {
			dumpTruck.setLoad(save.getSiloLevel() - maxSiloLevel);
			save.setSiloLevel(maxSiloLevel);
		}
		else {
			dumpTruck.setLoad(0);
		}
	}
	}
	public void clearSilo() {   
		Equipment activeEquipment = tractor.getTrailer();
		if(activeEquipment == dumpTruck) {
		int amountOfClear = dumpTruck.getMaxload() - dumpTruck.getLoad();
		if(save.getSiloLevel() >= (amountOfClear)) {
			dumpTruck.setLoad(dumpTruck.getMaxload());
			save.setSiloLevel(save.getSiloLevel() - amountOfClear);
		}
		else {
			dumpTruck.setLoad(dumpTruck.getLoad() + save.getSiloLevel());
			save.setSiloLevel(0);
			}
		}
	}
	
	
	
	public void machinePickUp(String storingMachinery, int index) {
		switch(storingMachinery) {
			case "tractor":
				tractor.setX(1350);
				tractor.setY(450);
				machineParking.remove(index);
				break;
			case "harvester":
				harvester.setX(1350);
				harvester.setY(450);
				machineParking.remove(index);
				break;
			case "seedDrill":
				seedDrill.setX(1350);
				seedDrill.setY(450);
				machineParking.remove(index);
				break;
			case "cultivator":
				cultivator.setX(1350);
				cultivator.setY(450);
				machineParking.remove(index);
				break;
			case "dumpTruck":
				dumpTruck.setX(1350);
				dumpTruck.setY(450);
				machineParking.remove(index);
				break;
		}
	}
	
	public void machineStore(String storingMachinery) {	
		boolean machineryIsNotParking = true;
		
		if(machineParking.size()>0) {
			for(int zaehler = 0; zaehler < machineParking.size(); zaehler++) {
				if(machineParking.get(zaehler).equals(storingMachinery)) {
					machineryIsNotParking = false;
					int index = zaehler;
					machinePickUp(storingMachinery, index);
				}
			}
		}
		
		if (machineryIsNotParking){
	
		Vehicle activeEquipment1 = (Vehicle) aonb.search(1300, 450, "Vehicle");	
		if(storingMachinery.equals("tractor") && tractor.trailer == null && activeEquipment1 == tractor) {
			machineParking.add(storingMachinery);
			player.setX(tractor.getX());	
			player.setY(tractor.getY()); 
			player.setImageW(); 
			player.setEnteredVehicle(null, tractor);
			tractor.setX(300);
			tractor.setY(0);
			tractor.setImageD();
		}
		else if(storingMachinery.equals("harvester")) {
			Vehicle activeEquipment2 = (Vehicle) aonb.search(1300, 450, "Vehicle2");	
			if(activeEquipment2 == harvester) {
				machineParking.add(storingMachinery);
				player.setX(harvester.getX());	
				player.setY(harvester.getY()); 
				player.setImageW(); 
				player.setEnteredVehicle(null, harvester);
				harvester.setX(350);
				harvester.setY(0);
				harvester.setImageD();
		}
		}
		else {
			Equipment activeEquipment = tractor.getTrailer();
			switch(storingMachinery) {
			case "cultivator":
				if(activeEquipment == cultivator) {
					machineParking.add(storingMachinery);		
					tractor.deequip();
					cultivator.setX(150);
					cultivator.setY(0);
					}
				break;
			case "dumpTruck":
					if(activeEquipment == dumpTruck) {
						machineParking.add(storingMachinery);
						tractor.deequip();
						dumpTruck.setX(200);
						dumpTruck.setY(0);
					}
				break;
				
			case "seedDrill":
					if(activeEquipment == seedDrill) {
						machineParking.add(storingMachinery);
						tractor.deequip();
						seedDrill.setX(250);
						seedDrill.setY(0);
					}
				break;
				default:
				break;
			}
		}
		}
	}
	
	
	public void farmyardMenu(Farmyard farmyard, Tractor tractor_, Cultivator cultivator_, SeedDrill seedDrill_, Harvester harvester_, DumpTruck dumpTruck_, WheatfieldActions wa) {
		tractor = tractor_;
		seedDrill = seedDrill_;
		cultivator = cultivator_;
		harvester = harvester_;
		seedDrill = seedDrill_;
		dumpTruck = dumpTruck_;

		if(farmyard != null) {
			Stage popupwindow = new Stage();

			popupwindow.initModality(Modality.APPLICATION_MODAL);
			popupwindow.setTitle("Landwirtschaftssimulator");

			VBox layout = new VBox(10);
			Label label1 = new Label("Farmyard");
			label1.setWrapText(true);
			layout.getChildren().addAll(label1);
			
			Button BtnCultivator = new Button("Cultivator");
			BtnCultivator.setPrefSize(90, 18);
			BtnCultivator.setOnMouseClicked(e -> {machineStore("cultivator"); });
			
			Button BtnDumpTruck = new Button("DumpTruck");
			BtnDumpTruck.setPrefSize(90, 18);
			BtnDumpTruck.setOnMouseClicked(e -> { machineStore("dumpTruck");});
			
			Button BtnHarvester = new Button("Harvester");
			BtnHarvester.setPrefSize(90, 18);
			BtnHarvester.setOnMouseClicked(e -> { machineStore("harvester");});
			
			Button BtnSeedDrill = new Button("SeedDrill");
			BtnSeedDrill.setPrefSize(90, 18);
			BtnSeedDrill.setOnMouseClicked(e -> { machineStore("seedDrill");});
			
			Button BtnTractor = new Button("Tractor");
			BtnTractor.setPrefSize(90, 18);
			BtnTractor.setOnMouseClicked(e -> { machineStore("tractor");});

			Button wheatfield2 = new Button("Buy second Wheatfield for: " + save.getPriceField2());
			wheatfield2.setPrefSize(200, 25);
			wheatfield2.setOnMouseClicked(e -> {wa.BuyWheatfieldTwo(save);});

			Button wheatfield3 = new Button("Buy third Wheatfield for: " + save.getPriceField3());
			wheatfield3.setPrefSize(200, 25);
			wheatfield3.setOnMouseClicked(e -> {wa.BuyWheatfieldThree(save);});
			
			Button load = new Button("load");
			load.setPrefSize(140, 18);
			load.setOnMouseClicked(e -> {clearSilo();});

			Button unload = new Button("unload");
			unload.setPrefSize(140, 18);
			unload.setOnMouseClicked(e -> {fillSilo();});
			
			Label currentSiloLevel = new Label("Silo level: " + save.getSiloLevel());
			currentSiloLevel.setPrefSize(90, 18);

			//GridPane gridpane = new GridPane();
			//			GridPane.setConstraints(load, 1, 0);
			//GridPane.setConstraints(unload, 2, 0);
			//GridPane.setConstraints(wheatfield2, 1, 1);
			//GridPane.setConstraints(wheatfield3, 2, 1);
			//Insets inset = new Insets(10, 5, 5, 5);
			//GridPane.setMargin(load, inset);
			//GridPane.setMargin(unload, inset);
			//GridPane.setMargin(wheatfield2, inset);
			//GridPane.setMargin(wheatfield3, inset);
			//gridpane.getChildren().addAll(wheatfield2, wheatfield3, load, unload);
			//gridpane.setAlignment(Pos.CENTER);
			
			layout.getChildren().addAll(BtnCultivator, BtnDumpTruck, BtnHarvester, BtnSeedDrill,BtnTractor,  load, unload, wheatfield2, wheatfield3, currentSiloLevel);
			
			layout.setAlignment(Pos.CENTER);
			
			Scene scene1 = new Scene(layout, 350, 400);
			
			popupwindow.setScene(scene1);

			popupwindow.showAndWait();
			
		}
		else {
			System.out.println("Not near enough!") ;
		}
		
	}
}
