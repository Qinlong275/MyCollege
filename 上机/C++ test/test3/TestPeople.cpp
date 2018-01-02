#include<iostream>
using namespace std;
class People{
	public:
		People(int mage,int mheight,int mweight){
			age=mage;
			height=mheight;
			weight=mweight;
			num++;
		}
		
		void Eating(){
			weight++;
		}
		
		void Sporting(){
			height++;
		}
		
		void Sleeping(){
			age++;
			height++;
			weight++;
		}
		
		void display(){
			cout<<age<<"岁 "<<height<<"厘米 "<<weight<<"市斤"<<endl;
		}
		
		static void displayNum();
		
		
	protected:
		int age;
		int height;
		int weight;
		static int num;
};
int People::num=0;
void People::displayNum(){
	cout<<"总人数为："<<num<<endl; 
} 
int main(){
	People p1(20,175,65);
	People p2(18,168,66);
	p1.display();
	p1.Eating();
	p1.Sporting();
	p1.Sleeping();
	cout<<endl;
	p1.display();
	
	p2.display();
	p2.Eating();
	p2.Sporting();
	p2.Sleeping();
	cout<<endl;
	p2.display();
	
	cout<<endl;
	People::displayNum();
	
	return 0;
}
