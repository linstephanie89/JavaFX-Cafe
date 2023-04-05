package code;

<<<<<<< HEAD
public class Coffee extends MenuItem {
    private String size;
    private boolean[] addIns = [false, false, false, false, false];
    private int numOrders;
    private int addInCount;
    private int NONE = 0;
    private int CAPACITY = 5;
    private double ADD_IN_PRICE = 0.30;
    private double SHORT_PRICE = 1.89;
    private double TALL_PRICE = 2.29;
    private double GRANDE_PRICE = 2.69;
    private double VENTI_PRICE = 3.09;
    private boolean addedIn = false;


    public Coffee (String size, double price, int numOrders) {
        super(price);
        this.size = size;
        this.addIns = String[CAPACITY];
        this.addInCount = NONE;
        this.numOrders = numOrders;
    }
    public void setAddInCount(int count) {
        addInCount += count;
    }

    public void setAddIns(String addIn) {
        addedIn = true;
        if (addIn.equals("Sweet Cream")) {
            addIns[0] = true;
        } if (addIn.equals("French Vanilla")) {
            addIns[1] = true;
        } if (addIn.equals("Irish Cream")) {
            addIns[2] = true;
        } if (addIn.equals("Caramel")) {
            addIns[3] = true;
        } if (addIn.equals("Mocha")) {
            addIns[4] = true;
        }
        addInCount += 1;
    }

    private String getFlavor(int index) {
        if (index == 0) {
            return "Sweet Cream";
        } else if (index == 1) {
            return "French Vanila";
        } else if (index == 2) {
            return "Irish Cream";
        } else if (index == 3) {
            return "Caramel";
        } return "Mocha";
    }

    @Override
    public double itemPrice() {
        if (this.size.equals("short")) {
            this.price = SHORT_PRICE;
        } else if (this.size.quals("tall")) {
            this.price = TALL_PRICE;
        } else if (this.size.equals("grande")){
            this.price = GRANDE_PRICE;
        } else {
            this.price = VENTI_PRICE;
        }
        this.price += (double) addInCount * ADD_IN_PRICE;
        return this.price;
    }

    @Override
    public String toString(){
        boolean multipleFlavors = false;
        String returnString = "";
        if (numOrders == 1) {
            returnString = "1 coffee";
        }
        returnString = Integer.toString(numOrders) + " coffees";
        if (addedIn) {
            returnString += " with";
            for (int i = 0; i < 5; i++) {
                if (multipleFlavors == true) {
                    returnString += ",";
                }
                if (addIns[i] == true) {
                    multipleFlavors = true;
                    returnString += " " + getFlavor(i);

                }
            }
        }
        returnString += " ordered! Total price is " + Integer.toString(this.itemPrice()) +".";
        return returnString;
    }
=======
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

>>>>>>> origin/main


}
