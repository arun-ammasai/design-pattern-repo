/**
 * Author :Arun Ammasai
 * JAVA/J2EE Programmer
 * 27-May-2018
 */
package com.guideme.pattern.opencloseprinciple;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Arun
 *
 */
public class OpenClosePrincipleExcercise {
	public static void main(String[] args) {
		Product1 apple = new Product1("Apple", Color1.BLUE, Size1.MEDIUM);
		Product1 samsung = new Product1("Samsung", Color1.BLUE, Size1.LARGE);
		Product1 nokia = new Product1("Nokia", Color1.GREEN, Size1.LARGE);
		List<Product1> products = new ArrayList<Product1>();
		products.add(apple);
		products.add(samsung);
		products.add(nokia);

		BetterFilter1 bf = new BetterFilter1();
		bf.filter(products, new ColorSpecification1(Color1.BLUE))
				.forEach(p -> System.out.println("This Product :" + p.name + " is Blue"));
		bf.filter(products, new SizeSpecification1(Size1.LARGE))
				.forEach(p -> System.out.println("This Product :" + p.name + " is Large"));

		bf.filter(products,
				new AndSpecification1<>(new ColorSpecification1(Color1.GREEN), new SizeSpecification1(Size1.LARGE)))
				.forEach(p -> System.out.println("This Product green :" + p.name + " and Large"));
	}
}

enum Color1 {
	RED, GREEN, BLUE;
}

enum Size1 {
	SMALL, MEDIUM, LARGE;
}

interface Specification1<T> {
	boolean isSatisfied(T item);
}

interface Filter1<T> {
	Stream<T> filter(List<T> items, Specification1<T> spec);
}

class AndSpecification1<T> implements Specification1<T> {
	Specification1<T> first, second;

	public AndSpecification1(Specification1<T> first, Specification1<T> second) {
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

class BetterFilter1 implements Filter1<Product1> {

	@Override
	public Stream<Product1> filter(List<Product1> items, Specification1<Product1> spec) {
		return items.stream().filter(p -> spec.isSatisfied(p));
	}

}

class ColorSpecification1 implements Specification1<Product1> {
	Color1 color;

	public ColorSpecification1(Color1 color) {
		this.color = color;
	}

	@Override
	public boolean isSatisfied(Product1 item) {
		return item.color == color;
	}
}

class SizeSpecification1 implements Specification1<Product1> {
	Size1 size;

	public SizeSpecification1(Size1 size) {
		super();
		this.size = size;
	}

	@Override
	public boolean isSatisfied(Product1 item) {
		// TODO Auto-generated method stub
		return item.size == size;
	}

}

class Product1 {
	public String name;
	public Color1 color;
	public Size1 size;

	public Product1(String name, Color1 color, Size1 size) {
		super();
		this.name = name;
		this.color = color;
		this.size = size;
	}

}