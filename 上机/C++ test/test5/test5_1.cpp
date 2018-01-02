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
			cout<<"姓名: "<<name<<" 编号："<<number<<" "; 
		}
}; 

class Student:public Person{
	private:
		int classNumber;
		int score;
	public:
		
		void sInput(){
			cout<<"输入学生信息"<<endl;
			input();
			cin>>classNumber>>score;
		}
		
		void sOutput(){
			output();
			cout<<"班号："<<classNumber<<" 成绩: "<<score<<endl;
		}
};

class Teacher:public Person{
	private:
		string job;
		string mini;
	public:
		
		void tInput(){
			cout<<"输入教师信息"<<endl;
			input();
			cin>>job>>mini;
		}
		
		void tOutput(){
			output();
			cout<<"职称: "<<job<<" 部门："<<mini<<endl;
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
