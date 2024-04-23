package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.io.IOException;

public class DataVerifyController {

    private UserData userData = UserData.getInstance();

    // FXML labels and buttons
    @FXML
    public Label lblSummary;

    @FXML
    public Label lblName;

    @FXML
    public Label lblSex;

    @FXML
    public Label lblAge;

    @FXML
    public Label lblWeight;

    @FXML
    public Label lblHeight;

    @FXML
    public Label lblExercise;

    @FXML
    public Label lblActivityLevel;

    @FXML
    public Label lblSleep;

    @FXML
    public Label lblQuality;

    @FXML
    private Button btnImproveHealth;

    @FXML
    private Button btnExit;




    /**
     * Switches to the DataVerifywindow which outputs a summary of the user's existing profile data, pulling from profiles.txt
     * @param event - the event that triggers the switch
     * @param userData - the user's data from a previous registration
     * @throws IOException - if the file is not found
     */
    @FXML
    public void switchToDataVerify(ActionEvent event, UserData userData) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataVerifyWindow.fxml"));
        Parent root = loader.load();
        DataVerifyController controller = loader.getController();

        ProfilesManager profilesManager = new ProfilesManager();
        String username = userData.getUsername();

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

        controller.updateLabels(name, enteredSex, age, weight, feet, inches, exerciseHours, activityLevel, sleep, quality);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void switchToAdviceMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuScene.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void exitApp() {
        System.exit(0);
    }

    // Setter for the UserData object
    public void setUserData(UserData userData) {
        this.userData = userData;
    }


    /**
     * Updates the labels in the DataVerifyWindow with the user's profile data
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
     */
    public void updateLabels(String name, String enteredSex, int age, float weight, int feet, int inches, int exerciseHours, String activityLevel, int sleep, int quality) {
        lblName.setText("Your name is: " + name);
        lblSex.setText("Your gender is: " + enteredSex);
        lblAge.setText("Your age is: " + age);
        lblWeight.setText("Your weight is: " + weight + "kgs");
        lblHeight.setText("Your height is: " + feet + "'" + inches + "\"");
        lblExercise.setText("The amount of physical activity you get is: " + exerciseHours + " hours");
        lblActivityLevel.setText("Your activity level is: " + activityLevel);
        lblSleep.setText("The amount of sleep you get is: " + sleep + " hours");
        lblQuality.setText("The quality of sleep you get is: " + quality + "/10");
    }

}

