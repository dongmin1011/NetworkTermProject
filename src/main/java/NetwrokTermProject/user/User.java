package NetwrokTermProject.user;

import NetwrokTermProject.Money.*;

public class User {
    private MoneyInF[] money;

    public User(){
        //사용자가 기본적으로 가지고 있는 돈 생성(기본 5개씩)
        money = new Money[5];
        money[0] = new Thousand(10);
        money[1] = new FiveHundred(10);
        money[2] = new Hundred(10);
        money[3] = new Fifty(1);
        money[4] = new Ten(10);
    }


    public void usingMoney(int n) {
        money[n].Sub();
    }
    public MoneyInF getMoney(int n){
        return money[n];
    }
}
