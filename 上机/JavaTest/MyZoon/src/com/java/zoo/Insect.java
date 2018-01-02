package com.java.zoo;

public class Insect extends Animal{

	private String phototaxis;//������
	
	public Insect(String name, String type,String phototaxis) {
		super(name, type);
		this.phototaxis=phototaxis;
	}

	@Override
	public String getInformation() {
		return super.getInformation()+" �ҶԹ�������ǣ�"+phototaxis+"\n";
	}
}
