package com.esewa.internship.rupeshsunuwar.junitTestingProject.src.test.java;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;

public class MockVsSpyTest{

    @Test
    public void testList() {

        List<String> listMock = Mockito.mock(ArrayList.class);
        List<String> listSpy = Mockito.spy(new ArrayList());


        listMock.add("table");
        Mockito.when(listMock.size()).thenReturn(1);
        listSpy.add("table");
        Mockito.when(listSpy.size()).thenReturn(1);
        System.out.println(listMock.size()+" "+listSpy.size());


    }
}
