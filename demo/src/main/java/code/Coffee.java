package code;

public class Coffee {
    private String cupSize;
    private String[] addIns;
    private int quantity;
    private double price;
    private double addInPrice;
    private double addInCost = 0.3;
    private double SHORT_PRICE=1.89;
    private double TALL_PRICE = 2.29;
    private double GRANDE_PRICE = 2.69;
    private double VENTI_PRICE=3.09;

    public Coffee(String cupSize, String[] addIns, int quantity){
        this.cupSize = cupSize;
        this.addIns = addIns;
        this.quantity = quantity;
    }
    public double calcSubTotal(){
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
            price +=addInPrice;
        }
        return price*quantity;

    }



}
