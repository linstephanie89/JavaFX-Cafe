package code;

import javafx.scene.control.Menu;
import java.util.*;

public class Order {
    private MenuItem[] Order;
    private int InitialCapacity = 4;
    private int size;
    private int EMPTY = 0;
    private int GrowthRate = 4;
    private double totalPrice;
    private int orderNumber;

    public Order() {
        this.Order = new MenuItem[InitialCapacity];
        this.size = EMPTY;
        this.totalPrice = EMPTY;
    }

    private void grow() {
        MenuItem[] oldOrder = this.Order;
        this.Order = new MenuItem[this.size + GrowthRate];
        for (int i = 0; i < this.size; i++) {
            this.Order[i] = oldOrder[i];
        }
    }

    public int findItem(MenuItem item) {
        for (int i = 0; i < size; i++) {
            MenuItem currentItem = Order[i];
            if (currentItem instanceof Coffee && item instanceof Coffee && (((Coffee) currentItem).compareAddIns((Coffee)item) == true)) {
                return i;
            } else if (currentItem instanceof Donut && item instanceof Donut) {
                if (((Donut) currentItem).getType().equals(((Donut) item).getType()) && (((Donut) currentItem).getFlavor().equals(((Donut) item).getFlavor()))) {
                    return i;
                }
            }
        }
        return -1;
    }

    public MenuItem returnItem(MenuItem item) {
        int index = findItem(item);
        if (index == -1) {
            return null;
        } else {
            return Order[index];
        }
    }

    public boolean add(MenuItem item) {
        MenuItem currentItem = returnItem(item);
        if (currentItem != null) {
            currentItem.setQuantity(currentItem.getQuantity() + item.getQuantity());
        }
        else {
            if (size == Order.length) {
                grow();
            }
            Order[size] = item;
            size++;
        }
        totalPrice += item.itemPrice();
        return true;
    }

    public boolean remove(MenuItem item) {
        MenuItem currentItem = returnItem(item);
        if (currentItem == null) {
            return false;
        } else {
            if (currentItem.getQuantity() > item.quantity) {
                currentItem.setQuantity(currentItem.getQuantity() - item.getQuantity());
            } else if (currentItem.getQuantity() == item.getQuantity()) {
                int index = findItem(item);
                for (int i = index; i < size-1; i++) {
                    Order[i] = Order[i+1];
                }
                Order[size-1] = null;
                size--;
            } else if (currentItem.getQuantity() < item.getQuantity()) {

            }
            totalPrice -= item.itemPrice();
            return true;
        }
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }
    public String toString() {
        String returnString = "";
        for (int i = 0; i < size; i++) {
            returnString += Order[i].toString()+"\n";
        }
        return returnString;
    }

    public ArrayList<MenuItem> OrderList() {
//        MenuItem[] temporaryList = new MenuItem[size];
//        for (int i = 0; i < size; i++) {
//            MenuItem menuitem = (MenuItem) Order[i];
//            temporaryList[i] = menuitem;
//        }
        ArrayList<MenuItem> OrderList = new ArrayList<MenuItem>(Arrays.asList(Order));
        return OrderList;
    }

    public int getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(int number) {
        this.orderNumber = number;
    }

}
