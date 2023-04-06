package com.example.demo;
import code.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class MainController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Order order = new Order();
    //private orderBasket orderbasket = new orderBasket();

    // public void setOrderBasket(orderBasket orderBasket) {
    //     this.orderbasket = orderBasket;
    // }

    public void setOrder(Order Order) {
        this.order = Order;
    }

    @FXML
    public void switchDonut(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource("donut-view.fxml"));
        root = loader.load();
        DonutController Donut = loader.getController();
        //Donut.setOrderBasket(orderbasket);
        Donut.setOrder(order);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchCoffee(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource("coffee-view.fxml"));
        root = loader.load();
        CoffeeController Coffee = loader.getController();
        //Coffee.setOrderBasket(orderbasket);
        Coffee.setOrder(order);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchOrderBasket(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource("order-view.fxml"));
        root = loader.load();
        OrderBasketController orderBasket = loader.getController();
        //orderBasket.setOrderBasket(orderbasket);
        orderBasket.setOrder(order);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchOrderHistory(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource("order-history-view.fxml"));
        root = loader.load();
        OrderHistoryController orderHistory = loader.getController();
        orderHistory.setOrder(order);
        //orderHistory.setOrderBasket(orderbasket);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
