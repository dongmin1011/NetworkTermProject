package NetwrokTermProject.Layout;


import NetwrokTermProject.Money.MoneyInF;
import NetwrokTermProject.VendingMachine.VendingMachine;
import NetwrokTermProject.user.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;


interface LayOutData{

//    fileIO file = new fileIO();		//파일 입출력 클래스 생성


    JButton returnbtn = new JButton("반환");		//반환버튼
    JTextArea MachineInfo = new JTextArea();		//자판기 정보 출력
    JLabel [] coincount = new JLabel[5];		//거스름돈에서 돈의 개수를 담는 레이블

//    default String getToday(int n) {
//        if(n>0&&n<10) {
//            return "0"+n;
//        }
//        return Integer.toString(n);
//    }
//    void Buttonfunc();
}
//@SuppressWarnings("serial")
public class LayOut extends JFrame implements LayOutData{
    VendingMachine machine = VendingMachine.getInstance();

    JFrame f = new JFrame();
    User consumer = new User();
    JPanel Drink_Grid = new JPanel();			//음료수 이름, 가격, 버튼이 존재하는 3x5 그리드 패널

    JPanel mid = new JPanel();					//Input과 거스름돈 정보과 음료수 배출구가 있는 패널
    JPanel Input = new JPanel();				//자판기 정보, 입력된 돈, 관리자 버튼이 있는 패널
    JTextField inputMoney = new JTextField();	//현재 자판기에 들어있는 돈

    JButton [] drinkbutton= new JButton[5];		//음료수 버튼
    JLabel [] Drink_price = new JLabel[5];
    JLabel [] drink_name = new JLabel[5];		//음료수 이름




    JButton adminButton = new JButton("관리자");	//관리자 버튼

    JPanel changeDrinks = new JPanel();			//거스름돈과 음료수 배출구 정보를 담고있는 패널
    JPanel changepanel = new JPanel();			//거스름돈 정보를 담고있는 패널
    JPanel DrinkArea = new JPanel();			//음료수 배출구를 담고있는 패널

    JLabel [] machinechange = new JLabel[5];	//거스름돈에서 돈의 크기를 담는 레이블

    JLabel[] moneycount = new JLabel[5];		//거스름돈 개수
    JButton[] user = new JButton[5];			//사용자의 돈

    JLabel GetDrink = new JLabel("음료수 배출구");
    JTextField GetDrinkName = new JTextField("");	//음료수 배출구에서 어떤 음료수가 나왔는지 보는 텍스트필드

    ArrayList<MoneyInF> inputmoneylist = new ArrayList<>();
    int [] change_cnt = new int[4];
    JTextArea usersdrink = new JTextArea();		//사용자가 뽑은 음료수 (반환버튼 누르면 삭제)


    //자판기 화면의 레이아웃 생성
    public LayOut() {
        f.setResizable(false);		//f프레임의 크기 변경 불가
        f.setPreferredSize(new Dimension(560, 720));
        int [] moneysize = {1000, 500, 100, 50, 10};
        f.setTitle("음료수 자판기");

        Drink_Grid.setLayout(new GridLayout(3,5,65,10));			//자판기 음료수, 가격, 구입 버튼이 있는 3x5레이아웃
        Drink_Grid.setBackground(Color.getHSBColor(360, 360, 360));	//Drink_grid의 배경색 지정
        Drink_Grid.setPreferredSize(new Dimension(540, 100));

        //음료수 이름과 가격, 버튼을 생성하고 Drink_grid에 삽입
        for(int i=0; i<5; i++) {
            drink_name[i]=new JLabel(machine.getDrinks(i).getName(), JLabel.CENTER);
            Drink_Grid.add(drink_name[i]);
        }
        for(int i=0; i<5; i++) {
            Drink_price[i] = new JLabel("", JLabel.CENTER);
            Drink_price[i].setText("<HTML>"+ Integer.toString(machine.getDrinks(i).getPrice())+"원</HTML>" );
            Drink_Grid.add(Drink_price[i]);
        }
        for(int i=0; i<5; i++) {
            drinkbutton[i] = new JButton("");
            drinkbutton[i].setEnabled(false);
            drinkbutton[i].setBackground(Color.black);
            drinkbutton[i].setPreferredSize(new Dimension(30,30));
            Drink_Grid.add(drinkbutton[i]);
        }
        f.add(Drink_Grid, BorderLayout.NORTH);	//Drink_grid를 프레임에 삽입

        Input.setLayout(new FlowLayout(0, 10, 30));	//Input은 입력된 돈과 반환버튼, 자판기 정보, 관리자 버튼이 1차원으로 저장됨

        mid.setBackground(Color.orange);
        Input.setBackground(Color.LIGHT_GRAY);

        for(int i=1; i<5; i++) {						//개수가 부족한 동전을 Info에 출력
            if(machine.getMoney(i).getCount()<=0) {
                MachineInfo.append(Integer.toString(machine.getMoney(i).getMoney())+"원짜리 개수 부족\n");
            }
        }
        //자판기가 입력받은 돈을 보여주는 JTextField inputMoney
        inputMoney.setEditable(false);
        inputMoney.setText(Integer.toString(0));
        inputMoney.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
        inputMoney.setPreferredSize(new Dimension(130, 50));
        inputMoney.setHorizontalAlignment(JLabel.CENTER);
        Input.add(inputMoney);
        //반환 버튼 크기 설정
        returnbtn.setPreferredSize(new Dimension(100, 30));
        returnbtn.setEnabled(false);
        Input.add(returnbtn);

        //자판기의 정보를 출력하는 MachineInfo를 설정
        MachineInfo.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(MachineInfo);
        Input.add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(160,100));
        Input.setPreferredSize(new Dimension(540, 150));


