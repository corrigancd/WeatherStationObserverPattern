package presentation;

import data.InitProperties;
import data.WeatherData_East;
import data.WeatherData_South;

public class GUIDisplay {
		
	public static void main(String[] args) {
		// Data objects
		WeatherData_East weatherEast = new WeatherData_East();
		WeatherData_South weatherSouth = new WeatherData_South();	
		// Observer objects
		ForecastDisplay foreCastDisplay = new ForecastDisplay(weatherEast);
		CurrentConditionsDisplay ccd = new CurrentConditionsDisplay(weatherEast);
	
		// Set data.
		float temp = 80, humidity =65, pressure = 30.4f;
		weatherEast.dataIn(temp, humidity, pressure);
				
		// New pressure data arrives
		pressure = 28.1f;
		weatherEast.dataIn(temp, humidity, pressure);
		
		// Change source of data to weatherWest
		foreCastDisplay.setSubject(weatherSouth);		
		pressure = InitProperties.INIT_PRESSURE;		
		weatherSouth.dataIn(temp, humidity, pressure);
			
		// Create Graphic and set string to display.
		// (full path is \OO_assignment\OO_Assignment5\bin\weather\presentation\image.png
		// obtained from: System.out.println(tester.class	
		// .getProtectionDomain().getCodeSource().getLocation().getPath()); 
		ShowImage imgShow = new ShowImage(); 		
		String fcst = foreCastDisplay.getForecast();
		
		// Get Current Condition data.
		double cpres = foreCastDisplay.getCurrentPressure();
		double lpres = foreCastDisplay.getLastPressure();
		double ctemp = ccd.getTemp();
		double chumd = ccd.getHumidity(); 
		
		if (foreCastDisplay.getForecast() == "rain") {	
			imgShow.ShowImageOnScreen("rain.png", fcst, cpres, lpres, ctemp, chumd);
		} else if (foreCastDisplay.getForecast() == "runshine") {
			imgShow.ShowImageOnScreen("sunny.png", fcst, cpres, lpres, ctemp, chumd);	
		} else if (foreCastDisplay.getForecast() == "no change") {
			imgShow.ShowImageOnScreen("rain.png", fcst, cpres, lpres, ctemp, chumd);
		} else {
			System.out.println("not a valid change");
		}
	}  
}
		

	










