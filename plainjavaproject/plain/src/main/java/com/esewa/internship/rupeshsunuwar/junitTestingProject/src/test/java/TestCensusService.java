package com.esewa.internship.rupeshsunuwar.junitTestingProject.src.test.java;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import com.unittesting.CensusService;

public class TestCensusService {

    @Test
    @Disabled
    @RepeatedTest(value = 10, name = "execution of {displayName} - {currentRepetition}/{totalRepetitions}")
    @DisplayName("Testing Export Data Method")
    public void testexportData() {
        System.out.println("Census Service Test");
        CensusService service = new CensusService();
        Assertions.assertEquals("data exported", service.exportData());
    }


    @ParameterizedTest
    @Disabled
    @ValueSource(ints = {15, 21, 33, 51, 77})
    public void testisOdd(int n) {
        CensusService service = new CensusService();
        Assertions.assertTrue(service.isOdd(n));

    }


    @ParameterizedTest
    @EmptySource
    public void testisEmpty(String data) {
        System.out.println("testisEmpty method");
        CensusService service = new CensusService();
        Assertions.assertTrue(service.isEmpty(data));


    }
}
