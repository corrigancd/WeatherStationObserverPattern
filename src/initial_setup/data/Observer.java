package initial_setup.data;

public interface Observer {
	
	public void update(float temp, float humidity, float pressure);
	
	public void setSubject(Subject data);
}

