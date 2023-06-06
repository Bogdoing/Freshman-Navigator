package com.example.course2.dao.tablePac;

import com.example.course2.entity.Fac;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableDR implements TableDAO{
    @Override
    public void getTable(TableView TVfac) {
        TableColumn Col0 = new TableColumn("name_f");
        Col0.setMinWidth(100);
        Col0.setCellValueFactory(new PropertyValueFactory<Fac, String>("name_f"));


        TableColumn Col1 = new TableColumn("adress");
        Col1.setMinWidth(100);
        Col1.setCellValueFactory(new PropertyValueFactory<Fac, String>("adress"));


        TableColumn Col2 = new TableColumn("telefon");
        Col2.setMinWidth(100);
        Col2.setCellValueFactory(new PropertyValueFactory<Fac, String>("telefon"));

        TableColumn Col3 = new TableColumn("mail");
        Col3.setMinWidth(100);
        Col3.setCellValueFactory(new PropertyValueFactory<Fac, String>("mail"));

        TVfac.getColumns().clear();
        TVfac.getColumns().addAll(Col0, Col1, Col2, Col3);
    }
}
