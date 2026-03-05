package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.assertThrows;

public class calcTesting {
    @ParameterizedTest
    @CsvSource(
            {
                    "10,'abc'",
                    "'abc',7",
                    "-10.5,'hello'"
            }
    )
//    void add(double a,double b,double expected){
//        calc cl=new calc();
//        double result= cl.add(a,b);
//        Assertions.assertEquals(expected,result);
//    }
    void addInvalidStringTest(String a, String b){
        calc cl=new calc();
            assertThrows(IllegalArgumentException.class,
                () -> cl.add(a,b));
    }
}
