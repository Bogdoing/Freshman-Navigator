package com.example.course2.entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Fac {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name_f;
    private SimpleStringProperty adress;
    private SimpleStringProperty telefon;
    private SimpleStringProperty mail;

    public Fac(int id, String name_f, String adress, String telefon, String mail) {
        this.id = new SimpleIntegerProperty (id);
        this.name_f = new SimpleStringProperty(name_f);
        this.adress = new SimpleStringProperty(adress);
        this.telefon = new SimpleStringProperty(telefon);
        this.mail = new SimpleStringProperty(mail);

    }

    public Fac() {
        this.id = new SimpleIntegerProperty (1);
        this.name_f = new SimpleStringProperty("name_f");
        this.adress = new SimpleStringProperty("adress");
        this.telefon = new SimpleStringProperty("telefon");
        this.mail = new SimpleStringProperty("mail");
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

    public String getName_f() {
        return name_f.get();
    }

    public SimpleStringProperty name_fProperty() {
        return name_f;
    }

    public void setName_f(String name_f) {
        this.name_f.set(name_f);
    }

    public String getAdress() {
        return adress.get();
    }

    public SimpleStringProperty adressProperty() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress.set(adress);
    }

    public String getTelefon() {
        return telefon.get();
    }

    public SimpleStringProperty telefonProperty() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon.set(telefon);
    }

    public String getMail() {
        return mail.get();
    }

    public SimpleStringProperty mailProperty() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail.set(mail);
    }

    @Override
    public String toString() {
        return "Fac{" +
                "id=" + id +
                ", name_f=" + name_f +
                ", adress=" + adress +
                ", telefon=" + telefon +
                ", mail=" + mail +
                '}';
    }

}
