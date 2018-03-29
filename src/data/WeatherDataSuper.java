package data;

import java.util.ArrayList;

public abstract class WeatherDataSuper implements Subject, WeatherData {

	private ArrayList<Observer> observers;
	private float temperature;
	private float humidity;
	private float pressure;
	
	
	// Constructor
	public WeatherDataSuper() {
		this.observers = new ArrayList<Observer>();
	}
	
	// Add observer to arraylist.
	public void registerObserver(Observer o) {
		this.observers.add(o);
	}
	// Remove Observer from arraylist.
	public void removeObserver(Observer o) {
		observers.remove(o);	
	}

	// Call the observers update method on observers.
	public void notifyObservers() {
		for(Observer o : observers) {
			// Update observer attributes.
			o.update(this.temperature,
					 this.humidity, 
					 this.pressure);
		}
	}
	
	// Set local attributes and notify all observers of change.
	public void dataIn(float temperature, float humidity, float pressure) {
		setTemperature(temperature);
		setHumidity(humidity);
		setPressure(pressure);
		notifyObservers();
	}
	
	


	
	public void setPressure(float p) {
		this.pressure = p;
	}

	public void setHumidity(float h) {
		this.humidity = h;
	}

	public void setTemperature(float t) {
		this.temperature = t;
	}
	
	public float getTemperature() {
		return this.temperature;
	}	

	public float getHumidity() {
		return this.humidity;
	}
	
	public float getPressure() {
		return this.pressure;
	}
}











