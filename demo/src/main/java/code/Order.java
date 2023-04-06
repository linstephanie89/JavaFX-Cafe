package code;


public class Order {

    private int orderNumber;
    private orderBasket[] Order;
    private int InitialCapacity = 4;
    private int size;
    private int EMPTY = 0;
    private int GrowthRate = 4;

    public Order() {
        this.Order = new orderBasket[InitialCapacity];
        this.size = EMPTY;
    }

    private void grow() {
        orderBasket[] oldOrder = this.Order;
        this.Order = new orderBasket[this.size + GrowthRate];
        for (int i = 0; i < this.size; i++) {
            this.Order[i] = oldOrder[i];
        }
    }

    public int find(orderBasket orderBasket) {
        for (int i = 0; i < size; i++) {
            if(Order[i].equals(orderBasket)) {
                return i;
            }
        }
        return -1;
    }
    public boolean add(orderBasket orderBasket) {
        if (size == Order.length) {
            grow();
        }
        Order[size] = orderBasket;
        size++;
        return true;
    }

    public boolean remove(orderBasket orderBasket) {
        if (find(orderBasket) == -1) {
            return false;
        } else {
            Order[find(orderBasket)] = null;
        }
        return true;
    }

    public int getSize() {
        return this.size;
    }

    public orderBasket getOrderBasket(int index) {
        return Order[index];
    }



}
