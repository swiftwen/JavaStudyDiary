package com.wp.study.designPattern.factoryPattern.factoryMethod.pizza;


public class SHPizzaB implements Pizza {

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
		System.out.println("SHPizzaB prepare()");
	}

	@Override
	public void bake() {
		// TODO Auto-generated method stub
		System.out.println("SHPizzaA bake()");
	}

	@Override
	public void cut() {
		// TODO Auto-generated method stub
		System.out.println("SHPizzaA cut()");
	}

	@Override
	public void box() {
		// TODO Auto-generated method stub
		System.out.println("SHPizzaA box()");
	}
}
