package com.java.zoo;

public class Bird extends Animal{
	
	private String wingColor;

	public Bird(String name, String type,String color) {
		super(name, type);
		wingColor=color;
	}

	@Override
	public String getInformation() {
		return super.getInformation()+" �ҵ���ë��ɫΪ "+wingColor+"\n";
	}
}
