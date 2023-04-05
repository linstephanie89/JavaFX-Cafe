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
    //private String[] addIns;
    private orderBasket orderBasket;
    private int initialAddInSize = 0;
    private int quantity;
    private int CAPACITY = 5;
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void setOrderBasket(orderBasket orderbasket) {
        orderBasket = orderbasket;
    }

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
        quantity = Integer.parseInt(quantityString);
        selectedQuantity.setText(item.getText());
    }
    private Coffee createCoffee(){
        String[] addIns = new String[CAPACITY];
        //int index = initialAddInSize;

        if(sweetCream.isSelected()){
            addIns[0] = "Sweet Cream";
            //index++;
        }else if(frenchVanilla.isSelected()){
            addIns[1] = "French Vanilla";
            //index++;
        }else if(irishCream.isSelected()){
            addIns[2]= "Irish Cream";
            //index++;
        }else if(caramel.isSelected()){
            addIns[3] = "Caramel";
            //index++;
        }else if(mocha.isSelected()){
            addIns[4] = "Mocha";
            //index++;
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(addIns[i]);
        }

        Coffee coffee = new Coffee("Coffee", size, addIns, quantity);

        return coffee;
    }
    @FXML
    public void addCoffee(ActionEvent event){
        if (size == null) {
            coffeeMessage.setText("Please select cup size.");
        } else if (quantityString == null) {
            coffeeMessage.setText("Please enter a quantity.");
        } else {
            Coffee coffee = createCoffee();
            orderBasket.add(coffee);
            double subTotal = orderBasket.getTotalPrice();
            subTotalCoffee.setText(String.format("$%.2f", subTotal));
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
        if (size == null) {
            coffeeMessage.setText("Please select cup size.");
        } else if (quantityString == null) {
            coffeeMessage.setText("Please enter a quantity.");
        } else {
            Coffee coffee = createCoffee();
            Coffee currentItem = (Coffee) orderBasket.returnItem(coffee);
            if (currentItem == null) {
                coffeeMessage.setText("Failed to remove item - no matching order");
            }
            if (currentItem != null) {
                if (currentItem.getQuantity() < quantity) {
                    coffeeMessage.setText("Failed to remove item - enter a number less than " + Integer.toString(currentItem.getQuantity() + 1));
                } else {
                    orderBasket.remove(coffee);
                    subTotalCoffee.setText(String.format("$%.2f", orderBasket.getTotalPrice()));
                    coffeeMessage.setText("Your coffee order has been removed successfully!");
                }

            }

        }
    }
    @FXML
    public void backToMainCoffee(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource("main-view.fxml"));
        root = loader.load();

        MainController main = loader.getController();
        main.setOrderBasket(orderBasket);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
