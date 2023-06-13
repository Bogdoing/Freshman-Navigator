package com.example.course2.dao.keepPac;

import com.example.course2.entity.Keep;

import java.util.List;

/**
 * Интерфейс описываюзий методы сущности Keeps
 */
public interface KeepsDAO {

    /**
     * Сохранение заметки
     * @param keep
     */
    void saveKeep(Keep keep);

    /**
     * Удаление заметки
     * @param keep
     */
    void delKeep(Keep keep);

    /**
     * Изменение заметки
     * @param keepLasted
     * @param keepNew
     */
    void rewriteKeep(Keep keepLasted, Keep keepNew);

    /**
     * Получение актуального списка заметок из БД
     * @return
     */
    List<Keep> getKeepList();
}
