package NetwrokTermProject.Money;

//Money클래스를 상속받는 1000, 500, 100, 50, 10원 클래스
public class Ten extends Money {
    public Ten() {
        money = 10;
    }

    public Ten(int n) {
        super(n);        //입력받은 n으로 money클래스초기화
        money = 10;        //돈의 단위는 10
    }
}
