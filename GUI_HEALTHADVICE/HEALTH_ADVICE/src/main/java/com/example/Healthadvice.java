package com.example;
import java.util.Scanner;
import java.io.*;
public class Healthadvice {



        // Call this in MAIN() method and DETAILED SUMMARY OUTPUT
        static final String YES = "Y";
        static final String NO = "N";
        static final String MALE = "M";
        static final String FEMALE = "F";

        private static final String FILE_PATH = "User_data.txt";

        /**
         * Runs the advice portion of the app, asks for user input, and provides advice based on the user's input
         */
        public static void runAdvice() {

            //running UserInput.java and return the user's input
            UserInput userInputApp = new UserInput();
            userInputApp.getUserInput();
            String name = userInputApp.getName();
            String enteredSex = userInputApp.getEnteredSex();
            int age = userInputApp.getAge();
            float weight = userInputApp.getWeight();
            int feet = userInputApp.getFeet();
            int inches = userInputApp.getInches();
            int exerciseHours = userInputApp.getExerciseHours();
            String activityLevel = userInputApp.getActivityLevel();
            int sleep = userInputApp.getSleep();
            int quality = userInputApp.getQuality();
            int choice = userInputApp.getChoice();
            boolean isFemale = userInputApp.getisFemale();
            adviceOutput(name, enteredSex, age, weight, feet, inches, exerciseHours, activityLevel, sleep, quality, choice, isFemale);

        }

