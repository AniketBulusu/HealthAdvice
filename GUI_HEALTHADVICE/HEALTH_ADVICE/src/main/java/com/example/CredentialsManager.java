package com.example;

import java.io.*;
import java.util.HashMap;



    public class CredentialsManager {
        private HashMap<String, String> credentials = new HashMap<>();

        private final String CredentialsFile = "credentials.txt";



        /**
         * Constructor to load credentials from the credentials file
         */
        public CredentialsManager() {
            loadCredentials();
        }



        /**
         * Load credentials from the credentials.txt file using a BufferedReader
         */
        private void loadCredentials() {
            try (BufferedReader reader = new BufferedReader(new FileReader(CredentialsFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":", 2);
                    if (parts.length == 2) {
                        credentials.put(parts[0], parts[1]);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading credentials file: " + e.getMessage());
            }
        }



        /**
         * Save credentials to the credentials.txt file for future use
         */
        public void saveCredentials() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CredentialsFile))) {
                for (HashMap.Entry<String, String> entry : credentials.entrySet()) {
                    writer.write(entry.getKey() + ":" + entry.getValue());
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error writing credentials file: " + e.getMessage());
            }
        }



        /**
         * Add a new user to the credentials file
         * @param username - username of the new user
         * @param password - password of the new user
         * @return - true if the user was added successfully, false if the username already exists
         */
        public boolean addUser(String username, String password) {
            if (credentials.containsKey(username)) {
                return false;
            }
            credentials.put(username, password);
            saveCredentials();
            return true;
        }



        /**
         * Validate the user by checking if the username and password match
         * @param username - username of the user
         * @param password - password of the user
         * @return - true if the username and password match, false otherwise
         */
        public boolean validateUser(String username, String password) {
            return credentials.containsKey(username) && credentials.get(username).equals(password);
        }
    }



