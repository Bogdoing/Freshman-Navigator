package com.example.course2.Chat;

import javafx.scene.paint.Color;

/**
 * Клас реализующий паттерн DAO для пользователей типа USER
 */
public class UserF {

    /**
     * Принимает название юзера и возвращает класс нужного юзера
     * @param userString
     * @return
     */
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
