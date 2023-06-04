package com.example.course2.Chat;


import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements User {

    Socket clientSocket;
    BufferedReader in;
    PrintWriter out;
    Scanner sc = new Scanner(System.in);

    @Override
    public void create(TextArea textArea, TextField textField){
        textArea.appendText("\nНачинайте общение");

        try {
            System.out.println("Start client");
            clientSocket = new Socket("127.0.0.1",5000);
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            sender(textField, textArea);
            receive(textArea);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean SinIn(TextField textField){
        if (textField.getText().equals("cli"))
            return true;
        return false;
    }

    @Override
    public void sender(TextField textField, TextArea textArea){
        Thread sender = new Thread(new Runnable() {
            String msg = textField.getText();
            @Override
            public void run() {
                while(true){
//                    msg = sc.nextLine();
                    textField.setOnAction(actionEvent -> {
                        msg = textField.getText();
                        out.println(msg);
                        textArea.appendText("\nClient : " + msg);
                        textField.clear();
                        out.flush();
                    });


                }
            }
        });
        sender.start();
    }

    @Override
    public void receive(){
        Thread receiver = new Thread(new Runnable() {
            String msg;
            @Override
            public void run() {
                try {
                    msg = in.readLine();
                    while(msg!=null){
                        System.out.println("Server : "+msg);
                        //textArea.appendText("Client : " + msg);
                        msg = in.readLine();
                    }
                    System.out.println("Server out of service");
                    out.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        receiver.start();
    }
    @Override
    public void receive(TextArea textArea) {
        Thread receiver = new Thread(new Runnable() {
            String msg;
            @Override
            public void run() {
                try {
                    msg = in.readLine();
                    while(msg!=null){
                        System.out.println("Server : "+msg);
                        textArea.appendText("\nServer : " + msg);
                        msg = in.readLine();
                    }
                    System.out.println("Server out of service");
                    out.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        receiver.start();
    }
}