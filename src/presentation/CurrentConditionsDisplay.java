package presentation;

import data.InitProperties;
import data.Subject;
	
public class CurrentConditionsDisplay extends AbstractDisplay {
	// Attributes
	private float temperature;
	private float humidity;
	
	// Constructor.
	public CurrentConditionsDisplay(Subject data) {
		this.subject = data;
		data.registerObserver(this);
	}
	
	
	// OBSERVER INTERFACE METHODS:
	
	// Sets this as subject.
	@Override
	public void setSubject(Subject data) {
		this.subject.removeObserver(this);
		data.registerObserver(this);
		this.subject = data;
		this.reset();
	}
	
	// Updates local attributes and displays
	public void update(float temperature,
			float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		display();
	}
	 
	// DISPLAY ELEMET INTERFACE METHODS. 

	// Print to console.
	public void display() {
		System.out.println("Current conditions: " + getTemp()
			+ "F degrees and " + getHumidity() + "% humidity");
	}
	@Override
	public void reset(){
		this.temperature = InitProperties.INIT_MAX_TEMP;
		this.humidity = InitProperties.INIT_HUMIDITY;
	}
	@Override
	public void close() {
		this.subject.removeObserver(this);
	}

	// Return string for GUI display.
	public String returnCurrentCondidtions(){
		
		String str = this.getHumidity()+"\n"+
					 this.getTemp()+"/n";
		return str;
	}
	
	// Getters
	public float getTemp(){
		return this.temperature;
	}
	public float getHumidity(){
		return this.humidity;		
	}
}


