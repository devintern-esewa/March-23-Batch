package com.esewa.internship.rupeshsunuwar.junitTestingProject.src.test.java;

import org.junit.jupiter.api.*;
import com.unittesting.BankLoanService;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TestingBankLoanServiceclass")
//@TestMethodOrder(value= MethodOrderer.OrderAnnotation.class)
@TestMethodOrder(value = MethodOrderer.DisplayName.class)
public class TestBankLoanService {

    private static BankLoanService service;


    @BeforeAll
    public static void setUpOnce() {
        System.out.println("setup once method");
        service = new BankLoanService();
    }


    //Run before each test  method
//    @BeforeEach
//    public void setUp(){
//        System.out.println("Setup() Method");
//        service=new BankLoanService();
//
//
//    }

    @Test
    @DisplayName("Testing with small Numbers")
    @Order(10)
    public void testCalcSimpleInterestWithSmallNumbers() {


        double actual = service.calcSimpleInterest(100000, 12, 2);
        double expected = 24000.0;

        assertEquals(expected, actual, "May results not matching");


    }

    @Test
    @DisplayName("Testing with Invalid input.")
    @Order(0)
    @Tag("Uat")
    public void testcalcsimpleInterestWithInvalidInput(TestInfo info) {

        System.out.println(info.getClass() + " " + info.getDisplayName()+ " "+ info.getTestMethod().get().getName() +
        info.getTags());


        //assert throws which expected the exception is thrown from the actual output.
        assertThrows(IllegalArgumentException.class, () -> service.calcSimpleInterest(0, 0, 0));


    }

    @Test
    @DisplayName("Testinh with Big Numbers")
    @Order(-1)
    @Tag("Uat")
    public void testcalcsimpleInterestWithBigNumbers() {


        double actual = service.calcSimpleInterest(10000000, 12, 2);
        double expected = 2400000.12;


        assertEquals(expected, actual, 0.5, "May result not matching");
        //0.5 is delta value the different.
    }


    @Test
    @DisplayName("Testing with time")
    @Order(5)
    public void testcalcsimpleInterestWithTime() {


        assertTimeout(Duration.ofMillis(2000),
                () -> service.calcSimpleInterest(1000000000, 12, 2)
        );
    }

    @Test
    @DisplayName("Testing for no exceptions")
    @Order(-2)
    public void testcalcsimpleInterestForNotExceptions() {


        assertDoesNotThrow(() -> service.calcSimpleInterest(10000, 12, 2));
    }


//    @AfterEach
//    public void clear(){
//
//        System.out.println("Clear method");
//        service=null;
//    }
//

    @AfterAll
    public static void clearOnce() {
        System.out.println("ClearOnce method");
        service = null;
    }


}
