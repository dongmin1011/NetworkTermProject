package NetwrokTermProject.Admin;

import NetwrokTermProject.Layout.AdminLayout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Admin{
    private PassWord pw;
    JFrame f = new JFrame();
    AdminLayout al;
    public Admin(){										//Admin생성자
        pw = new PassWord();							//비밀번호 생성
    }
    public PassWord getPW() {
        return pw;					//비밀번호 리턴
    }
//    public boolean isAdmin() {				//adminlayout이 생성되었는지 확인
//        if(al==null)return false;
//        else return true;
//    }
    public void dataClear() {				//adminlayout의 화면을 초기화
//        al.dataClear();
    }
public void AccessAdmin() {							//관리자 접근을 위해 비밀번호 입력
        f.setTitle("자판기 관리자 비밀번호");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel all = new JPanel();
        JPanel Input_PW = new JPanel();						//비밀번호 입력 패널
        JTextField Password = new JTextField("");			//비밀번호 입력 필드
        JButton input = new JButton("입력");					//입력 버튼
        JLabel text = new JLabel("비밀번호를 입력하세요", JLabel.CENTER);
        JPanel messagePanel = new JPanel();					//메시지를 담고있는 패널
        JLabel message = new JLabel("", JLabel.CENTER);		//메시지 레이블

        message.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        Password.setPreferredSize(new Dimension(130, 50));	//필드 크기 설정

        input.setPreferredSize(new Dimension(70, 50));		//버튼 크기 설정
        all.add(text, BorderLayout.NORTH);


        Input_PW.setLayout(new FlowLayout(0, 30, 10));		//Input_PW을 Flowlayout으로 선언
        Input_PW.add(new JLabel("비밀번호 : "));
        Input_PW.add(Password);
        Input_PW.add(input);						//비밀번호텍스트, 입력 필드, 버튼 차례로 추가
        all.add(Input_PW, BorderLayout.CENTER);		//all패널의 center에 추가

        messagePanel.add(message);		//메시지 필드 추가
        all.add(messagePanel);

        f.add(all);

        input.addActionListener(new ActionListener(){			//비밀번호 입력 버튼
            @Override
            public void actionPerformed(ActionEvent e) {

                if(Password.getText().equals(pw.getPassword())) {	//만약 입력한 텍스트가 비밀번호와 같다면
                    Password.setText("");
                    message.setText("");
                    new AdminLayout();
                    f.setVisible(false);
                    //관리자 모드 접근
                }
                else {
                    message.setText("비밀번호가 일치하지 않습니다.");		//틀리면 비밀번호가 일치하지 않습니다 출력
                    Password.setText("");
                }
            }
        });
        f.setResizable(false);				//크기 조정 불가
        f.setSize(400,200);					//프레임 사이즈 선언
        f.setVisible(true);
    }

}
