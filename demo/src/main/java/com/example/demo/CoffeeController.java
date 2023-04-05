package com.example.demo;

import code.Coffee;
import code.Donut;
import code.orderBasket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class CoffeeController {
    @FXML
    private CheckBox caramel;
    @FXML
    private CheckBox frenchVanilla;
    @FXML
    private CheckBox irishCream;
    @FXML
    private CheckBox mocha;
    @FXML
    private CheckBox sweetCream;
    @FXML
    private MenuButton selectedQuantity;
    @FXML
    private MenuButton selectedSize;
    @FXML
    private Label coffeeMessage;
    @FXML
    private TextArea subTotalCoffee;
    private String size;
    private String quantityString;
    private String[] addIns;
    private orderBasket orderBasket = new orderBasket();
    private int initialAddInSize = 0;
    private int quantity;
    private int CAPACITY = 5;
    private Stage stage;
    private Scene scene;

    @FXML
    public void cupSize(ActionEvent event){
        MenuItem item = (MenuItem) event.getSource();
        size = item.getText();
        selectedSize.setText(size);
    }
    @FXML
    public void setQuantity(ActionEvent event){
        MenuItem item = (MenuItem) event.getSource();
        quantityString = item.getText();
        selectedQuantity.setText(item.getText());
    }
    private void addToBasket(String size, int quantity){
        addIns = new String[CAPACITY];
        int index = initialAddInSize;

        if(sweetCream.isSelected()){
            addIns[index] = "Sweet Cream";
            index++;
        }else if(frenchVanilla.isSelected()){
            addIns[index] = "French Vanilla";
            index++;
        }else if(irishCream.isSelected()){
            addIns[index]= "Irish Cream";
            index++;
        }else if(caramel.isSelected()){
            addIns[index] = "Caramel";
            index++;
        }else if(mocha.isSelected()){
            addIns[index] = "Mocha";
            index++;
        }
        quantity = Integer.parseInt(quantityString);
        Coffee coffee = new Coffee("Coffee", size, addIns, quantity);
        orderBasket.add(coffee);
        double subTotal = orderBasket.getTotalPrice();
        subTotalCoffee.setText(String.format("$%.2f", subTotal));
    }
    @FXML
    public void addCoffee(ActionEvent event){
        if (size == null) {
            coffeeMessage.setText("Please select cup size.");
        } else if (quantityString == null) {
            coffeeMessage.setText("Please enter a quantity.");
        } else {
            addToBasket(size, quantity);
            coffeeMessage.setText("Your coffee order has been placed successfully!");
        }

        caramel.setSelected(false);
        frenchVanilla.setSelected(false);
        irishCream.setSelected(false);
        mocha.setSelected(false);
        sweetCream.setSelected(false);
        selectedQuantity.setText("Select Quantity");
        selectedSize.setText("Select Size");

        //updateSubTotal();

    }

    @FXML
    public void removeCoffee(ActionEvent event) {

    }
    @FXML
    public void backToMainCoffee(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
