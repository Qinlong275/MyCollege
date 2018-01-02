package com.java.zoo;

public class MyZoo {
	
	public static ZooArea birdArea;
	public static ZooArea fishArea;
	public static ZooArea mammaliaArea;
	public static ZooArea insectArea;
	
	public MyZoo() {
		birdArea=new ZooArea("��֮��");
		fishArea=new ZooArea("��֮��");
		mammaliaArea=new ZooArea("���鶯��֮��");
		insectArea=new ZooArea("����֮��");
		initialZoo();
	}
	
	//������һֻ�������
	public void addAnimal(Animal animal) {
		switch (animal.getType()) {
		case "bird":
			birdArea.addAnimal(animal);
			break;
		case "fish":
			fishArea.addAnimal(animal);
			break;
		case "mammalia":
			mammaliaArea.addAnimal(animal);
			break;
		case "insect":
			insectArea.addAnimal(animal);
			break;
		default:
			break;
		}
	}
	
	//չʾ��ǰ����԰��԰���Ķ������
	public static String showMyZoo() {
		StringBuilder myZoo=new StringBuilder();
		myZoo.append("��԰��Ŀǰ����������  ");
		myZoo.append(birdArea.getName()+": "+birdArea.getAnimalNum()+" ");
		myZoo.append(fishArea.getName()+": "+fishArea.getAnimalNum()+" ");
		myZoo.append(mammaliaArea.getName()+": "+mammaliaArea.getAnimalNum()+" ");
		myZoo.append(insectArea.getName()+": "+insectArea.getAnimalNum()+" ");
		return myZoo.toString();
	}
	
	//��ʼ������԰
	public void initialZoo() {
		addAnimal(new Bird("��ȸ", "bird", "��ɫ"));
		addAnimal(new Bird("ϲȵ", "bird", "��ɫ"));
		addAnimal(new Bird("����", "bird", "��ɫ"));
		addAnimal(new Bird("��ӥ", "bird", "��ɫ"));
		addAnimal(new Fish("����", "fish", 200));
		addAnimal(new Fish("����", "fish", 50));
		addAnimal(new Fish("����", "fish", 20));
		addAnimal(new Fish("ʳ����", "fish", 35));
		addAnimal(new Fish("����", "fish", 1000));
		addAnimal(new Fish("С����", "fish", 15));
		addAnimal(new Mammalia("�ϻ�", "mammalia", "���ԭ"));
		addAnimal(new Mammalia("����", "mammalia", "����ƽԭ"));
		addAnimal(new Mammalia("����", "mammalia", "�Ĵ�����ƽԭ"));
		addAnimal(new Mammalia("����", "mammalia", "����"));
		addAnimal(new Mammalia("��ţ", "mammalia", "����"));
		addAnimal(new Mammalia("����", "mammalia", "�����"));
		addAnimal(new Mammalia("����", "mammalia", "�󺣱�"));
		addAnimal(new Mammalia("����", "mammalia", "��Ѩ"));
		addAnimal(new Insect("����", "insect", "����"));
		addAnimal(new Insect("����", "insect", "�ܹ�"));
		addAnimal(new Insect("��Ӭ", "insect", "����"));
		addAnimal(new Insect("����", "insect", "�ܹ�"));
		addAnimal(new Insect("֪��", "insect", "�ܹ�"));
		addAnimal(new Insect("����", "insect", "����"));
		addAnimal(new Insect("����ư��", "insect", "����"));
		addAnimal(new Insect("ө���", "insect", "������"));
	}
	
}
