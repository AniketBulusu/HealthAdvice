package com.example;

import java.util.Scanner;


public class MainRunner {

    /**
     * Main method to run the app, initialize the login/registration, profiles manager, and health advice classes
     * @param args - static main starting point
     * 
     */
    public static void main(String[] args) throws InterruptedException {

        Scanner loginChoice = new Scanner(System.in);
        Scanner useUserData = new Scanner(System.in);

        LoginRegistration loginRegistration = new LoginRegistration();
        ProfilesManager profilesManager = new ProfilesManager();
        CredentialsManager credentialManager = new CredentialsManager();
        

        boolean loginSelect = false;


        System.out.println("Welcome to the Health Advice App");
        System.out.println("------------------------------------------------------------");

        // Loop to keep asking for login/registration until user selects to exit, or runs through the health advice app
        while (loginSelect == false) {

            System.out.println("Please enter '1' for Registration, '2' for Login, or '3' to Exit the program.");
            String loginPath = loginChoice.nextLine();

            if (loginPath.equals("1")) { // Registers user under profiles.txt and credentials.txt
                loginRegistration.register();
                
            }
            else if (loginPath.equals("2")) { // Logs in existing user and runs the health advice app
                String username = loginRegistration.login();
                System.out.println("Is your user data correct? If yes (type 'Y'), If no (type 'N') to re-enter, and if you would like to exit (type 'E')");
                String existingData = useUserData.nextLine();
                while(loginSelect == false){
                    if(existingData.equalsIgnoreCase("Y")) {


                        boolean isFemale = false;



                        Scanner existingScanner = new Scanner(System.in);

                        int age = profilesManager.getAge(username);
                        String name = profilesManager.getName(username);
                        float weight = profilesManager.getWeight(username);
                        int feet = profilesManager.getFeet(username);
                        int inches = profilesManager.getInches(username);
                        int exerciseHours = profilesManager.getExerciseHours(username);
                        int sleep = profilesManager.getSleepHours(username);
                        int quality = profilesManager.getSleepQuality(username);
                        String enteredSex = profilesManager.getGender(username);
                        String activityLevel = profilesManager.getActivityLevel(username);
                        int weightSystemChoice = 0;


                        if (enteredSex.equalsIgnoreCase("f")){
                            isFemale = true;
                        }

                        //System.out.println(age); // DEGUB STATEMENT 
                        //System.out.println(name); // DEGUB STATEMENT
                        //System.out.println(weight); // DEGUB STATEMENT
                        //System.out.println(feet); // DEGUB STATEMENT
                        //System.out.println(inches); // DEGUB STATEMENT
                        //System.out.println(exerciseHours); // DEGUB STATEMENT
                        //System.out.println(sleep); // DEGUB STATEMENT
                        //System.out.println(quality); // DEGUB STATEMENT
                        //System.out.println(enteredSex); // DEGUB STATEMENT
                        //System.out.println(activityLevel); // DEGUB STATEMENT
                        //System.out.println(isFemale); // DEGUB STATEMENT


                        loginSelect = true;
                        Healthadvice.adviceOutput(name, enteredSex, age, weight, feet, inches, exerciseHours, activityLevel, sleep, quality, weightSystemChoice, isFemale);

                        break;
                    }
                    else if (existingData.equalsIgnoreCase("N")){
                        Healthadvice.runAdvice();
                        break;
                    }
                    else if (existingData.equalsIgnoreCase("E")) {
                        System.out.println("Thank you for using our app! A little progress each day adds up to big results!");
                        System.exit(0);
                    }
                    else {
                        System.out.println("Invalid choice. If your data is correct, Please enter 'Y' . If no, Please enter 'N' to re-enter details, and finally 'E' if you want to exit the app!");
                        loginPath = loginChoice.nextLine();
                    }
                }

            } else if (loginPath.equals("3")) {
                System.out.println("Exiting the App! A little progress each day adds up to big results!");
                break;
            }
        }
    }
}



