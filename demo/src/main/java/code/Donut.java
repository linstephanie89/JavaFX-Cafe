package code;

public class Donut extends MenuItem{
    private String type;
    private String flavor;
    private double price;
    private int quantity;
    private double YEAST_PRICE=1.59;
    private double CAKE_PRICE = 1.79;
    private double DONUT_HOLE_PRICE = 0.39;
    public Donut(String name, String type, String flavor){
        super(name);
        this.type = type;
        this.flavor = flavor;
        if(type.equals("yeast")){
            this.price = YEAST_PRICE;
        }else if(type.equals("cake")){
            this.price = CAKE_PRICE;
        }else{
            this.price = DONUT_HOLE_PRICE;
        }
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    @Override
    public double itemPrice() {
        return price*quantity;
    }
}