        adminButton.setPreferredSize(new Dimension(80, 100));	//관리자 버튼의 크기 지정
        Input.add(adminButton);
        mid.add(Input, BorderLayout.NORTH);	//mid패널에 Input패널 삽입


        changeDrinks.setPreferredSize(new Dimension(500, 230));

        //반환된 음료수를 보여주는 DrinkArea Panel 생성
        //2행 1열의 구조로 GetDrink와 GetDrinkNane텍스트가 있음
        GetDrink.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        GetDrinkName.setPreferredSize(new Dimension(50, 50));
        GetDrinkName.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        GetDrinkName.setBackground(Color.white);
        DrinkArea.setLayout(new GridLayout(2, 1));
        DrinkArea.add(GetDrink);
        DrinkArea.add(GetDrinkName);
        GetDrinkName.setEditable(false);

        //반환된 동전의 개수를 저장하는 패널 changepanel을 생성
        changepanel.setBackground(Color.pink);
        changepanel.setPreferredSize(new Dimension(150, 200));

        changepanel.setLayout(new GridLayout(6,2));
        changepanel.add(new JLabel("거스름돈", JLabel.CENTER));
        changepanel.add(new JLabel("개수", JLabel.CENTER));
        machinechange[0] = new JLabel("<HTML>사용 안한<br> 천원짜리</HTML>");
        coincount[0] = new JLabel("");
        changepanel.add(machinechange[0]);
        changepanel.add(coincount[0]);
        machinechange[0].setHorizontalAlignment(JLabel.CENTER);
        coincount[0].setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        coincount[0].setHorizontalAlignment(JLabel.CENTER);
        for(int i=1; i<5; i++) {
            machinechange[i] = new JLabel(Integer.toString(moneysize[i]), JLabel.CENTER);
            machinechange[i].setFont(new Font("맑은 고딕", Font.PLAIN, 20));
            coincount[i] = new JLabel("");
            coincount[i].setFont(new Font("맑은 고딕", Font.PLAIN, 20));
            coincount[i].setHorizontalAlignment(JLabel.CENTER);

            changepanel.add(machinechange[i]);
            changepanel.add(coincount[i]);
            if(machine.getMoney(i).getCount()==0) {
                coincount[i].setText("x");
            }
        }
        //changeDrinks패널을 FlowLayout으로 생성하여 changepanel과 DrinkArea패널을 차례대로 왼쪽에서 오른쪽으로 배치
        changeDrinks.setLayout(new FlowLayout(10, 70, 15));
        changeDrinks.add(changepanel);
        changeDrinks.add(DrinkArea);
        mid.add(changeDrinks);

        //f프레임에 mid를 삽입
        f.add(mid);

