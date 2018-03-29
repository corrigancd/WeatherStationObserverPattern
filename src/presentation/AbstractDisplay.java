package presentation;

import data.Observer;
import data.Subject;

public abstract class AbstractDisplay implements Observer, DisplayElement{
	
	protected Subject subject;
	
	public AbstractDisplay() {
	}

}
