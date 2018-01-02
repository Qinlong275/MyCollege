package com.java.zoo;

//哺乳动物
public class Mammalia extends Animal{

	private String home;
	public Mammalia(String name, String type,String home) {
		super(name, type);
		this.home=home;
	}
	
	@Override
	public String getInformation() {
		return super.getInformation()+" 我的生存环境在："+home+"\n";
	}
}
