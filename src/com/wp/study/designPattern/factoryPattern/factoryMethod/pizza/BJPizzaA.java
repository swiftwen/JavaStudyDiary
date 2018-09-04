package com.wp.study.designPattern.factoryPattern.factoryMethod.pizza;


public class BJPizzaA implements Pizza {

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
		System.out.println("BJPizzaA prepare()");
	}

	@Override
	public void bake() {
		// TODO Auto-generated method stub
		System.out.println("BJPizzaA bake()");
	}

	@Override
	public void cut() {
		// TODO Auto-generated method stub
		System.out.println("BJPizzaA cut()");
	}

	@Override
	public void box() {
		// TODO Auto-generated method stub
		System.out.println("BJPizzaA box()");
	}
}
