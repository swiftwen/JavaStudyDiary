package com.wp.study.designPattern.observerPattern;


public class ObserverC implements Observer {

	private Subject weatherData;
	public ObserverC(Subject weatherData) {
		this.weatherData = weatherData;
		this.weatherData.register(this);
	}
	@Override
	public void update(float temperature,float humidity,int pressure) {
		// TODO Auto-generated method stub
		System.out.println("observer C update");
	}
}
