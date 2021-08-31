package Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class NotificationPopUp {
	String message;
	ArrayList<String> actions;
	String answer;


	public NotificationPopUp(String message_, ArrayList<String> actions_) {
		message = message_;
		actions = actions_;
	}

	public String display() {
		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Landwirtschaftssimulator");

		VBox layout = new VBox(10);
		Label label1 = new Label(message);
		layout.getChildren().addAll(label1);
		
		ArrayList<Button> buttons = new ArrayList<>();
		for (String action : actions) {
			buttons.add(new Button(action));
			buttons.get(buttons.size() - 1).setOnAction(e -> {setAnswer(action);popupwindow.close();});
			layout.getChildren().add(buttons.get(buttons.size() - 1));
		}
		

		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 300, 250);

		popupwindow.setScene(scene1);

		popupwindow.showAndWait();

		return getAnswer();

	}
	public void setAnswer(String answer_) {
		answer = answer_;
	}
	
	public String getAnswer() {
		return answer;
	}

}
