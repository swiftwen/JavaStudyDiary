package com.wp.study.designPattern.factoryPattern.factoryMethod;

import com.wp.study.designPattern.factoryPattern.factoryMethod.store.PizzaStore;
import com.wp.study.designPattern.factoryPattern.factoryMethod.store.SHPizzaStore;
/**
 * @desc 工厂方法模式测试
 * @author swiftwen
 * @date 2018年9月4日 下午4:13:02
 */
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PizzaStore store = new SHPizzaStore();
		store.orderPizza("A");
	}
}
