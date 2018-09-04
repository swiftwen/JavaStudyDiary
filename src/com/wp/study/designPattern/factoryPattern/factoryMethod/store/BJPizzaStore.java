package com.wp.study.designPattern.factoryPattern.factoryMethod.store;

import com.wp.study.designPattern.factoryPattern.factoryMethod.pizza.BJPizzaA;
import com.wp.study.designPattern.factoryPattern.factoryMethod.pizza.BJPizzaB;
import com.wp.study.designPattern.factoryPattern.factoryMethod.pizza.Pizza;

public class BJPizzaStore extends PizzaStore {

	@Override
	public Pizza createPizza(String type) {
		if("A".equals(type)){
			return new BJPizzaA();
		}else if("B".equals(type)){
			return new BJPizzaB();
		}
		return null;
	}
}
