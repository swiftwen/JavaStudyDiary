package com.wp.study.designPattern.observerPattern;


public class ObserverA implements Observer {

	private Subject weatherData;
	
	public ObserverA(Subject weatherData) {
		this.weatherData = weatherData;
		this.weatherData.register(this);
	}

	@Override
	public void update(float temperature,float humidity,int pressure) {
		// TODO Auto-generated method stub
		System.out.println("observer A update");
	}
}
