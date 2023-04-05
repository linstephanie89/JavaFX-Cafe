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
    private Label subTotalLabel;
    private String size;
    private String[] addIns;
    private int initialAddInSize = 0;
    private int quantity;

    @FXML
    public void cupSize(ActionEvent event){
        MenuItem item = (MenuItem) event.getSource();
        size = item.getText();
        selectedSize.setText(size);
    }
    @FXML
    public void setQuantity(ActionEvent event){
        MenuItem item = (MenuItem) event.getSource();
        quantity = Integer.parseInt(item.getText());
        selectedQuantity.setText(item.getText());
    }

    @FXML
    public void add(ActionEvent event){
        Coffee coffee = new Coffee(size,addIns,quantity);

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
        addIns = new String[5];
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

        Coffee coffee = new Coffee(size, addIns, quantity);
        double subTotal = coffee.calcSubTotal();

        subTotalLabel.setText(String.format("$%.2f", subTotal));

    }

}
