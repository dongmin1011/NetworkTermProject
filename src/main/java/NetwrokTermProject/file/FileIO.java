package NetwrokTermProject.file;


import java.awt.Dimension;
import java.awt.Font;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import javax.swing.*;

public class FileIO {
    private File file;
    //	private FileWriter fw;
    private BufferedWriter bw;
    private FileReader fr;
    private BufferedReader br;

    JFrame exhaustionframe= new JFrame();
    JFrame saleframe= new JFrame();

    JTextArea readexhaustion = new JTextArea();			//품절 정보를 출력할 TextArea
    JPanel readpanel = new JPanel();
    JScrollPane scrollPane = new JScrollPane(readexhaustion);

    JTextArea readSale = new JTextArea();				//판매 정보를 출력할 TextArea
    JPanel salepanel = new JPanel();
    JScrollPane scrollPane2 = new JScrollPane(readSale);

    public FileIO(){
        exhaustionframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        saleframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        readexhaustion.setEditable(false);
        readexhaustion.setFont(new Font("맑은 고딕", Font.PLAIN, 15));//readexhaustion의 폰트 설정
        readSale.setEditable(false);
        readSale.setFont(new Font("맑은 고딕", Font.PLAIN, 15));		//readSale의 폰트 설정

        scrollPane.setPreferredSize(new Dimension(520,640));		//scrollpane의 크기 설정
        readpanel.add(scrollPane);
        scrollPane2.setPreferredSize(new Dimension(520,640));		//scrollpane2의 크기 설정
        salepanel.add(scrollPane2);
    }

    public void openExhaustion() {					// 재고 소진 날짜 버튼 클릭시

        exhaustionframe.setTitle("재고 소진/추가 날짜");		//프레임의 타이틀 생성
        JLabel subject = new JLabel("재고 소진/추가 날짜");
        subject.setHorizontalAlignment(JLabel.CENTER);	//레이블 가운데 정렬
        subject.setFont(new Font("맑은 고딕", Font.BOLD, 20));		//글씨 폰트와 크기 설정
        exhaustionframe.add(subject, "North");			//프레임의 north에 추가


        ExhaustionFileRead();						//exhaustion파일로부터 데이터를 읽어옴

        exhaustionframe.setSize(560,720);
        exhaustionframe.setResizable(false);		//프레임 크기를 조정 불가
        exhaustionframe.setVisible(true);			//프레임이 보이도록함
    }
    public void openSale() {

        saleframe.setTitle("일별/월별 매출");				//프레임 타이틀 생성
        JLabel subject = new JLabel("일별/월별 매출");
        subject.setHorizontalAlignment(JLabel.CENTER);	//레이블 가운데 정렬
        subject.setFont(new Font("맑은 고딕", Font.BOLD, 20));	//글씨 크기, 폰트 설정
        saleframe.add(subject, "North");

        SaleFileRead();								//sale파일로부터 데이터를 읽어옴

        saleframe.setSize(560,720);
        saleframe.setResizable(false);				//프레임 크기 조정 불가
        saleframe.setVisible(true);					//프레임이 보이도록 설정
    }

