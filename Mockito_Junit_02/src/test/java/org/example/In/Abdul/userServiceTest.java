package org.example.In.Abdul;

import org.example.Repository.userRepo;
import org.example.Service.userService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class userServiceTest {
    @InjectMocks
    private userService service;

    @Mock
    private userRepo repo;

    @BeforeEach
    public void createObjForEachMethod() {
        MockitoAnnotations.openMocks(this);
    }

    /* @AfterEach
    public void clearDownMockObj() {
        Mockito.reset(this);
    }
      */

    @Test
    @DisplayName("Test case for creating Obj for Positive case")
    public void loginValidationTest() {
        // create mock obj
        Assertions.assertNotNull(repo);
        // setting mock behaviour
        Mockito.when(repo.getLoginStatus(anyString(), anyString()))
                .thenReturn("Login Success");
        // original behaviour
        String actualStatus = service.validateLogin("abdul", "abdul");
        Assertions.assertNotNull(actualStatus);
        Assertions.assertEquals("Login Success", actualStatus);
    }

    // for negative case test
    @Test
    @DisplayName("Test case for creating Obj for Negative case")
    public void loginInValidationTest() {
        // create mock obj
        Assertions.assertNotNull(repo);
        // setting mock behaviour
        Mockito.when(repo.getLoginStatus(anyString(), anyString()))
                .thenReturn("Login Failure");
        // original behaviour
        String actualStatus = service.validateLogin("abdul123", "154abdul");
        Assertions.assertNotNull(actualStatus);
        Assertions.assertEquals("Login Failure", actualStatus);
    }

    // test case for trowing RunTime exception
    @Test
    @DisplayName("Test case for creating Obj for Exceptions case")
    public void loginNullValidationTest() {
        // create mock obj
        Assertions.assertNotNull(repo);
        // setting mock behaviour
        Mockito.when(repo.getLoginStatus(null, null))
                .thenThrow(new RuntimeException("Please Provide Login Credentials"));
        // original behaviour
        Assertions.assertThrows(RuntimeException.class, () -> {
            service.validateLogin(null, null);
        }, "Please Provide Login Credentials");
    }

    // another method in the service class
    @Test
    @DisplayName("Test Case for void Methods")
    public void validationForVoidMethod() {
        Assertions.assertNotNull(repo);
        service.sendEmailSms();
        verify(repo, times(1)).sendEmailSms(anyInt());
    }
}
