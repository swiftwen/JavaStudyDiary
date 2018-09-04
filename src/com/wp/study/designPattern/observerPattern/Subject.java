package com.wp.study.designPattern.observerPattern;


public interface Subject {
	
	public void register(Observer observer);
	
	public void remove(Observer observer);
	
	public void notifyAllObservers();
	
	
}
