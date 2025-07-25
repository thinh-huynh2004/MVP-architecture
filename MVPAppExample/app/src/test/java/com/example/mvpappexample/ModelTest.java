package com.example.mvpappexample;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ModelTest {
    private Model model;

    @Before
    public void setUp() {
        model = new Model();
    }

    @Test
    public void checkAccount() {
        boolean check = model.checkAccount("abc", "abc");
        assertEquals(false, check);
    }
}