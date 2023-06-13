package com.example.course2.dao.messagePac;

import com.example.course2.entity.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс содержащий классы для созранение и получения сообщений из БД
 */
public class MessageDAOpg implements MessageDAO {

    private Connection connection;
    private final String url = "jdbc:postgresql://localhost:5432/couremina";
    private final String username = "postgres";
    private final String password = "12qwaszx";

    /**
     * Подключение к БД
     */
    public MessageDAOpg() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Созранение сообщений в БД
     * @param message
     */
    @Override
    public void saveData(Message message) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("insert into message (txt, author) VALUES (?, ?)");
            ps.setString(1, message.getText());
            ps.setString(2, message.getAuthor());

            ps.executeUpdate();
            System.out.println("DB " + message.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получение списка сообщений из БД
     * @return
     */
    @Override
    public List<Message> getDataList() {
        List<Message> messageList = new ArrayList<>();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM message");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Message message = new Message();

                message.setId( rs.getInt("id"));
                message.setText(rs.getString("txt"));
                message.setAuthor(rs.getString("task"));


                messageList.add(message);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        messageList.stream()
                .forEach(message -> System.out.println
                        ("DB  " + message.getId() + " " + message.getText() + " " + message.getAuthor()));

        return messageList;
    }
}
