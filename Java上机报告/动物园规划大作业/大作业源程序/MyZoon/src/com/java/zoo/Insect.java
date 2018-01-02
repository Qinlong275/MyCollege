package com.java.zoo;

public class Insect extends Animal{

	private String phototaxis;//趋光性
	
	public Insect(String name, String type,String phototaxis) {
		super(name, type);
		this.phototaxis=phototaxis;
	}

	@Override
	public String getInformation() {
		return super.getInformation()+" 我对光的特性是："+phototaxis+"\n";
	}
}
