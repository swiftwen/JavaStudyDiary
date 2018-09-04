package com.wp.study.designPattern.observerPattern;


public class ObserverPatternTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WeatherData weatherData = new WeatherData();
	    Observer o1 = new ObserverA(weatherData);
	    Observer o2 = new ObserverB(weatherData);
	    Observer o3 = new ObserverC(weatherData);
	    weatherData.change(1.0f, 1.0f, 1);
	    weatherData.remove(o1);
	    System.out.println("----------");
	    weatherData.change(2.0f, 2.0f, 2);
	}
}
