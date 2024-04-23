package com.example;

import java.io.*;
import java.util.HashMap;

public class ProfilesManager {
    private HashMap<String, String> profiles = new HashMap<>();
    private final String ProfilesFile = "profiles.txt";



    /**
     * Constructor to load profiles from the profiles.txt file
     */
    public ProfilesManager(){
        loadProfiles();
    }



    /**
     * Load profiles from the profiles.txt file using a BufferedReader
     */
    private void loadProfiles(){
        try (BufferedReader reader = new BufferedReader(new FileReader(ProfilesFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    profiles.put(parts[0], parts[1].replace("|", "\n"));
                }
            }
        }catch (IOException e) {
            System.out.println("Error reading profiles file: " + e.getMessage());
        }
    }



    /**
     * Save profiles to the profiles.txt file for future use
     */
    public void saveProfiles() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ProfilesFile))){
            for (HashMap.Entry<String, String> entry : profiles.entrySet()){
                writer.write(entry.getKey() + ":" + entry.getValue().replace("\n", "|"));
                writer.newLine();
            }
        }catch (IOException e) {
            System.out.println("Error writing profiles file: " + e.getMessage());
        }
    }



    /**
     * Add or update a user profile to the profiles file with the user's input data
     * @param username - username of the user
     * @param name - name of the user
     * @param enteredSex  - M/F depending on user input
     * @param age - age of the user
     * @param weight - weight of the user
     * @param feet - height of the user in feet
     * @param inches - height of the user in inches
     * @param exerciseHours - hours of exercise per day
     * @param activityLevel - activity level of the user
     * @param sleep - hours of sleep per day
     * @param quality - quality of sleep
     */
    public void addOrUpdateProfile(String username, String name, String enteredSex, int age, float weight, int feet, int inches, int exerciseHours, String activityLevel, int sleep, int quality) {
        String profileData = String.format("Name: %s\nGender: %s\nAge: %d\nWeight(lbs or kgs depending on user input): %f\nHeight: %d feet and %d inches\nPhysical Activity: %d hours\nActivity Level: %s\nSleep Duration: %d hours\nSleep Quality: %d", name, enteredSex, age, weight, feet, inches, exerciseHours, activityLevel, sleep, quality);
        profiles.put(username, profileData);
        saveProfiles();
    }




    /**
     * Delete a user profile from the profiles.txt file
     * @param username - username of the user
     */
    public void deleteProfile(String username) {
        profiles.remove(username);
        saveProfiles();
    }



    /**
     * Get a user profile from the profiles.txt file
     * @param username - username of the user
     * @return - profile data of the user
     */
    public String getProfile(String username) {
        return profiles.getOrDefault(username, "No profile found for this user.");
    }


    public int getAge(String username){
        String profile = profiles.get(username);
        String[] profileTextLines = profile.split("\n");
        for (String line : profileTextLines) {
            if (line.startsWith("Age: ")) {
                String age = line.substring(5);
                int ageInt = Integer.parseInt(age);
                return ageInt;
            }
        }
        return 0;
    }


    public String getName(String username){
        String profile = profiles.get(username);
        if (profile != null) {
            String[] profileTextLines = profile.split("\n");
            for (String line : profileTextLines) {
                if (line.startsWith("Name: ")) {
                    return line.substring(6);
                }
            }
        }
        return null;
    }


    public float getWeight(String username){
        String profile = profiles.get(username);
        if (profile != null) {
            String[] profileTextLines = profile.split("\n");
            for (String line : profileTextLines) {
                String prefix = "Weight(lbs or kgs depending on user input): ";
                if (line.startsWith(prefix)) {
                    String weight = line.substring(prefix.length());
                    float weightFloat = Float.parseFloat(weight);
                    return weightFloat;
                }
            }
        }
        return 0;
    }


    public int getFeet(String username){
        String profile = profiles.get(username);
        if (profile != null) {
            String[] profileTextLines = profile.split("\n");
            for (String line : profileTextLines) {
                if (line.startsWith("Height: ")) {
                    String height = line.substring(8);
                    String[] heightParts = height.split(" feet and ");
                    return Integer.parseInt(heightParts[0]);
                }
            }
        }
        return 0;
    }

    public int getInches(String username){
        String profile = profiles.get(username);
        if (profile != null) {
            String[] profileTextLines = profile.split("\n");
            for (String line : profileTextLines) {
                if (line.startsWith("Height: ")) {
                    String height = line.substring(8);
                    String[] heightParts = height.split(" feet and ");
                    String[] inchesParts = heightParts[1].split(" inches");
                    return Integer.parseInt(inchesParts[0]);
                }
            }
        }
        return 0;
    }


    public int getExerciseHours(String username){
        String profile = profiles.get(username);
        if (profile != null) {
            String[] profileTextLines = profile.split("\n");
            for (String line : profileTextLines) {
                if (line.startsWith("Physical Activity: ")) {
                    String exerciseHours = line.substring("Physical Activity: ".length()).split(" ")[0]; // Split on space and take the first part
                    int exerciseHoursInt = Integer.parseInt(exerciseHours);
                    return exerciseHoursInt;
                }
            }
        }
        return 0;
    }

    public int getSleepHours(String username){
        String profile = profiles.get(username);
        if (profile != null) {
            String[] profileTextLines = profile.split("\n");
            for (String line : profileTextLines) {
                if (line.startsWith("Sleep Duration: ")) {
                    String sleepHours = line.substring("Sleep Duration: ".length()).split(" ")[0]; // Split on space and take the first part
                    int sleepHoursInt = Integer.parseInt(sleepHours);
                    return sleepHoursInt;
                }
            }
        }
        return 0;
    }


    public int getSleepQuality(String username){
        String profile = profiles.get(username);
        if (profile != null) {
            String[] profileTextLines = profile.split("\n");
            for (String line : profileTextLines) {
                if (line.startsWith("Sleep Quality: ")) {
                    String sleepQuality = line.substring(15);
                    int sleepQualityInt = Integer.parseInt(sleepQuality);
                    return sleepQualityInt;
                }
            }
        }
        return 0;
    }


    public String getActivityLevel (String username){
        String profile = profiles.get(username);
        if (profile != null) {
            String[] profileTextLines = profile.split("\n");
            for (String line : profileTextLines) {
                if (line.startsWith("Activity Level: ")) {
                    return line.substring(16);
                }
            }
        }
        return null;
    }

    public String getGender (String username){
        String profile = profiles.get(username);
        if (profile != null) {
            String[] profileTextLines = profile.split("\n");
            for (String line : profileTextLines) {
                if (line.startsWith("Gender: ")) {
                    return line.substring(8);
                }
            }
        }
        return null;
    }
}


