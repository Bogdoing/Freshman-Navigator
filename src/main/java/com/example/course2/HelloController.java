package com.example.course2;

import com.example.course2.Chat.Client;
import com.example.course2.Chat.Server;
import com.example.course2.Chat.User;
import com.example.course2.Chat.UserF;
import com.example.course2.dao.facPac.FacDAO;
import com.example.course2.dao.facPac.FacF;
import com.example.course2.dao.keepPac.Keeps;
import com.example.course2.dao.messagePac.MassageF;
import com.example.course2.dao.messagePac.MessageDAO;
import com.example.course2.dao.messagePac.MessageDAOlist;
import com.example.course2.dao.messagePac.MessageDAOpg;
import com.example.course2.dao.tablePac.TableDAO;
import com.example.course2.dao.tablePac.TableF;
import com.example.course2.entity.Fac;
import com.example.course2.entity.Keep;
import com.example.course2.entity.Message;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.concurrent.Worker.State;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Контроллер главного окна программы
 */
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

    @FXML
    private ListView keepList;
    @FXML
    private TextField keepTitle;
    @FXML
    private Button keepAdd;
    @FXML
    private TextArea keepText;
    Keeps keeps = new Keeps();
    List<Keep> keepsListTitle = keeps.getKeepList();
    List<Keep> keepsListText = keeps.getKeepList();

    MassageF massageF = new MassageF();
    MessageDAO messageDAO;

    FacF facF = new FacF();
    FacDAO facDAO;

    TableF tableF = new TableF();
    TableDAO tableDAO;

    private ObservableList<Fac> observableList;

    private ObservableList<Fac> dataList = FXCollections.observableArrayList();


