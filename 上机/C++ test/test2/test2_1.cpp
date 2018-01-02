#include<iostream>
using namespace std;
class Date{
	int year;
	int month;
	int day;
	public:
		void input();
		void print();
/**		void input(){
			cout<<"输入年月日"<<endl;
			cin>>year>>month>>day; 
		}
		
		void print(){
			cout<<year<<"-"<<month<<"-"<<day<<endl;
		}
		**/
};

int main(){
	Date d1;
	d1.input();
	d1.print();
	return 0;
}

void Date ::input(){
	cout<<"输入年月日"<<endl;
	cin>>year>>month>>day; 
}
void Date ::print(){
	cout<<year<<"-"<<month<<"-"<<day<<endl;
}
