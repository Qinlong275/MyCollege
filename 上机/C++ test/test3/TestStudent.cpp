#include<iostream>
using namespace std;
class Student{
	public:
		Student(string mname,int mnum,int mMathScore,int mEnglishScore){
			name=mname;
			num=mnum;
			mathScore=mMathScore;
			englishScore=mEnglishScore;
			count++;
			mathTotalScore+=mathScore;
			englishTotalScore+=englishScore;
		}
		
		void ShowBase(){
			cout<<name<<" "<<num<<" "<<mathScore<<" "<<englishScore<<endl;	
		}
		
		static void ShowStatic();
		
	private:
		string name;
		int num;
		int mathScore;
		int englishScore;
		static int count;
		static int mathTotalScore;
		static int englishTotalScore;
};

int Student::count=0;
int Student::mathTotalScore=0;
int Student::englishTotalScore=0;
void Student::ShowStatic(){
	cout<<"总人数为: "<<count<<" 数学总成绩为："<<mathTotalScore<<" 英语总成绩为："<<englishTotalScore<<endl;
}

int main(){
	Student st1("qinlong",100,99,98);
	st1.ShowBase();
	Student st2("lihai",101,95,96);
	st2.ShowBase();
	Student st3("weinai",102,98,97);
	st3.ShowBase();
	cout<<endl;
	Student::ShowStatic();
}