        JPanel using = new JPanel();					//사용자가 가지고 있는 돈
        using.setBackground(Color.yellow);
        usersdrink.setBackground(Color.LIGHT_GRAY);			//사용자가 뽑은 음료수를 저장하는 텍스트
        usersdrink.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        usersdrink.setEditable(false);
        JScrollPane scrollPane2 = new JScrollPane(usersdrink);	//텍스트가 칸을 넘어갈 시 스크롤 가능
        scrollPane2.setPreferredSize(new Dimension(100,130));

        //사용자가 가지고 있는 돈을 선언(1000원의 경우 지폐이므로 장으로 출력)
        for(int i=0; i<5; i++) {
            user[i] = new JButton();
            user[i].setText("<HTML><body><center>"+Integer.toString(consumer.getMoney(i).getMoney())+"원<br>" +
                    Integer.toString(consumer.getMoney(i).getCount())+(consumer.getMoney(i).getCount()==1000?"장":"개") +"</body></HTML>");
            user[i].setPreferredSize(new Dimension(80, 130));
            using.add(user[i]);
        }
        using.add(scrollPane2);		//using패널에 뽑은 음료수 목록 추가
        f.add(using, BorderLayout.SOUTH);

        Buttonfunc();	//버튼 모음


        //f프레임의 사이즈 지정
        f.setSize(560,720);
        f.setVisible(true);
        //만약 f프레임이 닫히면 열려있는 모든 프레임을 닫음
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void Buttonfunc(){
        adminButton.addActionListener(new ActionListener(){			//관리자 버튼 입력시 동작
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("e = " + e);


                machine.getAdmin().AccessAdmin();		//관리자 기능에 접근
//                if(machine.getAdmin().isAdmin())
//                    machine.getAdmin().dataClear();			//관리자 프레임에 출력되어 있는 정보 제거
                for(int i=0; i<5; i++) {
                    user[i].setEnabled(false);			//관리자버튼을 누르면 사용자는 돈 입력 불가

                }
                String s = MachineInfo.getText();
                if(!s.contains("거스름 돈이 부족합니다!")) {	//만약 거스름돈이 존재한다면 반환버튼을 누를 수 있음
                    returnbtn.setEnabled(true);
                }
                if(machine.isInput()) {
                    machine.setInput(0);		//만약 자판기에 입력된 돈이 null이라면 admin에서 사용을 못하므로 임시로 0값을 넣음
                }
            }
        });
        //Drink_data는 음료수 버튼을 클릭시 일어나는 동작을 정의
        Drink_data();
//        //ReturnButton은 반환 버튼 클릭시 동작 정의
        ReturnButton();
        //Consumer_data는 사용자에 대한 정보와 돈 입력 정의
        Consumer_data();
    }
    void Drink_data() {
        //음료수 버튼이 눌렸을 때의 동작 정의
        drinkbutton[0].addActionListener(e->OrderDrink(0));
        drinkbutton[1].addActionListener(e->OrderDrink(1));
        drinkbutton[2].addActionListener(e->OrderDrink(2));
        drinkbutton[3].addActionListener(e->OrderDrink(3));
        drinkbutton[4].addActionListener(e->OrderDrink(4));

    }
