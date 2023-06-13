package com.example.course2.dao.facPac;

import com.example.course2.db.ConnectionDB;
import com.example.course2.entity.Fac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс содержащий методы работы с инфраструктурой ВУЗа типа (другое)
 */
public class FacDAOdr implements FacDAO{
    private final ConnectionDB connectionDB;
    private final Connection connection;

    {
        connectionDB = new ConnectionDB();
        connection = connectionDB.getConnection();
    }

    /**
     * Сохранение в БД
     * @param fac
     */
    @Override
    public void saveData(Fac fac) {

    }

    /**
     * Получение списка сущностей из БД
     * @return
     */
    @Override
    public List<Fac> getDataList() {
        List<Fac> facList = new ArrayList<>();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("select name_f, adress, telefon, mail from fac_dr");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Fac fac = new Fac();
                System.out.println(rs);
                fac.setName_f(rs.getString("name_f"));
                fac.setAdress(rs.getString("adress"));
                fac.setTelefon(rs.getString("telefon"));
                fac.setMail(rs.getString("mail"));

                facList.add(fac);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        facList.stream()
                .forEach(fac -> System.out.println
                        ("DB  " + fac.getName_k() + " " + fac.getAdress() + " " + fac.getTelefon()  + " " + fac.getMail()));

        return facList;
    }
}
