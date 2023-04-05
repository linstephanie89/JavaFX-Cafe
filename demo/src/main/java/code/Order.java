package code;

public class Order {

    private int orderNumber;
    private orderBasket[] Order;
    private int InitialCapacity = 4;
    private int size;
    private int EMPTY = 0;
    private int GrowthRate = 4;

    public Order(orderBasket[] Order) {
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

    public boolean add(orderBasket orderBasket) {
        if (size == Order.length) {
            grow();
        }
        Order[size] = orderBasket;
        size++;
        return true;
    }





}
