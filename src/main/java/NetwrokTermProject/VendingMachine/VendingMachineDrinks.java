package NetwrokTermProject.VendingMachine;

public class VendingMachineDrinks {
    protected String name;	//음료수 이름
    protected int price;	//가격
    protected int stock;	//재고

    public VendingMachineDrinks() {
        stock = 3;
    }
    boolean Stock_IsExist() {
        if(stock>0) return true;
        else return false;
    }


    public String getName() {return name;}		//음료수 이름 반환
    public void setName(String name) {this.name = name;}	//음료수 이름 변경
    public int getPrice() {return price;}		//음료수 가격 반환
    public void setPrice(int price) {this.price = price;}	//음료수 가격 변경
    public int getStock() {	return stock;}			//음료수 재고 반환
    public void setStock(int stock) {this.stock = stock;}	//음료수 재고 증가

    public void Sub() {	//음료수 재고 감소
        stock--;
    }
    public void Add() {	//음료수 재고 증가
        stock++;
    }
}

