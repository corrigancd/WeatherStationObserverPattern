package weather.presentation;

import weather.data.InitProperties;
import weather.data.Observer;
import weather.data.Subject;

public class ForecastDisplay implements Observer, DisplayElement {
	public static String INIT_FORECAST = "no change";
	private float currentPressure = InitProperties.INIT_PRESSURE;  
	private float lastPressure;
	private Subject subject;
	private String forecast  = INIT_FORECAST;

	public ForecastDisplay(Subject data) {
		this.subject = data;
		data.registerObserver(this);
	}

	public void update(float temp, float humidity, float pressure) {
        lastPressure = currentPressure;
		currentPressure = pressure;
		analyse();
		display();
	}

	private void analyse() {
		if (currentPressure > lastPressure) {
			forecast = "Sunshine";
		} else if (currentPressure == lastPressure) {
			forecast = "No Change";
		} else if (currentPressure < lastPressure) {
			forecast = "Rain";
		}
	}

	@Override
	// print a text version of the display's output
	public void display() {
		System.out.println("Analyse: ");
		System.out.println("Last pressure: " + lastPressure);
		System.out.println("Current pressure: " + currentPressure);
		System.out.println("Forecast: " + forecast);		
		
	}

	@Override
	public void setSubject(Subject data) {
		this.subject = data;
	}

	@Override
	//deregister self from subject
	public void close() {
		this.subject.removeObserver(this);
	}
	
	public float getCurrentPressure(){
		return this.currentPressure;
	}
	@Override
	//reset to default values
	public void reset(){
		this.currentPressure = InitProperties.INIT_PRESSURE;  
		this.lastPressure = 0.0f;
		this.forecast = INIT_FORECAST;
	}
	
	public String getForecast(){
		return this.forecast;
	}
}
