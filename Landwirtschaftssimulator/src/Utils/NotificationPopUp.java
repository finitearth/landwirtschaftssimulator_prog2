package Utils;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A class implementing methods necessary to make pop up notification windows.
 *
 * @author Tom Zehle
 * @version 1.0
 *
 */
public class NotificationPopUp {
	String message;
	ArrayList<String> actions;
	String answer;

	/**
	 * Constructor of the NotificationPopUp-Class
	 *
	 * @param String message The message to be displayed
	 *
	 * @param Arraylist<String> actions an arraylist of actions (string) to be
	 * chosen from.
	 */
	public NotificationPopUp(String message, ArrayList<String> actions) {
		this.message = message;
		this.actions = actions;
	}

	/**
	 * Displays the pop up window.
	 */
	public String display() {
		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Landwirtschaftssimulator");

		VBox layout = new VBox(10);
		Label label = new Label(message);
		label.setWrapText(true);
		layout.getChildren().addAll(label);

		ArrayList<Button> buttons = new ArrayList<>();
		for (String action : actions) {
			buttons.add(new Button(action));
			buttons.get(buttons.size() - 1).setOnAction(e -> {
				answer = action;
				popupwindow.close();
			});
			layout.getChildren().add(buttons.get(buttons.size() - 1));
		}

		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout, 300, 250);

		popupwindow.setScene(scene);

		popupwindow.showAndWait();

		return answer;

	}

}
