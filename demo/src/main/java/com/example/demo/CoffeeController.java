package com.example.demo;

import code.Coffee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private int initialAddInSize = 0;
    private int quantity;
    private int CAPACITY = 5;

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

    @FXML
    public void add(ActionEvent event){
        if (size == null) {
            coffeeMessage.setText("Please select cup size.");
        } else if (quantityString == null) {
            coffeeMessage.setText("Please enter a quantity.");
        } else {
            Coffee coffee = new Coffee("Coffee",size,addIns,quantity);

        }

        caramel.setSelected(false);
        frenchVanilla.setSelected(false);
        irishCream.setSelected(false);
        mocha.setSelected(false);
        sweetCream.setSelected(false);
        selectedQuantity.setText("Select Quantity");
        selectedSize.setText("Select Size");

        updateSubTotal();


    }

    private void updateSubTotal(){
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

        Coffee coffee = new Coffee("Coffee", size, addIns, quantity);
        double subTotal = coffee.itemPrice();

        subTotalCoffee.setText(String.format("$%.2f", subTotal));

    }

}
