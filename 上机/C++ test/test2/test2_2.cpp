#include<iostream>
using namespace std;
const double PI=3.14159;
class Circle{
	double radius;
	public:
		double getArea(){
			cout<<"����Բ�İ뾶"<<endl;
			cin>>radius;
			return PI*radius*radius;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
		}
};

int main(){
	double area;
	Circle c1;
	area=c1.getArea();
	cout<<"���Ϊ: "<<area<<endl; 
}