    public void SaleFileWrite(String str, boolean write) { 	//sale텍스트 파일에 글을 씀 false가 입력되면 처음부터 다시 입력받고 true가 입력되면 뒤에 이어서 씀
        try {
            file = new File("sale.txt");					//sale.txt파일 오픈
            bw = new BufferedWriter(new FileWriter(file, write));	//bufferedWriter를 이용하면 빠른 입출력이 가능하다.

            if(file.isFile() && file.canWrite()) {		//파일이 존재하고 쓸 수 있다면

                bw.write(str);							//입력받은 str문자열을 파일에 추가
                if(!str.equals(""))	bw.newLine();

                bw.flush();								//남아있는 데이터를 모두 출력
                bw.close();								//스트림을 닫음
            }
        } catch (IOException e) {
            //	e.printStackTrace();
        }
    }
    void SaleFileRead() {						//sale.txt파일을 읽음
        saleframe.add(salepanel);

        try {
            file = new File("sale.txt");			//sale.txt파일 오픈
            fr = new FileReader(file);
            br = new BufferedReader(fr);	//bufferedreader을 이용하여 입력
            String line="";
            ArrayList<String>list = new ArrayList<>();		//파일 데이터를 저장할 arraylist
            readSale.setText("");
            while((line=br.readLine())!=null) {		//파일에서 한줄씩 line에 저장
                list.add(line);				//입력받은 line을 list에 추가
            }
            Collections.sort(list);			//파일에 있는 데이터가 무작위로 있는 경우를 생각하여 리스트틀 오름차순 정렬

            int monthly = 0;			//월 매출 저장
            int daily = 0;				//일 매출 저장
            int yearly = 0;				//연 매출 저장

            ArrayList<String> monthday = new ArrayList<>();
            ArrayList<String> day = new ArrayList<>();		//이미 입력된 일을 저장하는 리스트
            ArrayList<String> month = new ArrayList<>();	//이미 입력된 월을 저장하는 리스트
            ArrayList<String> year = new ArrayList<>();		//이미 입력된 년을 저장하는 리스트


            for(int i=0; i<list.size();) {
                String s = list.get(i);					//현재 리스트를 s에 저장한다.
                StringTokenizer st = new StringTokenizer(s," ");	//StringTokenizer클래스를 이용하여 " "를 기준으로 문자열을 나눈다.
                String []array = new String[st.countTokens()];	//토큰의 크기만큼의 array를 선언한다
                int k=0;
                while(st.hasMoreElements()) {
                    array[k++] = st.nextToken();		//array배열에 토큰을 저장한다.
                }
                //array의 가장 마지막에는 돈이 저장되어있다. 여기서 "원"을 제거한 문자열을 숫자로 변환하여 money에 저장한다.
                int money = Integer.parseInt(array[k-1].replace("원", ""));
                int count = 0;						//count는 같은 문자열의 개수

                for(int j=0; j<list.size();) {
                    if(s.equals(list.get(j))) {
                        count++;				//만약 같은 문자열이 있다면 count증가
                        list.remove(j);
                    }
                    else {
                        j++;					//같은 문자열이 없다면 j증가
                    }
                }
                if(isexist(monthday, array[k-4]+" "+array[k-3])) {
                    if(daily!=0) {
                        readSale.append("-----------일 매출: "+daily + "원\n");
                        monthly +=daily;									//일 매출을 월매출에 더함
                        daily = 0;											//일매출 초기화
                    }
                    if(isexist(month,array[k-4])) {
                        if(monthly!=0) {
                            readSale.append("---------------월 매출: "+monthly + "원\n");
                            yearly+=monthly;									//월매출을 연매출에 추가함
                        }

                    }
                    if(isexist(year,array[k-5])) {									//이미 년이 존재하는지 확인
                        if(yearly!=0) {
                            readSale.append("---------------년 매출: "+yearly + "원\n");	//년이 바뀌면 전년도의 매출 출력
                        }
                        readSale.append(array[k-5] + "\n");				//현재 년도를 readSale에 출력
                        year.add(array[k-5]);						//year리스트에 년도 추가
                        yearly = 0;									//연 매출을 초기화
                        month.clear();								//month리스트 초기화
                        monthday.clear();
                    }
                    if(isexist(month,array[k-4])) {					//이미 월이 존재하는지 확인
                        readSale.append("  "+array[k-4]+"\n");		//월이 존재하지 않으면 readSale에 출력
                        day.clear();								//월이 존재하지 않는다는 것은 일이 초기화 되었다는 뜻이므로 day를 clear
                        month.add(array[k-4]);						//month리스트에 월 추가
                        monthly = 0;								//월이 초기화 되었으므로 월 매출 초기화
                    }
                    if(isexist(day, array[k-3])) {						//이미 일이 존재하는지 확인
                        readSale.append("     "+array[k-3]+"\n");			//존재하지 않는다면 현재 일을 readSale에 출력하고
                        day.add(array[k-3]);							//day 리스트에 추가
                    }
                    monthday.add(array[k-4]+" "+array[k-3]);
                    daily = 0;
                }
                readSale.append("        " + array[k-2] +" "+ (money*count) + "원\n");	//현재 일에 팔린 돈을 출력
                daily += money*count;
            }
            monthly +=daily;
            yearly += monthly;

            if(daily!=0) readSale.append("-----------일 매출: "+daily + "원\n");		//파일의 마지막에 도달하면 일매출, 월매출, 연매출을 모두 출력
            if(monthly!=0) readSale.append("---------------월 매출: "+monthly + "원\n");
            if(yearly!=0) {
                readSale.append("---------------년 매출: "+yearly + "원\n");
            }

            fr.close();
            br.close();
        } catch (FileNotFoundException e) {			//만약 파일이 존재하지 않는다면
            readexhaustion.setText("파일 입출력 에러");		//파일 입출력 에러 출력
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }



    }
    boolean isexist(ArrayList<String>list, String s) {		//리스트에 문자열이 존재하는지 확인하는 함수
        for(int i=0; i<list.size(); i++) {
            if(list.get(i).equals(s))return false;			//만약 리스트에 문자열이 존재하면 false리턴
        }
        return true;						//리스트에 문자열이 존재하지 않으면 true리턴
    }

