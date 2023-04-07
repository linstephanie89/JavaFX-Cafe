package com.example.demo;
import code.*;
import code.MenuItem;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
/**
 * Controller class for the Order Basket View that allows the user to view their order basket.
 * @author Stephanie Lin, Hyeseo Lee
 */
public class OrderBasketController implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public TableColumn<Coffee, String> addInCol;

    @FXML
    public TableColumn<code.MenuItem, String> itemCol;

    @FXML
    public TableView<code.MenuItem> orderBasketTable;

    @FXML
    public TableColumn<code.MenuItem, Integer> quantityCol;

    @FXML
    public TableColumn<Coffee , String> sizeCol;
    @FXML
    private Text subTotalText;
    @FXML
    private Text salesTaxText;
    @FXML
    private Text TotalText;
    private double tax = 0.06625;

    private Order basketOrder;
    private ArrayList<MenuItem> orderList;
    private ArrayList<Order> placedOrderList;

    /**
     * setter method that assigns the passed in Order to the order variable.
     * @param order representing the current order basket.
     */
    public void setOrder(Order order) {
        this.basketOrder = order;
    }

    /**
     * Setter method that assigns passed in ArrayList of Orders to placedOrderList.
     * @param orderlist representing the ArrayList to update placedOrderList.
     */
    public void setOrderList(ArrayList<Order> orderlist) {
        placedOrderList = orderlist;
    }

    /**
     *initializes the GUI interface and the basket Order.
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        basketOrder=new Order();
        setOrder(basketOrder);
    }

    /**
     * fills the TableView in GUI with the user's order basket items.
     * It will include add ins, size, subtotal, tax, and total.
     */
    @FXML
    public void populate() {
        orderList = basketOrder.OrderList();
        ObservableList<MenuItem> list =
                FXCollections.<MenuItem>observableArrayList(orderList);
        orderBasketTable.setEditable(true);

        setQuantity(list);
        itemCol.setCellValueFactory(cellData -> {
            MenuItem rowValue = cellData.getValue();
            if(rowValue!=null){
                return new SimpleStringProperty(rowValue.getName());
            }else{
                return new SimpleStringProperty("");
            }
        });
        addInCol.setCellValueFactory(cellData -> {
            MenuItem menuItem = cellData.getValue();
            if (menuItem instanceof Coffee) {
                Coffee rowValue = cellData.getValue();
                String addIn = rowValue.getAddInString();
                return new SimpleStringProperty(addIn);
            } else {
                return null;
            }
        });
        sizeCol.setCellValueFactory(cellData -> {
            MenuItem menuItem = cellData.getValue();
            if (menuItem instanceof Coffee) {
                Coffee rowValue = cellData.getValue();
                String size = rowValue.getCupSize();
                return new SimpleStringProperty(size);
            } else {
                return null;
            }
        });
        orderBasketTable.setItems(list);
        updatePrice();
    }

    /**
     * Populates the column for quantity.
     * @param list the ObservableList of MenuItems containing data for item.
     */
    public void setQuantity(ObservableList<MenuItem> list) {
        quantityCol.setCellValueFactory(cellData -> {
            MenuItem rowValue = cellData.getValue();
            if(rowValue!=null){
                String quantityString = String.valueOf(rowValue.getQuantity());
                IntegerProperty quantity = new
                        SimpleIntegerProperty(Integer.parseInt(quantityString));
                return quantity.asObject();
            }else{
                return null;
            }
        });
    }

    /**
     * updates the price value fields once items are added to basket.
     */
    public void updatePrice() {
        double subTotal = basketOrder.getTotalPrice();
        double salesTax = subTotal * tax;
        double total = basketOrder.priceWithTax();

        subTotalText.setText(String.format("$%.2f", subTotal));
        salesTaxText.setText(String.format("$%.2f", salesTax));
        TotalText.setText(String.format("$%.2f",total));
    }

    /**
     * switches the scene from Order Basket View to the Main View.
     * @param event triggered when the use selects the return icon.
     * @throws IOException may occur if an input or output operation fails.
     * The fail can happen when loading the main-view FXML file.
     */
    @FXML
    private void basketBackToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainController.class.getResource
                ("main-view.fxml"));
        root = loader.load();

        MainController main = loader.getController();
        main.setOrder(basketOrder);
        main.setOrderList(placedOrderList);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * switches the scene from Order Basket View to the Order History View.
     * @param event triggered when the use selects the order button.
     * @throws IOException may occur if an input or output operation fails.
     * This can happen when loading the main-view FXML file.
     */
    public void basketOrder(ActionEvent event) throws IOException{
        emptyOrderCoffee();
        emptyOrderDonut();
        emptyOrderMain();

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(MainController.class.getResource
                ("order-history-view.fxml"));
        Parent orderHistoryRoot = loader.load();
        OrderHistoryController Order = loader.getController();
        Order.setOrder(basketOrder);
        Order.setOrderList(placedOrderList);
        Order order = new Order();

        orderList = new ArrayList<MenuItem>();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(orderHistoryRoot);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * passes in a new order object to Coffee controller once the order has been placed.
     * @throws IOException may occur if an input or output operation fails.
     * This can happen when loading the main-view FXML file.
     */
    public void emptyOrderCoffee() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Order order = new Order();

        loader.setLocation(MainController.class.getResource
                ("coffee-view.fxml"));
        Parent coffeeRoot = loader.load();
        CoffeeController coffee = loader.getController();
        coffee.setOrder(order);
    }

    /**
     * passes in a new order object to Donut controller once the order has been placed.
     * @throws IOException may occur if an input or output operation fails.
     * This can happen when loading the main-view FXML file.
     */
    public void emptyOrderDonut() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Order order = new Order();
        loader.setLocation(MainController.class.getResource
                ("donut-view.fxml"));
        Parent donutRoot = loader.load();
        DonutController donut = loader.getController();
        donut.setOrder(order);
    }

    /**
     * passes in a new order object to Main controller once the order has been placed.
     * @throws IOException may occur if an input or output operation fails.
     * This can happen when loading the main-view FXML file.
     */
    public void emptyOrderMain() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Order order = new Order();
        loader.setLocation(MainController.class.getResource
                ("main-view.fxml"));
        Parent mainRoot = loader.load();
        MainController main = loader.getController();
        main.setOrder(order);
    }

    /**
     * allows the user to remove an item from their order basket.
     * @param event triggered when the user selects the remove button.
     */
    @FXML
    public void removeItem(ActionEvent event){
        MenuItem selectedItem = orderBasketTable.getSelectionModel()
                .getSelectedItem();
        if(selectedItem != null){
            basketOrder.remove(selectedItem);
            orderBasketTable.getItems().remove(selectedItem);
            populate();
        }
    }

}
