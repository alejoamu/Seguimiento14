package com.example.seguimiento14.model;

import javafx.beans.Observable;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AmountList {
    //Globales
    private ObservableList<Amount> amounts = FXCollections.observableArrayList();

    private ObservableList<Amount> entries = FXCollections.observableArrayList();

    private ObservableList<Amount> expenses = FXCollections.observableArrayList();

    //Constructor privado
    public ObservableList<Amount> getAmounts() {
        return amounts;
    }

    public ObservableList<Amount> entries() {
        return entries;
    }

    public ObservableList<Amount> expenses() {
        return expenses;
    }

    public String getBalance() {
        double counter = 0;
        for (Amount entries : amounts) {
            counter += entries.getAmount();
        }
        String balance = String.valueOf(counter);
        return balance;
    }

    public void setAmounts(ObservableList<Amount> amounts) {
        this.amounts = amounts;
    }


    private AmountList() {
    }

    private static AmountList instance = null;

    public static AmountList getInstance() {
        if (instance == null) {
            instance = new AmountList();
        }
        return instance;
    }


}
