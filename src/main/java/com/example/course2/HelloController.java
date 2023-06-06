package com.example.course2;

import com.example.course2.Chat.Client;
import com.example.course2.Chat.Server;
import com.example.course2.Chat.User;
import com.example.course2.Chat.UserF;
import com.example.course2.View.Web;
import com.example.course2.dao.facPac.FacDAO;
import com.example.course2.dao.facPac.FacF;
import com.example.course2.dao.messagePac.MassageF;
import com.example.course2.dao.messagePac.MessageDAO;
import com.example.course2.dao.messagePac.MessageDAOlist;
import com.example.course2.dao.messagePac.MessageDAOpg;
import com.example.course2.dao.tablePac.TableDAO;
import com.example.course2.dao.tablePac.TableF;
import com.example.course2.entity.Fac;
import com.example.course2.entity.Message;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.concurrent.Worker.State;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private ComboBox combobox;
    @FXML
    private ComboBox comboBoxInfr;
    @FXML
    private ComboBox comboboxData;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField textField;
    @FXML
    private TableView TVfac;
    @FXML
    private WebView webView;
    @FXML
    private TextField TFurl;
    @FXML
    private TextField textFieldInfr;

    MassageF massageF = new MassageF();
    MessageDAO messageDAO;

    FacF facF = new FacF();
    FacDAO facDAO;

    TableF tableF = new TableF();
    TableDAO tableDAO;

    private ObservableList<Fac> observableList;

    private ObservableList<Fac> dataList = FXCollections.observableArrayList();

    private Web web = new Web();

//    WebEngine engine = webView.getEngine();

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
        //System.out.println(messageDAO);
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
        facDAO = facF.getFakDao((String) comboBoxInfr.getValue());
        dataList = observableList;
        if (facDAO.getDataList() == null) {
            //onSentNewDataButton();
        }
        observableList = FXCollections.observableList(facDAO.getDataList());
        System.out.println("observableList"+observableList);
        TVfac.setItems(observableList);

    }

    @FXML
    protected void onGetcomboBoxInfr(){
        System.out.println("sdfsdfa" + comboBoxInfr.getValue());
        //comboBoxInfr.getItems();
        table_fac_init();
        onGetSearch();
    }

    protected void onGetSearch(){
        dataList = observableList;

        FilteredList<Fac> filteredData = new FilteredList<>(dataList, b -> true);

        textFieldInfr.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;

                }

                String lowerCaseFilter = newValue.toLowerCase();
                System.out.println("lowerCaseFilter - " + lowerCaseFilter
                        + " | employee - " + employee.getName_f().toLowerCase().indexOf(lowerCaseFilter)
                        + " | getName_f - " + employee.getName_f().toLowerCase());
                if (employee.getName_f().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    //System.out.println("getName_f");
                    return true;
                }else if (employee.getName_k().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    //System.out.println("getName_k");
                    return true;
                } else if (employee.getAdress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    //System.out.println("getAdress");
                    return true;
                } else if (String.valueOf(employee.getMail()).indexOf(lowerCaseFilter) != -1) {
                    //System.out.println("getMail");
                    return true;
                }
                else
                    return false;
            });
            SortedList<Fac> sortedData = new SortedList<>(filteredData);
            //System.out.println("filteredData - " + filteredData);

            sortedData.comparatorProperty().bind(TVfac.comparatorProperty());
            //System.out.println("sortedData - " + sortedData);

            TVfac.setItems(sortedData);
        });
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

    public void WebViewClick(){
        WebEngine engine = getEngines();
        //System.out.println(engine.getLocation());
        TFurl.setText(engine.getLocation());
    }

    public void goHome(){
        WebEngine engine = getEngines();
        engine.load("file:///D:/files/CODE/JAVA/course2/course2/src/main/resources/com/example/course2/index.html");
        engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
            @Override
            public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
                if (newState == State.SUCCEEDED) {
                    engine.executeScript("test()");
                }
            }
        });
        System.out.println("engine.getLocation() - " + engine.getLocation());
    }

    public WebEngine getEngines(){
        WebEngine engine = webView.getEngine();
        return engine;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combobox.getItems().add("Student");
        combobox.getItems().add("Curator");

        comboBoxInfr.getItems().add("ФК");
        comboBoxInfr.getItems().add("КФ");
        comboBoxInfr.getItems().add("ДР");
        comboBoxInfr.getSelectionModel().selectFirst();

        TVfac.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        textArea.appendText("Введите пароль");
        comboboxData.getItems().add("Save to DB");
        comboboxData.getItems().add("Save to List");
        table_fac_init(); // создание таблицы "инфраструскура ВУЗа"
        onGetSearch(); // поиск по названию


        WebEngine engine = getEngines();

        engine.load("file:///D:/files/CODE/JAVA/course2/course2/src/main/resources/com/example/course2/index.html");
        engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
            @Override
            public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
                if (newState == State.SUCCEEDED) {
                    engine.executeScript("test()");
                }
            }
        });
        //engine.load("127.0.0.1:5500/index.html");
        //engine.load(getClass().getResource("../View/index.html").toExternalForm());
        //engine.executeScript("document.getElementById('btn').addEventListener('click', function() { loadFile('/test/index2.html')});");
    }


    private void table_fac_init(){
        tableDAO = tableF.getTableDAO((String) comboBoxInfr.getValue());
        tableDAO.getTable(TVfac);

        onGetDataButton();
    }
}