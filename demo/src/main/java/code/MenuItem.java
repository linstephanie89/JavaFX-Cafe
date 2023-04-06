package code;

public abstract class MenuItem {
    public String name;
    public int quantity;
    public String addIns;
    private String size;
    public MenuItem(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
        this.addIns = null;
        this.size = null;
    }
    public String getName(){
        return name;
    }
    public abstract double itemPrice();
    public abstract int getQuantity();
    public abstract String toString();
    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }
    public void setSize(String size) {
        this.size = size;
    }

    public void setAddIns(String AddIns) {
        this.addIns = AddIns;
    }

    public String getSize() {
        return this.size;
    }

    public String getAddInStrings() {
        return this.addIns;
    }


}
