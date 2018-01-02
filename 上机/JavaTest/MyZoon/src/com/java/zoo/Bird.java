package com.java.zoo;

public class Bird extends Animal{
	
	private String wingColor;

	public Bird(String name, String type,String color) {
		super(name, type);
		wingColor=color;
	}

	@Override
	public String getInformation() {
		return super.getInformation()+" 我的羽毛颜色为 "+wingColor+"\n";
	}
}
