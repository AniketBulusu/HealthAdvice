package com.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;





class SceneControllerTest {
    // Wrapper method for calling the existing main method for testing invalid inputs
    private void invokeMainWithInputSex(String input) {
        InputStream originalSystemIn = System.in;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        UserInput input_test = new UserInput();

        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            System.setOut(new PrintStream(outputStream));

            input_test.getUserInput();

            String printedOutput = outputStream.toString();
            assertTrue(printedOutput.contains("Error: Please enter a valid option (M = Male, F = Female)"));
        } finally {
            System.setIn(originalSystemIn);
            System.setOut(System.out);
        }
    }

    // Wrapper Method for calling the main for testing age inputs that are invalid
    private void invokeMainWithInputAge(String input) {
        InputStream originalSystemIn = System.in;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        UserInput input_test = new UserInput();

        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            System.setOut(new PrintStream(outputStream));

            input_test.getUserInput();

            String printedOutput = outputStream.toString();
            assertTrue(printedOutput.contains("Error: Your age does not fit within the allowed range. Please enter an age from 1-75"));
        } finally {
            System.setIn(originalSystemIn);
            System.setOut(System.out);
        }
    }

    // Wrapper method for calling the main for testing invalid unit choice
    private void invokeMainWithInputInvalidUnitForWeight(String input) {
        InputStream originalSystemIn = System.in;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        UserInput input_test = new UserInput();

        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            System.setOut(new PrintStream(outputStream));

            input_test.getUserInput();

            String printedOutput = outputStream.toString();
            assertTrue(printedOutput.contains("Error: Please enter a valid choice! ('1' for lbs, '2' for kgs)"));
        } finally {
            System.setIn(originalSystemIn);
            System.setOut(System.out);
        }
    }

    //Wrapper Method for testing the bmi function
    private String invokeBMIwithInputs(float weight, int feet, int inches, int choice) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        BMI bmi_test = new BMI(weight, feet, inches, choice);
        try {
            System.setOut(new PrintStream(outputStream));
            bmi_test.calculateBMI(weight, feet, inches, choice);
        } finally {
            System.setOut(originalOut);
        }
        return outputStream.toString().trim();
    }


    // Testing if the main displays desired message when the user enters invalid sex
    @Test
    void InvalidSexShouldReturnMessage() {
        {// Mocking invalid sex input from user
            String user_input = "whatever\nM\nJack\n25\n1\n180\n5\n10\nSedentary\n8\n7\n2\n1\n";

            // Calling the wrapper method with the predefining the input
            invokeMainWithInputSex(user_input);
        }
    }


    // Testing if the main displays desired message when the user enters invalid number as sex
    @Test
    void InvalidNumberSexError() {
        // Mocking the invalid user input
        String user_input = "2\nM\nJack\n25\n1\n180\n5\n10\nSedentary\n8\n7\n2\n1\n";
        invokeMainWithInputSex(user_input);
    }

    // Testing if main displays desired output when user enters an age too big
    @Test
    void InvalidTooBigAgeShouldReturnMessage() {
        //Mocking invalid age input from user (predefining the input)
        String user_input = "M\nJack\n149\n25\n1\n180\n5\n10\nSedentary\n8\n7\n2\n1\n";
        // Calling the wrapper method predefined input passed on to it
        invokeMainWithInputAge(user_input);
    }

    //Testing if main displays desired output when user enters an age too small
    @Test
    void InvalidSmallAgeShouldReturnMessage() {
        //Mocking invalid small age inputs from user (predefining the input)
        String user_input = "M\nJack\n-43\n25\n1\n180\n5\n10\nSedentary\n8\n7\n2\n1\n";
        invokeMainWithInputAge(user_input);
    }

    //Testing if main displays an error message when user enters invalid unit for weight
    @Test
    void InvalidNumberForWeightUnit() {
        //Mocking invalid integer input for weight unit option
        String user_input = "F\nSusan\n25\n4\n1\n180\n5\n10\nSedentary\n8\n7\n2\n1\n";
        invokeMainWithInputInvalidUnitForWeight(user_input);
    }

    //Testing the bmi function at the boundaries of iterations. In this case, severely underweight in lbs
    @Test
    void testBMIBoundarySeverelyUnderweight() {
        float weight = 103.7f; // weight in pounds = BMI of 18.4
        int feet = 5;          // height in feet
        int inches = 3;        // height in inches
        int choice = 1;        // indicating lbs unit

        String user_input = invokeBMIwithInputs(weight, feet, inches, choice);
        assertTrue(user_input.contains("You are severely underweight!"));
    }

    // Testing the bmi function at the normal weight boundary
    @Test
    void testBMIBoundaryHealthyWeight() {
        float weight = 104.5f; // weight in pounds = BMI of 18.5
        int feet = 5;          // height in feet
        int inches = 3;        // height in inches
        int choice = 1;        // indicating lbs unit

        String user_input = invokeBMIwithInputs(weight, feet, inches, choice);
        assertTrue((user_input.contains("You are considered a normal healthy weight!")));
    }

    //Testing the bmi function at the over weight boundary
    @Test
    void testBMIBoundaryOverWeight() {
        float weight = 150.3f; // weight in pounds = BMI of 25.0
        int feet = 5;          // height in feet
        int inches = 5;        // height in inches
        int choice = 1;        // indicating lbs unit

        String user_input = invokeBMIwithInputs(weight, feet, inches, choice);
        assertTrue((user_input.contains("You are overweight!")));
    }

    //Testing the bmi function at the obese boundary in kgs
    @Test
    void testBMIBoundaryObeseInKgs() {
        float weight = 81.8735202f; // weight in kgs = BMI of 30.0
        int feet = 5;          // height in feet
        int inches = 5;        // height in inches
        int choice = 2;        // indicating kgs unit

        String user_input = invokeBMIwithInputs(weight, feet, inches, choice);
        assertTrue(user_input.contains("You are severely overweight or obese"));
    }

    //Testing if the calroieIntake function does return a string
    @Test
    void testcalculateCalorieIntakeReturnValue() {
        //Pre-defined parameters for the test. ie) mocking the user input
        boolean isFemale = false;
        int age = 34;
        String activity_level = "Active";

        //Creating the instance of calculateCalorieIntake class
        CalorieIntakeCalculator calculator = new CalorieIntakeCalculator(age, isFemale, activity_level);


        // Invoking the method directly as it does have a return value
        String user_input = calculator.calculateCalorieIntake(isFemale, age, activity_level);

        assertNotNull(user_input); //Test if it gives a null
        assertNotEquals("", user_input);
    }

    //Testing the sleep function related to the hours of sleep for adults (18-64)
    @Test
    void testNeedMoreSleepForAdults() {
        int age = 23;
        int hours = 2; // Less than recommended
        Sleep sleep_test = new Sleep(age, hours, 9); // Quality not relevant here
        String advice = sleep_test.provideSleepAdviceHour();
        assertTrue(advice.contains("It seems like you're not getting enough sleep\nfor your age group."));
    }

    //Testing the sleep function related to the hours of sleep for seniors (64 and up)
    @Test
    void testNeedMoreSleepForSenior() {
        int age = 65;
        int hours = 2; // Less than recommended
        Sleep sleep_test = new Sleep(age, hours, 1); // Quality not relevant here
        String advice = sleep_test.provideSleepAdviceHour();
        assertTrue(advice.contains("It seems like you're not getting enough sleep\nfor your age group."));
    }

    // Testing the sleep function related to the hours of sleep for adults when they need less sleep
    @Test
    void testNeedLessSleepForAdults() {
        int age = 23;
        int hours = 12; // More than necessary
        Sleep sleep_test = new Sleep(age, hours, 4); // Quality not relevant here
        String advice = sleep_test.provideSleepAdviceHour();
        assertTrue(advice.contains("You might be getting more sleep\nthan necessary for your age group."));
    }


    // Testing the sleep function related to the hours of sleep for seniors when they need less sleep
    @Test
    void testNeedLessSleepForSeniors() {
        int age = 65;
        int hours = 12; // More than necessary
        Sleep sleep_test = new Sleep(age, hours, 4); // Quality not relevant here
        String advice = sleep_test.provideSleepAdviceHour();
        assertTrue(advice.contains("You might be getting more sleep\nthan necessary for your age group."));
    }

    //Testing the sleep function output when an adult is doing great with their sleep hours
    @Test
    void testSleepRightHoursAdults() {
            int age = 27;
            int hours = 7; // Adequate sleep
            Sleep sleep_test = new Sleep(age, hours, 10); // Quality not relevant here
            String advice = sleep_test.provideSleepAdviceHour();
            assertTrue(advice.contains("Great!\nYou're getting an adequate amount\n of sleep for your age group."));
        }

    //Testing the sleep function output when an adult is doing great with their sleep hours
    @Test
    void testSleepRightHoursSenior() {
        //Pre-defined parameters for the test.
        int age = 65;
        int hours = 8;
        int quality = 3;

        Sleep sleep_test = new Sleep(age, hours, 10); // Quality not relevant here
        String advice = sleep_test.provideSleepAdviceHour();
        assertTrue(advice.contains("Great!\nYou're getting an adequate amount\n of sleep for your age group."));
    }

    //Testing the sleep function at its conditional boundary for sleep quality
    @Test
    void testSleepQualityVeryLow() {
        Sleep sleep_test = new Sleep(34, 8, 2); // Age and hours not directly relevant
        String advice = sleep_test.provideSleepAdviceQuality();
        assertTrue(advice.contains("Your sleep quality rating is especially low."));
    }


    /* -------------------------------------------------CUT OFF FOR DEMO 2 TESTS-------------------------------------------- */

    // Testing if the getProfile() method returns a profile properly when the profile exists
    @Test
    void testGetProfileReturnsProfileForExistingUser() {
        //Arrange
        ProfilesManager profiles_manager = new ProfilesManager();
        profiles_manager.addOrUpdateProfile("testUser", "Test Name", "M", 25, 150.0f, 5, 10, 1, "active", 8, 4);

        // Action
        String profileData = profiles_manager.getProfile("testUser");

        // Assert that the profile data contains expected information
        assertTrue(profileData.contains("Test Name"));
        assertTrue(profileData.contains("M"));
        assertTrue(profileData.contains("25"));
    }

    // Testing if the getProfile() method prints out the error message when the profile doesn't exist
    @Test
    void testGetProfileReturnsNoProfileFoundForNonExistingUser() {
        // //Arrange
        ProfilesManager profiles_manager = new ProfilesManager();

        // Execute
        String profileData = profiles_manager.getProfile("nonExistingUser");
        // Assert that the correct message is returned for a non-existing user
        assertEquals("No profile found for this user.", profileData);
    }

    // Testing if the addUser() method adds the user properly
    @Test
    void test_addUserSuccessful() {
        //Arrange to have an instance of CredentialsManager and to create a test user
        CredentialsManager credentialsManager_test = new CredentialsManager();
        String test_username = "TestUser";
        String test_password = "TestPassword";

        // Action, adding a test user
        boolean addUserResult = credentialsManager_test.addUser(test_username, test_password);

        // Asserting if the user's added via using validateUser()
        assertTrue(credentialsManager_test.validateUser(test_username, test_password));

    }

    //Testing if the validateUser returns true with correct credentials
    @Test
    void test_validateUserCorrectCredentials() {
        //Arrange to have an instance of CredentialsManager and to set test credentials
        CredentialsManager credentialsManager_test = new CredentialsManager();
        String test_username = "TestUser";
        String test_password = "TestPassword";

        //Asserting directly, no action required
        assertTrue(credentialsManager_test.validateUser(test_username, test_password));
    }

    //Testing if the validateUser returns false with wrong credentials
    @Test
    void test_validateUserIncorrectCredentials() {
        //Arrange to have an instance of CredentialsManager and to set test credentials
        CredentialsManager credentialsManager_test = new CredentialsManager();
        String test_username = "TestUser";
        String test_password = "TestPassword";

        //Asserting directly, no action required as the credentials exist from previous tests
        assertFalse(credentialsManager_test.validateUser(test_username, "weird_password"));
    }

    //Testing if the validateUser returns a false with non-existent user
    @Test
    void test_validateUserNonExistentUser() {
        //Arrange to have an instance of CredentialsManager but no need to set the credentials as user won't have them
        CredentialsManager credentialsManager_test = new CredentialsManager();

        //Asserting directly, no action required
        assertFalse(credentialsManager_test.validateUser("Non-existent", "a ghost"));
    }
//--------------------------------Cut off for Demo 3 tests, testing FXML Directories-----------------------------------

    //Testing if FXML files exist at their expected path.
    @Test
    void ensureLeaveWindowFXMLFileExists() {
    String path = "/com/example/LeaveTheAppWindow.fxml"; // It does not matter which file we choose for this testing
    URL resourceUrl = getClass().getResource(path);
    assertNotNull(resourceUrl, "LeaveTheAppWindow.fxml does not exist at the expected path: " + path);
}
}

