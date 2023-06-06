package com.example.course2.dao.facPac;

import com.example.course2.entity.Fac;

import java.util.List;

public interface FacDAO {
    void saveData(Fac fac);

    List<Fac> getDataList();

}
