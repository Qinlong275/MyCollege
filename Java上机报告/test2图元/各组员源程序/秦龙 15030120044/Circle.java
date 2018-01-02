package com.qinlong.shape;

public class Circle extends Shape{
	
	private double radius;
	
	public Circle(double radius) {
		super();
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	@Override
	public void draw() {
		System.out.println("’‚ «‘≤–Œ");
	}
}
