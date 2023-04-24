package com.esewa.internship.rupeshsunuwar.junitTestingProject.src.main.java.com.unittesting;

public class BankLoanService {

    public double calcSimpleInterest(double principle,double rate,double year){

        if(principle<=0 || rate <=0 || year<=0)

            throw  new IllegalArgumentException("Invalid Input");

        try{
            Thread.sleep(1000);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return ( principle * rate * year) / 100;
    }
}
