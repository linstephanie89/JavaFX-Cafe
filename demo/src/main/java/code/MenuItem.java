package code;

public abstract class MenuItem {
    private String name;
    public int quantity;
    public MenuItem(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }
    public String getName(){
        return name;
    }
    public abstract double itemPrice();
    public abstract int getQuantity();
    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }


}
