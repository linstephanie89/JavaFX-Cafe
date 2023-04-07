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

/**
 * Controller class for the Main Menu View.
 * @author Stephanie Lin, Hyeseo Lee
 */
public class MainController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Order order = new Order();

    /**
     * setter method that assigns the passed in Order to the order variable in this class.
     * @param Order representing the current order basket that user is interacting with.
     */
    public void setOrder(Order Order) {
        this.order = Order;
    }
    /**
     * switches the scene from Main View to the Donut View.
     * @param event triggered when the use selects the donut icon.
     * @throws IOException may occur if an input or output operation fails when loading the donut-view FXML file.
     */
    @FXML
    public void switchDonut(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource("donut-view.fxml"));
        root = loader.load();
        DonutController Donut = loader.getController();
        Donut.setOrder(order);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * switches the scene from Main View to the Coffee View.
     * @param event triggered when the use selects the coffee icon.
     * @throws IOException may occur if an input or output operation fails when loading the coffee-view FXML file.
     */
    @FXML
    public void switchCoffee(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource("coffee-view.fxml"));
        root = loader.load();
        CoffeeController Coffee = loader.getController();
        Coffee.setOrder(order);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * switches the scene from Main View to the Order Basket View.
     * @param event triggered when the use selects the shopping cart icon.
     * @throws IOException may occur if an input or output operation fails when loading the order-view FXML file.
     */
    @FXML
    public void switchOrderBasket(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource("order-view.fxml"));
        root = loader.load();
        OrderBasketController orderBasket = loader.getController();
        orderBasket.setOrder(order);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * switches the scene from Main View to the Order History View.
     * @param event triggered when the use selects the order history icon.
     * @throws IOException may occur if an input or output operation fails
     * when loading the order-history-view FXML file.
     */
    @FXML
    public void switchOrderHistory(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource("order-history-view.fxml"));
        root = loader.load();
        OrderHistoryController orderHistory = loader.getController();
        orderHistory.setOrder(order);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
