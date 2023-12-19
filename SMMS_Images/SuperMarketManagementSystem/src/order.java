public class order {
    private int ID;
    private String name;
    private int quantity;
    private double totalPrice;
    String type;

    //Constrcutor
    public order(int ID, String name, int quantity, double totalPrice, String type) {
        this.ID = ID;
        this.name = name;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.type=type;
    }


    //Setters
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }
    //Setters
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    //Setters
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    //Getter
    public int getID() {
        return ID;
    }
    //Getter
    public String getName() {
        return name;
    }
    //Getter
    public int getQuantity() {
        return quantity;
    }
    //Getter
    public double getTotalPrice() {
        return totalPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String toString()
    {
        return getID()+","+getName()+","+getQuantity()+","+getTotalPrice();
    }

}
