
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Optional;

import Fields.ArableField;
import Utils.AvailableObjectsNearby;
import Utils.CollisionChecker;
import Utils.NotificationPopUp;
import Utils.WheatfieldActions;
import buildings.Farmyard;
import buildings.GasStation;
import buildings.Landtrade;
import buildings.Player;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
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

public class MainApplication extends Application {
	GameState save = new GameState();
	AvailableObjectsNearby aonb = new AvailableObjectsNearby();
	WheatfieldActions wa = new WheatfieldActions(save);
	CollisionChecker bc;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		Pane welcome = new Pane();
		welcome.setPrefSize(1500, 1050);

		Label headline = new Label("Willkommen auf der Farm");
		headline.setFont(new Font("Arial", 40));
		headline.setPrefSize(460, 40);
		headline.relocate(520, 50);

		Button newGame = new Button("Neues Spiel");
		newGame.setFont(new Font("Arial", 30));
		newGame.setPrefSize(250, 20);
		newGame.relocate(625, 470);
		newGame.setOnMouseClicked(e -> {
			stage.setScene(chooseSettings(stage));
		});

		Button load = new Button("Spielstand laden");
		load.setFont(new Font("Arial", 30));
		load.setPrefSize(300, 20);
		load.relocate(600, 580);
		load.setOnMouseClicked(e -> {


			stage.setScene(chooseSettings(stage));
			save.loadfile(wa.wheatfieldOneTracker);
		});


