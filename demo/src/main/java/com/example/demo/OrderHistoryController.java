package com.example.demo;

import code.*;

import code.MenuItem;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.net.URL;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
/**
 * Controller class for the Order History View, allows the user to view all previous orders.
 * Allows user functionalities to cancel orders.
 * @author Stephanie Lin, Hyeseo Lee
 */
public class OrderHistoryController {

    @FXML
    private TableColumn<Order, Integer> orderNumberCol;
    @FXML
    private TableColumn<Order, String> orderItemCol;
    @FXML
    private TableColumn<Order, String> orderPriceCol;
    @FXML
    private Label orderMessage;
    @FXML
    public TableView<Order> orderTable;
    private Order Order;
    private ArrayList<Order> orderList;
    //private orderBasket orderbasket;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public int orderNumber;
    public void setOrder(Order order) {
        this.Order = order;
    }
    /**
     * setter method that assigns the passed in Order to the order variable.
     * @param orderlist representing the current order basket.
     */
    public void setOrderList(ArrayList<Order> orderlist) {
        orderList = orderlist;
    }

    /**
     * fills the TableView in GUI with all orders placed.
     * It will include order number, items in each order, and price of each order.
     * @param event triggered when the user selects View Order button.
     */
    @FXML
    private void viewOrder(ActionEvent event) {
        boolean inOrder = false;
        if (Order != null) {
            for (int i = 0; i < orderList.size(); i++) {
                if (Order.equals(orderList.get(i))) {
                    inOrder = true;
                }
            }
            if (inOrder == false && Order != null
                    && Order.getTotalPrice() != 0) {
                orderList.add(Order);
                int index = orderList.indexOf(Order);
                Order.setOrderNumber(index + 1);
                orderMessage.setText("Ordered");
                Order = new Order();
            }
        }
        ObservableList<Order> list =
                FXCollections.<Order>observableArrayList(orderList);
        orderNumberCol.setCellValueFactory(cellData -> {
            Order rowValue = cellData.getValue();
            String orderString = String.valueOf(rowValue.getOrderNumber());
            IntegerProperty orderNumber =
                    new SimpleIntegerProperty(Integer.parseInt(orderString));
            return orderNumber.asObject();
        });

        orderItemCol.setCellValueFactory(cellData -> {
            Order rowValue = cellData.getValue();
            if (rowValue.getTotalPrice() == 0) {
                return new SimpleStringProperty("Order Canceled.");
            }
            return new SimpleStringProperty(rowValue.toString());
        });

        orderPriceCol.setCellValueFactory(cellData -> {
            Order rowValue = cellData.getValue();
            return new SimpleStringProperty
                    (String.format("$%.2f", rowValue.priceWithTax()));
        });

        orderTable.setItems(list);
    }

    /**
     * Creates a File for the user to save/export.
     * It will also catch errors that may occur when user is creating the txt file.
     */
    public void createFile() {
        try {
            File file = new File("Order History.txt");
            if (file.createNewFile()) {
                orderMessage.setText("Order History File created.");
            } else {
                orderMessage.setText("Order History File found.");
            }
        } catch (IOException e) {
            orderMessage.setText("Error: Can't initialize file");
            e.printStackTrace();
        }
    }

    /**
     * populates the text file with the TableView order in GUI.
     * @param event triggered when user selects Export button.
     */
    @FXML
    public void writeToFile(ActionEvent event) {
        createFile();
        try {
            FileWriter writer = new FileWriter("Order History.txt");
            for (int i = 0; i < orderList.size(); i++) {
                writer.write("Order number "
                        + Integer.toString(i + 1) + ": \n");
                Order orderBasket = orderList.get(i);
                if (orderBasket.getTotalPrice() == 0) {
                    writer.write("Order canceled.\n\n");
                } else {
                    writer.write(orderBasket.toString());
                    writer.write("Total price: " + String.format
                            ("$%.2f", orderBasket.priceWithTax()) + "\n\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            orderMessage.setText("Error: Can't write to file");
            e.printStackTrace();
        }
    }

    /**
     * removes or cancels user selected order from the order history.
     * @param event triggered when the user selects the order and selects Remove button.
     */
    @FXML
    public void removePlacedOrder(ActionEvent event) {
        Order selectedItem = orderTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            int index = orderList.indexOf(selectedItem);
            orderList.set(index, new Order());
            orderList.get(index).setOrderNumber(index + 1);
            orderTable.getItems().remove(selectedItem);
        }
    }

    /**
     * switches the scene from Order History View to the Main View.
     * @param event triggered when the use selects the return icon.
     * @throws IOException may occur if an input or output operation fails.
     * The fail can happen when loading the main-view FXML file.
     */
    @FXML
    public void orderBackToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource
                ("main-view.fxml"));
        root = loader.load();

        MainController main = loader.getController();
        main.setOrder(Order);
        main.setOrderList(orderList);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
