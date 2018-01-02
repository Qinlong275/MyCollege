#include<iostream>
using namespace std;
class Rec{
	public:
		Rec(){
			for(int i=0; i<2; i++){  
        		for(int j=0; j<3; j++){  
            		ay[i][j]=0;  
        		}  
    		}
		}
		
		friend istream& operator>>(istream&,Rec&);
		friend ostream& operator<<(ostream&,Rec&);
	private:
		int ay[2][3];
};


istream& operator>>(istream&in,Rec&r){
	cout<<"请输入矩阵，2行3列"<<endl; 
	for(int i=0;i<2;i++){
		for(int j=0;j<3;j++){
			in>>r.ay[i][j];
		}
	}
	return in;
}

ostream& operator<<(ostream& o,Rec& r){
	cout<<"矩阵输出为:"<<endl;
	for(int i=0;i<2;i++){
		for(int j=0;j<3;j++){
			o<<r.ay[i][j]<<" ";
		}
		o<<endl;
	}
	return o;
}


int main(){
	
	Rec q;
	cin>>q;
	cout<<q;
	return 0;
}
