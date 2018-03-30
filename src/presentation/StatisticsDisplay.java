package presentation;

import data.InitProperties;

import data.Subject;

public class StatisticsDisplay extends AbstractDisplay {
	// Attributes	
	private float maxTemp = InitProperties.INIT_MAX_TEMP;
	private float minTemp = InitProperties.INIT_MIN_TEMP;
	private float tempSum= InitProperties.INIT_TEMP_SUM;
	private int numReadings;	
	// Constructor
	public StatisticsDisplay(Subject data) {
		super(data);
	}
	
	// | -- OBSERVER INTERFACE METHODS -- |
	// Update local attributes.
	public void update(float temp, float humidity, float pressure) {
		// Increment number of readings and total temperature. 
		this.tempSum += temp;
		this.numReadings++;
		// Reset max/min temperature.
		if (temp > maxTemp) {
			this.maxTemp = temp;} 
		if (temp < minTemp) {
			this.minTemp = temp;}
		// Display min, max and average temperatures.
		display();
	}
	
	// | -- DISPLAY ELEMENT INTERFACE -- |
	// Print string to console.
	@Override
	public void display() {
		System.out.println("Avg/Max/Min temperature = " + getAveTemp()
			+ "/" + getMaxTemp() + "/" + getMinTemp());
	}
	// Reset properties to initial values.
	@Override
	public void reset(){
		this.maxTemp = InitProperties.INIT_MAX_TEMP;
		this.minTemp = InitProperties.INIT_MIN_TEMP;
		this.tempSum= InitProperties.INIT_TEMP_SUM;
		this.numReadings=0;
	}	

	
	
	// Getters for values.
	public float getAveTemp(){
		return this.tempSum/numReadings;
	}
	public float getMaxTemp(){
		return this.maxTemp;
	}
	public float getMinTemp(){
		return this.minTemp;
	}
	

}

