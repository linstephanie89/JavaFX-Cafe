package com.example.demo;
import code.*;
import code.MenuItem;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class OrderBasketController implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;
    private double tax = 0.06625;

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
    @FXML
    private Text subTotalText;
    @FXML
    private Text salesTaxText;
    @FXML
    private Text TotalText;

    //private orderBasket orderBasket;
    private Order basketOrder;
    //private Order order;
//     public void setOrderBasket(orderBasket orderbasket) {
//         System.out.println("called");
//         this.orderBasket = orderbasket;
//     }

    public void setOrder(Order order) {
        this.basketOrder = order;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //populate();
        basketOrder=new Order();
        setOrder(basketOrder);
    }
    @FXML
    public void populate() {
        ArrayList<MenuItem> orderList = this.basketOrder.OrderList();
        ObservableList<MenuItem> list = FXCollections.<MenuItem>observableArrayList(orderList);
        System.out.println(list);
        orderBasketTable.setEditable(true);
        quantityCol.setCellValueFactory(cellData -> {
            MenuItem rowValue = cellData.getValue();
            if(rowValue!=null){
                String quantityString = String.valueOf(rowValue.getQuantity());
                IntegerProperty quantity = new SimpleIntegerProperty(Integer.parseInt(quantityString));
                return quantity.asObject();
            }else{
                return null;
            }
//            int quantity = rowValue.getQuantity();
//            return new SimpleIntegerProperty(quantity).asObject();
        });
        itemCol.setCellValueFactory(cellData -> {
            MenuItem rowValue = cellData.getValue();
            if(rowValue!=null){
                return new SimpleStringProperty(rowValue.getName());
            }else{
                return new SimpleStringProperty("");
            }
//            String name = rowValue.getName();
//            return new SimpleStringProperty(name);
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

        double subTotal = basketOrder.getTotalPrice();
        double salesTax = subTotal *tax;
        double total = subTotal+salesTax;

        subTotalText.setText(String.format("$%.2f", subTotal));
        salesTaxText.setText(String.format("$%.2f", salesTax));
        TotalText.setText(String.format("$%.2f",total));
    }

    @FXML
    private void basketBackToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource("main-view.fxml"));
        root = loader.load();

        MainController main = loader.getController();
        main.setOrder(basketOrder);

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
        basketOrder = null;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void removeItem(ActionEvent event){
        MenuItem selectedItem = orderBasketTable.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            basketOrder.remove(selectedItem);
            orderBasketTable.getItems().remove(selectedItem);
        }
    }

}
