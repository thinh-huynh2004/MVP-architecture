package com.example.mvpappexample;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

public class LoginPresenterTest {
    @Mock
    private Contract.View mockView;
    @Mock
    private Contract.Model mockModel;

    private Contract.Presenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        presenter = new Presenter(mockView, mockModel);

    }

}