		Button exit = new Button("Beenden");
		exit.setFont(new Font("Arial", 30));
		exit.setPrefSize(200, 20);
		exit.relocate(650, 690);
		exit.setOnMouseClicked(e -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Spiel beenden");
			alert.setContentText("Sind Sie sich sicher, dass Sie das Spiel beenden wollen?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				stage.close();
			} else {
				alert.close();
			}
		});

		Image backg = new Image("File:./Images/back.png", 1500, 1050, false, false); // TODO Hintergrundbild erstellen
		BackgroundImage backgroundImage = new BackgroundImage(backg, BackgroundRepeat.REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		welcome.setBackground(new Background(backgroundImage));

		welcome.getChildren().addAll(headline, load, newGame, exit);

		Scene scene = new Scene(welcome);

		stage.setScene(scene);
		stage.setTitle("Farmland");
		stage.show();

	}

	public Scene chooseSettings(Stage stage) {
		Pane settings = new Pane();
		settings.setPrefSize(1500, 1050);

		Label headline = new Label("Einstellungen");
		headline.setFont(new Font("Arial", 30));
		headline.setPrefSize(200, 20);
		headline.relocate(650, 30);

		Label nameDescription = new Label("Charakter Name");
		nameDescription.setFont(new Font("Arial", 16));
		nameDescription.setPrefSize(200, 10);
		nameDescription.relocate(250, 300);

		TextField name = new TextField();
		name.setPrefSize(200, 20);
		name.relocate(250, 330);

		Label difficultlyLevel = new Label("Schwierigkeitsgrad");
		difficultlyLevel.setFont(new Font("Arial", 16));
		difficultlyLevel.setPrefSize(400, 20);
		difficultlyLevel.relocate(250, 500);

		ToggleGroup difficultly = new ToggleGroup();
		RadioButton easy = new RadioButton("Einfach");
		easy.setToggleGroup(difficultly);
		easy.setSelected(true);
		easy.relocate(250, 530);
		RadioButton middle = new RadioButton("Mittel");
		middle.setToggleGroup(difficultly);
		middle.relocate(330, 530);
		RadioButton hard = new RadioButton("Schwer");
		hard.setToggleGroup(difficultly);
		hard.relocate(400, 530);

		ToggleGroup playerChoose = new ToggleGroup();

		RadioButton player1 = new RadioButton();
		player1.setToggleGroup(playerChoose);
		player1.setSelected(true);
		player1.relocate(1050, 300);
		Image player1Image = new Image("File:./Images/PlayerS.png", 50, 50, false, false);
		ImageView player1View = new ImageView(player1Image);
		player1View.relocate(1100, 280);

		RadioButton player2 = new RadioButton();
		player2.setToggleGroup(playerChoose);
		player2.relocate(1050, 400);
		Image player2Image = new Image("File:./Images/PlayerS.png", 50, 50, false, false);
		ImageView player2View = new ImageView(player2Image);
		player2View.relocate(1100, 380);

		RadioButton player3 = new RadioButton();
		player3.setToggleGroup(playerChoose);
		player3.relocate(1050, 500);
		Image player3Image = new Image("File:./Images/PlayerS.png", 50, 50, false, false);
		ImageView player3View = new ImageView(player3Image);
		player3View.relocate(1100, 480);

		RadioButton player4 = new RadioButton();
		player4.setToggleGroup(playerChoose);
		player4.relocate(1050, 600);
		Image player4Image = new Image("File:./Images/PlayerS.png", 50, 50, false, false);
		ImageView player4View = new ImageView(player4Image);
		player4View.relocate(1100, 580);

		Button start = new Button("Start");
		start.setFont(new Font("Arial", 25));
		start.setPrefSize(100, 20);
		start.relocate(700, 850);
		start.setOnMouseClicked(e -> {

			if (name.getText() == "") {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Oh, da ist etwas schief gelaufen");
				alert.setContentText("Gib dem Farmer einen Namen");
				alert.showAndWait();
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					alert.close();
				}

			} else {
				save.setPlayerName(name.getText());

				if (easy.isSelected()) {
					save.setCash(2000);
					save.setAmount(5);
					save.setUnitPrice(20);
					save.setPriceField2(500);
					save.setPriceField3(1000);
				}
				if (middle.isSelected()) {
					save.setCash(1500);
					save.setAmount(3);
					save.setUnitPrice(15);
					save.setPriceField2(1000);
					save.setPriceField3(1500);
				}
				if (hard.isSelected()) {
					save.setCash(1000);
					save.setAmount(1);
					save.setUnitPrice(10);
					save.setPriceField2(1500);
					save.setPriceField3(2000);
				}

				stage.setScene(generateGame());
			}

		});

		Button back = new Button("Zur�ck");
		back.setFont(new Font("Arial", 12));
		back.setPrefSize(100, 10);
		back.relocate(700, 950);
		back.setOnMouseClicked(e -> {
		});
		/*
		 * Image backg = new Image("File:./Images/", 1500, 1050, false, false); // TODO
		 * Hintergrundbild erstellen BackgroundImage backgroundImage = new
		 * BackgroundImage(backg,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
		 * BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		 * settings.setBackground(new Background(backgroundImage));
		 */
		settings.getChildren().addAll(headline, nameDescription, name, start, difficultlyLevel, easy, middle, hard,
				player1, player1View, player2, player2View, player3, player3View, player4, player4View, back);

		Scene scene = new Scene(settings);

		return scene;

	}

	public Scene generateGame() {

		GridPane grid = generateGamefield(wa.bitmap);

		/*
		 * for (int i = 0; i < 30; i++) { ColumnConstraints column = new
		 * ColumnConstraints(50); // Spielfeldgrösse
		 * grid.getColumnConstraints().add(column); } for (int i = 0; i < 21; i++) {
		 * RowConstraints row = new RowConstraints(50);
		 * grid.getRowConstraints().add(row); }
		 */

		Label headline = new Label(save.getPlayerName() + "'s Farm");
		headline.setFont(new Font("Arial", 25));
		grid.add(headline, 13, 0, 3, 1);

		Label currentCash = new Label("Geld: " + save.getCash());
		currentCash.setFont(new Font("Arial", 25));
		grid.add(currentCash, 27, 0, 3, 1);

		Label fuelTractor = new Label("Tank Traktor: " + save.getTractorFuel());
		fuelTractor.setFont(new Font("Arial", 20));
		grid.add(fuelTractor, 23, 0, 4, 1);

		Label fuelHarvester = new Label("Tank M�hdrescher: " + save.getHarvesterFuel());
		fuelHarvester.setFont(new Font("Arial", 20));
		grid.add(fuelHarvester, 18, 0, 5, 1);

		Menu menu = new Menu("Men�");
		MenuBar menuBar = new MenuBar();

		MenuItem newGame = new MenuItem("Neues Spiel");
		newGame.setOnAction(e -> {

		});

		MenuItem load = new MenuItem("Spiel laden");
		load.setOnAction(e -> {
//			save.loadfile(wa.arablefieldtracker);
			save.loadfile(wa.wheatfieldOneTracker);
			save.loadfile(wa.wheatfieldTwoTracker);
			save.loadfile(wa.wheatfieldThreeTracker);
		});

		MenuItem saveGame = new MenuItem("Spiel speichern");
		saveGame.setOnAction(e -> {
//			save.savetofile(wa.arablefieldtracker);
			save.savetofile(wa.wheatfieldOneTracker);
			save.savetofile(wa.wheatfieldTwoTracker);
			save.savetofile(wa.wheatfieldThreeTracker);
		});

		MenuItem keyAssignment = new MenuItem("Tastenbelegung");
		keyAssignment.setOnAction(e -> {

		});

		menu.getItems().add(newGame);
		menu.getItems().add(load);
		menu.getItems().add(saveGame);
		menu.getItems().add(keyAssignment);

		menuBar.getMenus().add(menu);

		grid.add(menuBar, 0, 0, 2, 1);

		Image backg = new Image("File:./Images/Map.png", 1500, 1050, false, false);

		BackgroundImage backgroundImage = new BackgroundImage(backg, BackgroundRepeat.REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

		grid.setBackground(new Background(backgroundImage));

		/*
		 * File file = new File("Images/Bitmap.bmp"); // Weizenfelder BufferedImage
		 * bitmap;
		 */
		/*
		 * try { bitmap = ImageIO.read(file); for (int y = 0; y < bitmap.getHeight();
		 * y++) { for (int x = 0; x < bitmap.getWidth(); x++) { //
		 * System.out.println(bitmap.getRGB(x, y)); if(bitmap.getRGB(x, y) == -10728) {
		 * grid.add(new Field(), x, (y+1)); } } } } catch (IOException e) {
		 * e.printStackTrace(); }
		 */

		// grid.setGridLinesVisible(false);

		/*
		 * Image background = new Image("File:./Images/Map.png"); // TODO Bild ersetzen
		 * Festes Spielfeld ImageView backg = new ImageView(background);
		 */
		// grid.add(backg, 0, 10); // Landschaft
		for (int y = 0; y < wa.bitmap.getHeight(); y++) {
			for (int x = 0; x < wa.bitmap.getWidth(); x++) {
//				System.out.println(wa.bitmap.getRGB(x, y));
				if (wa.bitmap.getRGB(x, y) == -3628785) {
					ArableField arableField = wa.GenerateWheatfieldOne(x, y);
					aonb.add(arableField, "ArableField");
					grid.add(arableField, x, (y + 1));
				} else if (wa.bitmap.getRGB(x, y) == -1976724) {
					ArableField arableField = wa.GenerateWheatfieldTwo(x, y);
					aonb.add(arableField, "ArableField");
					grid.add(arableField, x, (y + 1));
				} else if (wa.bitmap.getRGB(x, y) == -3614961) {
					ArableField arableField = wa.GenerateWheatfieldThree(x, y);
					aonb.add(arableField, "ArableField");
					grid.add(arableField, x, (y + 1));
				}
			}
		}

		int upper_boundary = 50 - 50;
		int left_boundary = 0 - 50;
		int right_boundary = 1500;
		int lower_boundary = 1050;

		bc = new CollisionChecker();
		bc.addboundary(left_boundary - 100, upper_boundary, left_boundary, lower_boundary); // Left Window boundary
		bc.addboundary(left_boundary, upper_boundary - 100, right_boundary, upper_boundary); // Upper Window boundary
		bc.addboundary(right_boundary, upper_boundary, right_boundary + 100, lower_boundary); // Right Window boundary
		bc.addboundary(left_boundary, lower_boundary, right_boundary, lower_boundary + 100); // Lower Window boundary

		for (int y = 0; y < wa.bitmap.getHeight(); y++) {
			for (int x = 0; x < wa.bitmap.getWidth(); x++) {
				if ((wa.bitmap.getRGB(x, y) == -14117429) ||(wa.bitmap.getRGB(x, y) == -12889573) ) {
					bc.addboundary(x*50, y*50+50, x*50+50, y*50+100);

				}

			}
		}
		
		DumpTruck dumptruck = new DumpTruck(1200, 450, 1000);
		Player player = new Player(1200, 400);
		Tractor tractor = new Tractor(1300, 500, 10000, 25.0, bc, aonb, save);
		Cultivator cultivator = new Cultivator(1250, 500);
		SeedDrill seeddrill = new SeedDrill(1350, 500);
		Landtrade landtrade = new Landtrade(1200, 550);
		Farmyard farmyard = new Farmyard(1300, 450);
		GasStation gasStation = new GasStation(250, 350);
		Harvester harvester = new Harvester(400, 500, 10000, 500, 25.0, bc, aonb, save);
		save.setup(player, tractor, cultivator, seeddrill, harvester, dumptruck);
		aonb.add(tractor, "Vehicle");
		aonb.add(cultivator, "Trailer");
		aonb.add(gasStation, "Building");
		aonb.add(landtrade, "Building");
		aonb.add(farmyard, "Building");
		aonb.add(seeddrill, "Trailer");
		aonb.add(harvester, "Vehicle");

		final Group group = new Group(grid, player, tractor, cultivator, gasStation, farmyard, landtrade, seeddrill,
				harvester);
		Scene scene = new Scene(group);

		movePlayerOnKeyPress(scene, player, gasStation, tractor, landtrade, farmyard, cultivator, seeddrill, save,
				harvester);
		movePlayerOnMousePress(scene, player, createTransition(player));

		// GridPane gridPane = generateGamefield(wa.bitmap);
		wa.updateWheatfields(grid);
		return scene;

	}

	private void movePlayerOnKeyPress(Scene scene, Player player, GasStation gasStation, Tractor tractorInstanz,
			Landtrade landtrade, Farmyard farmyard, Cultivator cultivator, SeedDrill seeddrill, GameState save,
			Harvester harvesterInstanz) { // TODO transitions

		/*
		 * bc.addboundary(550, 700, 650, 1000); // lower river and forest
		 * bc.addboundary(700, 350, 750, 650); // middle river bc.addboundary(600, 400,
		 * 850, 450); // river curls left bc.addboundary(600, 350, 650, 400); // river
		 * before bridge bc.addboundary(300, 250, 500, 300); // above gasstation
		 * bc.addboundary(0, 50, 300, 250); // upper right forest bc.addboundary(0, 300,
		 * 200, 350); //
		 */ scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				Vehicle enteredvehicle = player.getEnteredVehicle();
				double walkingspeed = 6.25;
				if (enteredvehicle == null) {
					switch (event.getCode()) {
					case UP, W:
						player.moveup(bc, walkingspeed);
						break;
					case RIGHT, D:
						player.moveright(bc, walkingspeed);
						break;
					case DOWN, S:
						player.movedown(bc, walkingspeed);
						break;
					case LEFT, A:
						player.moveleft(bc, walkingspeed);
						break;
					case E:
						player.setEnteredVehicle((Vehicle) aonb.search(player.getX(), player.getY(), "Vehicle"));
						break;
					case M:
						farmyard.farmyardMenu(((Farmyard) aonb.search(player.getX(), player.getY(), "Building")),
								tractorInstanz, aonb, player, cultivator, seeddrill, harvesterInstanz, wa);
					default:
						break;
					}
				} else if (enteredvehicle.getClass() == Tractor.class) {
					Tractor tractor = (Tractor) enteredvehicle;
					ArrayList<String> actions = new ArrayList<>();
					actions.add("test");
					NotificationPopUp wind = new NotificationPopUp("test", actions);
					switch (event.getCode()) {
					case UP, W:
						tractor.moveup();
						break;
					case RIGHT, D:
						tractor.moveright();
						break;
					case DOWN, S:
						tractor.movedown();
						break;
					case LEFT, A:
						tractor.moveleft();
						break;
					case E:
						player.setX(tractor.getX());
						player.setY(tractor.getY());
						player.setImageW();
						player.setEnteredVehicle(null);
						break;
					case X:
						tractor.equip((Equipment) aonb.search(tractor.getX(), tractor.getY(), "Trailer"));
						break;
					case F:
						System.out.println(wind.display());
						break;
					case Z:
						System.out.println(tractor.getX());
						System.out.println(tractor.getY());
						break;
					case L:
						gasStation.refuelTractor((GasStation) aonb.search(tractor.getX(), tractor.getY(), "Building"),
								tractorInstanz, save);
						break;
					case V:
						landtrade.selling((Landtrade) aonb.search(tractor.getX(), tractor.getY(), "Building"));
						break; // nur provisorisch
					case M:
						farmyard.farmyardMenu(((Farmyard) aonb.search(tractor.getX(), tractor.getY(), "Building")),
								tractorInstanz, aonb, player, cultivator, seeddrill, harvesterInstanz, wa);
					default:
						break;
					}

				} else if (enteredvehicle.getClass() == Harvester.class) {
					Harvester harvester = (Harvester) enteredvehicle;
					switch (event.getCode()) {
					case UP, W:
						harvester.moveup();
						break;
					case RIGHT, D:
						harvester.moveright();
						break;
					case DOWN, S:
						harvester.movedown();
						break;
					case LEFT, A:
						harvester.moveleft();
						break;
					case E:
						player.setX(harvester.getX());
						player.setY(harvester.getY());
						player.setImageW();
						player.setEnteredVehicle(null);
						break;
					case L:
						gasStation.refuelHarvester(
								(GasStation) aonb.search(harvester.getX(), harvester.getY(), "Building"),
								harvesterInstanz, save);
						break;
					case M:
						farmyard.farmyardMenu(
								((Farmyard) aonb.search(harvester.getX(), harvester.getY(), "Building")),
								tractorInstanz, aonb, player, cultivator, seeddrill, harvesterInstanz, wa);
					case SPACE:
						break;
					default:
						break;
					}
				}
			}
		});

	}

	private void movePlayerOnMousePress(Scene scene, final Player player, final TranslateTransition transition) {
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				int newX = ((int) event.getSceneX() / 50) * 50 + 10; // Rundet Position der Maus ab und zentriert
																		// Spieler
				int newY = ((int) event.getSceneY() / 50) * 50 + 10; // 10 = (Feld.Breite - Spieler.Breite) / 2
				if (!event.isControlDown()) {
					player.setX(newX);
					player.setY(newY);
				} else {
					transition.setToX(newX - player.getX());
					transition.setToY(newY - player.getY());
					transition.playFromStart();
				}
			}
		});
	}

	private TranslateTransition createTransition(final Player player) {
		final TranslateTransition transition = new TranslateTransition(Duration.seconds(0.25), player);
		transition.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				player.setX(player.getTranslateX() + player.getX());
				player.setY(player.getTranslateY() + player.getY());
				player.setTranslateX(0);
				player.setTranslateY(0);
			}
		});
		return transition;
	}

