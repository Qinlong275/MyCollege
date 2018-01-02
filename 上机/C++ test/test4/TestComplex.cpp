#include<iostream> 
using namespace std;
class Complex{
	private:
		double real;	//Êµ²¿ 
		double image;	//Ðé²¿
	
	public:
	 
	Complex(){
		real=0;
		image=0;
	}
	
	Complex(double r,double i){
		real=r;
		image=i;
	}
	
	void display();
	
	Complex operator*(Complex &c2); 
	
	friend Complex operator/(Complex &c1,Complex &c2);
	
};

void Complex::display(){
	cout<<"("<<real<<","<<image<<"i)"<<endl;
}

Complex Complex::operator*(Complex &c2){
	Complex c;
	c.real=real*c2.real-image*c2.image;
	c.image=image*c2.real+real*c2.image;
	return c;
}

Complex operator/(Complex &c1,Complex &c2){
	return Complex((c1.real*c2.real+c1.image*c2.image)/(c2.image*c2.image+c2.real*c2.real)
		,(c1.image*c2.real-c1.real*c2.image)/c2.image*c2.image+c2.real*c2.real);
}

int main(){
	Complex c1(3,4),c2(5,-10),c3,c4;
	c3=c1*c2;
	c4=c1/c2;
	cout<<"c3="; c3.display();
	cout<<"c4="; c4.display();
	return 0;
}
