package org.example.EntityTest;

import junit.framework.Assert;
import org.example.Entity.ContactManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

public class ContactManagerTest {


    private ContactManager contactManager;

    // @BeforeAll --> it is used execute before only once throughout the test  , it should be static
    @BeforeAll
    public static void beforeAll() {
        System.out.println("@BeforeAll():: called");
    }

    // @AfterAll --> it is used execute after only once throughout the test , it should be static
    @AfterAll
    public static void afterAll() {
        System.out.println("@AfterAll():: called");
    }

    private static List<String> phoneList() {
        return Arrays.asList("0123456789", "0123456789", "0123456789");
    }

    // @BeforeEach --> Creating Obj for each method
    @BeforeEach
    public void setUp() {

        contactManager = new ContactManager();
        System.out.println("@BeforeEach():: called");
    }

    // @AfterEach --> Deleting Obj for each method
    @AfterEach
    public void tearDown() {
        contactManager = null;
        System.out.println("@AfterEach():: called");
    }

    // @RepeatedTest --> used to test repeatedly as per the given value
    @Test
    @RepeatedTest(value = 5)
    public void repeatedTestCase() {
        System.out.println("@RepeatedTest():: called");
    }

    // Positive Case----> Obj has to be created
    @Test
    @DisplayName("Test Case:: Contact Is Created or Not ....!!")
    public void objShouldCreate() {
        // Create of contactManager
        // ContactManager contactManager = new ContactManager();
        //set values for obj
        contactManager.addContact("Mahesh", "Kumar", "0123456789");

        // ensuring obj is created or nor
        Assert.assertEquals(1, contactManager.getAllContacts().size());
        Assert.assertFalse(contactManager.getAllContacts().isEmpty());
    }

    // Negative case --> it must throw an RuntimeException
    @Test
    @DisplayName("Test Case for:: Firstname is Empty Should Throw an RuntimeException")
    public void ShouldThrowExceptionIfFirstnameEmpty() {
        //    ContactManager contactManager = new ContactManager();

        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null, "Rehaman", "0123456789");
        });
    }

    // Note:: In each method ,ContactManager obj is created , that means it is duplicated code,

    // to over come that problem ,in Junit we have @BeforeEach

    // @BeforeEach is used to execute statements for all the methods


    // Test case for---> phoneNumber

    // PhoneNumberShouldNotBeNull

    // PhoneNumberShouldStartsWithZero

    // PhoneNumberShouldHave10Digits

    @Test
    @DisplayName("Test Case for:: LastName is Empty Should Throw an RuntimeException")
    public void ShouldThrowExceptionIfLastNameEmpty() {
        //  ContactManager contactManager = new ContactManager();

        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Abdul", null, "0123456789");
        });
    }

    @Test
    @DisplayName("Test Case for :: PhoneNumber Should Not Be Null")
    public void PhoneNumberShouldNotBeNull() {
        Assertions.assertThrows(RuntimeException.class, () ->
        {
            contactManager.addContact("abdul", "rehaman", null);
        });
    }

    // PhoneNumberShouldStartsWithZero
    @Test
    @DisplayName("Test Case for :: PhoneNumber Should Starts With Zero")
    public void PhoneNumberShouldStartsWithZero() {
        Assertions.assertThrows(RuntimeException.class, () ->
        {
            contactManager.addContact("abdul", "rehaman", "124578963");
        });
    }

    // PhoneNumberShouldHave10Digits
    @Test
    @DisplayName("Test Case for :: PhoneNumber Should Have 10 Digits")
    public void PhoneNumberShouldHave10Digits() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("abdul", "rehaman", "1234567890181");
        });
    }

    // Test case for validating if the obj is already exists
    @Test
    @DisplayName("Test Case for validating if the obj is already exists")
    public void validateObjIsAlreadyExists() {
        // add the obj
        contactManager.addContact("abdul", "rehaman", "0123456789");

        // try to add the same obj
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("abdul", "rehaman", "0123456789");
        }, "Obj is already existing...!!1");
    }

    // Day 2 Unit test annotations
    // @ValueSource alone with @ParameterizedTest
    @ParameterizedTest
    @ValueSource(strings = {"0123456789", "0123456789", "0122456789"})
    @DisplayName("Test Case For :: Checking multiple phone-number using @ParameterizedTest && @ValueSource")
    public void checkMultiplePhoneNumbers(String phone) {
        // add the obj
        contactManager.addContact("abdul", "rehaman", phone);

        Assertions.assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
    }

    // CsvSourceFile form a given path

    // @CsvTest is same as @ValueSource
    // csv stands for comma separated values
    @ParameterizedTest
    @CsvSource({"0123456789", "0123456789", "0122456789"})
    @DisplayName("Test Case For :: Checking multiple phone-number using @ParameterizedTest && @CsvTest")
    public void checkWithCsvTest(String phone) {
        // add the obj
        contactManager.addContact("abdul", "rehaman", phone);

        Assertions.assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
    }


    // @MethodSource
    // 1st have to create a method

    /*  @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    // path should be given here  src/test/resource --> should be in this directory
    @DisplayName("Test Case For :: Checking multiple phone-number using @ParameterizedTest && @CsvSource")
    public void checkWithCsvSource(String phone) {
        // add the obj
        contactManager.addContact("abdul", "rehaman", phone);

        Assertions.assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
    }

     */


    // MethodSource alone with ParameterizedTest
    // 1st create a method like below
    @ParameterizedTest
    @MethodSource("phoneList")
    @DisplayName("Test Case using MethodSource by creating a Method")
    public void methodSourceTest(String phoneList) {
        // add the obj
        contactManager.addContact("abdul", "rehaman", phoneList);

        Assertions.assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
    }

    /*
     private static List<String> phoneList() {
        return Arrays.asList("0123456789", "0123456789", "0123456789");
    }
     */


}