package com.example;

import java.util.Scanner;
/**
 * The {@code UserInput} class is responsible for collecting health-related data from a user.
 * It uses console input to gather information such as name, sex, age, weight, height,
 * exercise hours, activity level, sleep habits, and sleep quality.
 *
 * The collected data can be retrieved via getter methods after the {@code getUserInput}
 * method has been called to populate the fields with user-provided values.
 */
public class UserInput {
    public String name;
    public String enteredSex;
    public int age;
    public float weight;
    public int feet;
    public int inches;
    public int exerciseHours;
    public String activityLevel;
    public int sleep;
    public int quality;
    public int choice;
    public boolean isFemale = false;
    static final String YES = "Y";
    static final String NO = "N";
    static final String MALE = "M";
    static final String FEMALE = "F";


    public void getUserInput()  {

        //Initialize the variables used in the main function

        Scanner userInput = new Scanner(System.in);

        System.out.println("------------------------------------------------------");
        System.out.println("Hello, welcome to the Health Advice app!\nI look forward to offering some help in your health area of choice.\nYour drive to stay healthy is inspirational, so let's get started!");
        System.out.println("------------------------------------------------------");

        System.out.println("In order to receive the most accurate advice,\nstart by entering your biological sex. (M = Male, F = Female)");
        this.enteredSex = userInput.nextLine().toUpperCase();

        // While loop initiates the user's biological sex selection, sets a variable for more targeted advice throughout the program
        while (!(enteredSex.equalsIgnoreCase(MALE) || enteredSex.equalsIgnoreCase(FEMALE))) {
            System.out.println("Error: Please enter a valid option (M = Male, F = Female)");
            enteredSex = userInput.nextLine().toUpperCase();
        }
        if(enteredSex == FEMALE){
            isFemale = true;
        }



        // After we get the biological sex, we can start getting their age, weight, height, sleep, physical activity.
        System.out.println("What is your name?");
        name = userInput.nextLine();
        while(name.isEmpty() || name.matches(".*\\d.*")) {  // Make sure that the name is not empty and it doesn't contain any form of numbers
            System.out.println("Invalid Input! Please enter a name that has more than 0 characters");
            name = userInput.nextLine();
        }
        // Let's start off with age
        System.out.println("Enter your age (1-75 yrs)");
        age = userInput.nextInt();
        while (age > 75 || age < 0) { // Initiate a while loop
            System.out.println("Error: Your age does not fit within the allowed range. Please enter an age from 1-75");
            age = userInput.nextInt();
        }


        // Next, let's do weight
        System.out.println("What is your preferred unit system for measuring your weight? Please enter '1' for lbs or '2' for kgs");
        int choice = userInput.nextInt();


        // Make sure the user input selects either choice 1 or choice 2. This is very important for us because if they select choice 1, we must convert it to kgs for the BMI calculation
        while ((choice != 1 && choice != 2)) {
            System.out.println("Error: Please enter a valid choice! ('1' for lbs, '2' for kgs)");
            choice = userInput.nextInt();
        }

        if (choice == 1) {
            System.out.println("What is your weight in lbs?");
            weight = userInput.nextFloat();                     // User gets two choices if they don't know their weight in either unit
        } else {
            System.out.println("What is your weight in kgs?");
            weight = userInput.nextFloat();
        }

        // Make sure weight is not 0
        while (weight <= 0) {
            System.out.println("Error: Invalid input. Please enter a weight greater than 0!");
            weight = userInput.nextFloat();
        }


        // Let's get height
        System.out.println("Now we will get your height in feet and inches");
        System.out.println("Feet: ");
        feet = userInput.nextInt();
        System.out.println("Inches: ");
        inches = userInput.nextInt();
        while (feet <= 0 || inches >= 13) { // User height is always (positive "+") so we must take care of this case as well as inches have to be 12 or under as ( 5ft, 0 inches is a valid height)
            System.out.println("Height is not valid");
            System.out.println("Feet: ");
            feet = userInput.nextInt();
            System.out.println("Inches: ");
            inches = userInput.nextInt();
        }


        //Let's get user's active level
        System.out.println("To get how much Calories you need daily, how would you describe your activity level (Sedentary, Moderately Active, Active): ");
        userInput.nextLine();
        activityLevel = userInput.nextLine().toUpperCase();
        while (!activityLevel.equalsIgnoreCase("Sedentary") && !activityLevel.equalsIgnoreCase("Moderately Active") && !activityLevel.equalsIgnoreCase("Active")) {
            System.out.println("Error: Invalid input! Please enter a valid activity level (Sedentary, Moderately Active, Active).");
            activityLevel = userInput.nextLine().toUpperCase();
        }


        // Let's get sleep. prompts the user for an initial input about their sleep habits and stores them as variables.
        System.out.println("How many hours of sleep do you get on average per night?"); // Convert user's response to uppercase so it removes the ambiguity of the user entering lowercase/uppercase letters
        sleep = userInput.nextInt();
        while (sleep < 1 && sleep > 24) {
            System.out.println("Please enter a number greater than '0' and less than '24' or invalid input");
            sleep = userInput.nextInt();
            int hours = sleep;

        }
        // Let's get sleep quality
        System.out.println("How would you rate your sleep quality? (1-10)");
        quality = userInput.nextInt();
        while (quality < 1 || quality > 10) { // Use a standard rating system from 1-10, checks for invalid inputs as well.
            System.out.println("Invalid input! Please enter a number between 1 and 10 to rate your sleep quality");
            quality = userInput.nextInt();
        }


        System.out.println("How many hours of exercise do you get per day on average?");
        exerciseHours = userInput.nextInt();
        while (exerciseHours < 0 || exerciseHours > 24) { // Although it's impractical for a user to get 24 hours of exercise every day, it is still technically correct to include it in the condition
            System.out.println("Error: Invalid hours of exercise. Please enter a valid daily hour value (0-24) ");
            exerciseHours = userInput.nextInt();
        }


    }
    public String getName() { return name; }
    public String getEnteredSex() { return enteredSex; }
    public int getAge() { return age; }
    public float getWeight() { return weight; }
    public int getFeet() { return feet; }
    public int getInches() { return inches; }
    public int getExerciseHours() { return exerciseHours; }
    public String getActivityLevel() { return activityLevel; }
    public int getSleep() { return sleep; }
    public int getQuality() { return quality; }
    public int getChoice() { return choice; }
    public Boolean getisFemale() { return isFemale; }

}

