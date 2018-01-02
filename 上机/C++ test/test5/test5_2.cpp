#include<iostream>
using namespace std;
class Teacher{
	protected:
		string name;
		int age;
		string sex;
		string address;
		int phone;
		string title;
	public:
		Teacher(string n,int a,string s,string ad,int p,string t);
		void display(){
			cout<<name<<" "<<age<<" "<<sex<<" "<<address<<" "<<phone<<" "<<title<<" ";
		}
};

Teacher::Teacher(string n,int a,string s,string ad,int p,string t){
	name=n;
	age=a;
	sex=s;
	address=ad;
	phone=p;
	title=t;
}

class Cadre{
	protected:
		string name;
		int age;
		string sex;
		string address;
		int phone;
		string post;
	public:
		Cadre(string n,int a,string s,string ad,int p,string po);
};

Cadre::Cadre(string n,int a,string s,string ad,int p,string po){
		name=n;
	age=a;
	sex=s;
	address=ad;
	phone=p;
	post=po;
}

class Teacher_Cadre:public Teacher,public Cadre{
	private:
		int eages;
	public:
		Teacher_Cadre(string n,int a,string s,string ad,int p,string t,string po,int e);
		void show(){
			display();
			cout<<post<<" "<<eages<<endl;
		}
		
};

Teacher_Cadre::Teacher_Cadre(string n,int a,string s,string ad,int p,
		string t,string po,int e):Teacher(n,a,s,ad,p,t),Cadre(n,a,s,ad,p,po){
	eages=e;
}

int main(){
	Teacher_Cadre t("秦龙",20,"男","西电",120,"A","学生",10000);
	t.show();
}
