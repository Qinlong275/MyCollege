#include<iostream>
using namespace std;
const float PI=3.14159;
class Shape{
	public:
		virtual float getArea() const=0;
};

class Circle:public Shape{
	private:
		float radius;
	public:
		Circle(float r){
			radius=r;
		}
		float getArea() const{
			return PI*radius*radius;
		}
};

class Square:public Shape{
	private:
		float length;
	public:
		Square(float le){
			length=le;
		}
		float getArea() const{
			return length*length;
		}
};

class Rectangle:public Shape{
	private:
		float len;
		float wid;
	public:
		
		Rectangle(float le,float wi){
			len=le;
			wid=wi;
		}
		
		float getArea() const{
			return len*wid;
		}
};

class Trapezoid:public Shape{
	private:
		float up;
		float down;
		float hi;
	public:
		Trapezoid(float u,float d,float h){
			up=u;
			down=d;
			hi=h;
		}
		float getArea() const{
			return (up+down)*hi/2;
		}
};

class Triangle:public Shape{
	private:
		float down;
		float hi;
	public:
		Triangle(float d,float h){
			down=d;
			hi=h;
		}
		float getArea() const{
			return down*hi/2;
		}
};

int main(){
	Circle c(3);
	Square s(4);
	Rectangle r(5,9);
	Trapezoid t(6,8,5);
	Triangle tr(10,4);
	Shape* shapes [5]={&c,&s,&r,&t,&tr};
	float areaSum=0;
	for(int i=0;i<5;i++){
		float area=shapes[i]->getArea();
		areaSum+=area;
		cout<<area<<" ";
	}
	cout<<endl<<"总的面积和为："<<areaSum<<endl;
	return 0;
}
