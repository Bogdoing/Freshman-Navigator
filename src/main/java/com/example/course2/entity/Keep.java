package com.example.course2.entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Keep {
    private SimpleStringProperty title;
    private SimpleStringProperty text;

    public Keep(String text, String title) {
        this.title = new SimpleStringProperty(title);
        this.text = new SimpleStringProperty(text);
    }

    public Keep() {
        this.title = new SimpleStringProperty("Not title");
        this.text = new SimpleStringProperty("Not text");
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getText() {
        return text.get();
    }

    public SimpleStringProperty textProperty() {
        return text;
    }

    public void setText(String text) {
        this.text.set(text);
    }


}
