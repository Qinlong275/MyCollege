#include<iostream>
using namespace std;
class Len{
	float length;
	float width;
	float height;
	public:
		void input(){
			cin>>length>>width>>height;
			cout<<endl;
		}
		
		float getVolume(){
			return length*width*height;
		}
};
int main(){
	Len l1;
	Len l2;
	Len l3;
	l1.input();
	l2.input();
	l3.input();
	cout<<l1.getVolume()<<" "<<l2.getVolume()<<" "<<l3.getVolume();  
}
