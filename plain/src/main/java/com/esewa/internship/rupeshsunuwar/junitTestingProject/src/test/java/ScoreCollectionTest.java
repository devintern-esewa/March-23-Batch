package com.esewa.internship.rupeshsunuwar.junitTestingProject.src.test.java;

import com.mockito.ScoreCollection;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ScoreCollectionTest {

    @Test
    public void answersArithmeticMeanOfTwoNumbers(){

        //Arrange
        ScoreCollection collection=new ScoreCollection();
        collection.add(()->5);
        collection.add(()->10);

        //Act
        int actualResult = collection.arithmeticMean();

        //Assert
       assertEquals(7.5,actualResult,0.5);

    }
}
