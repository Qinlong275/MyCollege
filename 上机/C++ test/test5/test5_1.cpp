#include<iostream>
using namespace std;
class Person{
	private:
		string name;
		int number;
		
	public:
		
		void input(){
			cin>>name>>number;
		}
		
		void output(){ 
			cout<<"����: "<<name<<" ��ţ�"<<number<<" "; 
		}
}; 

class Student:public Person{
	private:
		int classNumber;
		int score;
	public:
		
		void sInput(){
			cout<<"����ѧ����Ϣ"<<endl;
			input();
			cin>>classNumber>>score;
		}
		
		void sOutput(){
			output();
			cout<<"��ţ�"<<classNumber<<" �ɼ�: "<<score<<endl;
		}
};

class Teacher:public Person{
	private:
		string job;
		string mini;
	public:
		
		void tInput(){
			cout<<"�����ʦ��Ϣ"<<endl;
			input();
			cin>>job>>mini;
		}
		
		void tOutput(){
			output();
			cout<<"ְ��: "<<job<<" ���ţ�"<<mini<<endl;
		}
};

int main(){
	Student s;
	s.sInput();
	s.sOutput();
	Teacher t;
	t.tInput();
	t.tOutput();
	return 0;
}
