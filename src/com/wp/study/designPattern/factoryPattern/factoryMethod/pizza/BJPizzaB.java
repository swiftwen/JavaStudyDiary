package com.wp.study.designPattern.factoryPattern.factoryMethod.pizza;


public class BJPizzaB implements Pizza {

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
		System.out.println("BJPizzaB prepare()");
	}

	@Override
	public void bake() {
		// TODO Auto-generated method stub
		System.out.println("BJPizzaB bake()");
	}

	@Override
	public void cut() {
		// TODO Auto-generated method stub
		System.out.println("BJPizzaB cut()");
	}

	@Override
	public void box() {
		// TODO Auto-generated method stub
		System.out.println("BJPizzaB box()");
	}
}
