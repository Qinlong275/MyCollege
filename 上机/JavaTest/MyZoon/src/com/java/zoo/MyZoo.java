package com.java.zoo;

public class MyZoo {
	
	public static ZooArea birdArea;
	public static ZooArea fishArea;
	public static ZooArea mammaliaArea;
	public static ZooArea insectArea;
	
	public MyZoo() {
		birdArea=new ZooArea("鸟之家");
		fishArea=new ZooArea("鱼之家");
		mammaliaArea=new ZooArea("哺乳动物之家");
		insectArea=new ZooArea("昆虫之家");
		initialZoo();
	}
	
	//新引入一只动物进来
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
	
	//展示当前动物园各园区的动物情况
	public static String showMyZoo() {
		StringBuilder myZoo=new StringBuilder();
		myZoo.append("各园区目前动物数量：  ");
		myZoo.append(birdArea.getName()+": "+birdArea.getAnimalNum()+" ");
		myZoo.append(fishArea.getName()+": "+fishArea.getAnimalNum()+" ");
		myZoo.append(mammaliaArea.getName()+": "+mammaliaArea.getAnimalNum()+" ");
		myZoo.append(insectArea.getName()+": "+insectArea.getAnimalNum()+" ");
		return myZoo.toString();
	}
	
	//初始化动物园
	public void initialZoo() {
		addAnimal(new Bird("麻雀", "bird", "灰色"));
		addAnimal(new Bird("喜鹊", "bird", "蓝色"));
		addAnimal(new Bird("鹦鹉", "bird", "黄色"));
		addAnimal(new Bird("老鹰", "bird", "黑色"));
		addAnimal(new Fish("鲨鱼", "fish", 200));
		addAnimal(new Fish("鲤鱼", "fish", 50));
		addAnimal(new Fish("泥鳅", "fish", 20));
		addAnimal(new Fish("食人鱼", "fish", 35));
		addAnimal(new Fish("鲸鱼", "fish", 1000));
		addAnimal(new Fish("小丑鱼", "fish", 15));
		addAnimal(new Mammalia("老虎", "mammalia", "大草原"));
		addAnimal(new Mammalia("大象", "mammalia", "非洲平原"));
		addAnimal(new Mammalia("袋鼠", "mammalia", "澳大利亚平原"));
		addAnimal(new Mammalia("猴子", "mammalia", "树林"));
		addAnimal(new Mammalia("奶牛", "mammalia", "家养"));
		addAnimal(new Mammalia("河马", "mammalia", "沼泽地"));
		addAnimal(new Mammalia("海豹", "mammalia", "大海边"));
		addAnimal(new Mammalia("蝙蝠", "mammalia", "洞穴"));
		addAnimal(new Insect("蝴蝶", "insect", "趋光"));
		addAnimal(new Insect("蜻蜓", "insect", "避光"));
		addAnimal(new Insect("苍蝇", "insect", "趋光"));
		addAnimal(new Insect("蚂蚁", "insect", "避光"));
		addAnimal(new Insect("知了", "insect", "避光"));
		addAnimal(new Insect("蚂蚱", "insect", "趋光"));
		addAnimal(new Insect("七星瓢虫", "insect", "趋光"));
		addAnimal(new Insect("萤火虫", "insect", "自身发光"));
	}
	
}
