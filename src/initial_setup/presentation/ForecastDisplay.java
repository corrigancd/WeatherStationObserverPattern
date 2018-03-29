package initial_setup.presentation;

import initial_setup.data.InitProperties;
import initial_setup.data.Observer;
import initial_setup.data.Subject;

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
	// Update paramaters and display.
	public void update(float temp, float humidity, float pressure) {
        lastPressure = currentPressure;
		currentPressure = pressure;
		analyse();
		display();
	}
	// Analyse data, update and return forecast string.
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

	@Override
	// print a text version of the display's output
	public void display() {
		System.out.println( subject.toString()+"\n"+
						   "Current Pressure: "+currentPressure+"\n"+
						   "Last    Pressure: "+lastPressure+"\n"+
						   "Forecast        : "+forecast);
	}

	@Override
	public void setSubject(Subject data) {
		this.subject.registerObserver(this);
		data.registerObserver(this);
		this.subject = data;
		this.reset();
	}

	@Override
	//deregister self from subject
	public void close() {
		this.subject.removeObserver(this);
	}
	
	public float getCurrentPressure(){
		return this.currentPressure;
	}
	public float getLastPressure(){
		return this.lastPressure;
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
