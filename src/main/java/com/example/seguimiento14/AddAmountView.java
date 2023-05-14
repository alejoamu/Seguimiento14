package com.example.seguimiento14;

import com.example.seguimiento14.model.Amount;
import com.example.seguimiento14.model.AmountList;
import com.example.seguimiento14.model.AmountType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class AddAmountView implements Initializable {
    @FXML
    private Button addBTN;

    @FXML
    private TextField amountTF;

    @FXML
    private DatePicker dateTF;

    @FXML
    private TextField descriptionTF;

    @FXML
    private Button seeacountBTN;

    @FXML
    private ComboBox<String> typeCB;

    @FXML
    void add(ActionEvent event) {
        try {
            double value = Double.parseDouble(amountTF.getText());
            String description = descriptionTF.getText();
            LocalDate localDate = dateTF.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);

            Amount amount;
            Stage stage;
            String opt = typeCB.getValue();
            switch (opt) {
                case "Entry":
                    amount = new Amount(
                            value, description, date, AmountType.ENTRY
                    );
                    AmountList.getInstance().getAmounts().add(amount);
                    alert();
                    amountTF.clear();
                    descriptionTF.clear();
                    dateTF.getEditor().clear();
                    typeCB.getSelectionModel().clearSelection();
                    break;
                case "Expense":
                    amount = new Amount(
                            value* -1, description, date, AmountType.EXPENSE
                    );
                    AmountList.getInstance().getAmounts().add(amount);
                    alert();
                    amountTF.clear();
                    descriptionTF.clear();
                    dateTF.getEditor().clear();
                    typeCB.getSelectionModel().clearSelection();
                    break;
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Some data is wrong, please check the information");
            alert.showAndWait();
            ex.printStackTrace();
        }
    }

    @FXML
    void see(ActionEvent event) {
        Stage stage = (Stage) addBTN.getScene().getWindow();
        HelloApplication.openWindow("HelloController-View.fxml");
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Condiciones iniciales
        ObservableList olist = FXCollections.observableArrayList();
        olist.add("Entry");
        olist.add("Expense");
        typeCB.setItems(olist);
    }

    public void alert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("ALREADY");
        alert.setContentText("The transaction was successfully added");
        alert.showAndWait();
    }

}

