package com.example.course2.dao.keepPac;

import com.example.course2.entity.Keep;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KeepsTest {

    @Test
    void saveKeep() {
        KeepsDAO keepsDAO = new Keeps();
        Keep keep = new Keep("Text", "Title");
        try {
            keepsDAO.saveKeep(keep);
            System.out.println("saveKeep OK");
        }
        catch (Exception e){
            throw new RuntimeException("saveKeep NOT OK");
        }

    }

    @Test
    void delKeep() {
        KeepsDAO keepsDAO = new Keeps();
        Keep keep = new Keep("Text", "Title");
        try {
            keepsDAO.delKeep(keep);
            System.out.println("delKeep OK");
        }
        catch (Exception e){
            throw new RuntimeException("delKeep NOT OK");
        }

    }

    @Test
    void rewriteKeep() {
        KeepsDAO keepsDAO = new Keeps();
        Keep keepLast = new Keep("Text", "Title");
        Keep keepNew = new Keep("Text_new", "Title_new");
        try {
            keepsDAO.rewriteKeep(keepLast, keepNew);
            System.out.println("rewriteKeep OK");
        }
        catch (Exception e){
            throw new RuntimeException("rewriteKeep NOT OK");
        }
    }

    @Test
    void getKeepList() {
        Connection connection;
        final String url = "jdbc:postgresql://localhost:5432/couremina";
        final String username = "postgres";
        final String password = "12qwaszx";

        KeepsDAO keepsDAO = new Keeps();

        List<Keep> keepList = new ArrayList<>();
        PreparedStatement ps = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            ps = connection.prepareStatement("SELECT * FROM keeps");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Keep keep = new Keep();

                keep.setTitle(rs.getString("title"));
                keep.setText(rs.getString("text"));


                keepList.add(keep);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<Keep> keepListTest =  keepsDAO.getKeepList();

        if (keepList == keepListTest){
            System.out.println("rewriteKeep OK");
        }
        else {
            System.out.println("rewriteKeep NOT OK");
        }
    }
}