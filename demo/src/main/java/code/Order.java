package code;

public class orderBasket {
    private MenuItem[] orderBasket;
    private int InitialCapacity = 4;
    private int size;
    private int EMPTY = 0;
    private int GrowthRate = 4;
    private double totalPrice;

    public orderBasket(MenuItem[] orderBasket){
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
    public boolean add(MenuItem item){
        if(size == orderBasket.length){
            grow();
        }
        orderBasket[size] = item;
        size++;
        totalPrice += item.itemPrice();
        return true;
    }

    public int findItem(MenuItem item) {
        for (int i = 0; i < size; i ++) {
            if (orderBasket[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }
    public boolean remove(MenuItem item) {
        int index = this.findItem(item);
        if (index == -1) {
            return false;
        } else {
            for (int i = index; i < size-1; i++) {
                orderBasket[i] = orderBasket[i+1];
            }
            orderBasket[size-1] = null;
            size--;
            return true;
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

}
