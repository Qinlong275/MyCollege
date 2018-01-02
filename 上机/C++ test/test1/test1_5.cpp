#include<iostream>
using namespace std;
template<typename T>
void sort(T *p,int n){
	for(int i=0;i<n-1;i++){
		for(int j=0;j<n-1-i;j++){
			if(p[j]>p[j+1]){
				T temp=p[j];
				p[j]=p[j+1];
				p[j+1]=temp;
			}
		}
	}
	
	for(int i=0;i<n;i++){
		cout<<p[i]<<" ";
	}
	cout<<endl;
}
int main(){
	int n,a[100];
	float b[100];
	
	cout<<"浮点型排序,输入数量n："<<endl;
	cin>>n;
	for(int i=0;i<n;i++){
		cin>>b[i];
	}
	sort(b,n);
	
	cout<<"整形排序,输入数量n："<<endl; 
	cin>>n;
	for(int i=0;i<n;i++){
		cin>>a[i];
	}
	sort(a,n);	
}
