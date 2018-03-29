package initial_setup.data;

import java.util.*;

import initial_setup.data.Observer;

public class WeatherData_East implements Subject, WeatherData {
	private ArrayList<Observer> observers;
	private float temperature;
	private float humidity;
	private float pressure;
	
	public WeatherData_East() {
		this.observers = new ArrayList<Observer>();
	}
	
	public void registerObserver(Observer o) {
		this.observers.add(o);
	}
	
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	
	@Override
	//call the observers update method
	public void notifyObservers() {
		for(Observer o : observers) {
			o.update(this.temperature,
					 this.humidity, 
					 this.pressure);
		}
	}
	
	@Override
	//record the new data and notifyObservers()
	public void dataIn(float temperature, float humidity, float pressure) {
		setTemperature(temperature);
		setHumidity(humidity);
		setPressure(pressure);
		notifyObservers();
	}

	private void setPressure(float pres) {
		this.pressure = pres;
		
	}

	private void setHumidity(float hum) {
		this.humidity = hum;
		
	}

	private void setTemperature(float temp) {
		this.temperature = temp;
		
	}

	@Override
	public float getTemperature() {
		return temperature;
	}
	
	@Override
	public float getHumidity() {
		return humidity;
	}
	
	@Override
	public float getPressure() {
		return pressure;
	}

}
