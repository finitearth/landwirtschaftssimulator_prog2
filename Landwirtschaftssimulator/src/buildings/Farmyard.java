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
import javafx.stage.Modality;
import javafx.stage.Stage;
import machinery.Cultivator;
import machinery.Equipment;
import machinery.Harvester;
import machinery.SeedDrill;
import machinery.Tractor;
import machinery.Vehicle;
import settings.GameState;

public class Farmyard  extends building{


	ArrayList<String> actions = new ArrayList<>();
	String answer;


	private int siloLevel;
	private int maxSiloLevel;
	int maxload; //provisorisch
	int load;  //provisorisch
	Image Farmyard  = new Image("File:./Images/Scheune.png", 50, 50, false, false);
	ArrayList<String> machineParking = new ArrayList<>();

	public Farmyard(int x, int y) {
		super(x, y);
		this.setImage(Farmyard);
	}

	public void fillSilo(AvailableObjectsNearby aonb) {  //aonb mit dumptruck fehlt noch
		int amountOfFilling = load;
		siloLevel = siloLevel + amountOfFilling;
		if(siloLevel > maxSiloLevel) {
			load = siloLevel - maxSiloLevel;
			siloLevel = maxSiloLevel;
		}
		else {
			load = 0;
		}
	}
	public void clearSilo(AvailableObjectsNearby aonb) {   //aonb mit dumptruck fehlt noch
		int amountOfClear = maxload - load;
		if(siloLevel >= (amountOfClear)) {
			load = maxload;
			siloLevel = siloLevel - amountOfClear;
		}
		else {
			load = load + siloLevel;
			siloLevel = 0;
			}
	}
	public void machinePickUp(String storingMachinery, Tractor tractor, Cultivator cultivatorInstanz, SeedDrill seedDrillInstanz, int index) {
		switch(storingMachinery) {
			case "tractor":
				tractor.setX(1350);
				tractor.setY(450);
				machineParking.remove(index);
				break;
	//		case "harvester":
	//			tractor.setX(1350);
	//			tractor.setY(450);
	//			machineParking.remove(index);
	//			break;
			case "seedDrill":
				seedDrillInstanz.setX(1350);
				seedDrillInstanz.setY(450);
				machineParking.remove(index);
				break;
			case "cultivator":
				cultivatorInstanz.setX(1350);
				cultivatorInstanz.setY(450);
				machineParking.remove(index);
				break;
			case "dumpTruck":
				tractor.setX(1350);
				tractor.setY(450);
				machineParking.remove(index);
				break;
		}
	}

	public void machineStore(Tractor tractor, AvailableObjectsNearby aonb, String storingMachinery, Player player, Cultivator cultivatorInstanz, SeedDrill seedDrillInstanz, Harvester harvesterInstanz) {
		boolean machineryIsNotParking = true;

		if(machineParking.size()>0) {
			for(int zaehler = 0; zaehler < machineParking.size(); zaehler++) {
				if(machineParking.get(zaehler).equals(storingMachinery)) {
					machineryIsNotParking = false;
					int index = zaehler;
					machinePickUp(storingMachinery, tractor, cultivatorInstanz, seedDrillInstanz, index);
				}
			}
		}

		if (machineryIsNotParking){

		Vehicle activeEquipment4 = (Vehicle) aonb.search(1300, 450, "machinery.Vehicle");
		if(storingMachinery.equals("tractor") && tractor.trailer == null && activeEquipment4 != null) {
			machineParking.add(storingMachinery);
			player.setX(tractor.getX());
			player.setY(tractor.getY());
			player.setImageW();
			tractor.exit();
			player.setEnteredVehicle(null);
			tractor.setX(300);
			tractor.setY(0);
			tractor.setImageD();
		}
		else if(storingMachinery.equals("harvester")) {
			Vehicle activeEquipment5 = (Vehicle) aonb.search(1300, 450, "machinery.Vehicle");
			if(activeEquipment5 == harvesterInstanz) {
				machineParking.add(storingMachinery);
				player.setX(harvesterInstanz.getX());
				player.setY(harvesterInstanz.getY());
				player.setImageW();
				harvesterInstanz.exit();
				player.setEnteredVehicle(null);
				harvesterInstanz.setX(350);
				harvesterInstanz.setY(0);
				harvesterInstanz.setImageD();
		}
		}
		else {
			switch(storingMachinery) {
			case "cultivator":
				Equipment activeEquipment = ((Equipment) aonb.search(tractor.getX(), tractor.getY(), "machinery.Equipment"));
				if(activeEquipment == cultivatorInstanz) {
					machineParking.add(storingMachinery);
					tractor.deequip();
					cultivatorInstanz.setX(150);
					cultivatorInstanz.setY(0);
					}
				break;
		//	case "dumpTruck":
		//			Equipment activeEquipment2 = ((Equipment) aonb.search(tractor.getX(), tractor.getY(), "machinery.Equipment"));
		//			if(activeEquipment2 == null) {
		//				machineParking.add(storingMachinery);
		//				tractor.deequip();
		//				seedDrillInstanz.setX(200);
		//				seedDrillInstanz.setY(0);
		//			}
		//		break;

			case "seedDrill":
				Equipment activeEquipment3 = ((Equipment) aonb.search(tractor.getX(), tractor.getY(), "machinery.Equipment"));
					if(activeEquipment3 == seedDrillInstanz) {
						machineParking.add(storingMachinery);
						tractor.deequip();
						seedDrillInstanz.setX(250);
						seedDrillInstanz.setY(0);
					}
				break;
				default:
				break;
			}
		}
		}
	}


