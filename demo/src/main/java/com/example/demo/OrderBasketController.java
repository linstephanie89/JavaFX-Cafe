package com.example.demo;
import code.orderBasket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderBasketController implements Initializable {
    @FXML
    private static TableView<MenuItem> orderBasketView;
    @FXML
    private TableColumn<MenuItem,String> AddInsTable;
    @FXML
    private TableColumn<MenuItem,String> SizeTable;
    @FXML
    private TableColumn<MenuItem,Integer> quantityTable;
    @FXML
    private TableColumn<MenuItem,String> itemTable;
    private orderBasket orderBasket;
    public void setOrderBasket(orderBasket orderbasket) {
        this.orderBasket = orderbasket;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        quantityTable.setCellValueFactory(new PropertyValueFactory<MenuItem,Integer>("quant"));
        itemTable.setCellValueFactory(new PropertyValueFactory<MenuItem,String>("item"));
        AddInsTable.setCellValueFactory(new PropertyValueFactory<MenuItem,String>("addins"));
        SizeTable.setCellValueFactory(new PropertyValueFactory<MenuItem,String>("size"));
    }

    public static void showBasketItems(ObservableList<MenuItem> orderBasket) {
        ObservableList<MenuItem> observableOrderBasket = FXCollections.observableArrayList(orderBasket);
        // set the table view items to the observable list
        orderBasketView.setItems(observableOrderBasket);
    }
}
