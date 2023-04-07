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

public class OrderHistoryController {

    @FXML
    private TableColumn<Order, Integer> orderNumberCol;
    @FXML
    private TableColumn<Order, String> orderItemCol;
    @FXML
    private TableColumn<Order, Double> orderPriceCol;
    @FXML
    private Label orderMessage;
    @FXML
    public TableView<Order> orderTable;
    private Order Order;
    private ArrayList<Order> orderList = new ArrayList<>();
    //private orderBasket orderbasket;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void setOrder(Order order) {
        this.Order = order;
    }
//    public void setOrderBasket(orderBasket orderBasket) {
//        this.orderbasket = orderBasket;
//    }

    @FXML
    private void viewOrder(ActionEvent event) {
        boolean inOrder = false;
        if (Order != null) {
            for (int i = 0; i < orderList.size(); i++) {
                if (Order.equals(orderList.get(i))) {
                    inOrder = true;
                }
            }
            if (inOrder == false) {
                orderList.add(Order);
            }
        }

        ObservableList<Order> list = FXCollections.<Order>observableArrayList(orderList);

        orderNumberCol.setCellValueFactory(cellData -> {
            Order rowValue = cellData.getValue();
            String orderString = String.valueOf(rowValue.getOrderNumber());
            IntegerProperty orderNumber = new SimpleIntegerProperty(Integer.parseInt(orderString));
            return orderNumber.asObject();
        });

        orderItemCol.setCellValueFactory(cellData -> {
            Order rowValue = cellData.getValue();
            return new SimpleStringProperty(rowValue.toString());
        });

        orderPriceCol.setCellValueFactory(cellData -> {
            Order rowValue = cellData.getValue();
            String orderString = String.valueOf(rowValue.getTotalPrice());
            DoubleProperty orderPrice = new SimpleDoubleProperty(Double.parseDouble(orderString));
            return orderPrice.asObject();
        });

        orderTable.setItems(list);
    }

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

    @FXML
    public void writeToFile(ActionEvent event) {
        createFile();
        try{
            FileWriter writer = new FileWriter("Order History.txt");
            for (int i = 0; i < orderList.size(); i++) {
                writer.write("Order number " + Integer.toString(i) + ": ");
                Order orderBasket = orderList.get(i);
                if (orderBasket == null) {
                    writer.write("Order canceled.\n");
                } else {
                    writer.write(orderBasket.toString());
                }
            }
            writer.close();
        } catch (IOException e) {
            orderMessage.setText("Error: Can't write to file");
            e.printStackTrace();
        }
    }

    @FXML
    public void orderBackToMain(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource("main-view.fxml"));
        root = loader.load();

        MainController main = loader.getController();
        main.setOrder(Order);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
