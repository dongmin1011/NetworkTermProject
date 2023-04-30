package NetwrokTermProject.Money;

// 돈 클래스
public class Money implements MoneyInF{
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

