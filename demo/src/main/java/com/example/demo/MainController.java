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

    @FXML
    public void switchDonut(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("donut-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchCoffee(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("coffee-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchOrderBasket(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("order-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchOrderHistory(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("donut-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
