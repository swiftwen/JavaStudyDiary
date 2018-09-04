package com.wp.study.designPattern.factoryPattern.factoryMethod.store;

import com.wp.study.designPattern.factoryPattern.factoryMethod.pizza.Pizza;

public abstract class PizzaStore {
	
	public void orderPizza(String type){
		Pizza pizza = createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
	}
	public abstract Pizza createPizza(String type);
}
