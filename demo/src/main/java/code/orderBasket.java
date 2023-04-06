package code;

import javafx.scene.control.Menu;
import java.util.*;

public class orderBasket {
    private MenuItem[] orderBasket;
    private int InitialCapacity = 4;
    private int size;
    private int EMPTY = 0;
    private int GrowthRate = 4;
    private double totalPrice;

    public orderBasket() {
        this.orderBasket = new MenuItem[InitialCapacity];
        this.size = EMPTY;
        this.totalPrice = EMPTY;
    }

    private void grow() {
        MenuItem[] oldOrderBasket = this.orderBasket;
        this.orderBasket = new MenuItem[this.size + GrowthRate];
        for (int i = 0; i < this.size; i++) {
            this.orderBasket[i] = oldOrderBasket[i];
        }
    }

    public int findItem(MenuItem item) {
        for (int i = 0; i < size; i++) {
            MenuItem currentItem = orderBasket[i];
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
            return orderBasket[index];
        }
    }

    public boolean add(MenuItem item) {
        MenuItem currentItem = returnItem(item);
        if (currentItem != null) {
            currentItem.setQuantity(currentItem.getQuantity() + item.getQuantity());
        }
        else {
            if (size == orderBasket.length) {
                grow();
            }
            orderBasket[size] = item;
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
                    orderBasket[i] = orderBasket[i+1];
                }
                orderBasket[size-1] = null;
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
            returnString += orderBasket[i].toString()+"\n";
        }
        return returnString;
    }

    public ArrayList<MenuItem> orderBasketList() {
        MenuItem[] temporaryList = new MenuItem[size];
        for (int i = 0; i < size; i++) {
            MenuItem menuitem = (MenuItem) orderBasket[i];
            temporaryList[i] = menuitem;
        }
        ArrayList<MenuItem> orderBasketList = new ArrayList<MenuItem>(Arrays.asList(temporaryList));
        return orderBasketList;
    }

}
