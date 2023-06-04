package com.example.course2.Chat;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public interface User {
    void create(TextArea textArea, TextField textField);

    boolean SinIn(TextField textField);

    void sender(TextField textField, TextArea textArea);

    void receive(TextArea textArea);
    void receive();
}
