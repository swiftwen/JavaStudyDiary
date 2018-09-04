package com.wp.study.designPattern.observerPattern;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {

	List<Observer> list = null;
	
	private float temperature;
	
	private int pressure;
	
	private float humidity;
	
	public WeatherData() {
		list = new ArrayList<>();
	}

	@Override
	public void register(Observer observer) {
		// TODO Auto-generated method stub
		list.add(observer);
		
	}

	@Override
	public void remove(Observer observer) {
		// TODO Auto-generated method stub
		list.remove(observer);
	}

	@Override
	public void notifyAllObservers() {
		// TODO Auto-generated method stub
		for(int i=0;i<list.size();++i){
			list.get(i).update(temperature,humidity,pressure);
		}
	}
	
	public void change(float temperature,float humidity,int pressure){
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		notifyAllObservers();
	}
	
}
