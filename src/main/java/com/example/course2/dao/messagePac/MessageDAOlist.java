package com.example.course2.dao.messagePac;

import com.example.course2.entity.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageDAOlist implements MessageDAO {
    private List<Message> messageList;

    @Override
    public void saveData(Message message) {
        messageList = new ArrayList<>();
        messageList.add(message);
        System.out.println("App add " + message.toString());
    }

    @Override
    public List<Message> getDataList() {
        if (messageList != null) {
            messageList.stream()
                    .forEach(message -> System.out.println
                            ("App  getList" + message.getId() + " " + message.getText() + " " + message.getAuthor()));
        }
        return messageList;
    }
}
