package com.java.zoo;

import java.util.ArrayList;
import java.util.List;

//��ͬ�Ķ���԰��
public class ZooArea {
	
	private List<Animal> animals;
	private String name;
	
	public ZooArea(String name) {
		super();
		this.name=name;
		animals=new ArrayList<>(); 
	}

	public String getName() {
		return name;
	}
	
	public void addAnimal(Animal animal) {
		animals.add(animal);
	}
	
	public String showAllAnimals() {
		StringBuilder message=new StringBuilder();
		message.append(name+"԰���Ķ�������: \n");
		for (Animal animal : animals) {
			message.append(animal.getInformation());
		}
		return message.toString();
	}
	
	public int getAnimalNum() {
		return animals.size();
	}
}