    void ExhaustionFileRead() {				//exhaustion텍스트 파일을 읽어옴

        exhaustionframe.add(readpanel);
        try {

            file = new File("Exhaustion.txt");		//exhaustion파일 오픈
            fr = new FileReader(file);
            br = new BufferedReader(fr);		//bufferedread를 사용하여 읽음
            String line="";
            ArrayList<String>list = new ArrayList<>();	//파일에 있는 데이터를 저장하기 위한 arraylist

            readexhaustion.setText("");				//textarea 초기화
            while((line=br.readLine())!=null) {		//파일 끝까지 한줄 씩 읽고 list에 저장
                list.add(line);
            }

            for(int i=0; i<list.size();) {		//0부터 list의 크기까지 반복
                String s = list.get(i);			//현재 리스트를 s에 저장
                int count = 0;
                if(s.contains("품절")) {			//만약 s문자열에 "품절"이라는 단어라 있으면 readexhaustion에 추가 후 list(i)값 제거 후 countinue
                    readexhaustion.append(s+"\n");
                    list.remove(i);
                    continue;
                }
                for(int j=0; j<list.size();) {			//만약 품절이라는 단어가 없다면 처음부터 끝까지 반복
                    if(s.equals(list.get(j))) {		//만약 list뒤에 s와 같은 문자열을 가지고 있는 문자열이 있다면
                        count++;					//count를 증가	(여러개 추가시 하나로 묶어서 출력하기 위함)
                        list.remove(j);				//현재 list를 제거
                    }
                    else {
                        j++;						//만약 같은 문자열이 없다면 j증가
                    }
                }
                s = s.replace("추가", count+"개 추가");	//추가 단어를 count개 추가로 변환
                readexhaustion.append(s+"\n");			//readexhaustion에 추가로 작성
            }
            fr.close();
            br.close();					//스트림을 닫음

        } catch (FileNotFoundException e) {		//만약 파일이 존재하지 않는다면
            readexhaustion.setText("파일 입출력 에러");

        } catch (IOException e) {

        }
    }
    public void ExhaustionFileWrite(String str, boolean write) {		//exhaustion텍스트 파일에 추가 write가 false면 덮어쓰고 true면 이어씀

        try {
            file = new File("Exhaustion.txt");
            bw = new BufferedWriter(new FileWriter(file, write)); //bufferedWriter를 이용하면 빠른 입출력이 가능하다

            if(file.isFile() && file.canWrite()) {		//파일이 존재하고 쓸 수 있다면

                bw.write(str);						//입력받은 str문자열을 파일에 씀
                if(!str.equals("")) bw.newLine();

                bw.flush();							//남아있는 데이터를 모두 출력
                bw.close();							//스트림을 닫음
            }


        } catch (IOException e) {

            //	e.printStackTrace();
        }
    }


}
