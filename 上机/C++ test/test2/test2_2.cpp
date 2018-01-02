#include<iostream>
using namespace std;
const double PI=3.14159;
class Circle{
	double radius;
	public:
		double getArea(){
			cout<<"输入圆的半径"<<endl;
			cin>>radius;
			return PI*radius*radius;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
		}
};

int main(){
	double area;
	Circle c1;
	area=c1.getArea();
	cout<<"面积为: "<<area<<endl; 
}

