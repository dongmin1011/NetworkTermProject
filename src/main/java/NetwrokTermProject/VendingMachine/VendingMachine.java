package NetwrokTermProject.VendingMachine;

import NetwrokTermProject.Admin.Admin;
import NetwrokTermProject.Money.Money;
import NetwrokTermProject.Money.MoneyInF;
import NetwrokTermProject.Money.*;

//자판기 클래스
public class VendingMachine{
    private static VendingMachine machine= new VendingMachine();
    private VendingMachineDrinks [] drinks = new VendingMachineDrinks[5];	//자판기 음료수 배열객체
    private MoneyInF[] money;		//자판기 잔돈 배열 객체
    private Integer input;		//돈 입력

    private Admin admin = new Admin();	//자판기 관리자 클래스 생성

    public static VendingMachine getInstance() {
        return machine;
    }
    private VendingMachine(){				//자판기 생성자
        //자판기에는 물, 커피, 이온음료, 고급커피, 탄산음료가 있음
        drinks[0] = new Water();
        drinks[1] = (new Coffee());
        drinks[2] = (new SportsDrink());
        drinks[3] = (new HighQualityCoffee());
        drinks[4] = (new Soda());

        //자판기가 기본적으로 가지고 있는 돈 500, 100, 50, 10원 5개씩
        money = new Money[5];
        money[0] = new Thousand(0);
        money[1] = new FiveHundred(5);
        money[2] = new Hundred(5);
        money[3] =new Fifty(5);
        money[4] = new Ten(5);

        input = null;	//Integer타입 자동 박싱
    }


    public Admin getAdmin() {
        return admin;			//자판기의 admin객체 리턴
    }
    public boolean money_IsExist(int n) {			//자판기에 남아있는 잔돈이 있는지 확인
        if(money[n].getCount()>0) return true;	//존재한다면 true리턴
        else return false;
    }
    public boolean isInput() {				//input변수가 null인지 확인
        if(input==null)return true;	//input변수가 null이면 true리턴
        else return false;
    }

    public VendingMachineDrinks getDrinks(int n) {	//자판기 음료수 클래스 반환
        return drinks[n];
    }
    public MoneyInF getMoney(int n) {		//자판기의 돈 클래스 반환
        return money[n];
    }
    public void setInput(Integer n) {	//자판기 돈 초기화
        input = n;
    }
    public void AddInput(int n) {		//자판기에 돈이 들어옴
        input += n;
    }
    public void SubInput(int n) {	//자판기에서 돈이 나감
        input-=n;
    }
    public int getInput() {	//현재 자판기에 있는 돈 반환
        return input.intValue();
    }


}


