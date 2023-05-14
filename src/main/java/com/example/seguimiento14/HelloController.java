package com.example.seguimiento14;

import com.example.seguimiento14.model.Amount;
import com.example.seguimiento14.model.AmountList;
import com.example.seguimiento14.model.AmountType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TableView<Amount> acountTV;

    @FXML
    private Button addBTN;

    @FXML
    private Button deleteBTN;

    @FXML
    private TableColumn<Amount, Double> amountTC;

    @FXML
    private TableColumn<Amount, Date> dateTC;

    @FXML
    private TableColumn<Amount, String> descriptionTC;

    @FXML
    private Label balanceLabel;

    @FXML
    private Button vBalanceBTN;

    @FXML
    private Button vAllBTN;

    @FXML
    private Button vExpensesBTN;

    @FXML
    private Button vIncomeBTN;

    @FXML
    void add(ActionEvent event) {
        Stage stage = (Stage) addBTN.getScene().getWindow();
        HelloApplication.openWindow("Add-View.fxml");
        stage.close();
    }

    @FXML
    void delete(ActionEvent event) {
        Amount amount = this.acountTV.getSelectionModel().getSelectedItem();

        if (amount == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("You need to select a transaction to delete");
            alert.showAndWait();
        }else {
            AmountList.getInstance().getAmounts().remove(amount);
            this.acountTV.refresh();
        }
    }

    @FXML
    void seleccionar(MouseEvent event) {
        Amount amount = this.acountTV.getSelectionModel().getSelectedItem();
    }
    @FXML
    void viewBalance(ActionEvent event) {
        balanceLabel.setText(AmountList.getInstance().getBalance());
    }

    @FXML
    void viewAll(ActionEvent event) {
        acountTV.setItems(
                AmountList.getInstance().getAmounts()
        );
    }

    @FXML
    void viewExpenses(ActionEvent event) {
        AmountList.getInstance().expenses().clear();
        for (Amount a : AmountList.getInstance().getAmounts()) {
            if (a.getAmountType() == AmountType.EXPENSE) {
                AmountList.getInstance().expenses().add(a);
            }
        }
        acountTV.setItems(AmountList.getInstance().expenses());
    }

    @FXML
    void viewIncome(ActionEvent event) {
        AmountList.getInstance().entries().clear();
        for (Amount a : AmountList.getInstance().getAmounts()) {
            if (a.getAmountType() == AmountType.ENTRY) {
                AmountList.getInstance().entries().add(a);
            }
        }
        acountTV.setItems(AmountList.getInstance().entries());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        amountTC.setCellValueFactory(new PropertyValueFactory<>("amount"));
        descriptionTC.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateTC.setCellValueFactory(new PropertyValueFactory<>("date"));

        acountTV.setItems(
                AmountList.getInstance().getAmounts()
        );
    }


}

