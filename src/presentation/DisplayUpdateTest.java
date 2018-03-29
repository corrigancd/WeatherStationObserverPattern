package presentation;

import static org.junit.Assert.*;

//import org.junit.Test;

import org.junit.Test;

import data.WeatherData_East;
import data.WeatherData_North;
import data.WeatherData_South;
import data.WeatherData_West;
import data.InitProperties;

public class DisplayUpdateTest {
	
	@Test
	public void testCurrentConditionsUpdate() {	
		/**
		 * 2 sources of Data
		 */
		WeatherData_East weatherEast = new WeatherData_East();
		WeatherData_West weatherWest = new WeatherData_West();
	
		/**
		 * CurrentConditionsDisplay display widgets observing 
		 * the WeatherData_East object
		 */
		
		// Create a new Display, assigning a data source. 
		CurrentConditionsDisplay currentDisplay = 
			new CurrentConditionsDisplay(weatherEast);
		
		// Set data values and pass to Data source ( that is observed )
		float temp = 80;
		float humidity =65;
		float pressure = 30.4f;
		weatherEast.dataIn(temp, humidity, pressure);		
		// Run tests.
		assertEquals(temp, currentDisplay.getTemp(), 0.001f);
		assertEquals(humidity, currentDisplay.getHumidity(), 0.001f);
		
		// Set new data and pass to Data source.
		temp = 24;
		humidity = 32;
		pressure = 20.2f;
		weatherEast.dataIn(temp, humidity, pressure);
		// Run tests
		assertEquals(temp, currentDisplay.getTemp(), 0.001f);
		assertEquals(humidity, currentDisplay.getHumidity(), 0.001f);
		
		
		// Change data source
		currentDisplay.setSubject(weatherWest);
		
		// Set new data
		temp = 65;
		humidity = 46;
		pressure =36.5f;
		weatherWest.dataIn(temp, humidity, pressure);
		
		// Run tests. 
		assertEquals(temp, currentDisplay.getTemp(), 0.001f);
		assertEquals(humidity, currentDisplay.getHumidity(), 0.001f);
		

	}

	@Test
	public void testStatsUpdate() {	
		/**
		 * 2 sources of Data
		 */
		WeatherData_North weatherNorth = new WeatherData_North();
		WeatherData_West weatherWest = new WeatherData_West();
	
		/**
		 * StatisticsDisplay display widget observing 
		 * the WeatherData_East object
		 */
		StatisticsDisplay statsDisplay = 
			new StatisticsDisplay(weatherNorth);
		
		//new data
		float temp = 80;
		float humidity =65;
		float pressure = 30.4f;
		
		//expected data
		int readings=1;
		float maxTemp = 80;
		float minTemp = 80;
		float aveTemp = maxTemp/readings;
		
		//set new data
		weatherNorth.dataIn(temp, humidity, pressure);
		assertEquals(aveTemp, statsDisplay.getAveTemp(), 0.001f);
		assertEquals(maxTemp, statsDisplay.getMaxTemp(), 0.001f);
		assertEquals(minTemp, statsDisplay.getMinTemp(), 0.001f);
		
		//change data
		temp = 24;
		humidity = 32;
		pressure = 20.2f;
		
		readings++;

		minTemp = 24;
		aveTemp = (maxTemp+temp)/readings;
		
		//set new data
		weatherNorth.dataIn(temp, humidity, pressure);
		assertEquals(aveTemp, statsDisplay.getAveTemp(), 0.001f);
		assertEquals(maxTemp, statsDisplay.getMaxTemp(), 0.001f);
		assertEquals(minTemp, statsDisplay.getMinTemp(), 0.001f);
		
		/**
		 * change data source to weatherWest
		 */
		statsDisplay.setSubject(weatherWest);
		
		//new data
		temp = 65;
		humidity = 46;
		pressure =36.5f;
		
		readings = 1;
		maxTemp = 65;
		minTemp=65;
		aveTemp = maxTemp/readings;
		
		//set new data
		weatherWest.dataIn(temp, humidity, pressure);
		assertEquals(aveTemp, statsDisplay.getAveTemp(), 0.001f);
		assertEquals(maxTemp, statsDisplay.getMaxTemp(), 0.001f);
		assertEquals(minTemp, statsDisplay.getMinTemp(), 0.001f);
	}
	
	@Test
	public void testForeCastUpdate() {	
		
		/**
		 * 2 sources of Data
		 */
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
		System.out.println("| - TEST 1 - |");
		weatherEast.dataIn(temp, humidity, pressure);
		assertEquals(expected, foreCastDisplay.getForecast());
		System.out.println(expected +"  "+ foreCastDisplay.getForecast());
		//new pressure data arrives
		pressure = 28.1f;
		expected = "rain"; // change expectation
		System.out.println("| - TEST 2 - |");
		weatherEast.dataIn(temp, humidity, pressure);
		assertEquals(expected, foreCastDisplay.getForecast());
		System.out.println(expected +"  "+ foreCastDisplay.getForecast());
		
		//Change source of data to weatherSest
		System.out.println("| - TEST 3 - |");
		foreCastDisplay.setSubject(weatherSouth);
		
		pressure = InitProperties.INIT_PRESSURE;	
		
		weatherSouth.dataIn(temp, humidity, pressure);
		
		System.out.println(weatherSouth.getTemperature()+"  "+
				   weatherSouth.getHumidity()+"  "+
				   weatherSouth.getPressure());
		foreCastDisplay.display();
		expected = "no change"; // change expectation
		System.out.println(expected +"  "+ foreCastDisplay.getForecast());
		assertEquals(expected, foreCastDisplay.getForecast());
		
		
	}
}