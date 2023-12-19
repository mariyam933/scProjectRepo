class Product {

  private int ID;
  private int quantity;
  private double price;
  private String name;
  private String type;
  private String desc;
  private String imgPath;

  public Product(
      int id,
      int quantity,
      double price,
      String name,
      String type,
      String desc,
      String imgPath) {
    this.ID = id;
    this.quantity = quantity;
    this.price = price;
    this.name = name;
    this.type = type;
    this.desc = desc;
    this.imgPath = imgPath;
  }

  //Setters
  public void setID(int id) {
    this.ID = id;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  //setters
  public void setPrice(double price) {
    this.price = price;
  }

  //setters
  public void setName(String name) {
    this.name = name;
  }

  //setters
  public void setType(String type) {
    this.type = type;
  }

  //setters
  public void setDesc(String desc) {
    this.desc = desc;
  }

  //setters
  public void setImgPath(String imgPath) {
    this.imgPath = imgPath;
  }

  //getters
  public int getID() {
    return this.ID;
  }

  //getters
  public int getQuantity() {
    return this.quantity;
  }

  //getters
  public double getPrice() {
    return this.price;
  }

  //getters
  public String getName() {
    return this.name;
  }

  //getters
  public String getType() {
    return this.type;
  }

  //getters
  public String getDesc() {
    return this.desc;
  }

  //getters
  public String getImgPath() {
    return this.imgPath;
  }

  //compute Price
  public double computrPrice(int quantity) {
    return quantity * this.price;
  }
}
//Drive Class Electronics from Product
class Electronics extends Product {

  public Electronics(
      int id,
      int quantity,
      double price,
      String name,
      String type,
      String desc,
      String imgPath) {
    super(id, quantity, price, name, type, desc, imgPath);
  }
}
//Drive Class cosmetics from Product
class Cosmetics extends Product {
  public Cosmetics(
      int id,
      int quantity,
      double price,
      String name,
      String type,
      String desc,
      String imgPath) {
    super(id, quantity, price, name, type, desc, imgPath);
  }
}
//Drive class FruitAndVegitables from Product
class FruitAndVegitables extends Product {

  public FruitAndVegitables(
      int id,
      int quantity,
      double price,
      String name,
      String type,
      String desc,
      String imgPath) {
    super(id, quantity, price, name, type, desc, imgPath);
  }
}

//class Households from Product
class Households extends Product {

  public Households(
      int id,
      int quantity,
      double price,
      String name,
      String type,
      String desc,
      String imgPath) {
    super(id, quantity, price, name, type, desc, imgPath);
  }
}

//class Dairy from Product
class Dairy extends Product {

  public Dairy(
      int id,
      int quantity,
      double price,
      String name,
      String type,
      String desc,
      String imgPath) {
    super(id, quantity, price, name, type, desc, imgPath);
  }
}
//class kitchen from Product
class kitchen extends Product {

  public kitchen(
      int id,
      int quantity,
      double price,
      String name,
      String type,
      String desc,
      String imgPath) {
    super(id, quantity, price, name, type, desc, imgPath);
  }
}
