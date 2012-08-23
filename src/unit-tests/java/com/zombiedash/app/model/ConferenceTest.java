package com.zombiedash.app.model;

import org.junit.Test;

public class ConferenceTest {
    @Test(expected = RuntimeException.class)
    public void constructorShouldThrowExceptionOnAnyEmptyField(){
        new Conference("","","","","","",0);
    }

}
