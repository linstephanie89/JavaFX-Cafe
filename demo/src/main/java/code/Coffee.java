package code;

public class Coffee extends MenuItem {

    private String cupSize;
    private String[] addIns;
    private int quantity;
    private double price;
    private double addInPrice = 0.30;
    private double SHORT_PRICE=1.89;
    private double TALL_PRICE = 2.29;
    private double GRANDE_PRICE = 2.69;
    private double VENTI_PRICE=3.09;
    private double NONE = 0;

    public Coffee(String name, String cupSize, String[] addIns, int quantity){
        super(name);
        this.cupSize = cupSize;
        this.addIns = addIns;
        this.quantity = quantity;
        this.price = NONE;
    }

    @Override
    public int getQuantity() { return quantity;}

    @Override
    public double itemPrice(){
        price = 0.0;
        if(cupSize.equals("Short")){
            price = SHORT_PRICE;
        }else if(cupSize.equals("Tall")){
            price = TALL_PRICE;
        }else if(cupSize.equals("Grande")){
            price = GRANDE_PRICE;
        }else{
            price = VENTI_PRICE;
        }

        for(String item: addIns){
            if (item != null) {
                price +=addInPrice;
            }
        }
        return price*quantity;
    }



}