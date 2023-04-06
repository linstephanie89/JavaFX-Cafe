package com.example.demo;
import code.*;

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
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class OrderHistoryController {

    @FXML
    private TableColumn<orderBasket, String> orderNumberCol;
    @FXML
    private TableColumn<orderBasket, String> orderItemCol;
    @FXML
    private TableColumn<orderBasket, String> orderPriceCol;
    @FXML
    private Label orderMessage;
    private Order Order;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void setOrder(Order order) {
        this.Order = order;
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
            for (int i = 0; i < Order.getSize(); i++) {
                writer.write("Order number " + Integer.toString(i) + ": ");
                orderBasket orderBasket = Order.getOrderBasket(i);
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
