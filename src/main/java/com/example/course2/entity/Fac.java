package com.example.course2.entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Этот класс представляет собой модель данных Fac которая содержит данные о факультетах, кафердах и т.д.
 * Класс содержит геттеры и сетеры для полей данных
 */
public class Fac {

    private SimpleIntegerProperty id;
    private SimpleStringProperty name_f;
    private SimpleStringProperty adress;
    private SimpleStringProperty telefon;
    private SimpleStringProperty mail;
    private SimpleIntegerProperty fac_id;
    private SimpleStringProperty name_k;



    public Fac(int id, String name_f, String adress, String telefon, String mail, int fac_id, String name_k) {
        this.id = new SimpleIntegerProperty(id);
        this.name_f = new SimpleStringProperty(name_f);
        this.adress = new SimpleStringProperty(adress);
        this.telefon = new SimpleStringProperty(telefon);
        this.mail = new SimpleStringProperty(mail);
        this.fac_id = new SimpleIntegerProperty(fac_id);
        this.name_k = new SimpleStringProperty(name_k);

    }

    public Fac() {
        this.id = new SimpleIntegerProperty (1);
        this.name_f = new SimpleStringProperty("name_f");
        this.adress = new SimpleStringProperty("adress");
        this.telefon = new SimpleStringProperty("telefon");
        this.mail = new SimpleStringProperty("mail");
        this.fac_id = new SimpleIntegerProperty (1);
        this.name_k = new SimpleStringProperty("name_k");
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

    public int getFac_id() {
        return fac_id.get();
    }

    public SimpleIntegerProperty fac_idProperty() {
        return fac_id;
    }

    public void setFac_id(int fac_id) {
        this.fac_id.set(fac_id);
    }

    public String getName_k() {
        return name_k.get();
    }

    public SimpleStringProperty Name_kProperty() {
        return name_k;
    }

    public void setName_k(String fac) {
        this.name_k.set(fac);
    }


    @Override
    public String toString() {
        return "Fac{" +
                "id=" + id +
                ", name_f=" + name_f +
                ", adress=" + adress +
                ", telefon=" + telefon +
                ", mail=" + mail +
                ", fac_id=" + fac_id +
                ", name_k=" + name_k +
                '}';
    }
}
