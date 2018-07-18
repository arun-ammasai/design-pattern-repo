package com.guideme.pattern.prototype;

public class PrototypeCodingExcercise {

	public static void main(String[] args) {
		Line line1 = new Line(new Point(3, 3), new Point(10, 10));

		Line line2 = line1.deepCopy();
		line2.end.x=145;
		System.out.println(line1);
		System.out.println(line2);
	}
}

class Point {
	public int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

}

class Line {
	public Point start, end;

	public Line(Point start, Point end) {
		super();
		this.start = start;
		this.end = end;
	}

	public Line deepCopy() {
		Point s = new Point(start.x, start.y);
		Point e = new Point(end.x, end.y);
		return new Line(s, e);
	}

	@Override
	public String toString() {
		return "Line [start=" + start + ", end=" + end + "]";
	}
	
}
