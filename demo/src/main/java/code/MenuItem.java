package code;

public abstract class MenuItem {
    private String name;
    public int quantity;
    public MenuItem(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public abstract double itemPrice();
    public abstract int getQuantity();
}
