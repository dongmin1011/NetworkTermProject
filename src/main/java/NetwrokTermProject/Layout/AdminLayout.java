package NetwrokTermProject.Layout;


import NetwrokTermProject.VendingMachine.Drinks.*;
import NetwrokTermProject.VendingMachine.VendingMachine;

import NetwrokTermProject.file.FileIO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminLayout  extends JFrame implements LayOutData{
    FileIO file = new FileIO();
    VendingMachine machine = VendingMachine.getInstance();
    static int collect = 0;

    JFrame adminframe = new JFrame();
    JButton[] AdminMenu = new JButton[5];
    JLabel[] info_label = new JLabel[5];
    JLabel[] info_num = new JLabel[5];
    JLabel InfoText = new JLabel("");
    JButton []collectbtn = new JButton[5];

    JButton[] Adminstock = new JButton[5];
    JButton[] AdminAdditional = new JButton[5];

    JButton changePWbtn = new JButton("비밀번호 변경");
    JButton AddMoney = new JButton("돈 추가");
    //관리자 창의 메인 정보를 출력하는 레이블과 버튼들 선언
    public AdminLayout() {
        adminframe.setTitle("관리자 모드");
        adminframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel AdminGrid = new JPanel();						//관리자 버튼을 저장할 패널
        AdminGrid.setLayout(new GridLayout(5,3,10,30));			//관리자 버튼패널읠 GridLayout으로 5행 3열로 생성

        JPanel textPanel = new JPanel();
        JLabel text = new JLabel("자판기 관리자 메뉴");
        text.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        textPanel.setPreferredSize(new Dimension(560, 40));
        textPanel.setBackground(Color.yellow);
        textPanel.add(text);

        JPanel adminPanel = new JPanel();

        adminPanel.add(textPanel, BorderLayout.NORTH);

        String [] MenuName = {"화폐현황", "재고현황", "수금", "일별/월별 매출", "재고 소진/추가 날짜"};		//레이블을 생성할 때 한번에 생성 가능
        JPanel adminmenuPanel = new JPanel();

        String [] temp = {"자판기 관리", "재고 추가", "이름, 가격 설정"};		//레이블을 생성할 때 반복문을 이용하여 한번에 생성 가능
        JPanel Subject = new JPanel();
        JLabel[] sub = new JLabel[3];
        Subject.setLayout(new GridLayout(1, 3,10,50));				//SubjectPanel을 GridLayout 1행 3열로 생성
        Subject.setPreferredSize(new Dimension(540, 40));
        Subject.setBackground(Color.pink);
        for(int i=0; i<3; i++) {
            sub[i] = new JLabel(temp[i]);					//버튼들의 subject를 나타내는 레이블 생성
            sub[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
            sub[i].setHorizontalAlignment(JLabel.CENTER);
            Subject.add(sub[i]);								//Subject패널에 차례로 삽입
        }
        adminPanel.add(Subject);

        AdminGrid.setPreferredSize(new Dimension(540, 260));	//AdminGrid의 크기 고정
        for(int i=0; i<5; i++) {
            AdminMenu[i] = new JButton(MenuName[i]);			//관리자 메뉴버튼 추가
            AdminGrid.add(AdminMenu[i]);
            Adminstock[i] = new JButton(machine.getDrinks(i).getName()+"재고 추가");		//재고 추가 버튼 추가
            AdminGrid.add(Adminstock[i]);
            AdminAdditional[i] = new JButton(machine.getDrinks(i).getName()+" 이름, 가격변경");	//이름 가격변경 버튼 추가
            AdminGrid.add(AdminAdditional[i]);
        }
        for(int i=0; i<5; i++) {
            info_label[i] = new JLabel("");
            info_label[i].setHorizontalAlignment(JLabel.CENTER);
            info_label[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));

            info_num[i] = new JLabel("");
            info_num[i].setHorizontalAlignment(JLabel.CENTER);
            info_num[i].setFont(new Font("맑은 고딕", Font.PLAIN, 20));

            collectbtn[i] = new JButton();
            collectbtn[i].setContentAreaFilled(false);		//수금 버튼 배경 제거
            collectbtn[i].setBorderPainted(false);			//수금 버튼 테두리 제거
            collectbtn[i].setVisible(false);				//수금 버튼이 안보이도록 설정
            collectbtn[i].setEnabled(false);				//수금 버튼 클릭 불가
        }
        JPanel Info_data = new JPanel();							//자판기의 정보를 출력할 패널

        changePWbtn.setPreferredSize(new Dimension(100, 90));
        changePWbtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        AddMoney.setPreferredSize(new Dimension(100, 90));
        AddMoney.setFont(new Font("맑은 고딕", Font.BOLD, 15));

        Info_data.setLayout(new GridLayout(3,5));			//자판기 정보 출력 패널을 GridLayout으로 생성
        Info_data.setPreferredSize(new Dimension(540, 200));

        for(int i=0; i<5; i++) Info_data.add(info_label[i]);	//화폐 또는 음료수정보 레이블 추가
        for(int i=0; i<5; i++) Info_data.add(info_num[i]);		//개수정보 레이블 추가
        for(int i=0; i<5; i++) Info_data.add(collectbtn[i]);	//수금 버튼 추가
        JPanel change = new JPanel();
        JPanel InfoPanel  = new JPanel();
        InfoPanel.setPreferredSize(new Dimension(100, 90));
        change.setLayout(new GridLayout(1,3));				//돈 추가 버튼, 비밀번호 변경 버튼을 저장하기 위해 레이아웃 생성

        change.add(AddMoney);							//돈 추가 버튼 추가
        InfoPanel.add(InfoText);
        change.add(InfoPanel);							//임시 패널 추가
        change.add(changePWbtn);						//비밀번호 변경 버튼 추가

        change.setPreferredSize(new Dimension(550, 110));	//패널의 크기 설정

        adminmenuPanel.add(AdminGrid);
        adminPanel.add(adminmenuPanel);
        adminPanel.add(Info_data);
        adminPanel.add(change);

        Buttonfunc();

        adminframe.add(adminPanel);
        adminframe.setResizable(false);
        adminframe.setSize(560,720);
        adminframe.setVisible(true);
    }
    public void Buttonfunc() {
        changePWbtn.addActionListener(e->{					//만약 비밀번호 변경 버튼을 누를 시 동작
            dataClear();
            machine.getAdmin().getPW().ChangePassWord();
        });
        AddMoney.addActionListener(e->{						//돈 추가 버튼을 누를 시 동작
            dataClear();
            for(int i=0; i<5; i++) {
                info_label[i].setText(Integer.toString(machine.getMoney(i).getMoney()) + "원");
                info_num[i].setText(Integer.toString(machine.getMoney(i).getCount())+ ((machine.getMoney(i).getMoney()==1000)? "장":"개" ));
            }
            moneyadditional();
        });

        AdminMenu[0].addActionListener(e->{		//화폐 현황 버튼 클릭 시 동작
            dataClear();
            for(int i=0; i<5; i++) {		//info_label과 info_num의 데이터를 화폐와 개수를 출력할 수 있도록 설정
                info_label[i].setText(Integer.toString(machine.getMoney(i).getMoney()) + "원");
                info_num[i].setText(Integer.toString(machine.getMoney(i).getCount())+ ((machine.getMoney(i).getMoney()==1000)? "장":"개" ));

            }
            InfoText.setText("");

        });
        AdminMenu[1].addActionListener(e->{		//재고 현황 동작 버튼 클릭시 동작
            InfoText.setText("");
            dataClear();
            for(int i=0; i<5; i++) {		//info_label과 info_num의 데이터를 음료수와 개수를 출력할 수 있도록 설정
                info_label[i].setText(machine.getDrinks(i).getName());
                info_num[i].setText(Integer.toString(machine.getStock(i))+ "개");

            }
        });
        AdminMenu[2].addActionListener(e->{			//수금 버튼 클릭 시 동작
            InfoText.setText("");
            for(int i=0; i<5; i++) {			//Info를 화폐로 초기화
                info_label[i].setText(Integer.toString(machine.getMoney(i).getMoney()) + "원");
                info_num[i].setText(Integer.toString(machine.getMoney(i).getCount())+ ((machine.getMoney(i).getMoney()==1000)? "장":"개" ));
                collectbtn[i].setText("수금하기");
                collectbtn[i].setFont(new Font("맑은 고딕", Font.PLAIN, 15));
                collectbtn[i].setVisible(true);				//수금 버튼은 보이지만 클릭은 아직 불가

                if(machine.getMoney(i).getCount()>5) {		//만약 화폐의 개수가 6개 이상이면 수금 가능
                    collectbtn[i].setEnabled(true);
                }
                else {
                    collectbtn[i].setEnabled(false);		//화폐의 개수가 5개 이하면 버튼 클릭 불가
                }
            }
            if(machine.getMoney(0).getCount()>0) {			//1000원의 경우는 1개 이상이면 클릭 가능
                collectbtn[0].setEnabled(true);
            }

        });
        AdminMenu[3].addActionListener(e->{			//일별/월별 매출버튼 클릭시 동작
            dataClear();
            file.openSale();				//sale텍스트파일 open
        });
        AdminMenu[4].addActionListener(e->{			//재고 소진/추가 날짜버튼 클릭 시 동작
            dataClear();
            file.openExhaustion();			//exhaustion텍스트 파일 open
        });
        //수금 버튼 동작 설정
        collectbtn[0].addActionListener(e->collectmoney(0));
        collectbtn[1].addActionListener(e->collectmoney(1));
        collectbtn[2].addActionListener(e->collectmoney(2));
        collectbtn[3].addActionListener(e->collectmoney(3));
        collectbtn[4].addActionListener(e->collectmoney(4));

        //재고 추가 버튼
        Adminstock[0].addActionListener(e->AccessDrinks(0));
        Adminstock[1].addActionListener(e->AccessDrinks(1));
        Adminstock[2].addActionListener(e->AccessDrinks(2));
        Adminstock[3].addActionListener(e->AccessDrinks(3));
        Adminstock[4].addActionListener(e->AccessDrinks(4));

        //이름 가격 변경 버튼
        AdminAdditional[0].addActionListener(e->changeDrink(0));
        AdminAdditional[1].addActionListener(e->changeDrink(1));
        AdminAdditional[2].addActionListener(e->changeDrink(2));
        AdminAdditional[3].addActionListener(e->changeDrink(3));
        AdminAdditional[4].addActionListener(e->changeDrink(4));
    }
    void dataClear() {						//관리자 Info의 초기화
        InfoText.setText("");
        for(int i=0; i<5; i++) {
            info_label[i].setText("");
            info_num[i].setText("");		//text초기화
            collectbtn[i].setText("");		//수금하기 버튼을 누를 수 없고 사라짐
            collectbtn[i].setVisible(false);
            collectbtn[i].setEnabled(false);
        }
    }
    void AccessDrinks(int n) {						//음료수 보충 함수
        InfoText.setText("");
        dataClear();								//Info초기화
        if(machine.getStock(n) <10) {	//만약 음료수의 개수가 10개 이하라면
//            drinkbutton[n].setText("");				//drinkbutton을 ""로 초기화(품절표시를 없앰)
//            machine.getDrinks(n).Add();				//자판기의 음료수 개수 증가
            if(n==0) machine.inputDrinks(new Water(), n);
            else if(n==1) machine.inputDrinks(new Coffee(), n);
            else if(n==2) machine.inputDrinks(new SportsDrink(), n);
            else if(n==3) machine.inputDrinks(new HighQualityCoffee(), n);
            else if(n==4)machine.inputDrinks(new Soda(), n);
//            String s = MachineInfo.getText();
            String tempdrinkname = machine.getDrinks(n).getName();
            tempdrinkname += " 품절\n";
//            if(s.contains(tempdrinkname)) {				//만약 품절 표시가 있다면 음료수를 추가 했으므로 지워줌
//                s = s.replace(tempdrinkname, "");
//                MachineInfo.setText(s);
//            }
//            if(s.contains("음료수가 하나도 없습니다!")) {			//음료수가 하나도 없다는 메시지가 존재하면	 지워줌
//                s = s.replace("음료수가 하나도 없습니다!\n", "");
//                MachineInfo.setText(s);
//            }
            Calendar today = Calendar.getInstance();
            file.ExhaustionFileWrite(today.get(Calendar.YEAR )+"년 "+getToday(today.get(Calendar.MONTH )+1)+"월 "+getToday(today.get(Calendar.DATE ))+"일 "
                    + getToday(today.get(Calendar.HOUR_OF_DAY ))+"시 "+getToday(today.get(Calendar.MINUTE ))+"분 " +
                    machine.getDrinks(n).getName() +" 추가", true);		//음료수를 추가한 날짜를 파일에 저장

        }

        else InfoText.setText("10개 이상 보충 불가");		//만약 음료의 개수가 10개 이상 증가하면 보충 불가

        for(int i=0; i<5; i++) {							//음료수의 재고 변화를 업데이트
            info_label[i].setText(machine.getDrinks(i).getName());
            info_num[i].setText(Integer.toString(machine.getStock(i))+ "개");
        }

    }
    void changeDrink(int n) {				//음료수의 이름과 가격 변경

        InfoText.setText("");
        dataClear();
        JFrame changeframe = new JFrame();					//새로운 프레임 생성
        changeframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        changeframe.setTitle(machine.getDrinks(n).getName()+" 이름, 가격 변경");	//프레임 타이틀 설정

        JLabel subjectText = new JLabel(machine.getDrinks(n).getName()+"의 이름, 가격을 변경합니다.");
        subjectText.setHorizontalAlignment(JLabel.CENTER);
        subjectText.setFont((new Font("맑은 고딕", Font.BOLD, 20)));

        JPanel changeData = new JPanel();
        JPanel changePanel = new JPanel();
        JLabel [] input = new JLabel[2];
        JTextField [] inputfield = new JTextField[2];
        JButton [] inputbtn = new JButton[2];

        changePanel.setLayout(new GridLayout(2, 3, 0, 10));		//이름과 가격을 입력받을 패널을 2x3으로 생성
        changePanel.setPreferredSize(new Dimension(350, 70));	//changepanel의 사이즈를 지정
        String [] tempinput = {"이름 입력", "가격 입력(원없이)"};
        String tempbtn = "변경";

        for(int i=0; i<2; i++) {
            input[i] = new JLabel(tempinput[i]);
            input[i].setHorizontalAlignment(JLabel.CENTER);
            inputfield[i] = new JTextField();
            inputbtn[i] = new JButton(tempbtn);

            changePanel.add(input[i]);				//생성한 레이블과 필드, 패널을 차례대로 changepanel에 삽입
            changePanel.add(inputfield[i]);
            changePanel.add(inputbtn[i]);
        }

        JLabel Textlabel = new JLabel("");
        Textlabel.setHorizontalAlignment(JLabel.CENTER);
        Textlabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));


        inputbtn[0].addActionListener(e->{			//이름 변경 버튼을 눌렀을 때
            String s = inputfield[0].getText();

            int count=0;
            for(int i=0; i<s.length(); i++) {
                if(s.charAt(i)>='0' && s.charAt(i)<='9') {
                    count++;			//만약 문자열에 숫자가 존재한다면 count증가
                }
            }
            if(count==0) {			//문자열에 숫자가 없다면 변경
                if(s.length()>0) {
                    String str = MachineInfo.getText();
                    str = str.replace(machine.getDrinks(n).getName(), inputfield[0].getText());	//만약 이미 음료수가 품절되어 있다면 이름을 바꿈
                    MachineInfo.setText(str);

                    machine.getDrinks(n).setName(s);	//음료수의 이름 변경

                    Adminstock[n].setText(machine.getDrinks(n).getName()+"재고 추가");			//재고 추가와 이름,가격변경의 버튼도 변경
                    AdminAdditional[n].setText(machine.getDrinks(n).getName()+  "이름, 가격변경");


                    Textlabel.setText("이름 변경완료!");
                    inputfield[0].setText("");
//                    drink_name[n].setText(machine.getDrinks(n).getName());		//자판기 화면에서 음료수 이름도 변경
                }
                else {
                    Textlabel.setText("정확히 입력하세요");
                    inputfield[0].setText("");
                }
            }
            else {		//문자열에 숫자가 존재한다면 이름 변경 불가
                Textlabel.setText("이름에 숫자가 들어갈 수 없습니다");
                inputfield[0].setText("");
            }
        });

        inputbtn[1].addActionListener(e->{			//가격 변경 버튼을 눌렀을 때 동작
            try {
                Textlabel.setText("");
                String s = inputfield[1].getText();	//inputfiled를 s에 저장
                if(s.length()>0) {
                    int price = Integer.parseInt(s);	//s문자열을 숫자로 변환
                    if(price%10!=0) {				//만약 price에 1원이 존재한다면
                        price = price - price%10;		//10원까지 입력받도록 조정
                        Textlabel.setText("<HTML>10원자리까지 입력 가능합니다.<br>");
                    }
                    machine.getDrinks(n).setPrice( price );		//음료수의 가격 변경
//                    Drink_price[n].setText("<HTML>"+ Integer.toString(machine.getDrinks(n).getPrice())+"원</HTML>" );	//음료수 가격 변경
                    inputfield[1].setText("");

                    Textlabel.setText( Textlabel.getText() + "가격 변경완료!");
                }
                else {
                    Textlabel.setText("정확히 입력하세요"); //length가 0이면 변경 불가
                    inputfield[1].setText("");
                }
            }
            catch(NumberFormatException e1) {		//만약 문자가 입력되었다면 숫자로 변경 불가
                inputfield[1].setText("");
                Textlabel.setText("숫자만 입력하세요!");
            }

        });

        changeData.add(changePanel);		//changeData패널에 changepanel삽입
        changeData.add(Textlabel);			//textlabel삽입

        changeframe.add(subjectText, "North");
        changeframe.add(changeData);
        changeframe.setResizable(false);
        changeframe.setSize(400,200);
        changeframe.setVisible(true);
    }
    void collectmoney(int n) {			//수금하기 버튼 클릭 시 동작
        InfoText.setText("");

        JFrame CollectFrame = new JFrame();				//collectframe생성
        CollectFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        CollectFrame.setTitle(machine.getMoney(n).getMoney() +"원짜리 수금하기");		//타이틀 지정

        //subject레이블 생성 후 collectframe에 add
        JLabel collectsubject = new JLabel("몇" + (machine.getMoney(n).getMoney()==1000?"장의 지폐":"개의 동전") +"을 수금할 건지 입력하세요");
        collectsubject.setHorizontalAlignment(JLabel.CENTER);
        collectsubject.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        CollectFrame.add(collectsubject, "North");					//collectsubject레이블을 collectframe의 north에 삽입

        JPanel Panel = new JPanel();
        JPanel InputPanel = new JPanel();
        JLabel Input = new JLabel("개수 입력(숫자만 입력) : ");
        JTextField Inputtext = new JTextField();					//수금할 돈의 개수를 입력하는 필드
        JButton Inputbtn = new JButton("입력");							//수금 버튼 생성

        InputPanel.setLayout(new FlowLayout(JLabel.CENTER, 10, 20)); //InputPanel의 레이아웃을 FlowLayout으로 생성
        InputPanel.setPreferredSize(new Dimension(400, 70));

        Inputtext.setPreferredSize(new Dimension(50, 30));			//필드의 크기 고정
        Input.setFont(new Font("맑은 고딕", Font.BOLD, 15));

        InputPanel.add(Input);									//InputPanel에 Input레이블, 텍스트, 버튼 추가
        InputPanel.add(Inputtext);
        InputPanel.add(Inputbtn);
        Panel.add(InputPanel);

        JLabel Info_text = new JLabel("");
        Info_text.setHorizontalAlignment(JLabel.CENTER);
        Info_text.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        Panel.add(Info_text);

        Inputbtn.addActionListener(e->{					//수금 버튼을 눌렀을 때
            try {
                String s = Inputtext.getText();
                int tmp = Integer.parseInt(s);			//문자열을 숫자로 변환
                if(machine.getMoney(n).getMoney()==1000) {			//만약 1000원 지폐면 0개가 될때까지 수금 가능
                    if(machine.getMoney(n).getCount() - tmp >=0 ) {	//자판기가 가지고 있는 개수에서 tmp개를 빼도 0 이상이라면 수금 가능
                        for(int i=0; i<tmp; i++) {
                            machine.getMoney(n).Sub();		//자판기의 돈 감소
                        }
                        collect += machine.getMoney(n).getMoney()*tmp;
                        Inputtext.setText("");
                        Info_text.setText("현재 손익 : "+ Integer.toString(collect));	//수금 된 돈 메시지 출력
                    }
                    else {						//자판기에 있는 돈보다 많은 돈을 입력했을 경우
                        Inputtext.setText("");
                        Info_text.setText("수금할 돈이 현재 개수를 초과했습니다.");
                    }
                }
                else {			//1000을 제외한 동전 수금의 경우
                    if(machine.getMoney(n).getCount() - tmp >=5 ) {	//동전은 거스름돈이 필요하므로 5개까지 수금 가능
                        for(int i=0; i<tmp; i++) {
                            machine.getMoney(n).Sub();			//자판기 동전의 개수 감소
                        }
                        collect += machine.getMoney(n).getMoney()*tmp;
                        Inputtext.setText("");
                        Info_text.setText("현재 손익 : "+ Integer.toString(collect));	//수금 완료 메시지 출력
                    }
                    else {								//만약 동전이 5개 이하로 남게 된다면
                        Inputtext.setText("");
                        Info_text.setText("잔돈으로 써야 할 최소 5개의 동전이 필요합니다!");
                    }
                }
                for(int i=0; i<5; i++) {			//관리자 정보 레이블 업데이트
                    info_label[i].setText(Integer.toString(machine.getMoney(i).getMoney()) + "원");
                    info_num[i].setText(Integer.toString(machine.getMoney(i).getCount())+ ((machine.getMoney(i).getMoney()==1000)? "장":"개" ));

                    if(machine.getMoney(i).getCount()>5) {		//만약 자판기가 가지고 있는 돈 개수가 아직도 5 이상이면 버튼 클릭 가능 true
                        collectbtn[i].setEnabled(true);
                    }
                    else {
                        collectbtn[i].setEnabled(false);		//5이하이면 false
                    }

                }
                if(machine.getMoney(0).getCount()>0) {			//1000원짜리의 경우는 1개 이상이면 true
                    collectbtn[0].setEnabled(true);
                }
            }
            catch(NumberFormatException e1) {		//만약 숫자말고 문자를 입력햇다면
                Inputtext.setText("");
                Info_text.setText("숫자만 입력하세요!");
            }

        });
        CollectFrame.add(Panel);		//CollectFrame에 Panel추가

        CollectFrame.setResizable(false);
        CollectFrame.setSize(400, 200);
        CollectFrame.setVisible(true);
    }
    //만약 자판기에 잔돈이 없을 경우 잔돈을 추가하기 위한 패널
    void moneyadditional() {
        InfoText.setText("");
        dataClear();

        JFrame addframe = new JFrame();
        addframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addframe.setTitle("잔돈 추가");					//title지정

        JLabel text = new JLabel("추가할 잔돈의 버튼을 누르세요");
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        addframe.add(text, "North");

        JPanel addbtnpanel = new JPanel();		//addbtnpanel패널 생성
        addbtnpanel.setPreferredSize(new Dimension(400,75));
        JPanel addbtngrid = new JPanel();		//grid로 생성할 패널 생성

        addbtngrid.setLayout(new GridLayout(2, 2, 10, 10));	//패널의 layout을 2x2 Grid로 생성
        JButton[] addbtn = new JButton[4];					//4개의 버튼 생성
        JPanel addInfo = new JPanel();

        for(int i=0; i<4; i++) {
            addbtn[i] = new JButton(Integer.toString(machine.getMoney(i+1).getMoney())+" 동전 추가");	//동전 추가 버튼 초기화
            addbtngrid.add(addbtn[i]);			//grid패널에 삽입
        }
        addbtnpanel.add(addbtngrid);
        addInfo.add(addbtnpanel);

        JPanel Info = new JPanel();
        Info.setLayout(new GridLayout(2,1));
        JLabel addText = new JLabel("");
        JLabel moneytext = new JLabel("");
        moneytext.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        moneytext.setHorizontalAlignment(JLabel.CENTER);
        addText.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        addText.setHorizontalAlignment(JLabel.CENTER);
        addbtn[0].addActionListener(e->	addmoney(0,addText,moneytext));		//동전 추가 버튼 클릭시 동작
        addbtn[1].addActionListener(e->	addmoney(1,addText,moneytext));
        addbtn[2].addActionListener(e->	addmoney(2,addText,moneytext));
        addbtn[3].addActionListener(e->	addmoney(3,addText,moneytext));

        Info.add(addText);
        Info.add(moneytext);
        addInfo.add(Info);

        addframe.add(addInfo);
        addframe.setResizable(false);
        addframe.setSize(400, 200);
        addframe.setVisible(true);
    }
    void addmoney(int n, JLabel text, JLabel moneytext) {		//잔돈 추가 함수
        machine.getMoney(n+1).Add();		//입력받은 n에 맞게 개수 추가
        for(int i=0; i<5; i++) {			//추가된 개수 info업데이트
            info_label[i].setText(Integer.toString(machine.getMoney(i).getMoney()) + "원");
            info_num[i].setText(Integer.toString(machine.getMoney(i).getCount())+ ((machine.getMoney(i).getMoney()==1000)? "장":"개" ));
        }
        String s = MachineInfo.getText();

        text.setText(machine.getMoney(n+1).getMoney() + "원짜리 추가 완료");		//text에 추가 완료 메시지 출력

        if(machine.getMoney(n+1).getCount() > 0) {			//만약 동전의 개수가 0 이상이 되었다면
            s = MachineInfo.getText();
            if(s.contains(Integer.toString(machine.getMoney(n+1).getMoney())+"원짜리 개수 부족\n")) {		//동전 부족 메시지 제거
                s= s.replace(Integer.toString(machine.getMoney(n+1).getMoney())+"원짜리 개수 부족\n", "");
                if(coincount[n+1].getText().equals("x"))
                    coincount[n+1].setText("");
                MachineInfo.setText(s);
            }
        }
        s = MachineInfo.getText();
        if(s.contains("동전이 하나도 없습니다")) {					//만약 동전이 하나도 없다는 메시지가 있다면
            s= s.replace("동전이 하나도 없습니다.\n 관리자를 부르세요!\n", "");		//동전 부족 메시지 제거
            MachineInfo.setText(s);
        }

        int input = machine.getInput();							//자판기에 입력된 input을 저장
        for(int j=1; j<5; j++) {
            for(int i=0; i<machine.getMoney(j).getCount(); i++) {
                if(input - machine.getMoney(j).getMoney() >= 0) {
                    input -= machine.getMoney(j).getMoney();			//거스름돈 추가시 자판기에 남아있는 돈 계산
                }
                else break;
            }
        }
        collect-=machine.getMoney(n+1).getMoney();
        moneytext.setText("현재 손익 : "+Integer.toString(collect));
        if(input==0) {								//만약 자판기의 input이 0이라면
            s = MachineInfo.getText();
            if(s.contains("거스름 돈이 부족합니다!")) {		//거스름돈이 부족하다는 메시지 제거
                s = s.replace("거스름 돈이 부족합니다!\n관리자를 부르세요!\n", "");
                MachineInfo.setText(s);
            }									//거스름 돈이 충분하므로 반환 버튼 활성화
            returnbtn.setEnabled(true);			//반환버튼을 누르면 사용자 입력을 받을 수 있음
        }
    }

}

