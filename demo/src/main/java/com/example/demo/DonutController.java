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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DonutController implements Initializable {
    @FXML
    private ComboBox<String> donutComboBox;
    @FXML
    private ImageView donutImage;
    @FXML
    private ListView <String> donutList;
    @FXML
    private TextField quantityInput;
    @FXML
    private Button addButton;

    private Order orderBasket;
    private int orderNum = 1;
    private int capacity = 4;
    private String[] donutTypes = {"Yeast", "Cake", "Donut Hole"};
    private Map<String, String[]> flavors = new HashMap<>();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       orderBasket = new Order(orderNum, new MenuItem[4]);
        donutComboBox.getItems().addAll(donutTypes);
        donutComboBox.setOnAction(event -> {
            String selectedType = donutComboBox.getValue();
            if(selectedType != null){
                switch (selectedType){
                    case "Yeast":
                        Image yeastImage= new Image(getClass().getResourceAsStream("/Images/icons8-yeastDonut-66.png"));
                        donutImage.setImage(yeastImage);
                        String[] yeastFlavors = {"Plain", "Glazed", "Chocolate", "Strawberry", "Boston Cream", "Powdered Sugar"};
                        donutList.setItems(FXCollections.observableArrayList(yeastFlavors));
                        flavors.put(selectedType,yeastFlavors);
                        break;
                    case "Cake":
                        Image cakeImage= new Image(getClass().getResourceAsStream("/Images/icons8-cakeDonut-64.png"));
                        donutImage.setImage(cakeImage);
                        String[] cakeFlavors = {"Plain","Chocolate", "Strawberry"};
                        donutList.setItems(FXCollections.observableArrayList(cakeFlavors));
                        flavors.put(selectedType,cakeFlavors);
                        break;
                    case "Donut Hole":
                        Image donutHoleImage = new Image(getClass().getResourceAsStream("/Images/icons8-hanukkah-donut-48.png"));
                        donutImage.setImage(donutHoleImage);
                        String[] donutHoleFlavors = {"Plain", "Cinnamon", "Vanilla"};
                        donutList.setItems(FXCollections.observableArrayList(donutHoleFlavors));
                        flavors.put(selectedType,donutHoleFlavors);
                        break;
                    default:
                        break;
                }
            }
        });
    }
    @FXML
    public void addDonut(ActionEvent event){
        String selectedType = donutComboBox.getValue();
        String selectedFlavor = donutList.getSelectionModel().getSelectedItem();
        int quantity = Integer.parseInt(quantityInput.getText());

        if(selectedType != null && selectedFlavor != null && quantity >0){
            Donut donut = new Donut("donut", selectedType, selectedFlavor);
            donut.setQuantity(quantity);
            orderBasket.add(donut);
            //calculate subTotal
            //clear selection
        }
    }
    @FXML
    public void viewOrderBasket(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("order-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        OrderBasketController.showBasketItems((ObservableList<javafx.scene.control.MenuItem>) orderBasket);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//    private double calcSubTotal(){
//        double subTotal = 0;
//        for(Donut donut: orderBasket){
//            subTotal+=donut.itemPrice();
//        }
//        return subTotal;
//    }
}