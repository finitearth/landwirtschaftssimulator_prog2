package Fields;

public class ArableField extends Field{
	private int grainAmount;
	private int timeToGrow;
	int currentCondition;
	String[] growthState = {"sowReady", "growthStage1", "growthStage2", "growthStage3", "harvestReady", "harvested"};
	
	private String getHarvestCondition(int currentCondition) {
		return growthState[currentCondition];
	}
	
}