	public void farmyardMenu(Farmyard farmyard, Tractor tractorInstanz, AvailableObjectsNearby aonb, Player player, Cultivator cultivatorInstanz, SeedDrill seedDrillInstanz, Harvester harvesterInstanz, WheatfieldActions wa, GameState save) {
		if(farmyard != null) {
			Stage popupwindow = new Stage();

			popupwindow.initModality(Modality.APPLICATION_MODAL);
			popupwindow.setTitle("Landwirtschaftssimulator");

			VBox layout = new VBox(10);
			Label label1 = new Label("Farmyard");
			label1.setWrapText(true);
			layout.getChildren().addAll(label1);

			Button cultivator = new Button("Cultivator");
			cultivator.setPrefSize(90, 18);
			cultivator.setOnMouseClicked(e -> {machineStore(tractorInstanz, aonb, "cultivator", player, cultivatorInstanz, seedDrillInstanz, harvesterInstanz); });

			Button dumpTruck = new Button("DumpTruck");
			dumpTruck.setPrefSize(90, 18);
			dumpTruck.setOnMouseClicked(e -> { machineStore(tractorInstanz, aonb, "dumpTruck", player, cultivatorInstanz, seedDrillInstanz, harvesterInstanz);});

			Button harvester = new Button("Harvester");
			harvester.setPrefSize(90, 18);
			harvester.setOnMouseClicked(e -> { machineStore(tractorInstanz, aonb, "harvester", player, cultivatorInstanz, seedDrillInstanz, harvesterInstanz);});

			Button seedDrill = new Button("SeedDrill");
			seedDrill.setPrefSize(90, 18);
			seedDrill.setOnMouseClicked(e -> { machineStore(tractorInstanz, aonb, "seedDrill", player, cultivatorInstanz, seedDrillInstanz, harvesterInstanz);});

			Button tractor = new Button("Tractor");
			tractor.setPrefSize(90, 18);
			tractor.setOnMouseClicked(e -> { machineStore(tractorInstanz, aonb, "tractor", player, cultivatorInstanz, seedDrillInstanz, harvesterInstanz);});

			Button wheatfield2 = new Button("Buy second Wheatfield for: " + save.getPriceField2());
			wheatfield2.setPrefSize(200, 25);
			wheatfield2.setOnMouseClicked(e -> {wa.BuyWheatfieldTwo(save);});

			Button wheatfield3 = new Button("Buy third Wheatfield for: " + save.getPriceField3());
			wheatfield3.setPrefSize(200, 25);
			wheatfield3.setOnMouseClicked(e -> {wa.BuyWheatfieldThree(save);});

			Button load = new Button("load");
			load.setPrefSize(140, 18);
			load.setOnMouseClicked(e -> {clearSilo(aonb);});

			Button unload = new Button("unload");
			unload.setPrefSize(140, 18);
			unload.setOnMouseClicked(e -> {fillSilo(aonb);});

//			GridPane gridpane = new GridPane();
//			GridPane.setConstraints(load, 1, 0);
//			GridPane.setConstraints(unload, 2, 0);
//			GridPane.setConstraints(wheatfield2, 1, 1);
//			GridPane.setConstraints(wheatfield3, 1, 2);
//			Insets inset = new Insets(10, 5, 5, 5);
//			GridPane.setMargin(load, inset);
//			GridPane.setMargin(unload, inset);
//			GridPane.setMargin(wheatfield2, inset);
//			GridPane.setMargin(wheatfield3, inset);
//			gridpane.getChildren().addAll(wheatfield2, wheatfield3, load, unload);
//			gridpane.setAlignment(Pos.CENTER);
			
//			gridpane.setGridLinesVisible(true);

			layout.getChildren().addAll(cultivator, dumpTruck, harvester, seedDrill, tractor, load, unload, wheatfield2, wheatfield3);

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
