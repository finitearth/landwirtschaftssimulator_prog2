package Fields;

import javafx.scene.image.Image;

public class ArableField extends Field{
	@SuppressWarnings("unused")
	private int grainAmount;
	@SuppressWarnings("unused")
	private int timeToGrow;
	int currentCondition;
	String[] growthState = {"sowReady", "growthStage0", "growthStage1", "growthStage2", "harvestReady", "harvested"};
	
	Image sowReady 		= new Image("File:./Images/Wheatfield0.png");
	Image growthStage0	= new Image("File:./Images/Wheatfield1.png");
	Image growthStage1 	= new Image("File:./Images/Wheatfield2.png");
	Image growthStage2 	= new Image("File:./Images/Wheatfield3.png");
	Image harvestReady 	= new Image("File:./Images/Wheatfield4.png");
	Image harvested 	= new Image("File:./Images/Wheatfield5.png");
	
	
	
	public ArableField(int setCurrentCondition) {
		currentCondition = setCurrentCondition;
		updateFieldImage(growthState[currentCondition]);
	}
	
	public ArableField() {
		currentCondition = 4; // growthState[4] => "harvestReady"
		updateFieldImage(growthState[currentCondition]);
	}
	
	@SuppressWarnings("unused")
	private String getHarvestCondition(int currentCondition) {
		return growthState[currentCondition];
	}
	
	private void updateFieldImage(String growthState) {
		switch(growthState) {
			case "sowReady"		:	this.setImage(sowReady); 	 break;
			case "growthStage0"	:	this.setImage(growthStage0); break;
			case "growthStage1"	:	this.setImage(growthStage1); break;
			case "growthStage2"	:	this.setImage(growthStage2); break;
			case "harvestReady"	:	this.setImage(harvestReady); break;
			case "harvested"	:	this.setImage(harvested); 	 break;
			default				:	System.out.println("Invalid grothState"); break;
		}
		
	}
	
}
