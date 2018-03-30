package presentation;

import data.InitProperties;
import data.Subject;

public class ForecastDisplay extends AbstractDisplay {
	public static String INIT_FORECAST = "no change";
	private String forecast  = INIT_FORECAST;
	private float currentPressure = InitProperties.INIT_PRESSURE;  
	private float lastPressure;	
	// Constructor.
	public ForecastDisplay(Subject data) {
		super(data);
	}
	// Analyse data, update forecast string.
	private void analyse() {
		System.out.println("Analyse("+currentPressure +", "+lastPressure+")");
		if (currentPressure > lastPressure) {
			forecast = "sunshine";
		} else if (currentPressure == lastPressure) {
			forecast = "no change";
		} else if (currentPressure < lastPressure) {
			forecast = "rain";
		}		
	}
	
	// | -- OBSERVER INTERFACE METHODS -- |
	// Update parameters and display.
	public void update(float temp, float humidity, float pressure) {
        lastPressure = currentPressure;
		currentPressure = pressure;
		analyse();
		display();
	}
	
	// | -- DISPLAY ELEMENT INTERFACE -- |
	// Print string to console.
	public void display() {
		System.out.println( subject.toString()+"\n"+
						   "Current Pressure: "+currentPressure+"\n"+
						   "Last    Pressure: "+lastPressure+"\n"+
						   "Forecast        : "+forecast);
	}
	// Reset to default values
	public void reset(){
		this.currentPressure = InitProperties.INIT_PRESSURE;  
		this.lastPressure = 0.0f;
		this.forecast = INIT_FORECAST;
	}
	

	
	
	// Getters.
	public String getForecast(){
		return this.forecast;
	}
	public float getCurrentPressure(){
		return this.currentPressure;
	}
	public float getLastPressure(){
		return this.lastPressure;
	}
}
