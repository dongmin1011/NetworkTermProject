package NetwrokTermProject.Money;

public class Thousand extends Money {
    public Thousand() {
        money = 1000;
    }

    public Thousand(int n) {
        super(n);
        money = 1000;    //돈의 단위는 1000
    }
}
