package weather.data;

import java.util.ArrayList;

public abstract class WeatherDataSuper implements Subject, WeatherData {
	
	private ArrayList<Observer> observers;
	private float temperature;
	private float humidity;
	private float pressure;
	
	public WeatherDataSuper() {
		this.observers = new ArrayList<Observer>();
	}
	

	public void registerObserver(Observer o) {
		this.observers.add(o);
	}
	

	public void removeObserver(Observer o) {
		this.observers.remove(o);
	}
	

	//call the observers update method
	public void notifyObservers() {
		for (Observer o : observers) {
			//iterate and call update method of observers
			o.update(temperature, humidity, pressure);
		}
	} 

	//record the new data and notifyObservers()
	public void dataIn(float temperature, float humidity, float pressure) {
		this.humidity = humidity;//TODO
		this.temperature = temperature;
		this.pressure = pressure;
		notifyObservers();
	}


	public float getTemperature() {
		return temperature;
	}
	

	public float getHumidity() {
		return humidity;
	}
	

	public float getPressure() {
		return pressure;
	}

}
