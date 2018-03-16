package weather.presentation;

import weather.data.InitProperties;
import weather.data.WeatherData_East;
import weather.data.WeatherData_South;

public class tester {
	
	public static void main(String[] args) {
		WeatherData_East weatherEast = new WeatherData_East();
		WeatherData_South weatherSouth = new WeatherData_South();
		
		/**
		 * ForecastDisplay display widgets observing 
		 * the WeatherData_East object
		 */
		
		ForecastDisplay foreCastDisplay = new ForecastDisplay(weatherEast);
		
		//new data
		float temp = 80;
		float humidity =65;
		float pressure = 30.4f;
		
		String expected = "sunshine";
		
		weatherEast.dataIn(temp, humidity, pressure);
				
		//new pressure data arrives
		pressure = 28.1f;
		expected = "rain"; // change expectation
		
		weatherEast.dataIn(temp, humidity, pressure);
		
		//Change source of data to weatherWest
		foreCastDisplay.setSubject(weatherSouth);
		
		pressure = InitProperties.INIT_PRESSURE;
		
		weatherSouth.dataIn(temp, humidity, pressure);
		
		expected = "Rain";
	}
}