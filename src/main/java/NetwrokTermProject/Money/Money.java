package NetwrokTermProject.Money;

// 돈 클래스
public class Money {
    protected int money;		//돈의 단위
    protected int count;		//돈의 개수

    public Money(int n) {
        count = n;				//개수를 n개로 초기화
    }
    public Money() {count = 0; }		//기본생성자는 개수 0개로 초기화
    public int getMoney() {	return money;}	//현재 돈 단위 반환
    public int getCount() {	return count;}	//돈의 개수를 반환

    public void Add() {	//돈 개수 추가
        count++;
    }
    public void Sub() {	//돈 개수 감소
        count--;
    }
}
//Money클래스를 상속받는 1000, 500, 100, 50, 10원 클래스
class Ten extends Money{
    public Ten() { money = 10; }
    public Ten(int n) {
        super(n);		//입력받은 n으로 money클래스초기화
        money = 10;		//돈의 단위는 10
    }
}
class Fifty extends Money{
    public Fifty() {money = 50;}
    public Fifty(int n) {
        super(n);
        money = 50;		//돈의 단위는 50
    }
}
class Hundred extends Money{
    public Hundred() {money = 100;}
    public Hundred(int n) {
        super(n);
        money = 100;	//돈의 단위는 100
    }
}
class FiveHundred extends Money{
    public FiveHundred() {money = 500;}
    public FiveHundred(int n) {
        super(n);
        money = 500;	//돈의 단위는 500
    }
}
class Thousand extends Money{
    public Thousand() {money = 1000;}
    public Thousand(int n) {
        super(n);
        money = 1000;	//돈의 단위는 1000
    }
}
