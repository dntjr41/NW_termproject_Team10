package myPackage2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.net.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.ListSelectionModel;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

public class Login
{
	
	//
	static String ip=null; //txt파일에서, ip읽어옴.
	static int portnum=0; //port number
    String serverAddress; //서버 주소
    Scanner in;
    PrintWriter out;
    String curid;    //현재 로그인 id
    
    //login gui variables
    JFrame frame=new JFrame("login form");
    JLabel lbl,la1,la2,la3,emp;
    JTextField id;
    JPasswordField passwd;
    JPanel emptyPanel,idPanel,paPanel,loginPanel;
    JButton b1,b2;
    JTextArea content;
    
    //TODO:
    //메뉴창
    
    JFrame menu_frame;
	JLabel user_name;
	JLabel user_state;
	JPanel menu_p;
	JButton make_room;
	JButton add_friend;
	JList friend_list;
	DefaultListModel friend_model;
	JButton change_info;
	JPopupMenu popupMenu;
	JMenuItem lookinfo;
	JMenuItem sendMsg;
    
    
    //채팅방 swing 변수
    JFrame room_frame;
	JTextField sendTF; //보낼 메시지 적는 곳
	JLabel la_msg;	
	JTextArea ta;	//메시지가 쌓여서 보이는 곳
	JScrollPane sp_ta,sp_list;  
    JList<String> user;	// 현재 채팅방에 있는 유저 표시
	JButton invite_btn,exit_btn;  //초대 나가기 버튼 
	JPanel p;	 
	
