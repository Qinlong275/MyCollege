package com.java.zoo;

import java.util.ArrayList;
import java.util.List;

//不同的动物园区
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
		message.append(name+"园区的动物如下: \n");
		for (Animal animal : animals) {
			message.append(animal.getInformation());
		}
		return message.toString();
	}
	
	public int getAnimalNum() {
		return animals.size();
	}
}
