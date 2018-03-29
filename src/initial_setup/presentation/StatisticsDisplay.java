package initial_setup.presentation;

import initial_setup.data.InitProperties;
import initial_setup.data.Observer;
import initial_setup.data.Subject;



public class StatisticsDisplay implements DisplayElement, Observer {
	
	
	private float maxTemp = InitProperties.INIT_MAX_TEMP;
	private float minTemp = InitProperties.INIT_MIN_TEMP;
	private float tempSum= InitProperties.INIT_TEMP_SUM;
	private int numReadings;
	private Subject subject;

	public StatisticsDisplay(Subject data) {
		this.subject = data;
		//
		data.registerObserver(this);
	}
	// 
	public void update(float temp, float humidity, float pressure) {
		// Increment number of readings and 
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

	public void display() {
		System.out.println("Avg/Max/Min temperature = " + getAveTemp()
			+ "/" + getMaxTemp() + "/" + getMinTemp());
	}

	@Override
	public void setSubject(Subject data) {
		this.subject.removeObserver(this);
		data.registerObserver(this);
		this.subject = data;
		this.reset();
	}

	@Override
	public void close() {
		this.subject.removeObserver(this);
		
	}
	
	public float getAveTemp(){
		return this.tempSum/numReadings;
	}
	
	public float getMaxTemp(){
		return this.maxTemp;
	}
	
	public float getMinTemp(){
		return this.minTemp;
	}
	
	@Override
	public void reset(){
		this.maxTemp = InitProperties.INIT_MAX_TEMP;
		this.minTemp = InitProperties.INIT_MIN_TEMP;
		this.tempSum= InitProperties.INIT_TEMP_SUM;
		this.numReadings=0;
	}
}
