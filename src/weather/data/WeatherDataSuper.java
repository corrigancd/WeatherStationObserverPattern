package weather.data;

public abstract class WeatherDataSuper implements Subject, WeatherData {
	
	private ArrayList<Observer> observers;
	private float temperature;
	private float humidity;
	private float pressure;
	
	public WeatherData() {
		this.observers = new ArrayList<Observer>();
	}

}
