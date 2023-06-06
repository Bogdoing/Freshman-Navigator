package com.example.course2.dao.tablePac;

import com.example.course2.dao.facPac.FacDAO;
import com.example.course2.dao.facPac.FacDAOpg;

public class TableF {

    private TableDAO tableDAO = null;
    public static final String I_fk = "ФК";
    public static final String I_kf = "КФ";
    public static final String I_dr = "ДР";

    public TableDAO getTableDAO(String Type) {
        if (Type.equalsIgnoreCase(I_fk)) {
            tableDAO = new TableFK();
        } else if (Type.equalsIgnoreCase(I_kf)) {
            tableDAO = new TableKF();
        } else if (Type.equalsIgnoreCase(I_dr)) {
            tableDAO = new TableFK();
        }
        else {
            tableDAO = new TableFK();
        }
        return tableDAO;
    }

}
