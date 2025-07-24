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

    @Test
    public void testCheckAccount_Success() {
        HashMap<String, String> fakeAccount = new HashMap<>();
        fakeAccount.put("thinh", "123456");
        Mockito.when(mockModel.findAccount()).thenReturn(fakeAccount);

        presenter.checkAccount("thinh", "123456");

        Mockito.verify(mockView).onSuccess("Login success");
    }

    @Test
    public void testCheckAccount_UserNotFound() {
        HashMap<String, String> fakeData = new HashMap<>(); // trống
        Mockito.when(mockModel.findAccount()).thenReturn(fakeData);

        presenter.checkAccount("nonexist@gmail.com", "any");

        Mockito.verify(mockView).onError("Login failed");
    }
}

