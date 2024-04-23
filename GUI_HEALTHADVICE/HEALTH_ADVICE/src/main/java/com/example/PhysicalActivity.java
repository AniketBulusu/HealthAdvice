package com.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PhysicalActivity {
    ProfilesManager profilesManager = new ProfilesManager();
    private UserData userData = UserData.getInstance();
    String username = userData.getUsername();

    private int age;
    private int exerciseHours;
    private String activityLevel;


    private static final HashMap<String, List<String>> EXERCISE_RECOMMENDATIONS = new HashMap<String, List<String>>() {{
        put("sedentary", Arrays.asList(
                "\nBased on your activity level,\nyou may benefit from starting with\nlight activities such as:",
                "\n- Walking at a comfortable pace",
                "\n- Gentle stretching exercises",
                "\n- Short sessions of low-impact exercises like swimming or cycling",
                "\n- Gradually increasing activity over time as you become more comfortable and confident\n",
                "\nOnce you feel you are ready to receive\nrecommendations for a higher activity level,\nrun the app again for new tailored advice!\n"
        ));

        put("moderate", Arrays.asList(
                "\nFor a moderately active lifestyle,\nconsider incorporating activities\nsuch as:",
                "\n- Brisk walking or jogging",
                "\n- Dancing",
                "\n- Swimming or water sports",
                "\n- Cycling",
                "\n- Participating in group fitness classes",
                "\n- Aim to maintain this level of activity\nconsistently for health benefits\n"
        ));

        put("active", Arrays.asList(
                "\nWith your current active lifestyle,\nyou can engage in more vigorous activities\nsuch as:",
                "\n- Running or swimming",
                "\n- High-intensity interval training (HIIT)",
                "\n- Jumping rope",
                "\n- Competitive sports like basketball, soccer, or tennis",
                "\n- Aim for at least 75 minutes of\nvigorous-intensity activity per week\nfor substantial health benefits\n"
        ));

        put("child", Arrays.asList(
                "\nMy recommendations for your physical\nactivity at your current age range\n(1-4) are:",
                "\n- Include Energetic play (any intensity)",
                "\n- Have fun playing movement-based\ngames such as Tag or Playing at parks",
                "\n- Spread out exercise throughout the day to reduce periods of sitting/sedentary behavior",
                "\n- Prioritize rest alongside exercise to keep you physically strong and support growth\n"
        ));

        put("senior", Arrays.asList(
                "\nMy recommendations for your physical activity\nat your current age range (65+) are:",
                "\n- Include moderate-vigorous effort exercise\ninto your routine. This can include:",
                "\n- Participating in a low-impact\nrecreational sport like Pickleball, Golf or Bowling" ,
                "\n- Make time for light cardio activities\nthat support mobility and heart health such as\nSwimming, Hiking or Group exercise classes (Spin, Yoga, etc)",
                "\n- Controlled bodyweight or machine-based\nstrength training such as\nCable exercises, balance exercises or stretching",
                "\n- Spend several hours doing\nlight movement throughout the day,\neven better if outside",
                "\n- Split up periods of sitting/sedentary\nbehavior with exercise"
        ));
    }};

    public PhysicalActivity(String activityLevel, int exerciseHours, int age) {
        this.activityLevel = activityLevel;
        this.exerciseHours = exerciseHours;
        this.age = age;
    }

    public String generateRecommendations() {
        StringBuilder recommendations = new StringBuilder();
        int age = profilesManager.getAge(username);
        String activityLevel = profilesManager.getActivityLevel(username);

        // Add age-based recommendations
        if (age >= 1 && age <= 4) {
            EXERCISE_RECOMMENDATIONS.get("child").forEach(recommendations::append);
        } else if (age >= 65) {
            EXERCISE_RECOMMENDATIONS.get("senior").forEach(recommendations::append);
        }

        // Add activity-level-based recommendations
        if ("sedentary".equalsIgnoreCase(activityLevel)) {
            EXERCISE_RECOMMENDATIONS.get("sedentary").forEach(recommendations::append);
        } else if ("moderate".equalsIgnoreCase(activityLevel)) {
            EXERCISE_RECOMMENDATIONS.get("moderate").forEach(recommendations::append);
        } else if ("active".equalsIgnoreCase(activityLevel)) {
            EXERCISE_RECOMMENDATIONS.get("active").forEach(recommendations::append);
        }

        return recommendations.toString();
    }
}