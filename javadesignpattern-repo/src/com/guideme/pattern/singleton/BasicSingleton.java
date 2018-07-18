package com.guideme.pattern.singleton;

public class BasicSingleton {

	public static void main(String[] args) {
		BasicSingletonClass singleton = BasicSingletonClass.getInstance();
		singleton.setValue(10);
		System.out.println(singleton.getValue());
	}
}

class BasicSingletonClass {
	private BasicSingletonClass() {

	}

	private static final BasicSingletonClass instance = new BasicSingletonClass();

	public static BasicSingletonClass getInstance() {
		return instance;
	}

	private int value = 0;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
