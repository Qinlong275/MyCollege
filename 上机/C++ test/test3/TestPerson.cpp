#include<iostream>
 using namespace std;
 class Person{
 	public:
 		Person(string name,string address,string city,string province,string code){
 			info[0]=name;
 			info[1]=address;
 			info[2]=city;
 			info[3]=province;
 			info[4]=code;
		 }
		 
		 void reName(string name){
		 	info[0]=name;
		 }
		 
		 void display(){
		 	for(int i=0;i<5;i++){
		 		cout<<info[i]<<" ";
			 }
			 cout<<endl;
		 }
		 
	private:
		string info [5];
		
		
 };
 
 int main(){
 	Person p("����","����","����","����","710126");
	p.display();
	p.reName("��С��");
	cout<<"������"<<endl;
	p.display(); 
 	return 0;
 }
