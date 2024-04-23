package com.example;

public class Sleep {

    private int age;
    private int hours;
    private int quality;


    /**
     * Constructor to initialize the age, hours, and quality of sleep
     *
     * @param age     - age of the user
     * @param hours   - hours of sleep per day
     * @param quality - quality of sleep
     */
    public Sleep(int age, int hours, int quality) {
        this.age = age;
        this.hours = hours;
        this.quality = quality;
    }


    /**
     * Provide sleep advice based on the age, hours, and quality of sleep
     */
    public String provideSleepAdviceHour() {

        // Recommended hours of sleep based on the age of the user
        int recommendedSleepHours = (age >= 18 && age <= 64) ? 7 : (age > 64 ? 7 : 8);

        // Display advice based on sleep duration
        if (hours < recommendedSleepHours) {
            return ("It seems like you're not getting enough sleep\nfor your age group. Try to\ngo to bed earlier and create a relaxing bedtime routine\nto improve your sleep duration.\nIf you're concerned about your lack of sleep,\nseek the advice of a doctor to find a remedy for it.");
        } else if (hours > recommendedSleepHours + 2) {
            return ("You might be getting more sleep\nthan necessary for your age group.\nToo much sleep can also have negative\neffects on your health.\nTry to stick to a consistent sleep schedule\nand aim for the recommended amount of sleep.");
        } else {
            return ("Great!\nYou're getting an adequate amount\n of sleep for your age group.\nKeep up the good work and \nmaintain a consistent sleep schedule.");
        }
    }

    public String provideSleepAdviceQuality() {

        // Display advice based on sleep quality
        if (quality <= 2) {
            return ("\nYour sleep quality rating is especially low.\nConsider visiting a licensed physician and setting an\nappointment with your doctor to discuss your sleep habits.\nConsider factors such as your sleep environment,\nuse of electronic devices before bed, and stress levels.\nImproving these can enhance your sleep quality.");
        } else if (quality > 2 && quality <= 4) {
            return ("\nYour sleep quality rating is quite low.\nConsider factors such as your sleep environment,\nuse of electronic devices before bed, and stress levels.\nImproving these can enhance your sleep quality.");
        } else if (quality > 4 && quality <= 6) {
            return ("\nYour sleep quality rating is average.\nThere may be room for improvement.\nPay attention to factors that might be affecting\nyour sleep and try to address them.");
        } else if (quality > 6 && quality <= 8) {
            return ("\nYour sleep quality rating is quite good.\nThere may be room for improvement.\nContinue practicing good sleep hygiene\nto maintain this level of sleep quality.");
        } else {
            return ("\nYour sleep quality rating is excellent!\nContinue practicing good sleep hygiene\nto maintain this high level of sleep quality.");
        }
    }
}
