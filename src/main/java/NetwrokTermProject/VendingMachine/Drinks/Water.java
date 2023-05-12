package NetwrokTermProject.VendingMachine.Drinks;

import NetwrokTermProject.VendingMachine.VendingMachineDrinks;

//음료수 클래스를 상속받는 물, 커피, 이온음료, 고급커피, 탄산음료 클래스
public class Water extends VendingMachineDrinks {
    public Water() {
        name = "물";    //이름 지정
        price = 450; //가격 지정
    }
}
