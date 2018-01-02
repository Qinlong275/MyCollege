#include<iostream>
using namespace std;
int max(int a,int b,int c=0){
	if(b>a) a=b;
	if(c>a) a=c;
	return a;
}
int main(){
	int a,b,c;
	cin >> a>> b>> c;
	cout <<"max value is "<<max(a,b,c)<<endl;
	cin >>a>>b;
	cout <<"max value is "<<max(a,b)<<endl;
	return 0;
}
