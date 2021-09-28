package buildings;

import java.util.ArrayList;

/**
 * This class models the Farmyard. Particularly it saves the Silolevel as well as it updates it.
 * It also stores Vehicles and Machineries.
 * 
 * @author Julius Großmann
 * @version 1.0
 * 
 */

import Utils.AvailableObjectsNearby;
import Utils.WheatfieldActions;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import machinery.Cultivator;
import machinery.DumpTruck;
import machinery.Equipment;
import machinery.Harvester;
import machinery.SeedDrill;
import machinery.Tractor;
import machinery.Vehicle;
import settings.GameState;

public class Farmyard extends building {

	ArrayList<String> actions = new ArrayList<>();
	Tractor tractor;
	Player player;
	Cultivator cultivator;
	SeedDrill seedDrill;
	DumpTruck dumpTruck;
	Harvester harvester;
	AvailableObjectsNearby aonb;
	GameState save;

	/*
	 * @param siloLevel the current Silolevel.
	 * 
	 * @param maxSiloLevel the Limit of the amount in the Silo.
	 * 
	 * @param machineParking the List with the parking Vehicles an Machineries.
	 */	
	private int siloLevel = 0;
	private int maxSiloLevel = 5000;
	Image Farmyard = new Image("File:./Images/Scheune.png", 50, 50, false, false);
	ArrayList<String> machineParking = new ArrayList<>();

	/*
	 * The constructor for the Farmyard class. Calls the super constructor of
	 * building. Sets the image.
	 * 
	 * @param int x the x coordinate of the Farmyard.
	 * 
	 * @param int y the y coordinate of the Farmyard.
	 * 
	 * @param AvailableObjectsNearby aonb the instance of AONB, allowing to search
	 * for nearby objects.
	 * 
	 * @param GameState save the instance of the game state.
	 * 
	 * @param Player player the instance of the Player.
	 */
	public Farmyard(int x, int y, AvailableObjectsNearby aonb, GameState save, Player player) {
		super(x, y);
		this.setImage(Farmyard);
		this.aonb = aonb;
		this.save = save;
		this.player = player;
	}

	/*
	 * Checks if the Dumptruck is near the Farmyard and fills the load into the Silo if there is enough space.
	 */
	public void fillSilo() {
		Equipment activeEquipment = tractor.getTrailer();
		if (activeEquipment == dumpTruck) {	//is the dumptruck attached?
			save.setSiloLevel(save.getSiloLevel() + dumpTruck.getLoad());	//setting the Silolevel
			if (save.getSiloLevel() > maxSiloLevel) {	//is the Silolevel bigger than the maximum of the Silo?
				dumpTruck.setLoad(save.getSiloLevel() - maxSiloLevel);	//setting the dumptruck load
				save.setSiloLevel(maxSiloLevel);	//setting the Silolevel
			} else {
				dumpTruck.setLoad(0);	//setting the dumptruck load
			}
		}
	}

	/*
	 * Checks if the Dumptruck is near the Farmyard and fills the load from the Silo into the Dumptruck .
	 * 
	 * @param int amountOfClear the amount of load filling in the dumptruck
	 */
	public void clearSilo() {
		Equipment activeEquipment = tractor.getTrailer();
		if (activeEquipment == dumpTruck) {	//is the dumptruck attached?
			int amountOfClear = dumpTruck.getMaxload() - dumpTruck.getLoad();	//determine the loading
			if (save.getSiloLevel() >= (amountOfClear)) {	//is enough in the Silo
				dumpTruck.setLoad(dumpTruck.getMaxload());	//setting dumptruck load
				save.setSiloLevel(save.getSiloLevel() - amountOfClear);	//setting new Silolevel
			} else {
				dumpTruck.setLoad(dumpTruck.getLoad() + save.getSiloLevel());	//setting dumptruck load
				save.setSiloLevel(0);	//setting Silolevel to zero
			}
		}
	}

