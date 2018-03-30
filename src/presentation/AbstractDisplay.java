package presentation;

import data.Observer;
import data.Subject;

public abstract class AbstractDisplay implements Observer, DisplayElement{
	
	protected Subject subject;
	// Constructor
	public AbstractDisplay(Subject data) {
		this.subject = data;
		data.registerObserver(this);
	}
	// | -- OBSERVER INTERFACE METHODS -- |
	
	// Register this observer with the data object.
	public void setSubject(Subject data) {
		this.subject.registerObserver(this);
		data.registerObserver(this);
		this.subject = data;
		this.reset();
	}
	// Update parameters and display.
	public void update(float temp, float humidity, float pressure) {};
	
	
	
	// | -- DISPLAY ELEMENT INTERFACE -- |
	
	// Print string to console.
	public void display() {}
	// Reset properties to initial values.
	public void reset() {}
	// De-register self from subject
	public void close() {
		this.subject.removeObserver(this);
	}
}