        /**
         * Outputs the general summary of the user's health and provides advice based on the user's input
         * @param name
         * @param enteredSex
         * @param age
         * @param weight
         * @param feet
         * @param inches
         * @param exerciseHours
         * @param activityLevel
         * @param sleep
         * @param quality
         * @param choice
         * @param isFemale
         */
        public static void adviceOutput(String name, String enteredSex, int age, float weight, int feet, int inches, int exerciseHours, String activityLevel, int sleep, int quality, int choice, boolean isFemale){

            // Ask user if they want to use pounds or kilograms
            String chosenWeightSystem;
            Scanner userInput = new Scanner(System.in);

            System.out.println("\nWelcome to the Health Tracker App! We will provide you with a general summary of your existing health and provide advice on how to improve your health further!");
            System.out.println("\nWould you like to assess using kilograms or pounds? Please enter '1' for pounds or '2' for kilograms: ");
            choice = userInput.nextInt();

            while (choice != 1 && choice != 2) {
                System.out.println("Error: Please enter a valid option (1 = lbs, 2 = kgs)");
                choice = userInput.nextInt();
            }

            if (choice == 1) {
                System.out.println("You have selected pounds as your weight unit.");
                chosenWeightSystem = "lbs";
            } else {
                System.out.println("You have selected kilograms as your weight unit.");
                chosenWeightSystem = "kgs";
            }




            // General Output Summary
            // This output will include/restate the following: Sex, age, weight, height, physical activity, sleep, and lastly BMI
            System.out.println("\n\nHere is a general summary of your health");
            System.out.println("Your name is " + name );
            System.out.println("Your gender is " + enteredSex);
            System.out.println("Your age is " + age);
            System.out.println("Your weight is " + weight + chosenWeightSystem); // fixed issue where output was amiguous
            System.out.println("Your height is " + feet + " feet and " + inches + " inches");
            System.out.println("The amount of physical activity you get is/are " + exerciseHours + " hour(s)");
            System.out.println("Your activity level is " + activityLevel);
            System.out.println("The amount of sleep you get is/are " + sleep + " hour(s)");
            System.out.println("The quality of sleep you get is/are " + quality);
            BMI bmiAdvice = new BMI(weight, feet, inches, choice);
            bmiAdvice.calculateBMI(weight, feet, inches, choice); // Call BMI function || ** Exclude BMI from the file as that is a calculated value and not an input
            writeToFile(name, enteredSex, age, weight, feet, inches, exerciseHours, activityLevel, sleep, quality); // Call the WritetoFile function displaying all the print statements from the general output summary

            Scanner outputScanner = new Scanner(System.in);
            // Detailed Output
            System.out.println("Would you like to see how you could improve your health? If yes, please enter 'Y'. If no, please enter 'N' ");
            String check_advice = outputScanner.nextLine().toUpperCase(); // taking into account of the ambiguity of lowercase or uppercase letters
            while (!(check_advice.equalsIgnoreCase(YES) || check_advice.equalsIgnoreCase(NO))) {
                System.out.println("Error: Please enter a valid option (Y = Yes, N = No)");
                check_advice = userInput.nextLine().toUpperCase();
            }

            if (check_advice.equalsIgnoreCase(YES)) {

                System.out.println(" \nPlease select an area you want advice about:\n");
                System.out.println("(1) Receive advice related to PHYSICAL ACTIVITY\n" + "(2) Receive advice related to CALORIE INTAKE" + "\n" + "(3) Receive advice related to SLEEP" + "\n" + " ^ Enter one of the above numbers to\nselect the corresponding option ^");
                Scanner displaySelect = new Scanner(System.in);        
                String menuSelection = displaySelect.nextLine(); // Give user a selection of the three choices

                if (menuSelection.equalsIgnoreCase("1")) {
                    PhysicalActivity physicalActivity = new PhysicalActivity(activityLevel, exerciseHours, age);
                    physicalActivity.generateRecommendations();
                } else if (menuSelection.equalsIgnoreCase("2")) {
                    // First one is the class and the second one is the function
                    CalorieIntakeCalculator calculator = new CalorieIntakeCalculator(age,isFemale,activityLevel);
                    String calorieAdvice = calculator.calculateCalorieIntake(isFemale,age,activityLevel);

                } else if (menuSelection.equalsIgnoreCase("3")) {
                    Sleep sleepAdvice = new Sleep(age, sleep, quality);
                    sleepAdvice.provideSleepAdviceHour();
                    sleepAdvice.provideSleepAdviceQuality();

                } else {
                    System.out.println("Error: Please enter a menuSelection number (1-3)");
                }

                while (check_advice.equalsIgnoreCase(YES)) {
                    System.out.println("Would you like to see another option ((1) = Physical Activity, (2) = Calorie Intake, (3) = Sleep)) or leave the app(type 'N').");
                    menuSelection = displaySelect.nextLine();
                    if (menuSelection.equalsIgnoreCase("1")) {
                        PhysicalActivity physicalActivity = new PhysicalActivity(activityLevel, exerciseHours, age);
                        physicalActivity.generateRecommendations();
                    } else if (menuSelection.equalsIgnoreCase("2")) {
                        CalorieIntakeCalculator calculator = new CalorieIntakeCalculator(age,isFemale,activityLevel);
                        String calorieAdvice = calculator.calculateCalorieIntake(isFemale,age,activityLevel);


                    } else if (menuSelection.equalsIgnoreCase("3")) { // Call respective sleep quality function
                        Sleep sleepAdvice = new Sleep(age, sleep, quality);
                        sleepAdvice.provideSleepAdviceHour();
                        sleepAdvice.provideSleepAdviceQuality();

                    } else if (menuSelection.equalsIgnoreCase(NO)) {
                        check_advice = NO;
                        break;

                    } else {
                        System.out.println("Error: Please enter a number selection (1-3) or 'N' to leave the app.");
                    }

                }
            }



            if (check_advice.equalsIgnoreCase(NO)) {
                System.out.println("\nThank you for using our Health Tracker App. We hope that we guided you in the correct direction to achieve a better health lifestyle. Take care!");
                System.exit(0);

            }



        }
        //**************************** CUTOFF POINT FOR THE MAIN FUNCTION **********************************************

        // Create functions as we can call them into the Menu-Selection, General and Detailed Output Summary Cases






        // Pass in all the inputs that we got from our main into the WriteFile Function
        private static void writeToFile(String name, String enteredSex, int age, float weight, int feet, int inches, int exerciseHours, String activityLevel, int sleep, int quality) {
            try {
                FileWriter writer = new FileWriter(FILE_PATH, true); // The true parameter appends to the existing file
                writer.write("Name: " + name + "\n");
                writer.write("Gender: " + enteredSex + "\n");
                writer.write("Age: " + age + "\n");
                writer.write("Weight(lbs or kgs depending on user input): " + weight + "\n");
                writer.write("Height: " + feet + " feet and " + inches + " inches\n");
                writer.write("Physical Activity: " + exerciseHours + " hours\n");
                writer.write("Activity Level: " + activityLevel + "\n");
                writer.write("Sleep Duration: " + sleep + " hours\n");
                writer.write("Sleep Quality: " + quality + "\n");
                writer.write("------------------------------\n");
                writer.close(); // Once writing all the user inputs, it's important to close the writer action
                System.out.println("Data written to " + FILE_PATH);
            } catch (IOException e) { // In case there was an error in user input
                System.out.println("An error occurred while writing to the file " + e.getMessage());
            }
        }


    }