//	public void updateFields(GridPane gridPane, BufferedImage bitmap) {
//		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), ev -> {
//			for (int y = 0; y < bitmap.getHeight(); y++) {
//				for (int x = 0; x < bitmap.getWidth(); x++) {
////						System.out.println(bitmap.getRGB(x, y));
//					if (bitmap.getRGB(x, y) == -3628785) {
//						String position = "fieldX" + x + "Y" + (y + 1);
//						ArableField field = wa.wheatfieldOneTracker.get(position);
//						if (field != null) {
//							field.update();
//						}
//						else
//							System.out.println("errorr");
//					}
//				}
//			}
//		}));
//		timeline.setCycleCount(Animation.INDEFINITE);
//		timeline.play();
//	}

	public GridPane generateGamefield(BufferedImage bitmap) {
		GridPane grid = new GridPane();

		for (int i = 0; i < 30; i++) {
			ColumnConstraints column = new ColumnConstraints(50); // Spielfeldgrösse
			grid.getColumnConstraints().add(column);
		}

		for (int i = 0; i < 21; i++) {
			RowConstraints row = new RowConstraints(50);
			grid.getRowConstraints().add(row);
		}
		// grid.setGridLinesVisible(false);
		return grid;

		/*
		 * File file = new File("Images/Bitmap.bmp"); // Weizenfelder BufferedImage
		 * bitmap;
		 */
		/*
		 * try { bitmap = ImageIO.read(file); for (int y = 0; y < bitmap.getHeight();
		 * y++) { for (int x = 0; x < bitmap.getWidth(); x++) { //
		 * System.out.println(bitmap.getRGB(x, y)); if(bitmap.getRGB(x, y) == -10728) {
		 * grid.add(new Field(), x, (y+1)); } } } } catch (IOException e) {
		 * e.printStackTrace(); }
		 */

	}

}