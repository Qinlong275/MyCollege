#include<iostream>
using namespace std;
class Dog{
	public:
		Dog(string mname,int mage,char msex,int mweight){
			name=mname;
			age=mage;
			sex=msex;
			weight=mweight;
		}
		
		void pname(){
			cout<<name<<endl;
		}
		
		void page(){
			cout<<age<<endl;
		}
		
		string name;
		
	private:
		int age;
		char sex;
		int weight;
};

int main(){
	Dog d("log",12,'ÄÐ',60);
	Dog * dp=&d;
	(*dp).page();
	
	string *pn=&d.name;
	cout<<*pn;
	return 0;
}
