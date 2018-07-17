package com.guideme.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class OpenClosePrinciple {
	public static void main(String[] args) {
		Product apple = new Product("Apple", Color.BLUE, Size.MEDIUM);
		Product samsung = new Product("Samsung", Color.BLUE, Size.LARGE);
		Product nokia = new Product("Nokia", Color.GREEN, Size.LARGE);
		List<Product> products = new ArrayList();
		products.add(apple);
		products.add(samsung);
		products.add(nokia);

		// products.forEach(p -> System.out.println("Produt Name "+p.name));

		ProductFilter pf = new ProductFilter();
		System.out.println("Old Filter");
		pf.filterByColor(products, Color.BLUE).forEach(p -> System.out.println(" - " + p.name + " is Blue"));
		// pf.filterBySize(products, Size.LARGE).forEach(p -> System.out.println(" - " +
		// p.name + " is Large"));

		System.out.println("New Filter");
		BetterFilter bf = new BetterFilter();
		bf.filter(products, new ColorSpecification(Color.BLUE)) 
				.forEach(p -> System.out.println(" - " + p.name + " is blue"));
		bf.filter(products, new SizeSpecification(Size.LARGE))
				.forEach(p -> System.out.println(" - " + p.name + " is Large"));
		
		System.out.println("After New Modified Filter");
		bf.filter(products, new AndSpecification<>(new ColorSpecification(Color.BLUE),new SizeSpecification(Size.LARGE))).forEach(p -> System.out.println(" - " + p.name + " is Large and Blue"));;
	}
}

enum Color {
	RED, GREEN, BLUE;
}

enum Size {
	SMALL, MEDIUM, LARGE;
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

}

interface Specification<T> {
	boolean isSatisfied(T item);
}

class AndSpecification<T> implements Specification<T> {
	private Specification<T> first, second;

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

interface Filter<T> {
	Stream<T> filter(List<T> items, Specification<T> spec);
}

class ColorSpecification implements Specification<Product> {
	private Color color;

	public ColorSpecification(Color color) {
		this.color = color;
	}

	@Override
	public boolean isSatisfied(Product item) {
		return item.color == color;
	}
}

class SizeSpecification implements Specification<Product> {
	private Size size;

	public SizeSpecification(Size size) {
		this.size = size;
	}

	@Override
	public boolean isSatisfied(Product item) {
		return item.size == size;
	}
}

class BetterFilter implements Filter<Product> {

	@Override
	public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
		return items.stream().filter(p -> spec.isSatisfied(p));
	}

}

class ProductFilter {
	public Stream<Product> filterByColor(List<Product> products, Color color) {
		return products.stream().filter(p -> p.color == color);
	}

	public Stream<Product> filterBySize(List<Product> products, Size size) {
		return products.stream().filter(p -> p.size == size);
	}

	public Stream<Product> filterBySizeandColor(List<Product> products, Size size, Color color) {
		return products.stream().filter(p -> p.size == size && p.color == color);
	}
}
