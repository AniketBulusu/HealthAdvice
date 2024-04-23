package com.example;

//Object Oriented calculateCalroieIntake.java
public class CalorieIntakeCalculator {
    private int age;
    private boolean isFemale;
    private String activityLevl;


    // Constructor for the CalorieIntakeCalculator class
    public CalorieIntakeCalculator (int age,boolean isFemale,String activityLevel) {
        this.age = age;
        this.isFemale = isFemale;
        this.activityLevl = activityLevel;
    }

    public String calculateCalorieIntake (boolean isFemale, int age, String activityLevel) {
        String calorieIntake = "";
        // This function takes into account the priority of gender then look at the age range then takes into account the activity level of the user. Once those conditions are met, a recommended calorie intake will be given!
        // We split it off into 2 big categories for male and female
        if (isFemale) {
            if (age >= 0 && age <=3) {
                if (activityLevel.equalsIgnoreCase("Sedentary")) {
                    calorieIntake = "1,000";
                    return("Your calories intake should be around " + calorieIntake);
                } else if (activityLevel.equalsIgnoreCase("Moderately Active")) {
                    calorieIntake = "1,000-1,400";
                    return("Your calories intake should be around " + calorieIntake);
                } else { // Active
                    calorieIntake = "1,000-1,400";
                    return("Your calories intake should be around " + calorieIntake);
                }
            }else if (age >= 4 && age <= 8) {
                if (activityLevel.equalsIgnoreCase("Sedentary")) {
                    calorieIntake = "1,200-1,400";
                    return("Your calories intake should be around " + calorieIntake);
                } else if (activityLevel.equalsIgnoreCase("Moderately Active")) {
                    calorieIntake = "1,400-1,600";
                    return("Your calories intake should be around " + calorieIntake);
                } else { // Active
                    calorieIntake = "1,600_1800";
                    return("Your calories intake should be around " + calorieIntake);
                }
            } else if (age >= 9 && age <= 13) {
                if (activityLevel.equalsIgnoreCase("Sedentary")) {
                    calorieIntake = "1,400-1,600";
                    return("Your calories intake should be around " + calorieIntake);
                } else if (activityLevel.equalsIgnoreCase("Moderately Active")) {
                    calorieIntake = "1,600-2,000";
                    return("Your calories intake should be around " + calorieIntake);
                } else { // Active
                    calorieIntake = "1,800-2,200";
                    return("Your calories intake should be around " + calorieIntake);
                }
            } else if (age >= 14 && age <= 18) {
                if (activityLevel.equalsIgnoreCase("Sedentary")) {
                    calorieIntake = "1,800";
                    return("Your calories intake should be around " + calorieIntake);
                } else if (activityLevel.equalsIgnoreCase("Moderately Active")) {
                    calorieIntake = "2,000";
                    return("Your calories intake should be around " + calorieIntake);
                } else { // Active
                    calorieIntake = "2,400";
                    return("Your calories intake should be around " + calorieIntake);
                }
            } else if (age >= 19 && age <= 30) {
                if (activityLevel.equalsIgnoreCase("Sedentary")) {
                    calorieIntake = "1,800-2,000";
                    return("Your calories intake should be around " + calorieIntake);
                } else if (activityLevel.equalsIgnoreCase("Moderately Active")) {
                    calorieIntake = "2,000-2,200";
                    return("Your calories intake should be around " + calorieIntake);
                } else { // Active
                    calorieIntake = "2,400";
                    return("Your calories intake should be around " + calorieIntake);
                }
            } else if (age >= 31 && age <= 50) {
                if (activityLevel.equalsIgnoreCase("Sedentary")) {
                    calorieIntake = "1,800";
                    return("Your calories intake should be around " + calorieIntake);
                } else if (activityLevel.equalsIgnoreCase("Moderately Active")) {
                    calorieIntake = "2,000";
                    return("Your calories intake should be around " + calorieIntake);
                } else { // Active
                    calorieIntake = "2,200";
                    return("Your calories intake should be around " + calorieIntake);
                }
            } else if (age >= 51) {
                if (activityLevel.equalsIgnoreCase("Sedentary")) {
                    calorieIntake = "1,600";
                    return("Your calories intake should be around " + calorieIntake);
                } else if (activityLevel.equalsIgnoreCase("Moderately Active")) {
                    calorieIntake = "1,800";
                    return("Your calories intake should be around " + calorieIntake);
                } else { // Active
                    calorieIntake = "2,200-2,200";
                    return("Your calories intake should be around " + calorieIntake);
                }
            }
        } else { // Male
            if (age >= 0 && age <=3) {
                if (activityLevel.equalsIgnoreCase("Sedentary")) {
                    calorieIntake = "1,000";
                    return("Your calories intake should be around " + calorieIntake);
                } else if (activityLevel.equalsIgnoreCase("Moderately Active")) {
                    calorieIntake = "1,000-1,400";
                    return("Your calories intake should be around " + calorieIntake);
                } else { // Active
                    calorieIntake = "1,000-1,400";
                    return("Your calories intake should be around " + calorieIntake);
                }
            }else if (age >= 4 && age <= 8) {
                if (activityLevel.equalsIgnoreCase("Sedentary")) {
                    calorieIntake = "1,200-1,400";
                    return("Your calories intake should be around " + calorieIntake);
                } else if (activityLevel.equalsIgnoreCase("Moderately Active")) {
                    calorieIntake = "1,400-1,600";
                    return("Your calories intake should be around " + calorieIntake);
                } else { // Active
                    calorieIntake = "1,600-2,000";
                    return("Your calories intake should be around " + calorieIntake);
                }
            } else if (age >= 9 && age <= 13) {
                if (activityLevel.equalsIgnoreCase("Sedentary")) {
                    calorieIntake = "1,600-2,000";
                    return("Your calories intake should be around " + calorieIntake);
                } else if (activityLevel.equalsIgnoreCase("Moderately Active")) {
                    calorieIntake = "1,800-2,200";
                    return("Your calories intake should be around " + calorieIntake);
                } else { // Active
                    calorieIntake = "2,000-2,600";
                    return("Your calories intake should be around " + calorieIntake);
                }
            } else if (age >= 14 && age <= 18) {
                if (activityLevel.equalsIgnoreCase("Sedentary")) {
                    calorieIntake = "2,000-2,400";
                    return("Your calories intake should be around " + calorieIntake);
                } else if (activityLevel.equalsIgnoreCase("Moderately Active")) {
                    calorieIntake = "2,400-2,800";
                    return("Your calories intake should be around " + calorieIntake);
                } else { // Active
                    calorieIntake = "2,800-3,200";
                    return("Your calories intake should be around " + calorieIntake);
                }
            } else if (age >= 19 && age <= 30) {
                if (activityLevel.equalsIgnoreCase("Sedentary")) {
                    calorieIntake = "2,400-2,600";
                    return("Your calories intake should be around " + calorieIntake);
                } else if (activityLevel.equalsIgnoreCase("Moderately Active")) {
                    calorieIntake = "2,600-2,800";
                    return("Your calories intake should be around " + calorieIntake);
                } else { // Active
                    calorieIntake = "3,000";
                    return("Your calories intake should be around " + calorieIntake);
                }
            } else if (age >= 31 && age <= 50) {
                if (activityLevel.equalsIgnoreCase("Sedentary")) {
                    calorieIntake = "2,200-2,400";
                    return("Your calories intake should be around " + calorieIntake);
                } else if (activityLevel.equalsIgnoreCase("Moderately Active")) {
                    calorieIntake = "2,400-2,600";
                    return("Your calories intake should be around " + calorieIntake);
                } else { // Active
                    calorieIntake = "2,800-3,000";
                    return("Your calories intake should be around " + calorieIntake);
                }
            } else if (age >= 51) {
                if (activityLevel.equalsIgnoreCase("Sedentary")) {
                    calorieIntake = "2,000-2,200";
                    return("Your calories intake should be around " + calorieIntake);
                } else if (activityLevel.equalsIgnoreCase("Moderately Active")) {
                    calorieIntake = "2,200-2,400";
                    return("Your calories intake should be around " + calorieIntake);
                } else { // Active
                    calorieIntake = "2,400-2,800";
                    return("Your calories intake should be around " + calorieIntake);
                }
            }
        }
        return calorieIntake;
    }
}
