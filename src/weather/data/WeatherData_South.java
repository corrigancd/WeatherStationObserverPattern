package weather.data;

import java.util.ArrayList;

public class WeatherData_South implements Subject, WeatherData {
	private ArrayList<Observer> observers;
	private float temperature;
	private float humidity;
	private float pressure;
	
	public WeatherData_South() {
		this.observers = new ArrayList<Observer>();
	}
	
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
		System.out.println(observers.size());
	}
	
	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	
	@Override
	public void notifyObservers() { //call the observers update method
		for (Observer o : observers) {
			//iterate and call update method of observers
			o.update(temperature, humidity, pressure);
		}
	}
	
	@Override
	//record the new data and notifyObservers()
	public void dataIn(float temperature, float humidity, float pressure) {
		this.humidity = humidity;//TODO
		this.temperature = temperature;
		this.pressure = pressure;
		notifyObservers();
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

