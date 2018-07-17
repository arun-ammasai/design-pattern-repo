/**
 * Author :Arun Ammasai
 * JAVA/J2EE Programmer
 * 27-May-2018
 *//*
package com.guideme.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

*//**
 * @author Arun
 *
 *//*
public class PatternFilterExample {
	public static void main(String[] args) {
		Product apple = new Product("Apple", Color.BLUE, Size.MEDIUM);
		Product samsung = new Product("Samsung", Color.BLUE, Size.LARGE);
		Product nokia = new Product("Nokia", Color.GREEN, Size.LARGE);
		List<Product> products = new ArrayList<Product>();
		products.add(apple);
		products.add(samsung);
		products.add(nokia);

		BetterFilter bf = new BetterFilter();
		bf.filter(products, new ColorSpecification(Color.BLUE))
				.forEach(p -> System.out.println("This Product :" + p.name + " is Blue"));
		bf.filter(products, new SizeSpecification(Size.LARGE))
				.forEach(p -> System.out.println("This Product :" + p.name + " is Large"));

		bf.filter(products,
				new AndSpecification<>(new ColorSpecification(Color.GREEN), new SizeSpecification(Size.LARGE)))
				.forEach(p -> System.out.println("This Product green :" + p.name + " and Large"));
	}
}

enum Color {
	RED, GREEN, BLUE;
}

enum Size {
	SMALL, MEDIUM, LARGE;
}

interface Specification<T> {
	boolean isSatisfied(T item);
}

interface Filter<T> {
	Stream<T> filter(List<T> items, Specification<T> spec);
}

class AndSpecification<T> implements Specification<T> {
	Specification<T> first, second;

	public AndSpecification(Specification<T> first, Specification<T> second) {
		super();
		this.first = first;
		this.second = second;
	}

	@Override
	public boolean isSatisfied(T item) {
		// TODO Auto-generated method stub
		return first.isSatisfied(item) && second.isSatisfied(item);
	}

}

class BetterFilter implements Filter<Product> {

	@Override
	public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
		return items.stream().filter(p -> spec.isSatisfied(p));
	}

}

class ColorSpecification implements Specification<Product> {
	Color color;

	public ColorSpecification(Color color) {
		this.color = color;
	}

	@Override
	public boolean isSatisfied(Product item) {
		return item.color == color;
	}
}

class SizeSpecification implements Specification<Product> {
	Size size;

	public SizeSpecification(Size size) {
		super();
		this.size = size;
	}

	@Override
	public boolean isSatisfied(Product item) {
		// TODO Auto-generated method stub
		return item.size == size;
	}

}

class Product {
	public String name;
	public Color color;
	public Size size;

	public Product(String name, Color color, Size size) {
		super();
		this.name = name;
		this.color = color;
		this.size = size;
	}

}*/