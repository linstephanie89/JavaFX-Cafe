package code;

public class Order {
    private int orderNumber;
    private MenuItem[] orderBasket;
    private int InitialCapacity = 4;
    private int size;
    private int EMPTY = 0;
    private int GrowthRate = 4;

    public Order(int orderNumber, MenuItem[] orderBasket){
        this.orderNumber = orderNumber;
        this.orderBasket = new MenuItem[InitialCapacity];
        this.size = EMPTY;
    }
    private void grow() {
        MenuItem[] oldOrderBasket = this.orderBasket;
        this.orderBasket = new MenuItem[this.size + GrowthRate];
        for (int i = 0; i < this.size; i++) {
            this.orderBasket[i] = oldOrderBasket[i];
        }
    }
    public boolean add(MenuItem item){
        if(size == orderBasket.length){
            grow();
        }
        orderBasket[size] = item;
        size++;
        return true;
    }
    public boolean remove(MenuItem item) {
        this.orderBasket[this.orderBasket.length - 1] = null;
        size--;
        return true;
    }
}
