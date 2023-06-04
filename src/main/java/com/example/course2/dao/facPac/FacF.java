package com.example.course2.dao.facPac;

public class FacF {

    private FacDAO facDAO = null;
    public static final String DB_pg = "Save to DB";
    public static final String DB_li = "Save to List";

    public FacDAO getFakDao(String shapeType) {
        if (shapeType.equalsIgnoreCase(DB_pg)) {
            facDAO = new FacDAOpg();
        } else if (shapeType.equalsIgnoreCase(DB_li)) {
            //facDAO = new MessageDAOlist();
        }
        return facDAO;
    }
}
