package code;

public class Coffee extends MenuItem {

    //private String cupSize;
    private String[] addIns;
    private double price;
    private double addInPrice = 0.30;
    private double SHORT_PRICE=1.89;
    private double TALL_PRICE = 2.29;
    private double GRANDE_PRICE = 2.69;
    private double VENTI_PRICE=3.09;
    private double NONE = 0;

    public Coffee(String name, String cupSize, String[] addIns, int quantity){
        super(name, quantity);
        super.setSize(cupSize);
        this.addIns = addIns;
        super.setAddIns(addInToString(addIns));
        if(cupSize.equals("Short")){
            this.price = SHORT_PRICE;
        }else if(cupSize.equals("Tall")){
            this.price = TALL_PRICE;
        }else if(cupSize.equals("Grande")){
            this.price = GRANDE_PRICE;
        }else{
            this.price = VENTI_PRICE;
        }

        if (addIns != null) {
            for(String item: addIns){
                if (item != null) {
                    this.price +=addInPrice;
                }
            }
        }
    }


    @Override
    public int getQuantity() { return this.quantity;}
    public String getSize() {
        return super.getSize();
    }

    public String[] getAddIns() {
        return this.addIns;
    }

    public boolean compareAddIns(Coffee coffee) {
        String newAddIns = coffee.getAddInStrings();
        String currAddIns = this.getAddInStrings();
//        String[] newAddIns = coffee.getAddIns();
//        String[] currAddIns = super.getAddIns();
//        for (int i = 0; i < 5; i++) {
//            if (newAddIns[i] != currAddIns[i]) {
//                return false;
//            }
//        }
        if (newAddIns.equals(currAddIns)) {
            return true;
        }
        return false;
    }

//    public void setQuantity(int newQuantity) {
//        this.quantity = newQuantity;
//    }

    @Override
    public double itemPrice(){
        return this.price * (double) this.quantity;
    }

    @Override
    public String toString() {
        String returnString= this.getSize() + " coffee with: ";
        for (int i = 0; i < 5; i++) {
            if (addIns[i] != null) {
                returnString += addIns[i] + ", ";
            }
        }
        returnString += "quantity: " + Integer.toString(this.quantity);
        return returnString;
    }

    public String addInToString(String[] addIns) {
        String returnString = "";
        for (int i = 0; i < 5; i++) {
            if (addIns[i] != null) {
                returnString += addIns[i];
            }
        }
        return returnString;
    }



}