package com.example;

public class BMI {
    private float weight;
    private int feet;
    private int inches;
    private int choice;

    /**
     * Creating a BMI function that we call in the general output summary as it's gives an initial indicator of their health
     * Based off the user's height and weight, that is how BMI is calculated with a relatively simple formula
     * @param weight - float - getting their weight
     * @param feet - int - getting their 1st parameter of height which is feet
     * @param inches - int - getting their 2nd parameter of height which is inches
     * @param choice - int - if their choice is '1', we don't have to make the conversion. If their choice is '2', we must make a conversion to lbs.
     */

    public BMI ( float weight, int feet, int inches, int choice) {
        this.weight = weight;
        this.feet = feet;
        this.inches = inches;
        this.choice=choice;

    }
    public void calculateBMI(float weight, int feet, int inches, int choice) {
        // Constants for BMI categories
        final double UNDERWEIGHT_THRESHOLD = 18.5;
        final double NORMAL_WEIGHT_LOWER_BOUND = 18.5;
        final double NORMAL_WEIGHT_UPPER_BOUND = 24.9;
        final double OVERWEIGHT_LOWER_BOUND = 25.0;
        final double OBESITY_LOWER_BOUND = 30.0;

        // Conversion factor for weight from kg to lbs
        final double KG_TO_LBS_CONVERSION_FACTOR = 2.20462;

        // Calculate BMI based on user's choice
        double bmi;

        if (choice == 1) {
            int totalHeightInInches = (feet * 12) + inches;
            bmi = (weight / Math.pow(totalHeightInInches, 2)) * 703;
        } else {
            double lbsWeight = weight * KG_TO_LBS_CONVERSION_FACTOR;
            int totalHeightInInches = (feet * 12) + inches;
            bmi = (lbsWeight / Math.pow(totalHeightInInches, 2)) * 703;
        }

        // Determine BMI category and print result
        if (bmi < UNDERWEIGHT_THRESHOLD) {
            System.out.println("You are severely underweight!");
        } else if (bmi >= NORMAL_WEIGHT_LOWER_BOUND && bmi <= NORMAL_WEIGHT_UPPER_BOUND) {
            System.out.println("You are considered a normal healthy weight!");
        } else if (bmi > OVERWEIGHT_LOWER_BOUND && bmi <= OBESITY_LOWER_BOUND) {
            System.out.println("You are overweight!");
        } else {
            System.out.println("You are severely overweight or obese!");
        }
    }



}



