package com.java.zoo;

//���鶯��
public class Mammalia extends Animal{

	private String home;
	public Mammalia(String name, String type,String home) {
		super(name, type);
		this.home=home;
	}
	
	@Override
	public String getInformation() {
		return super.getInformation()+" �ҵ����滷���ڣ�"+home+"\n";
	}
}
