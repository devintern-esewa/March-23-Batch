package com.esewa.internship.rupeshsunuwar.junitTestingProject.src.main.java.com.unittesting;

public class CensusService {

    public String exportData() {

        return "data exported";
    }

    public boolean  isOdd(int n) {

        if (n % 2 == 0) {
            return false;

        } else {
            return true;

        }


    }

    public boolean isEmpty(String name){

        return name.isBlank();


    }
}
