package com.example.course2.Chat;

import javafx.scene.paint.Color;

public class UserF {
    public User getUser(String userString) {
        User user = null;
        if (userString == "Curator"){
            user = new Server();
            System.out.println("Server - " + userString + "\n");
        }
        else if (userString == "Student") {
            user = new Client();
            System.out.println("Client - " + userString + "\n");
        }
        else System.out.println("Erors fabric - " + userString + "\n");

        return user;
    }
}
