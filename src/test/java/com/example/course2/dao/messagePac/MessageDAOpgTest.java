package com.example.course2.dao.messagePac;

import com.example.course2.db.ConnectionDB;
import com.example.course2.entity.Message;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageDAOpgTest {

    @Test
    void saveData() {
        MessageDAO messageDAO = new MessageDAOpg();
        Message message = new Message();

        try{
            messageDAO.saveData(message);
            System.out.println("saveData OK");
        }
        catch (Exception e){
            throw new RuntimeException("saveData NOT OK");
        }
    }

    @Test
    void getDataList() {
        MessageDAO messageDAO = new MessageDAOpg();
        Message message = new Message();

        List<Message> messageList = messageDAO.getDataList();


        //
        Connection connection;
        ConnectionDB connectionDB = new ConnectionDB();
        connection = connectionDB.getConnection();
        List<Message> messageListTest = new ArrayList<>();

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM message");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Message messageTest = new Message();

                messageTest.setId( rs.getInt("id"));
                messageTest.setText(rs.getString("txt"));
                messageTest.setAuthor(rs.getString("author"));


                messageListTest.add(messageTest);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (messageList == messageListTest){
            System.out.println("getDataList OK");
        }
        else {
            System.out.println("getDataList OK");
        }
    }
}