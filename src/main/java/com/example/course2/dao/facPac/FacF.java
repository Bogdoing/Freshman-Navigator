package com.example.course2.dao.facPac;

public class FacF {

    private FacDAO facDAO = null;

    public static final String F_fk = "ФК";

    public static final String F_kf = "КФ";
    public static final String F_dr = "ДР";

    public FacDAO getFakDao(String shapeType) {
        if (shapeType.equalsIgnoreCase(F_fk)) {
            System.out.println(F_fk);
            facDAO = new FacDAOpg();
        } else if (shapeType.equalsIgnoreCase(F_kf)) {
            System.out.println(F_kf);
            facDAO = new FacDAOkf();
        }else if (shapeType.equalsIgnoreCase(F_dr)) {
            System.out.println(F_dr);
            facDAO = new FacDAOdr();
        } else{
            System.out.println("NaN");
        }

        return facDAO;
    }
}
