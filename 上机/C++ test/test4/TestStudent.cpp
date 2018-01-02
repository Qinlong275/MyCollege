#include<iostream>
using namespace std;
class Student{
	public:
		Student(int n,int s){
			number=n;
			score=s;
		}
		
		friend void qmax(Student*p);
	private:
		int number;
		int score;
};

void qmax(Student*p){
	int max=p->score;
	int number;
	for(int i=0;i<5;i++){
		if(max<(p+i)->score){
			max=(p+i)->score;
			number=(p+i)->number;
		}
	}
	cout<<number<<"号学生成绩最高,分数为："<<max<<endl; 
}
int main(){
	
	Student q[]={Student(1000,95),Student(1001,98),Student(1002,99),Student(1003,86),Student(1004,95)};
	qmax(q);
	return 0;
}
