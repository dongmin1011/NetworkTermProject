package NetwrokTermProject.Drinks;

public abstract class AbstractDrink {
    protected String name;	//음료수 이름
    protected int price;	//가격
    protected int stock;	//재고

    public AbstractDrink() {

//        stock = 3;
    }
//    boolean Stock_IsExist() {
//        if(stock>0) return true;
//        else return false;
//    }

    public String getName() { return name;  }
    public int getPrice() {  return price; }
    public int getStock() {  return stock;  }

    public void setName(String name) {  //음료수 이름 변경
        this.name = name;
    }

    public void setPrice(int price) {  //음료수 가격 변경
        this.price = price;
    }
}
