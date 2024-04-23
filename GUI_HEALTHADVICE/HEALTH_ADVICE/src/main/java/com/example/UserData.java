package com.example;

public class UserData {
    private static UserData instance;
    private String username;
    private String gender;
    private String name;
    private String age;
    private String weight;
    private String feet;
    private String inches;
    private String activityLevel;
    private String hoursOfSleep;
    private String sleepQuality;
    private String hoursOfExercise;

    public static UserData getInstance() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getFeet() {
        return feet;
    }

    public void setFeet(String feet) {
        this.feet = feet;
    }

    public String getInches() {
        return inches;
    }

    public void setInches(String inches) {
        this.inches = inches;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getHoursOfSleep() {
        return hoursOfSleep;
    }

    public void setHoursOfSleep(String hoursOfSleep) {
        this.hoursOfSleep = hoursOfSleep;
    }

    public String getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(String sleepQuality) {
        this.sleepQuality = sleepQuality;
    }

    public String getHoursOfExercise() {
        return hoursOfExercise;
    }

    public void setHoursOfExercise(String hoursOfExercise) {
        this.hoursOfExercise = hoursOfExercise;
        }
}

