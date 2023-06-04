package com.example.course2.dao.facPac;

import com.example.course2.entity.Fac;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacDAOpg implements FacDAO{
    private Connection connection;
    private final String url = "jdbc:postgresql://localhost:5432/couremina";
    private final String username = "postgres";
    private final String password = "12qwaszx";

    public FacDAOpg() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveData(Fac fac) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("insert into fac (name_f, adress, telefon, mail) VALUES (?, ?, ?, ?)");
            ps.setString(1, fac.getName_f());
            ps.setString(2, fac.getAdress());
            ps.setString(3, fac.getTelefon());
            ps.setString(4, fac.getMail());

            ps.executeUpdate();
            System.out.println("DB " + fac.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Fac> getDataList() {
        List<Fac> facList = new ArrayList<>();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM fac");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Fac fac = new Fac();
                System.out.println(rs);
                fac.setId( rs.getInt("id"));
                fac.setName_f(rs.getString("name_f"));
                fac.setAdress(rs.getString("adress"));
                fac.setTelefon(rs.getString("telefon"));
                fac.setMail(rs.getString("mail"));

                //message.setAuthor(rs.getString("task"));

                facList.add(fac);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        facList.stream()
                .forEach(fac -> System.out.println
                        ("DB  " + fac.getId() + " " + fac.getName_f() + " " + fac.getAdress() + " " + fac.getTelefon()  + " " + fac.getMail()));

        return facList;
    }




}
