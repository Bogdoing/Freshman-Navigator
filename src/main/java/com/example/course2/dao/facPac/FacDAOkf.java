package com.example.course2.dao.facPac;

import com.example.course2.db.ConnectionDB;
import com.example.course2.entity.Fac;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacDAOkf implements FacDAO{

    private final ConnectionDB connectionDB;
    private final Connection connection;

    {
        connectionDB = new ConnectionDB();
        connection = connectionDB.getConnection();
    }

    @Override
    public void saveData(Fac fac) {

    }

    @Override
    public List<Fac> getDataList() {
        List<Fac> facList = new ArrayList<>();
        PreparedStatement ps = null;
        try {
            //ps = connection.prepareStatement("SELECT * FROM fac_caf");
            ps = connection.prepareStatement("SELECT fc.name_k, fc.adress, fc.telefon, fc.mail, f.name_f  FROM fac f, fac_caf fc WHERE f.id = fc.fac_id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Fac fac = new Fac();
                System.out.println(rs);
                //fac.setId( rs.getInt("id"));
                fac.setName_k(rs.getString("name_k"));
                fac.setAdress(rs.getString("adress"));
                fac.setTelefon(rs.getString("telefon"));
                fac.setMail(rs.getString("mail"));
                fac.setName_f( rs.getString("name_f"));

                facList.add(fac);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        facList.stream()
//                .forEach(fac -> System.out.println
//                        ("DB  " + fac.getId() + " " + fac.getName_f() + " " + fac.getAdress() + " " + fac.getTelefon()  + " " + fac.getMail() + " " + fac.getFac_id()));

        facList.stream()
                .forEach(fac -> System.out.println
                        ("DB  " + fac.getName_k() + " " + fac.getAdress() + " " + fac.getTelefon()  + " " + fac.getMail() + " " + fac.getName_f()));

        return facList;
    }
}
