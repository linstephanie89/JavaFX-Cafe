package code;

public abstract class MenuItem {
    private String name;
    public MenuItem(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public abstract double itemPrice();
}
