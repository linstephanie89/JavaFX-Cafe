package com.example.demo;
import code.*;
import code.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class OrderBasketController implements Initializable{
    private Stage stage;
    private Scene scene;

    @FXML
    private TableColumn<code.MenuItem, String> addInCol;

    @FXML
    private TableColumn<code.MenuItem, String> itemCol;

    @FXML
    private TableView<code.MenuItem> orderBasketTable;

    @FXML
    private TableColumn<code.MenuItem, Integer> quantityCol;

    @FXML
    private TableColumn<code.MenuItem , String> sizeCol;

    private orderBasket orderBasket;
    public void setOrderBasket(orderBasket orderbasket) {
        System.out.println("called");
        this.orderBasket = orderbasket;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<code.MenuItem> orderList = this.orderBasket.orderBasketList();
        orderBasketTable.setEditable(true);
        ObservableList<code.MenuItem> list = FXCollections.<code.MenuItem>observableArrayList();
        list.addAll(orderList);
        quantityCol.setCellValueFactory(new PropertyValueFactory<code.MenuItem, Integer>("quantity"));
        itemCol.setCellValueFactory(new PropertyValueFactory<code.MenuItem, String>("name"));
        addInCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("addIns"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<code.MenuItem, String>("size"));
        orderBasketTable.setItems(list);
        orderBasketTable.getColumns().addAll(quantityCol, itemCol, addInCol, sizeCol);

        stage.setScene(scene);
        stage.show();
    }

    public void showBasketItems(List<MenuItem> items){

    }

}