//
    //음료수 버튼을 눌렀을 때(매개변수는 음료의 위치)
    void OrderDrink(int n) {
        int count = 0;
        int []moneysize = {1000,500,100,50,10};
        for(int i=0; i<inputmoneylist.size(); ) {
            for(int j=0; j<5; j++) {
                if(inputmoneylist.get(i).getMoney()==moneysize[j] ) {
                    if(moneysize[j]!=1000) {
                        machine.getMoney(j).Add();		//자판기가 가지고 있는 돈 증가
                    }
                }
            }
            if(inputmoneylist.get(i).getMoney()!=1000) {
                inputmoneylist.remove(i);		//자판기에 천원말고 다른 돈이 들어와있으면 inputlist에서 제거하고 자판기로 들어감

            }
            else {				//천원짜리가 자판기 안에 남아있는 경우를 셈
                count++;		//천원짜리의 개수 증가
                i++;
            }
        }

        Calendar today = Calendar.getInstance();

        if(machine.getDrinks(n).getPrice()<=machine.getInput()) {	//자판기에 있는 돈보다 음료수의 가격이 쌀 경우(구입 가능)
            if(machine.getDrinks(n).getStock()>0) {					//음료수의 재고가 남아있다면

                machine.getDrinks(n).Sub();			//자판기에 있는 음료수의 재고를 줄임

//                file.SaleFileWrite(today.get(Calendar.YEAR )+"년 "+getToday(today.get(Calendar.MONTH )+1)+"월 "+getToday(today.get(Calendar.DATE ))+"일 "+
//                        machine.getDrinks(n).getName() +" "+ machine.getDrinks(n).getPrice() + "원", true);	//음료수 구입 내역을 파일에 이어서 씀


                machine.SubInput(machine.getDrinks(n).getPrice());		//자판기에 입력되어있는 돈에서 음료수의 가격을 뱀
                inputMoney.setText(Integer.toString(machine.getInput()));	//자판기에 입력되어 있는 돈을 inputMoney 필드에 업데이트

                if(machine.getInput()-count*1000 < 0) {			//만약 자판기가 아직 사용하지 않은 1000원을 사용해야 한다면
                    count--;						//저장되어있는 천원의 개수를 줄임
                    machine.getMoney(0).Add();		//자판기의 천원짜리 증가
                    inputmoneylist.remove(0);		//inputlist에 남아있는 천원 제거
                }
                GetDrinkName.setText(machine.getDrinks(n).getName());		//음료수 반환구에 뽑은 음료수 출력
                usersdrink.append(machine.getDrinks(n).getName()+'\n');		//사용자가 뽑은 음료수 추가
            }
            else {												//음료수의 재고가 없다면
                drinkbutton[n].setEnabled(false);				//음료수버튼을 클릭불가
                drinkbutton[n].setBackground(Color.black);
            }
        }
        for(int i=0; i<5; i++) {
            if(machine.getDrinks(i).getPrice() >machine.getInput()) {
                drinkbutton[i].setEnabled(false);			//음료수의 가격이 더 비싸다면 음료수 버튼은 클릭 불가하고 검은색으로 바뀜
                drinkbutton[i].setBackground(Color.black);
            }
        }

        if(machine.getDrinks(n).getStock()==0) {		//만약 음료수의 개수가 0개라면
            drinkbutton[n].setEnabled(false);			//음료수 클릭 불가
            drinkbutton[n].setText("품");				//음료수 버튼에 품절 표시
            drinkbutton[n].setBackground(Color.black);

            //파일에 음료수가 소진된 날을 씀
//            file.ExhaustionFileWrite(today.get(Calendar.YEAR )+"년 "+getToday(today.get(Calendar.MONTH )+1)+
//                    "월 "+getToday(today.get(Calendar.DATE ))+"일 "
//                    + getToday(today.get(Calendar.HOUR_OF_DAY ))+"시 "+
//                    getToday(today.get(Calendar.MINUTE ))+"분 "+ today.get(Calendar.SECOND )+"초 "+
//                    machine.getDrinks(n).getName() +" 품절", true);	//파일에 품절 표시

            //MachineInfo(음료수 정보)에 품절된 음료수 표시
            MachineInfo.append(machine.getDrinks(n).getName()+ " 품절\n");
        }

        String s = MachineInfo.getText();		//MachineInfo에 출력된 출력문 제거

        if(s.contains("5000원 이상 입력 불가")) {				//5000원 이상 입력 불가 출력문 제거
            s= s.replace("5000원 이상 입력 불가\n", "");
            MachineInfo.setText(s);
        }
        if(s.contains("천원지폐는 3장이상 불가")&& count<3) {		//1000원 3장 이상 입력 불가 출력문 제거(천원의 개수가 3개 이하일때만)
            s= s.replace("천원지폐는 3장이상 불가\n", "");
            MachineInfo.setText(s);
        }
        count = 0;
        for(int i=0; i<5; i++) {						//모든 음료수가 하나도 없다면
            if(machine.getDrinks(i).getStock()==0)count++;
        }
        if(count==5) {									//MachineInfo에 음료수가 없다고 출력
            MachineInfo.append("음료수가 하나도 없습니다!\n");
        }

    }
