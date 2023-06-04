package com.example.course2;

import com.example.course2.Chat.Client;
import com.example.course2.Chat.Server;
import com.example.course2.Chat.User;
import com.example.course2.Chat.UserF;
import com.example.course2.dao.facPac.FacDAO;
import com.example.course2.dao.facPac.FacF;
import com.example.course2.dao.messagePac.MassageF;
import com.example.course2.dao.messagePac.MessageDAO;
import com.example.course2.dao.messagePac.MessageDAOlist;
import com.example.course2.dao.messagePac.MessageDAOpg;
import com.example.course2.entity.Fac;
import com.example.course2.entity.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private ComboBox combobox;
    @FXML
    private ComboBox comboboxData;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField textField;
    @FXML
    private TableView TVfac;

    MassageF massageF = new MassageF();
    MessageDAO messageDAO;

    FacF facF = new FacF();
    FacDAO facDAO;
    private ObservableList<Fac> observableList;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void saveData() {
        Message message = new Message();
        message.setText(textArea.getText());
        message.setAuthor((String) combobox.getValue());
        messageDAO = massageF.getTaskDao((String) comboboxData.getValue());
        System.out.println(messageDAO);
        messageDAO.saveData(message);
        if (messageDAO instanceof MessageDAOpg)
            textArea.appendText("\nData is save: MessageDAOpg");
        else if (messageDAO instanceof MessageDAOlist)
            textArea.appendText("\nData is save: MessageDAOlist ");
        else textArea.appendText("\nData is not save ");

    }

    @FXML
    protected void setUser() {
        System.out.println(combobox.getValue());

        UserF userF = new UserF();
        User user = userF.getUser((String) combobox.getValue());

        if ((textField.getText().equals("serv")) && (user instanceof Server))
            user.create(textArea, textField);
        else if ((textField.getText().equals("cli")) && (user instanceof Client))
            user.create(textArea, textField);
        else textArea.appendText("Pass not validation");

        textField.clear();
    }

    @FXML
    protected void onGetDataButton(){
        facDAO = facF.getFakDao("Save to DB");
        if (facDAO.getDataList() == null) {
            //onSentNewDataButton();
        }

        observableList = FXCollections.observableList(facDAO.getDataList());

        TVfac.setItems(observableList);

        //table_fac_init();
    }
    //@FXML
    public void onSentNewDataButton() {
//        Fac fac = new Fac();
//
//        fac.set(dateField.getText());
//        fac.setTask(taskField.getText());
//
//        facDAO = facF.getFacDao(info.getText());
//
//        facDAO.saveData(note);

        //onGetDataButton();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combobox.getItems().add("Student");
        combobox.getItems().add("Curator");
        textArea.appendText("Введите пароль");
        comboboxData.getItems().add("Save to DB");
        comboboxData.getItems().add("Save to List");
        table_fac_init();
    }

    private void table_fac_init(){
        TableColumn Col0 = new TableColumn("id");
        Col0.setMinWidth(15);
        Col0.setCellValueFactory(new PropertyValueFactory<Fac, Integer>("id"));

        TableColumn Col1 = new TableColumn("name_f");
        Col1.setMinWidth(100);
        Col1.setCellValueFactory(new PropertyValueFactory<Fac, String>("name_f"));


        TableColumn Col2 = new TableColumn("adress");
        Col2.setMinWidth(100);
        Col2.setCellValueFactory(new PropertyValueFactory<Fac, String>("adress"));


        TableColumn Col3 = new TableColumn("telefon");
        Col3.setMinWidth(100);
        Col3.setCellValueFactory(new PropertyValueFactory<Fac, String>("telefon"));

        TableColumn Col4 = new TableColumn("mail");
        Col4.setMinWidth(100);
        Col4.setCellValueFactory(new PropertyValueFactory<Fac, String>("mail"));

        TVfac.getColumns().clear();
        TVfac.getColumns().addAll(Col0, Col1, Col2, Col3, Col4);

        onGetDataButton();
    }
}