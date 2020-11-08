package myPackage;
import myPackage.Resister;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.*;

public class Login extends JFrame
{
    JLabel lbl,la1,la2,la3,emp;
    JTextField id;
    JPasswordField passwd;
    JPanel emptyPanel,idPanel,paPanel,loginPanel;
    JButton b1,b2;
    JTextArea content;
 
    public Login()
    {
          super( "Login Form" );
          
          // FlowLayout사용
          setLayout( new FlowLayout() );
          
          // Border로 영역 생성
          EtchedBorder eborder =  new EtchedBorder();
          // 레이블 생성     
          lbl = new JLabel( "Enter Id and Password" );
          // 레이블에 영역 만들기
          lbl.setBorder(eborder);
          // 레이블 추가
          add(lbl);

          emptyPanel = new JPanel();
          emp = new JLabel("\n");
          emptyPanel.add(emp);
          add(emp);
          
          // id패널과 pw 패널생성
          idPanel = new JPanel();
          paPanel = new JPanel();

          la3 = new JLabel("Username");
          la2 = new JLabel("      Password");
          // id텍스트필드와 pw텍스트 필드 선언
          id = new JTextField(20);
          passwd = new JPasswordField(20);
          idPanel.add(la3);
          idPanel.add(id);
          paPanel.add(la2);
          paPanel.add(passwd);
          
          // 로그인과 회원가입을 위한 패널 생성
          
          loginPanel = new JPanel();
          b1 = new JButton("Log in");
          b2 = new JButton("Resister");
          loginPanel.add(emp);
          loginPanel.add(b1);
          loginPanel.add(b2);
          add(emptyPanel);
          add(emptyPanel);
          add(idPanel);
          add(paPanel);
          add(emptyPanel);
          add(loginPanel);
          b1.addActionListener(new ActionListener() 
          {
        	  public void actionPerformed(ActionEvent e)
        	  {
        	  String logid = id.getText().trim();
        	  String logpw = passwd.getText().trim();
        	  if(logid.length()==0 || logpw.length()==0) 
        	  {
        	  JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 입력 하셔야 됩니다.", "아이디나 비번을 입력!", JOptionPane.DEFAULT_OPTION);
        	  return;
        	  }
        	  else if(logid.equals("test") && logpw.equals("test1")) 
        	  {
        	  JOptionPane.showMessageDialog(null, "로그인 성공", "로그인 확인!", JOptionPane.DEFAULT_OPTION);
        	  return;
        	  }
        	  JOptionPane.showMessageDialog(null, "로그인 실패", "로그인 확인!", JOptionPane.DEFAULT_OPTION);
        	  }
        	  });
          b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Resister();				
			}
          });
          // 3행 20열 영역의 텍스트에어리어 
          //content = new JTextArea(3,20);
          //JScrollPane s= new JScrollPane(content);
          //add(s);
          setSize( 400, 200 );
          setVisible(true);
          setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    

	public static void main(String args[]) { 
        new Login();
    }   
}