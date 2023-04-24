package com.esewa.internship.rupeshsunuwar.junitTestingProject.src.test.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.unittesting.Printer;

import static org.junit.jupiter.api.Assertions.fail;

public class PrinterTest {


    @Test
    public void testSingleton(){

        Printer p1=Printer.getInstance();
        Printer p2=Printer.getInstance();
        if(p1==null || p2==null)
            fail("p1 , p2 reference must be null");
        Assertions.assertSame(p1,p2);

    }
}
