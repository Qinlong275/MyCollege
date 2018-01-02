#include<iostream>
using namespace std;
void sort(int &a,int &b,int &c){
	if(a>b) {
		int x=a;
		a=b;
		b=x;
	}
	if(a>c){
		int x=a;
		a=c;
		c=x;
	}
	if(b>c){
		int x=b;
		b=c;
		c=x;
	}
}
int main(){
	int a,b,c;
	cin >>a>>b>>c;
	sort(a,b,c);
	cout<<"after sort: "<<a<<" "<<b<<" "<<c<<endl;
	return 0;
}
