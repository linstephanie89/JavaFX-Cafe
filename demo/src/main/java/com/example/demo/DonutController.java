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

import java.io.IOException;
import java.net.URL;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Controller class for the Donut View that allows the user to place donut orders.
 * @author Stephanie Lin, Hyeseo Lee
 */
public class DonutController implements Initializable {
    @FXML
    private ComboBox<String> donutComboBox;
    @FXML
    private ImageView donutImage;
    @FXML
    private ListView<String> donutList;
    @FXML
    private TextField quantityInput;
    @FXML
    private Label donutMessage;
    @FXML
    private TextArea subTotal;
    private Order order;
    private int quantity;
    private int validQuantity = 1;
    private int increment = 1;
    private int capacity = 4;
    private String[] donutTypes = {"Yeast", "Cake", "Donut Hole"};
    private Map<String, String[]> flavors = new HashMap<>();
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

    /**
     * Setter method that assigns passed in ArrayList of Orders to orderList.
     * @param orderlist representing the ArrayList to update orderList.
     */
    public void setOrderList(ArrayList<Order> orderlist) {
        orderList = orderlist;
    }

    /**
     * initializes the interface in Donut GUI.
     * @param location  The location used to resolve relative paths for the root, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        donutComboBox.getItems().addAll(donutTypes);
        donutComboBox.setOnAction(event -> {
            String selectedType = donutComboBox.getValue();
            if (selectedType != null) {
                switch (selectedType) {
                    case "Yeast":
                        Image yeastImage = new Image(getClass().getResourceAsStream
                                ("/Images/icons8-yeastDonut-66.png"));
                        donutImage.setImage(yeastImage);
                        String[] yeastFlavors =
                                {"Plain", "Glazed", "Chocolate", "Strawberry",
                                        "Boston Cream", "Powdered Sugar"};
                        donutList.setItems(FXCollections.observableArrayList
                                (yeastFlavors));
                        flavors.put(selectedType, yeastFlavors);
                        break;
                    case "Cake":
                        Image cakeImage = new Image(getClass().getResourceAsStream
                                ("/Images/icons8-cakeDonut-64.png"));
                        donutImage.setImage(cakeImage);
                        String[] cakeFlavors = {"Plain", "Chocolate", "Strawberry"};
                        donutList.setItems(FXCollections.observableArrayList(cakeFlavors));
                        flavors.put(selectedType, cakeFlavors);
                        break;
                    case "Donut Hole":
                        Image donutHoleImage = new Image(getClass().getResourceAsStream
                                ("/Images/icons8-hanukkah-donut-48.png"));
                        donutImage.setImage(donutHoleImage);
                        String[] donutHoleFlavors = {"Plain", "Cinnamon", "Vanilla"};
                        donutList.setItems(FXCollections.
                                observableArrayList(donutHoleFlavors));
                        flavors.put(selectedType, donutHoleFlavors);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * adds the donut order to the order basket and clears the user's selection.
     * @param event triggered when the user selects the add button.
     */
    @FXML
    public void addDonut(ActionEvent event) {
        String selectedType = donutComboBox.getValue();
        String selectedFlavor = donutList.getSelectionModel().
                getSelectedItem();

        if (selectedType == null) {
            donutMessage.setText("Please select the donut type.");
        } else if (selectedFlavor == null) {
            donutMessage.setText("Please select a flavor.");
        } else if (quantityInput.getText().isEmpty()) {
            donutMessage.setText("Please enter a quantity.");
        } else {
            try {
                int quantity = Integer.parseInt(quantityInput.getText());
                if (quantity < validQuantity) {
                    donutMessage.setText("Quantity should be at least 1.");
                } else {
                    Donut donut = new Donut("donut", selectedType,
                            selectedFlavor, quantity);
                    this.order.add(donut);
                    subTotal.setText(String.format("$%.2f",
                            order.getTotalPrice()));
                    donutMessage.setText("Your donut order has been " +
                            "placed successfully!");
                }
            } catch (NumberFormatException e) {
                donutMessage.setText("Please enter a valid quantity.");
            }
        }
    }

    /**
     * removes the donut order from the order basket.
     * @param event triggered when the user selects the remove button.
     */
    @FXML
    public void removeDonut(ActionEvent event) {
        String selectedType = donutComboBox.getValue();
        String selectedFlavor = donutList.getSelectionModel().getSelectedItem();
        if (selectedType == null) {
            donutMessage.setText("Please select the donut type.");
        } else if (selectedFlavor == null) {
            donutMessage.setText("Please select a flavor.");
        } else if (quantityInput.getText().isEmpty()) {
            donutMessage.setText("Please enter a quantity.");
        } else {
            try {
                int quantity = Integer.parseInt(quantityInput.getText());
                if (quantity < validQuantity) {
                    donutMessage.setText("Quantity should be at least 1.");
                } else {
                    Donut donut = new Donut("donut", selectedType,
                            selectedFlavor, quantity);
                    Donut currentItem = (Donut) order.returnItem(donut);
                    if (currentItem == null) {
                        donutMessage.setText("Failed to remove item - " +
                                "no matching order");
                    }
                    if (currentItem != null) {
                        if (currentItem.getQuantity() < quantity) {
                            donutMessage.setText("Failed to remove item - " +
                                    "enter a number less than " +
                                    Integer.toString(currentItem.
                                            getQuantity() + increment));
                        } else {
                            order.remove(donut);
                            subTotal.setText(String.format("$%.2f",
                                    order.getTotalPrice()));
                            donutMessage.setText("Your donut order has been " +
                                    "removed successfully!");
                        }
                    }
                }
            } catch (NumberFormatException e) {
                donutMessage.setText("Please enter a valid quantity.");
            }
        }
    }

    /**
     * switches the scene from Donut View to the Order Basket View.
     * @param event triggered when the use selects the shopping cart icon.
     * @throws IOException may occur if an input or output operation failes.
     *                     This can happen when loading the order-view FXML file.
     */
    @FXML
    public void viewOrderBasket(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource
                ("order-view.fxml"));
        root = loader.load();
        OrderBasketController orderbasket = loader.getController();
        orderbasket.setOrder(order);
        orderbasket.setOrderList(orderList);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * switches the scene from Donut View back to the Main Menu View.
     * @param event triggered when the user selects the return icon.
     * @throws IOException may occur if an input or output operation fails.
     *                     This can happen when loading the main-view FXML file.
     */
    @FXML
    public void backToMainDonut(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource
                ("main-view.fxml"));
        root = loader.load();
        MainController main = loader.getController();
        main.setOrder(order);
        main.setOrderList(orderList);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}