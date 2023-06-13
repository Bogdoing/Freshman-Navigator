package com.example.course2.dao.messagePac;

/**
 * Класс реализующий паттерн DAO для сущностей типа Message
 */
public class MassageF {
    private MessageDAO noteDao = null;
    public static final String DB_pg = "Save to DB";
    public static final String DB_li = "Save to List";

    /**
     * Возвращает класс сущности Message в зависимости от входных данных
     * @param shapeType
     * @return
     */
    public MessageDAO getTaskDao(String shapeType) {
        if (shapeType.equalsIgnoreCase(DB_pg)) {
            noteDao = new MessageDAOpg();
        } else if (shapeType.equalsIgnoreCase(DB_li)) {
            noteDao = new MessageDAOlist();
        }
        return noteDao;
    }
}
