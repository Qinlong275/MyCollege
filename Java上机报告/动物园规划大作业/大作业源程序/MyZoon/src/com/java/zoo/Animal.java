package com.java.zoo;

public class Animal {
	private String name;
	private String type;
	
	public Animal(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}

	public String getInformation(){
		return "我属于："+getType()+"类 我的名字叫："+getName();
	};
}
