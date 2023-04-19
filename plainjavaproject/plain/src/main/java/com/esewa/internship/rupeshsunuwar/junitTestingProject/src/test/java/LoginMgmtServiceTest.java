package com.esewa.internship.rupeshsunuwar.junitTestingProject.src.test.java;

import com.nt.dao.LoginDAO;
import com.nt.service.LoginMgmtService;
import com.nt.service.LoginMgmtServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class LoginMgmtServiceTest {

    @InjectMocks
    private static LoginMgmtServiceImpl loginService;
    @Mock
    private static LoginDAO loginDAOMock;

    public LoginMgmtServiceTest() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testLoginWithValidCredentials() {
        Mockito.when(loginDAOMock.authenticate("rupesh", "4566")).thenReturn(1);
        assertTrue(loginService.login("rupesh", "4566"));
    }


    @Test
    public void testLoginWithInValidCredentials() {
        Mockito.when(loginDAOMock.authenticate("rupesh", "4577")).thenReturn(0);
        assertFalse(loginService.login("rupesh", "4577"));
    }


    @Test
    public void testLoginWithNoCredentials() {
        assertThrows(IllegalArgumentException.class, () -> {
            loginService.login("", "");

        });
    }

    @Test
    public void testRegisterWithSpy() {
        LoginDAO loginDAOSpy = Mockito.spy(LoginDAO.class);
        LoginMgmtService loginService = new LoginMgmtServiceImpl(loginDAOSpy);
        loginService.registerUser("xyz", "visitor");
        loginService.registerUser("rupesh", "admin");
        loginService.registerUser("python", "");

        Mockito.verify(loginDAOSpy, Mockito.times(0)).addUser("xyz", "visitor");
        Mockito.verify(loginDAOSpy, Mockito.times(1)).addUser("rupesh", "admin");
        Mockito.verify(loginDAOSpy, Mockito.never()).addUser("python", "");

    }
}
