package com.java.zoo;

public class Fish extends Animal{
	
	private float length;
	
	public Fish(String name, String type,float length) {
		super(name, type);
		this.length=length;
	}


	@Override
	public String getInformation() {
		return super.getInformation()+" �ҵ��峤Ϊ��"+length+"����\n";
	}
}
