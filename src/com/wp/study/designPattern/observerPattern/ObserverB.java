package com.wp.study.designPattern.observerPattern;


public class ObserverB implements Observer {

	private Subject weatherData;
	public ObserverB(Subject weatherData) {
		this.weatherData = weatherData;
		this.weatherData.register(this);
	}
	@Override
	public void update(float temperature,float humidity,int pressure) {
		// TODO Auto-generated method stub
		System.out.println("observer B update");
	}
}
