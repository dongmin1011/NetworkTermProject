package NetwrokTermProject.Money;

public class Hundred extends Money {
    public Hundred() {
        money = 100;
    }

    public Hundred(int n) {
        super(n);
        money = 100;    //돈의 단위는 100
    }
}
