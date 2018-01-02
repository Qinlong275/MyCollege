package com.qinlong.shape;

import java.util.ArrayList;
import java.util.List;

public class TestShape {
	
	public static void drawShape(List<Shape> myShapeList) {
		for (Shape shape : myShapeList) {
			shape.draw();
		}
	}

	public static void main(String[] args) {
		Circle circle=new Circle(2.5);
		Triangle triangle=new Triangle();
		Rectangle rectangle=new Rectangle();
		List<Shape> myShapeList=new ArrayList<>();
		myShapeList.add(circle);
		myShapeList.add(triangle);
		myShapeList.add(rectangle);
		drawShape(myShapeList);
	}

}
