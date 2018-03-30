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
		System.out.println("\n| ---------------------------------------- |");
		  System.out.println("| -- Testing:  CurrentConditions Update -- |");
		System.out.println("| ---------------------------------------- |\n");
		/**
		 * 2 sources of Data
		 */
		WeatherData_East weatherEast = new WeatherData_East();
		WeatherData_West weatherWest = new WeatherData_West();	
		// Create a new Display, assigning a data source. 
		CurrentConditionsDisplay currentDisplay = 
			new CurrentConditionsDisplay(weatherEast);
		System.out.println("\n| -- Test 1 -- |\n");
		// Set data values and pass to Data source ( that is observed )
		float temp = 80, 
			  humidity =65, 
			  pressure = 30.4f;
		weatherEast.dataIn(temp, humidity, pressure);		
		// Run tests.
		assertEquals(temp, currentDisplay.getTemp(), 0.001f);
		assertEquals(humidity, currentDisplay.getHumidity(), 0.001f);
		System.out.println("\n| -- Test 2 -- |\n");
		// Set new data and pass to Data source.
		temp = 24;
		humidity = 32;
		pressure = 20.2f;
		weatherEast.dataIn(temp, humidity, pressure);
		// Run tests
		assertEquals(temp, currentDisplay.getTemp(), 0.001f);
		assertEquals(humidity, currentDisplay.getHumidity(), 0.001f);
		
		System.out.println("\n| -- Test 3 -- |\n");
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
		System.out.println("\n| --------------------------------- |");
		System.out.println("| -- Testing:  Statistics Update -- |");
		System.out.println("| --------------------------------- |\n");
		// Data sources.
		WeatherData_North weatherNorth = new WeatherData_North();
		WeatherData_West weatherWest = new WeatherData_West();
		// Test object.
		StatisticsDisplay statsDisplay =new StatisticsDisplay(weatherNorth);
		System.out.println("\n| -- Test 1 -- |\n");
		// New data
		float temp = 80,humidity =65, pressure = 30.4f;
		// Expected data
		int readings=1;
		float maxTemp = 80, minTemp = 80, aveTemp = maxTemp/readings;
		weatherNorth.dataIn(temp, humidity, pressure);
		// Run Tests
		assertEquals(aveTemp, statsDisplay.getAveTemp(), 0.001f);
		assertEquals(maxTemp, statsDisplay.getMaxTemp(), 0.001f);
		assertEquals(minTemp, statsDisplay.getMinTemp(), 0.001f);
		System.out.println("\n| -- Test 2 -- |\n");
		// Change data.
		temp = 24;
		humidity = 32;
		pressure = 20.2f;
		readings++;
		minTemp = 24;
		aveTemp = (maxTemp+temp)/readings;	
		weatherNorth.dataIn(temp, humidity, pressure);
		// Run Tests
		assertEquals(aveTemp, statsDisplay.getAveTemp(), 0.001f);
		assertEquals(maxTemp, statsDisplay.getMaxTemp(), 0.001f);
		assertEquals(minTemp, statsDisplay.getMinTemp(), 0.001f);
		System.out.println("\n| -- Test 3 -- |\n");
		// Change data source to weatherWest
		statsDisplay.setSubject(weatherWest);
		// New data
		temp = 65;
		humidity = 46;
		pressure =36.5f;
		readings = 1;
		maxTemp = 65;
		minTemp=65;
		aveTemp = maxTemp/readings;	
		weatherWest.dataIn(temp, humidity, pressure);
		// Run Tests.
		assertEquals(aveTemp, statsDisplay.getAveTemp(), 0.001f);
		assertEquals(maxTemp, statsDisplay.getMaxTemp(), 0.001f);
		assertEquals(minTemp, statsDisplay.getMinTemp(), 0.001f);
	}
	
	@Test
	public void testForeCastUpdate() {		
		System.out.println("\n| ------------------------------- |");
		System.out.println("| -- Testing:  Forecast Update -- |");
		System.out.println("| ------------------------------- |\n");
		// Data Objects
		WeatherData_East weatherEast = new WeatherData_East();
		WeatherData_South weatherSouth = new WeatherData_South();
		// Test Object.
		ForecastDisplay foreCastDisplay = new ForecastDisplay(weatherEast);
		System.out.println("\n| - TEST 1 - |\n");
		// New test data
		float temp = 80, humidity =65, pressure = 30.4f;
		String expected = "sunshine";
		weatherEast.dataIn(temp, humidity, pressure);
		// Run Test.
		assertEquals(expected, foreCastDisplay.getForecast());
		System.out.println(expected +"  "+ foreCastDisplay.getForecast());
		System.out.println("\n| - TEST 2 - |\n");
		//new pressure data arrives
		pressure = 28.1f;
		expected = "rain"; // change expectation
		weatherEast.dataIn(temp, humidity, pressure);
		// Run Test.
		assertEquals(expected, foreCastDisplay.getForecast());
		System.out.println(expected +"  "+ foreCastDisplay.getForecast());
		System.out.println("\n| - TEST 3 - |\n");
		//Change source of data to weatherSest
		foreCastDisplay.setSubject(weatherSouth);
		// Set to initial value.
		pressure = InitProperties.INIT_PRESSURE;	
		expected = "no change"; // change expectation
		weatherSouth.dataIn(temp, humidity, pressure);
		System.out.println(weatherSouth.getTemperature()+"  "+
				   weatherSouth.getHumidity()+"  "+
				   weatherSouth.getPressure());
		foreCastDisplay.display();
		// Run Test.
		System.out.println(expected +"  "+ foreCastDisplay.getForecast());
		assertEquals(expected, foreCastDisplay.getForecast());
	}
}


