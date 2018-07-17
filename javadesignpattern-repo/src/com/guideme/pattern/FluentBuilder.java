package com.guideme.pattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FluentBuilder {

	public static void main(String[] args) {
		HtmlBuilder hb = new HtmlBuilder("ul");
		hb.addChild("li", "Hello");
		hb.addChild("li", "World");
		System.out.println(hb);
	}
}

class HtmlElement {
	public String name, text;
	public List<HtmlElement> elements = new ArrayList<HtmlElement>();
	private final int indentSize = 2;
	private final String newLine = System.lineSeparator();

	public HtmlElement() {
	}

	public HtmlElement(String name, String text) {
		this.name = name;
		this.text = text;
	}

	private String toStringImpl(int size) {
		StringBuilder sb = new StringBuilder();
		String i = String.join("", Collections.nCopies(indentSize * size, " "));
		sb.append(String.format("%s<%s>%s", i, name, newLine));
		if (text != null && !text.isEmpty()) {
			System.out.println("nCopies indent:" + "Size :" + size);
			int modified = size + 1;
			System.out.println("Modified :" + modified);
			sb.append(String.join("", Collections.nCopies(indentSize * modified, " "))).append(text).append(newLine);
		}

		for (HtmlElement e : elements) {
			sb.append(e.toStringImpl(size + 1));
		}
		sb.append(String.format("%s</%s>%s", i, name, newLine));
		return sb.toString();
	}

	@Override
	public String toString() {
		return toStringImpl(0);
	}
}

class HtmlBuilder {
	private String rootName;
	private HtmlElement root = new HtmlElement();

	public HtmlBuilder(String rootName) {
		this.rootName = rootName;
		root.name = rootName;
	}

	public void addChild(String childName, String childText) {
		HtmlElement element = new HtmlElement(childName, childText);
		root.elements.add(element);
	}

	public void clear() {
		root = new HtmlElement();
		root.name = rootName;
	}

	public String toString() {
		return root.toString();
	}

}