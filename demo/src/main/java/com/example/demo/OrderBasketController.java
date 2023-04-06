package com.example.demo;
import code.*;
import code.MenuItem;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class OrderBasketController implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public TableColumn<Coffee, String> addInCol;

    @FXML
    public TableColumn<code.MenuItem, String> itemCol;

    @FXML
    public TableView<code.MenuItem> orderBasketTable;

    @FXML
    public TableColumn<code.MenuItem, Integer> quantityCol;

    @FXML
    public TableColumn<Coffee , String> sizeCol;

    //private orderBasket orderBasket;
    private Order basketOrder;
    // public void setOrderBasket(orderBasket orderbasket) {
    //     System.out.println("called");
    //     this.orderBasket = orderbasket;
    // }

    public void setOrder(Order order) {
        this.basketOrder = order;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void populate() {
        ArrayList<code.MenuItem> orderList = this.order.orderList();
        ObservableList<code.MenuItem> list = FXCollections.<code.MenuItem>observableArrayList(orderList);
        System.out.println(list);
        orderBasketTable.setEditable(true);
        quantityCol.setCellValueFactory(cellData -> {
            MenuItem rowValue = cellData.getValue();
            int quantity = rowValue.getQuantity();
            return new SimpleIntegerProperty(quantity).asObject();
        });
        itemCol.setCellValueFactory(cellData -> {
            MenuItem rowValue = cellData.getValue();
            String name = rowValue.getName();
            return new SimpleStringProperty(name);
        });
        addInCol.setCellValueFactory(cellData -> {
            MenuItem menuItem = cellData.getValue();
            if (menuItem instanceof Coffee) {
                Coffee rowValue = cellData.getValue();
                String addIn = rowValue.getAddInString();
                return new SimpleStringProperty(addIn);
            } else {
                return null;
            }
        });
        sizeCol.setCellValueFactory(cellData -> {
            MenuItem menuItem = cellData.getValue();
            if (menuItem instanceof Coffee) {
                Coffee rowValue = cellData.getValue();
                String size = rowValue.getCupSize();
                return new SimpleStringProperty(size);
            } else {
                return null;
            }
        });

        orderBasketTable.setItems(list);
        //orderBasketTable.getColumns().addAll(quantityCol, itemCol, addInCol, sizeCol);
        //stage.setScene(scene);
        //stage.show();
    }

    @FXML
    private void basketBackToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource("main-view.fxml"));
        root = loader.load();

        MainController main = loader.getController();
        main.setOrder(order);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void basketOrder(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource("order-history-view.fxml"));
        root = loader.load();
        OrderHistoryController orderHistory = loader.getController();
        orderHistory.setOrder(basketOrder);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
