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
		this.subject = data;
		data.registerObserver(this);
	}
	
	// OBSERVER INTERFACE METHODS:
	
	// Set this as subject
	public void setSubject(Subject data) {
		this.subject.removeObserver(this);
		data.registerObserver(this);
		this.subject = data;
		this.reset();
	}

	// Update local attributes.
	public void update(float temp, float humidity, float pressure) {
		// Increment number of readings and total temperature. 
		this.tempSum += temp;
		this.numReadings++;
		// Reset max/min temperature.
		if (temp > maxTemp) {
			this.maxTemp = temp;
		} 
		if (temp < minTemp) {
			this.minTemp = temp;
		}
		// Display min, max and average temperatures.
		display();
	}
	
	// DISPLAY ELEMENT INTERFACE

	@Override
	public void display() {
		System.out.println("Avg/Max/Min temperature = " + getAveTemp()
			+ "/" + getMaxTemp() + "/" + getMinTemp());
	}
	
	@Override
	public void reset(){
		this.maxTemp = InitProperties.INIT_MAX_TEMP;
		this.minTemp = InitProperties.INIT_MIN_TEMP;
		this.tempSum= InitProperties.INIT_TEMP_SUM;
		this.numReadings=0;
	}

	@Override
	public void close() {
		this.subject.removeObserver(this);	
	}
	
	
	// Getters for temperature values.
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
