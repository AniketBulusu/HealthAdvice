package com.example;

import java.io.IOException;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;


public class SceneController {

    private Stage stage;


    private Scene scene;
    private Parent root;
    private UserData userData = UserData.getInstance();
    private Sleep sleepData;

    private CalorieIntakeCalculator CalorieData;

    private PhysicalActivity PhysicalActivityData;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField genderField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField weightField;

    @FXML
    private TextField feetField;

    @FXML
    private TextField inchesField;

    @FXML
    private TextField activityLevelField;

    @FXML
    private TextField hoursOfSleepField;

    @FXML
    private TextField sleepQualityField;

    @FXML
    private TextField hoursOfExerciseField;

    @FXML
    Label CaloriesIntakeDisplay;

    @FXML
    Label PhysicalActivityDisplay;

    @FXML
    Label SleepDisplay;
    @FXML
    Label loginFailed;


    private CredentialsManager credentialManager = new CredentialsManager();



    public SceneController() {
        this.credentialManager = new CredentialsManager();
    }


    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene1.fxml")));
        System.out.println("Username set in userData initial: " + userData.getUsername());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
        Parent root = loader.load();
        SceneController Controller = loader.getController();
        // Pass the existing userData object to the registration controller
        Controller.setUserData(userData);
        //System.out.println("Username set in userData Login: " + userData.getUsername());  DEBUG
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRegistrationWindow(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistrationWindow.fxml"));
        Parent root = loader.load();
        SceneController Controller = loader.getController();

        // Pass the existing userData object to the registration controller
        Controller.setUserData(userData);
        System.out.println("Username set in userData Registrationwindow: " + userData.getUsername());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToExit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LeaveTheAppWindow.fxml"));
        Parent root = loader.load();
        SceneController Controller = loader.getController();

        // Pass the existing userData object to the registration controller
        Controller.setUserData(userData);
        System.out.println("Username set in userData Exit: " + userData.getUsername());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * exitApp is called when the user presses the 'Exit App' button on the exit message screen, will close the application.
     * @param event
     */
    public void exitApp(ActionEvent event) {
        System.exit(0);
    }




    /**
     * switchToUserInput is called when the user presses the 'Submit' button on the registration screen.
     * Allows user to set their data for the profile they can later use in the login.
     * @param event
     * @throws IOException
     */
    public void switchToUserInput(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GetUserInputAfterRegistrationWindow.fxml"));
        Parent root = loader.load();

        // get the controller for the next scene
        SceneController controller = loader.getController();

        // pass the userData object to the next scene
        controller.setUserData(this.userData);
        System.out.println("Username set in userData Userinputafterreg: " + userData.getUsername());

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * switchToCaloriesintakeWindow is called when the user presses the 'Calorie Intake' button on the Health Advice MenuScene screen.
     * Will display the user's recommended calorie intake based on their profile data.
     * @param event
     * @throws IOException
     */

    public void switchToCaloriesIntakeWindow(ActionEvent event) throws IOException {
        ProfilesManager profilesManager = new ProfilesManager();
        String username = userData.getUsername();

        int age = profilesManager.getAge(username);

        boolean isFemale = false;
        String gender = profilesManager.getGender(username);
        if (Objects.equals(gender, "F")) {
            isFemale = true;
        }

        String activityLevel = profilesManager.getActivityLevel(username);
        CalorieIntakeCalculator intakeCalculatorObject = new CalorieIntakeCalculator(age, isFemale, activityLevel);




        FXMLLoader loader = new FXMLLoader(getClass().getResource("CaloriesIntakeScene.fxml"));
        Parent root = loader.load();
        SceneController sceneController = loader.getController();
        sceneController.setCalorieData(intakeCalculatorObject);
        sceneController.DisplayCaloriesIntake();
        //root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LeaveTheAppWindow.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setCalorieData(CalorieIntakeCalculator CalorieData) {
        // Set the sleep data and update the display
        this.CalorieData = CalorieData;
        DisplayCaloriesIntake();
    }

    public void DisplayCaloriesIntake(){
        ProfilesManager profilesManager = new ProfilesManager();
        String username = userData.getUsername();

        int age = profilesManager.getAge(username);

        boolean isFemale = false;
        String gender = profilesManager.getGender(username);
        if (Objects.equals(gender, "F")) {
            isFemale = true;
        }
        String activityLevel = profilesManager.getActivityLevel(username);
        CaloriesIntakeDisplay.setText(CalorieData.calculateCalorieIntake(isFemale,age, activityLevel));
    }

    /**
     * switchToMenuWindow is called when the user presses the 'Back' button on the Health Advice MenuScene screen.
     * Will bring the user back to the main menu screen.
     * @param event
     * @throws IOException
     */

    public void switchToMenuWindow(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuScene.fxml"));
        Parent root = loader.load();

        // get the controller for the next scene
        SceneController controller = loader.getController();

        // pass the userData object to the next scene
        controller.setUserData(this.userData);
        System.out.println("Username set in userData Menuwindow : " + userData.getUsername());

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToPhysicalActivityWindow(ActionEvent event) throws IOException {
        ProfilesManager profilesManager = new ProfilesManager();
        String username = userData.getUsername();

        int age = profilesManager.getAge(username);
        int exerciseHours = profilesManager.getExerciseHours(username);
        String activityLevel = profilesManager.getActivityLevel(username);

        PhysicalActivity physicalActivityObject = new PhysicalActivity(activityLevel, exerciseHours, age);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PhysicalActivityScene.fxml"));
        Parent root = loader.load();
        SceneController sceneController = loader.getController();
        sceneController.setPhysicalActivityData(physicalActivityObject);
        sceneController.DisplayPhysicalActivity();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setPhysicalActivityData(PhysicalActivity PhysicalActivityData) {
        this.PhysicalActivityData = PhysicalActivityData;
        DisplayPhysicalActivity();
    }


    public void DisplayPhysicalActivity(){
        PhysicalActivityDisplay.setText(PhysicalActivityData.generateRecommendations());
    }

    public void switchToSleepWindow(ActionEvent event) throws IOException {
        ProfilesManager profilesManager = new ProfilesManager();
        String username = userData.getUsername();


        int age = profilesManager.getAge(username);

        int sleep = profilesManager.getSleepHours(username);

        int quality = profilesManager.getSleepQuality(username);

        Sleep sleepObject = new Sleep(age, sleep, quality);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SleepScene.fxml"));
        Parent root = loader.load();
        SceneController sceneController = loader.getController();

        sceneController.setSleepData(sleepObject);


        SceneController sceneSController = loader.getController();
        sceneSController.DisplaySleepRequire();
        //root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LeaveTheAppWindow.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setSleepData(Sleep sleepData) {
        // Set the sleep data and update the display
        this.sleepData = sleepData;
        DisplaySleepRequire();
    }

    public void DisplaySleepRequire(){
        SleepDisplay.setText(sleepData.provideSleepAdviceHour() + "\n" + sleepData.provideSleepAdviceQuality());
    }


    public void onLoginButtonPress(ActionEvent press)  throws IOException{ // Takes the user inputted username and password saves as strings
        String username = usernameField.getText();
        String password = passwordField.getText();

        userData.setUsername(username);
        System.out.println("Username set in userData after login press: " + userData.getUsername());
        boolean isValid = this.credentialManager.validateUser(username, password);

        if(isValid == true){
            DataVerifyController dataVerifyController = new DataVerifyController();
            dataVerifyController.setUserData(userData);
            dataVerifyController.switchToDataVerify(press,userData);
        }
        else{
            loginFailed.setVisible(true);
        }
    }

    /**
     * This method is called when the user presses the register button
     * @param press
     * @throws IOException
     */
    public void onRegisterButtonPress(ActionEvent press)  throws IOException{
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Creates a new instance of the UserData class for setting and getting our userData variables
        userData.setUsername(username);
        System.out.println("Username set in userData: " + userData.getUsername());

        CredentialsManager credentialsManager = new CredentialsManager();


        credentialsManager.addUser(username, password);
        switchToUserInput(press);
    }


    // NK - Added this to pull from input fields and convert to usable variables

    /**
     * @param press
     * @throws IOException We check for error handling and mis-input from the user. We pop up a display message telling them how to fix it.
     *                     We call profile manager so then when the user gives the data, it can be added along with their credentials(username/password)
     */
    public void onInputSubmitButtonPress(ActionEvent press) throws IOException {
        try {
            ProfilesManager profilesManager = new ProfilesManager();

            String gender = genderField.getText();
            if (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")) {
                showErrorAlert("Invalid Gender", "Please enter a valid gender (M or F)");
                return;
            }

            String name = nameField.getText();
            if (name.isEmpty() || !name.matches("[a-zA-Z]+")) {
                showErrorAlert("Invalid Name", "Please enter a name that has more than 0 characters and contains only alphabets");
                return;
            }

            String activityLevel = activityLevelField.getText();
            if (!activityLevel.equalsIgnoreCase("Sedentary") && !activityLevel.equalsIgnoreCase("Moderately Active") && !activityLevel.equalsIgnoreCase("Active")) {
                showErrorAlert("Invalid Activity Level", "Please enter a valid activity level (Sedentary, Moderately Active, Active)");
                return;
            }

            int ageInt = Integer.parseInt(ageField.getText());
            if (ageInt < 1 || ageInt > 75) {
                showErrorAlert("Invalid Age", "Please enter an age between 1 and 75.");
                return;
            }

            float weightFloat = Float.parseFloat(weightField.getText());
            if (weightFloat <= 0) {
                showErrorAlert("Invalid Weight", "Please enter a weight greater than 0!");
                return;
            }

            int feetInt = Integer.parseInt(feetField.getText());
            int inchesInt = Integer.parseInt(inchesField.getText());
            if (feetInt <= 0 || inchesInt >= 12 || inchesInt < 0) {
                showErrorAlert("Invalid Height", "Please enter a valid height");
                return;
            }

            int exerciseHoursInt = Integer.parseInt(hoursOfExerciseField.getText());
            if (exerciseHoursInt < 0 || exerciseHoursInt > 24) {
                showErrorAlert("Invalid Exercise Hours", "Please enter a valid daily hour value (0-24)!");
                return;
            }

            int sleepInt;
            sleepInt = Integer.parseInt(hoursOfSleepField.getText());
            if (sleepInt < 1 || sleepInt > 24) {
                showErrorAlert("Invalid Sleep Hours", "Please enter a number greater than '0' and less than '24' or invalid input");
                return;
            }

            int qualityInt = Integer.parseInt(sleepQualityField.getText());
            if (qualityInt < 1 || qualityInt > 10) {
                showErrorAlert("Invalid Sleep Quality", "Please enter a number between 1 and 10");
                return;
            }

            // If all inputs are valid, add or update the profile
            profilesManager.addOrUpdateProfile(name,
                    gender,
                    activityLevel,
                    ageInt,
                    weightFloat,
                    feetInt,
                    inchesInt,
                    exerciseHoursInt,
                    activityLevel,
                    sleepInt,
                    qualityInt);
            switchToScene1(press);

        } catch (NumberFormatException e) {
            showErrorAlert("Invalid Input", "Please enter valid numbers for all fields.");
        }
    }



    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }








// Gets the userData object for data transfer between scenes
    public UserData getUserData() {
        return userData;
    }

    // Sets the userData object for later data transfer from scene to scene
    public void setUserData(UserData userData) {
    this.userData = userData;
    }
}