	/*
	 * Checks if the Vehicle or Machinery is stored in the Farmyard and brings it back to the field.
	 * 
	 * @param String storingMachinery the List of the stored Machineries and Vehicles, necessary to know if Vehicle/Machinery ist parking at the moment.
	 * 
	 * @param int index the index to go through the Arraylist
	 */
	public void machinePickUp(String storingMachinery, int index) {
		switch (storingMachinery) {	//showing which Machinery/Vehicle should be picked up
		case "tractor":
			tractor.setX(1350);	//setting the x-Coordinate so the tractor is again available
			tractor.setY(450);	//setting the y-Coordinate so the tractor is again available
			machineParking.remove(index);	//remove Vehicle/Machinery from the Arraylist
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

	/*
	 * Checks if the Vehicle or Machinery is near the Farmyard and stores them so they are not available anymore.
	 * 
	 * @param String storingMachinery the List of the stored Machineries and Vehicles, necessary for adding them to the List.
	 * 
	 * @param boolean machineryIsNotParking for cheeking if the Vehicle or Machinery is parking at the moment.
	 * 
	 * @param int zahler the index to go through the Arraylist 
	 */
	public void machineStore(String storingMachinery) {
		boolean machineryIsNotParking = true;

		if (machineParking.size() > 0) {	//is Machinery/Vehicle already parking?
			for (int zaehler = 0; zaehler < machineParking.size(); zaehler++) {
				if (machineParking.get(zaehler).equals(storingMachinery)) {
					machineryIsNotParking = false;
					int index = zaehler;
					machinePickUp(storingMachinery, index);		//if Machinery/Vehicle is parking, it comes back
				}
			}
		}

		if (machineryIsNotParking) {	//is Machinery/Vehicle is not parking

			Vehicle activeEquipment1 = (Vehicle) aonb.search(1300, 450, "Vehicle");	//search for the tractor at the Farmyard
			if (storingMachinery.equals("tractor") && tractor.trailer == null && activeEquipment1 == tractor) {	//is the tractor near the Farmyard, without a trailer and the tractor choosen to parking?
				machineParking.add(storingMachinery);	//adding the tractor to the Arraylist with the parking Vehicles/Machineries
				player.setX(tractor.getX());	//change the x-Cordinates of the player to set him near the Farmyard
				player.setY(tractor.getY());	//change the y-Cordinates of the player to set him near the Farmyard
				player.setImageW();	//setting the right player image 
				player.setEnteredVehicle(null, tractor); //remove player from the tractor
				tractor.setX(300);	//change the x-Cordinates of the tractor so it can not be used anymore and is shown add the top
				tractor.setY(0);	//change the y-Cordinates of the tractor so it can not be used anymore and is shown add the top
				tractor.setImageD();	//setting the right player image
			} else if (storingMachinery.equals("harvester")) {	//is the Harvester is choosen
				Vehicle activeEquipment2 = (Vehicle) aonb.search(1300, 450, "Vehicle2");
				if (activeEquipment2 == harvester) {	//if the Harvester is near the Farmyard
					machineParking.add(storingMachinery);
					player.setX(harvester.getX());
					player.setY(harvester.getY());
					player.setImageW();
					player.setEnteredVehicle(null, harvester);
					harvester.setX(350);
					harvester.setY(0);
					harvester.setImageD();
				}
			} else {
				Equipment activeEquipment = tractor.getTrailer();
				switch (storingMachinery) { //going through the Machineries to check if it is choosen
				case "cultivator":
					if (activeEquipment == cultivator) {
						machineParking.add(storingMachinery);
						tractor.deequip();		//deequip the cultivator
						cultivator.setX(150);
						cultivator.setY(0);
					}
					break;
				case "dumpTruck":
					if (activeEquipment == dumpTruck) {
						machineParking.add(storingMachinery);
						tractor.deequip();
						dumpTruck.setX(200);
						dumpTruck.setY(0);
					}
					break;

				case "seedDrill":
					if (activeEquipment == seedDrill) {
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

	/*
	 * Displays the Menu of the Farmyard. Buttons for storing or picking up Machineries and Vehicles. Buttons to load and unload the Silo.
	 * Buttons to buy new fields.
	 * 
	 * @param Farmyard farmyard is the Instance of the Farmyard.
	 * 
	 * @param Tractor tractor is the Instanz of the Tractor.
	 * 
	 * @param Cultivator cultivator is the Instance of the Cultivator.
	 * 
	 * @param SeedDrill seedDrill is the Instance of the Seeddrill.
	 * 
	 * @param Harvester harvester is the Instance of the HArvester.
	 * 
	 * @param DumpTruck dumpTruck is the Instance of the Dumptruck.
	 * 
	 * @param WheatfieldActions wa is the Instance of the WheatfieldActions.
	 */
	public void farmyardMenu(Farmyard farmyard, Tractor tractor_, Cultivator cultivator_, SeedDrill seedDrill_,
			Harvester harvester_, DumpTruck dumpTruck_, WheatfieldActions wa) {
		tractor = tractor_;
		seedDrill = seedDrill_;
		cultivator = cultivator_;
		harvester = harvester_;
		seedDrill = seedDrill_;
		dumpTruck = dumpTruck_;

		if (farmyard != null) {	//is the player near the Farmyard
			Stage popupwindow = new Stage();

			popupwindow.initModality(Modality.APPLICATION_MODAL);
			popupwindow.setTitle("Landwirtschaftssimulator");

			VBox layout = new VBox(10);
			Label label1 = new Label("Farmyard");
			label1.setWrapText(true);
			layout.getChildren().addAll(label1);

			Button BtnCultivator = new Button("Cultivator");	//Button to park or pickup the Cultivator
			BtnCultivator.setPrefSize(90, 18);
			BtnCultivator.setOnMouseClicked(e -> {
				machineStore("cultivator");
			});

			Button BtnDumpTruck = new Button("DumpTruck");
			BtnDumpTruck.setPrefSize(90, 18);
			BtnDumpTruck.setOnMouseClicked(e -> {
				machineStore("dumpTruck");
			});

			Button BtnHarvester = new Button("Harvester");
			BtnHarvester.setPrefSize(90, 18);
			BtnHarvester.setOnMouseClicked(e -> {
				machineStore("harvester");
			});

			Button BtnSeedDrill = new Button("SeedDrill");
			BtnSeedDrill.setPrefSize(90, 18);
			BtnSeedDrill.setOnMouseClicked(e -> {
				machineStore("seedDrill");
			});

			Button BtnTractor = new Button("Tractor");
			BtnTractor.setPrefSize(90, 18);
			BtnTractor.setOnMouseClicked(e -> {
				machineStore("tractor");
			});

			Button wheatfield2 = new Button("Buy second Wheatfield for: " + save.getPriceField2());
			wheatfield2.setPrefSize(200, 25);
			wheatfield2.setOnMouseClicked(e -> {
				wa.BuyWheatfieldTwo(save);
			});

			Button wheatfield3 = new Button("Buy third Wheatfield for: " + save.getPriceField3());
			wheatfield3.setPrefSize(200, 25);
			wheatfield3.setOnMouseClicked(e -> {
				wa.BuyWheatfieldThree(save);
			});

			Button load = new Button("load");
			load.setPrefSize(140, 18);
			load.setOnMouseClicked(e -> {
				clearSilo();
			});

			Button unload = new Button("unload");
			unload.setPrefSize(140, 18);
			unload.setOnMouseClicked(e -> {
				fillSilo();
			});

			Label currentSiloLevel = new Label("Silo level: " + save.getSiloLevel());	//showing the current Silolevel
			currentSiloLevel.setPrefSize(90, 18);

			Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), ev -> {	//updates Silolevel
				currentSiloLevel.setText("Silo level: " + save.getSiloLevel());
			}));
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();

			layout.getChildren().addAll(BtnCultivator, BtnDumpTruck, BtnHarvester, BtnSeedDrill, BtnTractor, load,
					unload, wheatfield2, wheatfield3, currentSiloLevel);

			layout.setAlignment(Pos.CENTER);

			Scene scene1 = new Scene(layout, 350, 400);

			popupwindow.setScene(scene1);

			popupwindow.showAndWait();

		} else {
			System.out.println("Not near enough!");
		}

	}
}