	//추가하려는 친구
    String relaid;
	
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
    	        sub.add(nickPanel);

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
    	        			  String nick = nickField.getText();
    	        			  out.println("Resister"+name+" "+bday+" "+email+" "+id+" "+pw + " " + nick); //서버에게 전달.
    	        		  }
    	        		  sub.dispose();
    	        	  }
    	        	  });
    	        sub.setSize( 250, 350 );
    	        sub.setVisible(true);
    	        sub.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	}
    }
     
    //TODO:
    public class Chatting_Room {
    	
    		
    	   public Chatting_Room(String Room_name) {

    		  room_frame = new JFrame(Room_name);

    		  sendTF = new JTextField(15);	  

    		  la_msg = new JLabel("Message");

    		  	  

    		  ta = new JTextArea();

    		  ta.setLineWrap(true);//TextArea 가로길이를 벗어나는 text발생시 자동 줄바꿈

    		  user = new JList<String>();

    		  

    		  sp_ta = new JScrollPane(ta);

    		  sp_list = new JScrollPane(user);

    		  	  

    		  invite_btn = new JButton("친구 초대");

    		  exit_btn = new JButton("나가기");

    		  

    		  p = new JPanel();

    		  

    		  sp_ta.setBounds(10,10,380,390); 

    		  la_msg.setBounds(10,410,60,30); 

    		  sendTF.setBounds(70,410,320,30); 

    		  

    		  sp_list.setBounds(400,10,120,350); 

    		  invite_btn.setBounds(400,370,120,30); 

    		  exit_btn.setBounds(400,410,120,30); 

    		  

    		  p.setLayout(null);

    		  p.setBackground(Color.GRAY);

    		  p.add(sp_ta);

    		  p.add(la_msg);

    		  p.add(sendTF);

    		  p.add(sp_list);

    		  p.add(invite_btn);

    		  p.add(exit_btn);

    		  

    		  room_frame.add(p);

    		  room_frame.setBounds(300,200,550,500);

    		  room_frame.setVisible(true);

    		  sendTF.requestFocus();	 
    	   }
    	
    }
    //TODO:

    public class Menu {
    	
    	public Menu(String name, String comment, String friend) {
    		
        	popupMenu = new JPopupMenu();
        	lookinfo = new JMenuItem("친구정보보기");
        	sendMsg = new JMenuItem("채팅신청하기");
        	popupMenu.add(lookinfo);
        	popupMenu.add(sendMsg);

    		
    		
    		menu_frame = new JFrame("Menu");
    		user_name = new JLabel(name);
    		user_state = new JLabel(comment);
    		make_room = new JButton("방 만들기");
    		add_friend=new JButton("친구검색");
    		friend_list = new JList(new DefaultListModel());
    		friend_model = (DefaultListModel)friend_list.getModel();
    		
    		for(String add:friend.split(" ")) {
    			friend_model.addElement(add);
    		}
    		friend_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  		
    		
    		friend_list.addMouseListener(new MouseAdapter() {
    			@Override
    			public void mousePressed(MouseEvent e) {
    				// 오른쪽 버튼 클릭 시 ...
    				if(e.getModifiers() == MouseEvent.BUTTON3_MASK) { // 왼쪽이 1 가운데가 2 오른쪽이 3   BUTTON3_MASK - 오른쪽 버튼
    					// System.out.println("반응");
    					popupMenu.show(friend_list, e.getX(), e.getY());
        				friend_list = (JList)e.getSource();
    				}
    			}
    		});		
    		
    		friend_model.remove(0); // db에 저장된 친구이름이 null로 시작해서 앞에 null 지워줌
    		change_info = new JButton("정보 수정");
    		
    		
    		user_name.setBounds(10, 50, 150, 20);
    		user_state.setBounds(10, 80, 350, 15);
    		make_room.setBounds(200, 400, 120, 30);
    		add_friend.setBounds(10, 10, 120, 30);
    		friend_list.setBounds(10, 110, 350, 130);
    		change_info.setBounds(150, 10, 120,30);
    		
    		menu_p = new JPanel();
    		menu_p.setLayout(null);

    		menu_p.add(add_friend);
    		menu_p.add(change_info);

    		menu_p.add(user_name);
    		menu_p.add(user_state);
    		
    		menu_p.add(friend_list);
    		
    		menu_p.add(make_room);

    		menu_frame.add(menu_p);
    		
    		
    		
    		menu_frame.setSize(400,500);
    		menu_frame.setVisible(true);	

    		
    		//방만들기 버튼 눌리면
    		make_room.addActionListener(new ActionListener() {
     			@Override
     			public void actionPerformed(ActionEvent e) {
     				new Chatting_Room("chatting room");				
     			}
               });
    		add_friend.addActionListener(new ActionListener() {
     			@Override
     			public void actionPerformed(ActionEvent e) 
     			{
     						new Addfr();		
     			}
               });
    		change_info.addActionListener(new ActionListener() { // 메뉴에서 정보변경 버튼 누르면 

				@Override
				public void actionPerformed(ActionEvent e) {
						new ChangeInfo();
				}
    		});
    		
	    	}
	
    
	    
    	
    }
    public class Addfr //로그인해서, user를 검색하는창
    {
 	   public Addfr()
 	   {
 		   JFrame adder=new JFrame();
 		   adder.setTitle("친구 검색");
 		  adder.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 			adder.setLayout(new FlowLayout());
 			JLabel searid=new JLabel("아이디");
 			JTextField uid=new JTextField(20);
 			adder.add(searid);
 			adder.add(uid);
 			adder.setSize(300,150);
 			adder.setVisible(true);
 			JButton search=new JButton("검색");
 			JButton add=new JButton("추가");
 			JButton list=new JButton("친구리스트");
 			JPanel pnr=new JPanel();
 			pnr.add(search);
 			pnr.add(add);
 			pnr.add(list);
 			adder.add(pnr,BorderLayout.SOUTH);
 			search.addActionListener(new ActionListener() //text를 작성하고, 검색을 함. 
 				       	{
 				    	   public void actionPerformed(ActionEvent e)
 				    	   {
 				    		   String comment=e.getActionCommand();
 		    	        		  if(comment.contentEquals("검색")) 
 		    	        		  {
 				    			  String conveyid=uid.getText(); //텍스트창에서 문서를 받아온다
 				    			  out.println("Search"+conveyid);//서버에 검색하는 아이디를 전달.
 		    	        		  }
 				    	   }
 				       });	   
 			add.addActionListener(new ActionListener() //text로 정보를 확인하고, 친구를 추가함.
				       	{
				    	   public void actionPerformed(ActionEvent e)
				    	   {
				    		   String comment=e.getActionCommand();
		    	        		  if(comment.contentEquals("추가")) 
		    	        		  {
				    			  relaid=uid.getText(); //텍스트창에서 문서를 받아온다
				    			  out.println("ADDF"+relaid);//서버에 추가하려는 id를 보낸다. 
		    	        		  }
				    	   }
				       });	   
 			list.addActionListener(new ActionListener() //text로 정보를 확인하고, 친구를 추가함.
			       	{
			    	   public void actionPerformed(ActionEvent e)
			    	   {
			    		   String comment=e.getActionCommand();
	    	        		  if(comment.contentEquals("친구리스트")) 
	    	        		  {
	    	        			  out.println("List"+curid);
	    	        		  }
			    	   }
			       });	   
 		}  
 	   
 	   }
    
    public class ChangeInfo{ //내정보 변경하는 창
    	public ChangeInfo() {
    		JFrame ch_frame=new JFrame();
    		JPanel ch_panel = new JPanel();
    		ch_panel.setLayout(null);

    		
  		   	ch_frame.setTitle("내 정보 변경");
  		   	
  		   	JLabel nicklbl2 = new JLabel( "Nick Name");
  		   	JTextField nickField2 = new JTextField(50); //바꿀 닉네임
  		   	
  		   	JLabel commentlbl = new JLabel("Comment");
  		   	JTextField commentField = new JTextField(50); // 바꿀 상태 메시지
  		   	
  		   	JButton ch_btn = new JButton("정보 변경"); // 정보 변경 버튼 누르면 바꾸고자 하는 정보가 반영됨
  		   	
  		   	nicklbl2.setBounds(10,10,100,30);
  		   	nickField2.setBounds(130, 10, 300,30);
  		   	ch_panel.add(nickField2);
  		   	ch_panel.add(nicklbl2);
  		   	
  		   	commentlbl.setBounds(10,80,100,30);
  		   	commentField.setBounds(130, 80, 300,30);
  		   	ch_panel.add(commentlbl);
  		   	ch_panel.add(commentField);
  		   	
  		   	ch_btn.setBounds(330,120,100,30);
  		   	ch_panel.add(ch_btn);
  		   	
  		   	ch_frame.add(ch_panel);
  		   	ch_frame.setSize(500,200);
  		   	ch_frame.setVisible(true);
  		   	
  		   	ch_btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String ch_nick = nickField2.getText(); 
					String ch_comment = commentField.getText();
					out.println("ChangeInfo:"+ch_nick+":"+ch_comment);	// 바꿀 내용 서버에 알림
		    		user_state.setText(ch_comment);	// 바꾼 내용을 메뉴 칸에 반영함
					ch_frame.dispose();
				}
  		   		
  		   	});
  		   	
  		   	
  		   	
  		   
    	}
    	
    }
 private void run() {
              try {
            	  Socket socket = new Socket(serverAddress, portnum); //입력받은 ip주소와 portnumber로 소켓 생성.
                  in = new Scanner(socket.getInputStream());
                  out = new PrintWriter(socket.getOutputStream(), true);
                  String[] userinfo = null;
                  while(true)
                  {
                	  String line=in.nextLine();
                	  System.out.println(line);
                	  if(line.contains("access"))
                	  {
                		  userinfo = line.split(":");
                		  
                    	  JOptionPane.showMessageDialog(null, "로그인 성공", "로그인 확인!", JOptionPane.DEFAULT_OPTION);//서버가 로그인 성공이라고 했을 시, 창 표출
                    	  //TODO:
                    	  new Menu(userinfo[1],userinfo[2],userinfo[4]); 
                    	  curid=userinfo[3];
                	  }
                	  // 아이디가 틀렸을 때
                	  else if(line.contains("id invalid"))
                	  {
                		  JOptionPane.showMessageDialog(null, "아이디 틀림!", "로그인 확인!", JOptionPane.DEFAULT_OPTION); //로그인 실패 시, 창표출
                		  return;
                	  }
                	  
                	  // 비밀번호가 틀렸을 때
                	  else if(line.contains("pw invalid"))
                	  {
                		  JOptionPane.showMessageDialog(null, "비밀번호 틀림!", "로그인 확인!", JOptionPane.DEFAULT_OPTION); //로그인 실패 시, 창표출
                		  return;
                	  }
                	  else if(line.contains("Welcome"))
                	  {
                		  JOptionPane.showMessageDialog(null, "회원가입성공", "환영합니다!", JOptionPane.DEFAULT_OPTION); //회원가입 성공 시, 창표출
                		  return;
                	  }
                	  else if(line.contains("Searching")) //검색한 정보를 받았을 때,
                	  {
                		  String printinfo=line.substring(9); //정보 뽑아냄
                		  String [] showbox=printinfo.split(" ");
                		  String wholeinfo="";
                		 wholeinfo="name"+":"+showbox[0]+"\n"+"birthday"+":"+showbox[1]+"\n"+"email"+":"+showbox[2]+"\n"+"comment"+":"+showbox[3];
                		  JOptionPane.showMessageDialog(null, wholeinfo, "검색하신 정보는 위와 같습니다.", JOptionPane.DEFAULT_OPTION); 
                		  //묶은 정보를 출력.
                	
                	  }
                	  else if(line.contains("ADD")) //친구 추가완료됨
                	  {
                		  JOptionPane.showMessageDialog(null, "친구추가완료", "축하합니다", JOptionPane.DEFAULT_OPTION); //친구추가된 경우, 창표출
                		  friend_model.addElement(relaid);
                	  }
                	  else if(line.contains("DUPLICATION")) 
                	  {
                		  JOptionPane.showMessageDialog(null, "이미 추가된 친구입니다.", "친구 추가 실패",JOptionPane.DEFAULT_OPTION);// 친구가 중복된 경우
                	  }
                	  else if(line.contains("Frlist")) //server가 친구리스트를 보내줬을 때.
                	  {
                		  String flend=line.substring(6); //친구리스트를 문자열화
                		  String [] flist=flend.split(" "); //친구리스트가 한꺼번에 왔기 때문에, 쪼개준다.
                		  String ourfr="";             
                		  for(int i=0; i<flist.length;i++)
                		  {
                			  ourfr=ourfr+flist[i]+"\n"; //ourfr에 친구리스트 쪼개서 저장
                		  }
                		  JOptionPane.showMessageDialog(null, ourfr, "고객님의 친구명단은 아래와 같습니다", JOptionPane.DEFAULT_OPTION); //친구명단을 임시로 창표출
                		  return; //친구리스트 출력.
                	  }
                	  else if(line.contains("No user")) //server에서, 해당 사용자가 없다고 왓을 경우 오류 출력.
                	  {
                		  JOptionPane.showMessageDialog(null, "존재하지 않는 아이디입니다.", "error	", JOptionPane.DEFAULT_OPTION); //친구명단을 임시로 창표출
                		  
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
