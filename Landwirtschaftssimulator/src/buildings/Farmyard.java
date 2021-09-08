package buildings;

import java.util.ArrayList;

import buildings.building;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Farmyard  extends building{
	
	
	String message = "test";
	ArrayList<String> actions = new ArrayList<>();
	String answer;
	
	
	private int siloLevel;
	private int maxSiloLevel;
	Image Farmyard  = new Image("File:./Images/Scheune.png", 50, 50, false, false);
	String[] machineParking = {};
	
	public Farmyard(int x, int y) {
		super(x, y);
		this.setImage(Farmyard);
	}
	
	public int fillSilo(int amountOfFilling) {
		siloLevel = siloLevel + amountOfFilling;
		if(siloLevel > maxSiloLevel) {
			siloLevel = maxSiloLevel;
			return siloLevel - maxSiloLevel;
		}
		else return 0;
	}
	public int clearSilo(int amountOfClear) {
		if(siloLevel >= amountOfClear) {
			siloLevel = siloLevel - amountOfClear;
			return amountOfClear;
		}
		else {
			int filling = siloLevel;
			siloLevel = 0;
			System.out.println("You just get " + filling); //nur provisorisch
			return filling;
		}
	}
	public void machinePickUp() {
		
	}
	public void machineStore() {
		
	}
	
	
	
	
	
	
	
	//folgendes ist erst provisorisch
	
	public String display(Farmyard farmyard) {
		if(farmyard != null) {
			Stage popupwindow = new Stage();

			popupwindow.initModality(Modality.APPLICATION_MODAL);
			popupwindow.setTitle("Landwirtschaftssimulator");

			VBox layout = new VBox(10);
			Label label1 = new Label(message);
			label1.setWrapText(true);
			layout.getChildren().addAll(label1);
			layout.setAlignment(Pos.CENTER);

			Scene scene1 = new Scene(layout, 300, 250);
			
			popupwindow.setScene(scene1);

			popupwindow.showAndWait();

			return getAnswer();
		}
		else {
			return null;
		}
		

	}
	public void setAnswer(String answer_) {
		answer = answer_;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	
	
	
}
