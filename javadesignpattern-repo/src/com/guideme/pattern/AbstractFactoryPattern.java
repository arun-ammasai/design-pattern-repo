package com.guideme.pattern;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.util.Pair;
import org.reflections.Reflections;

public class AbstractFactoryPattern {

	public static void main(String[] args) throws Exception {
		HotDrinkMachine machine = new HotDrinkMachine();
		HotDrink hotDrink = machine.makeDrink();
		hotDrink.consume();
	}
}

interface HotDrink {
	void consume();
}

class Tea implements HotDrink {

	@Override
	public void consume() {
		System.out.println("This is delicious cup of Tea");
	}
}

class Coffee implements HotDrink {

	@Override
	public void consume() {
		System.out.println("This is delicious cup of Coffee");
	}

}

interface HotDrinkFactory {
	HotDrink prepare(int amount);
}

class TeaFactory implements HotDrinkFactory {

	@Override
	public HotDrink prepare(int amount) {
		System.out.println("From HotDrinkFactory prepare Tea Factory");
		return new Tea();
	}
}

class CoffeeFactory implements HotDrinkFactory {

	@Override
	public HotDrink prepare(int amount) {
		System.out.println("From HotDrinkFactory prepare Coffee Factory");
		return new Coffee();
	}
}

class HotDrinkMachine {
	private List<Pair<String, HotDrinkFactory>> factories = new ArrayList<>();

	public HotDrinkMachine() throws Exception {
		Set<Class<? extends HotDrinkFactory>> types = new Reflections("").getSubTypesOf(HotDrinkFactory.class);
		for (Class<? extends HotDrinkFactory> type : types) {
			HotDrinkFactory d = type.getDeclaredConstructor().newInstance();
			factories.add(new Pair<>(type.getSimpleName().replaceAll("Factor", ""), type.getDeclaredConstructor().newInstance()));
		}
	}

	public HotDrink makeDrink() throws Exception {
		System.out.println("Available Drinks :"+factories.size());
		for (int i = 0; i < factories.size(); i++) {
			Pair<String, HotDrinkFactory> item = factories.get(i);
			System.out.println("" + i + ": " + item.getKey());
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String s;
			int i, amount;
			if ((s = reader.readLine()) != null && (i = Integer.parseInt(s)) >= 0 && i < factories.size()) {
				System.out.println("Specify amount: ");
				s = reader.readLine();
				if (s != null && (amount = Integer.parseInt(s)) > 0) {
					return factories.get(i).getValue().prepare(amount);
				}
			}
			System.out.println("Incorrect input, try again.");
		}
	}

}