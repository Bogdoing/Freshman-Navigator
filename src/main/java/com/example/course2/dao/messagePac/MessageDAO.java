package com.example.course2.dao.messagePac;

import com.example.course2.entity.Message;

import java.util.List;

/**
 * Интерфейс описываюзий методы сущности Message
 */
public interface MessageDAO {

    /**
     * Сохранение сообщений в БД
     * @param message
     */
    void saveData(Message message);

    /**
     * Получение списка сообщений из БД
     * @return
     */
    List<Message> getDataList();

}
