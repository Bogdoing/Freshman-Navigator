package com.example.course2.dao.facPac;

import com.example.course2.entity.Fac;

import java.util.List;

/**
 * Интерфейс описываюзий методы сущности Keeps
 */
public interface FacDAO {

    /**
     * Сохранение факультетов/кафедр/др в БД
     * @param fac
     */
    void saveData(Fac fac);

    /**
     * Получение списка факультетов/кафедр/др из БД
     * @return
     */
    List<Fac> getDataList();

}
