package com.esewa.internship.rupeshsunuwar.junitTestingProject.src.main.java.com.unittesting;

public class Printer {

    private static Printer INSTANCE=new Printer();

    private Printer(){

    }
    public static Printer getInstance(){

        return INSTANCE;
    }

}
