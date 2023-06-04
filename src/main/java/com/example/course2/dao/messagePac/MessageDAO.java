package com.example.course2.dao.messagePac;

import com.example.course2.entity.Message;

import java.util.List;


public interface MessageDAO {

    void saveData(Message message);

    List<Message> getDataList();

}
