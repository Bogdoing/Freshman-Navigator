package com.example.course2.entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Этот класс представляет собой модель данных Message
 * которая содержит данные истории сообщений польховатей и автора сохранения.
 * Класс содержит геттеры и сетеры для полей данных
 */
public class Message {
    private SimpleIntegerProperty id;
    private SimpleStringProperty text;
    private SimpleStringProperty author;

    public Message(int id, String text, String author) {
        this.id = new SimpleIntegerProperty (id);
        this.text = new SimpleStringProperty(text);
        this.author = new SimpleStringProperty(author);
    }

    public Message() {
        this.id = new SimpleIntegerProperty (1);
        this.text = new SimpleStringProperty("Not text");
        this.author = new SimpleStringProperty("Not author");
    }


    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
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

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }


    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text=" + text +
                ", author=" + author +
                '}';
    }
}
