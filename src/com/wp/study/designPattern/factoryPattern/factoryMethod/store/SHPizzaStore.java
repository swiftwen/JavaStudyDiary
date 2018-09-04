package com.wp.study.designPattern.factoryPattern.factoryMethod.store;

import com.wp.study.designPattern.factoryPattern.factoryMethod.pizza.Pizza;
import com.wp.study.designPattern.factoryPattern.factoryMethod.pizza.SHPizzaA;
import com.wp.study.designPattern.factoryPattern.factoryMethod.pizza.SHPizzaB;

public class SHPizzaStore extends PizzaStore {

	@Override
	public Pizza createPizza(String type) {
		if("A".equals(type)){
			return new SHPizzaA();
		}else if("B".equals(type)){
			return new SHPizzaB();
		}
		return null;
	}
}
