#include <iostream>
#include<string>
#include<vector>
using namespace std;

class Transaction{

public:
    int transactionID  ;
    string description ;
     string date="01/01/2023";
    double amount;



    Transaction(int id,string decs,double amount): transactionID(id), description(decs), amount(amount) {}

    void settransactionID(int id){
        transactionID = id;
    }
    void setdescription(const string decs){
        description= decs;
    }
    void setamount(double amount){
        amount=amount;
    }


    int getid() const{
        return transactionID;

    }
    string getdecs() const{
    return description;
    }

    double getamount() const{
        return amount;
    }

    void  display(){

    cout<<"ID giao dich: "<< transactionID<<endl;
    cout<<"         Noi dung: "<< description<<endl;
    cout<<"         So tien: "<< amount <<endl;
    cout<<"         Ngay: "<<date<<endl;

    }


};




int main()
{
    Transaction Transaction1(1,"Chuyen khoan em tra tien , cam on anh",500);
    Transaction1.display() ;

    return 0 ;
}
