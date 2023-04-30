package NetwrokTermProject.Admin;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

//비밀번호 클래스
public class PassWord {
    private String PassWord;	//private으로 비밀번호 선언

    JFrame f = new JFrame();	//비밀번호 변경 프레임 생성

    static int count = 3;		//시간 초를 셀 count변수
    PassWord(){
        PassWord = "abcdefg";	//기본 비밀번호는 abcdefg로 생성
    }
    public void ChangePassWord() {
        f.setTitle("비밀번호 변경");		//프레임 타이틀 지정
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel TitleText = new JLabel("변경할 비밀번호를 입력하세요");
        TitleText.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        TitleText.setHorizontalAlignment(JLabel.CENTER);
        f.add(TitleText, BorderLayout.NORTH);

        JPanel Input = new JPanel();					//입력 필드와 버튼이 있는 패널
        JTextField changeTextfield = new JTextField();	//비밀번호 입력 필드
        JButton changebtn = new JButton("확인");			//비밀번호 입력 버튼
        changebtn.setPreferredSize(new Dimension(70, 50));	//버튼의 크기 설정

        Input.setLayout(new FlowLayout(0, 30, 10));			//컴포넌트가 추가되는 순서대로 왼쪽에서 오른쪽으로 추가
        changeTextfield.setPreferredSize(new Dimension(130, 50));	//입력 필드의 크기 설정

        Input.setPreferredSize(new Dimension(400, 50));			//Input패널의 크기 설정
        Input.add(new JLabel("입력 : "));				//Input패널에 입력 텍스트 삽입
        Input.add(changeTextfield);					//Input패널에 입력 필드 삽입
        Input.add(changebtn);						//Input패널에 입력 버튼 삽입

        f.add(Input, BorderLayout.CENTER);			//f프레임에 Input패널 삽입

        JPanel messageData = new JPanel();			//메세지를 출력하는 텍스트를 담고있는 패널
        JLabel message = new JLabel("");		//메시지는 공백이었다가 입력이 들어오면 출력이 됨.
        message.setFont(new Font("맑은 고딕", Font.PLAIN, 20));	//메시지의 폰트 설정
        messageData.add(message);
        f.add(messageData, BorderLayout.SOUTH);			//매시지 패널을 f프레임에 삽입

        changebtn.addActionListener(new ActionListener(){		//입력 버튼을 누를 때 동작
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                int n =	AvailablePassword(changeTextfield.getText());	//입력한 비밀번호가 적정한지 확인
                if(n == -1) {		//비밀번호가 8자리 미만일때
                    message.setText("비밀번호는 8자리 이상을 입력하세요");
                }
                else if(n==-2) {	//비밀번호에 숫자가 없을 때
                    message.setText("숫자가 하나 이상 포함되어야 합니다.");
                }
                else if(n==-3) {	//비밀번호에 특수문자가 없을때
                    message.setText("특수문자가 하나 이상 포함되어야 합니다.");
                }
                else if(n==-4) {	//비밀번호에 영문자가 없을 때
                    message.setText("영문자가 하나 이상 포함되어야 합니다.");
                }
                else {
                    PassWord = changeTextfield.getText();	//바뀐 비밀번호를 password에 저장
                    Timer timer=new Timer();
                    TimerTask task=new TimerTask(){
                        @Override
                        public void run() {
                            //TODO Auto-generated method stub

                            //비밀번호 변경성공 메시지 출력 후 3초 뒤 자동 종료
                            if(count>0) {
                                message.setText("비밀번호 변경 성공!"+count+"초 뒤 자동종료...");
                                count--;
                            }
                            else {
                                timer.cancel();
                                count = 3;			//count의 개수를 3초로 설정
                                message.setText("");	//메시지 텍스트를 지움
                                f.setVisible(false);	//f프레임이 보이지 않도록 설정
                            }
                        }
                    };
                    timer.schedule(task, 0, 1000); //실행 Task, 0초뒤 실행, 1초마다 반복
                }
                changeTextfield.setText("");	//입력 필드 초기화

            }
        });
        f.setResizable(false);	//프레임 크기 조정 불가능

        f.setSize(400,200);		//프레임 크기 설정
        f.setVisible(true);		//프레임이 보이도록 함
    }
    public String getPassword() {
        return PassWord;			//현재 비밀번호를 반환
    }
    int AvailablePassword(String str) {
        if(str.length() < 8) {	//만약 문자열의 길이가 8 이하면 -1 리턴
            return -1;
        }
        else if(!str.matches(".*[0-9].*")) {		//matches 정규식 패턴
            return -2;					//문자열에 숫자가 포함되어있지 않다면 -2리턴
        }
        else if(str.matches("^[ㄱ-ㅎ가-힣a-zA-Z0-9]*$")) {
            return -3;					//문자열에 특수문자가 포함되어 있지 않다면 -3리턴
        }
        int count = 0;
        for(int i=0; i<str.length(); i++) {
            if(isAlpha(str.charAt(i))) {	//문자열에 알파벳이 있는지 확인
                count++;					//알파벳의 개수 증가
            }
        }
        if(count>0) return 1;		//알파벳이 1개 이상이면 1 리턴
        else return -4;
    }
    public static boolean isAlpha(char char1) {		//알파벳이 존재하면 true리턴
        return (char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z');
    }


}
