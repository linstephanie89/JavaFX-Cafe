package com.example.demo;
import code.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrderBasketController implements Initializable{
    @FXML
    private TableColumn<orderBasket, String> addInCol;

    @FXML
    private TableColumn<orderBasket, String> itemCol;

    @FXML
    private TableView<orderBasket> orderBasketTable;

    @FXML
    private TableColumn<orderBasket, String> quantityCol;

    @FXML
    private TableColumn<orderBasket, String> sizeCol;

    private orderBasket orderBasket;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<orderBasket> list = FXCollections.observableArrayList();
        orderBasket basket = new orderBasket();
        list.add(basket);

        orderBasketTable.setItems(list);

        quantityCol.setCellValueFactory(new PropertyValueFactory<orderBasket, String>("quantity"));
        itemCol.setCellValueFactory(new PropertyValueFactory<orderBasket, String>("item"));
        addInCol.setCellValueFactory(new PropertyValueFactory<orderBasket, String>("addIns"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<orderBasket, String>("size"));
    }
    public void setOrderBasket(orderBasket orderbasket) {
        this.orderBasket = orderbasket;
    }

    public void showBasketItems(List<MenuItem> items){
        orderBasket = new orderBasket();
        for(MenuItem item: items){
           orderBasket.add(item.getText(), item.getId(), item.getParentPopup().getScene().getRoot().toString());
        }
        orderBasketTable.setItems(orderBasketTable.getItems());
    }

}
