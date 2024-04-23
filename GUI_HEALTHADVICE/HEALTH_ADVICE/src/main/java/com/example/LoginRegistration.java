package com.example;

import java.util.Scanner;


public class LoginRegistration {


    private ProfilesManager profileManager;
    public CredentialsManager credentialManager;
    private UserInput userInput;


    public LoginRegistration() {
        this.userInput = new UserInput();
        this.profileManager = new ProfilesManager();
        this.credentialManager = new CredentialsManager();
    }

    public String login() {
        boolean goodLogin = false;

        while(goodLogin == false){
            Scanner scanner = new Scanner(System.in);
            Scanner chooseExit = new Scanner(System.in);

            System.out.println("Enter your username:");
            String username = scanner.nextLine();

            System.out.println("Enter your password:");
            String password = scanner.nextLine();

            if (credentialManager.validateUser(username, password)) {
                System.out.println("Login successful!");
                goodLogin = true;

                // Fetch and display user profile data
                String profileData = profileManager.getProfile(username);
                System.out.println("\nHere is a display of your data:\n------------------------------------------------------------\n" + profileData);
                return username;

            } else {
                System.out.println("Login failed. Incorrect username or password. Press 'Enter' to try again, or type 'E' to exit the app."); // Prompt user to try again or exit in case of incorrect login
                String choice = chooseExit.nextLine();
                if(choice.equalsIgnoreCase("E")){
                    System.out.println("Thank you for using our app! A little progress each day adds up to big results!");
                    System.exit(0);
                }
            }
        }
        return null;

    }

    public void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username:");
        String username = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        CredentialsManager credentialManager = new CredentialsManager(); // Initialize the credentialManager variable

        if (credentialManager.addUser(username, password)) {
            System.out.println("Registration Successful!");
        } else {
            System.out.println("Username already exists. Please choose a different username.");
        }

    }

    

       
    private String formatProfileData(){
            return String.format("Name: %s\nGender: %s\nAge: %d\nWeight: %.2f\nHeight: %d feet %d inches\nExercise Hours: %d\nActivity Level: %s\nSleep Hours: %d\nSleep Quality: %d\n", userInput.getName(), userInput.getEnteredSex(), userInput.getAge(), userInput.getWeight(), userInput.getFeet(), userInput.getInches(), userInput.getExerciseHours(), userInput.getActivityLevel(), userInput.getSleep(), userInput.getQuality());
    }


        /**
         * Creates a new profile for the user by asking for user input and storing it in the profiles file
         * @param username - username of the user
         */
    public void createProfile(String username) {
        this.userInput.getUserInput();
        String profileData = formatProfileData();
        profileManager.addOrUpdateProfile(username,userInput.getName(), userInput.getEnteredSex(), userInput.getAge(), userInput.getWeight(), userInput.getFeet(), userInput.getInches(), userInput.getExerciseHours(), userInput.getActivityLevel(), userInput.getSleep(), userInput.getQuality());
        System.out.println("\nProfile created successfully!");
    }
}
