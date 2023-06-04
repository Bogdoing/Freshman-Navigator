package com.example.course2.dao.messagePac;

public class MassageF {
    private MessageDAO noteDao = null;
    public static final String DB_pg = "Save to DB";
    public static final String DB_li = "Save to List";

    public MessageDAO getTaskDao(String shapeType) {
        if (shapeType.equalsIgnoreCase(DB_pg)) {
            noteDao = new MessageDAOpg();
        } else if (shapeType.equalsIgnoreCase(DB_li)) {
            noteDao = new MessageDAOlist();
        }
        return noteDao;
    }
}