//    WebEngine engine = webView.getEngine();

    /**
     * Выбор (ComboBox) куда сохранять сообщения
     */
    @FXML
    protected void saveData() {
        Message message = new Message();
        message.setText(textArea.getText());
        message.setAuthor((String) combobox.getValue());
        messageDAO = massageF.getTaskDao((String) comboboxData.getValue());
        messageDAO.saveData(message);
        if (messageDAO instanceof MessageDAOpg)
            textArea.appendText("\nData is save: MessageDAOpg");
        else if (messageDAO instanceof MessageDAOlist)
            textArea.appendText("\nData is save: MessageDAOlist ");
        else textArea.appendText("\nData is not save ");

    }

    /**
     * Создание класса user для чата
     */
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

    /**
     * Выбор категории инфраструктуры ВУЗа
     */
    @FXML
    protected void onGetcomboBoxInfr() {
        System.out.println("sdfsdfa" + comboBoxInfr.getValue());
        //comboBoxInfr.getItems();
        table_fac_init();
        onGetSearch();
    }

    /**
     * Добовление заметки
     */
    @FXML
    protected void addKeep() {
        Keep keep = new Keep();
        System.out.println("keepTitle.getText()" + keepTitle.getText());
        keep.setTitle(keepTitle.getText());
        keep.setText(keepText.getText());

        System.out.println("keep save - " + keep);
        keeps.saveKeep(keep);

        keepList.getItems().clear();
        keeps();

    }

    /**
     * Удаление заметки
     */
    @FXML
    protected void delKeeps() {
        Keep keep = new Keep(keepText.getText(), keepTitle.getText());

        System.out.println("del keep - " + keep.getTitle() + " + " + keep.getText());
        keeps.delKeep(keep);

        keepList.getItems().clear();
        keeps();
    }

    /**
     * Очистка полуй заметок для написания новой
     */
    @FXML
    protected void newKeep() {
        keepTitle.setText("");
        keepText.setText("");
    }

    /**
     * Изменение имеющийся заметки
     */
    @FXML
    protected void reKeep() {
        Keep keepLast = new Keep(keepText.getText(), keepTitle.getText());

        System.out.println("re keep - " + keepLast.getTitle() + " + " + keepLast.getText());

        // Create the custom dialog.
        //Dialog<Pair<String, String>> dialog = new Dialog<>();
        Dialog<Keep> dialog = new Dialog<>();
        dialog.setTitle("Rewrite Keep");
        dialog.setHeaderText("Rewrite Keep");

        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField title = new TextField();
        title.setPromptText("Title");
        TextArea text = new TextArea();
        text.setPromptText("Text");

        grid.add(new Label("Title:"), 0, 0);
        grid.add(title, 1, 0);
        grid.add(new Label("Text:"), 0, 1);
        grid.add(text, 1, 1);

        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        title.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> title.requestFocus());

        Keep resultKeeps = new Keep();

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Keep(text.getText(), title.getText());
            }
            return null;
        });

        //Optional<Pair<String, String>> result = dialog.showAndWait();
        Optional<Keep> resultKeep = dialog.showAndWait();
        resultKeeps = resultKeep.get();

        System.out.println("resultKeeps - " + resultKeeps.getText() + " / " + resultKeeps.getTitle() + " *");
        System.out.println("keepLast    - " + keepLast.getText() + " / " + keepLast.getTitle() + " *");

        keeps.rewriteKeep(keepLast, resultKeeps);

        keepList.getItems().clear();
        keeps();
    }

    /**
     * Обработчик нажатия мыши по ListView содержащий заметки
     * для выбора конкретной заметки
     */
    private void keeps() {
        keepsListTitle = keeps.getKeepList();
        keepsListText = keepsListTitle;
        System.out.println("keepsListTitle - " + keepsListTitle);
        System.out.println("keepsListText - " + keepsListText);
        for (int i = 0; i < keepsListTitle.size(); i++) {
            keepList.getItems().add(keepsListTitle.get(i).getTitle());
        }

        keepList.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                //keepText.setText("clicked on " + keepList.getSelectionModel().getSelectedItem());

                System.out.println("keepList - " + keepList.getItems());

                for (int i = 0; i < keepsListText.size(); i++) {
                    if (keepList.getSelectionModel().getSelectedItem() == keepsListText.get(i).getTitle()) {
                        keepText.setText("" + keepsListText.get(i).getText());
                        keepTitle.setText("" + keepsListText.get(i).getTitle());
                    }
                }


            }
        });
    }

    /**
     * Создание таблицы инфраструктуры ВУЗа
     */
    protected void setTable() {
        facDAO = facF.getFakDao((String) comboBoxInfr.getValue());
        dataList = observableList;
        if (facDAO.getDataList() == null) {
            //onSentNewDataButton();
        }
        observableList = FXCollections.observableList(facDAO.getDataList());
        System.out.println("observableList" + observableList);
        TVfac.setItems(observableList);

    }

    /**
     * Интерактывный поиск инфраструктуры ВУЗа
     */
    protected void onGetSearch() {
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
                if (employee.getName_f().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    //System.out.println("getName_f");
                    return true;
                } else if (employee.getName_k().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    //System.out.println("getName_k");
                    return true;
                } else if (employee.getAdress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    //System.out.println("getAdress");
                    return true;
                } else if (String.valueOf(employee.getMail()).indexOf(lowerCaseFilter) != -1) {
                    //System.out.println("getMail");
                    return true;
                } else
                    return false;
            });
            SortedList<Fac> sortedData = new SortedList<>(filteredData);
            //System.out.println("filteredData - " + filteredData);

            sortedData.comparatorProperty().bind(TVfac.comparatorProperty());
            //System.out.println("sortedData - " + sortedData);

            TVfac.setItems(sortedData);
        });
    }

    /**
     * Обработчик нажатия на WebView
     */
    public void WebViewClick() {
        WebEngine engine = getEngines();
        //System.out.println(engine.getLocation());
        TFurl.setText(engine.getLocation());
    }

    /**
     * Переход на главную страницу WebView
     * по нажатию кнопки Домой
     */
    public void goHome() {
        WebEngine engine = getEngines();
        engine.load("file:///D:/files/CODE/JAVA/PROJECT/Freshman-Navigator-master/src/main/resources/com/example/course2/index.html");
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

    /**
     * Создание WebEngine
     * @return
     */
    public WebEngine getEngines() {
        WebEngine engine = webView.getEngine();
        return engine;
    }

    /**
     * Метод инициализации формы
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combobox.getItems().add("Student");
        combobox.getItems().add("Curator");

        comboBoxInfr.getItems().add("ФК");
        comboBoxInfr.getItems().add("КФ");
        comboBoxInfr.getItems().add("ДР");
        comboBoxInfr.getSelectionModel().selectFirst();

        keeps();

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

    /**
     * Обновление таблицы
     */
    private void table_fac_init() {
        tableDAO = tableF.getTableDAO((String) comboBoxInfr.getValue());
        tableDAO.getTable(TVfac);

        setTable();
    }
}