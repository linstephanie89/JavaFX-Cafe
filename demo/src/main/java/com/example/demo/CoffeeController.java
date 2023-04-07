package com.example.demo;

import code.Coffee;
import code.Donut;
import code.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller class for the Coffee View that allows the user to place coffee orders.
 * @author Stephanie Lin, Hyeseo Lee
 */
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
    private Order order;
    private int initialAddInSize = 0;
    private int quantity;
    private int CAPACITY = 5;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private ArrayList<Order> orderList;

    /**
     * setter method that assigns the passed in Order to the order variable.
     * @param Order representing the current order basket.
     */
    public void setOrder(Order Order) {
        this.order = Order;
    }
    public void setOrderList(ArrayList<Order> orderlist) {
        orderList = orderlist;
    }

    /**
     * assigns the cup size user selects to the variable size and
     * changes the MenuItem text to display the user's selection in GUI.
     * @param event triggered when user selects a cup size from the MenuItem drop down.
     */
    @FXML
    public void cupSize(ActionEvent event){
        MenuItem item = (MenuItem) event.getSource();
        size = item.getText();
        selectedSize.setText(size);
    }

    /**
     * assigns the quantity user selects the variable quantity and
     * changes the MenuItem text to display the user's selection in GUI.
     * @param event triggered when user selects a quantity from the MenuItem drop down.
     */
    @FXML
    public void setQuantity(ActionEvent event){
        MenuItem item = (MenuItem) event.getSource();
        quantityString = item.getText();
        quantity = Integer.parseInt(quantityString);
        selectedQuantity.setText(item.getText());
    }

    /**
     * create a Coffee object with the user's selections.
     * @return a Coffee object that holds the attributes based on user's selection.
     */
    private Coffee createCoffee(){
        String[] addIns = new String[CAPACITY];
        if(sweetCream.isSelected()){
            addIns[0] = "Sweet Cream";
        }else if(frenchVanilla.isSelected()){
            addIns[1] = "French Vanilla";
        }else if(irishCream.isSelected()){
            addIns[2]= "Irish Cream";
        }else if(caramel.isSelected()){
            addIns[3] = "Caramel";
        }else if(mocha.isSelected()){
            addIns[4] = "Mocha";
        }
        Coffee coffee = new Coffee("Coffee", size, addIns, quantity);
        return coffee;
    }
    /**
     * adds the coffee order to the order basket and clears the user's selection.
     * @param event triggered when the user selects the add button.
     */
    @FXML
    public void addCoffee(ActionEvent event){
        if (size == null) {
            coffeeMessage.setText("Please select cup size.");
        } else if (quantityString == null) {
            coffeeMessage.setText("Please select a quantity.");
        } else {
            Coffee coffee = createCoffee();
            order.add(coffee);
            double subTotal = order.getTotalPrice();
            subTotalCoffee.setText(String.format("$%.2f", subTotal));
            coffeeMessage.setText("Your coffee order has been placed" +
                    " successfully!");
        }
        caramel.setSelected(false);
        frenchVanilla.setSelected(false);
        irishCream.setSelected(false);
        mocha.setSelected(false);
        sweetCream.setSelected(false);
        selectedQuantity.setText("Select Quantity");
        selectedSize.setText("Select Size");


    }

    /**
     * removes the coffee order from the order basket based on user's specifications.
     * @param event triggered when user selects the remove button.
     */
    @FXML
    public void removeCoffee(ActionEvent event) {
        if (size == null) {
            coffeeMessage.setText("Please select cup size.");
        } else if (quantityString == null) {
            coffeeMessage.setText("Please enter a quantity.");
        } else {
            Coffee coffee = createCoffee();
            Coffee currentItem = (Coffee) order.returnItem(coffee);
            if (currentItem == null) {
                coffeeMessage.setText("Failed to remove item - " +
                        "no matching order");
            }
            if (currentItem != null) {
                if (currentItem.getQuantity() < quantity) {
                    coffeeMessage.setText("Failed to remove item - " +
                            "enter a number less than " +
                            Integer.toString(currentItem.getQuantity() + 1));
                } else {
                    order.remove(coffee);
                    subTotalCoffee.setText(String.format("$%.2f",
                            order.getTotalPrice()));
                    coffeeMessage.setText("Your coffee order has been " +
                            "removed successfully!");
                }

            }

        }
    }
    /**
     * switches the scene from Coffee View back to the Main Menu View.
     * @param event triggered when the user selects the return icon.
     * @throws IOException may occur if an input or output operation fails.
     * This can happen when loading the main-view FXML file.
     */
    @FXML
    public void backToMainCoffee(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource
                ("main-view.fxml"));
        root = loader.load();

        MainController main = loader.getController();
        main.setOrder(order);
        main.setOrderList(orderList);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * switches the scene from Coffee View to the Order Basket View.
     * @param event triggered when the use selects the shopping cart icon.
     * @throws IOException may occur if an input or output operation failes.
     * This can happen when loading the order-view FXML file.
     */
    @FXML
    public void viewCoffeeOrderBasket(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource
                ("order-view.fxml"));
        root = loader.load();
        OrderBasketController orderbasket = loader.getController();
        //orderbasket.setOrderBasket(orderBasket);
        orderbasket.setOrder(order);
        orderbasket.setOrderList(orderList);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
