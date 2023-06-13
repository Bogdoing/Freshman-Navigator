package com.example.course2.Chat;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Интерфейс определяющий методы работы пользователей чата
 */
public interface User {

    /**
     * Создание/подключение сераера
     * @param textArea
     * @param textField
     */
    void create(TextArea textArea, TextField textField);

    /**
     * Отвечает за проверку пароля пользователя
     * @param textField
     * @return
     */
    boolean SinIn(TextField textField);

    /**
     * Отвечает за отправку сообщений
     * @param textField
     * @param textArea
     */
    void sender(TextField textField, TextArea textArea);

    /**
     * Отвечает за принятие сообщений и отображение их в форму
     * @param textArea
     */
    void receive(TextArea textArea);

    /**
     * Отвечает за принятие сообщений и отображение их в консоль
     */
    void receive();
}