//
//
//    //반환버튼을 눌렀을 때의 동작 정의
    void ReturnButton() {
        returnbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int []moneysize = {1000,500,100,50,10};
                adminButton.setEnabled(true);					//반환 버튼을 누르면 관리자 접근 가능
                usersdrink.setText("");							//사용자가 뽑은 음료수 목록 제거

                for (int i=0; i<4; i++) { change_cnt[i] = 0; }	//거스름 돈을 저장할 change_cnt초기화

                int temp = 0;
//                adminframe.setVisible(false);					//관리자 모드가 켜져있는 상태에서 반환버튼을 누르면 관리자 모드 자동 종료
//                adminframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                for(int i=0; i<inputmoneylist.size(); ) {							//돈을 하나도 사용 안했을때
                    for(int j=0; j<5; j++) {
                        if(inputmoneylist.get(i).getMoney() == moneysize[j]) {

                            machine.SubInput( moneysize[j]);
                            if(inputmoneylist.get(i).getMoney()== 500) change_cnt[0]++;		//입력한 돈에 맞게 거스름돈의 개수 증가
                            else if(inputmoneylist.get(i).getMoney()== 100) change_cnt[1]++;
                            else if(inputmoneylist.get(i).getMoney()== 50) change_cnt[2]++;
                            else if(inputmoneylist.get(i).getMoney()== 10) change_cnt[3]++;
                            consumer.getMoney(j).Add();
                        }
                    }
                    if(inputmoneylist.get(i).getMoney()==1000) {
                        temp++;								//1000원의 개수 증가
                    }

                    inputmoneylist.remove(i);				//반환 했으므로 inputlist를 제거
                }
                coincount[0].setText(Integer.toString(temp)+"개");

                for(int i=1; i<5; i++) {
                    if(change_cnt[i-1]==0 && machine.getMoney(i).getCount()==0) {
                        coincount[i].setText("x");
                    }
                    else
                        coincount[i].setText(Integer.toString(change_cnt[i-1])+"개");
                }
                System.out.println("temp = " + machine.getInput());
                if(machine.getInput()>0) {			//자판기 input에 돈이 남아있다면
                    int count = 0;


                    for (int i=0; i<4; i++) { change_cnt[i] = 0; }		//거스름돈 초기화


                    //거스름 돈을 구하는 과정
                    for(int j=1; j<5; j++) {
                        for(int i=0; i<machine.getMoney(j).getCount();) {
                            if(machine.getInput() - machine.getMoney(j).getMoney() >= 0) {	//만약 현재 input에서 돈을 빼도 0 이상이라면 거스름 돈 가능
                                machine.SubInput(machine.getMoney(j).getMoney());		//현재 input에서 money를 뱀
                                change_cnt[j-1]++;									//거스름돈 배열의 개수 증가
                                machine.getMoney(j).Sub();							//자판기가 가지고 있는 돈 감소
                            }
                            else break;											//input에서 거스름돈을 뺄때 음수가 나올경우 반복 종료하고 더 작은 돈으로 내려감
                        }
                    }

                    for(int i=1; i<5; i++) {									//위에서 구한 거스름 돈 출력
                        if(change_cnt[i-1]==0 && machine.getMoney(i).getCount()==0) {
                            coincount[i].setText("x");
                        }
                        else
                            coincount[i].setText(Integer.toString(change_cnt[i-1])+"개");	//천원짜리는 위에서 출력
                        for(int j=0; j<change_cnt[i-1]; j++) {
                            consumer.getMoney(i).Add();				//거스름돈 개수만큼 사용자의 돈 개수 증가
                        }
                        String s = MachineInfo.getText();
                        //만약 자판기가 가지고 있는 돈의 개수가 0개이고 동전 부족 메시지가 MachineInfo에 출력되지 않았다면 추가함
                        if((machine.getMoney(i).getCount()==0) &&(!s.contains(Integer.toString(machine.getMoney(i).getMoney())+"원짜리 개수 부족\n"))) {
                            MachineInfo.append(Integer.toString(machine.getMoney(i).getMoney())+"원짜리 개수 부족\n");
                            //	coincount[i].setText("x");
                        }
                        if(!machine.money_IsExist(i)) {	//만약 자판기의 동전이 없다면 count증가
                            count++;
                        }
                    }
                    if(machine.getInput()>0) {				//모든 반복을 마쳤는데 Input이 남아있으면 거스름돈이 부족한 것
                        String s = MachineInfo.getText();
                        if(!s.contains("거스름 돈이 부족합니다!")) {	//거스름돈 부족 메시지 출력
                            MachineInfo.append("거스름 돈이 부족합니다!\n관리자를 부르세요!\n");
                        }
                        for(int i=0; i<5; i++) {
                            user[i].setEnabled(false);			//만약 거스름 돈이 부족하면 사용자로부터 입력 불가
                        }
                        returnbtn.setEnabled(false);			//거스름돈이 부족하면 반환 버튼 클릭 불가
                        inputMoney.setText(Integer.toString(machine.getInput()));	//현재 Input을 가지고 inputmoney텍스트 초기화
                    }
                    else {
                        String s = MachineInfo.getText();
                        if(s.contains("거스름 돈이 부족합니다!")) {					//Input이 0이라면 거스름돈 부족 메시지 제거
                            s = s.replace("거스름 돈이 부족합니다!\n관리자를 부르세요!\n", "");
                            MachineInfo.setText(s);
                        }
                    }
                    if(count==4) {						//count가 4이면 자판기는 동전이 하나도 없음
                        String s = MachineInfo.getText();
                        if(!s.contains("동전이 하나도 없습니다")) {		//동전이 없다는 메시지 출력
                            MachineInfo.append("동전이 하나도 없습니다.\n 관리자를 부르세요!\n");
                        }

                        adminButton.setEnabled(true);		//관리자 버튼 클릭 가능

                    }
                }

                for(int j=0; j<5; j++) {		//반환 버튼을 눌렀으므로 사용자가 가지고 있는 돈 업데이트와
                    //음료수 버튼 클릭불가/ 검은색
                    user[j].setText("<HTML><body><center>"+Integer.toString(consumer.getMoney(j).getMoney())+"원<br>" +
                            Integer.toString(consumer.getMoney(j).getCount())+(consumer.getMoney(j).getMoney()==1000?"장":"개") +"</body></HTML>");
                    drinkbutton[j].setEnabled(false);
                    drinkbutton[j].setBackground(Color.black);
                    Drink_price[j].setText(Integer.toString(machine.getDrinks(j).getPrice())+"원");
                    drink_name[j].setText(machine.getDrinks(j).getName());
                    if(machine.getDrinks(j).getStock()>0) drinkbutton[j].setText("");
                }
                if(machine.getInput()==0) {							//만약 입력된 돈이 0원이라면
                    for(int i=0; i<5; i++) {
                        if(consumer.getMoney(i).getCount()>0) {
                            user[i].setEnabled(true);				//사용자가 가지고 있는 돈의 개수가 1개 이상이면 버튼 클릭 가능
                        }

                    }
                    String s= MachineInfo.getText();
                    for(int i=0; i<4; i++) {
                        if(machine.getMoney(i+1).getCount() > 0) {	//만약 동전의 개수가 1개 이상이면 동전 부족 메시지 제거
                            s = MachineInfo.getText();
                            if(s.contains(Integer.toString(machine.getMoney(i+1).getMoney())+"원짜리 개수 부족\n")) {
                                s= s.replace(Integer.toString(machine.getMoney(i+1).getMoney())+"원짜리 개수 부족\n", "");
                                MachineInfo.setText(s);
                            }
                        }
                    }

                    s = MachineInfo.getText();		//1000원짜리 3장 출력문 제거
                    if(s.contains("천원지폐는 3장이상 불가")) {
                        s= s.replace("천원지폐는 3장이상 불가\n", "");
                        MachineInfo.setText(s);
                    }
                    s = MachineInfo.getText();
                    if(s.contains("5000원 이상 입력 불가")) {				//5000원 이상 입력 불가 출력문 제거
                        s= s.replace("5000원 이상 입력 불가\n", "");
                        MachineInfo.setText(s);

                    }

                }
                if(machine.getInput()>0) {								//Input에 돈이 남아있다면
                    inputMoney.setText(Integer.toString(machine.getInput()));	//동적할당 해제하지 않고 InputMoney텍스트를 input으로 초기화
                }
                else {
                    inputMoney.setText(Integer.toString(0));
                    machine.setInput(null);										//잔돈 변수 동적할당 해제
                }

                returnbtn.setEnabled(false);		//반환버튼을 클릭하면 다시 클릭 불가
                GetDrinkName.setText("");			//음료수 배출구의 텍스트 제거
            }

        });
    }
    //자판기 사용자에 대한 정보를 정의
    void Consumer_data() {


        //사용자가 돈을 입력했을 때 버튼 정의
        user[0].addActionListener(e->Using_Money(0));
        user[1].addActionListener(e->Using_Money(1));
        user[2].addActionListener(e->Using_Money(2));
        user[3].addActionListener(e->Using_Money(3));
        user[4].addActionListener(e->Using_Money(4));


    }
    //사용자가 돈을 자판기에 입력했을 때(매개변수 n은 돈의 크기)
    void Using_Money(int n) {

        int count = 0;
        if(machine.isInput())machine.setInput(0);	//사용자가 돈을 입력했는데 만약 Input변수가 null이라면 0박싱
        adminButton.setEnabled(false);				//사용자가 돈을 입력하면 관리자 버튼 접근 불가

        for(int i=0; i<inputmoneylist.size(); i++) {	//입력한 리스트의 1000원 짜리 개수를 셈
            if(inputmoneylist.get(i).getMoney()==1000) {
                count++;
            }
        }
        if(count>=3 && n==0) {					//만약 n이 0(천원 버튼)이고 count(리스트의 1000원짜리 개수)가 3이상이라면
            String s  = MachineInfo.getText();
            if(!s.contains("천원지폐는 3장이상 불가"))		//만약 MachineInfo에 출력문이 없다면 MachineInfo에 천원지폐는 3장이상 불가 출력(중복 출력 방지)
                MachineInfo.append("천원지폐는 3장이상 불가\n");
        }
        else if(machine.getInput() + consumer.getMoney(n).getMoney() >5000) {	//만약 현재 입력된 input에서 입력한 지폐의 크기를 더하면 5000원 이상이 됐을 때

            String s  = MachineInfo.getText();
            if(!s.contains("5000원 이상 입력 불가"))	//만약 MachineInfo에 출력문이 없다면 MachineInfo에 5000원 이상 입력 불가 출력(중복 출력 방지)
                MachineInfo.append("5000원 이상 입력 불가\n");
        }
        else {												//1000원지폐가 3장 이하고 5000원 이하라면

            machine.AddInput(consumer.getMoney(n).getMoney());		//자판기의 input에 사용자의 돈 추가
            inputmoneylist.add(consumer.getMoney(n));				//moneylist에 입력한 돈 추가
            consumer.usingMoney(n);							//사용자의 돈에서 돈 감소

            //사용자의 돈 개수가 감소했으므로 사용자의 돈 버튼을 업데이트
            user[n].setText(("<HTML><body><center>"+Integer.toString(consumer.getMoney(n).getMoney())+"원<br>" +
                    Integer.toString(consumer.getMoney(n).getCount())+(consumer.getMoney(n).getCount()==1000?"장":"개")+"</body></HTML>"));
            inputMoney.setText(Integer.toString(machine.getInput() ));	//자판기 input텍스트도 업데이트

            //입력한 돈으로 음료수를 구입할 수 있는지 확인
            for(int i=0; i<5; i++) {
                if(machine.getInput() >= machine.getDrinks(i).getPrice()) {		//만약 현재 입력된 input이 음료수의 가격보다 높다면
                    drinkbutton[i].setEnabled(true);					//음료수 구입 가능
                    drinkbutton[i].setBackground(Color.red);			//음료수 버튼이 빨간색
                }
                if(machine.getDrinks(i).getStock()==0) {			//만약 음료수의 재고가 없다면
                    drinkbutton[i].setEnabled(false);				//음료수 버튼 클릭 불가
                    drinkbutton[i].setText("품");					//품절 표시
                    drinkbutton[i].setBackground(Color.black);
                }
            }
            returnbtn.setEnabled(true);					//사용자의 돈이 입력되면 반환버튼을 누를 수 있음
            for(int i=0; i<5; i++) {					//만약 사용자의 돈 개수가 0개라면
                if(consumer.getMoney(i).getCount()==0) {
                    user[i].setEnabled(false);			//돈 버튼 클릭 불가
                }
            }
            GetDrinkName.setText("");					//음료수 배출구 텍스트 초기화
            for(int i=0; i<5; i++) {					//거스름 돈 텍스트 초기화
                if(i!=0&&machine.getMoney(i).getCount()==0) {
                    coincount[i].setText("x");
                }
                else
                    coincount[i].setText("");
            }
        }
    }
}

