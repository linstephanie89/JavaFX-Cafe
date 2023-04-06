package code;

public class Donut extends MenuItem{
    private String type;
    private String flavor;
    private double price;
    private double YEAST_PRICE=1.59;
    private double CAKE_PRICE = 1.79;
    private double DONUT_HOLE_PRICE = 0.39;
    public Donut(String name, String type, String flavor, int quantity){
        super(type + " " + name + ", " + flavor + " flavor", quantity);
        this.type = type;
        this.flavor = flavor;
        if(type.equals("Yeast")){
            this.price = YEAST_PRICE;
        }else if(type.equals("Cake")){
            this.price = CAKE_PRICE;
        }else{
            this.price = DONUT_HOLE_PRICE;
        }
    }
    public String getType() {
        return type;
    }

    public String getFlavor() {
        return flavor;
    }
//    public void setQuantity(int quantity){
//        this.quantity = quantity;
//    }
    @Override
    public double itemPrice() {
        return price*quantity;
    }
    @Override
    public int getQuantity() { return quantity;}
    @Override
    public String toString() {
        String returnString= this.getType() + " donut, " + this.getFlavor()
                + " flavor, quantity: "+ Integer.toString(this.quantity);
        return returnString;
    }

}
