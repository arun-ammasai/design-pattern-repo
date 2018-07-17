package com.guideme.pattern;

/*
 Use Case : Calculate addition and multiplication
*/
public class SimpleFactoryPatternExample {

	public static void main(String[] args) {
		Calculator cal = Calculator.CalculatorFactory.calculateTheta(1000, 5);
		System.out.println(cal);
	}
}

class Calculator {
	private double a, b;

	private Calculator(double a, double b) {
		this.a = a;
		this.b = b;
	}

	public static class CalculatorFactory {
		public static Calculator addNumbers(double a, double b) {
			return new Calculator(a, b);
		}

		public static Calculator calculateTheta(double a, double b) {
			return new Calculator(a * Math.cos(b), a * Math.sin(b));
		}
	}

	@Override
	public String toString() {
		return "Calculator {a=" + a + ", b=" + b + "}";
	}
}