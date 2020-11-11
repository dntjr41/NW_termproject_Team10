package myPackage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.net.*;
public class Login
{
	static String ip=null; //txt파일에서, ip읽어옴.
	static int portnum=0; //port number
    String serverAddress; //서버 주소
    Scanner in;
    PrintWriter out;
    JFrame frame=new JFrame("login form");
    JLabel lbl,la1,la2,la3,emp;
    JTextField id;
    JPasswordField passwd;
    JPanel emptyPanel,idPanel,paPanel,loginPanel;
    JButton b1,b2;
    JTextArea content;
    public Login(String serverAddress)
    {
    	this.serverAddress=serverAddress;
          // FlowLayout사용
          frame.setLayout( new FlowLayout() );
          
          // Border로 영역 생성
          EtchedBorder eborder =  new EtchedBorder();
          // 레이블 생성     
          lbl = new JLabel( "Enter Id and Password" );
          // 레이블에 영역 만들기
          lbl.setBorder(eborder);
          // 레이블 추가
          frame.add(lbl);

          emptyPanel = new JPanel();
          emp = new JLabel("\n");
          emptyPanel.add(emp);
          frame.add(emp);
          
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
          b1 = new JButton("Login");
          b2 = new JButton("Resister");
          loginPanel.add(emp);
          loginPanel.add(b1);
          loginPanel.add(b2);
          frame.add(emptyPanel);
          frame.add(emptyPanel);
          frame.add(idPanel);
          frame.add(paPanel);
          frame.add(emptyPanel);
          frame.add(loginPanel);
          b1.addActionListener(new ActionListener() 
          {
        	  public void actionPerformed(ActionEvent e)
        	  {
        		  String comment=e.getActionCommand();
        		  if(comment.contentEquals("Login")) //로그인 버튼을 눌렀을 때,
        		  {
        			  String logid= id.getText().trim(); //id에 누른 값을 불러옴
        			  String logpw= passwd.getText(); //패스워드에 누른 값을 불러옴.
        			  out.println("logid"+logid+" "+logpw); //서버에게 아이디와 패스워드 전달
        			  if(logid.length()==0 || logpw.length()==0) 
                	  {
                	  JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 입력 하셔야 됩니다.", "아이디나 비번을 입력!", JOptionPane.DEFAULT_OPTION);
                	  return;
                	  }
        			  frame.dispose();
        		  }
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
          frame.setSize( 400, 200 );
          frame.setVisible(true);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public class Resister{

    	JFrame sub=new JFrame("Resister");
    	JLabel lbl,namelbl,birthlbl,emaillbl, idlbl,pwlbl,nicklbl;
        JTextField nameField, birthField,emailField, idField, nickField;
        JPasswordField passwd;
        JPanel namePanel,birthPanel, emailPanel, idPanel,paPanel,nickPanel;
        JButton resister_btn;
        JTextArea content;
        
    	public Resister() {
    	        sub.setLayout(new FlowLayout());
    	        
    	        EtchedBorder eborder =  new EtchedBorder();
  
    	        lbl = new JLabel( "Enter user information" );
    	        // 레이블에 영역 만들기
    	        lbl.setBorder(eborder);
    	        sub.add( lbl );
    	        namePanel = new JPanel();
    	        birthPanel = new JPanel();
    	        emailPanel = new JPanel();
    	        idPanel = new JPanel();
    	        paPanel = new JPanel();
    	        nickPanel = new JPanel();
    	        
    	        namelbl = new JLabel("User Name");
    	        nameField = new JTextField(10);
    	        namePanel.add(namelbl);
    	        namePanel.add(nameField);
    	        sub.add(namePanel);
    	        
    	        birthlbl = new JLabel("birthday");
    	        birthField = new JTextField(10);
    	        birthPanel.add(birthlbl);
    	        birthPanel.add(birthField);
    	        sub.add(birthPanel);
    	        
    	        emaillbl = new JLabel("email");
    	        emailField = new JTextField(10);
    	        emailPanel.add(emaillbl);
    	        emailPanel.add(emailField);
    	        sub.add(emailPanel);
    	        idlbl = new JLabel("user Id");
    	        idField = new JTextField(10);
    	        idPanel.add(idlbl);
    	        idPanel.add(idField);
    	        sub.add(idPanel);
    	        pwlbl = new JLabel("password");
    	        passwd = new JPasswordField(10);
    	        paPanel.add(pwlbl);
    	        paPanel.add(passwd);
    	        sub.add(paPanel);
    	        nicklbl = new JLabel("nick name");
    	        nickField = new JTextField(10);
    	        nickPanel.add(nicklbl);
    	        nickPanel.add(nickField);
    	        resister_btn = new JButton("Resister");
    	        sub.add(resister_btn);
    	        resister_btn.addActionListener(new ActionListener() 
    	          {
    	        	  public void actionPerformed(ActionEvent e) 
    	        	  {
    	        		  String comment=e.getActionCommand();
    	        		  if(comment.contentEquals("Resister")) //register버튼 입력시,
    	        		  {
    	        			  String name=nameField.getText(); //이름, 생년월일등을 불러옴
    	        			  String bday=birthField.getText();
    	        			  String email=emailField.getText();
    	        			  String id=idField.getText();
    	        			  String pw=passwd.getText();
    	        			  out.println("Resister"+name+" "+bday+" "+email+" "+id+" "+pw); //서버에게 전달.
    	        		  }
    	        		  sub.dispose();
    	        	  }
    	        	  });
    	        sub.setSize( 250, 350 );
    	        sub.setVisible(true);
    	        sub.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	}
    }
    private void run() {
              try {
            	  Socket socket = new Socket(serverAddress, portnum); //입력받은 ip주소와 portnumber로 소켓 생성.
                  in = new Scanner(socket.getInputStream());
                  out = new PrintWriter(socket.getOutputStream(), true);
                  while(in.hasNextLine())
                  {
                	  String line=in.nextLine();
                	  System.out.println(line);
                	  if(line.contains("access"))
                	  {
                    	  JOptionPane.showMessageDialog(null, "로그인 성공", "로그인 확인!", JOptionPane.DEFAULT_OPTION);//서버가 로그인 성공이라고 했을 시, 창 표출
                    	  return;  
                	  }
                	  else if(line.contains("invalid"))
                	  {
                		  JOptionPane.showMessageDialog(null, "로그인 실패", "로그인 확인!", JOptionPane.DEFAULT_OPTION); //로그인 실패 시, 창표출
                		  return;
                	  }
                	  else if(line.contains("Welcome"))
                	  {
                		  JOptionPane.showMessageDialog(null, "회원가입성공", "환영합니다!", JOptionPane.DEFAULT_OPTION); //회원가입 성공 시, 창표출
                		  return;
                	  }
                  }
                  }
              catch(Exception e)
              {
            	  System.out.println(e);
              }
              finally {
                  frame.dispose();
              }
                 
              }
    public static void server(String fileName) //input.txt파일에서 ip주소와 port number를 불러오는 함수.
          {  
      		Scanner inputStream = null;
      		try 
      		{
      			inputStream = new Scanner(new File(fileName));//input파일을 읽어옴.
      		} 
      		catch (FileNotFoundException e) //파일이 없을 경우, 자동생성
      		{
      			ip = "localhost";
      			portnum = 9999; 
      			e.printStackTrace();
      		}
      		
      		while (inputStream.hasNext()) //input파일에서, ip주소와 portnumber를 읽어옴.
      		{
      			ip = inputStream.next(); 
      			portnum = inputStream.nextInt();
      		}
          }
    public static void main(String args[])
    {
    	 String fname="input.txt";
	        server(fname); // input내용으로 정보 불러오기
	        Login client=new Login(ip);
	        client.run();
    }
}
