package com.java.zoo;

public class TestZoo {

	public static void main(String[] args) {
		MyZoo myZoo=new MyZoo();
		System.out.println(myZoo.showMyZoo());
		System.out.println(myZoo.fishArea.showAllAnimals());
		myZoo.addAnimal(new Bird("����", "bird", "��ɫ"));
		System.out.println(myZoo.showMyZoo());
		System.out.println(myZoo.birdArea.showAllAnimals());
	}

}
